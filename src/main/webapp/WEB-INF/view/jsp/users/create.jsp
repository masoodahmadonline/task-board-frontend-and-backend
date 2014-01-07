<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/WEB-INF/view/jsp/common/variables.jsp" /> 
<c:set var="pageTitle" scope="request" >
    <spring:message code="pageTitle.users.create"/>
</c:set>

<c:import url="${mainDir}/common/header.jsp" />

    <body>
        <c:import url="${mainDir}/common/inner-header.jsp" />
        <div class="priv-wrapper">
            <ul>
                <li>
                    <span class="priv-board-title"> Board - 1</span>
                    <ul>
                        <li>
                            <span class="box-priv-wrapper">
                                <span class="priv-box-title">Box - 1 -------------------- -  - -     ---------- ---   ------ </span>
                                <span class="priv-checkbox-container">
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="11" type="checkbox" /><label for="11" class="priv-checkbox-this"></label>
                                        <input id="22" type="checkbox" /><label for="22" class="priv-checkbox-all-children"></label>
                                        <input id="33" type="checkbox" /><label for="33" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>

                                </span>
                            </span>    
                        </li>
                        <li>
                            <span class="box-priv-wrapper">
                                <span class="priv-box-title">Box - 2</span>
                                <span class="priv-checkbox-container">
                                    <span class="priv-checkbox-set">
                                        <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                        <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                        <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                    </span>
                                </span>
                            </span>     
                            <ul>
                                <li>
                                    <span class="box-priv-wrapper">
                                        <span class="priv-box-title">Box - 2 (child box)</span>
                                        <span class="priv-checkbox-container">
                                            <span class="priv-checkbox-set">
                                                <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                            </span>
                                        </span>
                                    </span>     
                                </li>
                                <li>
                                        <span class="box-priv-wrapper">
                                            <span class="priv-box-title">Box - 2 (child box)</span>
                                            <span class="priv-checkbox-container">
                                                <span class="priv-checkbox-set">
                                                    <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                    <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                    <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                </span>
                                            </span>
                                        </span>    
                                        <ul>
                                            <li>
                                                <span class="box-priv-wrapper">
                                                    <span class="priv-box-title">Box - 2 (sub child box)</span>
                                                    <span class="priv-checkbox-container">
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                    </span>
                                                </span>     
                                            </li>
                                            <li>
                                                <span class="box-priv-wrapper">
                                                    <span class="priv-box-title">Box - 2 (sub child box)</span>
                                                    <span class="priv-checkbox-container">
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>
                                                        <span class="priv-checkbox-set">
                                                            <input id="1" type="checkbox" /><label for="1" class="priv-checkbox-this"></label>
                                                            <input id="2" type="checkbox" /><label for="2" class="priv-checkbox-all-children"></label>
                                                            <input id="3" type="checkbox" /><label for="3" class="priv-checkbox-can-give-to-others"></label>
                                                        </span>

                                                    </span>    
                                                </span> 
                                            </li>
                                        </ul>
                                    
                                </li>
                            </ul>
                        </li>
                        <li>
                            <span class="priv-box-title">Box - 1</span>
                        </li>
                        <li>
                            <span class="priv-box-title">Box - 1</span>
                        </li>
                        <li>
                            <span class="priv-box-title">Box - 1</span>
                        </li>
                        <li>
                            <span class="priv-box-title">Box - 1</span>
                        </li>
                        <li>
                            <span class="priv-box-title">Box - 1</span>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>                        
   <c:import url="${mainDir}/common/footer.jsp" />