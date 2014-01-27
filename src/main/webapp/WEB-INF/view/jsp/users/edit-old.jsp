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



                        <form:form action="${pageContext.request.contextPath}/users/edit" method="post" commandName="editUserWrapper" >

                            <table >

                                <c:forEach items="${editUserWrapper.userList}" var="wrapper" varStatus="idx">
                                    <form:hidden path="userList[${idx.index}].userId"></form:hidden>
                                    <tr>
                                        <td style="width: 30px; vertical-align: middle; " rowspan="2">
                                            <img src="${resourcesDir}/images/avatar-small.png" height="40px;" />
                                        </td>
                                        <td style="width: 200px; text-align: left; font-weight: bold; font-size: medium; vertical-align: bottom" >
                                                ${wrapper.firstName} &nbsp;${wrapper.lastName}
                                        </td>
                                        <td rowspan="2" style="width: 80px; vertical-align: middle">
                                            <form:select path="userList[${idx.index}].roleId" >
                                                <form:options items="${roleList}" />
                                            </form:select>
                                        </td>
                                        <td rowspan="2" style="width: 20px; vertical-align: middle">
                                            <form:input path="userList[${idx.index}].wip" size="5" maxlength="4"/>
                                        </td>
                                        <td rowspan="2" style="width: 40px; vertical-align: middle;">
                                            <form:checkbox path="userList[${idx.index}].enableUserId" cssStyle="display: block;"></form:checkbox>

                                        </td>

                                        <td>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 200px; text-align: left; font-weight: normal; font-size: x-small; vertical-align: top" >
                                                ${wrapper.email}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td style="width:40px;">

                                    </td>
                                    <td style="width:200px;">
                                        <input type="submit" tabindex="8" value="Update"/>
                                    </td>
                                </tr>

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
//            e.preventDefault();
//            pending ajax call until user module related backend is stable.
        });
    });
</script>


<c:import url="${mainDir}/common/footer.jsp" />