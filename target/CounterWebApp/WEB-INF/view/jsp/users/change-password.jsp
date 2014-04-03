<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    Change password
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
                <span class="form-title">Password Change</span>

                <c:if test="${error}">
                    <span class="message-error">${errorMsg}</span>
                </c:if>
                <c:if test="${success}">
                    <span class="message-success">${successMsg}</span>
                </c:if>
            </div>
            <div>
                <form:form action="${pageContext.request.contextPath}/users/change-password" method="post" commandName="changePasswordWrapper" >
                    <div class="form-wrapper jQeffect-show-clip" >
                        <div class="inner-form-wrapper" >

                            <h2>Change Password</h2>
                            <form:hidden path="userId"/>
                            <table>
                                <tbody>
                                <tr>
                                    <td class="form-input-description">
                                        Old Password:
                                    </td>

                                    <td>
                                        <form:password path="oldPassword" cssClass="form-input" autocomplete="false"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        New Password:
                                    </td>

                                    <td>
                                        <form:password path="password1" cssClass="form-input" autocomplete="false"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Confirm Password:
                                    </td>

                                    <td>
                                        <form:password path="password2" cssClass="form-input" autocomplete="false"/>
                                    </td>

                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div class="form-wrapper jQeffect-show-clip">

                        <input type="submit" value="Update" />&nbsp;   &nbsp;
                        <input type="button" value="Back"/>

                    </div>
                </form:form>
            </div>
        </div>

    </div>
    <div style="display:inline-block; margin-left: 30px; vertical-align: middle;">

    </div>

</div>
<script>
function backToUsers(){
    $.ajax({
        url: "${pageContext.request.contextPath}/users/back-to-edit-user",
        cache: false,
        success: function(){alert('Success while request..');},
        error: function(){alert('Error while request..');}
    });
}
</script>


<c:import url="${mainDir}/common/footer.jsp" />