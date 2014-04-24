<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="/resources/tld/datagrid.tld" prefix="grd" %>
<%@ page import="com.freeware.gridtag.*" %>
<%
    int intCurr = 1;
    int intSortOrd = 0;
    String strTmp = null;
    String strSortCol = null;
    String strSortOrd = "ASC";
    boolean blnSortAsc = true;
    strTmp = request.getParameter("txtCurr");
    try{
        if (strTmp != null)
            intCurr = Integer.parseInt(strTmp);
    }
    catch (NumberFormatException NFEx)
    {
    }
    strSortCol = request.getParameter("txtSortCol");
    strSortOrd = request.getParameter("txtSortAsc");
    if (strSortCol == null) strSortCol = "taskPriority";
    if (strSortOrd == null) strSortOrd = "ASC";
    blnSortAsc = (strSortOrd.equals("ASC"));
%>
<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    Users Home
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body>
    <c:import url="${mainDir}/common/inner-header.jsp" />


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
                &nbsp;&nbsp;<a title="Delete Board" href='<c:out value="${bId}"/>'
                               onclick="return confirm('Deleting this Board will result in the removal of all board related data. Are you sure you wish to delete this Board?')">Delete</a>
                <c:url var="boardId" value="/boards/edit-board">
                    <c:param name="boardId" value="${board.id}" />
                </c:url>
                &nbsp;&nbsp;<a title="Edit Board" href='<c:out value="${boardId}"/>'>Edit</a>
            </security:authorize>
    </c:forEach><br /><br />

    <%--<security:authorize access="hasAnyRole('ACCOUNT_ADMIN_ROLE')">
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

    <br />
    <a href="${pageContext.request.contextPath}/reports/cards/by-status" >See reports for tasks by their status</a>
    <br /><br />--%>

    <%--{M-A}: This code is no working for me. So in order to run it, I have to commment it out. This needs to be jointly fixed by team and/or creater of this code. Surely, it runs on the online server. But the issue needs to be find out.--%>
    <%--<c:if test="${!empty taskList}">--%>
    <%--<table width="905" align="center" cellpadding="0" cellspacing="0">--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<form NAME="frmMain" METHOD="post">--%>
                    <%--<grd:dbgrid id="tblStat" name="tblStat" width="100" pageSize="5" border="0" cellSpacing="1" cellPadding="1"--%>
                                <%--currentPage="<%=intCurr%>" dataMember="${taskList}" dataSource="${taskList}" cssClass="gridTable">--%>
                        <%--<grd:gridpager imgFirst="${resourcesDir}/images/First.gif" imgPrevious="${resourcesDir}/images/Previous.gif"--%>
                                       <%--imgNext="${resourcesDir}/images/Next.gif" imgLast="${resourcesDir}/images/Last.gif"/>--%>
                        <%--&lt;%&ndash;<grd:gridsorter sortColumn="<%=strSortCol%>" sortAscending="<%=blnSortAsc%>"--%>
                            <%--imageAscending="${resourcesDir}/images/ImgAsc.gif" imageDescending="${resourcesDir}/images/ImgDesc.gif"/>&ndash;%&gt;--%>
                        <%--<grd:rownumcolumn headerText="" width="5" HAlign="center"/>--%>
                        <%--<grd:textcolumn dataField="taskName" headerText="Task Name" width="25" sortable="true"/>--%>
                        <%--<grd:textcolumn dataField="taskPriority" headerText="Priority" width="15" sortable="true"/>--%>
                        <%--<grd:textcolumn dataField="taskStatus" headerText="Status" width="15"/>--%>
                        <%--<grd:textcolumn dataField="taskAssignedBy" headerText="Assigned By" width="15"/>--%>
                        <%--<grd:datecolumn dataField="taskAssignedDate" headerText="Assigned Date" width="20" dataFormat="dd-mm-yyyy"/>--%>
                    <%--</grd:dbgrid>--%>
                    <%--<input TYPE="hidden" NAME="txtCurr" VALUE="<%=intCurr%>">--%>
                    <%--<input TYPE="hidden" NAME="txtSortCol" VALUE="<%=strSortCol%>">--%>
                    <%--<input TYPE="hidden" NAME="txtSortAsc" VALUE="<%=strSortOrd%>">--%>
                <%--</form>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
    <%--</c:if>--%>

    <c:import url="${mainDir}/common/footer.jsp" />



