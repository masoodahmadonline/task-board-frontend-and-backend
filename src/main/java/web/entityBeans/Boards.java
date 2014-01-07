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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author syncsys
 */

public class Boards {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany
    @JoinTable (name = "boards_childboxes", 
            joinColumns = @JoinColumn(name="boardId"), 
            inverseJoinColumns = @JoinColumn(name="childBoxId")
            )
    private Collection<Boxes> childBoxList = new ArrayList<Boxes>();
    private String title;
    private String description;
    @ManyToOne
    private Companies company;
    @ManyToMany
    private Collection<BoardPrivileges> boardPrivilegesList = new ArrayList<BoardPrivileges>();
    @ManyToMany 
    private Collection <Users> userList = new ArrayList<Users>();

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
     * @return the childBoxList
     */
    public Collection<Boxes> getChildBoxList() {
        return childBoxList;
    }

    /**
     * @param childBoxList the childBoxList to set
     */
    public void setChildBoxList(Collection<Boxes> childBoxList) {
        this.childBoxList = childBoxList;
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
     * @return the boardPrivilegesList
     */
    public Collection<BoardPrivileges> getBoardPrivilegesList() {
        return boardPrivilegesList;
    }

    /**
     * @param boardPrivilegesList the boardPrivilegesList to set
     */
    public void setBoardPrivilegesList(Collection<BoardPrivileges> boardPrivilegesList) {
        this.boardPrivilegesList = boardPrivilegesList;
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
     * @return the userList
     */
    public Collection <Users> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(Collection <Users> userList) {
        this.userList = userList;
    }
    
        /**
     * @return the company
     */


   
}
