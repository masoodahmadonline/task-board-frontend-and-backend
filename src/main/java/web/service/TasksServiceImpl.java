/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

/**
 *
 * @author syncsys
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.BoxesDAO;
import web.dao.TasksDAO;
import web.dao.UsersDAO;
import web.entity.Attachment;
import web.entity.Boxes;
import web.entity.Tasks;
import web.entity.Users;
import web.service.common.ResultImpl;
import web.service.common.ValidationUtility;
import web.wrapper.UserWrapper;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class TasksServiceImpl implements TasksService{

    @Autowired
    private UsersDAO userDAO;
    @Autowired
    private BoxesDAO boxDAO;
    @Autowired
    private TasksDAO taskDAO;
    @Autowired
    private ResultImpl result;
    
   


    @Transactional (readOnly = false)
    public ResultImpl editTask(Long id, String taskTitle, String taskDescription, UserWrapper wrapper){
        result = getTaskById(id);
        if(result.getIsSuccessful()){
            Tasks task = (Tasks)result.getObject();

            task.setTitle(taskTitle);
            task.setDescription(taskDescription);
            if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
                task.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
                task.setUpdatedDate(new Date());
            }
            result.setObject(task);
            result.setMessageList(Arrays.asList("success.taskEdited"));
        }else{
            result.setMessageList(Arrays.asList("error.taskEditFailed"));
        }
        return result;
    }

    @Transactional (readOnly = false)
    public ResultImpl changeTaskStatus(Long id, String status, UserWrapper wrapper){
        Tasks task = (Tasks)(getTaskById(id)).getObject();
        Tasks taskWithChangedStatus = setTaskStatus(task,status, wrapper);
        if(taskWithChangedStatus.getStatus().equals(status)){
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.taskStatusChanged"));
            result.setObject(task);
        }else{
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.taskStatusChangeFailure"));
        }
        return result;
    }

    @Transactional (readOnly = false)
    public Tasks setTaskStatus(Tasks task, String status, UserWrapper wrapper){
        if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
            task.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
            task.setUpdatedDate(new Date());
        }
        task.setStatus(status);
        return task;
    }
    

    @Transactional(readOnly = false)
    public ResultImpl save(Tasks task){
        Tasks taskToBeReturned = taskDAO.save(task);
        if(taskToBeReturned == null){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.taskCreationErrorUnknown"/*,"string"*/));
        }else{
            result.setIsSuccessful(true);
            result.setObject(taskToBeReturned);
            result.setMessageList(Arrays.asList("success.taskCreated"/*,"string"*/));
        }
        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl saveAttachment(Long taskId, Attachment attachment){
        Tasks task = taskDAO.getTaskById(taskId);
        //save attachment while making the task as its parent
        Attachment savedAttachment = taskDAO.saveAttachment(attachment);
        savedAttachment.setParentTask(task);
        if(savedAttachment == null){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.attachmentCreationErrorUnknown"));
        }else{
            result.setIsSuccessful(true);
            result.setObject(savedAttachment);
            result.setMessageList(Arrays.asList("success.attachmentCreated"));
        }
        return result;
    }
    
    @Transactional(readOnly = false)
    public ResultImpl deleteTask(Long id, UserWrapper wrapper){
        // Implementation for removing associated user to each task
        int size = 0;
        Tasks task = new Tasks();
        task = (Tasks) userDAO.findById(task, id);
        if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
            task.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
            task.setUpdatedDate(new Date());
        }
        /*if(task.getUserList().isEmpty()){
            userDAO.remove(task);
        }else{
            Iterator<Users> it = task.getUserList().iterator();
            size = task.getUserList().size();
            while(it.hasNext()){
                Users user = it.next();
                if(ValidationUtility.isExists(user.getId())){
                    it.remove();
                }
            }
            userDAO.remove(task);
        }*/
        userDAO.remove(task);
        if(task == null){
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.taskDeletionFailed"));
        }else{
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.taskDeleted"));
        }

        /*
        if(taskDAO.deleteTask(id)){
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.taskDeleted"));
        }else{
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.taskDeletionFailed"));
        }*/
        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl deleteAttachment(Long id, UserWrapper wrapper){
        if(taskDAO.deleteAttachment(id, wrapper)){
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.attachmentDeleted"));
        }else{
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.attachmentDeletionFailed"));
        }
        return result;
    }

    @Transactional (readOnly = false)
    public ResultImpl changeTaskPriority(Long id, String priority, UserWrapper wrapper){
        Tasks task = (Tasks)(getTaskById(id)).getObject();
        Tasks taskWithChangedPriority = setTaskPriority(task,priority, wrapper);
        if(taskWithChangedPriority.getPriority().equals(priority)){
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.taskPriorityChanged"));
            result.setObject(task);
        }else{
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.taskPriorityChangeFailure"));
        }
        return result;
    }

    @Transactional
    public ResultImpl getTaskById(Long id){
        Tasks task = taskDAO.getTaskById(id);
        if (task != null){
            result.setIsSuccessful(true);
            result.setObject(task);
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.taskRetrievalFailed"));
        }
        return result;
    }

    @Transactional (readOnly = false)
    public Tasks setTaskPriority(Tasks task, String priority, UserWrapper wrapper){
        if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
            task.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
            task.setUpdatedDate(new Date());
        }
        task.setPriority(priority);
        return task;
    }

    @Transactional(readOnly = false) //queued, to be made like changeTaskPriority(Long id, String priority)
    public ResultImpl moveTask(Long taskId, Long initialParentBoxId, Long destinationParentBoxId, UserWrapper wrapper){
        Tasks task = taskDAO.getTaskById(taskId);
        Boxes newParentBox = boxDAO.getBoxById(destinationParentBoxId);
        task.setParentBox(newParentBox);//queued
        if(ValidationUtility.isExists(wrapper.getUpdatedBy())){
            task.setUpdatedBy(Long.valueOf(wrapper.getUpdatedBy()));
            task.setUpdatedDate(new Date());
        }
        if(task != null){
            result.setObject(task);
            result.setIsSuccessful(true);
            result.setMessageList(Arrays.asList("success.taskMoved"));
            System.out.println("parent of task changed to box having id ====== "+task.getParentBox().getId() );
        }else{
            result.setIsSuccessful(false);
            result.setMessageList(Arrays.asList("error.taskMoveFailure"));
        }
        return result;
    }

    @Transactional
    public ResultImpl getAttachmentById(Long id){
        Attachment attachment = taskDAO.getAttachmentById(id);
        if (attachment != null){
            result.setIsSuccessful(true);
            result.setObject(attachment);
            //success message not needed to show on every retrieval
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.attachmentRetrievalFailed"));
        }
        return result;
    }

    @Transactional(readOnly = false)
    public Tasks setParent(Tasks task, Boxes parentBox){
        task.setParentBox(parentBox);
        return task;
    }
    
    
}
