<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.users.create"/>
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body
        style=" border: transparent 1px solid;
                background-image: url('${resourcesDir}/images/edit-user-bg.png');
                background-repeat: no-repeat;
                background-attachment: fixed;

                ">
<c:import url="${mainDir}/common/inner-header.jsp" />



<div class="wrapper-for-form-block">
    <div class="form-block">

        <div style="display: block; ">
            <div class="form-messages">
                <span class="form-title">Edit Profile - Technical Information</span>

                <c:if test="${error}">
                    <span class="message-error">${errorMsg}</span>
                </c:if>
                <c:if test="${success}">
                    <span class="message-success">${successMsg}</span>
                </c:if>
            </div>
            <div>
                <form:form action="${pageContext.request.contextPath}/users/profile-edit-technical" method="post" commandName="editUserTechnicalWrapper" >
                    <div class="form-wrapper jQeffect-show-clip" >
                        <div class="inner-form-wrapper" >
                            <table>
                                <tbody>
                                <tr>
                                    <td colspan="2" style="max-width: 600px;">
                                        <c:forEach items="${editUserTechnicalWrapper.userList}" var="wrapper" varStatus="idx">
                                            <span style="background-color: #bfbfbf; -moz-border-radius: 15px; border-radius: 15px; font-size: 14px; width: 100px; max-width: 100px;">
                                                &nbsp;${wrapper.firstName}&nbsp;${wrapper.lastName}&nbsp;
                                            </span>&nbsp;
                                        </c:forEach>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-wrapper jQeffect-show-clip" >
                        <div class="inner-form-wrapper" >
                            <table>
                                <tbody>


                                <tr>
                                    <td class="form-input-description">
                                        Role:
                                    </td>
                                    <td>
                                        <form:select path="roleId" >
                                            <form:options items="${roleList}" />
                                        </form:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        WIP limit:
                                    </td>
                                    <td>
                                        <form:input path="wip" size="5" maxlength="4" cssStyle="text-align: left;"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div class="form-wrapper jQeffect-show-clip">

                        <input type="submit" value="Update" />&nbsp; &nbsp;
                        <input type="reset" value="Reset" />

                    </div>
                </form:form>
            </div>
        </div>

    </div>
    <div style="display:inline-block; margin-left: 30px; vertical-align: middle;">

    </div>

</div>
<script>
    $(document).ready(function(){
        $("#selectAll").click(function () {
            $("input[type=checkbox]").prop('checked', true)
        });
    });
</script>


<c:import url="${mainDir}/common/footer.jsp" />


