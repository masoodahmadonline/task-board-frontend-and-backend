/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.Boards;
import web.entity.Companies;
import web.entity.Users;
import web.service.BoardsService;
import web.service.CompaniesService;
import web.service.UsersService;
import web.service.common.Result;
import web.service.common.ResultImpl;
import web.wrapper.UserWrapper;
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
    
//    @RequestMapping(value = "/users/create", method = RequestMethod.GET)
public String createUser(ModelMap model){
    return "/users/create";
}

    //    @RequestMapping(value = "/create", method = RequestMethod.GET )
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String createUser(@RequestParam(value="email", required=false) String emailVar,
                             @RequestParam(value="firstName", required=false) String firstNameVar,
                             @RequestParam(value="lastName", required=false) String lastNameVar,
                             @RequestParam(value="password1", required=false) String password1Var,
                             @RequestParam(value="password2", required=false) String password2Var,
                             @RequestParam(value="enable", required=false) String enableVar,
                             ModelMap model){

        System.out.println("\n\n********** POST User Created Info Start ***************");
        System.out.println("Email:      " + emailVar);
        System.out.println("First Name: " + firstNameVar);
        System.out.println("Last Name:  " + lastNameVar);
        System.out.println("Password 1: " + password1Var);
        System.out.println("Password 2: " + password2Var);
        System.out.println("Enable:     " + enableVar);
        System.out.println("********** POST User Created Info End ***************\n\n");

        UserWrapper wrapper = new UserWrapper();
        wrapper.setEmail(emailVar);
        wrapper.setFirstName(firstNameVar);
        wrapper.setLastName(lastNameVar);
        wrapper.setPassword1(password1Var);
        wrapper.setPassword2(password2Var);
        wrapper.setEnableUser(enableVar);

        result = userService.saveUser(wrapper);

        List errorMessagesList = new ArrayList();
        List successMessagesList = new ArrayList();
        if(result.getIsSuccessful()){
            successMessagesList.add(result.getMessageList());
            model.put("error", false);
            model.put("success", true);
            model.put("successMsg", result.getMessage());
            System.out.println("\n********** Success message from controller ***************\n");
        }else{
            errorMessagesList.add(result.getMessageList());
            model.put("error", true);
            model.put("success", false);
            model.put("errorMsg", result.getMessage());
            System.out.println("\n********** error message from controller ***************\n");
        }

        model.put("successMessages", successMessagesList);
        model.put("errorMessages", errorMessagesList);

        return "/users/create";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public String editUser(ModelMap model){
        System.out.println("\n Edit User GET method \n");
        UserWrapper wrapper = new UserWrapper();
        List<UserWrapper> usersList = null;
        List<UserWrapper> roleList = null;

        usersList = userService.listUsersWithDetail();
        roleList = userService.populateRoleList();

        Map<String,String> roleMap = new LinkedHashMap<String, String>();
        if (roleList != null && roleList.size() > 0) {
            for (UserWrapper uWrapper : roleList) {
                roleMap.put(uWrapper.getRoleId(), uWrapper.getRoleName());
            }
        }

        //wrapper.setRoleList(roleList);
        wrapper.setUserList(usersList);
        model.put("roleList", roleMap);
        model.put("checkEnableUser", wrapper.getEnableUserId());
        model.put("editUserWrapper", wrapper);

        return "/users/edit";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("editUserWrapper")UserWrapper userWrapper, ModelMap model){
        System.out.println("\n Edit User POST method \n");

        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername(); //get logged in username (email)
        result = userService.getUserByLoginId(loginId);
        Users user = (Users)result.getObject();

        result = userService.editUserAccess(userWrapper);
        System.out.println("\n size of user's list: "+userWrapper.getUserList().size()+"\n");

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