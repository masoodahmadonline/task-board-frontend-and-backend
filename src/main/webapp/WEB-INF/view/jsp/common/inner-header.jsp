<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wrapper">
    
        
            <div id="header">  
                <div id="header-inner" >
                    <img id="company-logo" src="${resourcesDir}/images/logo.png" />
                    <c:if test="${sessionScope.userName != null}">
                              <div id="user"><span>&#x25be; </span>${sessionScope.userName}.
                                  <div id="user-options">
                                      <a href="${pageContext.request.contextPath}/logout">Logout</a><br />
                                      Edit profile <br />
                                      and some more options 
                                  </div>
                              </div>
                    </c:if>                  

                    <div id="circle" class="circle">
                      <span style="position:absolute; display:block; left:75px; bottom:60px;">Menu</span>
                    </div>
                    <div id="message-box"></div>
                </div>
            </div>
        
        <div id="content">