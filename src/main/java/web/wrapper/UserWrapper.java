package web.wrapper;

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
    private String taskId;
    private boolean enableUserId = false;
    private boolean enableUserEditId = false;
    private boolean enableUserAssignId = false;

    private boolean taskUserExistSingle = false;
    private boolean taskUserExistMultiple = false;
    private String taskUserSize;

    private String roleId;
    private String roleName;

    private List<UserWrapper> userList = null;
    private List<UserWrapper> roleList = null;

    public void clearAll(){
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
        this.setTaskId("");
        this.setEnableUserAssignId(false);
        this.setEnableUserEditId(false);
        this.setEnableUserId(false);

        this.setTaskUserSize("");
        this.setTaskUserExistSingle(false);
        this.setTaskUserExistMultiple(false);

        this.setRoleId("");
        this.setRoleName("");

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
}
