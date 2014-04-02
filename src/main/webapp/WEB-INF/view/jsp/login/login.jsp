<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.login"/>
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

<body
    style=" border: transparent 1px solid;
	background-image: url('${resourcesDir}/images/handprint.png');
	background-repeat: no-repeat;
	background-attachment: fixed;
	    
	    ">
    <c:import url="${mainDir}/common/inner-header.jsp" />
        

        
            <div class="wrapper-for-form-block">
                <div id="login-block-1">

                    <div style="display: block;  ">
                        <div class="form-messages">
                            <span class="form-title">Welcome<br />Authentication Required</span>

                            <c:if test="${param.error}">
                            <span class="message-error">${invalidCreds}</span>
                            </c:if>
                        </div>
                        <div>

                                <div id="outer-login-wrapper" class="blur draggable jQeffect-show-clip" >

                                    <div class="inner-form-wrapper" >
                                        <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">

                                        <table>
                                        <tbody>
                                              <tr>
                                                <td>&nbsp;
                <!--                                  <img style="height:30px;" src="${resourcesDir}/images/company.png" />-->
                                                </td>

                                                <td>&nbsp;
                <!--                                  <input type="text" placeholder="Company Name" />-->
                                                </td>

                                                <td>
                                                </td>

                                              </tr>

                                              <tr>
                                                <td>
                                                    <img style="height:30px;" src="${resourcesDir}/images/person.png" />
                                                </td>

                                                <td>
                                                    <input class="form-input" id="j_username" name="j_username" type="text" placeholder="<spring:message code="placeholder.login"/>" tabindex="1" />
                                                </td>

                                                <td>
                                                    <input type="submit" value="Log-In" tabindex="3"/>
                                                </td>

                                              </tr>

                                              <tr>
                                                <td>
                                                  <img style="height:30px;" src="${resourcesDir}/images/password.png" />
                                                </td>

                                                <td>
                                                    <input class="form-input" id="j_password" name ="j_password" type="password" placeholder="<spring:message code="placeholder.password"/>" tabindex="2" />
                                                </td>

                                                <td>
                                                    <input type="reset" tabindex="4"/>
                                                </td>

                                              </tr>
                                              <tr>
                                                <td>
                                                </td>
                                                <td style="text-align:right;" tabindex="5">
                                                  Forgot your password?
                                                </td>
                                                <td>
                                                </td>
                                              </tr>


                                          </tbody>
                                          </table>
                                        </form>


                                      </div>

                                </div>

                        </div>
                    </div>

                </div>
                <div id="login-block-2" style="display:inline-block; margin-left: 30px; vertical-align: middle;">
                    <div  id="login-accordion-container" class="ui-widget-content accordion-resizer draggable">
                            <div id="login-accordion" class="accordion">


                                <h3><span class="accordion-heading ">Warning for unauthorized log-in attempts </span></h3>
                                <div>
                                  <p>
                                    Any attempt to gain unauthorized access can be reported to authorities and legal action may be taken.
                                  </p> 
                                </div>

                                <h3><span class="accordion-heading ">Browser support</span></h3>
                                <div>
                                  <p>

                                  </p>
                                  <ul>
                                    <li>Google chrome v33 and above are most recommended.</li>
                                    <li>Firefox v28 is supported and prefered 98%.</li>

                                  </ul>
                                </div>

                            </div>
                          </div>
                </div>

            </div>  
       
        
         
 <c:import url="${mainDir}/common/footer.jsp" />