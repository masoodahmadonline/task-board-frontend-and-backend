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
public class BoardWrapper implements Serializable {
    private String boardId;
    private String boardUserId;
    private boolean boardUpdateRequired = false;
    private String boardCount;
    private String boardUserCount;
    private String taskCount;
    private String taskUserCount;
    private String boxCount;
    private String attachmentCount;
    private String message;
    private String totalCount;

    public void clearAll(){
        this.setBoardId("");
        this.setBoardUserId("");
        this.setTotalCount("");
        this.setAttachmentCount("");
        this.setBoardCount("");
        this.setBoardUpdateRequired(false);
        this.setBoardUserCount("");
        this.setBoxCount("");
        this.setTaskCount("");
        this.setTaskUserCount("");
        this.setMessage("");

    }


    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public boolean isBoardUpdateRequired() {
        return boardUpdateRequired;
    }

    public void setBoardUpdateRequired(boolean boardUpdateRequired) {
        this.boardUpdateRequired = boardUpdateRequired;
    }

    public String getBoardCount() {
        return boardCount;
    }

    public void setBoardCount(String boardCount) {
        this.boardCount = boardCount;
    }

    public String getBoardUserCount() {
        return boardUserCount;
    }

    public void setBoardUserCount(String boardUserCount) {
        this.boardUserCount = boardUserCount;
    }

    public String getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(String taskCount) {
        this.taskCount = taskCount;
    }

    public String getTaskUserCount() {
        return taskUserCount;
    }

    public void setTaskUserCount(String taskUserCount) {
        this.taskUserCount = taskUserCount;
    }

    public String getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(String boxCount) {
        this.boxCount = boxCount;
    }

    public String getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getBoardUserId() {
        return boardUserId;
    }

    public void setBoardUserId(String boardUserId) {
        this.boardUserId = boardUserId;
    }
}
