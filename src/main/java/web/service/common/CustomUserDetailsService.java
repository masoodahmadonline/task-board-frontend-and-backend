/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import web.dao.UsersDAO;
import web.dao.impl.jpa.UsersDAOImpl;
import web.entity.Users;


/**
 *
 * @author syncsys
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    //@Resource
   @Autowired
   private UsersDAO userDAO;

  
 /**
  * Retrieves a springUser record containing the springUser's credentials and access.
  */
 public UserDetails loadUserByUsername(String email)
   throws UsernameNotFoundException, DataAccessException {
   
    
  // Declare a null Spring User
  UserDetails springUser = null;
  
   
  try {
      System.out.println("=====zzzzzzzz==");
    System.out.println("the email passed from CustomUserDetailsService in method loadUserByUsername is: " +email);
   // Search database for a springUser that matches the specified email
   // You can provide a custom DAO to access your persistence layer
   // Or use JDBC to access your database
   // DbUser is our custom domain springUser. This is not the same as Spring's User
      System.out.println("debug ---- 1");
   Users dbUser = userDAO.getUserByLoginId(email);
      System.out.println("dbUser email: "+dbUser.getEmail());
      System.out.println("dbUser password: "+dbUser.getPassword());
      Integer authId = 0;
      //Implement for Boards_Users role
      /*if(ValidationUtility.isExists(dbUser.getUserRoleForBoard())){
          authId =  Integer.valueOf("" + dbUser.getUserRoleForBoard().getId());
      }*/
    
   // Populate the Spring User object with details from the dbUser
   // Here we just pass the email, password, and access level
   // getAuthorities() will translate the access level to the correct role type
 System.out.println("debug ---- 2");
   springUser =  new User(
     dbUser.getEmail().toLowerCase(),
     dbUser.getPassword(),
     true,
     true,
     true,
     true,
     //getAuthorities(authId));
     getAuthorities(dbUser.isAccountAdmin()));
 System.out.println("debug ---- 3");
  } catch (Exception e) {
   System.out.println("print Error in retrieving user");
   e.printStackTrace();
    System.out.println(e.getMessage());
   throw new UsernameNotFoundException("Error in retrieving user");
  }
   System.out.println("debug ---- 4");
  // Return springUser to Spring for processing.
  // Take note we're not the one evaluating whether this springUser is authenticated or valid
  // We just merely retrieve a springUser that matches the specified email
  return springUser;
 }
  
 /**
  * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
  * Basically, this interprets the access value whether it's for a regular springUser or admin.
  *
  * @param access an integer value representing the access of the springUser
  * @return collection of granted authorities
  */
  /*public Collection<GrantedAuthority> getAuthorities(Integer access) {
      // Create a list of grants for this springUser
      List<GrantedAuthority> authList = (List<GrantedAuthority>) new ArrayList<GrantedAuthority>(access);

      // All users are granted with ROLE_USER access
      // Therefore this springUser gets a ROLE_USER by default
      //System.out.println("Grant ROLE_USER to this user");
      //authList.add(new GrantedAuthorityImpl("ROLE_USER"));

      // Check if this springUser has admin access
      // We interpret Integer(1) as an admin springUser

      if (access.equals(ProjectDBConstants.COMPANY_ADMIN_ROLE_ID)) {
          authList.add(new GrantedAuthorityImpl("ROLE_COMPANY_ADMIN"));
          System.out.println("Grant ROLE_COMPANY_ADMIN to this user");
      }else if (access.equals(ProjectDBConstants.ADMIN_ROLE_ID)) {
          authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
          System.out.println("Grant ROLE_ADMIN to this user");
      }else if (access.equals(ProjectDBConstants.MANAGER_ROLE_ID)) {
          authList.add(new GrantedAuthorityImpl("ROLE_MANAGER"));
          System.out.println("Grant ROLE_MANAGER to this user");
      }else if (access.equals(ProjectDBConstants.READER_ROLE_ID)) {
          authList.add(new GrantedAuthorityImpl("ROLE_READER"));
          System.out.println("Grant ROLE_READER to this user");
      }else if (access.equals(ProjectDBConstants.NO_ACCESS_ROLE_ID)) {
          authList.add(new GrantedAuthorityImpl("ROLE_NO_ACCESS"));
          System.out.println("Grant ROLE_NO_ACCESS to this user");
      }else{
          authList.add(new GrantedAuthorityImpl("ROLE_USER"));
          System.out.println("Grant ROLE_USER to this user");
      }
      return authList;
  }*/

    public Collection<GrantedAuthority> getAuthorities(boolean access) {
        List<GrantedAuthority> authList = (List<GrantedAuthority>) new ArrayList<GrantedAuthority>();
        if (access) {
            authList.add(new GrantedAuthorityImpl("ACCOUNT_ADMIN_ROLE"));
            System.out.println("Grant ACCOUNT_ADMIN_ROLE to this user");
        }else{
            authList.add(new GrantedAuthorityImpl("ACCOUNT_USER_ROLE"));
            System.out.println("Grant ACCOUNT_USER_ROLE to this user");
        }
        return authList;
    }



  }
