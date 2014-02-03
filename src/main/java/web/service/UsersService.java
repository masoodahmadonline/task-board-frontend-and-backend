/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import web.entity.Users;
import web.service.common.ResultImpl;
import web.wrapper.UserWrapper;

import java.util.List;

/**
 *
 * @author syncsys
 */
public interface UsersService {
    ResultImpl save(Users user);
    public ResultImpl getUserByLoginId(String email);
    public ResultImpl changeUserRoleForBoard(String userEmail, String role, long boardId);


    ResultImpl saveUser(UserWrapper wrapper);     // Farhan - added for Creating a new user
    public List<UserWrapper> populateRoleList(); // For populating user roles for board
    public List<UserWrapper> listUsersWithDetail();  // To display list of all users with detail
    ResultImpl editUserAccess(UserWrapper wrapper);  // Edit list of user's access
    ResultImpl populateUserInfo(String userId);  // Edit User Info
    ResultImpl taskAssignment(UserWrapper wrapper);  // Assign/ Un-assign task to users
    UserWrapper getTaskUsersList(Long taskId);  // get list of users for each task
    ResultImpl updateUserInfo(UserWrapper wrapper);     // Farhan - added for updating user profile information
  
}
