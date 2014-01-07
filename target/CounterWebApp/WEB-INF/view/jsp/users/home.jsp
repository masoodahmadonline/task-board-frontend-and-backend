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
        </c:forEach>
               
  <c:import url="${mainDir}/common/footer.jsp" />



