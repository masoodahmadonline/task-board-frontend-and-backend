/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.controller;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.entity.Attachment;
import web.entity.Boxes;
import web.entity.Tasks;
import web.entity.Users;
import web.service.TasksService;
import web.service.BoxesService;
import web.service.UsersService;
import web.service.common.Result;
import web.service.common.ValidationUtility;
import web.wrapper.UserWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    @PreAuthorize("@securityService.hasBoxTaskEditPermission(#parentBoxId)")
    @RequestMapping (value = "/task/create/{parentBoxId}/{taskTitle}/{taskDescription}", method=RequestMethod.GET)
    public @ResponseBody  Tasks createTask(ModelMap model,
                                          @PathVariable(value="parentBoxId") String parentBoxId,
                                          @PathVariable(value="taskTitle") String taskTitle,
                                          @PathVariable(value="taskDescription") String taskDescription

                                          ){

        //queue
        //get user id from session (save id in session first)
        //verify for privs of user if he can create box or not.
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername(); //get logged in username id
        result = userService.getUserByLoginId(loginId);
        Users user = (Users)result.getObject();

        Tasks taskToBeReturned = null;
        Tasks task = new Tasks();
        task.setTitle(taskTitle);
        task.setDescription(taskDescription);
        task.setCreater(user);
        task.setStatus("new");
        task.setPriority("normal");
        task.setCreationDateTime(new Date());
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
    @RequestMapping (value = "/task/edit/{taskId}/{taskTitle}/{taskDescription}", method=RequestMethod.GET)
    public @ResponseBody  boolean editTask(ModelMap model,
                                          @PathVariable(value="taskId") String taskId,
                                          @PathVariable(value="taskTitle") String taskTitle,
                                          @PathVariable(value="taskDescription") String taskDescription
    ){


        result = taskService.editTask(Long.valueOf(taskId), taskTitle, taskDescription);
        if(result.getIsSuccessful()){
            return true;
        }else{
            return false;
        }

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
    @PreAuthorize("@securityService.hasBoardEditPermission(#boardId)")
    @RequestMapping (value = "/task/set-priority/{boardId}/{taskId}/{priority}", method=RequestMethod.GET)
    public @ResponseBody  String setPriority(ModelMap model,
                                            @PathVariable(value="boardId") String boardId,
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
    @PreAuthorize("@securityService.hasBoardEditPermission(#boardId)")
    @RequestMapping (value = "/task/set-status/{boardId}/{taskId}/{status}", method=RequestMethod.GET)
    public @ResponseBody  String setStatus(ModelMap model,
                                           @PathVariable(value="boardId") String boardId,
                                           @PathVariable(value="taskId") String taskId,
                                           @PathVariable(value="status") String status
    ){
        System.out.println("task status controller method called.");
        result = taskService.changeTaskStatus(Long.parseLong(taskId), status);
        if (result.getIsSuccessful()) {
            model.put("successMessages", result.getMessageList());
            System.out.println("task status changed ------------------");
            return "success";
        }else{
            model.put("errorMessages", result.getMessageList());
            System.out.println("result for ask status change was false");
            return "failure";
        }
        //queued - send model message also (if needed)
    }

    //ajax
    @PreAuthorize("@securityService.hasBoxTaskEditPermission(#initialParentBoxId)")
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

    //ajax
    @PreAuthorize("@securityService.hasBoardEditPermission(#boardId)")
    @RequestMapping (value = "/task/assign/{boardId}/{taskId}", method= RequestMethod.GET)
    public @ResponseBody List<UserWrapper> assignTask(ModelMap model, @PathVariable(value="boardId") String boardId, @PathVariable(value="taskId") String taskId){
        System.out.println("task assign controller method called.");
        User springUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        String companyId = null;
        companyId = userService.getCompanyId(loginId);

        if(ValidationUtility.isExists(taskId) && ValidationUtility.isExists(boardId)){
            result = userService.getTaskUsersListAll(Long.valueOf(boardId), Long.valueOf(taskId), companyId);
        }
        for(UserWrapper wr : (List<UserWrapper>) result.getObject()){
            System.out.println("User enabled : " + wr.isEnableUserAssignId());
        }
        return (List<UserWrapper>) result.getObject();
    }

    @PreAuthorize("@securityService.hasBoardEditPermission(#bId)")
    @RequestMapping (value = "/task/assign-task/{bId}/{tId}", method=RequestMethod.POST)
    public @ResponseBody Result taskAssignment(HttpServletRequest request, ModelMap model, @PathVariable(value="bId") String bId,
                                 @PathVariable(value="tId") String tId) throws IOException {
        System.out.println("\n Task Id after POST method (for user assignment) : "+tId+"\n");
        UserWrapper wrapper = new UserWrapper();
        List<UserWrapper> userList = new ArrayList<UserWrapper>();
        String[] names2 = request.getParameterValues("ulist");
        List<String> lList = Arrays.asList(names2);
        for (String s : lList) {
            JSONParser parser=new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(s);
                JSONArray array = (JSONArray)obj;
                UserWrapper wrapper1 = null;
                for(int i = 0; i < array.size(); i++){
                    wrapper1 = new UserWrapper();
                    JSONObject jObject = (JSONObject)array.get(i);
                    Set elementNames = jObject.keySet();
                    Iterator iter = elementNames.iterator();
                    while(iter.hasNext()){
                        Object ob = iter.next();
                        System.out.println("Key:" + ob + "Value:" + jObject.get(ob));
                        if(ob.equals("userId")){
                            wrapper1.setUserId("" + jObject.get(ob));
                        }
                        if(ob.equals("enableUserAssignId")){
                            wrapper1.setEnableUserAssignId((Boolean) jObject.get(ob));
                        }
                        userList.add(wrapper1);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        wrapper.setTaskId(tId);
        wrapper.setBoardId(bId);
        wrapper.setUserList(userList);
        User springUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = springUser.getUsername();
        loginId = userService.getUserId(loginId);
        wrapper.setCreatedBy(loginId);
        wrapper.setUpdatedBy(loginId);
        result = userService.taskAssignment(wrapper);
        //System.out.println("\n size of user's list: "+ulist.size()+"\n");
        /*if(result.getIsSuccessful()){
            model.put("error", false);
            model.put("success", true);
            model.put("successMsg", result.getMessage());
            //model.put("successMessages", result.getMessageList());
            System.out.println("\n********** Success message from controller ***************\n");

        }else{
            model.put("error", true);
            model.put("success", false);
            model.put("errorMsg", result.getMessage());
            model.put("errorMessages", result.getMessageList());
            System.out.println("\n********** error message from controller ***************\n");
        }*/
        //return "redirect:"+session.getAttribute("previous_page").toString();
        return result;
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