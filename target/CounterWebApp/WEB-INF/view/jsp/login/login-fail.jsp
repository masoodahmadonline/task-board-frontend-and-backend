<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    Login Failed
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body>
    <c:import url="${mainDir}/common/inner-header.jsp" />
    
        login fail.
    
     <c:import url="${mainDir}/common/footer.jsp" />
