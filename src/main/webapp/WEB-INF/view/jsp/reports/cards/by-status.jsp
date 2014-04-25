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
    
   		     <h2>Task status and details report.</h2>
            <br />
	        <table>
	       		<tbody>
		        	<tr style="background-color: #dadada; font-weight: bold;">
		        		<td style="width:200px;">
		        			Board
		        		</td>
		        		<td style="width:200px;">
		        			Date From
		        		</td>
		        		<td style="width:200px;">
		        			Date to
		        		</td>
                        <td style="width:50px;">
		        			order tasks<br />by date
		        		</td>
                        <td style="width:50px;">
                            order tasks<br />by status
                        </td>
                        <td style="width:50px; border-right: thin #dadada solid">
                            order tasks<br />by priority
                        </td>
                        <td style="width:50px; border-left: thin #dadada solid">
                            view as<br />pdf report
                        </td>
                        <td style="width:50px;">
                            view as<br />excel report
                        </td>
		        		
		        		
		        	</tr>
	        	</tbody>
      		</table>  
      	
        <c:forEach var="board" items="${boards}">
        <form method="post" action="${pageContext.request.contextPath}/report/task-details">
	        <table>
	       		<tbody>
                    <security:authorize access="@securityService.hasUserAccessPermission(${board.id})">
                        <tr>
                            <td style="width:200px;">
                                <a href="${pageContext.request.contextPath}/boards/${board.id}" >${board.title}</a>
                                <input type="hidden" name="boardId" value="${board.id}" />
                            </td>
                            <td style="width:200px;">
                                <input class="date-picker" name="startDate" required="required" />
                            </td>
                            <td style="width:200px;">
                                <input class="date-picker" name="endDate" required="required"/>
                            </td>
                            <td style="width:50px;">
                                <input name="orderBy" type="radio" value="date" checked="checked" />
                            </td>

                            <td style="width:50px;">
                                <input name="orderBy" type="radio" value="status"/>
                            </td>

                            <td style="width:50px; border-right: thin #dadada solid">
                                <input name="orderBy" type="radio" value="priority"/>
                            </td>

                            <td style="width:50px; border-left: thin #dadada solid">
                                <input name="viewAs" type="radio" value="pdf" checked="checked" />
                            </td>

                            <td style="width:50px;">
                                <input name="viewAs" type="radio" value="excel" />
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
    	<script>
            $(function() {
                $( ".date-picker" ).datepicker();
            });
        </script>

    		
               
  <c:import url="${mainDir}/common/footer.jsp" />



