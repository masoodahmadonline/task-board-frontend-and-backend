<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.login"/>
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body>
    <c:import url="${mainDir}/common/inner-header.jsp" />
    
   
    
     
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

               
  <c:import url="${mainDir}/common/footer.jsp" />



