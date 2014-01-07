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
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
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
        /////////////////// queue , check if named boar already exists or not?
        Boards boardToBeReturned = boardDAO.save(board);
        if(boardToBeReturned == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessageList(Arrays.asList("error.boardCreationErrorUnknown"/*,"string"*/));
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setObject(boardToBeReturned);
                result.setMessageList(Arrays.asList("success.boardCreated"/*,"string"*/));
                return result;
            }
        
       
    }
    
    @Transactional(readOnly = false)
    public ResultImpl getBoardById(Long id){
        Boards board = boardDAO.getBoardById(id);
        if (board != null){
            result.setIsSuccessful(true);
            result.setObject(board);
            //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.boardCreationErrorUnknown"/*,"string"*/));
        }
        return result;
        
    }
    
    @Transactional(readOnly = false)
    public ResultImpl getBoardByIdToDisplay(Long id){
        System.out.println("-----------------1");
        Boards board = boardDAO.getBoardById(id);
        System.out.println("-----------------2");
        if (board != null){
            System.out.println("-----------------3");
            Collection<Boxes> childBoxList = new ArrayList<Boxes>();
            childBoxList =  board.getChildBoxList();
            if(childBoxList.isEmpty() || childBoxList == null){
                System.out.println("child boxes are null---");
            }else{
                System.out.println("childbox list size: "+ childBoxList.size());
                crawlBoxes(childBoxList);
            }
            System.out.println("-----------------4");

            result.setIsSuccessful(true);
            result.setObject(board);
            return result;
            //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
        }else{
            System.out.println("-----------------5");
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.namedBoardDoesNotExist"/*,"string"*/));
            return result;
        }
        
        
        
    }

    @Transactional(readOnly = false)
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
    
    @Transactional(readOnly = false)
    public ResultImpl getBoardListByUser(Users user){
        Users  u =  userDAO.getUserByLoginId(user.getEmail());
        List<Boards> boardList = new ArrayList<Boards>(u.getBoardList());
        if(!boardList.isEmpty()){
            result.setIsSuccessful(true);
            result.setObject(boardList);
        }else{
            result.setIsSuccessful(false);
        }
        
        return result;
    }
    
    
}
