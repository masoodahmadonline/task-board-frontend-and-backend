<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="box" items="${box.childBoxList}">
    <script>console.log("1-boxid: "+ ${box.id});</script>
    <div id="boxid-${box.id}" class="box box-${box.type}" >
        <div class="box-title" title="${box.description}">
                      <span>
                          <div class="drop-menu-button">&#x25be;&nbsp;
                              <ul class="drop-menu-options">
                                  <li style="width: auto;" class="create-task-wizard"><a href="#">Create a task</a></li>
                                  <li style="width: auto;"><a href="#" class="create-box-wizard">Create child box</a></li>
                                  <li class="ui-state-disabled"><a href="#">Edit this box</a></li>
                                  <li><a href="#" class="delete-box-wizard">Delete this box</a></li>
                              </ul>
                          </div>
                          ${box.title}
                      </span>
        </div>
        <div class="box-body">
            <c:set var="box" value="${box}" scope="request" />
            <c:if test="${not empty box.childBoxList}"><script>console.log("2-boxid: "+ ${box.id});</script></c:if>
            <jsp:include page="box-template.jsp">
                <jsp:param name="box" value="${box}" />
            </jsp:include>
            <c:forEach var="task" items="${box.taskList}">
                <div class="task" id="taskid-${task.id}">
                    <div class="task-title" title="${task.title}">
                       <span>
                          <div class="drop-menu-button">&#x25be;&nbsp;
                              <ul class="drop-menu-options">
                                  <li><a href="#" class="task-assign-unassign-wizard">Assign/Unassign</a></li>
                                  <li><a href="#" class="file-attachment-wizard">Attach file</a></li>
                                  <li class="ui-state-disabled"><a href="#">Edit this task</a></li>
                                  <li><a href="#">Set priority</a>
                                      <ul>
                                          <li><a href="#" class="task-priority-critical-button">Critical</a></li>
                                          <li><a href="#" class="task-priority-high-button">High</a></li>
                                          <li><a href="#" class="task-priority-normal-button">Normal</a></li>
                                          <li><a href="#" class="task-priority-low-button">Low</a></li>
                                      </ul>
                                  </li>
                                  <li><a href="#" class="delete-task-wizard">Delete this task</a></li>
                              </ul>
                          </div>
                          ${task.title}
                      </span>
                    </div>
                    <div class="task-body">
                        <c:if test="${not empty task.attachmentList}">
                            <div class="attachment">
                                <div id="boo" class="attachment-content" class="forms-for-board" title="Files attached to this task">
                                    <form>
                                        <table>
                                            <tbody style="margin-right:5px;margin-left: 15px; border: red thin solid background: blue;">
                                            <c:forEach var="attachment" items="${task.attachmentList}">
                                                <tr id="attachmentid-${attachment.id}">
                                                    <td>
                                                        ${attachment.name} - ${attachment.description}
                                                    </td>
                                                    <td style="min-width: 5px;">
                                                        &nbsp;
                                                    </td>
                                                    <td>
                                                        <a title="Download file" class="download-button" href="${pageContext.request.contextPath}/attachment/download/${attachment.id}"></a>
                                                    </td>
                                                    <td>
                                                        <div title="Delete file" class="delete-button delete-attachment"></div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                        <div class="task-priority task-priority-${task.priority}" id="priorityId-${task.id}"></div>
                        <%--<c:if test="${not empty task.userList}">
                            <c:forEach var="user" items="${task.userList}">
                                <div class="user-icon" id="userId-${user.id}"></div>
                            </c:forEach>
                        </c:if>--%>
                        ${task.description}
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</c:forEach>