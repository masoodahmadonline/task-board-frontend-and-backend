<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wrapper">
    
        
            <div id="header">  
                <div id="header-inner" >
                    <img id="company-logo" src="${resourcesDir}/images/logo.png" />
                    <c:if test="${sessionScope.userName != null}">
                              <div id="user"><span>&#x25be; </span>${sessionScope.userName}.
                                  <div id="user-options">
                                      <a href="${pageContext.request.contextPath}/logout">Logout</a>
                                      <br />
                                      <a href="${pageContext.request.contextPath}/users/profile-edit-personal" >Edit Personal Information</a>
                                      <br />

                                  </div>
                              </div>
                    </c:if>                  

                    <div id="circle" class="circle">

                          <div class="menu-items-row-for-top">
                              <span style="display:block;text-align:left; margin:2px;"> <strong>My Home</strong></span>
                              <a href="${pageContext.request.contextPath}/users/home" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">Home</span></div> </a>
                              <a href="${pageContext.request.contextPath}/users/profile-edit-personal" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">Profile</span></div> </a>
                          </div>
                          <div class="menu-items-row-for-middle">
                              <span style="display:block;text-align:left; margin:2px;"> <strong>Create</strong></span>
                              <a href="${pageContext.request.contextPath}/users/create" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">Create<br />User</span></div></a>
                              <a href="${pageContext.request.contextPath}/boards/create" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">Create<br />Board</span></div></a>
                          </div>
                          <div class="menu-items-row-for-middle">
                              <span style="display:block;text-align:left; margin:2px;"> <strong>View & Edit</strong></span>
                              <a href="${pageContext.request.contextPath}/users/edit" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">User<br />Access</span></div></a>
                              <a href="${pageContext.request.contextPath}/reports/cards/by-status" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">Reports</span></div></a>
                          </div>
                          <div class="menu-items-row-for-bottom">
                              <span style="display:block;text-align:left; margin:2px;"> <strong>Secure</strong></span>
                              <a href="${pageContext.request.contextPath}/logout" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">Logout</span></div> </a>
                              <a href="${pageContext.request.contextPath}/users/change-password" ><div class="menu-item-wrapper" ><span class="circle-menu-item-text">Change<br />Password</span></div></a>


                          </div>


                      <span style="position:absolute; display:block; left:75px; bottom:60px;">&#x25be;</span>
                    </div>
                    <div id="message-box"></div>
                </div>
            </div>
        
        <div id="content">