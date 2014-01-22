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
import web.entity.Boards;
import web.entity.Companies;
import web.entity.Users;
import web.service.BoardsService;
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
public class UserController {
    @Autowired
    private UsersService userService;
    @Autowired
    private BoardsService boardService;
    @Autowired
    private CompaniesService companyService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Result result;
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(value="error",required=false) boolean error,
                                                ModelMap model) {
        System.out.println("Received request to show login page");
        String message =  null;
        if (error == true) {
        message = messages.getMessage("message.invalidCreds",  new Object [] {"UserName"}, "Invalid credentials", null);
        model.put("invalidCreds", message);
        } else {
        model.put("invalidCreds", "");
        }
        return "/login/login";
    }
    
//    @RequestMapping(value = "/create", method = RequestMethod.GET )
    @RequestMapping(value = "/users/create" )
    public String createUser(ModelMap model){
//        Users user = new Users();
//        user.setEmail("admin@admin.com".toLowerCase());
//        user.setName("Admin");
//        user.setPassword("admin");
//        userService.saveOrUpdate(user);  //or its changed to .save(user)
//        System.out.println("created--------------------");
        return "/users/create";
    }

    @RequestMapping(value = "/users/edit" )
    public String editUser(ModelMap model){
        return "/users/edit";
    }
    
    @RequestMapping(value = "/users/home" )
    public String userHome(ModelMap model, HttpServletRequest request) {
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername(); //get logged in username
        result = userService.getUserByLoginId(loginId); /////////////   to be edited
        Users user = (Users)result.getObject();
        HttpSession session = request.getSession(true);
        session.setAttribute("userName", user.getName());
        result = boardService.getBoardListByUser(user);
        List<Boards> boardList = new ArrayList<Boards>((List)result.getObject());
        model.put("boards",boardList);
        System.out.println("boardList size: "+boardList.size());
        for(Boards b: boardList){
         System.out.println("The board id fetched was "+b.getId());
        }
//        model.addAttribute("username", user.getName());
        System.out.println("user-home shown--------------------");
        return "/users/home";       
    }
    
    @RequestMapping(value = "/logoutfail" )
    public String logoutFail(ModelMap model){
        
        System.out.println("logout user fail page shown--------------------");
        return "/login/logout-fail";       
    }

    @RequestMapping("/loginfail")
    public String loginFail(ModelMap model){
        return "/login/login-fail";       
    }
    
    @RequestMapping("/users/createadmin")
    public String createAdmin(ModelMap model){
        List errorMessagesList = new ArrayList();
        List successMessagesList = new ArrayList();
        Companies company = new Companies();
        company.setName("Accu Reference");
        result = companyService.save(company);
        if(result.getIsSuccessful()){
            successMessagesList.add(result.getMessageList());     // to be edited / removed. adds double success messagge on view page.
            Users user = new Users();
            user.setEmail("admin@admin.com");
            user.setName("Admin");
            user.setPassword("admin");
            user.setCompany((Companies)result.getObject());   
            user.setIsEnabled(true);
            result = userService.save(user);
            if(result.getIsSuccessful()){
                successMessagesList.add(result.getMessageList());  // to be edited / removed. adds double success messagge on view page.
            }else{
                errorMessagesList.add(result.getMessageList());
            }
        }else{
            errorMessagesList.add(result.getMessageList());
        }
        
        model.put("successMessages", successMessagesList);
        model.put("errorMessages", errorMessagesList);
        
        // if not. add error to map and return
        
        return "/users/createadmin";       
    }

    //{user-module} this method can be overridden
    @RequestMapping(value = "/users/change-role/{userEmail}/{role}/{boardId}", method=RequestMethod.GET)
    public String changeUserRole(ModelMap model,
                                 @PathVariable(value="userEmail") String userEmail,
                                 @PathVariable(value="role") String role,
                                 @PathVariable(value="boardId") String boardId){
        System.out.println("==========================");

        result = userService.changeUserRoleForBoard(userEmail, role, Long.parseLong(boardId));
        System.out.println("=========end=================");

        return null;
    }

}



//    @RequestMapping(value = "/logout" )
//    public String logout(ModelMap model, HttpServletRequest request){
////        request.getSession(true).invalidate();
//        System.out.println("logout user page shown--------------------");
//        return "/login/logout";
//    }