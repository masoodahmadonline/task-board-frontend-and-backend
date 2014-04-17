<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:forEach var="box" items="${box.childBoxList}">
    <script>console.log("1-boxid: "+ ${box.id});</script>
    <div id="boxid-${box.id}" class="box box-${box.type}" >
        <div class="box-title" title="${box.description}">
                      <span>
                          <security:authorize access="@securityService.hasBoxTaskEditPermission(${box.id})">
                          <div class="drop-menu-button">&#x25be;&nbsp;
                              <ul class="drop-menu-options">
                                  <li style="width: auto;" class="create-task-wizard"><a href="#">Create a task</a></li>
                                  <li style="width: auto;"><a href="#" class="create-box-wizard">Create child box</a></li>
                                  <li style="width: auto;"><a href="#" class="edit-box-wizard">Edit this box</a></li>
                                  <li><a href="#" class="delete-box-wizard">Delete this box</a></li>
                              </ul>
                          </div>
                          </security:authorize>
                          <span class="box-title-text">${box.title}</span><span class="box-description-text" style="display:none;">${box.description}</span>
                      </span>
        </div>
        <div class="box-body">
            <c:set var="box" value="${box}" scope="request" />
            <c:if test="${not empty box.childBoxList}"><script>console.log("2-boxid: "+ ${box.id});</script></c:if>
            <jsp:include page="box-template.jsp">
                <jsp:param name="box" value="${box}" />
            </jsp:include>
            <c:forEach var="task" items="${box.taskList}">
                <div class="task task-status-${task.status} tooltip" id="taskid-${task.id}"  title="Title: ${task.title} <br /><br />Description: ${task.description}" >
                    <div class="task-title">
                       <span>
                          <security:authorize access="@securityService.hasBoxTaskEditPermission(${box.id})">
                          <div class="drop-menu-button">&#x25be;&nbsp;
                              <ul class="drop-menu-options">
                                  <li><a href="#" class="task-assign-unassign-wizard">Assign/Unassign</a></li>
                                  <li><a href="#" class="file-attachment-wizard">Attach file</a></li>
                                  <li><a href="#" class="edit-task-wizard">Edit this task</a></li>
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
                          <span class="task-title-text">${task.title}</span>
                      </span>
                    </div>
                    <div class="task-body">
                        <c:if test="${not empty task.attachmentList}">
                            <div class="attachment">
                                <security:authorize access="@securityService.hasBoxTaskEditPermission(${box.id})">
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
                        <c:if test="${not empty task.userList}">
                            <div class="user-icon1" id="userIconId1">
                                <c:forEach var="us" items="${task.userList}" varStatus="status">
                                    <c:choose>
                                        <c:when test="${status.index == 0}">
                                            <img src="${resourcesDir}${us.imageName}" height="15px" width="15px"/>
                                        </c:when>
                                        <c:when test="${status.index == 1}">
                                            <img src="${resourcesDir}${us.imageName}" height="15px" width="15px"/>
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <security:authorize access="@securityService.hasBoxTaskEditPermission(${box.id})">
                                    <div id="boo2" class="user-content" class="forms-for-board" title="Users assigned to this task">
                                        <form>
                                            <table>
                                                <tbody style="margin-right:5px;margin-left: 15px; border: red thin solid background: blue;">
                                                <c:forEach var="user" items="${task.userList}">
                                                    <tr id="userid-${user.id}">
                                                        <td style="vertical-align: top">
                                                            ${user.firstName}&nbsp;${user.lastName}
                                                        </td>
                                                        <td style="min-width: 5px;">
                                                            &nbsp;
                                                        </td>
                                                        <td>
                                                            <img alt="${resourcesDir}${user.imageName}" class="top-header-image" src="${resourcesDir}${user.imageName}" height="50" width="50"/>
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
                        <span class="task-description-text">${task.description}</span>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</c:forEach>