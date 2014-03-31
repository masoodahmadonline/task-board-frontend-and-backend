/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UsersDAO;
import web.entity.Boards_Users;
import web.entity.Boxes;
import web.entity.Users;
import web.service.UsersService;

/**
 *
 * @author syncsys
 */
@Transactional(readOnly = true)
@Component("securityService")
@PreAuthorize("isAuthenticated()")
public class SecurityService{

    @Autowired
    private UsersDAO userDAO;
    @Autowired
    private UsersService userService;

    public boolean hasUserAccessPermission(String boardId) {
        boolean result = false;
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String userId = null;
        userId = userService.getUserId(loginId);

        if(ValidationUtility.isExists(userId) && ValidationUtility.isExists(boardId)){
            Boards_Users boardUsers = new Boards_Users();
            boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(boardId), "userlist.id", Long.valueOf(userId));
            if(ValidationUtility.isExists(boardUsers)){
                if(ValidationUtility.isExists(boardUsers.getUserRoleForBoard())){
                    Long roleId =  boardUsers.getUserRoleForBoard().getId();
                    int adminRoleId = ProjectDBConstants.ADMIN_ROLE_ID.intValue();
                    int managerRoleId = ProjectDBConstants.MANAGER_ROLE_ID.intValue();
                    int compAdminRoleId = ProjectDBConstants.COMPANY_ADMIN_ROLE_ID.intValue();
                    if(roleId == adminRoleId || roleId == managerRoleId || roleId == compAdminRoleId){
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /*public boolean hasCompanyCreatePermission() {
        boolean result = false;
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String userId = null;
        userId = userService.getUserId(loginId);
        Users user = new Users();
        user = (Users)userDAO.findById(user, Long.valueOf(userId));
        if(user.isCompanyCreator()){
            result = true;
        }
        return result;
    }*/

    public boolean hasBoardViewPermission(String boardId) {
        boolean result = false;
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String userId = null;
        userId = userService.getUserId(loginId);

        if(ValidationUtility.isExists(userId) && ValidationUtility.isExists(boardId)){
            Boards_Users boardUsers = new Boards_Users();
            boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(boardId), "userlist.id", Long.valueOf(userId));
            if(ValidationUtility.isExists(boardUsers)){
                if(ValidationUtility.isExists(boardUsers.getUserRoleForBoard())){
                    Long roleId =  boardUsers.getUserRoleForBoard().getId();
                    int readerRoleId = ProjectDBConstants.READER_ROLE_ID.intValue();
                    int userRoleId = ProjectDBConstants.USER_ROLE_ID.intValue();
                    int managerRoleId = ProjectDBConstants.MANAGER_ROLE_ID.intValue();
                    int adminRoleId = ProjectDBConstants.ADMIN_ROLE_ID.intValue();
                    int compAdminRoleId = ProjectDBConstants.COMPANY_ADMIN_ROLE_ID.intValue();
                    if(roleId == compAdminRoleId || roleId == adminRoleId || roleId == managerRoleId || roleId == userRoleId || roleId == readerRoleId){
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean hasBoardCreatePermission() {
        boolean result = false;
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String userId = null;
        userId = userService.getUserId(loginId);
        Users user = new Users();
        user = (Users)userDAO.findById(user, Long.valueOf(userId));
        if(user.isBoardCreator()){
            result = true;
        }
        return result;
    }

    public boolean hasBoxCreatePermission(String parent, String parentId) {
        boolean result = false;
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String userId = null;
        userId = userService.getUserId(loginId);

        boolean isBoard = true;
        String boardId = null;
        Boxes parentBox = null;
        if(parent.equals("board")){
            boardId = parentId;
        }else{
            do{
                parentBox = new Boxes();
                parentBox = (Boxes) userDAO.findById(parentBox, Long.valueOf(parentId));
                if(parentBox.getIsFirstLevelBox() && !ValidationUtility.isExists(parentBox.getParentBox())){
                    boardId = "" + parentBox.getParentBoard().getId();
                    isBoard = false;
                    break;
                }else{
                    parentId = "" + parentBox.getParentBox().getId();
                }
            }while(isBoard);
        }

        if(ValidationUtility.isExists(userId) && ValidationUtility.isExists(boardId)){
            Boards_Users boardUsers = new Boards_Users();
            boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(boardId), "userlist.id", Long.valueOf(userId));
            if(ValidationUtility.isExists(boardUsers)){
                if(ValidationUtility.isExists(boardUsers.getUserRoleForBoard())){
                    Long roleId =  boardUsers.getUserRoleForBoard().getId();
                    int readerRoleId = ProjectDBConstants.READER_ROLE_ID.intValue();
                    int userRoleId = ProjectDBConstants.USER_ROLE_ID.intValue();
                    int managerRoleId = ProjectDBConstants.MANAGER_ROLE_ID.intValue();
                    int adminRoleId = ProjectDBConstants.ADMIN_ROLE_ID.intValue();
                    int compAdminRoleId = ProjectDBConstants.COMPANY_ADMIN_ROLE_ID.intValue();
                    if(roleId == compAdminRoleId || roleId == adminRoleId || roleId == managerRoleId || roleId == userRoleId){
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean hasBoxTaskEditPermission(String boxId) {
        boolean result = false;
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String userId = null;
        userId = userService.getUserId(loginId);

        boolean isBoard = true;
        String boardId = null;
        Boxes parentBox = null;

        do{
            parentBox = new Boxes();
            parentBox = (Boxes) userDAO.findById(parentBox, Long.valueOf(boxId));
            if(parentBox.getIsFirstLevelBox() && !ValidationUtility.isExists(parentBox.getParentBox())){
                boardId = "" + parentBox.getParentBoard().getId();
                isBoard = false;
                break;
            }else{
                boxId = "" + parentBox.getParentBox().getId();
            }
        }while(isBoard);


        if(ValidationUtility.isExists(userId) && ValidationUtility.isExists(boardId)){
            Boards_Users boardUsers = new Boards_Users();
            boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(boardId), "userlist.id", Long.valueOf(userId));
            if(ValidationUtility.isExists(boardUsers)){
                if(ValidationUtility.isExists(boardUsers.getUserRoleForBoard())){
                    Long roleId =  boardUsers.getUserRoleForBoard().getId();
                    int readerRoleId = ProjectDBConstants.READER_ROLE_ID.intValue();
                    int userRoleId = ProjectDBConstants.USER_ROLE_ID.intValue();
                    int managerRoleId = ProjectDBConstants.MANAGER_ROLE_ID.intValue();
                    int adminRoleId = ProjectDBConstants.ADMIN_ROLE_ID.intValue();
                    int compAdminRoleId = ProjectDBConstants.COMPANY_ADMIN_ROLE_ID.intValue();
                    if(roleId == compAdminRoleId || roleId == adminRoleId || roleId == managerRoleId || roleId == userRoleId){
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean hasBoardEditPermission(String boardId) {
        boolean result = false;
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String userId = null;
        userId = userService.getUserId(loginId);

        if(ValidationUtility.isExists(userId) && ValidationUtility.isExists(boardId)){
            Boards_Users boardUsers = new Boards_Users();
            boardUsers = (Boards_Users) userDAO.findByTwoPropertiesUnique(boardUsers, "boardlist.id", Long.valueOf(boardId), "userlist.id", Long.valueOf(userId));
            if(ValidationUtility.isExists(boardUsers)){
                if(ValidationUtility.isExists(boardUsers.getUserRoleForBoard())){
                    Long roleId =  boardUsers.getUserRoleForBoard().getId();
                    int userRoleId = ProjectDBConstants.USER_ROLE_ID.intValue();
                    int adminRoleId = ProjectDBConstants.ADMIN_ROLE_ID.intValue();
                    int managerRoleId = ProjectDBConstants.MANAGER_ROLE_ID.intValue();
                    int compAdminRoleId = ProjectDBConstants.COMPANY_ADMIN_ROLE_ID.intValue();
                    if(roleId == adminRoleId || roleId == managerRoleId || roleId == compAdminRoleId || roleId == userRoleId){
                        result = true;
                    }
                }
            }
        }
        return result;
    }

}
