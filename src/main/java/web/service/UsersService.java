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
    public List<UserWrapper> populateCompanyList(); // For populating all companies
    public List<UserWrapper> listUsersWithDetail(String companyId, String boardId);  // To display list of all users in a company with detail
    public List<UserWrapper> listUsersWithDetail(String companyId);  // To display list of all users in a company with detail
    ResultImpl listUsersWithDetail(UserWrapper wrapper);  // To display list of selected users with detail
    ResultImpl editUserAccess(UserWrapper wrapper);  // Edit list of user's access
    ResultImpl populateUserInfo(String userId);  // Edit User Info
    ResultImpl taskAssignment(UserWrapper wrapper);  // Assign/ Un-assign task to users
    UserWrapper getTaskUsersList(Long taskId);  // get list of user-icons for each task
    ResultImpl updateUserInfo(UserWrapper wrapper);     // Farhan - added for updating user profile information
    ResultImpl getTaskUsersListAll(Long boardId, Long taskId, String companyId);   // get list of all user of a company for each task
    ResultImpl changePassword(UserWrapper wrapper);     // Farhan - added for changing a user's password
    public String getUserId(String email);
    ResultImpl deleteUser(UserWrapper wrapper);  // Delete User
    ResultImpl deleteUserAccess(UserWrapper wrapper);  // Disable User
    public String populatePersonImage(String userId, String path); // populate person image
    public String getCompanyId(String email);
    public ResultImpl deleteBoard(Long id);
  
}
