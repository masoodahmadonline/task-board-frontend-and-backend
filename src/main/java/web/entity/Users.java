/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entity;

import java.util.*;
import javax.persistence.*;


/**
 *
 * @author syncsys
 */
@Entity
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private boolean isEnabled; // have to log in first time with given password (when user was invited)
    private byte[] personimage;
    private String imageName;
    private boolean boardCreator;
    private boolean accountAdmin;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
    
    @ManyToOne
    private Companies company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userlist", cascade=CascadeType.ALL)
    private Collection<Boards_Users> boardUsers = new ArrayList<Boards_Users>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userlist", cascade=CascadeType.ALL)
    private Collection<Tasks_Users_Updated> taskUsers = new ArrayList<Tasks_Users_Updated>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="tasks_users", joinColumns={@JoinColumn(name="userlist_id")}, inverseJoinColumns={@JoinColumn(name="tasklist_id")})
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isEnabled
     */
    public boolean getIsEnabled() {
        return isEnabled();
    }

    /**
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(boolean isEnabled) {
        this.setEnabled(isEnabled);
    }

    /**
     * @return the company
     */
    public Companies getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Companies company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }


    public byte[] getPersonimage() {
        return this.personimage;
    }

    public void setPersonimage(byte[] personimage) {
        this.personimage = personimage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public Collection<Boards_Users> getBoardUsers() {
        return boardUsers;
    }

    public void setBoardUsers(Collection<Boards_Users> boardUsers) {
        this.boardUsers = boardUsers;
    }

    public boolean isBoardCreator() {
        return boardCreator;
    }

    public void setBoardCreator(boolean boardCreator) {
        this.boardCreator = boardCreator;
    }

    public boolean isAccountAdmin() {
        return accountAdmin;
    }

    public void setAccountAdmin(boolean accountAdmin) {
        this.accountAdmin = accountAdmin;
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

    public Collection<Tasks_Users_Updated> getTaskUsers() {
        return taskUsers;
    }

    public void setTaskUsers(Collection<Tasks_Users_Updated> taskUsers) {
        this.taskUsers = taskUsers;
    }

    public Collection<Tasks> getTaskList() {
        return taskList;
    }

    public void setTaskList(Collection<Tasks> taskList) {
        this.taskList = taskList;
    }

}
