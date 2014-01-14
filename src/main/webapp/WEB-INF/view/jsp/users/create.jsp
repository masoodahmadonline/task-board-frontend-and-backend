<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.users.create"/>
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

                <c:if test="${param.error}">
                    <span class="message-error">User creation failed ${replace this with server side message}</span>
                </c:if>
                <c:if test="${param.userExists}">
                    <span class="message-error">User already exists by that email ${replace this with server side message}</span>
                </c:if>
                <c:if test="${successMessage}">
                    <span class="message-success">User created successfully. Create another user? ${replace this with server side message}</span>
                </c:if>
            </div>
            <div>

                <div class="form-wrapper draggable jQeffect-show-clip" >

                    <div class="inner-form-wrapper" >
                        <form action="${pageContext.request.contextPath}/users/create" method="post">

                            <table>
                                <tbody>
                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            <input class="form-input" name="email" type="email" required="required" placeholder="<spring:message code="placeholder.email"/>"  tabindex="1" />
                                        </td>
                                        <td>

                                        </td>

                                    </tr>

                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            <input class="form-input" name="firstName" type="text" required="required" placeholder="<spring:message code="placeholder.firstName"/>" tabindex="2" />
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            <input class="form-input" name="lastName" type="text" required="required" placeholder="<spring:message code="placeholder.lastName"/>" tabindex="3" />
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            <input id="password1" name="password1" class="form-input" type="password" required="required" placeholder="<spring:message code="placeholder.password"/>" tabindex="4" />
                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            <input id="password2" name="password2" class="form-input" type="password" required="required" placeholder="<spring:message code="placeholder.retypePassword"/>" tabindex="5" />
                                        </td>
                                        <td>

                                        </td>


                                    </tr>

                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            <input id="1" name="enable" class="form-input" type="checkbox" checked="checked" tabindex="6" />
                                            <label for="1" class="priv-checkbox-this"></label>
                                            <spring:message code="form.text.checkbox.enableUser"/>

                                        </td>
                                        <td>

                                        </td>


                                    </tr>

                                    <tr>
                                        <td>

                                        </td>
                                        <td>
                                            <input type="submit" value="<spring:message code="form.text.button.creatUser"/>" tabindex="7"/>
                                            <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                        </td>

                                        <td>

                                        </td>

                                    </tr>

                                </tbody>
                            </table>
                        </form>
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
                message += "<spring:message code="error.confirmPassowordMissmatch"/>";
                e.preventDefault();
            }

            if($("#password1").empty() || $("#password1").val().length < 5){
                show = true;
                message += "<spring:message code="error.emptyOrShortPassword"/>";
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