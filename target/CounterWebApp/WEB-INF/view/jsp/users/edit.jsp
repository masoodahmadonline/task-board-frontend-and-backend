<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" />
<c:set var="pageTitle" scope="request" >
    Edit user
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
                <span class="form-title"><spring:message code="title.editeUserAccess"/></span>

                <c:if test="${error}">
                    <span class="message-error">${errorMsg}</span>
                </c:if>
                <c:if test="${success}">
                    <span class="message-success">${successMsg}</span>
                </c:if>
            </div>
            <div>
                <div class="form-wrapper jQeffect-show-clip">
                    <form id="live-search" action="" class="styled" method="post">
                        <input type="text" class="form-input" id="filter" placeholder="Search" value="" />
                        <span id="filter-count"></span>
                    </form>
                </div>
                <form:form action="${pageContext.request.contextPath}/users/edit" commandName="editUserWrapper" >
                    <div class="form-wrapper jQeffect-show-clip" >
                        <div class="inner-form-wrapper" >
                            <br />
                            <table class="usersListClass">
                                <c:forEach items="${editUserWrapper.userList}" var="wrapper" varStatus="idx">
                                    <form:hidden path="userList[${idx.index}].userId"></form:hidden>
                                    <tr>
                                        <td>
                                            <img src="${resourcesDir}${wrapper.imageName}" height="40px;" width="40px;" />
                                        </td>
                                        <td>
                                            <span style="width:200px;  display:inline-block; text-align: left; font-weight: bold">${wrapper.firstName}&nbsp;${wrapper.lastName}</span> <br />
                                            <span style="width:200px;  display:inline-block; text-align: left;">${wrapper.email}</span>
                                        </td>
                                        <td>
                                            <span style="width:100px;  display:inline-block; text-align: right; padding: 5px;">${wrapper.enableUser}</span>
                                        </td>
                                        <td>
                                            <c:url var="uId1" value="change-password">
                                                <c:param name="uId" value="${wrapper.userId}" />
                                            </c:url>
                                            <a title="Change Password" href='<c:out value="${uId1}"/>'>Change Password</a>
                                        </td>
                                        <td>
                                            <c:url var="uId2" value="profile-edit-personal">
                                                <c:param name="uId" value="${wrapper.userId}" />
                                            </c:url>
                                            <a title="Edit user" href='<c:out value="${uId2}"/>'>Edit</a>
                                        </td>
                                        <%--<td>
                                            <c:url var="uId3" value="delete-user">
                                                <c:param name="uId" value="${wrapper.userId}" />
                                            </c:url>
                                            <a title="Delete user" class="delete-button" href='<c:out value="${uId3}"/>'></a>
                                        </td>--%>
                                    </tr>
                                </c:forEach>


                            </table>
                        </div>

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
        $("#filter").keyup(function(){
            // Retrieve the input field text and reset the count to zero
            var filter = $(this).val(), count = 0;
            // Loop through the users list
            $(".usersListClass tr").each(function(){
                // If the table row does not contain the text phrase fade it out
                if ($(this).text().search(new RegExp(filter, "i")) < 0) {
                    $(this).fadeOut();
                    // Show the table row if the phrase matches and increase the count by 1
                } else {
                    $(this).show();
                    count++;
                }
            });
            // Update the count
            //var numberItems = count;
            //$("#filter-count").text("Number of Users = "+count);
        });
    });
</script>


<c:import url="${mainDir}/common/footer.jsp" />