/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author syncsys
 */
@Entity
public class Tasks {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Boxes parentBox;
    private String title;
    @Lob
    private String description;
    private String priority; // high , low, critical, normal
    private String status; //completed, working, pending/queued, in problems.
    private String userSize; // just for checking how many users assigned to this task (no backend functionality)
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
    private Long boardLogId;

    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "tasklist")
    private Collection<Tasks_Users_Updated> taskUsers = new ArrayList<Tasks_Users_Updated>();

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name="tasks_users", joinColumns={@JoinColumn(name="tasklist_id")}, inverseJoinColumns={@JoinColumn(name="userlist_id")})
    private Collection<Users> userList = new ArrayList<Users>();
    /*@ManyToMany(mappedBy = "taskList")
    private Collection <Users> userList = new ArrayList<Users>();*/

    @OneToMany   (cascade = CascadeType.REMOVE, mappedBy = "parentTask")
    private  Collection <Attachment> attachmentList = new ArrayList<Attachment>();
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
     * @return the parentBox
     */
    public Boxes getParentBox() {
        return parentBox;
    }

    /**
     * @param parentBox the parentBox to set
     */
    public void setParentBox(Boxes parentBox) {
        this.parentBox = parentBox;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(Collection<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getUserSize() {
        return userSize;
    }

    public void setUserSize(String userSize) {
        this.userSize = userSize;
    }

    public Collection<Tasks_Users_Updated> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(Collection<Tasks_Users_Updated> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public Collection<Users> getUserList() {
        return userList;
    }

    public void setUserList(Collection<Users> userList) {
        this.userList = userList;
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

    public Long getBoardLogId() {
        return boardLogId;
    }

    public void setBoardLogId(Long boardLogId) {
        this.boardLogId = boardLogId;
    }
}
