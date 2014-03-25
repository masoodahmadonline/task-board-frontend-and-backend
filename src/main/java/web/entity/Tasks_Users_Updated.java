/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author syncsys
 */
@Entity
public class Tasks_Users_Updated implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="userlist_id")
    private Users userlist;
    @ManyToOne
    @JoinColumn(name="tasklist_id")
    private Tasks tasklist;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUserlist() {
        return userlist;
    }

    public void setUserlist(Users userlist) {
        this.userlist = userlist;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Tasks getTasklist() {
        return tasklist;
    }

    public void setTasklist(Tasks tasklist) {
        this.tasklist = tasklist;
    }


}
