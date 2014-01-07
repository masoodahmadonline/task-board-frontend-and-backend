/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.entityBeans;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author syncsys
 */

public class BoardPrivileges {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne  
    private Users user;
    @ManyToMany  (mappedBy = "boardPrivilegesList")
    private Collection<Boards> boardList = new ArrayList<Boards>();
    //This Board
    private boolean isCreater; //is creater of this board
    private boolean canRead;
    private boolean canUpdate;
    private boolean canDelete;

    //First Level Boxes
    private boolean canCreateFirstLevelChildBox;
    private boolean canReadFisrtLevelChildBox;
    private boolean canUpdateFisrtLevelChildBox;
    private boolean canDeleteFisrtLevelChildBox;
    //inherit
    private boolean inheritFirstLevelCreatePrivilegeToAllChildBoxes;
    private boolean inheritFirstLevelReadPrivilegeToAllChildBoxes;
    private boolean inheritFirstLevelUpdatePrivilegeToAllChildBoxes;
    private boolean inheritFirstLevelDeletePrivilegeToAllChildBoxes;
    
    //any task
    private boolean canCreateAnyTaskInThisBoard;
    private boolean canReadAnyTaskInThisBoard;
    private boolean canUpdateAnyTaskInThisBoard;
    private boolean canDeleteAnyTaskInThisBoard;
    
    //users        
    private boolean canInviteUsersToThisBoard;
    private boolean canReadUsersOfThisBoard;
    private boolean canUpdateUsersOfThisBoard;
    private boolean canDeleteUsersOfThisBoard;

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
     * @return the userList
     */
   

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
     * @return the isCreater
     */
    public boolean getIsCreater() {
        return isCreater;
    }

    /**
     * @param isCreater the isCreater to set
     */
    public void setIsCreater(boolean isCreater) {
        this.isCreater = isCreater;
    }

    /**
     * @return the canRead
     */
    public boolean isCanRead() {
        return canRead;
    }

    /**
     * @param canRead the canRead to set
     */
    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    /**
     * @return the canUpdate
     */
    public boolean isCanUpdate() {
        return canUpdate;
    }

    /**
     * @param canUpdate the canUpdate to set
     */
    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    /**
     * @return the canDelete
     */
    public boolean isCanDelete() {
        return canDelete;
    }

    /**
     * @param canDelete the canDelete to set
     */
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    /**
     * @return the canCreateFirstLevelChildBox
     */
    public boolean isCanCreateFirstLevelChildBox() {
        return canCreateFirstLevelChildBox;
    }

    /**
     * @param canCreateFirstLevelChildBox the canCreateFirstLevelChildBox to set
     */
    public void setCanCreateFirstLevelChildBox(boolean canCreateFirstLevelChildBox) {
        this.canCreateFirstLevelChildBox = canCreateFirstLevelChildBox;
    }

    /**
     * @return the canReadFisrtLevelChildBox
     */
    public boolean isCanReadFisrtLevelChildBox() {
        return canReadFisrtLevelChildBox;
    }

    /**
     * @param canReadFisrtLevelChildBox the canReadFisrtLevelChildBox to set
     */
    public void setCanReadFisrtLevelChildBox(boolean canReadFisrtLevelChildBox) {
        this.canReadFisrtLevelChildBox = canReadFisrtLevelChildBox;
    }

    /**
     * @return the canUpdateFisrtLevelChildBox
     */
    public boolean isCanUpdateFisrtLevelChildBox() {
        return canUpdateFisrtLevelChildBox;
    }

    /**
     * @param canUpdateFisrtLevelChildBox the canUpdateFisrtLevelChildBox to set
     */
    public void setCanUpdateFisrtLevelChildBox(boolean canUpdateFisrtLevelChildBox) {
        this.canUpdateFisrtLevelChildBox = canUpdateFisrtLevelChildBox;
    }

    /**
     * @return the canDeleteFisrtLevelChildBox
     */
    public boolean isCanDeleteFisrtLevelChildBox() {
        return canDeleteFisrtLevelChildBox;
    }

    /**
     * @param canDeleteFisrtLevelChildBox the canDeleteFisrtLevelChildBox to set
     */
    public void setCanDeleteFisrtLevelChildBox(boolean canDeleteFisrtLevelChildBox) {
        this.canDeleteFisrtLevelChildBox = canDeleteFisrtLevelChildBox;
    }

    /**
     * @return the inheritFirstLevelCreatePrivilegeToAllChildBoxes
     */
    public boolean isInheritFirstLevelCreatePrivilegeToAllChildBoxes() {
        return inheritFirstLevelCreatePrivilegeToAllChildBoxes;
    }

    /**
     * @param inheritFirstLevelCreatePrivilegeToAllChildBoxes the inheritFirstLevelCreatePrivilegeToAllChildBoxes to set
     */
    public void setInheritFirstLevelCreatePrivilegeToAllChildBoxes(boolean inheritFirstLevelCreatePrivilegeToAllChildBoxes) {
        this.inheritFirstLevelCreatePrivilegeToAllChildBoxes = inheritFirstLevelCreatePrivilegeToAllChildBoxes;
    }

    /**
     * @return the inheritFirstLevelReadPrivilegeToAllChildBoxes
     */
    public boolean isInheritFirstLevelReadPrivilegeToAllChildBoxes() {
        return inheritFirstLevelReadPrivilegeToAllChildBoxes;
    }

    /**
     * @param inheritFirstLevelReadPrivilegeToAllChildBoxes the inheritFirstLevelReadPrivilegeToAllChildBoxes to set
     */
    public void setInheritFirstLevelReadPrivilegeToAllChildBoxes(boolean inheritFirstLevelReadPrivilegeToAllChildBoxes) {
        this.inheritFirstLevelReadPrivilegeToAllChildBoxes = inheritFirstLevelReadPrivilegeToAllChildBoxes;
    }

    /**
     * @return the inheritFirstLevelUpdatePrivilegeToAllChildBoxes
     */
    public boolean isInheritFirstLevelUpdatePrivilegeToAllChildBoxes() {
        return inheritFirstLevelUpdatePrivilegeToAllChildBoxes;
    }

    /**
     * @param inheritFirstLevelUpdatePrivilegeToAllChildBoxes the inheritFirstLevelUpdatePrivilegeToAllChildBoxes to set
     */
    public void setInheritFirstLevelUpdatePrivilegeToAllChildBoxes(boolean inheritFirstLevelUpdatePrivilegeToAllChildBoxes) {
        this.inheritFirstLevelUpdatePrivilegeToAllChildBoxes = inheritFirstLevelUpdatePrivilegeToAllChildBoxes;
    }

    /**
     * @return the inheritFirstLevelDeletePrivilegeToAllChildBoxes
     */
    public boolean isInheritFirstLevelDeletePrivilegeToAllChildBoxes() {
        return inheritFirstLevelDeletePrivilegeToAllChildBoxes;
    }

    /**
     * @param inheritFirstLevelDeletePrivilegeToAllChildBoxes the inheritFirstLevelDeletePrivilegeToAllChildBoxes to set
     */
    public void setInheritFirstLevelDeletePrivilegeToAllChildBoxes(boolean inheritFirstLevelDeletePrivilegeToAllChildBoxes) {
        this.inheritFirstLevelDeletePrivilegeToAllChildBoxes = inheritFirstLevelDeletePrivilegeToAllChildBoxes;
    }

    /**
     * @return the canCreateAnyTaskInThisBoard
     */
    public boolean isCanCreateAnyTaskInThisBoard() {
        return canCreateAnyTaskInThisBoard;
    }

    /**
     * @param canCreateAnyTaskInThisBoard the canCreateAnyTaskInThisBoard to set
     */
    public void setCanCreateAnyTaskInThisBoard(boolean canCreateAnyTaskInThisBoard) {
        this.canCreateAnyTaskInThisBoard = canCreateAnyTaskInThisBoard;
    }

    /**
     * @return the canReadAnyTaskInThisBoard
     */
    public boolean isCanReadAnyTaskInThisBoard() {
        return canReadAnyTaskInThisBoard;
    }

    /**
     * @param canReadAnyTaskInThisBoard the canReadAnyTaskInThisBoard to set
     */
    public void setCanReadAnyTaskInThisBoard(boolean canReadAnyTaskInThisBoard) {
        this.canReadAnyTaskInThisBoard = canReadAnyTaskInThisBoard;
    }

    /**
     * @return the canUpdateAnyTaskInThisBoard
     */
    public boolean isCanUpdateAnyTaskInThisBoard() {
        return canUpdateAnyTaskInThisBoard;
    }

    /**
     * @param canUpdateAnyTaskInThisBoard the canUpdateAnyTaskInThisBoard to set
     */
    public void setCanUpdateAnyTaskInThisBoard(boolean canUpdateAnyTaskInThisBoard) {
        this.canUpdateAnyTaskInThisBoard = canUpdateAnyTaskInThisBoard;
    }

    /**
     * @return the canDeleteAnyTaskInThisBoard
     */
    public boolean isCanDeleteAnyTaskInThisBoard() {
        return canDeleteAnyTaskInThisBoard;
    }

    /**
     * @param canDeleteAnyTaskInThisBoard the canDeleteAnyTaskInThisBoard to set
     */
    public void setCanDeleteAnyTaskInThisBoard(boolean canDeleteAnyTaskInThisBoard) {
        this.canDeleteAnyTaskInThisBoard = canDeleteAnyTaskInThisBoard;
    }

    /**
     * @return the canInviteUsersToThisBoard
     */
    public boolean isCanInviteUsersToThisBoard() {
        return canInviteUsersToThisBoard;
    }

    /**
     * @param canInviteUsersToThisBoard the canInviteUsersToThisBoard to set
     */
    public void setCanInviteUsersToThisBoard(boolean canInviteUsersToThisBoard) {
        this.canInviteUsersToThisBoard = canInviteUsersToThisBoard;
    }

    /**
     * @return the canReadUsersOfThisBoard
     */
    public boolean isCanReadUsersOfThisBoard() {
        return canReadUsersOfThisBoard;
    }

    /**
     * @param canReadUsersOfThisBoard the canReadUsersOfThisBoard to set
     */
    public void setCanReadUsersOfThisBoard(boolean canReadUsersOfThisBoard) {
        this.canReadUsersOfThisBoard = canReadUsersOfThisBoard;
    }

    /**
     * @return the canUpdateUsersOfThisBoard
     */
    public boolean isCanUpdateUsersOfThisBoard() {
        return canUpdateUsersOfThisBoard;
    }

    /**
     * @param canUpdateUsersOfThisBoard the canUpdateUsersOfThisBoard to set
     */
    public void setCanUpdateUsersOfThisBoard(boolean canUpdateUsersOfThisBoard) {
        this.canUpdateUsersOfThisBoard = canUpdateUsersOfThisBoard;
    }

    /**
     * @return the canDeleteUsersOfThisBoard
     */
    public boolean isCanDeleteUsersOfThisBoard() {
        return canDeleteUsersOfThisBoard;
    }

    /**
     * @param canDeleteUsersOfThisBoard the canDeleteUsersOfThisBoard to set
     */
    public void setCanDeleteUsersOfThisBoard(boolean canDeleteUsersOfThisBoard) {
        this.canDeleteUsersOfThisBoard = canDeleteUsersOfThisBoard;
    }

    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }
    

    
    
}
