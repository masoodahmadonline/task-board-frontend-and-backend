<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springform"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%--test--%>
<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    ${board.title}
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body onload="setVariables();">
<c:import url="${mainDir}/common/inner-header.jsp" />

<style>
    .ui-tooltip, .arrow:after {
        background: #e4e8ff;
        border: 2px solid #d3d3d3;
    }
    .ui-tooltip {
        padding: 10px 20px;
        color: black;
        border-radius: 20px;
        /*font: bold 14px "Helvetica Neue", Sans-Serif;*/
        box-shadow: 0 0 7px black;
    }
    .arrow {
        width: 70px;
        height: 16px;
        overflow: hidden;
        position: absolute;
        left: 50%;
        margin-left: -35px;
        bottom: -16px;
    }
    .arrow.top {
        top: -16px;
        bottom: auto;
    }
    .arrow.left {
        left: 20%;
    }
    .arrow:after {
        content: "";
        position: absolute;
        left: 20px;
        top: -20px;
        width: 25px;
        height: 25px;
        box-shadow: 6px 5px 9px -9px black;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        tranform: rotate(45deg);
    }
    .arrow.top:after {
        bottom: -20px;
        top: auto;
    }
</style>

<script type="text/javascript">

    var boardIdValue;
    var totalCountValue;

    function setVariables() {
        boardIdValue = "${boardWrapper.boardId}";
        totalCountValue = "${boardWrapper.totalCount}";
    }

    window.setInterval(function () {
        ShowDialog();
    }, 20000);
    function ShowDialog() {
        var obj = {};
        var boardId = "boardId";
        var totalCount = "totalCount";
        obj[boardId] = boardIdValue;
        obj[totalCount] = totalCountValue;
        var objList = [];
        objList.push(obj);

        $.ajax({
            url: "${pageContext.request.contextPath}/update-board",
            type:"POST",
            cache: false,
            data : {
                bWrapper : JSON.stringify(objList)
            },
            async: false,
            success: function(response){
                boardIdValue = response.boardId;
                //totalCountValue = response.totalCount;
                if(response.boardUpdateRequired){
                    showBoardUpdateMessage(response.message);
                }else{
                    //showErrorMessage(response.message);
                }
            },
            error: function(){
                showBoardUpdateMessage("Board updates not available at the moment!");
            }
        });
    };
function showBoardUpdateMessage(message) {
    $("#board-updated-box").addClass("board-message");
    $("#board-updated-box").html(message);
    /*$("#board-updated-box").append("<a href='#' onClick='location.reload();'>myString</a>");
    $("#board-updated-box").append(document.create);*/

}

function showSuccessMessage(message) {
    $("#message-box").addClass("success-message");
    $("#message-box").html(message);
    $("#message-box").effect( "highlight",
            {color:"#669966"}, 1000 );

}
function showErrorMessage(message) {
    $("#message-box").addClass("error-message");
    $("#message-box").html(message);
    $("#message-box").effect( "highlight",
            {color:"#669966"}, 1000 );

}

function ajaxCreateBox(parent){

    //alert("parent of box is: "+ parent.attr("id"));
    var parentElementId =   parent.attr("id");
    var parentId =  parentElementId.split("-")[1];
    var parentType;
    if (parentElementId.split("-")[0] == "boardid"){ parentType = "board";}
    if (parentElementId.split("-")[0] == "boxid"){ parentType = "box";}

    var boardLogId = "${board.id}";
    var boxType = $('input[name="box-creation-form-type"]:checked').val();
    var boxTitle = $("#box-creation-form-title").val();
    var boxDescription = $("#box-creation-form-description").val();
    if(boxTitle.length > 0 && boxDescription.length > 0){
        $.ajax({
            url: "${pageContext.request.contextPath}/box/create/"+boardLogId+"/"+parentType+"/"+parentId+"/"+boxType+"/"+boxTitle+"/"+boxDescription,
            cache: false,
            //data:'firstName=' + $("#firstName").val() + "&lastName=" + $("#lastName").val() + "&email=" + $("#email").val(),
            success: function(response){
                var fetchedBoxId = response.id;
                var fetchedBoxType = response.type;
                var fetchedBoxTitle = response.title;
                var fetchedBoxDescription = response.description;
                //alert(fetchedBoxType+" "+fetchedBoxTitle+" "+fetchedBoxDescription);
                //$('#result').html(fetchedBoxId+" "+fetchedBoxType+" "+fetchedBoxTitle+" "+fetchedBoxDescription);
                // createBoxInDom();
                $("#"+parentElementId+" > ."+parentType+"-body").append(
                        '<div id="boxid-'+fetchedBoxId+'" class="box box-'+fetchedBoxType+'" >'+
                                '    <div class="box-title" title="'+fetchedBoxDescription+'">'+
                                '          <span>'+
                                '                <div class="drop-menu-button">&#x25be; &nbsp;'+
                                '                    <ul class="drop-menu-options">'+
                                '                              <li style="width: auto;"><a href="#" class="create-task-wizard">Create a task</a></li>'+
                                '                              <li style="width: auto;"><a href="#" class="create-box-wizard">Create child box</a></li>'+
                                '                              <li style="width: auto;"><a href="#" class="edit-box-wizard">Edit this box</a></li>'+
                                '                              <li><a href="#" class="delete-box-wizard">Delete this box</a></li>'+
                                '                      </ul>'+
                                '                  </div>'+
                                '                <span class="box-title-text">'+fetchedBoxTitle+'</span><span class="box-description-text" style="display:none;">'+fetchedBoxDescription+'</span>'+
                                '            </span> '+
                                '        </div>'+
                                '        <div class="box-body">'+
                                '        </div>   '+
                                '</div>');

                $('#boxid-'+fetchedBoxId+' .drop-menu-options').menu().hide();
                $('#boxid-'+fetchedBoxId+' .delete-box-wizard').click(function () {
                    ajaxDeleteBox(     $(this).parents(".box").first()      );
                });
                showSuccessMessage('Box created successfully!');


            },
            error: function(){
                showErrorMessage('There was a problem creating Box. Check internet connectivity and access rights');
            }


        });
        $('#box-creation-form').find("input[type=text], textarea, checkbox").val("");
        $( "#box-creation-form" ).dialog( "close" );
    }


}

function ajaxCreateTask(parentBox){
    console.log('boo');
    var boardLogId = "${board.id}";
    var parentBoxId = parentBox.attr('id').split("-")[1];
    var taskTitle = $("#task-creation-form-title").val();
    var taskDescription = $("#task-creation-form-description").val();
    if(taskTitle.length > 0 && taskDescription.length > 0){
        $( "#task-creation-form" ).dialog( "close" );
        $.ajax({
            url: "${pageContext.request.contextPath}/task/create/"+boardLogId+"/"+parentBoxId+"/"+taskTitle+"/"+taskDescription,
            cache: false,

            success: function(response){
                var fetchedTaskId = response.id;
                var fetchedTaskTitle = response.title;
                var fetchedTaskDescription = response.description;

                //$('#result').html(fetchedTaskId+"  "+fetchedTaskTitle+" "+fetchedTaskDescription);
                // createBoxInDom();
                $("#boxid-"+parentBoxId+" .box-body").append(
                        '<div class="task tooltip" id="taskid-'+fetchedTaskId+'" title="Title: '+fetchedTaskTitle+' <br /><br />Description: '+fetchedTaskDescription+'">' +
                                '<div class="task-title">' +
                                '<span> '+
                                '    <div class="drop-menu-button">&#x25be;&nbsp; '+
                                '        <ul class="drop-menu-options">'+
                                '                <li><a href="#" class="task-assign-unassign-wizard">Assign/Unassign</a></li>'+
                                '                <li><a href="#" class="file-attachment-wizard">Attach file</a></li> '+
                                '                <li><a href="#" class="edit-task-wizard">Edit this task</a></li> '+
                                '                <li><a href="#">Set priority</a> '+
                                '                    <ul>  '+
                                '                        <li><a href="#" class="task-priority-critical-button">Critical</a></li> '+
                                '                        <li><a href="#" class="task-priority-high-button">High</a></li>'+
                                '                        <li><a href="#" class="task-priority-normal-button">Normal</a></li> '+
                                '                        <li><a href="#" class="task-priority-low-button">Low</a></li> '+
                                '                    </ul> '+
                                '                </li> '+
                                '                <li><a href="#">Set status</a> '+
                                '                    <ul>  '+
                                '                        <li><a href="#" class="task-status-new-button">New</a></li> '+
                                '                        <li><a href="#" class="task-status-in-process-button">In-process</a></li>'+
                                '                        <li><a href="#" class="task-status-in-issues-button">In-issues</a></li> '+
                                '                        <li><a href="#" class="task-status-completed-button">Completed</a></li> '+
                                '                    </ul> '+
                                '                </li> '+
                                '                <li><a href="#" class="delete-task-wizard">Delete this task</a></li>'+
                                '        </ul>'+
                                '    </div>   '+
                                '<span class="task-title-text">'+fetchedTaskTitle+'</span>'+
                                '</span>      '+
                                '</div>' +
                                '<div class="task-body">' +
                                '<span class="task-description-text">'+fetchedTaskDescription+'</span>'+
                                '   <div class="task-priority task-priority-normal task-status-new"></div>'+
                                '</div>' +
                                '' +
                                '</div>'
                );
                $('#taskid-'+fetchedTaskId+' .drop-menu-options').menu().hide();
                $('#taskid-'+fetchedTaskId+' .delete-task-wizard').click(function () {
                    ajaxDeleteTask(     $(this).parents(".task").first()      );
                });
                $('#taskid-'+fetchedTaskId+' .task-assign-unassign-wizard').click(function () {
                    $('#taskIdForAssignUser').val(  $(this).parents(".task").first().attr('id').split("-")[1] );
                    $('#boardIdForAssignUser').val(  $(this).parents(".board").first().attr('id').split("-")[1] );
                    ajaxAssignTask($(this).parents(".board").first(), $(this).parents(".task").first());
                    //$("#task-assign-unassign-form").dialog("open");
                    //window["parentTask"] = $(this).parents(".task").first();
                });

                $("#taskid-"+fetchedTaskId+"").tooltip({
                    content: function() {
                        return $(this).attr('title');
                    }
                });

            },
            error: function(){
                showErrorMessage('There is some problem during task assignment. Please check internet connectivity and access rights');
            }

        });

        $('#task-creation-form').find("input[type=text], textarea, checkbox").val("");

    }
}



function ajaxDeleteBox(box){
    var boxId = box.attr('id').split("-")[1];
    var boardLogId = "${board.id}";
    //alert("box id to be sent for deletion by ajax call: "+boxId);

    $.ajax({

        url: "${pageContext.request.contextPath}/box/delete/"+boardLogId+"/"+boxId,

        cache: false,

        success: function(response){
            box.remove();
            showSuccessMessage('Box deleted successfully!');
        },
        error: function(){
            showErrorMessage('There was a problem deleting Box. Check internet connectivity and access rights');
        }

    });
}


function ajaxEditBox(box, boxType, boxTitle, boxDescription, success, error){
    var boxId = box.attr('id').split("-")[1];
    var boardLogId = "${board.id}";
    //alert("box id to be sent to edit by ajax call: "+boxId +" type:"+boxType+" title:"+boxTitle+" desc"+boxDescription);

    $.ajax({
        url: "${pageContext.request.contextPath}/box/edit/"+boardLogId+"/"+boxId+"/"+boxType+"/"+boxTitle+"/"+boxDescription,
        cache: false,
        success: success,
        error: error
    });
}

function ajaxEditTask(task, taskTitle, taskDescription, success, error){
    var taskId = task.attr('id').split("-")[1];
    var boardLogId = "${board.id}";
    //alert("task id to be sent to edit by ajax call: "+taskId +" title:"+taskTitle+" desc"+taskDescription);

    $.ajax({
        url: "${pageContext.request.contextPath}/task/edit/"+boardLogId+"/"+taskId+"/"+taskTitle+"/"+taskDescription,
        cache: false,
        success: success,
        error: error
    });
}



function ajaxDeleteTask(task){
    var taskId = task.attr('id').split("-")[1];
    var boardLogId = "${board.id}";
    $.ajax({
        url: "${pageContext.request.contextPath}/task/delete/"+boardLogId+"/"+taskId,
        cache: false,
        success: function(response){
            task.remove();
            showSuccessMessage('Task deleted successfully!');
        },
        error: function(){
            showErrorMessage('There was a problem deleting Task. Check internet connectivity and access rights');
        }
    });
}

function ajaxChangeTaskPriority(boardId, taskId, priority, success, error){
    console.log("task id to be changed for setting priority: "+taskId);
    $.ajax({
        url: "${pageContext.request.contextPath}/task/set-priority/"+boardId+"/"+taskId+"/"+priority,
        cache: false,
        success: success,
        error: error
    });
}

function ajaxChangeTaskStatus(boardId, taskId, status, success, error){
    console.log("task id to be changed for setting status: "+taskId);
    $.ajax({
        url: "${pageContext.request.contextPath}/task/set-status/"+boardId+"/"+taskId+"/"+status,
        cache: false,
        success: success,
        error: error
    });
}

function ajaxMoveTask(taskId, initialParentBoxId, destinationParentBoxId, success, error){
    var boardLogId = "${board.id}";
//                var taskId = task.attr('id').split("-")[1];
//                var initialParentBox = task.parents(".box").first();
//                var initialParentBoxId = initialParentBox.attr('id').split("-")[1];

    console.log("task id to be sent for movement by ajax call: "+taskId);

    $.ajax({

        url: "${pageContext.request.contextPath}/task/move/"+boardLogId+"/"+taskId+"/"+initialParentBoxId+"/"+destinationParentBoxId,

        cache: false,
        success: success,
        error: error
    });
}

function ajaxDeleteAttachment(attachmentId, success, error){
    var boardLogId = "${board.id}";
    //                var taskId = task.attr('id').split("-")[1];
    //                var initialParentBox = task.parents(".box").first();
    //                var initialParentBoxId = initialParentBox.attr('id').split("-")[1];

    console.log("attachmentId id to be sent for deletion by ajax call: "+attachmentId);
    $.ajax({
        url: "${pageContext.request.contextPath}/attachment/delete/"+boardLogId+"/"+attachmentId,
        cache: false,
        success: success,
        error: error

    });
}

$(function() {
    //wizard
    //form
    //wizard-submit

    ////////////////////////// box creation ///////////////////////////////
    $( "#box-creation-form" ).dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });



    $(".delete-box-wizard").click(function () {
        ajaxDeleteBox(     $(this).parents(".box").first()      );
    });

    $(".create-box-wizard-submit").click(function () {
        //$( "#box-creation-form" ).dialog( "close" );
    });



    ////////////////////////// box editing ///////////////////////////////
    $( "#box-editing-form" ).dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });





    $(".edit-box-wizard-submit").click(function () {

        var boxType = $('input[name="box-editing-form-type"]:checked').val();
        var boxTitle = $("#box-editing-form-title").val();
        var boxDescription = $("#box-editing-form-description").val();
        console.log("type:"+boxType+" title:"+boxTitle+" desc"+boxDescription)
        ajaxEditBox(window.box, boxType, boxTitle, boxDescription, function(){
            //alert("boo");
            $(window.box).removeClass("box-vertical");
            $(window.box).removeClass("box-horizontal");
            $(window.box).addClass("box-"+boxType);
            $(window.box).find(".box-title-text").first().html(boxTitle);
                    //description change/edit is pending.


        },
        function(){
            //alert("ooh");
        });
        $( "#box-editing-form" ).dialog( "close" );
    });


    ////////////////////////// task editing ///////////////////////////////
    $( "#task-editing-form" ).dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });





    $(".edit-task-wizard-submit").click(function () {


        var taskTitle = $("#task-editing-form-title").val();
        var taskDescription = $("#task-editing-form-description").val();
        console.log( "title:"+taskTitle+" desc"+taskDescription)
        ajaxEditTask(window.task, taskTitle, taskDescription, function(){
                    //alert("boo");

                    $(window.task).find(".task-title-text").first().html(taskTitle);
                    $(window.task).find(".task-description-text").first().html(taskDescription);
                    showSuccessMessage('Task edited successfully!');

                },
                function(){
                    showErrorMessage('Error editing task. Check internet connectivity and access rights.');
                });
        $( "#task-editing-form" ).dialog( "close" );
    });


    ////////////////////////// task creation ///////////////////////////////
    $( "#task-creation-form" ).dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });

//    $(".create-task-wizard").click(function () {      //created document.on(click, ... function at doc.ready at bottom
//        $( "#task-creation-form").dialog( "open" );
//        window["parentBox"] =  $(this).parents(".box").first() ;
//    });

    $(".delete-task-wizard").click(function () {

        ajaxDeleteTask(     $(this).parents(".task").first()      );

    });

    $(".create-task-wizard-submit").click(function () {
        //$( "#task-creation-form" ).dialog( "close" );

    });






    ////////////////////////// attachment creation ///////////////////////////////
    $("#file-attachment-form").dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });



    $(".file-attachment-wizard-submit").click(function () {
        //queue

    });

    $('#file-attachment-inner-form').on('submit', function(event) {
        if(!$('#file').val()){

            alert('Please select a file to attach');
            event.preventDefault();
        }
    });





    ////////////////////////// task assigning ///////////////////////////////
    $("#task-assign-unassign-form").dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });

    $(".task-assign-unassign-wizard").click(function () {
        $('#taskIdForAssignUser').val(  $(this).parents(".task").first().attr('id').split("-")[1] );
        $('#boardIdForAssignUser').val(  $(this).parents(".board").first().attr('id').split("-")[1] );
        ajaxAssignTask($(this).parents(".board").first(), $(this).parents(".task").first()      );
        //$("#task-assign-unassign-form").dialog("open");
        window["parentTask"] = $(this).parents(".task").first();
        window["parentBoard"] = $(this).parents(".board").first();

    });

    $(".task-assign-unassign-wizard-submit").click(function () {
        ajaxAssignTaskSubmit(window.taskUsersList);


    });





});









</script>

<div id="box-creation-form" class="forms-for-board" title="Create new child box">
    <form >
        <table>
            <tr>
                <td>Box Type to create:</td>
                <td>
                    <input name="box-creation-form-type" value="vertical" type="radio" checked="checked" />Vertical Box
                    <br />
                    <input name="box-creation-form-type" value="horizontal" type="radio" />Horizontal Box
                </td>
            </tr>
            <tr>
                <td>Box title:</td><td><input id="box-creation-form-title" type="text" required="required" />
            </tr>
            <tr>
                <td>Box description</td><td><textarea id="box-creation-form-description" style="min-height: 100px;" required="required"></textarea></td>
            </tr>
            <tr>

                <td><input type="reset" /></td><td><input type="button" value="Create" class="create-box-wizard-submit" onClick="ajaxCreateBox(parentOfBox);" /></td>
            </tr>
        </table>



    </form>
</div>

<div id="box-editing-form" class="forms-for-board" title="Edit this box">
    <form >
        <table>
            <tr>
                <td>Box Type (if changed required):</td>
                <td>
                    <input id="box-editing-form-type-id-vertical" required="required" name="box-editing-form-type" value="vertical" type="radio" checked="checked" />Vertical Box
                    <br />
                    <input id="box-editing-form-type-id-horizontal" required="required" name="box-editing-form-type" value="horizontal" type="radio" />Horizontal Box
                </td>
            </tr>
            <tr>
                <td>Box title:</td><td><input id="box-editing-form-title" required="required" type="text" required="required" />
            </tr>
            <tr>
                <td>Box description</td><td><textarea id="box-editing-form-description" required="required" style="min-height: 100px;" required="required"></textarea></td>
            </tr>
            <tr>

                <td><input type="reset" /></td><td><input type="button" value="Edit" class="edit-box-wizard-submit" /></td>
            </tr>
        </table>



    </form>
</div>


<div id="task-editing-form" class="forms-for-board" title="Edit this task">
    <form >
        <table>

            <tr>
                <td>Task title:</td><td><input id="task-editing-form-title" type="text" required="required" />
            </tr>
            <tr>
                <td>Task description</td><td><textarea id="task-editing-form-description" style="min-height: 100px;" required="required"></textarea></td>
            </tr>
            <tr>

                <td><input type="reset" /></td><td><input type="button" value="Edit" class="edit-task-wizard-submit" /></td>
            </tr>
        </table>



    </form>
</div>

<div id="task-creation-form" class="forms-for-board" title="Create new task">
    <form >
        <table>

            <tr>
                <td>Task title:</td><td><input id="task-creation-form-title" type="text" required="required"  /></td>
            </tr>
            <tr>
                <td>Task description</td><td><textarea id="task-creation-form-description" style="min-height: 100px;" required="required"></textarea></td>
            </tr>
            <tr>
                <td><input type="reset" /></td><td><input type="button" value="Create" class="create-task-wizard-submit" onClick="ajaxCreateTask(window.parentBox);" /></td>
            </tr>
        </table>



    </form>
</div>


<div id="task-assign-unassign-form" class="forms-for-board" style="max-height: 600px; overflow-x: visible;" title="Assign or Unassign this task">
    <form id="live-search" action="" class="styled" method="post" style="text-align: center;">
        <input type="text" class="form-input" style="width: 150px;" id="filter" placeholder="Search" value="" />
        <span id="filter-count"></span>
    </form>
    <form><table><tr><td colspan="2">
        <table id="task-assign-unassign-table" class="usersListClass">

        </table>
        </td></tr><tr><td><input type="hidden" id="taskIdForAssignUser" /><input type="hidden" id="boardIdForAssignUser" /><input type="reset" value="Reset" /></td>
        <td><input type="button" value="Submit" class="task-assign-unassign-wizard-submit" /></td></tr></table>
    </form>
</div>


<div id="file-attachment-form" class="forms-for-board" title="Attach a file to this task">
<form action="${pageContext.request.contextPath}/task/attach-file" method="POST" enctype="multipart/form-data" id="file-attachment-inner-form">
<table>
<input type="file" name="file" id="file">
<tr>
    <td>File description</td>
    <td><textarea id="fileDescription" name="fileDescription" style="min-height: 100px;"></textarea></td>
</tr>
<input id="taskIdForFileUpload" type="hidden" name="taskIdForFileUpload" value="" />
<input id="boardIdForFileUpload" type="hidden" name="boardIdForFileUpload" value="" />
<%--<input id="taskIdForFileUpload" type="hidden" name="taskIdForFileUpload" value="233" />--%>

            <%--<tr>--%>
            <%--<td><input type="file" name="file" id="file"> </td>--%>

            <%--</tr>--%>

            <tr>
                <td><input type="reset"/></td>
                <td><input type="submit" value="Upload" class="file-attachment-wizard-submit"
                <%--onClick="ajaxUploadFile(window.parentTask);"--%>
                        /></td>
            </tr>
        </table>


    </form>
</div>



<div id="result"></div>


<div class="board" id="boardid-${board.id}">
    <div class="board-title">
        <security:authorize access="@securityService.hasBoardEditPermission(${board.id})">
            <span class="drop-menu-button" style="font-size: 10px; text-align: center; margin-top: 10px;">&#x25be; Manage Board
                <span>
                    <ul class="drop-menu-options" >
                        <li style="width: auto;"><a href="#" class="create-box-wizard">Create child box</a></li>
                        <security:authorize access="@securityService.hasUserAccessPermission(${board.id})">
                            <li style="width: auto;">
                                <c:url var="boardUserId" value="${board.id}/edit-user-access">
                                    <c:param name="id" value="${board.id}" />
                                </c:url>
                                <a href='<c:out value="${boardUserId}"/>'>Edit Board Users Access</a>
                            </li>
                        </security:authorize>
                        <%--<li class="ui-state-disabled"><a href="#">Edit this board</a></li>--%>
                        <%--<li class="ui-state-disabled"><a href="#">Delete this board</a></li>--%>
                    </ul>
                </span>
            </span>
        </security:authorize>
            <span class="title" >
                ${board.title}
            </span>
    </div>
    <div class="board-body"  style="   border: transparent 1px solid;
    <%--background-image: url('${resourcesDir}/images/board-bg.png');--%>
    <%--background-repeat: no-repeat;--%>
    <%--background-attachment: fixed;--%>
    <%--background-position: center center;--%>
    <%--background-size: 100% 100%;--%>
            ">
        <c:set var="box" value="${board}" scope="request" />
        <jsp:include page="box-template.jsp">
            <jsp:param name="box" value= "${box}"/>
        </jsp:include>
    </div>
</div>



<script type="text/javascript">

$(document).ready(function(){


    $('.attachment tbody tr:nth-child(2n)').css({backgroundColor:'#dadada' ,  'padding': '1px'});
    $('.attachment table').css({'border-spacing':'0px'});

    $('.user-icon1 tbody tr:nth-child(2n)').css({backgroundColor:'#dadada' ,  'padding': '1px'});
    $('.user-icon1 table').css({'border-spacing':'0px'});

    $('.drop-menu-options').menu().hide();

//                        $('.drop-menu-button').click(function() {
//                            $('.drop-menu-options', this).toggle();
//                        }); 



//                    $('.board').on('click', '.box', function(){
//                        $('.task').draggable();
//                        $('.drop-menu-options').menu().hide();
//        //                $('.drop-menu-button').click(function() {
//        //                    $('.drop-menu-options',this).toggle();
//        //                });
//                    });


//                    $('.board').on('click', '.drop-menu-button', function(e) {
//                  
//                        
//                        $('.drop-menu-options',e.target).toggle();
//                        $('.task').draggable();
//                        $('.box-body').droppable({
//
//                            drop:function(e, ui) {
//                                $(e.target).append($(ui.draggable).detach().css({'top':'', 'left':''}));
//                            }
//
//                        });
//
//                    });




        $(".tooltip").tooltip({
            content: function() {
                return $(this).attr('title');
            },
            position: {
                my: "center bottom-20",
                at: "center top",
                using: function( position, feedback ) {
                    $( this ).css( position );
                    $( "<div>" )
                            .addClass( "arrow" )
                            .addClass( feedback.vertical )
                            .addClass( feedback.horizontal )
                            .appendTo( this );
                }
            }
        });

    $(document).on('mouseover', '.task', function(e) {

//    $(".tooltip").tooltip();

        $('.task').draggable({ revert: "invalid" });
        $('.box-body').droppable({
            //activeClass: "ui-state-hover",
            hoverClass: "ui-state-focus",
            drop:function(e, ui) {


                var initialParentId = ui.draggable.parents('.box').first().attr('id').split("-")[1] ;
                var destinationParentId = $(e.target).parents('.box').first().attr('id').split("-")[1] ;
                var movedElementId = $(ui.draggable).attr('id').split("-")[1] ;

                console.log("the element of id "+movedElementId+" \n initial parent of id  "+initialParentId+" \n destination parent of id "+destinationParentId);
                // Switch to between true and false
                //


                ajaxMoveTask(movedElementId, initialParentId, destinationParentId,
                        function(){
                            console.log("1");
//
                            $(e.target).append($(ui.draggable).detach().css({'top':'', 'left':''}));
                            console.log("detached");
                            console.log("2");
                            ui.draggable.draggable({ revert: "invalid"  });
                            showSuccessMessage("Task moved successfully and position saved in database !");

                            console.log("3");
                        },
                        function(){
                            ui.draggable.animate({ top: 0, left: 0 }, 'slow');
                            //ui.draggable.draggable({ revert: "valid"  });
                            showErrorMessage("Task move failed. You are not authorized to move task !");
                        }
                );
            }

        });
    });
    $(document).on('click', '.drop-menu-button', function(e) {

        $('.drop-menu-options',e.target).toggle();
//                    $('.task').draggable({ revert: "invalid" });
//                    $('.box-body').droppable({
//                        //activeClass: "ui-state-hover",
//                        hoverClass: "ui-state-focus",
//                        drop:function(e, ui) {
//
//
//                                var initialParentId = ui.draggable.parents('.box').first().attr('id').split("-")[1] ;
//                                var destinationParentId = $(e.target).parents('.box').first().attr('id').split("-")[1] ;
//                                var movedElementId = $(ui.draggable).attr('id').split("-")[1] ;
//
//                            console.log("the element of id "+movedElementId+" \n initial parent of id  "+initialParentId+" \n destination parent of id "+destinationParentId);
//                                // Switch to between true and false
//                                                   //
//
//
//                                ajaxMoveTask(movedElementId, initialParentId, destinationParentId,
//                                function(){
//                                    console.log("1");
////
//                                    $(e.target).append($(ui.draggable).detach().css({'top':'', 'left':''}));
//                                    console.log("detached");
//                                    console.log("2");
//                                    ui.draggable.draggable({ revert: "invalid"  });
//                                    console.log("3");
//                                },
//                                function(){
//                                    ui.draggable.draggable({ revert: "valid"  });
//                                });
//                        }
//
//                    });


    });

    $(document).on('mouseleave', '.drop-menu-options', function(e) {

        $('.drop-menu-options').menu().hide();
    });

    $(document).on('click', '.drop-menu-options', function(e) {

        $('.drop-menu-options').menu().hide();
    });

//                $(document).on('click', '.file-attachment-wizard', function(e) {
//
//                    $('#bla').val(  $(this).parents(".task").first()  );
//                });



    $(document).on('click', '.file-attachment-wizard', function(e) {
        //   $('#taskId-input-field').val(  $(this).parents(".task").first().attr('id') );
        $('#taskIdForFileUpload').val(  $(this).parents(".task").first().attr('id').split("-")[1] );
        $('#boardIdForFileUpload').val(  $(this).parents('.board').first().attr("id").split("-")[1] );
        console.log("task id is :" +  $('#taskIdForFileUpload').val());
//                    alert("hello "+$('#taskid-input-field').val());
    });



//            $( ".attachment-content" ).dialog({
////                     autoOpen: false,
//                dialogClass: 'forms-for-board'
//            });

    $(document).on('click', '.attachment', function(e) {

        $(this).children('.attachment-content').first().dialog({

            appentTo: $(this),
            dialogClass: 'forms-for-board',
            close: function(){
                $(this).dialog('destroy');
            }
        });

    });

    $(document).on('click', '.user-icon1', function(e) {

        $(this).children('.user-content').first().dialog({

            appentTo: $(this),
            dialogClass: 'forms-for-board',
            close: function(){
                $(this).dialog('destroy');
            }
        });

    });

    $(document).on('click', '.delete-attachment', function(e) {
        var id = $(this).parents('tr').first().attr("id").split("-")[1];
        ajaxDeleteAttachment(id,
                function(){
                    console.log("1");
//
                    //$(this).parents('tr').first().remove();
                    console.log("attachment file id that is going to be removed is "+$("#attachmentid-"+id).attr("id"));
                    $("#attachmentid-"+id).remove();
                    console.log("2");

                    showSuccessMessage("File deleted successfully!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("File deletion failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });

//            class="task-priority-critical-button"

    $(document).on('click', '.task-priority-critical-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var priority = "critical";

        ajaxChangeTaskPriority(bId, id, priority,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-critical");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-high");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-low");

                    $("#taskid-"+id).find(".task-priority").first().addClass("task-priority-critical");


                    console.log("2");

                    showSuccessMessage("Task priority changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task priority change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });

    $(document).on('click', '.task-priority-high-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var priority = "high";

        ajaxChangeTaskPriority(bId, id, priority,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-critical");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-high");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-low");

                    $("#taskid-"+id).find(".task-priority").first().addClass("task-priority-high");


                    console.log("2");

                    showSuccessMessage("Task priority changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task priority change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });

    $(document).on('click', '.task-priority-normal-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var priority = "normal";

        ajaxChangeTaskPriority(bId, id, priority,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-critical");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-high");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-low");

                    $("#taskid-"+id).find(".task-priority").first().addClass("task-priority-");


                    console.log("2");

                    showSuccessMessage("Task priority changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task priority change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });

    $(document).on('click', '.task-priority-low-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var priority = "low";

        ajaxChangeTaskPriority(bId, id, priority,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-critical");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-high");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-");
                    $("#taskid-"+id).find(".task-priority").first().removeClass("task-priority-low");

                    $("#taskid-"+id).find(".task-priority").first().addClass("task-priority-low");


                    console.log("2");

                    showSuccessMessage("Task priority changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task priority change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });


    $(document).on('click', '.task-status-new-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var status = "new";

        ajaxChangeTaskStatus(bId, id, status,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).removeClass("task-status-new");
                    $("#taskid-"+id).removeClass("task-status-in-process");
                    $("#taskid-"+id).removeClass("task-status-in-issues");
                    $("#taskid-"+id).removeClass("task-status-completed");

                    $("#taskid-"+id).addClass("task-status-new");


                    console.log("2");

                    showSuccessMessage("Task status changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task status change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });

    $(document).on('click', '.task-status-in-process-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var status = "in-process";

        ajaxChangeTaskStatus(bId, id, status,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).removeClass("task-status-new");
                    $("#taskid-"+id).removeClass("task-status-in-process");
                    $("#taskid-"+id).removeClass("task-status-in-issues");
                    $("#taskid-"+id).removeClass("task-status-completed");

                    $("#taskid-"+id).addClass("task-status-in-process");


                    console.log("2");

                    showSuccessMessage("Task status changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task status change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });

    $(document).on('click', '.task-status-in-issues-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var status = "in-issues";

        ajaxChangeTaskStatus(bId, id, status,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).removeClass("task-status-new");
                    $("#taskid-"+id).removeClass("task-status-in-process");
                    $("#taskid-"+id).removeClass("task-status-in-issues");
                    $("#taskid-"+id).removeClass("task-status-completed");

                    $("#taskid-"+id).addClass("task-status-in-issues");


                    console.log("2");

                    showSuccessMessage("Task status changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task status change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });





    $(document).on('click', '.task-status-completed-button', function(e) {
        var id = $(this).parents('.task').first().attr("id").split("-")[1];
        var bId = $(this).parents('.board').first().attr("id").split("-")[1];
        console.log("task " +id);
        var status = "completed";

        ajaxChangeTaskStatus(bId, id, status,
                function(){
                    console.log("1");

                    console.log($("#taskid-"+id).attr("id"));
                    $("#taskid-"+id).removeClass("task-status-new");
                    $("#taskid-"+id).removeClass("task-status-in-process");
                    $("#taskid-"+id).removeClass("task-status-in-issues");
                    $("#taskid-"+id).removeClass("task-status-completed");

                    $("#taskid-"+id).addClass("task-status-completed");


                    console.log("2");

                    showSuccessMessage("Task status changed!");

                    console.log("3");
                },
                function(){

                    showErrorMessage("Task status change failed. Check permissions and/or network connectivity !");
                    console.log("4");
                }
        );
    });

//            $(document).on('click', '.attachment', function(e) {
//
//                $(this).children('.attachment-content').first().dialog( "open" );
//            });


//            $(document).on('click', '.attachment', function(e) {
//
//                $(this).children().first('.attachment-content').toggleClass('attachment-content-show');
//            });
//            $(document).on('mouseleave', '.attachment-content', function(e) {
//
//                $(this).removeClass('attachment-content-show');
//            });








//		      var menuOnn;  
//			
//		
//			$('.blur').blurjs({
//				draggable: true,
//				overlay: 'rgba(255,255,255,0.1)',
//				radius:40
//			});
//			
//			$('.box-title , .board-title , .box-body').blurjs({
//				
//			//	source: 'body',
//				draggable: false,
//			//	overlay: 'rgba(255,255,255,0.5)',
//			//	overlay: 'rgba(70,130,180, 0.2)',
//				radius:40
//			});
//			








//			 $("#circle").click(function(){
//				      
//			      if(menuOnn != 1){
//				  $("#circle").animate({
//				    backgroundColor: "rgba(70,130,180, 0.3)",
//				    //opacity: 0.4,
//				    marginRight: "-40px",
//				    marginTop: "-40px"
//				    
//				    
//				  }, 1500 );
//				  menuOnn = 1;
//			      }else{
//				  $("#circle").animate({
//				    backgroundColor: "rgba(70,130,180, 0.3)",
//				    //opacity: 0.4,
//				    marginRight: "-180px",
//				    marginTop: "-192px"
//				    
//				  }, 1500 );
//				  menuOnn = 0;
//			      }
//				  
//			  });









    var taskColor = ['rgba(255,255,0,0.3)','rgba(255,0,0,0.3)', 'rgba(0,128,0,0.3)', 'rgba(0,0,255,0.3)'];
    var cornerArg1 = ['invsteep','dog3','tear','dogfold','bevelfold', 'fray'] ;
    var cornerArg2 = ['tl br','tr bl','top','bottom','right','left','tl','br','tr','br'];
    function pickRandom(arr) {
        return arr[Math.floor(Math.random() * arr.length)];
    }

//    $('.task').each(function (_, item) {
//        $(item).css({
//            "background-color": pickRandom(taskColor)
//        });
////			      $(item).append($('<div class="task-title"><b>Task title</b></div><div class="task-body">demo text bla bla bla bla bla bla</div>'));
////                  $(item).corner(pickRandom(cornerArg1)+" "+pickRandom(cornerArg2));
//        //    $(item).append($('<u>hello world</u> <p>demo text bla bla bla</p>'));
//    });









    $(document).on('mouseenter', '.box', function(e) {


        if ( $(this).find(".box").length > 0 ){
            $(this).css({

                'max-width': 'none'
            });
        }

    });

    $('.box').each(function(){
        if ($(this).find(".box").length > 0) {
            $(this).css({
                'max-width': 'none'
            });
        }
    });

    $("#filter").keyup(function(){
        var filter = $(this).val(), count = 0;
        $(".usersListClass tr").each(function(){
            if ($(this).text().search(new RegExp(filter, "i")) < 0) {
                $(this).fadeOut();
            } else {
                $(this).show();
                count++;
            }
        });
    });



        /////////////////////task creation ////////////////////
    $(document).on('click', '.create-task-wizard', function(e) {
        $( "#task-creation-form").dialog( "open" );
        window["parentBox"] =  $(this).parents(".box").first() ;
    });


       ///////////box creation//////////////////////////
    $(document).on('click', '.create-box-wizard', function(e) {
        $( "#box-creation-form").dialog( "open" );
        window["parentBoxForBoxCreation"]      =  $(this).parents(".box").first() ;
        window["parentBoardForBoxCreation"]    =  $(this).parents(".board").first() ;
        if(parentBoxForBoxCreation.length){
            window["parentOfBox"] = parentBoxForBoxCreation;
            console.log("parent of box is box, "+ parentBoxForBoxCreation.attr("id"));
        }else{
            window["parentOfBox"] = parentBoardForBoxCreation;
            console.log("parent of box is board, "+ parentBoardForBoxCreation.attr("id"));
        }



    });


    ///////////////////// box editing ///////////////////

    $(document).on('click', '.edit-box-wizard', function(e) {
        $( "#box-editing-form").dialog( "open" );
        window["box"]      =  $(this).parents(".box").first() ;
//        var boxType=
        var boxTitle = $(this).parents(".box").first().find(".box-title-text").first().text();
        $("#box-editing-form-title").val(boxTitle);
        var boxDescription = $(this).parents(".box").first().find(".box-description-text").first().text();
        $("#box-editing-form-description").val(boxDescription);

        if($(this).parents(".box").first().hasClass("box-vertical")){
            console.log("this has box has vertical class applied to it.");
            var boxType = "vertical";

            $("#box-editing-form-type-id-horizontal").prop('checked',false);
            $("#box-editing-form-type-id-vertical").prop('checked',true);


        }
        if($(this).parents(".box").first().hasClass("box-horizontal")){
            console.log("this has box has horizontal class applied to it.");
            var boxType = "horizontal";
            $("#box-editing-form-type-id-vertical").prop('checked',false);
            $("#box-editing-form-type-id-horizontal").prop('checked',true);
        }




//        $('input[name="box-editing-form-type"]:checked').val();
//        $("#box-editing-form-title").val();
//        $("#box-editing-form-description").val();
    });

    ///////////////////// task editing ///////////////////

    $(document).on('click', '.edit-task-wizard', function(e) {
        $( "#task-editing-form").dialog( "open" );
        window["task"]      =  $(this).parents(".task").first() ;

        var taskTitle = $(this).parents(".task").first().find(".task-title-text").first().text();
        $("#task-editing-form-title").val(taskTitle);
        var taskDescription = $(this).parents(".task").first().find(".task-description-text").first().text();
        $("#task-editing-form-description").val(taskDescription);


    });

    ///////////////////////// file attachment ///////////////
    $(document).on('click', '.file-attachment-wizard', function(e) {
        $("#file-attachment-form").dialog("open");
        window["parentTask"] = $(this).parents(".task").first();
    });




































});

function ajaxAssignTask(board, task){

    var boardId = board.attr('id').split("-")[1];
    var taskId = task.attr('id').split("-")[1];
    $.ajax({

        url: "${pageContext.request.contextPath}/task/assign/"+boardId+"/"+taskId,
        cache: false,
        success: function(response){
            var userList = response;
            window["parentTask"] = $(this).parents(".task").first();
            //$('#taskIdForAssignUser').val(  $(this).parents(".task").first().attr('id').split("-")[1] );
            <%--$('#task-assign-unassign-table').before(+
                    '<form><table><tr><td colspan="2">'+
                    ''
            );
            $('#task-assign-unassign-table').after(+
                    '</td></tr><tr><td><input type="hidden" id="taskIdForAssignUser" /><input type="reset" value="Reset" /></td>'+
                    '<td><input type="submit" value="Submit" class="task-assign-unassign-wizard-submit" /></td></tr></table></form>'+
                    ''
            );--%>
            $('#task-assign-unassign-table').empty();
            $.each(userList, function( index, value ) {

                if(value.enableUserAssignId == true){
                    $('#task-assign-unassign-table').prepend(+
                            '<input type="hidden" id="task-assign-userId2" value='+value.userId+' />'+
                            '   <tr>' +
                            '       <td>' +
                            //'           <img src="${resourcesDir}/images/avatar-small.png"/>' +
                            '           <img src="${resourcesDir}'+value.imageName+'" height="25" width="22"/>' +
                            '       </td>' +
                            '       <td style="width: 200px;">' +
                            '           <input type="hidden" id="task-assign-userId" name="user-id-'+index+'" value='+value.userId+' />'+
                            '           <span style="display:inline-block; text-align: left; font-weight: bold">'+value.firstName+'&nbsp;'+value.lastName+'</span> <br />' +
                            '           <span style="display:inline-block; text-align: left;">'+value.email+'</span>' +
                            '       </td>'+
                            '        <td><input id="checkBoxId" name="checkBoxName'+index+'" type="checkbox" checked style="display: block;" /></td>'+
                            '   </tr>' +
                            ''
                    );
                }else{
                    $('#task-assign-unassign-table').prepend(+
                            '<input type="hidden" id="task-assign-userId2" value='+value.userId+' />'+
                            '   <tr>' +
                            '       <td>' +
                            '           <img src="${resourcesDir}'+value.imageName+'" height="25" width="22"/>' +
                            '       </td>' +
                            '       <td class="user-id2" style="width: 200px;">' +
                            '           <input type="hidden" id="task-assign-userId" name="user-id-'+index+'" value='+value.userId+' />'+
                            '           <span style="display:inline-block; text-align: left; font-weight: bold">'+value.firstName+'&nbsp;'+value.lastName+'</span> <br />' +
                            '           <span style="display:inline-block; text-align: left;">'+value.email+'</span>' +
                            '       </td>'+
                            '        <td><input id="checkBoxId" name="checkBoxName'+index+'" type="checkbox" style="display: block;" /></td>'+
                            '   </tr>' +
                            ''
                    );
                }
            });
            window["taskUsersList"] = userList;
            $("#task-assign-unassign-form").dialog("open");
            console.log("task id for user assignment is :" +  $('#taskIdForAssignUser').val());
        },
        error: function(){
            showErrorMessage('There was a problem during task assignment to the user. Please check internet connectivity and access rights');
        }
    });
}

function ajaxAssignTaskSubmit(ulist){

    var bId = $("#boardIdForAssignUser").val();
    var tId = $("#taskIdForAssignUser").val();
    var objList = [];
    $.each(ulist, function( index, value ) {
        var checkBoxVar = $('input[name=checkBoxName'+index+']:checked');
        var userNameVar = $('input[name=user-id-'+index+']');
        //alert("Index:"+index+" UserID: "+$(userNameVar).val()+" Length: "+$(checkBoxVar).length);
        var enableUserCheck = false;
        if($(checkBoxVar).length == 1){
            enableUserCheck = true;
        }else{
            enableUserCheck = false;
        }
        var obj = {};
        var userID = "userId";
        var enableUser = "enableUserAssignId";
        obj[userID] = $(userNameVar).val();
        obj[enableUser] = enableUserCheck;
        objList.push(obj);

    })
    $.ajax({
        url: "${pageContext.request.contextPath}/task/assign-task/"+bId+"/"+tId,
        type:"POST",
        cache: false,
        data : {
            ulist : JSON.stringify(objList)
            //ulist : ulist
        },
        async: false,


        success: function(response){
            if(response.isSuccessful){
                showSuccessMessage(response.message);
                $("#task-assign-unassign-form").dialog("close");
                //$("#taskid-"+tId).find(".user-icon").first().addClass("user-icon1");
                setTimeout("location.reload(true);",2000);
            }else{
                showErrorMessage(response.message);
                $("#task-assign-unassign-form").dialog("close");
            }
        },
        error: function(){
            showErrorMessage('There is a problem during task assignment to the user. Please check internet connectivity and access rights');
        }

    });
}

/*function ajaxAssignTaskSubmit(result){

    $.ajax({
        url: "${pageContext.request.contextPath}/task/assign-task",
        cache: false,
        success: function(response){
            showSuccessMessage(response);
        },
        error: function(response){
            showErrorMessage(response);
            alert('Error while request..');
        }
    });
}*/


$(document).ready(function(){
    $('box-creation-form').on('submit', function(e){
        var message = "";
        var show = false;
        if($("#box-creation-form-title").val().length <= 0 ){
            show = true;
            message = "<spring:message code="error.confirmPassowordMissmatch"/>";
            e.preventDefault();
        }

        if(show){
//                alert(message);
            $(".form-messages").append('<span class="message-error">'+message+'</span>');
        }

    });

});



</script>
</div>

<c:import url="${mainDir}/common/footer.jsp" />


<!--
http://www.malsup.com/jquery/corner/
http://blurjs.com/simpledemo.html
http://www.functionn.in/2012/01/blurjs-jquery-plugin-producing-psuedo.html#.UfGjOj5BXZs
http://www.blurjs.com/
http://codecanyon.net/item/translucent-responsive-banner-rotator-slider/801607?WT.ac=search_item&WT.seg_1=search_item&WT.z_author=VF
http://vectorflower.com/preview/trans_banner/index3.html?s_sel=3&&t_sel=4



create box > vertical/horizontal?

drop: function(e, ui) { $(e.target).append($('#'+ui.draggable.id).detach()); }
ui.draggable is the element being dragged, then then dropped


$(".target").droppable({
    accept: '.draggable',
    drop: function(event, ui) {
        $(this).append(ui.draggable);
    }
});
$(".draggable").draggable();
Here is a demonstration: http://jsfiddle.net/VTHcG/

http://jsfiddle.net/4bmT9/

great       http://jsfiddle.net/4bmT9/1/


$('.dragme').draggable();
$('#drop, #source').droppable({
    drop:function(e, ui) {
        $(e.target).append($(ui.draggable).detach().css({'top':'', 'left':''}));
    }
});




<div id="source" class="box">
    <div class="dragme">bacon</div>
    
</div>
<div id="drop" class="box"></div>





.box { height:100px;width:100px;border:1px solid #333; }
.dragme { width:100%; margin:5px; border:1px solid #333; }
 






##############################################


<div id="source" class="box">
    <div class="dragme">bacon</div>
    
</div>
<div id="drop" class="box"></div>



$('#drop, #source').sortable({
    connectWith:'#drop, #source'
});



.box { height:100px;width:100px;border:1px solid #333; }
.dragme { width:100%; margin:5px; border:1px solid #333; }





Quest> dom element createion event
* T13|sleeps has quit (Client Quit)
<Zzaichik> there are several different events
* roven has quit (Remote host closed the connection)
<duch_kazatel> then change 'click' to 'DOMSubtreeModified


$('.class').on('click', '.board', function(){/* this code will execute when you click on anything
<duch_kazatel> with class board */})
-->



<%--$(document).on('mouseenter', '.box', function(e) {--%>
<%--var $this = $(this),--%>
<%--parentWidth = $this.parent('.box').width() - 60,--%>
<%--thisElementWidth = $this.width();--%>

<%--if ( thisElementWidth >= parentWidth ){--%>
<%--$this.parent('.box').css({--%>
<%--'width': thisElementWidth + 65 + 'px',--%>
<%--'max-width': thisElementWidth + 65 + 'px'--%>
<%--});--%>
<%--}--%>

<%--});--%>