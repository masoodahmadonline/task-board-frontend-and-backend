/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.annotate.JsonIgnore;
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
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.Boards;
import web.entity.Boxes;
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
public class BoxController {
    @Autowired
    private BoxesService boxService;
    @Autowired
    private BoardsService boardService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Result result;
    
    
    //ajax
    @RequestMapping (value = "/box/create/{parent}/{parentId}/{boxType}/{boxTitle}/{boxDescription}", method=RequestMethod.GET)
    public @ResponseBody  Boxes createBox(ModelMap model,
                                          @PathVariable(value="parent") String parent,
                                          @PathVariable(value="parentId") String parentId,
                                          @PathVariable(value="boxType") String boxType,
                                          @PathVariable(value="boxTitle") String boxTitle,
                                          @PathVariable(value="boxDescription") String boxDescription
                                          ){
        
        //queue
        //get user id from session (save id in session first)
        //verify for privs of user if he can create box or not.
        
        //create box in the parent board / box
        Boxes box = new Boxes();
        box.setTitle(boxTitle);
        box.setType(boxType);
        box.setDescription(boxDescription);
        if(parent.equals("board")){
            box.setIsFirstLevelBox(true);
            //do this in service rather here. / that is set parent of box.
            Boards board = (Boards)( boardService.getBoardById(Long.valueOf(parentId)) ).getObject();
            boxService.setParent(box, board);
        }else if(parent.equals("box")){
            Boxes parentBox = (Boxes)( boxService.getBoxById(Long.valueOf(parentId)) ).getObject();
            boxService.setParent(box, parentBox);
        }
        
        Boxes savedBox = (Boxes)boxService.save(box).getObject();

        System.out.println("box saved title was "+ savedBox.getTitle());
        Boxes boxToBeReturned = new Boxes();
        boxToBeReturned.setType(savedBox.getType());
        boxToBeReturned.setTitle(savedBox.getTitle());
        boxToBeReturned.setId(savedBox.getId());
        boxToBeReturned.setDescription(savedBox.getDescription());
        
        
        
        return boxToBeReturned;
    }
    
    //ajax
    @RequestMapping (value = "/box/delete/{boxId}", method=RequestMethod.GET)
    public @ResponseBody  String deleteBox(ModelMap model,
                                          @PathVariable(value="boxId") String boxId
                                          ){
        System.out.println("box delete controller method called.");
        result = boxService.deleteBox(Long.parseLong(boxId));

        System.out.println("succes was: "+ result.getIsSuccessful()   );
        if (result.getIsSuccessful()) {
            System.out.println("box deleted ------------------");
        }else{
            System.out.println("result was false");
        }



        return "success";
    }
    
    
    
   
    
   
}
