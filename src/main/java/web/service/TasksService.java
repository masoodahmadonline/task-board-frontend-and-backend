/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import web.entity.Attachment;
import web.entity.Boards;
import web.entity.Boxes;
import web.entity.Tasks;
import web.service.common.ResultImpl;

/**
 *
 * @author syncsys
 */
public interface TasksService {
    public ResultImpl save(Tasks task);
    public ResultImpl getTaskById(Long id);
    public Tasks setParent(Tasks childTask, Boxes parentBox); // make return object as reustl;
    public ResultImpl deleteTask(Long id);
    public ResultImpl moveTask(Long taskId, Long initialParentBoxId, Long destinationParentBoxId);
    public ResultImpl saveAttachment(Long taskId, Attachment attachment);
    public ResultImpl getAttachmentById(Long id);
    public ResultImpl deleteAttachment(Long id);
    public ResultImpl changeTaskPriority(Long id, String priority);
}                               
