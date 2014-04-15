<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.board.create"/>
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body
        style=" border: transparent 1px solid;
                background-image: url('${resourcesDir}/images/create-user-bg.png');
                background-repeat: no-repeat;
                background-attachment: fixed;

                ">
<c:import url="${mainDir}/common/inner-header.jsp" />



<div class="wrapper-for-form-block">
    <div class="form-block">

        <div style="display: block;  ">
            <div class="form-messages">
                <span class="form-title">${boardTitle}</span>

                <c:if test="${error}">
                    <span class="message-error">${errorMsg}</span>
                </c:if>
                <c:if test="${success}">
                    <span class="message-success">${successMsg}</span>
                </c:if>
            </div>
            <div>

                <div class="form-wrapper draggable jQeffect-show-clip" >

                    <div class="inner-form-wrapper" >
                        <form:form action="${pageContext.request.contextPath}/boards/create" method="post" commandName="createBoardWrapper">
                            <form:hidden path="boardId"></form:hidden>
                            <table>
                                <tbody>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">Board Name:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:input cssClass="form-input" path="boardName" tabindex="1" autocomplete="false" placeholder="Enter Board Name" maxlength="100" />
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">Board Description:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:input cssClass="form-input" path="boardDesc" tabindex="1" autocomplete="false" placeholder="Enter Board Description" maxlength="100" />
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">Company:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:hidden path="companyId"></form:hidden>
                                        <form:select path="companyId" cssClass="form-input" disabled="true">
                                            <form:options items="${companyList}" />
                                        </form:select>
                                    </td>
                                </tr>
                                <tr><td colspan="2">&nbsp;</td></tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.submit"/>" tabindex="7"/>
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                    <td></td>
                                </tr>
                                </tbody>
                            </table>
                        </form:form>
                    </div>

                </div>

            </div>
        </div>

    </div>
    <div style="display:inline-block; margin-left: 30px; vertical-align: middle;">

    </div>

</div>



<c:import url="${mainDir}/common/footer.jsp" />