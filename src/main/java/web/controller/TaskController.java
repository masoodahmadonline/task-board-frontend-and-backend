/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.entity.Attachment;
import web.entity.Boxes;
import web.entity.Tasks;
import web.service.TasksService;
import web.service.BoxesService;
import web.service.UsersService;
import web.service.common.Result;
import web.wrapper.UserWrapper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

/**
 *
 * @author syncsys
 */

@Controller
public class TaskController {
    @Autowired
    private TasksService taskService;
    @Autowired
    private BoxesService boxService;
    @Autowired
    private UsersService userService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Result result;
    
    
    //ajax
    @RequestMapping (value = "/task/create/{parentBoxId}/{taskTitle}/{taskDescription}", method=RequestMethod.GET)
    public @ResponseBody  Tasks createTask(ModelMap model,
                                          @PathVariable(value="parentBoxId") String parentBoxId,
                                          @PathVariable(value="taskTitle") String taskTitle,
                                          @PathVariable(value="taskDescription") String taskDescription

                                          ){
        
        //queue
        //get user id from session (save id in session first)
        //verify for privs of user if he can create box or not.

        Tasks taskToBeReturned = null;
        Tasks task = new Tasks();
        task.setTitle(taskTitle);
        task.setDescription(taskDescription);
        Boxes parentBox = (Boxes)( boxService.getBoxById(Long.valueOf(parentBoxId)) ).getObject();
        taskService.setParent(task, parentBox);
        result = taskService.save(task);
        if(result.getIsSuccessful()){
            model.put("successMessages", result.getMessageList());
            Tasks savedTask = (Tasks)result.getObject();
            System.out.println("box saved title was " + savedTask.getTitle());
            taskToBeReturned = new Tasks();
            taskToBeReturned.setTitle(savedTask.getTitle());
            taskToBeReturned.setId(savedTask.getId());
            taskToBeReturned.setDescription(savedTask.getDescription());
        }else{
            model.put("errorMessages", result.getMessageList());
        }
        return taskToBeReturned; //queued - send model message also (if needed)
    }
    
    //ajax
    @RequestMapping (value = "/task/delete/{taskId}", method=RequestMethod.GET)
    public @ResponseBody  String deleteTask(ModelMap model,
                                          @PathVariable(value="taskId") String taskId
                                          ){
        System.out.println("task delete controller method called.");
        result = taskService.deleteTask(Long.parseLong(taskId));
        if (result.getIsSuccessful()) {
            model.put("successMessages", result.getMessageList());
            System.out.println("task deleted ------------------");
            return "success";//queued - send model message also (if needed)
        }else{
            System.out.println("task deletion failed ---------------");
            model.put("errorMessages", result.getMessageList());
            return "failure";//queued - send model message also (if needed)
        }
    }

    //ajax
    @RequestMapping (value = "/task/set-priority/{taskId}/{priority}", method=RequestMethod.GET)
    public @ResponseBody  String setPriority(ModelMap model,
                                            @PathVariable(value="taskId") String taskId,
                                            @PathVariable(value="priority") String priority
    ){
        System.out.println("task priority controller method called.");
        result = taskService.changeTaskPriority(Long.parseLong(taskId), priority);
        if (result.getIsSuccessful()) {
            model.put("successMessages", result.getMessageList());
            System.out.println("task priority changed ------------------");
            return "success";
        }else{
            model.put("errorMessages", result.getMessageList());
            System.out.println("result for ask priority change was false");
            return "failure";
        }
        //queued - send model message also (if needed)
    }

    //ajax
    @RequestMapping (value = "/task/move/{taskId}/{initialParentBoxId}/{destinationParentBoxId}", method=RequestMethod.GET)
    public @ResponseBody  String moveTask(ModelMap model,
                                           @PathVariable(value="taskId") String taskId,
                                           @PathVariable(value="initialParentBoxId") String initialParentBoxId,
                                           @PathVariable(value="destinationParentBoxId") String destinationParentBoxId
                                        ){

        System.out.println("task delete controller method called.");
        result = taskService.moveTask(Long.parseLong(taskId), Long.parseLong(initialParentBoxId), Long.parseLong(destinationParentBoxId));

        Tasks savedTask = (Tasks)result.getObject();
//        Tasks taskToBeReturned = new Tasks();
//        taskToBeReturned.setTitle(savedTask.getTitle());
//        taskToBeReturned.setId(savedTask.getId());
//        taskToBeReturned.setDescription(savedTask.getDescription());
//        taskToBeReturned.setParentBox(savedTask.getParentBox());
        if (result.getIsSuccessful()) {
            System.out.println("task moved ------------------");
            model.put("successMessages", result.getMessageList());
            return "success";
        }else{
            System.out.println("task move failuer --------------");
            model.put("errorMessages", result.getMessageList());
            return "failure";
        }
        //queued - send model message also (if needed)
    }

    @RequestMapping (value = "/task/attach-file", method=RequestMethod.POST)
    public String uploadFile( HttpSession session,
                            @RequestParam("taskIdForFileUpload") String taskIdForFileUpload,
                            @RequestParam("fileDescription") String fileDescription,
                            @RequestParam("file") MultipartFile file
        ) throws IOException {
        System.out.println("taskId is: "+taskIdForFileUpload+" and file name and size are: "+file.getName()+ " " +file.getSize()/1024+ " kb, content type: " +file.getContentType() + file.getOriginalFilename() + "THe description is: " +fileDescription);
        String filePathToSave =  "/attachments/"+taskIdForFileUpload+"/"+file.getOriginalFilename();
        FileUtils.writeByteArrayToFile(new File(filePathToSave), file.getBytes());
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize() / 1024);//convert to kb instead of bytes
        attachment.setPath(filePathToSave);
        attachment.setDescription(fileDescription);
        Long taskId = Long.parseLong(taskIdForFileUpload);
        System.out.println("service call param for task: ===========" +taskId);
        result = taskService.saveAttachment(taskId, attachment);
        if (result.getIsSuccessful()){
            Attachment savedAttachment = (Attachment)result.getObject();
            System.out.println("========== attachment details ======= "+
                    savedAttachment.getName()       +
                    savedAttachment.getSize()       +
                    savedAttachment.getId()         +
                    savedAttachment.getParentTask() +
                    savedAttachment.getPath()
            );
        }
        System.out.println(session.getAttribute("previous_page").toString());
        return "redirect:"+session.getAttribute("previous_page").toString();
        //queued - send model message also (if needed)
    }

    @RequestMapping (value = "/attachment/download/{attachmentId}", method=RequestMethod.GET)
    public String downloadFile( HttpServletResponse response,
                                @PathVariable(value = "attachmentId")Long attachmentId


    )throws IOException{
        result = taskService.getAttachmentById(attachmentId);
        if(result.getIsSuccessful()){
            Attachment attachment = (Attachment)result.getObject();
            File file = new File(attachment.getPath());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            byte[] ba = FileUtils.readFileToByteArray(file);
            response.getOutputStream().write(ba);
        }
        return null;
        //queued - send model message also (if needed)
    }

    //ajax
    @RequestMapping (value = "/attachment/delete/{attachmentId}", method=RequestMethod.GET)
    public @ResponseBody  String deleteAttachment(ModelMap model,
                                          @PathVariable(value="attachmentId") Long attachmentId ){
        result =  taskService.deleteAttachment(attachmentId);
        if(result.getIsSuccessful()){
            System.out.println("attachment deleted ==========");
            return "success";
        }else{
            System.out.println("attachment NOT deleted ==========");
            return "failure";
        }
    } //queued - send model message also (if needed)


    @RequestMapping (value = "/task/assign-task", method=RequestMethod.POST)
    public String taskAssignment( HttpSession session, @ModelAttribute("uWrapper")UserWrapper userWrapper, ModelMap model) throws IOException {

        System.out.println("\n Task Id after POST method (for user assignment): "+userWrapper.getTaskId()+"\n");
        result = userService.taskAssignment(userWrapper);
        System.out.println("\n size of user's list: "+userWrapper.getUserList().size()+"\n");
        if(result.getIsSuccessful()){
            model.put("error", false);
            model.put("success", true);
            model.put("successMsg", result.getMessage());
            System.out.println("\n********** Success message from controller ***************\n");
        }else{
            model.put("errorMsg", result.getMessage());
            System.out.println("\n********** error message from controller ***************\n");
        }
        return "redirect:"+session.getAttribute("previous_page").toString();
    }

}



//    @RequestMapping (value = "/attachment/download")
//    public String downloadFileTemp ( HttpSession session,  HttpServletResponse response
//
//
//    )throws IOException{
//
//        File file = new File("/attachments/244/irc.png");
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
//        byte[] ba = FileUtils.readFileToByteArray(file);
//        response.getOutputStream().write(ba);
////        response.setHeader("Content-Disposition","attachment; filename=\yourData.csv\"");
////        OutputStream resOs= response.getOutputStream();
////        OutputStream buffOs= new BufferedOutputStream(resOs);
////        OutputStreamWriter outputwriter = new OutputStreamWriter(buffOs);
////
////        CsvWriter writer = new CsvWriter(outputwriter, '\u0009');
////        for(int i=1;i <allRecords.size();i++){
////            CompositeRequirement aReq=allRecords.get(i);
////            writer.write(aReq.toString());
////        }
////        outputwriter.flush();
////        outputwriter.close();
//        return null;
//    }