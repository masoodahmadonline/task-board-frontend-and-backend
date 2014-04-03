<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    Create user
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
                <span class="form-title"><spring:message code="title.createUser"/></span>

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
                        <form:form action="${pageContext.request.contextPath}/users/create" method="post" commandName="createUserWrapper">
                            <table>
                                <tbody>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">User Name:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:input cssClass="form-input" path="email" tabindex="1" autocomplete="false" placeholder="Email Address (Will be login ID)" maxlength="100" />
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">First Name:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:input cssClass="form-input" path="firstName" autocomplete="false" maxlength="100" placeholder="First Name" tabindex="2" />
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">Last Name:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:input cssClass="form-input" path="lastName" autocomplete="false" maxlength="100" placeholder="Last Name" tabindex="3" />
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">Company:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:select path="companyId" cssClass="form-input" >
                                            <form:options items="${companyList}" />
                                        </form:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">Password:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:password id="password1" path="password1" autocomplete="false" cssClass="form-input" placeholder="Password" maxlength="100" tabindex="4" />
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; text-align: right">Confirm Password:&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        <form:password id="password2" path="password2" autocomplete="false" cssClass="form-input" placeholder="Re-type Password" maxlength="100" tabindex="5" />
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"></td>
                                    <td style="text-align: left">
                                        <form:checkbox path="enableUserId" cssStyle="display: inline-block;" tabindex="6" placeholder="Chekcbox" />
                                        <spring:message code="form.text.checkbox.enableUser"/>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"></td>
                                    <td style="text-align: left">
                                        <form:checkbox path="canCreateBoard" cssStyle="display: inline-block;" tabindex="7" placeholder="Chekcbox" />
                                        <spring:message code="form.text.checkbox.isBoardCreator"/>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"></td>
                                    <td style="text-align: left">
                                        <form:checkbox path="accountAdmin" cssStyle="display: inline-block;" tabindex="8" placeholder="Chekcbox" />
                                        <spring:message code="form.text.checkbox.isAdmin"/>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.creatUser"/>" tabindex="7"/>
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="9"/>
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
<script>
    $(document).ready(function(){
        $('form').on('submit', function(e){
            var message = "";
            var show = false;
            if(  $("#password1").val() != $("#password2").val() ){
                show = true;
                message = "<spring:message code="error.confirmPassowordMissmatch"/>";
                e.preventDefault();
            }else if($("#password1").val().length < 5){
                show = true;
                message = "<spring:message code="error.emptyOrShortPassword"/>";
                e.preventDefault();
            }

            if(show){
//                alert(message);
                $(".form-messages").append('<span class="message-error">'+message+'</span>');
            }

        });

    });
</script>


<c:import url="${mainDir}/common/footer.jsp" />