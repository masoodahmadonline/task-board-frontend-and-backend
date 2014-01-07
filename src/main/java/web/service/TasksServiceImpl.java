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
import web.entity.Attachment;
import web.entity.Boxes;
import web.entity.Tasks;
import web.service.common.ResultImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TasksServiceImpl implements TasksService{
    
    @Autowired
    private BoxesDAO boxDAO;
    @Autowired
    private TasksDAO taskDAO;
    @Autowired
    private ResultImpl result;
    
   
    
    @Transactional(readOnly = false)
    public ResultImpl save(Tasks task){
        /////////////////// queue , check if named box already exists or not?
        Tasks taskToBeReturned = taskDAO.save(task);
        if(taskToBeReturned == null){
                result.setIsSuccessful(false);
                result.setObject(null);
                result.setMessageList(Arrays.asList("error.boardCreationErrorUnknown"/*,"string"*/));  //queue change
                return result;
        }else{
            result.setIsSuccessful(true);
            result.setObject(taskToBeReturned);
            result.setMessageList(Arrays.asList("success.boardCreated"/*,"string"*/));    //queue change
            return result;
        }


    }

    @Transactional(readOnly = false)
    public ResultImpl saveAttachment(Long taskId, Attachment attachment){
        //get task by id
        Tasks task = taskDAO.getTaskById(taskId);
        //save attachment while making the task as its parent
        Attachment savedAttachment = taskDAO.saveAttachment(attachment);
        savedAttachment.setParentTask(task);
        if(savedAttachment == null){
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.boardCreationErrorUnknown"/*,"string"*/));  //queue change
            return result;
        }else{
            result.setIsSuccessful(true);
            result.setObject(savedAttachment);
            result.setMessageList(Arrays.asList("success.boardCreated"/*,"string"*/));    //queue change
            return result;
        }



    }
    
    @Transactional(readOnly = false)
    public ResultImpl deleteTask(Long id){
        if(taskDAO.deleteTask(id)){
            result.setIsSuccessful(true);
        }else{
            result.setIsSuccessful(false);
        }

             return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl deleteAttachment(Long id){
        if(taskDAO.deleteAttachment(id)){
            result.setIsSuccessful(true);
        }else{
            result.setIsSuccessful(false);
        }

        return result;
    }

    @Transactional
    public ResultImpl changeTaskPriority(Long id, String priority){
        Tasks task = taskDAO.getTaskById(id);
        task.setPriority(priority);
        if(task.getPriority() != null){
            result.setIsSuccessful(true);
            result.setObject(task);
        }else{
            result.setIsSuccessful(false);
        }
        return result;
    }


    @Transactional(readOnly = false)
    public ResultImpl moveTask(Long taskId, Long initialParentBoxId, Long destinationParentBoxId){
        Tasks task = taskDAO.getTaskById(taskId);
        Boxes newParentBox = boxDAO.getBoxById(destinationParentBoxId);
        task.setParentBox(newParentBox);
        if(task != null){
            result.setObject(task);
            result.setIsSuccessful(true);
            System.out.println("parent of task changed to box having id ============== "+task.getParentBox().getId() );
        }else{
            result.setIsSuccessful(false);
        }

        return result;
    }

    @Transactional(readOnly = false)
    public ResultImpl getTaskById(Long id){
        Tasks task = taskDAO.getTaskById(id);
        if (task != null){
            result.setIsSuccessful(true);
            result.setObject(task);
            //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.boardCreationErrorUnknown"/*,"string"*/));
        }
        return result;
        
    }

    @Transactional(readOnly = false)
    public ResultImpl getAttachmentById(Long id){
        Attachment attachment = taskDAO.getAttachmentById(id);
        if (attachment != null){
            result.setIsSuccessful(true);
            result.setObject(attachment);
            //result.setMessageList(Arrays.asList("error.userCreationErrorUnknown"/*,"string"*/));
        }else{
            result.setIsSuccessful(false);
            result.setObject(null);
            result.setMessageList(Arrays.asList("error.boardCreationErrorUnknown"/*,"string"*/));
        }
        return result;

    }



    @Transactional(readOnly = false)
    public Tasks setParent(Tasks task, Boxes parentBox){
        task.setParentBox(parentBox);
        return task;
    }
    
    
}
