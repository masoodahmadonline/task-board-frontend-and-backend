package web.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.BoardsDAO;
import web.dao.UsersDAO;
import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Tasks;
import web.entity.Users;
import web.service.common.ResultImpl;
import web.service.common.ValidationUtility;
import web.utils.BoxComparator;
import web.utils.TaskComparator;
import web.wrapper.UserWrapper;

@Service
@Transactional(readOnly = true)
public class BoardsServiceImpl implements BoardsService{
    
    @Autowired
    private BoardsDAO boardDAO;
    @Autowired
    private UsersDAO userDAO;
    @Autowired
    private ResultImpl result;

    @Transactional(readOnly = false)
    public ResultImpl save(Boards board){
        Boards boardToBeReturned = boardDAO.save(board);
        if(boardToBeReturned == null){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.boardCreationErrorUnknown"/*,"second message (if any)"*/));
        }else{
            result.setIsSuccessful(true);
            result.setObject(boardToBeReturned);
            result.setMessageList(Arrays.asList("success.boardCreated"));
        }
        return result;
    }
    
    @Transactional
    public ResultImpl getBoardById(Long id){
        Boards board = boardDAO.getBoardById(id);
        if (board != null){
            result.setIsSuccessful(true);
            result.setObject(board);
            result.setMessageList(Arrays.asList("success.boardLoadedSuccessfully"));
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.boardRetrievalFailed","error.namedBoardDoesNotExist"));
        }
        return result;
    }
    
    @Transactional
    public ResultImpl getBoardByIdToDisplay(Long id){
        result = getBoardById(id);
        Boards board;
        if (result.getIsSuccessful()){
            board = (Boards)result.getObject();
            Collection<Boxes> childBoxList = board.getChildBoxList();
            if(childBoxList == null || childBoxList.isEmpty()){

               System.out.println("child boxes for getBoardByIdToDisplay() are null---");
            }else{
               System.out.println("childbox list size for getBoardByIdToDisplay(): "+ childBoxList.size());
               Collections.sort((List) childBoxList, new BoxComparator());
               crawlBoxes(childBoxList);
            }
        }
        return result;
    }

    @Transactional
    void crawlBoxes(Collection boxList){
        for( Object o : boxList ){
           Boxes box = (Boxes)o;    
           System.out.println("box crawled was: "+ box.getId());
           List<Tasks> taskList = (List<Tasks>) box.getTaskList();
           System.out.println( "The task List size is  ========= " +taskList.size() );
           Collections.sort(taskList, new TaskComparator());

           for(Object t : taskList){
               Tasks task = (Tasks)t;
               System.out.println( "The Attachment List size is  ========= " +task.getAttachmentList().size() );
               UserWrapper tempWrapper = new UserWrapper();
               tempWrapper = getTaskUsersList(task.getId());
               task.setUserSize("0");
               if(ValidationUtility.isExists(tempWrapper)){
                   if(tempWrapper.isTaskUserExistSingle() && tempWrapper.isTaskUserExistMultiple()){
                       task.setUserSize("2");
                   }else if(tempWrapper.isTaskUserExistSingle() && !tempWrapper.isTaskUserExistMultiple()){
                       task.setUserSize("1");
                   }else{
                       task.setUserSize("0");
                   }
               }
               System.out.println( "Task ID: " +task.getId());
               System.out.println( "User Size: " +task.getUserSize());
           }
           List<Boxes> childBoxList = new ArrayList<Boxes> ( box.getChildBoxList() ); // list of sub child boxes of this box
           Collections.sort((List)childBoxList, new BoxComparator());
           System.out.println(" ======================== boxid : "+box.getId()+" and its childBoxList size: "+ childBoxList.size());
           if(!childBoxList.isEmpty()){
               crawlBoxes(childBoxList);
           }

       }
    }
    
    @Transactional
    public ResultImpl getBoardListByUser(Users user){
        Users  u =  userDAO.getUserByLoginId(user.getEmail());
        //List<Boards> boardList = new ArrayList<Boards>(u.getBoardList());
        /*Since only one board, so no need to add all users to this board
        * Currently users are being created without taking board consideration (similar to LeanKit)*/
        Boards board = new Boards();
        List<Boards> boardList = userDAO.findAll(board);
        if(!boardList.isEmpty()){
            result.setIsSuccessful(true);
            result.setObject(boardList);
            result.setMessageList(Arrays.asList("success.boardLoadedSuccessfully"));
        }else{
            result.setIsSuccessful(false);
            result.setObject(boardList);//added this because exception occurs if user not assigned to board
            result.setMessageList(Arrays.asList("error.noBoardFoundForUser"));
        }
        return result;
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
    
    
}
