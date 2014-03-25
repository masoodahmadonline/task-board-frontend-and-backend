<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:forEach var="box" items="${box.childBoxList}">
    <script>console.log("1-boxid: "+ ${box.id});</script>
    <div id="boxid-${box.id}" class="box box-${box.type}" >
        <div class="box-title" title="${box.description}">
                      <span>
                          <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')">
                          <div class="drop-menu-button">&#x25be;&nbsp;
                              <ul class="drop-menu-options">
                                  <li style="width: auto;" class="create-task-wizard"><a href="#">Create a task</a></li>
                                  <li style="width: auto;"><a href="#" class="create-box-wizard">Create child box</a></li>
                                  <li style="width: auto;"><a href="#" class="edit-box-wizard">Edit this box</a></li>
                                  <li><a href="#" class="delete-box-wizard">Delete this box</a></li>
                              </ul>
                          </div>
                          </security:authorize>
                          <span class="box-title-text">${box.title}</span>
                      </span>
        </div>
        <div class="box-body">
            <c:set var="box" value="${box}" scope="request" />
            <c:if test="${not empty box.childBoxList}"><script>console.log("2-boxid: "+ ${box.id});</script></c:if>
            <jsp:include page="box-template.jsp">
                <jsp:param name="box" value="${box}" />
            </jsp:include>
            <c:forEach var="task" items="${box.taskList}">
                <div class="task task-status-${task.status}" id="taskid-${task.id}">
                    <div class="task-title" title="${task.title}">
                       <span>
                          <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')">
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
                                  <li><a href="#">Set status</a>
                                      <ul>
                                          <li><a href="#" class="task-status-new-button">New</a></li>
                                          <li><a href="#" class="task-status-in-process-button">In-process</a></li>
                                          <li><a href="#" class="task-status-in-issues-button">In-issues</a></li>
                                          <li><a href="#" class="task-status-completed-button">Completed</a></li>
                                      </ul>
                                  </li>
                                  <li><a href="#" class="delete-task-wizard">Delete this task</a></li>
                              </ul>
                          </div>
                          </security:authorize>
                          ${task.title}
                      </span>
                    </div>
                    <div class="task-body">
                        <c:if test="${not empty task.attachmentList}">
                            <div class="attachment">
                                <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')">
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
                                </security:authorize>
                            </div>
                        </c:if>
                        <div class="task-priority task-priority-${task.priority}" id="priorityId-${task.id}"></div>
                        <c:set var="uSize" value="${task.userSize}" scope="request" />
                        <c:if test="${uSize != null }">
                            <c:if test="${uSize == '1' }">
                                <div class="user-icon1" id="userIconId1"></div>
                            </c:if>
                            <c:if test="${uSize == '2' }">
                                <div class="user-icon1" id="userIconId1"></div>
                                <div class="user-icon2" id="userIconId2"></div>
                            </c:if>
                        </c:if>

                        ${task.description}
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</c:forEach>