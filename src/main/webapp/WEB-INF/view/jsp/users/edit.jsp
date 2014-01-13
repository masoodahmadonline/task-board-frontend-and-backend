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
                <span class="form-title"><spring:message code="title.editeUser"/></span>

                <c:if test="${param.error}">
                    <span class="message-error">${replace this with server side message}</span>
                </c:if>

                <c:if test="${param.success}">
                    <span class="message-success">User(s) edited successfully. ${replace this with server side message}</span>
                </c:if>
            </div>
            <div>

                <div class="form-wrapper jQeffect-show-clip" >

                    <div class="inner-form-wrapper" >
                        <a href="#">a</a> &nbsp;&nbsp; <a href="#">b</a> &nbsp;&nbsp; <a href="#">c</a> &nbsp;&nbsp;
                        <a href="#">d</a> &nbsp;&nbsp; <a href="#">e</a> &nbsp;&nbsp; <a href="#">f</a> &nbsp;&nbsp;
                        <a href="#">g</a> &nbsp;&nbsp; <a href="#">h</a> &nbsp;&nbsp; <a href="#">i</a> &nbsp;&nbsp;
                        <a href="#">j</a> &nbsp;&nbsp; <a href="#">k</a> &nbsp;&nbsp; <a href="#">l</a> &nbsp;&nbsp;
                        <a href="#">m</a> &nbsp;&nbsp; <a href="#">n</a> &nbsp;&nbsp; <a href="#">o</a> &nbsp;&nbsp;
                        <a href="#">p</a> &nbsp;&nbsp; <a href="#">q</a> &nbsp;&nbsp; <a href="#">r</a> &nbsp;&nbsp;
                        <a href="#">s</a> &nbsp;&nbsp; <a href="#">t</a> &nbsp;&nbsp; <a href="#">u</a> &nbsp;&nbsp;
                        <a href="#">v</a> &nbsp;&nbsp; <a href="#">x</a> &nbsp;&nbsp; <a href="#">y</a> &nbsp;&nbsp;
                        <a href="#">z</a>

                        <br />
                        <br />







                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <table>
                                <tbody>
                                    <tr>
                                        <td style="width:40px;">

                                        </td>
                                        <td style="width:200px;">
                                              First Name, Last Name
                                        </td>
                                        <td style="width:200px;">
                                            Email, Role
                                        </td>
                                        <td>
                                            WIP
                                        </td>
                                        <td>

                                        </td>
                                        <td>

                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>



                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />

                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />

                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />


                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />

                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />


                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />

                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />

                        <form action="${pageContext.request.contextPath}/users/edit" method="post">
                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <input class="form-input" type="text" name="firstName" value="Masood" required="required" placeholder="<spring:message code="placeholder.firstName"/>"/> <br />
                                        <input class="form-input" type="text" name="lastName" value="Ahmad" placeholder="<spring:message code="placeholder.lastName"/>"/> <br />

                                    </td>
                                    <td>
                                        <input class="form-input" type="email" name="email" value="masood@template.com" required="required" placeholder="<spring:message code="placeholder.email"/>"/> <br />
                                        <select name="role" required="required">
                                            <option value="organizationAdmin">Organization Admin</option>
                                            <option value="admin" selected="selected">Admin</option>
                                            <option value="manager">Manager</option>
                                            <option value="user">User</option>
                                            <option value="reader">Reader</option>
                                            <option value="noaccess">No Access</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-input" style="width: 20px;" type="number" name="wip" value="10" /><br />
                                    </td>

                                    <td>
                                        <input type="submit" value="<spring:message code="form.text.button.update"/>" tabindex="7"/> <br />
                                        <input type="<spring:message code="form.text.button.reset"/>" tabindex="8"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                        <hr />


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
//            e.preventDefault();
//            pending ajax call until user module related backend is stable.
        });
    });
</script>


<c:import url="${mainDir}/common/footer.jsp" />