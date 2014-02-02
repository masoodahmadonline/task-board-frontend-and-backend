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

                <c:if test="${param.error}">
                    <span class="message-error">${replace this with server side message}</span>
                </c:if>

                <c:if test="${param.success}">
                    <span class="message-success"> ${replace this with server side message}</span>
                </c:if>
            </div>
            <div>
                <form>
                    <div class="form-wrapper jQeffect-show-clip" >

                        <div class="inner-form-wrapper" >


                            <input type="hidden" name="userIdhere" />



                            <table>
                                <tbody>
                                <tr>
                                    <td class="form-input-description">
                                        Role:
                                    </td>
                                    <td>
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        WIP limit:
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" value="10" />
                                    </td>
                                </tr>

                                <tr>
                                    <td class="form-input-description">
                                        Enabled:
                                    </td>
                                    <td>
                                        <select class="form-input" name="enabled" required="required">
                                            <option value="yes" selected="selected">Yes</option>
                                            <option value="no">No</option>
                                        </select>
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
                </form>
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


