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
public class Boards {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany (mappedBy="parentBoard", cascade = CascadeType.REMOVE)
//    @JoinTable (name = "boards_childboxes", 
//            joinColumns = @JoinColumn(name="boardId"), 
//            inverseJoinColumns = @JoinColumn(name="childBoxId")
//            )
    private Collection<Boxes> childBoxList = new ArrayList<Boxes>();
    private String title;
    private String description;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
    @ManyToOne
    private Companies company;
    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "boardlist")
    private Collection<Boards_Users> boardUsers = new ArrayList<Boards_Users>();


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

    public Collection<Boards_Users> getBoardUsers() {
        return boardUsers;
    }

    public void setBoardUsers(Collection<Boards_Users> boardUsers) {
        this.boardUsers = boardUsers;
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
}
