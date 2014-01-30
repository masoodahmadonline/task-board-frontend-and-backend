<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springform"%>
<%--test--%>
<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.board"/>
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body>
<c:import url="${mainDir}/common/inner-header.jsp" />

<script type="text/javascript">

function showSuccessMessage(message) {
    $("#message-box").addClass("success-message");
    $("#message-box").html(message);
    $("#message-box").effect( "highlight",
            {color:"#669966"}, 10000 );

}
function showErrorMessage(message) {
    $("#message-box").addClass("error-message");
    $("#message-box").html(message);
    $("#message-box").effect( "highlight",
            {color:"#669966"}, 10000 );

}

function ajaxCreateBox(parent){

    alert("parent of box is: "+ parent.attr("id"));
    var parentElementId =   parent.attr("id");
    var parentId =  parentElementId.split("-")[1];
    var parentType;
    if (parentElementId.split("-")[0] == "boardid"){ parentType = "board";}
    if (parentElementId.split("-")[0] == "boxid"){ parentType = "box";}


    var boxType = $('input[name="box-creation-form-type"]:checked').val();
    var boxTitle = $("#box-creation-form-title").val();
    var boxDescription = $("#box-creation-form-description").val();
    $.ajax({

//                            url: "${pageContext.request.contextPath}/box/create/board/3/vertical/MyBoxTitle",
        url: "${pageContext.request.contextPath}/box/create/"+parentType+"/"+parentId+"/"+boxType+"/"+boxTitle+"/"+boxDescription,
        cache: false,
        //data:'firstName=' + $("#firstName").val() + "&lastName=" + $("#lastName").val() + "&email=" + $("#email").val(),
        success: function(response){
            var fetchedBoxId = response.id;
            var fetchedBoxType = response.type;
            var fetchedBoxTitle = response.title;
            var fetchedBoxDescription = response.description;
            //alert(fetchedBoxType+" "+fetchedBoxTitle+" "+fetchedBoxDescription);
            $('#result').html(fetchedBoxId+" "+fetchedBoxType+" "+fetchedBoxTitle+" "+fetchedBoxDescription);
            // createBoxInDom();
            $("#"+parentElementId+" ."+parentType+"-body").append(
                    '<div id="boxid-'+fetchedBoxId+'" class="box box-'+fetchedBoxType+'" >'+
                            '    <div class="box-title" title="'+fetchedBoxDescription+'">'+
                            '          <span>'+
                            '                <div class="drop-menu-button">&#x25be; &nbsp;'+
                            '                    <ul class="drop-menu-options">'+
                            '                              <li style="width: auto;"><a href="#">Create a task</a></li>'+
                            '                              <li><a href="#" class="create-box-wizard">Create child box</a></li>'+
                            '                              <li class="ui-state-disabled"><a href="#">Edit this box</a></li>'+
                            '                              <li><a href="#" class="delete-box-wizard">Delete this box</a></li>'+
                            '                      </ul>'+
                            '                  </div>'+
                            '                '+fetchedBoxTitle+''+
                            '            </span> '+
                            '        </div>'+
                            '        <div class="box-body">'+
                            '        </div>   '+
                            '</div>');

            $('#boxid-'+fetchedBoxId+' .drop-menu-options').menu().hide();
            $('#boxid-'+fetchedBoxId+' .delete-box-wizard').click(function () {
                ajaxDeleteBox(     $(this).parents(".box").first()      );
            });


        },
        error: function(){
            alert('Error while request..');
        }


    });
    $('#box-creation-form').children('form').reset;

}

function ajaxCreateTask(parentBox){

    var parentBoxId = parentBox.attr('id').split("-")[1];
    var taskTitle = $("#task-creation-form-title").val();
    var taskDescription = $("#task-creation-form-description").val();
//                                                          alert ("parent id: "+parentBoxId+ "taskTitle: " + taskTitle + "taskDescription: "+taskDescription );
    $.ajax({


        url: "${pageContext.request.contextPath}/task/create/"+parentBoxId+"/"+taskTitle+"/"+taskDescription,
        cache: false,

        success: function(response){
            var fetchedTaskId = response.id;
            var fetchedTaskTitle = response.title;
            var fetchedTaskDescription = response.description;

            $('#result').html(fetchedTaskId+"  "+fetchedTaskTitle+" "+fetchedTaskDescription);
            // createBoxInDom();
            $("#boxid-"+parentBoxId+" .box-body").append(
                    '<div class="task" id="taskid-'+fetchedTaskId+'">' +
                            '<div class="task-title" title="'+fetchedTaskTitle+'">' +
                            '<span> '+
                            '    <div class="drop-menu-button">&#x25be;&nbsp; '+
                            '        <ul class="drop-menu-options">'+
                            '                <li><a href="#" class="task-assign-unassign-wizard">Assign/Unassign</a></li>'+
                            '                <li><a href="#" class="file-attachment-wizard">Attach file</a></li> '+
                            '                <li class="ui-state-disabled"><a href="#">Edit this task</a></li> '+
                            '                <li><a href="#">Set priority</a> '+
                            '                    <ul>  '+
                            '                        <li><a href="#" class="task-priority-critical-button">Critical</a></li> '+
                            '                        <li><a href="#" class="task-priority-high-button">High</a></li>'+
                            '                        <li><a href="#" class="task-priority-normal-button">Normal</a></li> '+
                            '                        <li><a href="#" class="task-priority-low-button">Low</a></li> '+
                            '                    </ul> '+
                            '                </li> '+
                            '                <li><a href="#" class="delete-task-wizard">Delete this task</a></li>'+
                            '        </ul>'+
                            '    </div>   '+
                            fetchedTaskTitle+
                            '</span>      '+
                            '</div>' +
                            '<div class="task-body">' +
                            fetchedTaskDescription +
                            '   <div class="task-priority task-priority-normal"></div>'+
                            '</div>' +
                            '' +
                            '</div>'
            );
            $('#taskid-'+fetchedTaskId+' .drop-menu-options').menu().hide();
            $('#taskid-'+fetchedTaskId+' .delete-task-wizard').click(function () {
                ajaxDeleteTask(     $(this).parents(".task").first()      );
            });
            $('#taskid-'+fetchedTaskId+' .task-assign-unassign-wizard').click(function () {
                //ajaxAssignTask($(this).parents(".task").first());
                $("#task-assign-unassign-form").dialog("open");
                window["parentTask"] = $(this).parents(".task").first();
            });

        },
        error: function(){
            alert('Error while request..');
        }

    });
    $('#task-creation-form').children('form').reset();
}



function ajaxDeleteBox(box){
    var boxId = box.attr('id').split("-")[1];
    alert("box id to be sent for deletion by ajax call: "+boxId);

    $.ajax({

        url: "${pageContext.request.contextPath}/box/delete/"+boxId,

        cache: false,

        success: function(response){

            box.remove();


        },
        error: function(){
            alert('Error while request..');
        }

    });
}



function ajaxDeleteTask(task){

    var taskId = task.attr('id').split("-")[1];
    alert("task id to be sent for deletion by ajax call: "+taskId);

    $.ajax({

        url: "${pageContext.request.contextPath}/task/delete/"+taskId,

        cache: false,

        success: function(response){

            task.remove();


        },
        error: function(){
            alert('Error while request..');
        }

    });
}



function ajaxChangeTaskPriority(taskId, priority, success, error){

    console.log("task id to be changed for setting priority: "+taskId);

    $.ajax({

        url: "${pageContext.request.contextPath}/task/set-priority/"+taskId+"/"+priority,

        cache: false,
        success: success,

        //                    success: function(response){
        //
        //                        console.log("success move");
        //                        b= true;
        //
        //                    },
        //                    error: function(){
        //                        console.log('Error while request..');
        //                        b= false;
        //                    }
        error: error

    });

}

function ajaxMoveTask(taskId, initialParentBoxId, destinationParentBoxId, success, error){
    var b = false;
//                var taskId = task.attr('id').split("-")[1];
//                var initialParentBox = task.parents(".box").first();
//                var initialParentBoxId = initialParentBox.attr('id').split("-")[1];

    console.log("task id to be sent for movement by ajax call: "+taskId);

    $.ajax({

        url: "${pageContext.request.contextPath}/task/move/"+taskId+"/"+initialParentBoxId+"/"+destinationParentBoxId,

        cache: false,
        success: success,

//                    success: function(response){
//
//                        console.log("success move");
//                        b= true;
//
//                    },
//                    error: function(){
//                        console.log('Error while request..');
//                        b= false;
//                    }
        error: error

    });
    return b;
}

function ajaxDeleteAttachment(attachmentId, success, error){
    var b = false;
    //                var taskId = task.attr('id').split("-")[1];
    //                var initialParentBox = task.parents(".box").first();
    //                var initialParentBoxId = initialParentBox.attr('id').split("-")[1];

    console.log("attachmentId id to be sent for deletion by ajax call: "+attachmentId);

    $.ajax({

        url: "${pageContext.request.contextPath}/attachment/delete/"+attachmentId,

        cache: false,
        success: success,

        //                    success: function(response){
        //
        //                        console.log("success move");
        //                        b= true;
        //
        //                    },
        //                    error: function(){
        //                        console.log('Error while request..');
        //                        b= false;
        //                    }
        error: error

    });
    return b;
}

$(function() {
    //wizard
    //form
    //wizard-submit


    $( "#box-creation-form" ).dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });
    $(".create-box-wizard").click(function () {
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


    $(".delete-box-wizard").click(function () {
        ajaxDeleteBox(     $(this).parents(".box").first()      );
    });

    $(".create-box-wizard-submit").click(function () {
        $( "#box-creation-form" ).dialog( "close" );
    });






    $( "#task-creation-form" ).dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });

    $(".create-task-wizard").click(function () {
        $( "#task-creation-form").dialog( "open" );
        window["parentBox"] =  $(this).parents(".box").first() ;
    });

    $(".delete-task-wizard").click(function () {

        ajaxDeleteTask(     $(this).parents(".task").first()      );

    });

    $(".create-task-wizard-submit").click(function () {
        $( "#task-creation-form" ).dialog( "close" );

    });







    $("#file-attachment-form").dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });

    $(".file-attachment-wizard").click(function () {
        $("#file-attachment-form").dialog("open");
        window["parentTask"] = $(this).parents(".task").first();
    });

    $(".file-attachment-wizard-submit").click(function () {
        //$("#file-attachment-form").dialog("close");

    });







    $("#task-assign-unassign-form").dialog({
        autoOpen: false,
        dialogClass: 'forms-for-board'
    });

    $(".task-assign-unassign-wizard").click(function () {
        ajaxAssignTask(     $(this).parents(".task").first()      );

    });

    $(".task-assign-unassign-wizard-submit").click(function () {
        //ajaxAssignTaskSubmit($(this).parents(".task").first());
        //$("#task-assign-unassign-form").dialog("close");

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
                <td>Box title:</td><td><input id="box-creation-form-title" type="text" />
            </tr>
            <tr>
                <td>Box description</td><td><textarea id="box-creation-form-description" style="min-height: 100px;"></textarea></td>
            </tr>
            <tr>

                <td><input type="reset" /></td><td><input type="button" value="Create" class="create-box-wizard-submit" onClick="ajaxCreateBox(parentOfBox);" /></td>
            </tr>
        </table>



    </form>
</div>

<div id="task-creation-form" class="forms-for-board" title="Create new task">
    <form >
        <table>

            <tr>
                <td>Task title:</td><td><input id="task-creation-form-title" type="text" /></td>
            </tr>
            <tr>
                <td>Task description</td><td><textarea id="task-creation-form-description" style="min-height: 100px;"></textarea></td>
            </tr>
            <tr>
                <td><input type="reset" /></td><td><input type="button" value="Create" class="create-task-wizard-submit" onClick="ajaxCreateTask(window.parentBox);" /></td>
            </tr>
        </table>



    </form>
</div>

<c:if test="${viewAssignForm}">
    <div id="task-assign-unassign-form" class="forms-for-board" style="max-height: 600px; overflow-x: visible;" title="Assign or Unassign this task">
        <springform:form action="${pageContext.request.contextPath}/task/assign-task" method="POST" commandName="uWrapper">
            <table id="task-assign-unassign-table">
                <tr>
                    <td></td>
                    <td></td>
                    <td style="text-align: right;">Assign?</td>
                </tr>
                <springform:hidden id="taskIdForAssignUser" path="taskId" />
                <c:forEach items="${uWrapper.userList}" var="wrapper" varStatus="idx">
                    <springform:hidden path="userList[${idx.index}].userId"></springform:hidden>
                    <tr>
                        <td>
                            <img src="${resourcesDir}/images/avatar-small.png"/>
                        </td>
                        <td style="width: 200px;">
                            <span style="display:inline-block; text-align: left; font-weight: bold">${wrapper.firstName}&nbsp;${wrapper.lastName}</span> <br />
                            <span style="display:inline-block; text-align: left;">${wrapper.email}</span>
                        </td>
                        <td style="text-align: right;">
                            <springform:checkbox id="1" path="userList[${idx.index}].enableUserAssignId" cssStyle="display: block;"></springform:checkbox>

                        </td>
                    </tr>

                </c:forEach>

                <tr>
                    <td><input type="reset" value="Reset" /></td><td><input type="submit" value="Submit" class="task-assign-unassign-wizard-submit" /></td>
                </tr>
            </table>
        </springform:form>
    </div>
</c:if>

<div id="file-attachment-form" class="forms-for-board" title="Attach a file to this task">
    <form action="${pageContext.request.contextPath}/task/attach-file" method="POST" enctype="multipart/form-data">
        <table>
            <input type="file" name="file" id="file">
            <tr>
                <td>File description</td>
                <td><textarea id="fileDescription" name="fileDescription" style="min-height: 100px;"></textarea></td>
            </tr>
            <input id="taskIdForFileUpload" type="hidden" name="taskIdForFileUpload" value="" />
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
            <span class="drop-menu-button">&#x25be;
                <span>
                    <ul class="drop-menu-options" >
                        <li style="width: auto;"><a href="#" class="create-box-wizard">Create child box</a></li>
                        <li class="ui-state-disabled"><a href="#">Edit this board</a></li>
                        <li class="ui-state-disabled"><a href="#">Delete this board</a></li>
                    </ul>
                </span>
            </span>
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
    $(document).on('mouseover', '.task', function(e) {
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
                            ui.draggable.draggable({ revert: "valid"  });
                            showErrorMessage("Task move failed. Check permissions and/or network connectivity !");
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
        console.log("task id is :" +  $('#taskIdForFileUpload').val());
//                    alert("hello "+$('#taskid-input-field').val());
    });

    $(document).on('click', '.task-assign-unassign-wizard', function(e) {
        //   $('#taskId-input-field').val(  $(this).parents(".task").first().attr('id') );
        $('#taskIdForAssignUser').val(  $(this).parents(".task").first().attr('id').split("-")[1] );
        console.log("task id for user assignment is :" +  $('#taskIdForAssignUser').val());
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
        console.log("task " +id);
        var priority = "critical";

        ajaxChangeTaskPriority(id, priority,
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
        console.log("task " +id);
        var priority = "high";

        ajaxChangeTaskPriority(id, priority,
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
        console.log("task " +id);
        var priority = "normal";

        ajaxChangeTaskPriority(id, priority,
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
        console.log("task " +id);
        var priority = "low";

        ajaxChangeTaskPriority(id, priority,
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

    $('.task').each(function (_, item) {
        $(item).css({
            "background-color": pickRandom(taskColor)
        });
//			      $(item).append($('<div class="task-title"><b>Task title</b></div><div class="task-body">demo text bla bla bla bla bla bla</div>'));
//                  $(item).corner(pickRandom(cornerArg1)+" "+pickRandom(cornerArg2));
        //    $(item).append($('<u>hello world</u> <p>demo text bla bla bla</p>'));
    });









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







});

function ajaxAssignTask(task){

    var taskId = task.attr('id').split("-")[1];
    $.ajax({

        url: "${pageContext.request.contextPath}/task/assign/"+taskId,
        cache: false,
        success: function(response){
            //$("#task-assign-unassign-form").load(".forms-for-board");
            //$( "#task-assign-unassign-form" ).load( "${pageContext.request.contextPath}/task/assign/"+taskId+" #task-assign-unassign-table" );
            $("#task-assign-unassign-form").dialog("open");
            window["parentTask"] = $(this).parents(".task").first();
            //$("#task-assign-unassign-form").load(".forms-for-board");
        },
        error: function(){
            alert('Error while request..');
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

/*function createDialog(abc) {
    return $("<div id='task-assign-unassign-form-1' class='forms-for-board' style='max-height: 600px; overflow-x: visible;' title='"+abc+"'>" +
            "<springform:form action='${pageContext.request.contextPath}/task/assign-task' method='POST' commandName='uWrapper'>" +
            "   <table id='task-assign-unassign-table-1'>" +
            "       <tr><td></td><td></td><td style='text-align: right;'>Assign?</td></tr>" +
            "           <springform:hidden id='taskIdForAssignUser-1' path='taskId' />" +
            "           <c:forEach items='${uWrapper.userList}' var='wrapper' varStatus='idx'>" +
            "               <springform:hidden path='userList[${idx.index}].userId'></springform:hidden>" +
            "               <tr><td>" +
            "                   <img src='${resourcesDir}/images/avatar-small.png'/></td>" +
            "                   <td style='width: 200px;'>" +
            "                       <span style='display:inline-block; text-align: left; font-weight: bold'>${wrapper.firstName}&nbsp;${wrapper.lastName}</span> <br />" +
            "                       <span style='display:inline-block; text-align: left;'>${wrapper.email}</span></td>"+
            "                    <td style='text-align: right;'>" +
            "                       <springform:checkbox id='1-1' path='userList[${idx.index}].enableUserAssignId' cssStyle='display: block;'></springform:checkbox>" +
            "                    </td></tr>"+
            "           </c:forEach>"+
            "       <tr>" +
            "           <td><input type='reset' value='Reset' /></td><td><input type='submit' value='Submit' class='task-assign-unassign-wizard-submit' /></td>" +
            "       </tr>" +
            "   </table>"+
            "</springform:form>"+
            "</div>")
            .dialog({
                resizable: false,
                height:340,
                modal: true,
                buttons: {
                    "Confirm": function() {
                        $( this ).dialog( "close" );
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
}*/

function createDialog(title, text) {
    return $("<div class='dialog' title='" + title + "'><p>" + text + "</p></div>")
            .dialog({
                resizable: false,
                height:140,
                modal: true,
                buttons: {
                    "Confirm": function() {
                        $( this ).dialog( "close" );
                    },
                    Cancel: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
}

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