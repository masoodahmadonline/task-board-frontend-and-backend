<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    Report by card dates and status
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body>
    <c:import url="${mainDir}/common/inner-header.jsp" />
    
   		
	        <table>
	       		<tbody>
		        	<tr>
		        		<td style="width:200px;">
		        			Board
		        		</td>
		        		<td style="width:200px;">
		        			Date From
		        		</td>
		        		<td style="width:200px;">
		        			Date to
		        		</td>
		        		<td style="width:100px;">
		        			order by<br />
		        			card status?
		        		</td>
		        		
		        		
		        	</tr>
	        	</tbody>
      		</table>  
      	
        <c:forEach var="board" items="${boards}">
        <form method="post" action="${pageContext.request.contextPath}/report/pdf">
	        <table>
	       		<tbody>
                    <security:authorize access="@securityService.hasUserAccessPermission(${board.id})">
                        <tr>
                            <td style="width:200px;">
                                <a href="${pageContext.request.contextPath}/boards/${board.id}" >${board.title}</a>
                                <input type="hidden" name="boardId" value="${board.id}" />
                            </td>
                            <td style="width:200px;">
                                <input type="date" name="startDate" />
                            </td>
                            <td style="width:200px;">
                                <input type="date" name="endDate"/>
                            </td>
                            <td style="width:100px;">
                                <input id="${board.id}" name="orderByStatus" type="checkbox" /><label for="${board.id}" class="priv-checkbox-this"></label>
                            </td>

                            <td>
                                <input type="submit" value="View Report" />
                            </td>
                        </tr>
                    </security:authorize>
	        	</tbody>
      		</table>  
      	</form>	
           
        </c:forEach><br /><br />
    	
    		
               
  <c:import url="${mainDir}/common/footer.jsp" />



