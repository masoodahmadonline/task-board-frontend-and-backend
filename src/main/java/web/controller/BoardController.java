package web.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.*;
import web.service.BoardsService;
import web.service.BoxesService;
import web.service.UsersService;
import web.service.common.Result;
import web.service.common.ValidationUtility;
import web.wrapper.UserWrapper;


//test comment to check github functionality
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
    Map<String,String> companyMap = new LinkedHashMap<String, String>();


    /*@RequestMapping (value = "/boards/create")
    public String createBoard(ModelMap model){
        //// queue, {user-module} if user is admin or has privs to create a board
        result = userService.getUserByLoginId("admin@admin.com");
        Users user = (Users)result.getObject();
        Boards board = new Boards();
        board.setTitle("Temprary Board");
        board.setCreatedBy(user.getId());
        board.setCreatedDate(new Date());
        Companies comp = new Companies();

        //board.setUserList(userList); //Implement for Boards_Users role
        result = boardService.save(board);
        if(result.getIsSuccessful()){
            model.put("successMessages", result.getMessageList());
        }else{
            model.put("errorMessages", result.getMessageList());
        }
        return "/boards/create";
    } */

    @PreAuthorize("@securityService.hasBoardCreatePermission()")
    @RequestMapping(value = "/boards/create", method = RequestMethod.GET)
    public String createBoard(ModelMap model){
        UserWrapper wrapper = new UserWrapper();
        this.companyMap = populateCompanyCombo();
        model.put("companyList", companyMap);
        model.put("createBoardWrapper", wrapper);
        return "/boards/create";
    }

    @PreAuthorize("@securityService.hasBoardCreatePermission()")
    @RequestMapping(value = "/boards/create", method = RequestMethod.POST)
    public String createBoard(HttpSession session, @ModelAttribute("createBoardWrapper")UserWrapper userWrapper, ModelMap model){

        model.put("error", true);
        model.put("success", false);
        this.companyMap = populateCompanyCombo();
        model.put("companyList", this.companyMap);
        if(!ValidationUtility.isExists(userWrapper.getBoardName())){
            model.put("errorMsg", "Please enter board name");
        }else if(!ValidationUtility.isExists(userWrapper.getCompanyId())){
            model.put("errorMsg", "Please select your company");
        }else {
            User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginId = springUser.getUsername();
            result = userService.getUserByLoginId(loginId);
            Users user = (Users)result.getObject();
            userWrapper.setUserId("" + user.getId());
            userWrapper.setCreatedBy("" + user.getId());
            userWrapper.setUpdatedBy("" + user.getId());
            result = boardService.saveBoard(userWrapper);
            if(result.getIsSuccessful()){
                model.put("error", false);
                model.put("success", true);
                model.put("successMsg", result.getMessage());
            }else{
                model.put("errorMsg", result.getMessage());
            }
        }

        return "/boards/create";
    }

    @PreAuthorize("@securityService.hasBoardViewPermission(#id)")
    @RequestMapping (value = "/boards/{id}")
    public String viewBoard(ModelMap model,
                           HttpServletRequest request,
                           HttpSession session,
                           @PathVariable(value="id") String id,
                           @ModelAttribute("uWrapper")UserWrapper userWrapper){

        //// queue, {user-module} if user is admin or has privs to view a board
       List<UserWrapper> usersList = null;

       User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String loginId = springUser.getUsername();
       String companyId = null;
       companyId = userService.getCompanyId(loginId);

       usersList = userService.listUsersWithDetail(companyId, id);
       //UserWrapper userWrapper = new UserWrapper();
       //userWrapper.setUserList(usersList);
       //model.put("uWrapper", userWrapper);
       model.put("viewAssignForm", true);
       if(ValidationUtility.isExists(userWrapper.getUserList()))
           for(UserWrapper wr : userWrapper.getUserList()){
               System.out.println("User enabled Initial: " + wr.isEnableUserAssignId());
           }
       String[] uriArray = request.getRequestURI().toString().split("/");
        int uriArraySize = uriArray.length;
        session.setAttribute("previous_page", "/"+uriArray[uriArraySize-2]+"/"+uriArray[uriArraySize-1]);
        result =  boardService.getBoardByIdToDisplay(Long.parseLong(id));
        if(result.getIsSuccessful()){
            model.put("successMessages", result.getMessageList());
            model.put("board", result.getObject());
            Boards tempBoard = (Boards)result.getObject();
            List<Boxes> boxList = new ArrayList<Boxes>( tempBoard.getChildBoxList() );
            for (Boxes box: boxList){
                System.out.println(box.getId());
                for(Tasks task : box.getTaskList()){
                }
            }
        }else{
            model.put("errorMessages", result.getMessageList());
        }
        return "/boards/board";
   }


    @PreAuthorize("@securityService.hasUserAccessPermission(#bId)")
    @RequestMapping(value = "/boards/delete-board")
    public String deleteBoard(HttpSession session, @RequestParam(required=false) String bId, ModelMap model){
        System.out.println("\n Delete board method \n");
        String returnString = "redirect:/users/home";
        if (ValidationUtility.isExists(bId)) {
            result = userService.deleteBoard(Long.valueOf(bId));
            /*if(result.getIsSuccessful()){
                model.put("error", false);
                model.put("success", true);
                model.put("successMsg", result.getMessage());
                returnString = "/users/home";
            }else{
                model.put("error", true);
                model.put("success", false);
                model.put("errorMsg", result.getMessage());
                returnString = "/users/home";
            }*/
        }
        return returnString;
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

}



//      for viewBoard() /boards/{id}
//       String requestUrl="/"+uri[1]+"/"+uri[2] ;
//       String stripped = request.getRequestURI().toString().substring(1);
//       String sub = stripped.substring(stripped.indexOf('/'));
//       if(uri.split("/").length == 2){
//           session.setAttribute("previous_page",uri);
//       }else{
//           session.setAttribute("previous_page",sub);
//       }