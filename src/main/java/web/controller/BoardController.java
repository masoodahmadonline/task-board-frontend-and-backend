/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.entity.BoardPrivileges;
import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Tasks;
import web.entity.Companies;
import web.entity.Users;
import web.service.BoardsService;
import web.service.BoxesService;
import web.service.CompaniesService;
import web.service.UsersService;
import web.service.common.Result;
import web.service.common.ResultImpl;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

/**
 *
 * @author syncsys
 */

@Controller
public class BoardController {
    @Autowired
    private BoardsService boardService;
    @Autowired
    private BoxesService boxService;
    @Autowired
    private UsersService userService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Result result;
    
    

    @RequestMapping (value = "/boards/create")
    public String createBoard(ModelMap model){
        ///////// queue, if user is admin or has privs to creat a board
        result = userService.getUserByLoginId("admin@admin.com");
        Users user = (Users)result.getObject();

        
        Boards board = new Boards();
        board.setTitle("Accu Refference - Main Board");
        List<Users> userList = new ArrayList<Users>();
        userList.add(user);
        board.setUserList(userList);
        
        result = boardService.save(board);
        if(result.getIsSuccessful()){
            model.put("successMessages", result.getMessageList());
        }else{
            model.put("errorMessages", result.getMessageList());
        }
        
        
        return "/boards/create";
    }
    
    
   @RequestMapping (value = "/boards/{id}")
   public String viewBoard(ModelMap model, HttpServletRequest request,HttpSession session, @PathVariable(value="id") String id){


        String[] uriArray = request.getRequestURI().toString().split("/");
        int uriArraySize = uriArray.length;
        session.setAttribute("previous_page", "/"+uriArray[uriArraySize-2]+"/"+uriArray[uriArraySize-1]);

////       String requestUrl="/"+uri[1]+"/"+uri[2] ;
//       String stripped = request.getRequestURI().toString().substring(1);
//       String sub = stripped.substring(stripped.indexOf('/'));
//       if(uri.split("/").length == 2){
//           session.setAttribute("previous_page",uri);
//       }else{
//           session.setAttribute("previous_page",sub);
//       }

        ///////// queue, if user is admin or has privs to creat a board
      System.out.println("debug --- 1");
       result =  boardService.getBoardByIdToDisplay(Long.parseLong(id));
       System.out.println("debug --- 2");

       System.out.println("debug --- 3");
       if(result.getIsSuccessful()){
           System.out.println("debug --- 4");
            model.put("successMessages", result.getMessageList());
            model.put("board", result.getObject());
            //
            Boards tempBoard = (Boards)result.getObject();
            List<Boxes> boxList = new ArrayList<Boxes>( tempBoard.getChildBoxList() );
            for (Boxes box: boxList){
                System.out.println(box.getId());
                for(Tasks task : box.getTaskList()){
                    System.out.println("task id: ========"+ task.getId());
                    System.out.println("task title: ====="+ task.getTitle());
                }
            }
            //

            
            System.out.println("debug --- 5");
       }else{
           System.out.println("debug --- 6");
            model.put("errorMessages", result.getMessageList());
            System.out.println("debug --- 7");
       }  
       System.out.println("debug --- 8");
//       Boxes box = (Boxes)( (boxService.getBoxById(287L)).getObject() );
//       Boxes box2 = (Boxes)( (boxService.getBoxById(209L)).getObject() );


       return "/boards/board";
    }
   
   
    
   
    
   
}
