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

        <div style="display: block;  ">
            <div class="form-messages">
                <span class="form-title">Edit Profile</span>

                <c:if test="${param.error}">
                    <span class="message-error">${replace this with server side message}</span>
                </c:if>

                <c:if test="${param.success}">
                    <span class="message-success"> ${replace this with server side message}</span>
                </c:if>
            </div>
            <div>
                <form:form action="${pageContext.request.contextPath}/users/profile-edit" method="post" commandName="editUserProfileWrapper" >
                    <div class="form-wrapper jQeffect-show-clip" >

                        <div class="inner-form-wrapper" >

                             <h2>Personal</h2>
                            <form:hidden path="userId"/>
                            <table>
                                <tbody>
                                <tr>
                                    <td class="form-input-description">
                                        Avatar:
                                    </td>
                                    <td class="form-input-view">
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                        <br />
                                        <input type="file" />
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
                                    <td class="form-input-description">
                                        Address:
                                    </td>

                                    <td>
                                        <form:input path="address"  cssClass="form-input"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Contact Number:
                                    </td>

                                    <td>
                                        <form:input path="contactNumber"  cssClass="form-input"/>
                                    </td>

                                </tr>
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