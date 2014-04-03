/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

/**
 *
 * @author syncsys
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import web.dao.BoardsDAO;
import web.dao.TasksDAO;
import web.dao.UsersDAO;
import web.entity.*;
import web.service.common.DateUtility;
import web.service.common.ProjectDBConstants;
import web.service.common.ResultImpl;
import web.service.common.ValidationUtility;
import web.wrapper.UserWrapper;

import javax.mail.*;
import javax.mail.internet.*;

@Service
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService{
    
    @Autowired
    private UsersDAO userDAO;
    @Autowired
    private BoardsDAO boardDAO;
    @Autowired
    private TasksDAO taskDAO;
    @Autowired
    private ResultImpl result;
    
   
    
    @Transactional(readOnly = false)
    public ResultImpl save(Users user){
        if( userDAO.doesLoginIdExists(user.getEmail()) ){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.emailAlreadyExists"/*,"some.other.message"*/));
        }else{
            userDAO.save(user);
            if(user == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"));
            }else{
                result.setIsSuccessful(true);
                result.setObject(user);
                result.setMessageList(Arrays.asList("success.userCreated"));
            }
        }
        return result;
    }
    
    @Transactional
    public ResultImpl getUserByLoginId(String email){
        Users user = userDAO.getUserByLoginId(email);
        if (user != null){
            result.setIsSuccessful(true);
            result.setObject(user);
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.namedUserDoesNotExist"/*,"string"*/));
        }
        return result;
        
    }

    @Transactional
    public String getUserId(String email){
        Users user = userDAO.getUserByLoginId(email);
        String userID = null;
        if (user != null){
            userID = "" + user.getId();
        }
        return userID;

    }

    @Transactional
    public String getCompanyId(String email){
        Users user = userDAO.getUserByLoginId(email);
        String companyID = null;
        if (ValidationUtility.isExists(user)){
            if(ValidationUtility.isExists(user.getCompany())){
                companyID = "" + user.getCompany().getId();
            }
        }
        return companyID;

    }

    @Deprecated
    @Transactional(readOnly = false)
    //changeable with full authority by other peer developers{M-A}
    public ResultImpl changeUserRoleForBoard(String userEmail, String role, long boardId){
                   System.out.println("================1=============");
        Users user = userDAO.getUserByLoginId(userEmail);
        if (user != null){
            System.out.println("================2=============");
            Boards board = boardDAO.getBoardById(boardId);
            if(board != null){
                System.out.println("================3=============");
                //List<UserRoleForBoard> urfbList =  new ArrayList<UserRoleForBoard>( user.getUserRoleForBoard() );
                System.out.println("================4=============");
                    UserRoleForBoard urfb = new UserRoleForBoard();
                System.out.println("================5=============");
                    urfb.setBoard(board);
                    //urfb.setUser(user);
                    urfb.setRole(role);
                    //urfbList.add(urfb);
                    //user.setUserRoleForBoardList(urfbList);
                System.out.println("================6=============");
                    //System.out.println(urfb.getBoard().getTitle()+urfb.getRole()+urfb.getUser().getName()+"=====================");
                    result.setIsSuccessful(true);
            }
        }
        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl deleteBoard(Long id){
        int size = 0;
        Boards board = new Boards();
        board = (Boards) userDAO.findById(board, id);

        userDAO.remove(board);
        if(board == null){
            result.setIsSuccessful(false);
            result.setMessage("Board not deleted");

        }else{
            result.setIsSuccessful(true);
            result.setMessage("Board deleted successfully");

        }

        /*
        if(taskDAO.deleteTask(id)){
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.taskDeleted"));
        }else{
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.taskDeletionFailed"));
        }*/
        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl editBoard(Long id){
        int size = 0;
        Boards board = new Boards();
        board = (Boards) userDAO.findById(board, id);
        UserWrapper wrapper = new UserWrapper();
        wrapper.setBoardId("" + board.getId());
        wrapper.setBoardName(board.getTitle());
        wrapper.setBoardDesc(board.getDescription());
        if(ValidationUtility.isExists(board.getCompany())){
            wrapper.setCompanyId("" + board.getCompany().getId());
        }
        result.setObject(wrapper);
        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl saveUser(UserWrapper wrapper) {
        Users userTable = null;

        if(userDAO.doesLoginIdExists(wrapper.getEmail()) ){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage("The email address supplied is already taken.");
            result.setMessageList(Arrays.asList("error.emailAlreadyExists"/*,"string"*/));
            System.out.println("\n********** Error message from ServiceImpl 1 ***************\n");
            // setResult(false,null,Arrays.asList("error.emailAlreadyExists"/*,"string"*/) );
            return result;
        }else{
            userTable = new Users();
            userTable = populateUsersFromWrapper(wrapper, userTable);
            System.out.println("\nuser id before:" + userTable.getId());
            userDAO.save(userTable);
            System.out.println("\nuser id after:" + userTable.getId());
            //Implement for Boards_Users role
            if(ValidationUtility.isExists(userTable.getId())){
                Boards_Users boardUsers = new Boards_Users();
                Boards board = new Boards();
                UserRoleForBoard role = new UserRoleForBoard();
                role.setId(Long.valueOf(ProjectDBConstants.NO_ACCESS_ROLE_ID)); // For New User: assign no-access to all boards of the given company
                List boardUsersList = new ArrayList();
                List boardsList = new ArrayList();
                boardUsersList = userDAO.findByProperty(boardUsers, "userlist.id", userTable.getId());
                if(boardUsersList.isEmpty()){//double check for New User
                    if(ValidationUtility.isExists(wrapper.getCompanyId())){
                        boardsList = userDAO.findByProperty(board, "company.id", Long.valueOf(wrapper.getCompanyId()));
                        for (int i = 0; i < boardsList.size(); i++) {
                            board = (Boards) boardsList.get(i);
                            boardUsers = new Boards_Users();
                            boardUsers.setBoardlist(board);
                            boardUsers.setUserlist(userTable);
                            boardUsers.setUserRoleForBoard(role);
                            boardUsers.setWip(Long.valueOf(0));
                            if(ValidationUtility.isExists(wrapper.getCreatedBy())){
                                boardUsers.setCreatedBy(Long.valueOf(wrapper.getCreatedBy()));
                            }
                            boardUsers.setCreatedDate(new Date());
                            userDAO.save(boardUsers);
                        }
                    }
                }


            }
            /*User inserted in all boards as well
            board = new Boards();
            List boardList = new ArrayList();
            boardList = userDAO.findAll(board);
            for (int j = 0; j < boardList.size(); j++) {
                board = (Boards) boardList.get(j);
                if(!board.getUserList().contains(userTable)){
                    board.getUserList().add(userTable);
                }
                System.out.println("\nuser id 1:" + userTable.getId());
                boardDAO.save(board);
                System.out.println("\nuser id 2:" + userTable.getId());
            }
            System.out.println("\nuser id 3:" + userTable.getId());*/


            if(userTable == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessage("There was an unknown error while creating user.");
                //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
                System.out.println("\n********** Error message from ServiceImpl 2 ***************\n");
                return result;
            }else{
                if(sendEmail(userTable)){
                    result.setIsSuccessful(true);
                    result.setObject(wrapper);
                    result.setMessage("User created successfully and email sent to the new user");
                    return result;
                }else{
                    result.setIsSuccessful(true);
                    result.setObject(wrapper);
                    result.setMessage("User created successfully but email not sent. Please check your SMTP settings or firewall.");
                    return result;
                }
            }
        }
    }

    @Transactional(readOnly = false)
    public ResultImpl updateUserInfo(UserWrapper wrapper) {

        Users userTable = new Users();
        if(ValidationUtility.isExists(wrapper.getUserId())){
            userTable = (Users) userDAO.findById(userTable, Long.valueOf(wrapper.getUserId()));
            userTable = populateUsersFromWrapper(wrapper, userTable);
            userDAO.save(userTable);

            if(userTable == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessage("There was an unknown error while updating user.");
                //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
                System.out.println("\n********** Error message from ServiceImpl 2 ***************\n");
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setObject(wrapper);
                result.setMessage("User updated successfully.");
                //result.setMessageList(Arrays.asList("success.userCreated"/*,"string"*/));
                System.out.println("\n********** Success message from ServiceImpl ***************\n");
                return result;
            }
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage("Error: User does not exist");
            return result;
        }
    }


    @Transactional(readOnly = false)
    public ResultImpl editUserAccess(UserWrapper wrapper) {
        Boards_Users boardUsers = null;
        if(ValidationUtility.isExists(wrapper.getUserList())){
            if (wrapper.getUserList().size() > 0) {
                for (UserWrapper uWrapper : wrapper.getUserList()) {
                    if(ValidationUtility.isExists(uWrapper.getEmail())){
                        /*uTable = new Users();
                        uTable = (Users)userDAO.findById(uTable, Long.valueOf(uWrapper.getUserId()));
                        if(ValidationUtility.isExists(wrapper.getRoleId())){
                            UserRoleForBoard uRoleTable = new UserRoleForBoard();
                            uRoleTable.setId(Long.valueOf(wrapper.getRoleId()));
                            uTable.setUserRoleForBoard(uRoleTable);
                        }*/
                        //Implement for Boards_Users role
                        if(ValidationUtility.isExists(wrapper.getBoardId()) && ValidationUtility.isExists(uWrapper.getUserId())){
                            boardUsers = new Boards_Users();
                            boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(wrapper.getBoardId()), "userlist.id", Long.valueOf(uWrapper.getUserId()));
                            UserRoleForBoard uRoleTable = new UserRoleForBoard();
                            uRoleTable.setId(Long.valueOf(wrapper.getRoleId()));
                            boardUsers.setUserRoleForBoard(uRoleTable);
                            if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
                                boardUsers.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
                            }
                            boardUsers.setUpdatedDate(new Date());
                            if(ValidationUtility.isExists(wrapper.getWip())){
                                boardUsers.setWip(Long.valueOf(wrapper.getWip()));
                            }else{
                                boardUsers.setWip(Long.valueOf(0));
                            }
                            userDAO.save(boardUsers);
                        }
                        if(boardUsers == null){
                            result.setIsSuccessful(false);
                            result.setObject(null);
                            result.setMessage("There was an unknown error while updating selected users' access levels");
                            //return result;
                        }else{
                            result.setIsSuccessful(true);
                            result.setObject(uWrapper);
                            result.setMessage("The selected users' access levels updated successfully.");
                            //return result;
                        }
                    }

                }
            }
        }
        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl deleteUser(UserWrapper wrapper) {
        int size = 0;
        Users user = new Users();
        Tasks_Users_Updated taskUser = new Tasks_Users_Updated();
        Boards_Users boardUser = new Boards_Users();
        if(ValidationUtility.isExists(wrapper.getUserId())){
            user = (Users) userDAO.findById(user, Long.valueOf(wrapper.getUserId()));

            // Implementation for manually generated tasks_users_updated join table (for extra columns)
            List taskUsersList = new ArrayList();
            taskUsersList = userDAO.findByProperty(taskUser, "userlist.id", Long.valueOf(wrapper.getUserId()));
            List boardUsersList = new ArrayList();
            boardUsersList = userDAO.findByProperty(boardUser, "userlist.id", Long.valueOf(wrapper.getUserId()));
            for (int i = 0; i < taskUsersList.size(); i++) {
                taskUser = new Tasks_Users_Updated();
                taskUser = (Tasks_Users_Updated) taskUsersList.get(i);
                userDAO.remove(taskUser);
            }
            for (int i = 0; i < boardUsersList.size(); i++) {
                boardUser = new Boards_Users();
                boardUser = (Boards_Users) boardUsersList.get(i);
                userDAO.remove(boardUser);
            }
            userDAO.remove(user);


            /* Implementation for auto generated tasks_users join table*/
            if(user.getTaskList().isEmpty()){
                userDAO.remove(user);
            }else{

                Iterator<Tasks> it = user.getTaskList().iterator();
                size = user.getTaskList().size();
                while(it.hasNext()){
                    Tasks task = it.next();
                    if(ValidationUtility.isExists(task.getId())){
                        it.remove(); // To solve concurrentmodificationexception exception, add this line and comment out following lines
                        //user.getTaskList().remove(task);
                        //userDAO.save(user);
                    }
                }
                userDAO.remove(user);
            }

            if(user == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessage("There was an unknown error while removing user.");
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setMessage("User removed successfully.");
                return result;
            }
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage("Error: User does not exist");
            return result;
        }
    }

    @Transactional(readOnly = false)
    public ResultImpl deleteUserAccess(UserWrapper wrapper) {
        Users user = new Users();
        Tasks_Users_Updated taskUser = new Tasks_Users_Updated();
        Boards_Users boardUser = new Boards_Users();
        if(ValidationUtility.isExists(wrapper.getUserId())){
            user = (Users) userDAO.findById(user, Long.valueOf(wrapper.getUserId()));
            user.setEnabled(false);
            if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
                user.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
            }
            user.setUpdatedDate(new Date());
            userDAO.save(user);

            if(user == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessage("There was an unknown error while disabling user.");
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setMessage("User disabled successfully.");
                return result;
            }
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage("Error: User does not exist");
            return result;
        }
    }

    @Transactional(readOnly = false)
    public ResultImpl taskAssignment(UserWrapper wrapper) {
        Users uTable = null;
        Tasks tTable = null;
        Boxes boxTable = null;
        Boards boardTable = null;
        Tasks_Users_Updated taskUsers = null;
        UserWrapper tempW = new UserWrapper();
        tempW =  checkWip(wrapper);
        if(tempW.isTempWipValue()){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage(tempW.getTempWipMessage());
        }else{
            if(ValidationUtility.isExists(wrapper.getTaskId())){
                tTable = new Tasks();
                tTable = (Tasks)userDAO.findById(tTable, Long.valueOf(wrapper.getTaskId()));
                /*if(ValidationUtility.isExists(tTable.getParentBox())){
                    boxTable = tTable.getParentBox();
                    while(boxTable != null){
                        if(boxTable.getIsFirstLevelBox()){
                            boardTable = boxTable.getParentBoard();
                            break;
                        }
                    }



                }*/

                if(ValidationUtility.isExists(wrapper.getUserList())){
                    if (wrapper.getUserList().size() > 0) {
                        for (UserWrapper uWrapper : wrapper.getUserList()) {
                            if(ValidationUtility.isExists(uWrapper.getUserId())){
                                uTable = new Users();
                                uTable = (Users)userDAO.findById(uTable, Long.valueOf(uWrapper.getUserId()));

                                //Implementation for manually generated tasks_users_updated table (insert createdBy, createdDate columns)
                                taskUsers = new Tasks_Users_Updated();
                                taskUsers = (Tasks_Users_Updated) userDAO.findByTwoPropertiesUnique(taskUsers, "tasklist.id", tTable.getId(), "userlist.id", Long.valueOf(uWrapper.getUserId()));

                                if(uWrapper.isEnableUserAssignId()){
                                    if(!ValidationUtility.isExists(taskUsers)){
                                        taskUsers = new Tasks_Users_Updated();
                                        taskUsers.setUserlist(uTable);
                                        taskUsers.setTasklist(tTable);
                                        if(ValidationUtility.isExists(wrapper.getCreatedBy())){
                                            taskUsers.setCreatedBy(Long.valueOf(wrapper.getCreatedBy()));
                                        }
                                        taskUsers.setCreatedDate(new Date());
                                    }else{
                                        if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
                                            taskUsers.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
                                        }
                                        taskUsers.setUpdatedDate(new Date());
                                    }
                                    userDAO.save(taskUsers);
                                }else{
                                    if(ValidationUtility.isExists(taskUsers)){
                                        userDAO.remove(taskUsers);
                                    }
                                }

                                /* Implementation for auto generated tasks_users join table*/
                                if(uWrapper.isEnableUserAssignId()){
                                    if(!uTable.getTaskList().contains(tTable)){
                                        uTable.getTaskList().add(tTable);
                                    }
                                }else{
                                    if(uTable.getTaskList().contains(tTable)){
                                        uTable.getTaskList().remove(tTable);
                                    }
                                }
                                userDAO.save(uTable);


                                if(uTable == null){
                                    result.setIsSuccessful(false);
                                    result.setObject(null);
                                    result.setMessage("There was an unknown error while assigning task to users");
                                    result.setMessageList(Arrays.asList("There was an unknown error while assigning task to users"));
                                }else{
                                    result.setIsSuccessful(true);
                                    result.setObject(uWrapper);
                                    result.setMessageList(Arrays.asList("The selected users assigned to the task successfully."));
                                    result.setMessage("The selected users assigned to the task successfully.");
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public UserWrapper checkWip(UserWrapper wrapper) {
        Users uTable = null;
        Tasks tTable = null;
        Boards_Users boardUsers = null;
        Tasks_Users_Updated taskUsers = new Tasks_Users_Updated();
        UserWrapper retWrapper = new UserWrapper();
        retWrapper.setTempWipValue(false);
        if(ValidationUtility.isExists(wrapper.getTaskId())){
            tTable = new Tasks();
            tTable = (Tasks)userDAO.findById(tTable, Long.valueOf(wrapper.getTaskId()));
            if(ValidationUtility.isExists(wrapper.getUserList())){
                if (wrapper.getUserList().size() > 0) {
                    for (UserWrapper uWrapper : wrapper.getUserList()) {
                        if(ValidationUtility.isExists(uWrapper.getUserId())){
                            uTable = new Users();
                            uTable = (Users)userDAO.findById(uTable, Long.valueOf(uWrapper.getUserId()));
                            if(uWrapper.isEnableUserAssignId()){
                                Long wipValue = Long.valueOf("0");
                                if(ValidationUtility.isExists(wrapper.getBoardId()) && ValidationUtility.isExists(uWrapper.getUserId())){
                                    boardUsers = new Boards_Users();//Implement for Boards_Users role
                                    boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(wrapper.getBoardId()), "userlist.id", Long.valueOf(uWrapper.getUserId()));
                                    if(ValidationUtility.isExists(boardUsers.getWip())){
                                       wipValue = boardUsers.getWip();
                                    }
                                }
                                // Implementation for manually generated tasks_users_updated join table (for extra columns)
                                List usersList = new ArrayList();
                                usersList = userDAO.findByProperty(taskUsers, "userlist.id", Long.valueOf(uWrapper.getUserId()));
                                Long tasksSize = Long.valueOf(usersList.size());
                                /* Implementation for auto generated tasks_users join table
                                Long tasksSize = Long.valueOf(uTable.getTaskList().size());
                                 */
                                System.out.println("\n User: "+ uTable.getFirstName() + " - Wip: " + wipValue + " - Task Size: " + tasksSize+ "");
                                if(wipValue > 0){
                                    if(wipValue <= tasksSize){
                                        retWrapper.setTempWipValue(true);
                                        retWrapper.setTempWipMessage("Tasks not assigned. User ["+uTable.getFirstName()+" "+uTable.getLastName()+"] exceeds WIP limit.");
                                        return retWrapper;
                                    }else{
                                        retWrapper.setTempWipValue(false);
                                    }
                                }else{
                                    retWrapper.setTempWipValue(false);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Result: " + retWrapper.isTempWipValue());
        return retWrapper;
    }

    public ResultImpl populateUserInfo(String userId){
        Users uTable = new Users();
        if(!ValidationUtility.isExists(userId)){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage("Please select a User before Update");
            return result;
        }else{
            uTable = (Users)userDAO.findById(uTable, Long.valueOf(userId));
            UserWrapper wrapper = new UserWrapper();
            wrapper = populateUserWrapperFromUserTable(wrapper, uTable);
            if(wrapper == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessage("There was an unknown error while updating user.");
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setObject(wrapper);
                result.setMessage("The user was updated successfully.");
                return result;
            }
        }
    }

    private Users populateUsersFromWrapper(UserWrapper wrapper, Users table){

        if(ValidationUtility.isExists(wrapper.getUserId())){
            table.setId(Long.valueOf(wrapper.getUserId()));
            if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
                table.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
            }
            table.setUpdatedDate(new Date());
        }else{
            if(ValidationUtility.isExists(wrapper.getCreatedBy())){
                table.setCreatedBy(Long.valueOf(wrapper.getCreatedBy()));
            }
            table.setCreatedDate(new Date());
        }
        table.setEmail(wrapper.getEmail());
        table.setFirstName(wrapper.getFirstName());
        table.setLastName(wrapper.getLastName());
        if(ValidationUtility.isExists(wrapper.getPassword1())){
            table.setPassword(wrapper.getPassword1());
        }
        if(ValidationUtility.isExists(wrapper.isUserAccessFlag())){
            if(wrapper.isUserAccessFlag()){
                if(ValidationUtility.isExists(wrapper.isEnableUserId())){
                    table.setEnabled(wrapper.isEnableUserId());
                }
                if(ValidationUtility.isExists(wrapper.isAccountAdmin())){
                    table.setAccountAdmin(wrapper.isAccountAdmin());
                }
                if(ValidationUtility.isExists(wrapper.isCanCreateBoard())){
                    table.setBoardCreator(wrapper.isCanCreateBoard());
                }
            }
        }

        if(ValidationUtility.isExists(wrapper.getCompanyId())){
            Companies comp = new Companies();
            comp.setId(Long.valueOf(wrapper.getCompanyId()));
            table.setCompany(comp);
        }

        /*if(!ValidationUtility.isExists(table.getUserRoleForBoard())){
            UserRoleForBoard role = new UserRoleForBoard();
            role.setId(Long.valueOf(ProjectDBConstants.USER_ROLE_ID)); // For New User: assign role of user
            table.setUserRoleForBoard(role);
        }
        if(!ValidationUtility.isExists(table.getWip())){
            table.setWip(Long.valueOf(0));  // For new User: assign Wip = 0
        }*/
        System.out.println("\n\n Image Name: " + wrapper.getImageName());
        if(ValidationUtility.isExists(wrapper.getImageName())){
            table.setImageName(wrapper.getImageName());
        }
        if(!ValidationUtility.isExists(table.getImageName())){
            table.setImageName("/images/user/avatar-small.png");
        }

        if(ValidationUtility.isExists(wrapper.getImageData())){
            if(wrapper.getImageData().getSize() > 0){
                MultipartFile file = wrapper.getImageData();
                try{
                    table.setPersonimage(file.getBytes());
                } catch (Exception e) {
                    System.out.println("------------- File Info exception ----------------");
                    e.printStackTrace();
                }
            }
        }


        return table;
    }

    @Transactional
    public List<UserWrapper> populateRoleList() {
        List<UserWrapper> list = new ArrayList<UserWrapper>();
        UserRoleForBoard table = new UserRoleForBoard();
        UserWrapper wrapper = null;

        List qList = new ArrayList();
        qList = userDAO.findAll(table);
        for (int j = 0; j < qList.size(); j++) {
            table = (UserRoleForBoard) qList.get(j);
            wrapper = new UserWrapper();
            wrapper = populateWrapperFromRoleTable(wrapper, table);
            list.add(wrapper);
        }

        return list;
    }

    @Transactional
    public List<UserWrapper> populateCompanyList() {
        List<UserWrapper> list = new ArrayList<UserWrapper>();
        Companies table = new Companies();
        UserWrapper wrapper = null;

        List qList = new ArrayList();
        qList = userDAO.findAll(table);
        for (int j = 0; j < qList.size(); j++) {
            table = (Companies) qList.get(j);
            wrapper = new UserWrapper();
            wrapper = populateWrapperFromCompanyTable(wrapper, table);
            list.add(wrapper);
        }

        return list;
    }

    @Transactional
    public List<UserWrapper> listAllUsersWithDetail() {
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = new Users();
        List list = new ArrayList();
        UserWrapper wrapper = null;

        list = userDAO.findAll(userTable);

        for (int i = 0; i < list.size(); i++) {
            userTable = (Users) list.get(i);
            wrapper = new UserWrapper();
            wrapper = populateUserWrapperFromUserTable(wrapper, userTable);
            userList.add(wrapper);
        }


        return userList;
    }

    @Transactional
    public List<UserWrapper> listUsersWithDetail(String companyId, String boardId) {
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = new Users();
        List list = new ArrayList();
        UserWrapper wrapper = null;
        if(ValidationUtility.isExists(companyId)){
            list = userDAO.findByProperty(userTable, "company.id", Long.valueOf(companyId));
        }

        for (int i = 0; i < list.size(); i++) {
            userTable = (Users) list.get(i);
            if(userTable.isEnabled()){ // get list of enabled users
                wrapper = new UserWrapper();
                wrapper.setBoardId(boardId);
                wrapper = populateUserWrapperFromUserTable(wrapper, userTable);
                userList.add(wrapper);
            }
        }


        return userList;
    }

    @Transactional
    public List<UserWrapper> listUsersWithDetail(String companyId) {
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = new Users();
        List list = new ArrayList();
        UserWrapper wrapper = null;
        if(ValidationUtility.isExists(companyId)){
            list = userDAO.findByProperty(userTable, "company.id", Long.valueOf(companyId));
        }

        for (int i = 0; i < list.size(); i++) {
            userTable = (Users) list.get(i);
            wrapper = new UserWrapper();
            wrapper = populateUserWrapperFromUserTable(wrapper, userTable);
            userList.add(wrapper);
        }


        return userList;
    }


    @Transactional
    public ResultImpl listUsersWithDetail(UserWrapper uWrapper) {
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = null;
        if(ValidationUtility.isExists(uWrapper.getUserList())){
            for (UserWrapper wrapper : uWrapper.getUserList()) {
                if(wrapper.isEnableUserEditId()){
                    userTable = new Users();
                    userTable = (Users)userDAO.findById(userTable, Long.valueOf(wrapper.getUserId()));
                    wrapper = populateUserWrapperFromUserTable(wrapper, userTable);
                    wrapper.setEnableUserEditId(true);
                    userList.add(wrapper);
                }
            }
        }

        if(!userList.isEmpty() && userList.size()>0){
            result.setIsSuccessful(true);
            result.setObject(userList);
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
        }

        return result;
    }

    @Transactional
    public List<UserWrapper> populateUsersTaskList(Long userId) {
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = new Users();
        Tasks_Users_Updated taskUserTable = new Tasks_Users_Updated();
        Tasks taskTable = new Tasks();
        List list = new ArrayList();
        UserWrapper wrapper = null;
        if(ValidationUtility.isExists(userId)){
            list = userDAO.findByProperty(taskUserTable, "userlist.id", Long.valueOf(userId));
        }

        for (int i = 0; i < list.size(); i++) {
            taskUserTable = (Tasks_Users_Updated) list.get(i);
            wrapper = new UserWrapper();
            taskTable = (Tasks)userDAO.findById(taskTable, taskUserTable.getTasklist().getId());
            if(ValidationUtility.isExists(taskUserTable.getCreatedBy())){
                userTable = (Users)userDAO.findById(userTable, taskUserTable.getCreatedBy());
            }
            wrapper.setTaskName(taskTable.getTitle());
            wrapper.setTaskDesc(taskTable.getDescription());
            wrapper.setTaskPriority(taskTable.getPriority());
            wrapper.setTaskStatus(taskTable.getStatus());
            wrapper.setTaskAssignedBy(userTable.getFirstName());
            wrapper.setTaskAssignedDate(DateUtility.getCustomDate("dd-MM-yyyy", taskUserTable.getCreatedDate()));
            userList.add(wrapper);
        }


        return userList;
    }

    private UserWrapper populateWrapperFromRoleTable(UserWrapper wrapper, UserRoleForBoard table){
        wrapper.setRoleId("" + table.getId());
        wrapper.setRoleName(table.getRole());

        return wrapper;
    }

    private UserWrapper populateWrapperFromCompanyTable(UserWrapper wrapper, Companies table){
        wrapper.setCompanyId("" + table.getId());
        wrapper.setCompanyName(table.getName());

        return wrapper;
    }


    private UserWrapper populateUserWrapperFromUserTable(UserWrapper wrapper, Users table){
        wrapper.setUserId("" + table.getId());
        wrapper.setFirstName(table.getFirstName());
        wrapper.setLastName(table.getLastName());
        wrapper.setEmail(table.getEmail());

        //Implement for Boards_Users role
        /*if(ValidationUtility.isExists(table.getUserRoleForBoard())){
            wrapper.setRoleName(table.getUserRoleForBoard().getRole());
            wrapper.setRoleId("" + table.getUserRoleForBoard().getId());
        }
        wrapper.setWip("" + table.getWip());*/
        if(ValidationUtility.isExists(wrapper.getBoardId()) && ValidationUtility.isExists(table.getId())){
            Boards_Users boardUsers = new Boards_Users();//Implement for Boards_Users role
            boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(wrapper.getBoardId()), "userlist.id", table.getId());
            if(ValidationUtility.isExists(boardUsers)){
                if(ValidationUtility.isExists(boardUsers.getWip())){
                    wrapper.setWip("" + boardUsers.getWip());
                }
                if(ValidationUtility.isExists(boardUsers.getUserRoleForBoard())){
                    wrapper.setRoleName(boardUsers.getUserRoleForBoard().getRole());
                    wrapper.setRoleId("" + boardUsers.getUserRoleForBoard().getId());
                }
            }
        }
        wrapper.setEnableUserEditId(false);
        wrapper.setEnableUserAssignId(false);
        if(ValidationUtility.isExists(table.isAccountAdmin())){
            wrapper.setAccountAdmin(table.isAccountAdmin());
        }
        if(ValidationUtility.isExists(table.isEnabled())){
            wrapper.setEnableUserId(table.isEnabled());
            if(table.isEnabled()){
                wrapper.setEnableUser("Enabled");
            }else{
                wrapper.setEnableUser("Disabled");
            }
        }
        if(ValidationUtility.isExists(table.isBoardCreator())){
            wrapper.setCanCreateBoard(table.isBoardCreator());
        }
        if(ValidationUtility.isExists(table.getImageName())){
            wrapper.setImageName(table.getImageName());
        }
        //wrapper.setAddress();
        //wrapper.setContactNumber();


        return wrapper;
    }

    public UserWrapper getTaskUsersList(Long taskId){
        Tasks tTable = new Tasks();
        Tasks_Users_Updated taskUsers = null;

        tTable = (Tasks)userDAO.findById(tTable, taskId);
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = new Users();
        List list = new ArrayList();
        UserWrapper returnWrapper = null;

        list = userDAO.findAll(userTable);

        for (int i = 0; i < list.size(); i++) {
            userTable = (Users) list.get(i);
            UserWrapper wrapper = null;
            wrapper = new UserWrapper();
            wrapper = populateUserWrapperFromUserTable(wrapper, userTable);

            // Implementation for manually generated tasks_users join table (for extra columns)
            taskUsers = new Tasks_Users_Updated();
            taskUsers = (Tasks_Users_Updated) userDAO.findByTwoPropertiesUnique(taskUsers, "tasklist.id", tTable.getId(), "userlist.id", userTable.getId());
            if(ValidationUtility.isExists(taskUsers)){
                wrapper.setEnableUserAssignId(true);
                userList.add(wrapper);
            }else{
                userList.remove(wrapper);
            }

            /* Implementation for auto generated tasks_users join table
            System.out.println("\n ---- Task exist for User Id : " + userTable.getId());
            System.out.println(" ---- Task exist for User Size : " + userTable.getTaskList().size() + "\n");
            if(userTable.getTaskList().contains(tTable)){
                System.out.println("\n ---- Assigned Task Id: " + tTable.getId());
                System.out.println("\n ---- Assigned User Id: " + userTable.getId());
                wrapper.setEnableUserAssignId(true);
                userList.add(wrapper);
            }else{
                userList.remove(wrapper);
            }*/


        }

        if(userList.size()>0){
            returnWrapper = new UserWrapper();
            returnWrapper.setTaskUserSize("" + userList.size());
            returnWrapper.setTaskUserExistSingle(true);
            if(userList.size()==1){
                returnWrapper.setTaskUserExistMultiple(false);
            }else{
                returnWrapper.setTaskUserExistMultiple(true);
            }
        }

        return returnWrapper;
    }

    public ResultImpl getTaskUsersListAll(Long boardId, Long taskId, String companyId){
        Tasks tTable = new Tasks();

        tTable = (Tasks)userDAO.findById(tTable, taskId);
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = new Users();
        Boards_Users buTable = new Boards_Users();
        Tasks_Users_Updated taskUsers = null;
        List list = new ArrayList();
        UserWrapper returnWrapper = null;
        if(ValidationUtility.isExists(companyId)){
            list = userDAO.findByProperty(userTable, "company.id", Long.valueOf(companyId));
        }

        for (int i = 0; i < list.size(); i++) {
            userTable = (Users) list.get(i);
            UserWrapper wrapper = null;
            wrapper = new UserWrapper();
            buTable = new Boards_Users();
            buTable = (Boards_Users)userDAO.findByTwoPropertiesUnique(buTable, "boardlist.id", boardId, "userlist.id", userTable.getId());
            if(ValidationUtility.isExists(buTable)){
                if(ValidationUtility.isExists(buTable.getUserRoleForBoard())){
                    if(!(buTable.getUserRoleForBoard().getId() == ProjectDBConstants.NO_ACCESS_ROLE_ID.intValue()) &&
                       !(buTable.getUserRoleForBoard().getId() == ProjectDBConstants.READER_ROLE_ID.intValue())){
                        wrapper = populateUserWrapperFromUserTable(wrapper, userTable);
                        // Implementation for manually generated tasks_users_updated join table (for extra columns)
                        taskUsers = new Tasks_Users_Updated();
                        taskUsers = (Tasks_Users_Updated) userDAO.findByTwoPropertiesUnique(taskUsers, "tasklist.id", tTable.getId(), "userlist.id", userTable.getId());
                        if(ValidationUtility.isExists(taskUsers)){
                            wrapper.setEnableUserAssignId(true);
                        }

                        /* Implementation for auto generated tasks_users join table
                        if(userTable.getTaskList().contains(tTable)){
                            System.out.println("\n ---- User exist for Task: " + tTable.getId());
                            System.out.println("---- Task exist for User: " + userTable.getId()+"\n");
                            wrapper.setEnableUserAssignId(true);
                        }*/
                        userList.add(wrapper);
                    }
                }
            }
        }

        if(!userList.isEmpty() && userList.size()>0){
            result.setIsSuccessful(true);
            result.setObject(userList);
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
        }

        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl changePassword(UserWrapper wrapper) {

        Users userTable = new Users();
        userTable = (Users)userDAO.findById(userTable, Long.valueOf(wrapper.getUserId()));
        if(userTable.getPassword().equals(wrapper.getOldPassword())){
            userTable.setPassword(wrapper.getPassword1());
            if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
                userTable.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
            }
            userTable.setUpdatedDate(new Date());
            userDAO.save(userTable);
            result.setIsSuccessful(true);
            result.setObject(wrapper);
            result.setMessage("Your Password has been changed successfully");
            return result;
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage("Password not changed. Your old Password does not exist in database");
            return result;
        }
    }

    @Transactional
    public String populatePersonImage(String userId, String path){
        String fileNameToSave = "";
        UserWrapper wrapper = new UserWrapper();
        Users table = new Users();

        String fileSeparator = "/";
        String your_os = System.getProperty("os.name").toLowerCase();
        if(your_os.indexOf("win") >= 0){
            fileSeparator = "/";
        }else{
            fileSeparator = "\\";
        }

        if(ValidationUtility.isExists(userId)){
            table = (Users)userDAO.findById(table, Long.valueOf(userId));
            if(ValidationUtility.isExists(table.getPersonimage())){
                fileNameToSave = File.separator + "images"+ File.separator + "user" + File.separator + table.getId() + ".png";
                fileNameToSave = fileNameToSave.replace("\\", fileSeparator);
                wrapper.setImageName(fileNameToSave);
                try {
                    populatePersonImages(table, fileNameToSave, path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                fileNameToSave = File.separator + "images"+ File.separator + "user" + File.separator + "avatar-small.png";
                fileNameToSave = fileNameToSave.replace("\\", fileSeparator);
                wrapper.setImageName(fileNameToSave);
            }

        }
        System.out.println("\n File Name to Save: " + fileNameToSave + "\n");

        return fileNameToSave;
    }

    private void populatePersonImages(Users personTable, String fileNameToSave, String path) throws Exception {
        if (personTable != null) {

            byte imageBytes[] = null;
            FileOutputStream fos = null;
            try {
                imageBytes = personTable.getPersonimage();
                fos = new FileOutputStream(path + File.separator + "resources"+ fileNameToSave);
                fos.write(imageBytes);

            } catch (FileNotFoundException fileNotFoundException) {
                throw new Exception(fileNotFoundException);
            } catch (IOException ioException) {
                throw new Exception(ioException);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ioException) {
                        throw new Exception(ioException);
                    }
                }
            }
        }
    }

    private boolean sendEmailUsingGmail(Users userTable){
        boolean result = false;
        String to = userTable.getEmail();
        String from = "farhan.bajwa@hotmail.com";
        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";
        final String gmailUsername = "onlinetaskboard@gmail.com";
        final String gmailPassword = "Onlinetaskboard123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gmailUsername, gmailPassword);
                    }
                });
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Welcome to OnlineTaskBoard");
            message.setContent("<h1>Hi"+userTable.getFirstName()+"</h1>",
                    "text/html" );
            Transport.send(message);
            result = true;
            System.out.println("Sent message successfully....");

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }finally {
            return result;
        }
    }

    private boolean sendEmailLocal(Users userTable){
        boolean result = false;
        String from = "farhan.bajwa@gmail.com";
        String to = "farhan.bajwa@hotmail.com";
        String smtpHost = "smtp.emailsrvr.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        Session session = Session.getDefaultInstance(props);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Welcome to OnlineTaskBoard");
            message.setContent("<h1>Hi"+userTable.getFirstName()+"</h1>",
                    "text/html" );
            Transport.send(message);
            result = true;
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }finally {
            return result;
        }
    }

    private boolean sendEmail(Users userTable){
        boolean result = false;
        final String SMTP_OUT_SERVER = "smtp.gmail.com"; // gmail smtp server
        final String USER = "onlinetaskboard@gmail.com";
        final String PASSWORD = "Onlinetaskboard123";

        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", SMTP_OUT_SERVER);

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.user", USER);
        props.setProperty("mail.password", PASSWORD);

        try{
            Session mailSession = Session.getDefaultInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            MimeMessage message = new MimeMessage(mailSession);
            message.setSentDate(new java.util.Date());
            message.setSubject("Welcome to OnlineTaskBoard");
            message.setFrom(new InternetAddress(USER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userTable.getEmail()));
            message.setContent("<h3>Hi "+userTable.getFirstName()+",</h3><br /><br />" +
                    "Thanks for using OnlineTaskBoard!<br />We are glad you are on board!<br /><br />" +
                    "<h4>Thanks,<br />The OnlineTaskBoard Team</h4>", "text/html");

            transport.connect(SMTP_OUT_SERVER, USER, PASSWORD);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
            result = true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }finally {
            return result;
        }

    }




}
