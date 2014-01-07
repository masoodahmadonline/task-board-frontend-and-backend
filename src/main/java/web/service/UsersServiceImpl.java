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
import web.dao.UsersDAO;
import web.entity.UserRoleForBoard;
import web.entity.Users;
import web.entity.Boards;
import web.service.common.ResultImpl;

@Service
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService{
    
    @Autowired
    private UsersDAO userDAO;
    @Autowired
    private BoardsDAO boardDAO;
    @Autowired
    private ResultImpl result;
    
   
    
    @Transactional(readOnly = false)
    public ResultImpl save(Users user){
        if( userDAO.doesLoginIdExists(user.getEmail()) ){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.emailAlreadyExists"/*,"string"*/));
           // setResult(false,null,Arrays.asList("error.emailAlreadyExists"/*,"string"*/) );
            return result;
        }else{
            Users userToBeReturned = userDAO.save(user);
            if(userToBeReturned == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
                return result;
            }else{
                result.setIsSuccessful(true);
                result.setObject(userToBeReturned);
                result.setMessageList(Arrays.asList("success.userCreated"/*,"string"*/));
                return result;
            }
           
            

//             
//         return new ResultImpl(true,userToBeReturned,Arrays.asList("success.userCreated"/*,"string"*/));
        }
       
    }
    
    @Transactional(readOnly = false)
    public ResultImpl getUserByLoginId(String email){
        Users user = userDAO.getUserByLoginId(email);
        if (user != null){
            result.setIsSuccessful(true);
            result.setObject(user);
            //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.namedUserDoesNotExist"/*,"string"*/));
        }
        return result;
        
    }

    @Transactional(readOnly = false)
    public ResultImpl changeUserRoleForBoard(String userEmail, String role, long boardId){
                   System.out.println("================1=============");
        Users user = userDAO.getUserByLoginId(userEmail);
        if (user != null){
            System.out.println("================2=============");
            Boards board = boardDAO.getBoardById(boardId);
            if(board != null){
                System.out.println("================3=============");
                List<UserRoleForBoard> urfbList =
                        new ArrayList<UserRoleForBoard>( user.getUserRoleForBoardList() );
                System.out.println("================4=============");
                    UserRoleForBoard urfb = new UserRoleForBoard();
                System.out.println("================5=============");
                    urfb.setBoard(board);
                    urfb.setUser(user);
                    urfb.setRole(role);
                    urfbList.add(urfb);
                    user.setUserRoleForBoardList(urfbList);
                System.out.println("================6=============");
                    System.out.println(urfb.getBoard().getTitle()+urfb.getRole()+urfb.getUser().getName()+"=====================");
                    result.setIsSuccessful(true);

            }
        }
        return result;
    }
    
 
}
