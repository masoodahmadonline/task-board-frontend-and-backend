/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.Boards;
import web.entity.Companies;
import web.entity.Users;
import web.service.BoardsService;
import web.service.CompaniesService;
import web.service.UsersService;
import web.service.common.CustomUserDetailsService;
import web.service.common.Result;
import web.service.common.ResultImpl;
import web.service.common.ValidationUtility;
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
    @Autowired
    private ProviderManager authenticationManager;
    @Autowired
    private ServletContext servletContext;


    private UserWrapper tempUserListWrapper = new UserWrapper();
    Map<String,String> roleMap = new LinkedHashMap<String, String>();
    Map<String,String> companyMap = new LinkedHashMap<String, String>();
    private String message = "";

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(ModelMap model) {
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        result = userService.getUserByLoginId(loginId);
        Users user = (Users)result.getObject();
        model.addAttribute("message", "Sorry "+user.getFirstName()+"! You don't have privileges to view this page!!!");
        return "access-denied";
    }

    @PreAuthorize("permitAll")
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

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE')")
    @RequestMapping(value = "/users/create", method = RequestMethod.GET)
    public String createUser(ModelMap model){
        System.out.println("\n Create User GET method \n");
        UserWrapper wrapper = new UserWrapper();
        this.companyMap = populateCompanyCombo();
        model.put("companyList", companyMap);
        model.put("createUserWrapper", wrapper);
        return "/users/create";
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE')")
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String createUser(HttpSession session, @ModelAttribute("createUserWrapper")UserWrapper userWrapper, ModelMap model){

        System.out.println("\n\n********** POST User Created Info Start ***************");

        model.put("error", true);
        model.put("success", false);
        this.companyMap = populateCompanyCombo();
        model.put("companyList", this.companyMap);
        if(!ValidationUtility.isExists(userWrapper.getEmail())){
            model.put("errorMsg", "Please enter your Login ID (Email)");
        }else if(!ValidationUtility.isValidEmail(userWrapper.getEmail())){
            model.put("errorMsg", "Please enter Valid Email");
        }else if(!ValidationUtility.isExists(userWrapper.getFirstName())){
            model.put("errorMsg", "Please enter your First Name");
        }else if(!ValidationUtility.isExists(userWrapper.getLastName())){
            model.put("errorMsg", "Please enter your Last Name");
        }else if(!ValidationUtility.isExists(userWrapper.getPassword1())){
            model.put("errorMsg", "Please enter both Passwords");
        }else if(!ValidationUtility.isExists(userWrapper.getPassword2())){
            model.put("errorMsg", "Please enter both Passwords");
        }else if(!userWrapper.getPassword1().equals(userWrapper.getPassword2())){
            model.put("errorMsg", "Passwords don't match");
        }else if(!ValidationUtility.isExists(userWrapper.getCompanyId())){
            model.put("errorMsg", "Please select your company");
        }else {
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername();
            loginId = userService.getUserId(loginId);
            userWrapper.setCreatedBy(loginId);
            userWrapper.setUpdatedBy(loginId);
            userWrapper.setUserAccessFlag(true);
            result = userService.saveUser(userWrapper);
            if(result.getIsSuccessful()){
                model.put("error", false);
                model.put("success", true);
                model.put("successMsg", result.getMessage());
                System.out.println("\n********** Success message from controller ***************\n");
            }else{
                model.put("errorMsg", result.getMessage());
                System.out.println("\n********** error message from controller ***************\n");
            }
        }

        return "/users/create";
    }

    @PreAuthorize("@securityService.hasUserAccessPermission(#id)")
    @RequestMapping(value = "/boards/{id}/edit-user-access", method = RequestMethod.GET)
    public String editBoardUser(HttpServletRequest req, HttpSession session, @PathVariable(value="id") String id, ModelMap model){
        System.out.println("\n Edit User GET method \n");
        UserWrapper wrapper = new UserWrapper();
        List<UserWrapper> usersList = null;

        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String companyId = null;
        companyId = userService.getCompanyId(loginId);

        this.setTempUserListWrapper(null);
        usersList = userService.listUsersWithDetail(companyId, id);
        wrapper.setUserList(usersList);
        model.put("editUserWrapper", wrapper);
        model.put("boardId", id);

        String successParam = req.getParameter("success");
        System.out.println("Parameter Value: " + successParam);
        if(ValidationUtility.isExists(successParam)){
            getPageMessages(successParam, model);
        }

        return "/boards/edit-user-access";
    }

    @PreAuthorize("@securityService.hasUserAccessPermission(#id)")
    @RequestMapping(value = "/boards/{id}/edit-user-access", method = RequestMethod.POST)
    public String editBoardUser(HttpSession session, @PathVariable(value="id") String id , @ModelAttribute("editUserWrapper")UserWrapper userWrapper, ModelMap model){
        System.out.println("\n Edit User POST method \n");
        model.put("boardId", id);
        String returnString = "redirect:/boards/"+id+"/edit-user-access";
        this.setMessage("");
        this.setTempUserListWrapper(null);
        if (userWrapper.getUserList() != null && userWrapper.getUserList().size() > 0) {
            for (UserWrapper uWrapper : userWrapper.getUserList()) {
                if(uWrapper.isEnableUserEditId()){
                    System.out.println("\n UserID: "+uWrapper.getUserId()+" Name:" + uWrapper.getFirstName());
                    this.setTempUserListWrapper(userWrapper);
                    editUserProfileTechnical(session, id, model);
                    returnString = "/boards/edit-user-profile";
                }else{
                    this.setPageMessages("Please select user before edit user's access", true, model);
                }
            }
        }else{
            this.setPageMessages("Please select user before edit user's access", true, model);
        }

        //return "redirect:"+session.getAttribute("previous_page").toString();
        return returnString;
    }

    @PreAuthorize("@securityService.hasUserAccessPermission(#id)")
    @RequestMapping(value = "/boards/{id}/delete-user")
    public String deleteUserAccess(HttpSession session, @PathVariable(value="id") String id, @RequestParam(required=false) String uId, ModelMap model){
        System.out.println("\n Delete User POST method \n");
        String returnString = "redirect:/boards/"+id+"/edit-user-access";
        this.setMessage("");
        if (ValidationUtility.isExists(uId)) {
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername();
            loginId = userService.getUserId(loginId);
            if(uId.equalsIgnoreCase(loginId)){
                this.setPageMessages("You are not allowed to disable yourself", true, model);
            }else{
                UserWrapper wrapper = new UserWrapper();
                wrapper.setUserId(uId);
                wrapper.setCreatedBy(loginId);
                wrapper.setUpdatedBy(loginId);
                result = userService.deleteUserAccess(wrapper);
                setPageMessages(result, model);
            }
        }else{
            this.setPageMessages("Please select user before deletion", true, model);
        }

        //return "redirect:"+session.getAttribute("previous_page").toString();
        return returnString;
    }

    @PreAuthorize("@securityService.hasUserAccessPermission(#id)")
    @RequestMapping(value = "/boards/{id}/edit-user-profile", method = RequestMethod.GET)
    public String editUserProfileTechnical(HttpSession session, @PathVariable(value="id") String id, ModelMap model){
        System.out.println("\n Edit User Technical method \n");
        model.put("boardId", id);
        String returnPage = "/boards/"+id+"/edit-user-profile";

        model = populateUserTechnicalInfo(model);
        if(model.isEmpty()){
            model.put("errorMsg", "Please select user before edit user's technical information");
            returnPage = "redirect:/boards/"+id+"/edit-user-access";
        }

        return returnPage;
    }

    @PreAuthorize("@securityService.hasUserAccessPermission(#id)")
    @RequestMapping(value = "/boards/{id}/edit-user-profile", method = RequestMethod.POST)
    public String editUserProfileTechnical(HttpServletRequest req, HttpSession session, @PathVariable(value="id") String id, @ModelAttribute("editUserTechnicalWrapper")UserWrapper userWrapper, ModelMap model){
        System.out.println("\n Edit User Technical POST method \n");
        String returnPage = "redirect:/boards/"+id+"/edit-user-access";
        this.setMessage("");
        if(ValidationUtility.isExists(this.tempUserListWrapper)){
            userWrapper.setUserList(this.tempUserListWrapper.getUserList());
            userWrapper.setBoardId(id);
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername();
            loginId = userService.getUserId(loginId);
            userWrapper.setCreatedBy(loginId);
            userWrapper.setUpdatedBy(loginId);
            result = userService.editUserAccess(userWrapper);
            //System.out.println("\n size of user's list: "+userWrapper.getUserList().size()+"\n");
            setPageMessages(result, model);
            this.roleMap = populateUserRoleCombo();
            model.put("roleList", roleMap);
            doAutoLogin(req);

        }else{
            model.put("errorMsg", "User Profile not updated. Please select user first");
            //returnPage = "/users/profile-edit-personal";
            returnPage = "redirect:/boards/"+id+"/edit-user-access";
        }

        return returnPage;// If we redirect, whole page populated and error/success message not displayed
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE')")
    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public String editUser(HttpServletRequest req, HttpSession session, ModelMap model){
        System.out.println("\n Edit User GET method \n");
        UserWrapper wrapper = new UserWrapper();
        List<UserWrapper> usersList = null;
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String companyId = null;
        companyId = userService.getCompanyId(loginId);


        this.setTempUserListWrapper(null);
        usersList = userService.listUsersWithDetail(companyId);
        wrapper.setUserList(usersList);
        model.put("editUserWrapper", wrapper);

        String successParam = req.getParameter("success");
        System.out.println("Parameter Value: " + successParam);
        if(ValidationUtility.isExists(successParam)){
            getPageMessages(successParam, model);
        }

        return "/users/edit";
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE')")
    @RequestMapping(value = "/users/delete-user")
    public String deleteUser(HttpSession session, @RequestParam(required=false) String uId, ModelMap model){
        System.out.println("\n Delete User POST method \n");
        String returnString = "redirect:/users/edit";
        this.setMessage("");
        if (ValidationUtility.isExists(uId)) {
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername();
            loginId = userService.getUserId(loginId);
            if(uId.equalsIgnoreCase(loginId)){
                this.setPageMessages("You are not allowed to remove yourself", true, model);
            }else{
                UserWrapper wrapper = new UserWrapper();
                wrapper.setUserId(uId);
                wrapper.setCreatedBy(loginId);
                wrapper.setUpdatedBy(loginId);
                result = userService.deleteUser(wrapper);
                setPageMessages(result, model);
            }
        }else{
            this.setPageMessages("Please select user before deletion", true, model);
        }

        //return "redirect:"+session.getAttribute("previous_page").toString();
        return returnString;
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE', 'ACCOUNT_USER_ROLE')")
    @RequestMapping(value = "/users/profile-edit-personal", method = RequestMethod.GET)
    public String editUserProfile(HttpSession session, @RequestParam(required=false) String uId, ModelMap model){
        System.out.println("\n Edit User Info method \n");
        String returnPage = "/users/profile-edit-personal-access";
        if(!ValidationUtility.isExists(uId)){//If user edit his own information (i.e. from home page)
            System.out.println("\n Edit Personal Profile: RequestParam not exist. get current user's login id \n");
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername();
            uId = userService.getUserId(loginId);
            returnPage = "/users/profile-edit-personal";
        }
        String imageName = "";
        UserWrapper wrapper = new UserWrapper();


        if(ValidationUtility.isExists(uId)){
            result = userService.populateUserInfo(uId);
            imageName = userService.populatePersonImage(uId, servletContext.getRealPath(File.separator));
            wrapper = (UserWrapper) result.getObject();
            wrapper.setImageName(imageName);
            model.put("editUserProfileWrapper", wrapper);
            model.put("imageName", imageName);
        }else{
            model.put("errorMsg", "Please select user before edit user's profile information");
            returnPage = "/users/edit";
        }


        return returnPage;
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE', 'ACCOUNT_USER_ROLE')")
    @RequestMapping(value = "/users/profile-edit-personal", method = RequestMethod.POST)
    public String editUserProfile(HttpSession session,
                           @ModelAttribute("editUserProfileWrapper")UserWrapper userWrapper, ModelMap model){
        System.out.println("\n Edit User Info POST method \n");
        String returnPage = "/users/profile-edit-personal";
        model.put("error", true);
        model.put("success", false);
        String imageName = "";
        UserWrapper wrapper = new UserWrapper();

        if(ValidationUtility.isExists(userWrapper.getUserId())){
            if(!ValidationUtility.isExists(userWrapper.getEmail())){
                model.put("errorMsg", "Please enter your Login ID (Email)");
            }else if(!ValidationUtility.isValidEmail(userWrapper.getEmail())){
                model.put("errorMsg", "Please enter Valid Email");
            }else if(!ValidationUtility.isExists(userWrapper.getFirstName())){
                model.put("errorMsg", "Please enter your First Name");
            }else if(!ValidationUtility.isExists(userWrapper.getLastName())){
                model.put("errorMsg", "Please enter your Last Name");
            }else{
                User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String loginId = springUser.getUsername();
                loginId = userService.getUserId(loginId);
                userWrapper.setCreatedBy(loginId);
                userWrapper.setUpdatedBy(loginId);
                userWrapper.setUserAccessFlag(false);
                result = userService.updateUserInfo(userWrapper);
                if(result.getIsSuccessful()){
                    model.put("error", false);
                    model.put("success", true);
                    model.put("successMsg", result.getMessage());
                    imageName = userService.populatePersonImage(userWrapper.getUserId(), servletContext.getRealPath(File.separator));
                    wrapper = (UserWrapper) result.getObject();
                    wrapper.setImageName(imageName);
                    model.put("editUserProfileWrapper", wrapper);
                    model.put("imageName", imageName);

                }else{
                    model.put("errorMsg", result.getMessage());
                }
            }
        }else{
            model.put("errorMsg", "User Profile not updated. Please select user first");
            //returnPage = "/users/profile-edit-personal";
            returnPage = "redirect:/users/home";
        }

        return returnPage;// If we redirect, whole page populated and error/success message not displayed
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE')")
    @RequestMapping(value = "/users/profile-edit-personal-access", method = RequestMethod.POST)
    public String editUserProfileAccess(HttpSession session,
                                  @ModelAttribute("editUserProfileWrapper")UserWrapper userWrapper, ModelMap model){
        System.out.println("\n Edit User Info Access POST method \n");
        String returnPage = "/users/profile-edit-personal-access";
        model.put("error", true);
        model.put("success", false);
        String imageName = "";
        UserWrapper wrapper = new UserWrapper();

        if(ValidationUtility.isExists(userWrapper.getUserId())){
            if(!ValidationUtility.isExists(userWrapper.getEmail())){
                model.put("errorMsg", "Please enter your Login ID (Email)");
            }else if(!ValidationUtility.isValidEmail(userWrapper.getEmail())){
                model.put("errorMsg", "Please enter Valid Email");
            }else if(!ValidationUtility.isExists(userWrapper.getFirstName())){
                model.put("errorMsg", "Please enter your First Name");
            }else if(!ValidationUtility.isExists(userWrapper.getLastName())){
                model.put("errorMsg", "Please enter your Last Name");
            }else{
                User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String loginId = springUser.getUsername();
                loginId = userService.getUserId(loginId);
                userWrapper.setCreatedBy(loginId);
                userWrapper.setUpdatedBy(loginId);
                userWrapper.setUserAccessFlag(true);
                result = userService.updateUserInfo(userWrapper);
                if(result.getIsSuccessful()){
                    model.put("error", false);
                    model.put("success", true);
                    model.put("successMsg", result.getMessage());
                    imageName = userService.populatePersonImage(userWrapper.getUserId(), servletContext.getRealPath(File.separator));
                    wrapper = (UserWrapper) result.getObject();
                    wrapper.setImageName(imageName);
                    model.put("editUserProfileWrapper", wrapper);
                    model.put("imageName", imageName);

                }else{
                    model.put("errorMsg", result.getMessage());
                }
            }
        }else{
            model.put("errorMsg", "User Profile not updated. Please select user first");
            //returnPage = "/users/profile-edit-personal";
            returnPage = "redirect:/users/edit";
        }

        return returnPage;// If we redirect, whole page populated and error/success message not displayed
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE', 'ACCOUNT_USER_ROLE')")
    @RequestMapping(value = "/users/change-password", method = RequestMethod.GET)
    public String changeUserPassword(HttpSession session, @RequestParam(required=false) String uId, ModelMap model){
        System.out.println("\n Change Password Get method \n");
        if(!ValidationUtility.isExists(uId)){
            System.out.println("\n Change Password: RequestParam not exist. get current user's login id \n");
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername(); //get logged in username
            uId = userService.getUserId(loginId);
        }

        String returnPage = "/users/change-password";
        if(ValidationUtility.isExists(uId)){
            UserWrapper wrapper = new UserWrapper();
            wrapper.setUserId(uId);
            model.put("changePasswordWrapper", wrapper);

        }else{
            model.put("errorMsg", "Please select user before edit user's profile information");
            returnPage = "/users/edit";
        }


        return returnPage;
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE', 'ACCOUNT_USER_ROLE')")
    @RequestMapping(value = "/users/change-password", method = RequestMethod.POST)
    public String changePassword(HttpSession session,
                                  @ModelAttribute("changePasswordWrapper")UserWrapper userWrapper, ModelMap model){
        System.out.println("\n Change Password POST method \n");
        String returnPage = "/users/change-password";
        model.put("error", true);
        model.put("success", false);
        if(!ValidationUtility.isExists(userWrapper.getOldPassword())){
            model.put("errorMsg", "Please enter your old password first");
        }else if(!ValidationUtility.isExists(userWrapper.getPassword1())){
            model.put("errorMsg", "Please enter both Passwords");
        }else if(!ValidationUtility.isExists(userWrapper.getPassword2())){
            model.put("errorMsg", "Please enter both Passwords");
        }else if(!userWrapper.getPassword1().equals(userWrapper.getPassword2())){
            model.put("errorMsg", "New Passwords don't match");
        }else {
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername();
            loginId = userService.getUserId(loginId);
            userWrapper.setCreatedBy(loginId);
            userWrapper.setUpdatedBy(loginId);
            result = userService.changePassword(userWrapper);
            if(result.getIsSuccessful()){
                model.put("error", false);
                model.put("success", true);
                model.put("successMsg", result.getMessage());
                System.out.println("\n********** Success message from controller ***************\n");
            }else{
                model.put("errorMsg", result.getMessage());
                System.out.println("\n********** error message from controller ***************\n");
            }
        }

        return returnPage;// If we redirect, whole page populated and error/success message not displayed
    }

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE', 'ACCOUNT_USER_ROLE')")
    @RequestMapping(value = "/users/home" )
    public String userHome(ModelMap model, HttpServletRequest request) {
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername(); //get logged in username
        result = userService.getUserByLoginId(loginId); /////////////   to be edited
        Users user = (Users)result.getObject();
        HttpSession session = request.getSession(true);
        session.setAttribute("userName", user.getEmail());
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

    @RequestMapping(value = "/users/back-to-edit-user" )
    public String usersBackPage(HttpSession session) {
        //return "redirect:"+session.getAttribute("previous_page").toString();
        return "redirect:/boards/edit-user-access";
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

    @PreAuthorize("hasAnyRole('ACCOUNT_ADMIN_ROLE')")
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
            //user.setName("Admin");
            user.setFirstName("Admin");
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


    public UserWrapper getTempUserListWrapper() {
        return tempUserListWrapper;
    }

    public void setTempUserListWrapper(UserWrapper tempUserListWrapper) {
        this.tempUserListWrapper = tempUserListWrapper;
    }

    private Map populateUserRoleCombo(){
        List<UserWrapper> roleList = null;
        roleList = userService.populateRoleList();
        Map<String,String> roleMap = new LinkedHashMap<String, String>();
        if (roleList != null && roleList.size() > 0) {
            for (UserWrapper uWrapper : roleList) {
                roleMap.put(uWrapper.getRoleId(), uWrapper.getRoleName());
            }
        }
        return roleMap;
    }

    private Map populateCompanyCombo(){
        List<UserWrapper> companyList = null;
        companyList = userService.populateCompanyList();
        Map<String,String> roleMap = new LinkedHashMap<String, String>();
        if (companyList != null && companyList.size() > 0) {
            for (UserWrapper uWrapper : companyList) {
                roleMap.put(uWrapper.getCompanyId(), uWrapper.getCompanyName());
            }
        }
        return roleMap;
    }

    private ModelMap populateUserTechnicalInfo(ModelMap model){
        UserWrapper wrapper = new UserWrapper();
        this.roleMap = populateUserRoleCombo();
        model.put("roleList", roleMap);

        if(ValidationUtility.isExists(this.tempUserListWrapper)){
            result = userService.listUsersWithDetail(tempUserListWrapper);
            wrapper.setUserList((List<UserWrapper>) result.getObject());
            model.put("editUserTechnicalWrapper", wrapper);
        }else{
            model.clear();
            return model;
        }

        return model;
    }

    private void doAutoLogin(HttpServletRequest request) {
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        result = userService.getUserByLoginId(loginId);
        Users user = (Users)result.getObject();

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = this.authenticationManager.authenticate(token);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            System.out.println("---------------Authentication  exception-------------- ");
        }

    }

    private ModelMap setPageMessages(Result result, ModelMap model){
        if(result.getIsSuccessful()){
            model.put("success", "1");
        }else{
            model.put("success", "0");
        }
        this.setMessage(result.getMessage());
        return model;
    }

    private ModelMap setPageMessages(String message, boolean error, ModelMap model){
        if(error){
            model.put("success", "0");
        }else{
            model.put("success", "1");
        }
        this.setMessage(message);
        return model;
    }

    private ModelMap getPageMessages(String param, ModelMap model){
        if(param.equalsIgnoreCase("1")){
            model.put("error", false);
            model.put("success", true);
            model.put("successMsg", this.getMessage());
        }
        if(param.equalsIgnoreCase("0")){
            model.put("error", true);
            model.put("success", false);
            model.put("errorMsg",this.getMessage());
        }
        return model;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}






//    @RequestMapping(value = "/logout" )
//    public String logout(ModelMap model, HttpServletRequest request){
////        request.getSession(true).invalidate();
//        System.out.println("logout user page shown--------------------");
//        return "/login/logout";
//    }