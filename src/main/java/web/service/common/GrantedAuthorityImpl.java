/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.common;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author syncsys
 */
public class GrantedAuthorityImpl implements GrantedAuthority{
    private String role;
    
    public GrantedAuthorityImpl(String role){
        setRole(role);
        getAuthority();
    }
    @Override
    public String getAuthority() {
       
        return role;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    
}
