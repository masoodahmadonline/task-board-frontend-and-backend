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
                 <form>
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

                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                    </td>

                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <span style="width:200px;  display:inline-block; text-align: left;">Masood Ahmad</span> <br />
                                        <span style="width:200px;  display:inline-block; text-align: left;">masood@syncsysllc.com</span> <br />

                                    </td>
                                    <td>
                                        <span style="width:100px;  display:inline-block; text-align: right; padding: 5px;">Admin</span>
                                    </td>
                                    <td>
                                        <span style="width: 20px;  display:inline-block; text-align: center;padding: 5px;">WIP: 10</span>
                                    </td>

                                    <td>
                                        <a href="#" style="display:inline-block; text-align: center;padding: 5px;">Edit User</a> <br />
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr />

                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-this"></label>
                                    </td>

                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <span style="width:200px;  display:inline-block; text-align: left;">Farhan Bajwa</span> <br />
                                        <span style="width:200px;  display:inline-block; text-align: left;">farhan@syncsysllc.com</span> <br />

                                    </td>
                                    <td>
                                        <span style="width:100px;  display:inline-block; text-align: right; padding: 5px;">Manager</span>
                                    </td>
                                    <td>
                                        <span style="width: 20px;  display:inline-block; text-align: center;padding: 5px;">WIP: 5</span>
                                    </td>

                                    <td>
                                        <a href="#" style="display:inline-block; text-align: center;padding: 5px;">Edit User</a> <br />
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr />



                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-this"></label>
                                    </td>

                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <span style="width:200px;  display:inline-block; text-align: left;">Muhammad Ismail</span> <br />
                                        <span style="width:200px;  display:inline-block; text-align: left;">ismail@syncsysllc.com</span> <br />

                                    </td>
                                    <td>
                                        <span style="width:100px;  display:inline-block; text-align: right; padding: 5px;">User</span>
                                    </td>
                                    <td>
                                        <span style="width: 20px;  display:inline-block; text-align: center;padding: 5px;">WIP: 2</span>
                                    </td>

                                    <td>
                                        <a href="#" style="display:inline-block; text-align: center;padding: 5px;">Edit User</a> <br />
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr />

                            <input type="hidden" name="userIdhere" />
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <input id="4" type="checkbox" /><label for="4" class="priv-checkbox-this"></label>
                                    </td>

                                    <td>
                                        <img src="${resourcesDir}/images/avatar-small.png" />
                                    </td>
                                    <td>
                                        <span style="width:200px;  display:inline-block; text-align: left;">God help otb</span> <br />
                                        <span style="width:200px;  display:inline-block; text-align: left;">HELP@syncsysllc.com</span> <br />

                                    </td>
                                    <td>
                                        <span style="width:100px;  display:inline-block; text-align: right; padding: 5px;">SomeOther</span>
                                    </td>
                                    <td>
                                        <span style="width: 20px;  display:inline-block; text-align: center;padding: 5px;">WIP: 0</span>
                                    </td>

                                    <td>
                                        <a href="#" style="display:inline-block; text-align: center;padding: 5px;">Edit User</a> <br />
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <hr />



                        </div>

                    </div>
                    <div class="form-wrapper jQeffect-show-clip">

                        <a href="#">1</a> &nbsp;&nbsp; <a href="#">2</a> &nbsp;&nbsp; <a href="#">3</a> &nbsp;&nbsp;
                        <a href="#">4</a> &nbsp;&nbsp; <a href="#">5</a> &nbsp;&nbsp; <a href="#">6</a> &nbsp;&nbsp;
                        <a href="#">7</a> &nbsp;&nbsp;

                        <br />
                        <br />
                        <input type="submit" value="Edit Role/WIP" />&nbsp;   &nbsp;
                        <input type="reset" value="Reset" />
                        <input id="selectAll" type="button" value="Select All">
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