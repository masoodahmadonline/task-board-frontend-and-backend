package web.wrapper;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import web.service.common.ValidationUtility;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Syncsys
 * Date: 1/16/14
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserWrapper implements Serializable {
    private String boardId;
    private String boardName;
    private String boardDesc;
    private String taskName;
    private String taskDesc;
    private String taskPriority;
    private String taskStatus;
    private String taskAssignedBy;
    private String taskAssignedDate;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
    private String userId;
    private String email;
    private String address;
    private String contactNumber;
    private String firstName;
    private String lastName;
    private String oldPassword;
    private String password1;
    private String password2;
    private String enableUser;
    private String wip;
    private CommonsMultipartFile imageData = null;
    private String imageName;
    private String taskId;
    private boolean enableUserId = false;
    private boolean enableUserEditId = false;
    private boolean enableUserAssignId = false;
    private boolean canCreateBoard = false;
    private boolean accountAdmin = false;
    private boolean userAccessFlag = false;

    private boolean taskUserExistSingle = false;
    private boolean taskUserExistMultiple = false;
    private String taskUserSize;

    private boolean tempWipValue = false;
    private String tempWipMessage;

    private String roleId;
    private String roleName;

    private String companyId;
    private String companyName;

    private List<UserWrapper> userList = null;
    private List<UserWrapper> roleList = null;

    public void clearAll(){
        this.setBoardId("");
        this.setBoardName("");
        this.setBoardDesc("");
        this.setCreatedBy("");
        this.setCreatedDate("");
        this.setUpdatedBy("");
        this.setUpdatedDate("");
        this.setUserId("");
        this.setEmail("");
        this.setAddress("");
        this.setContactNumber("");
        this.setFirstName("");
        this.setLastName("");
        this.setOldPassword("");
        this.setPassword1("");
        this.setPassword2("");
        this.setEnableUser("");
        this.setWip("");
        this.setImageName("");
        this.setImageData(null);
        this.setTaskId("");
        this.setTaskName("");
        this.setTaskDesc("");
        this.setTaskPriority("");
        this.setTaskStatus("");
        this.setTaskAssignedBy("");
        this.setTaskAssignedDate("");
        this.setEnableUserAssignId(false);
        this.setEnableUserEditId(false);
        this.setEnableUserId(false);
        this.setUserAccessFlag(false);
        this.setAccountAdmin(false);
        this.setCanCreateBoard(false);

        this.setTaskUserSize("");
        this.setTaskUserExistSingle(false);
        this.setTaskUserExistMultiple(false);

        this.setRoleId("");
        this.setRoleName("");
        this.setCompanyId("");
        this.setCompanyName("");

        this.setTempWipValue(false);
        this.setTempWipMessage("");

        if(ValidationUtility.isExists(getUserList())){
            getUserList().clear();
        }
        if(ValidationUtility.isExists(getRoleList())){
            getRoleList().clear();
        }

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEnableUser() {
        return enableUser;
    }

    public void setEnableUser(String enableUser) {
        this.enableUser = enableUser;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserWrapper> getUserList() {
        return userList;
    }

    public void setUserList(List<UserWrapper> userList) {
        this.userList = userList;
    }

    public List<UserWrapper> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserWrapper> roleList) {
        this.roleList = roleList;
    }

    public String getWip() {
        return wip;
    }

    public void setWip(String wip) {
        this.wip = wip;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isEnableUserEditId() {
        return enableUserEditId;
    }

    public void setEnableUserEditId(boolean enableUserEditId) {
        this.enableUserEditId = enableUserEditId;
    }

    public boolean isEnableUserAssignId() {
        return enableUserAssignId;
    }

    public void setEnableUserAssignId(boolean enableUserAssignId) {
        this.enableUserAssignId = enableUserAssignId;
    }

    public boolean isEnableUserId() {
        return enableUserId;
    }

    public void setEnableUserId(boolean enableUserId) {
        this.enableUserId = enableUserId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getTaskUserSize() {
        return taskUserSize;
    }

    public void setTaskUserSize(String taskUserSize) {
        this.taskUserSize = taskUserSize;
    }

    public boolean isTaskUserExistSingle() {
        return taskUserExistSingle;
    }

    public void setTaskUserExistSingle(boolean taskUserExistSingle) {
        this.taskUserExistSingle = taskUserExistSingle;
    }

    public boolean isTaskUserExistMultiple() {
        return taskUserExistMultiple;
    }

    public void setTaskUserExistMultiple(boolean taskUserExistMultiple) {
        this.taskUserExistMultiple = taskUserExistMultiple;
    }

    @Override
    public String toString() {
        return "User [Email=" + email +"]";
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public boolean isTempWipValue() {
        return tempWipValue;
    }

    public void setTempWipValue(boolean tempWipValue) {
        this.tempWipValue = tempWipValue;
    }

    public String getTempWipMessage() {
        return tempWipMessage;
    }

    public void setTempWipMessage(String tempWipMessage) {
        this.tempWipMessage = tempWipMessage;
    }

    public CommonsMultipartFile getImageData() {
        return imageData;
    }

    public void setImageData(CommonsMultipartFile imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public boolean isCanCreateBoard() {
        return canCreateBoard;
    }

    public void setCanCreateBoard(boolean canCreateBoard) {
        this.canCreateBoard = canCreateBoard;
    }


    public boolean isAccountAdmin() {
        return accountAdmin;
    }

    public void setAccountAdmin(boolean accountAdmin) {
        this.accountAdmin = accountAdmin;
    }

    public boolean isUserAccessFlag() {
        return userAccessFlag;
    }

    public void setUserAccessFlag(boolean userAccessFlag) {
        this.userAccessFlag = userAccessFlag;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskAssignedBy() {
        return taskAssignedBy;
    }

    public void setTaskAssignedBy(String taskAssignedBy) {
        this.taskAssignedBy = taskAssignedBy;
    }

    public String getTaskAssignedDate() {
        return taskAssignedDate;
    }

    public void setTaskAssignedDate(String taskAssignedDate) {
        this.taskAssignedDate = taskAssignedDate;
    }
}
