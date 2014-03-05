/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import javax.persistence.*;

/**
 *
 * @author syncsys
 */
@Entity
public class Boxes{
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Boards parentBoard;
    @ManyToOne
    private Boxes parentBox; // the root box will have parentBox == null;
    @OneToMany (mappedBy ="parentBox", cascade = CascadeType.REMOVE)
//    @JoinTable
//            (name = "Boxes_childboxes",
//            joinColumns = @JoinColumn(name="BoxId"),
//            inverseJoinColumns = @JoinColumn(name="childBoxId")
//                )
    private Collection<Boxes> childBoxList = new ArrayList<Boxes>(); // the last leaf box will have childBox == null;
    @OneToMany(cascade=CascadeType.REMOVE,mappedBy="parentBox")
    private Collection<Tasks> taskList = new ArrayList<Tasks>();
    @ManyToOne
    private Companies company;
    @ManyToMany
    private Collection<BoxPrivileges> boxPrivilegesList = new ArrayList<BoxPrivileges>();
    private String type; // vertical / horizontal
    private String title;
    @Lob
    private String description;
    private boolean isFirstLevelBox; //in a board.
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
     * @return the parentBoard
     */
    public Boards getParentBoard() {
        return parentBoard;
    }

    /**
     * @param parentBoard the parentBoard to set
     */
    public void setParentBoard(Boards parentBoard) {
        this.parentBoard = parentBoard;
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
     * @return the boxPrivilegesList
     */
    public Collection<BoxPrivileges> getBoxPrivilegesList() {
        return boxPrivilegesList;
    }

    /**
     * @param boxPrivilegesList the boxPrivilegesList to set
     */
    public void setBoxPrivilegesList(Collection<BoxPrivileges> boxPrivilegesList) {
        this.boxPrivilegesList = boxPrivilegesList;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the isFirstLevelBox
     */
    public boolean getIsFirstLevelBox() {
        return isFirstLevelBox;
    }

    /**
     * @param isFirstLevelBox the isFirstLevelBox to set
     */
    public void setIsFirstLevelBox(boolean isFirstLevelBox) {
        this.isFirstLevelBox = isFirstLevelBox;
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


}
