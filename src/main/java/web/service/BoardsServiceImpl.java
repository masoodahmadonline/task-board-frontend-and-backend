package web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
           System.out.println( "The task List size is  ========= " +box.getTaskList().size() );
           for(Object t : box.getTaskList()){
               Tasks task = (Tasks)t;
               System.out.println( "The Attachment List size is  ========= " +task.getAttachmentList().size() );;
           }
           List<Boxes> childBoxList = new ArrayList<Boxes> ( box.getChildBoxList() ); // list of sub child boxes of this box
           System.out.println(" ======================== boxid : "+box.getId()+" and its childBoxList size: "+ childBoxList.size());
           if(!childBoxList.isEmpty()){
               crawlBoxes(childBoxList);
           }

       }
    }
    
    @Transactional
    public ResultImpl getBoardListByUser(Users user){
        Users  u =  userDAO.getUserByLoginId(user.getEmail());
        List<Boards> boardList = new ArrayList<Boards>(u.getBoardList());
        if(!boardList.isEmpty()){
            result.setIsSuccessful(true);
            result.setObject(boardList);
            result.setMessageList(Arrays.asList("success.boardLoadedSuccessfully"));
        }else{
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.noBoardFoundForUser"));
        }
        return result;
    }
    
    
}
