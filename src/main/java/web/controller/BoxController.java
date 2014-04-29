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
import org.springframework.security.access.prepost.PreAuthorize;
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
import web.wrapper.BoardWrapper;
import web.wrapper.UserWrapper;

import java.util.Date;
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
    private UsersService userService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Result result;
    
    
    //ajax
    @PreAuthorize("@securityService.hasBoxCreatePermission(#parent, #parentId)")
    @RequestMapping (value = "/box/create/{boardLogId}/{parent}/{parentId}/{boxType}/{boxTitle}/{boxDescription}", method=RequestMethod.GET)
    public @ResponseBody  Boxes createBox(ModelMap model,
                                          @PathVariable(value="boardLogId") String boardLogId,
                                          @PathVariable(value="parent") String parent,
                                          @PathVariable(value="parentId") String parentId,
                                          @PathVariable(value="boxType") String boxType,
                                          @PathVariable(value="boxTitle") String boxTitle,
                                          @PathVariable(value="boxDescription") String boxDescription
                                          ){
        //queue {user-module}
        //get user id from session (save id in session first)
        //verify for privs of user if he can create box or not.
        
        //create box in the parent board / box
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        result = userService.getUserByLoginId(loginId);
        Users user = (Users)result.getObject();
        Boxes boxToBeReturned = null;
        Boxes box = new Boxes();
        box.setTitle(boxTitle);
        box.setType(boxType);
        box.setDescription(boxDescription);
        box.setCreatedBy(user.getId());
        box.setCreatedDate(new Date());
        if(parent.equals("board")){
            box.setIsFirstLevelBox(true);
            Boards board = (Boards)( boardService.getBoardById(Long.valueOf(parentId)) ).getObject();
             boxService.setParent(box, board);

        }else if(parent.equals("box")){
            Boxes parentBox = (Boxes)( boxService.getBoxById(Long.valueOf(parentId)) ).getObject();
            boxService.setParent(box, parentBox);
        }
        result = boxService.save(box);
        if(result.getIsSuccessful()){
            model.put("successMessages", result.getMessageList());
            Boxes savedBox = (Boxes)result.getObject();
            System.out.println("box saved title was "+ savedBox.getTitle());
            //reusing savedBox in boxToBeReturned because of an issue.
            //unable to do that directly to savedBox
            boxToBeReturned = new Boxes();
            boxToBeReturned.setType(savedBox.getType());
            boxToBeReturned.setTitle(savedBox.getTitle());
            boxToBeReturned.setId(savedBox.getId());
            boxToBeReturned.setDescription(savedBox.getDescription());
        }else{
            model.put("errorMessages", result.getMessageList());
        }
        /*BoardWrapper bWrapper = new BoardWrapper();
        bWrapper.setBoardId(boardLogId);
        bWrapper = boardService.getUpdatedBoardWrapper(bWrapper);
        model.put("boardWrapper", bWrapper);*/
        return boxToBeReturned; //queued - send model message also (if needed)
    }

    //ajax
    @PreAuthorize("@securityService.hasBoxTaskEditPermission(#boxId)")
    @RequestMapping (value = "/box/edit/{boardLogId}/{boxId}/{boxType}/{boxTitle}/{boxDescription}", method=RequestMethod.GET)
    public @ResponseBody  boolean editBox(ModelMap model,
                                          @PathVariable(value="boardLogId") String boardLogId,
                                          @PathVariable(value="boxId") String boxId,
                                          @PathVariable(value="boxType") String boxType,
                                          @PathVariable(value="boxTitle") String boxTitle,
                                          @PathVariable(value="boxDescription") String boxDescription
    ){
        //queue {user-module}
        //get user id from session (save id in session first)
        //verify for privs of user if he can edit box or not.
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        result = userService.getUserByLoginId(loginId);
        Users user = (Users)result.getObject();
        UserWrapper wrapper = new UserWrapper();
        wrapper.setUpdatedBy("" + user.getId());
        result = boxService.editBox(Long.valueOf(boxId), boxType, boxTitle, boxDescription, wrapper);
        /*BoardWrapper bWrapper = new BoardWrapper();
        bWrapper.setBoardId(boardLogId);
        bWrapper = boardService.getUpdatedBoardWrapper(bWrapper);
        model.put("boardWrapper", bWrapper);*/
        if(result.getIsSuccessful()){
            return true;
        }else{
            return false;
        }

    }

    //ajax
    @PreAuthorize("@securityService.hasBoxTaskEditPermission(#boxId)")
    @RequestMapping (value = "/box/delete/{boardLogId}/{boxId}", method=RequestMethod.GET)
    public @ResponseBody  String deleteBox(ModelMap model,
                                          @PathVariable(value="boardLogId") String boardLogId,
                                          @PathVariable(value="boxId") String boxId
                                          ){
        System.out.println("box delete controller method called.");
        result = boxService.deleteBox(Long.parseLong(boxId));
        /*BoardWrapper bWrapper = new BoardWrapper();
        bWrapper.setBoardId(boardLogId);
        bWrapper = boardService.getUpdatedBoardWrapper(bWrapper);
        model.put("boardWrapper", bWrapper);*/
        if(result.getIsSuccessful()){
            model.put("successMessages", result.getMessageList());
            System.out.println("box deleted ------------------");
            return "success";  //queued - send model message also (if needed)
        }else{
            model.put("errorMessages", result.getMessageList());
            System.out.println("box deletion failed ------------------");
            return "failure";  //queued - send model message also (if needed)
        }
    }

}
