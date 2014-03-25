/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entity;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
 
/**
 *
 * @author syncsys
 */
@Entity
public class Companies {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;

    //users
    @OneToMany (mappedBy = "company")
    private Collection<Users> userList = new ArrayList<Users>();
    //boards
    @OneToMany  (mappedBy = "company")
    private Collection<Boards> boardList = new ArrayList<Boards>();

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
