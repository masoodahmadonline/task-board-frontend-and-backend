/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entityBeans;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
 
/**
 *
 * @author syncsys
 */

public class Companies {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String address1;
    private String address2;
    private String address3;
    private String phone1;
    private String phone2;
    private String phone3;
    
    //users
    @OneToMany (mappedBy = "company")
    private Collection<Users> userList = new ArrayList<Users>();
    //boards
    @OneToMany  (mappedBy = "company")
    private Collection<Boards> boardList = new ArrayList<Boards>();
    //boxes
    @OneToMany (mappedBy = "company")
    private Collection<Boxes> boxList = new ArrayList<Boxes>();
    //tasks
    @OneToMany  (mappedBy = "company")
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * @return the phone1
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * @param phone1 the phone1 to set
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    /**
     * @return the phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * @return the phone3
     */
    public String getPhone3() {
        return phone3;
    }

    /**
     * @param phone3 the phone3 to set
     */
    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    /**
     * @return the userList
     */
    public Collection<Users> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(Collection<Users> userList) {
        this.userList = userList;
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
     * @return the boxList
     */
    public Collection<Boxes> getBoxList() {
        return boxList;
    }

    /**
     * @param boxList the boxList to set
     */
    public void setBoxList(Collection<Boxes> boxList) {
        this.boxList = boxList;
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
            
}
