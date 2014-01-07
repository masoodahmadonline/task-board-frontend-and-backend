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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author syncsys
 */

public class BoxPrivileges {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne  
    private Users user;
    @ManyToMany  (mappedBy = "boxPrivilegesList")
    private Collection<Boxes> boxList = new ArrayList<Boxes>();
    
    //This box
    private boolean isCreater; //is creater of this box
    private boolean canRead;
    private boolean canUpdate;
    private boolean canDelete;
    //inherit
    private boolean canCreateAllChildBoxes;
    private boolean canReadAnyChildBoxes;
    private boolean canUpdateAnylChildBoxes;
    private boolean canDeleteAnyChildBoxes;

    //tasks
    private boolean canCreateTasksInThisBox;
    private boolean canReadTasksInThisBox;
    private boolean canUpdateTasksInThisBox;
    private boolean canDeleteTasksInThisBox;
    private boolean canMoveTasksOutOfThisBox;
    private boolean canMoveTasksIntoThisBox;
    private boolean canAssignTasksToOthersInThisBox;
    private boolean canOthersAssignTasksToMeInThisBox;
    //inherit
    private boolean canCreateTasksInAllChildBoxes;
    private boolean canReadTasksInAllChildBoxes;
    private boolean canUpdateTasksInAllChildBoxes;
    private boolean canDeleteTasksInAllChildBoxes;
    private boolean canMoveTasksOutOfAllChildBoxes;
    private boolean canMoveTasksIntoAllChildBoxes;
    private boolean canAssignTasksToOthersInAllChildBoxes;
    private boolean canOthersAssignTasksToMeInAllChildBoxes;

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
     * @return the canCreateAllChildBoxes
     */
    public boolean isCanCreateAllChildBoxes() {
        return canCreateAllChildBoxes;
    }

    /**
     * @param canCreateAllChildBoxes the canCreateAllChildBoxes to set
     */
    public void setCanCreateAllChildBoxes(boolean canCreateAllChildBoxes) {
        this.canCreateAllChildBoxes = canCreateAllChildBoxes;
    }

    /**
     * @return the canReadAnyChildBoxes
     */
    public boolean isCanReadAnyChildBoxes() {
        return canReadAnyChildBoxes;
    }

    /**
     * @param canReadAnyChildBoxes the canReadAnyChildBoxes to set
     */
    public void setCanReadAnyChildBoxes(boolean canReadAnyChildBoxes) {
        this.canReadAnyChildBoxes = canReadAnyChildBoxes;
    }

    /**
     * @return the canUpdateAnylChildBoxes
     */
    public boolean isCanUpdateAnylChildBoxes() {
        return canUpdateAnylChildBoxes;
    }

    /**
     * @param canUpdateAnylChildBoxes the canUpdateAnylChildBoxes to set
     */
    public void setCanUpdateAnylChildBoxes(boolean canUpdateAnylChildBoxes) {
        this.canUpdateAnylChildBoxes = canUpdateAnylChildBoxes;
    }

    /**
     * @return the canDeleteAnyChildBoxes
     */
    public boolean isCanDeleteAnyChildBoxes() {
        return canDeleteAnyChildBoxes;
    }

    /**
     * @param canDeleteAnyChildBoxes the canDeleteAnyChildBoxes to set
     */
    public void setCanDeleteAnyChildBoxes(boolean canDeleteAnyChildBoxes) {
        this.canDeleteAnyChildBoxes = canDeleteAnyChildBoxes;
    }

    /**
     * @return the canCreateTasksInThisBox
     */
    public boolean isCanCreateTasksInThisBox() {
        return canCreateTasksInThisBox;
    }

    /**
     * @param canCreateTasksInThisBox the canCreateTasksInThisBox to set
     */
    public void setCanCreateTasksInThisBox(boolean canCreateTasksInThisBox) {
        this.canCreateTasksInThisBox = canCreateTasksInThisBox;
    }

    /**
     * @return the canReadTasksInThisBox
     */
    public boolean isCanReadTasksInThisBox() {
        return canReadTasksInThisBox;
    }

    /**
     * @param canReadTasksInThisBox the canReadTasksInThisBox to set
     */
    public void setCanReadTasksInThisBox(boolean canReadTasksInThisBox) {
        this.canReadTasksInThisBox = canReadTasksInThisBox;
    }

    /**
     * @return the canUpdateTasksInThisBox
     */
    public boolean isCanUpdateTasksInThisBox() {
        return canUpdateTasksInThisBox;
    }

    /**
     * @param canUpdateTasksInThisBox the canUpdateTasksInThisBox to set
     */
    public void setCanUpdateTasksInThisBox(boolean canUpdateTasksInThisBox) {
        this.canUpdateTasksInThisBox = canUpdateTasksInThisBox;
    }

    /**
     * @return the canDeleteTasksInThisBox
     */
    public boolean isCanDeleteTasksInThisBox() {
        return canDeleteTasksInThisBox;
    }

    /**
     * @param canDeleteTasksInThisBox the canDeleteTasksInThisBox to set
     */
    public void setCanDeleteTasksInThisBox(boolean canDeleteTasksInThisBox) {
        this.canDeleteTasksInThisBox = canDeleteTasksInThisBox;
    }

    /**
     * @return the canMoveTasksOutOfThisBox
     */
    public boolean isCanMoveTasksOutOfThisBox() {
        return canMoveTasksOutOfThisBox;
    }

    /**
     * @param canMoveTasksOutOfThisBox the canMoveTasksOutOfThisBox to set
     */
    public void setCanMoveTasksOutOfThisBox(boolean canMoveTasksOutOfThisBox) {
        this.canMoveTasksOutOfThisBox = canMoveTasksOutOfThisBox;
    }

    /**
     * @return the canMoveTasksIntoThisBox
     */
    public boolean isCanMoveTasksIntoThisBox() {
        return canMoveTasksIntoThisBox;
    }

    /**
     * @param canMoveTasksIntoThisBox the canMoveTasksIntoThisBox to set
     */
    public void setCanMoveTasksIntoThisBox(boolean canMoveTasksIntoThisBox) {
        this.canMoveTasksIntoThisBox = canMoveTasksIntoThisBox;
    }

    /**
     * @return the canAssignTasksToOthersInThisBox
     */
    public boolean isCanAssignTasksToOthersInThisBox() {
        return canAssignTasksToOthersInThisBox;
    }

    /**
     * @param canAssignTasksToOthersInThisBox the canAssignTasksToOthersInThisBox to set
     */
    public void setCanAssignTasksToOthersInThisBox(boolean canAssignTasksToOthersInThisBox) {
        this.canAssignTasksToOthersInThisBox = canAssignTasksToOthersInThisBox;
    }

    /**
     * @return the canOthersAssignTasksToMeInThisBox
     */
    public boolean isCanOthersAssignTasksToMeInThisBox() {
        return canOthersAssignTasksToMeInThisBox;
    }

    /**
     * @param canOthersAssignTasksToMeInThisBox the canOthersAssignTasksToMeInThisBox to set
     */
    public void setCanOthersAssignTasksToMeInThisBox(boolean canOthersAssignTasksToMeInThisBox) {
        this.canOthersAssignTasksToMeInThisBox = canOthersAssignTasksToMeInThisBox;
    }

    /**
     * @return the canCreateTasksInAllChildBoxes
     */
    public boolean isCanCreateTasksInAllChildBoxes() {
        return canCreateTasksInAllChildBoxes;
    }

    /**
     * @param canCreateTasksInAllChildBoxes the canCreateTasksInAllChildBoxes to set
     */
    public void setCanCreateTasksInAllChildBoxes(boolean canCreateTasksInAllChildBoxes) {
        this.canCreateTasksInAllChildBoxes = canCreateTasksInAllChildBoxes;
    }

    /**
     * @return the canReadTasksInAllChildBoxes
     */
    public boolean isCanReadTasksInAllChildBoxes() {
        return canReadTasksInAllChildBoxes;
    }

    /**
     * @param canReadTasksInAllChildBoxes the canReadTasksInAllChildBoxes to set
     */
    public void setCanReadTasksInAllChildBoxes(boolean canReadTasksInAllChildBoxes) {
        this.canReadTasksInAllChildBoxes = canReadTasksInAllChildBoxes;
    }

    /**
     * @return the canUpdateTasksInAllChildBoxes
     */
    public boolean isCanUpdateTasksInAllChildBoxes() {
        return canUpdateTasksInAllChildBoxes;
    }

    /**
     * @param canUpdateTasksInAllChildBoxes the canUpdateTasksInAllChildBoxes to set
     */
    public void setCanUpdateTasksInAllChildBoxes(boolean canUpdateTasksInAllChildBoxes) {
        this.canUpdateTasksInAllChildBoxes = canUpdateTasksInAllChildBoxes;
    }

    /**
     * @return the canDeleteTasksInAllChildBoxes
     */
    public boolean isCanDeleteTasksInAllChildBoxes() {
        return canDeleteTasksInAllChildBoxes;
    }

    /**
     * @param canDeleteTasksInAllChildBoxes the canDeleteTasksInAllChildBoxes to set
     */
    public void setCanDeleteTasksInAllChildBoxes(boolean canDeleteTasksInAllChildBoxes) {
        this.canDeleteTasksInAllChildBoxes = canDeleteTasksInAllChildBoxes;
    }

    /**
     * @return the canMoveTasksOutOfAllChildBoxes
     */
    public boolean isCanMoveTasksOutOfAllChildBoxes() {
        return canMoveTasksOutOfAllChildBoxes;
    }

    /**
     * @param canMoveTasksOutOfAllChildBoxes the canMoveTasksOutOfAllChildBoxes to set
     */
    public void setCanMoveTasksOutOfAllChildBoxes(boolean canMoveTasksOutOfAllChildBoxes) {
        this.canMoveTasksOutOfAllChildBoxes = canMoveTasksOutOfAllChildBoxes;
    }

    /**
     * @return the canMoveTasksIntoAllChildBoxes
     */
    public boolean isCanMoveTasksIntoAllChildBoxes() {
        return canMoveTasksIntoAllChildBoxes;
    }

    /**
     * @param canMoveTasksIntoAllChildBoxes the canMoveTasksIntoAllChildBoxes to set
     */
    public void setCanMoveTasksIntoAllChildBoxes(boolean canMoveTasksIntoAllChildBoxes) {
        this.canMoveTasksIntoAllChildBoxes = canMoveTasksIntoAllChildBoxes;
    }

    /**
     * @return the canAssignTasksToOthersInAllChildBoxes
     */
    public boolean isCanAssignTasksToOthersInAllChildBoxes() {
        return canAssignTasksToOthersInAllChildBoxes;
    }

    /**
     * @param canAssignTasksToOthersInAllChildBoxes the canAssignTasksToOthersInAllChildBoxes to set
     */
    public void setCanAssignTasksToOthersInAllChildBoxes(boolean canAssignTasksToOthersInAllChildBoxes) {
        this.canAssignTasksToOthersInAllChildBoxes = canAssignTasksToOthersInAllChildBoxes;
    }

    /**
     * @return the canOthersAssignTasksToMeInAllChildBoxes
     */
    public boolean isCanOthersAssignTasksToMeInAllChildBoxes() {
        return canOthersAssignTasksToMeInAllChildBoxes;
    }

    /**
     * @param canOthersAssignTasksToMeInAllChildBoxes the canOthersAssignTasksToMeInAllChildBoxes to set
     */
    public void setCanOthersAssignTasksToMeInAllChildBoxes(boolean canOthersAssignTasksToMeInAllChildBoxes) {
        this.canOthersAssignTasksToMeInAllChildBoxes = canOthersAssignTasksToMeInAllChildBoxes;
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
