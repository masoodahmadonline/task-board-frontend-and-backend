/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entityBeans;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author syncsys
 */

public class TaskPrivileges {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne  
    private Users user;
    @ManyToMany  (mappedBy = "taskPrivilegesList")
    private Collection<Tasks> taskList = new ArrayList<Tasks>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

   
    /**
     * @return the taskList
     */
    public Collection<Tasks> getTaskList() {
        return taskList;
    }

    /**
     * @param taskList the taskList to set
     */
    public void setTaskList(Collection<Tasks> taskList) {
        this.taskList = taskList;
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }
    

   
}
