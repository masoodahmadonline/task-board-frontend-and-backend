/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author syncsys
 */
@Entity
public class Boards_Users implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="userlist_id")
    private Users userlist;
    @ManyToOne
    @JoinColumn(name="boardlist_id")
    private Boards boardlist;
    private Long wip;
    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
    @ManyToOne
    private UserRoleForBoard userRoleForBoard;

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

    public Boards getBoardlist() {
        return boardlist;
    }

    public void setBoardlist(Boards boardlist) {
        this.boardlist = boardlist;
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
