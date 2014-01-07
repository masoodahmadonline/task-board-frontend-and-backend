/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import web.entity.Users;
import web.service.common.ResultImpl;

/**
 *
 * @author syncsys
 */
public interface UsersService {
    ResultImpl save(Users user);
    public ResultImpl getUserByLoginId(String email);
    public ResultImpl changeUserRoleForBoard(String userEmail, String role, long boardId);
  
}
