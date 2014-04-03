<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    Edit personal access
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

        <div style="display: block;  ">
            <div class="form-messages">
                <span class="form-title">Edit Profile</span>

                <c:if test="${error}">
                    <span class="message-error">${errorMsg}</span>
                </c:if>
                <c:if test="${success}">
                    <span class="message-success">${successMsg}</span>
                </c:if>
            </div>
            <div>
                <form:form action="${pageContext.request.contextPath}/users/profile-edit-personal-access" method="post" commandName="editUserProfileWrapper" enctype="multipart/form-data" >
                    <div class="form-wrapper jQeffect-show-clip" >
                        <div class="inner-form-wrapper" >

                            <h2>Personal Information</h2>
                            <form:hidden path="userId"/>
                            <form:hidden path="imageName"/>
                            <table>
                                <tbody>
                                <tr>
                                    <td class="">

                                    </td>
                                    <td>
                                        <img alt="${resourcesDir}${imageName}" class="top-header-image" src="${resourcesDir}${imageName}" height="150" width="200"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <form:input path="imageData" id="image" type="file" cssClass="form-input" cssStyle="border: none;" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        First Name:
                                    </td>

                                    <td>
                                        <form:input path="firstName" cssClass="form-input"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Last Name:
                                    </td>

                                    <td>
                                        <form:input path="lastName" cssClass="form-input" />
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Email:
                                    </td>

                                    <td>
                                        <form:input path="email"  cssClass="form-input"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td style="text-align: right"></td>
                                    <td style="text-align: left">
                                        <form:checkbox path="enableUserId" cssStyle="display: inline-block;" tabindex="6" placeholder="Chekcbox" />
                                        <spring:message code="form.text.checkbox.enableUser"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"></td>
                                    <td style="text-align: left">
                                        <form:checkbox path="canCreateBoard" cssStyle="display: inline-block;" tabindex="7" placeholder="Chekcbox" />
                                        <spring:message code="form.text.checkbox.isBoardCreator"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td style="text-align: right"></td>
                                    <td style="text-align: left">
                                        <form:checkbox path="accountAdmin" cssStyle="display: inline-block;" tabindex="8" placeholder="Chekcbox" />
                                        <spring:message code="form.text.checkbox.isAdmin"/>
                                    </td>

                                </tr>
                                <tr><td colspan="2">&nbsp;</td></tr>

                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div class="form-wrapper jQeffect-show-clip">

                        <input type="submit" value="Update" />&nbsp;   &nbsp;
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