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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author syncsys
 */

public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String type; // admin / teamlead / normal
    private String name;
    private String email;
    private String password;
    @Lob
    private String description;
    private boolean isEnabled; // have to log in first time with given password (when user was invited)

    @ManyToOne
    private Companies company;

    @ManyToMany (mappedBy = "userList")
    private Collection<Boards> boardList = new ArrayList<Boards>();
    @ManyToMany (mappedBy = "userList")
    private Collection<Boxes> BoxList = new ArrayList<Boxes>();
    @ManyToMany (mappedBy = "userList")
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
     * @return the isEnabled
     */
    public boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
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

    
    
}
