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
    System.out.println("the email passed from CustomUserDetailsService in method loadUserByUsername is: " +email);
   // Search database for a springUser that matches the specified email
   // You can provide a custom DAO to access your persistence layer
   // Or use JDBC to access your database
   // DbUser is our custom domain springUser. This is not the same as Spring's User
      System.out.println("debug ---- 1");
   Users dbUser = userDAO.getUserByLoginId(email);
    
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
     //getAuthorities(dbUser.getAccess()) );
     getAuthorities(2) );
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
  public Collection<GrantedAuthority> getAuthorities(Integer access) {
   // Create a list of grants for this springUser
   List<GrantedAuthority> authList = (List<GrantedAuthority>) new ArrayList<GrantedAuthority>(2);
    
   // All users are granted with ROLE_USER access
   // Therefore this springUser gets a ROLE_USER by default
   System.out.println("Grant ROLE_USER to this user");
   authList.add(new GrantedAuthorityImpl("ROLE_USER"));
    
   // Check if this springUser has admin access
   // We interpret Integer(1) as an admin springUser
   
//   if ( access.compareTo(1) == 0) {
//    // User has admin access
//    logger.debug("Grant ROLE_ADMIN to this user");
//    authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
//   }
 
   // Return list of granted authorities
   return authList;
   }
    
}
