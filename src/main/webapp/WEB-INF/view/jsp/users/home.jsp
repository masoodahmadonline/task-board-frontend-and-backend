<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.login"/>
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body>
    <c:import url="${mainDir}/common/inner-header.jsp" />
<<<<<<< HEAD
    
   
    
     
            Welcome, ${sessionScope.userName}. <br />

            You are authorized to access the following boards: <br /><br />
        <c:forEach var="board" items="${boards}">
            <a href="${pageContext.request.contextPath}/boards/${board.id}" >${board.title}</a>
        </c:forEach><br /><br />
    <a href="${pageContext.request.contextPath}/users/create" >Create a new User</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/users/edit" >Edit Users Access </a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/users/profile-edit-personal" >Edit Personal Information</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/users/change-password" >Change Password</a>
    <br />
    <a href="${pageContext.request.contextPath}/reports/cards/by-status" >See reports for tasks by their status</a>
=======
>>>>>>> 8f78a5254026f7c7540782ba8b45b1b4a1058260



    <c:if test="${error}">
    <span class="message-error">${errorMsg}</span><br /><br />
    </c:if>
    <c:if test="${success}">
    <span class="message-success">${successMsg}</span><br /><br />
    </c:if>


    Welcome, ${sessionScope.userName}. <br />

    You are authorized to access the following boards: <br />
    <c:forEach var="board" items="${boards}"><br />
            <security:authorize access="@securityService.hasBoardViewPermission(${board.id})">
                <a href="${pageContext.request.contextPath}/boards/${board.id}" >${board.title}</a>
            </security:authorize>
            <security:authorize access="@securityService.hasUserAccessPermission(${board.id})">
                <c:url var="bId" value="/boards/delete-board">
                    <c:param name="bId" value="${board.id}" />
                </c:url>
                &nbsp;&nbsp;<a title="Delete Board" href='<c:out value="${bId}"/>'>Delete</a>
            </security:authorize>
    </c:forEach><br /><br />

    <security:authorize access="hasAnyRole('ACCOUNT_ADMIN_ROLE')">
        <a href="${pageContext.request.contextPath}/users/create" >Create a new User</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </security:authorize>
    <security:authorize access="hasAnyRole('ACCOUNT_ADMIN_ROLE')">
        <a href="${pageContext.request.contextPath}/users/edit" >Users Access</a>&nbsp;&nbsp;&nbsp;&nbsp;
    </security:authorize>
    <a href="${pageContext.request.contextPath}/users/profile-edit-personal" >Edit Personal Information</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/users/change-password" >Change Password</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <security:authorize access="@securityService.hasBoardCreatePermission()">
        <a href="${pageContext.request.contextPath}/boards/create" >Create a new Board</a>
    </security:authorize>
               
  <c:import url="${mainDir}/common/footer.jsp" />



