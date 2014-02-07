/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

/**
 *
 * @author syncsys
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.BoardsDAO;
import web.dao.TasksDAO;
import web.dao.UsersDAO;
import web.entity.Tasks;
import web.entity.UserRoleForBoard;
import web.entity.Users;
import web.entity.Boards;
import web.service.common.ResultImpl;
import web.service.common.ValidationUtility;
import web.wrapper.UserWrapper;

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
            Users userToBeReturned = userDAO.save(user);
            if(userToBeReturned == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"));
            }else{
                result.setIsSuccessful(true);
                result.setObject(userToBeReturned);
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
    public ResultImpl saveUser(UserWrapper wrapper) {

        if(userDAO.doesLoginIdExists(wrapper.getEmail()) ){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessage("The email address supplied is already taken.");
            result.setMessageList(Arrays.asList("error.emailAlreadyExists"/*,"string"*/));
            System.out.println("\n********** Error message from ServiceImpl 1 ***************\n");
            // setResult(false,null,Arrays.asList("error.emailAlreadyExists"/*,"string"*/) );
            return result;
        }else{
            Users userTable = new Users();
            userTable = populateUsersFromWrapper(wrapper, userTable);
            System.out.println("\nuser id before:" + userTable.getId());
            userDAO.save(userTable);
            System.out.println("\nuser id after:" + userTable.getId());

            if(userTable == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessage("There was an unknown error while creating user.");
                //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
                System.out.println("\n********** Error message from ServiceImpl 2 ***************\n");
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setObject(wrapper);
                result.setMessage("The user was created successfully.");
                //result.setMessageList(Arrays.asList("success.userCreated"/*,"string"*/));
                System.out.println("\n********** Success message from ServiceImpl ***************\n");
                return result;
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
        Users uTable = null;
        if(ValidationUtility.isExists(wrapper.getUserList())){
            if (wrapper.getUserList().size() > 0) {
                for (UserWrapper uWrapper : wrapper.getUserList()) {
                    if(uWrapper.isEnableUserEditId()){
                        if(ValidationUtility.isExists(uWrapper.getUserId())){
                            uTable = new Users();
                            uTable = (Users)userDAO.findById(uTable, Long.valueOf(uWrapper.getUserId()));
                            if(ValidationUtility.isExists(uWrapper.getRoleId())){
                                UserRoleForBoard uRoleTable = new UserRoleForBoard();
                                uRoleTable.setId(Long.valueOf(uWrapper.getRoleId()));
                                uTable.setUserRoleForBoard(uRoleTable);
                            }
                            if(ValidationUtility.isExists(uWrapper.getWip())){
                                uTable.setWip(Long.valueOf(uWrapper.getWip()));
                            }else{
                                uTable.setWip(Long.valueOf(0));
                            }
                            userDAO.save(uTable);
                            if(uTable == null){
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
        }
        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl taskAssignment(UserWrapper wrapper) {
        Users uTable = null;
        Tasks tTable = null;
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
                                if(!uTable.getTaskList().contains(tTable)){
                                    uTable.getTaskList().add(tTable);
                                }
                            }else{
                                if(uTable.getTaskList().contains(tTable)){
                                    uTable.getTaskList().remove(tTable);
                                }
                            }
                            userDAO.save(uTable);
                            //uTable.getTaskList().add(tTable);
                            System.out.println("\n\n\n\nUser ID: " + uTable.getId());
                            System.out.println("Task ID: " + tTable.getId() +"\n\n\n\n");
                            //uTable.setTaskList(tasksList);

                            /*if(ValidationUtility.isExists(uWrapper.getRoleId())){
                                UserRoleForBoard uRoleTable = new UserRoleForBoard();
                                uRoleTable.setId(Long.valueOf(uWrapper.getRoleId()));
                                uTable.setUserRoleForBoard(uRoleTable);
                            }
                            if(ValidationUtility.isExists(uWrapper.getWip())){
                                uTable.setWip(Long.valueOf(uWrapper.getWip()));
                            }else{
                                uTable.setWip(Long.valueOf(10));
                            }*/

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

        return result;
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
        }
        table.setEmail(wrapper.getEmail());
        table.setFirstName(wrapper.getFirstName());
        table.setLastName(wrapper.getLastName());
        if(ValidationUtility.isExists(wrapper.getPassword1())){
            table.setPassword(wrapper.getPassword1());
        }
        if(ValidationUtility.isExists(wrapper.isEnableUserId())){
            table.setEnabled(wrapper.isEnableUserId());
        }
        if(ValidationUtility.isExists(wrapper.getEnableUser())){
            if(wrapper.getEnableUser().equalsIgnoreCase("on")){
                table.setEnabled(true);
            }else{
                table.setEnabled(false);
            }
        }

        if(!ValidationUtility.isExists(table.getUserRoleForBoard())){
            UserRoleForBoard role = new UserRoleForBoard();
            role.setId(Long.valueOf(2)); // For New User: assign role of user
            table.setUserRoleForBoard(role);
        }
        if(!ValidationUtility.isExists(table.getWip())){
            table.setWip(Long.valueOf(0));  // For new User: assign Wip = 0
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
    public List<UserWrapper> listUsersWithDetail() {
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
    public ResultImpl listUsersWithDetail(UserWrapper uWrapper) {
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        Users userTable = null;
        if(ValidationUtility.isExists(uWrapper.getUserList())){
            for (UserWrapper wrapper : uWrapper.getUserList()) {
                if(wrapper.isEnableUserEditId()){
                    userTable = new Users();
                    userTable = (Users)userDAO.findById(userTable, Long.valueOf(wrapper.getUserId()));
                    wrapper = populateUserWrapperFromUserTable(wrapper, userTable);
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

    private UserWrapper populateWrapperFromRoleTable(UserWrapper wrapper, UserRoleForBoard table){
        wrapper.setRoleId("" + table.getId());
        wrapper.setRoleName(table.getRole());

        return wrapper;
    }

    private UserWrapper populateUserWrapperFromUserTable(UserWrapper wrapper, Users table){
        wrapper.setUserId("" + table.getId());
        wrapper.setFirstName(table.getFirstName());
        wrapper.setLastName(table.getLastName());
        wrapper.setEmail(table.getEmail());
        if(ValidationUtility.isExists(table.getUserRoleForBoard())){
            wrapper.setRoleName(table.getUserRoleForBoard().getRole());
            wrapper.setRoleId("" + table.getUserRoleForBoard().getId());
        }
        wrapper.setWip("" + table.getWip());
        wrapper.setEnableUserEditId(false);
        wrapper.setEnableUserAssignId(false);
        wrapper.setEnableUserId(false);
        //wrapper.setAddress();
        //wrapper.setContactNumber();


        return wrapper;
    }

    public UserWrapper getTaskUsersList(Long taskId){
        Tasks tTable = new Tasks();

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
            System.out.println("\n ---- Task exist for User Id : " + userTable.getId());
            System.out.println(" ---- Task exist for User Size : " + userTable.getTaskList().size() + "\n");
            if(userTable.getTaskList().contains(tTable)){
                System.out.println("\n ---- Assigned Task Id: " + tTable.getId());
                System.out.println("\n ---- Assigned User Id: " + userTable.getId());
                wrapper.setEnableUserAssignId(true);
                userList.add(wrapper);
            }else{
                userList.remove(wrapper);
            }


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

    public ResultImpl getTaskUsersListAll(Long taskId){
        Tasks tTable = new Tasks();

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
            if(userTable.getTaskList().contains(tTable)){
                System.out.println("\n ---- User exist for Task: " + tTable.getId());
                System.out.println("---- Task exist for User: " + userTable.getId()+"\n");
                wrapper.setEnableUserAssignId(true);
            }
            userList.add(wrapper);
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




}
