/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.util.Date;
import java.util.List;

import web.entity.Attachment;
import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Tasks;
import web.service.common.ResultImpl;
import web.wrapper.UserWrapper;

/**
 *
 * @author syncsys
 */
public interface TasksService {
    public ResultImpl save(Tasks task);
    public ResultImpl getTaskById(Long id);
    public Tasks setParent(Tasks childTask, Boxes parentBox); // make return object as reustl;
    public ResultImpl deleteTask(Long id, UserWrapper wrapper);
    public ResultImpl moveTask(Long taskId, Long initialParentBoxId, Long destinationParentBoxId, UserWrapper wrapper);
    public ResultImpl saveAttachment(Long taskId, Attachment attachment);
    public ResultImpl getAttachmentById(Long id);
    public ResultImpl deleteAttachment(Long id, UserWrapper wrapper);
    public ResultImpl changeTaskPriority(Long id, String priority, UserWrapper wrapper);
    public ResultImpl changeTaskStatus(Long id, String status, UserWrapper wrapper);
    public ResultImpl editTask(Long id, String boxTitle, String boxDescription, UserWrapper wrapper);
    

}                               
