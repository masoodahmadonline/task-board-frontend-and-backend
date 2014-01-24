/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entity;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


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
    private String name;//to be depricated
    private String email;
    private String password;
    private boolean isEnabled; // have to log in first time with given password (when user was invited)
    private String role;
    private Long wip;
    
    @ManyToOne
    private Companies company;
    @OneToMany
    private Collection<BoardPrivileges> boardPrivilegsList =  new ArrayList<BoardPrivileges>();
    @OneToMany
    private Collection<BoxPrivileges> boxPrivilegsList =  new ArrayList<BoxPrivileges>();
    @OneToMany
    private Collection<TaskPrivileges> taskPrivilegsList =  new ArrayList<TaskPrivileges>();
    
    @ManyToMany (mappedBy = "userList")
    private Collection<Boards> boardList = new ArrayList<Boards>();
    @ManyToMany (mappedBy = "userList")
    private Collection<Boxes> BoxList = new ArrayList<Boxes>();
    @ManyToMany (mappedBy = "userList")
    private Collection<Tasks> taskList = new ArrayList<Tasks>();

    @ManyToOne
    private UserRoleForBoard userRoleForBoard;

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
     * @return the boardPrivilegsList
     */
    public Collection<BoardPrivileges> getBoardPrivilegsList() {
        return boardPrivilegsList;
    }

    /**
     * @param boardPrivilegsList the boardPrivilegsList to set
     */
    public void setBoardPrivilegsList(Collection<BoardPrivileges> boardPrivilegsList) {
        this.boardPrivilegsList = boardPrivilegsList;
    }

    /**
     * @return the boxPrivilegsList
     */
    public Collection<BoxPrivileges> getBoxPrivilegsList() {
        return boxPrivilegsList;
    }

    /**
     * @param boxPrivilegsList the boxPrivilegsList to set
     */
    public void setBoxPrivilegsList(Collection<BoxPrivileges> boxPrivilegsList) {
        this.boxPrivilegsList = boxPrivilegsList;
    }

    /**
     * @return the taskPrivilegsList
     */
    public Collection<TaskPrivileges> getTaskPrivilegsList() {
        return taskPrivilegsList;
    }

    /**
     * @param taskPrivilegsList the taskPrivilegsList to set
     */
    public void setTaskPrivilegsList(Collection<TaskPrivileges> taskPrivilegsList) {
        this.taskPrivilegsList = taskPrivilegsList;
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

    /**
     * @return the boardList
     */
    public Collection<Boards> getBoardList() {
        return boardList;
    }

    /**
     * @param boardList the boardList to set
     */
    public void setBoardList(Collection<Boards> boardList) {
        this.boardList = boardList;
    }

    /**
     * @return the BoxList
     */
    public Collection<Boxes> getBoxList() {
        return BoxList;
    }

    /**
     * @param BoxList the BoxList to set
     */
    public void setBoxList(Collection<Boxes> BoxList) {
        this.BoxList = BoxList;
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


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UserRoleForBoard getUserRoleForBoard() {
        return userRoleForBoard;
    }

    public void setUserRoleForBoard(UserRoleForBoard userRoleForBoard) {
        this.userRoleForBoard = userRoleForBoard;
    }

    public Long getWip() {
        return wip;
    }

    public void setWip(Long wip) {
        this.wip = wip;
    }
}
