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

        <div style="display: block;  ">
            <div class="form-messages">
                <span class="form-title">View Profile</span>

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

                             <h2>Personal</h2>
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td class="form-input-description">
                                        Avatar:
                                    </td>
                                    <td class="form-input-view">
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        First Name:
                                    </td>

                                    <td class="form-input-view">
                                        Masood
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Last Name:
                                    </td>

                                    <td class="form-input-view">
                                        Ahmad
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Email:
                                    </td>

                                    <td class="form-input-view">
                                        masood@syncsysllc.com
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Address:
                                    </td>

                                    <td class="form-input-view">
                                        256c Model Town
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        City:
                                    </td>

                                    <td class="form-input-view">
                                        Lahore
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        State/Province:
                                    </td>

                                    <td class="form-input-view">
                                        Punjab
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Country:
                                    </td>

                                    <td class="form-input-view">
                                        Pakistan
                                    </td>

                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        Phone (landline):
                                    </td>

                                    <td class="form-input-view">
                                        042 123456789
                                    </td>

                                </tr>

                                <tr>  <tr>
                                    <td class="form-input-description">
                                        Phone (office):
                                    </td>

                                    <td class="form-input-view">
                                        042 987654321
                                    </td>

                                </tr>
                                    <td class="form-input-description">
                                        Phone (Cell):
                                    </td>

                                    <td class="form-input-view">
                                        03134955672
                                    </td>

                                </tr>
                                </tbody>
                            </table>
                            <hr />
                            <h2>Technical</h2>
                            <table>
                                <tbody>
                                <tr>
                                    <td class="form-input-description">
                                         Role:
                                    </td>
                                    <td class="form-input-view">
                                         Admin
                                    </td>
                                </tr>
                                <tr>
                                    <td class="form-input-description">
                                        WIP limit:
                                    </td>
                                    <td class="form-input-view">
                                        10
                                    </td>
                                </tr>

                                <tr>
                                    <td class="form-input-description">
                                        Enabled:
                                    </td>
                                    <td class="form-input-view">
                                        Yes
                                    </td>
                                </tr>
                                </tbody>
                            </table>


                        </div>

                    </div>
                    <div class="form-wrapper jQeffect-show-clip">

                        <input type="submit" value="Edit" />&nbsp;   &nbsp;

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