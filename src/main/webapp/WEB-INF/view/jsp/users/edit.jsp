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
                <span class="form-title"><spring:message code="title.editeUser"/></span>

                <c:if test="${error}">
                    <span class="message-error">${errorMsg}</span>
                </c:if>
                <c:if test="${success}">
                    <span class="message-success">${successMsg}</span>
                </c:if>
            </div>
            <div>
                <form:form action="${pageContext.request.contextPath}/users/edit" method="post" commandName="editUserWrapper" >
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


                            <table>
                                <c:forEach items="${editUserWrapper.userList}" var="wrapper" varStatus="idx">
                                    <form:hidden path="userList[${idx.index}].userId"></form:hidden>
                                    <tr>
                                        <td>
                                            <form:checkbox id="1" path="userList[${idx.index}].enableUserEditId" cssStyle="display: inline-block; background-color: #fcefa1; box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.7) inset;"></form:checkbox>
                                        </td>
                                        <td>
                                            <img src="${resourcesDir}/images/avatar-small.png" height="40px;" />
                                        </td>
                                        <td>
                                            <span style="width:200px;  display:inline-block; text-align: left; font-weight: bold">${wrapper.firstName}&nbsp;${wrapper.lastName}</span> <br />
                                            <span style="width:200px;  display:inline-block; text-align: left;">${wrapper.email}</span>
                                        </td>
                                        <td>
                                            <span style="width:100px;  display:inline-block; text-align: right; padding: 5px;">${wrapper.roleName}</span>
                                        </td>
                                        <td>
                                            <span style="width: 40px;  display:inline-block; text-align: center;padding: 5px;">WIP: ${wrapper.wip}</span>
                                        </td>
                                        <td>
                                            <c:url var="uId" value="profile-edit-personal">
                                                <c:param name="uId" value="${wrapper.userId}" />
                                            </c:url>
                                            <a href='<c:out value="${uId}"/>'>Edit User</a>
                                        </td>
                                    </tr>
                                </c:forEach>


                            </table>
                        </div>

                    </div>
                    <div class="form-wrapper jQeffect-show-clip">

                        <br />
                        <br />
                        <input type="submit" value="Edit Role/WIP" />&nbsp;   &nbsp;
                        <input id="resetId" type="reset" value="Reset" />
                        <input id="selectAll" type="button" value="Select All">
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
        $("#resetId").click(function () {
            $("input[type=checkbox]").prop('checked', false)
        });
    });
</script>


<c:import url="${mainDir}/common/footer.jsp" />