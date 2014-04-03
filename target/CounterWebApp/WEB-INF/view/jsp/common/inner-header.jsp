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
                              <span style="display:block;text-align:left; margin:2px;"> heading here</span>
                              <div class="menu-item-wrapper" >Home</div>
                              <div class="menu-item-wrapper" >Profile</div>

                          </div>
                          <div class="menu-items-row-for-middle">
                              <span style="display:block;text-align:left; margin:2px;"> heading here</span>
                              <div class="menu-item-wrapper" >Create User</div>
                              <div class="menu-item-wrapper" >Create Board</div>
                          </div>
                          <div class="menu-items-row-for-middle">
                              <span style="display:block;text-align:left; margin:2px;"> heading here</span>
                              <div class="menu-item-wrapper" >User Access</div>
                              <div class="menu-item-wrapper" >Reports</div>
                          </div>
                          <div class="menu-items-row-for-bottom">
                              <span style="display:block;text-align:left; margin:2px;"> heading here</span>
                              <div class="menu-item-wrapper" >Logout</div>
                              <div class="menu-item-wrapper" >Change Password</div>


                          </div>


                      <span style="position:absolute; display:block; left:75px; bottom:60px;">Menu</span>
                    </div>
                    <div id="message-box"></div>
                </div>
            </div>
        
        <div id="content">