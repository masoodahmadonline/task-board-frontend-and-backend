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
                                                  <%--Forgot your password?--%>
                                                    <br />
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


                                <h3><span class="accordion-heading ">Terms and conditions </span></h3>
                                <div>
                                    <p>
                                    <h2>WEBSITE TERMS AND CONDITIONS TEMPLATE </h2>
                                    <p>
                                    <h4>Introduction</h4>
                                    <p>
                                    These terms and conditions govern your use of this website; by using this website, you accept these terms and conditions in full. If you disagree with these terms and conditions or any part of these terms and conditions, you must not use this website.
                                    [You must be at least [18] years of age to use this website. By using this website [and by agreeing to these terms and conditions] you warrant and represent that you are at least [18] years of age.]
                                    About these website terms and conditions
                                    </p>
                                    <h4>License to use website</h4>
                                    <p>
                                    Unless otherwise stated, [NAME] and/or its licensors own the intellectual property rights in the website and material on the website. Subject to the license below, all these intellectual property rights are reserved.
                                    You may view, download for caching purposes only, and print pages [or [OTHER CONTENT]] from the website for your own personal use, subject to the restrictions set out below and elsewhere in these terms and conditions.
                                    You must not:
                                    republish material from this website (including republication on another website);
                                    sell, rent or sub-license material from the website;
                                    show any material from the website in public;
                                    reproduce, duplicate, copy or otherwise exploit material on this website for a commercial purpose;]
                                    [edit or otherwise modify any material on the website; or]
                                    [redistribute material from this website [except for content specifically and expressly made available for redistribution].]
                                    [Where content is specifically made available for redistribution, it may only be redistributed [within your organization].]
                                    Acceptable use
                                    </p>
                                    You must not use this website in any way that causes, or may cause, damage to the website or impairment of the availability or accessibility of the website; or in any way which is unlawful, illegal, fraudulent or harmful, or in connection with any unlawful, illegal, fraudulent or harmful purpose or activity.
                                    You must not use this website to copy, store, host, transmit, send, use, publish or distribute any material which consists of (or is linked to) any spyware, computer virus, Trojan horse, worm, keystroke logger, root kit or other malicious computer software.
                                    You must not conduct any systematic or automated data collection activities (including without limitation scraping, data mining, data extraction and data harvesting) on or in relation to this website without [NAME'S] express written consent.
                                    [You must not use this website to transmit or send unsolicited commercial communications.]
                                    [You must not use this website for any purposes related to marketing without [NAME'S] express written consent.]
                                    [Restricted access
                                    [Access to certain areas of this website is restricted.] [NAME] reserves the right to restrict access to [other] areas of this website, or indeed this entire website, at [NAME'S] discretion.
                                    If [NAME] provides you with a user ID and password to enable you to access restricted areas of this website or other content or services, you must ensure that the user ID and password are kept confidential.
                                    [[NAME] may disable your user ID and password in [NAME'S] sole discretion without notice or explanation.]
                                    [User content
                                    In these terms and conditions, “your user content” means material (including without limitation text, images, audio material, video material and audio-visual material) that you submit to this website, for whatever purpose.
                                    You grant to [NAME] a worldwide, irrevocable, non-exclusive, royalty-free license to use, reproduce, adapt, publish, translate and distribute your user content in any existing or future media. You also grant to [NAME] the right to sub-license these rights, and the right to bring an action for infringement of these rights.
                                    Your user content must not be illegal or unlawful, must not infringe any third party's legal rights, and must not be capable of giving rise to legal action whether against you or [NAME] or a third party (in each case under any applicable law).
                                    You must not submit any user content to the website that is or has ever been the subject of any threatened or actual legal proceedings or other similar complaint.
                                    [NAME] reserves the right to edit or remove any material submitted to this website, or stored on [NAME'S] servers, or hosted or published upon this website.
                                    [Notwithstanding [NAME'S] rights under these terms and conditions in relation to user content, [NAME] does not undertake to monitor the submission of such content to, or the publication of such content on, this website.]
                                    No warranties
                                    This website is provided “as is” without any representations or warranties, express or implied. [NAME] makes no representations or warranties in relation to this website or the information and materials provided on this website.
                                    Without prejudice to the generality of the foregoing paragraph, [NAME] does not warrant that:
                                    this website will be constantly available, or available at all; or
                                    the information on this website is complete, true, accurate or non-misleading.
                                    Nothing on this website constitutes, or is meant to constitute, advice of any kind. [If you require advice in relation to any [legal, financial or medical] matter you should consult an appropriate professional.]
                                    Limitations of liability
                                    [NAME] will not be liable to you (whether under the law of contact, the law of torts or otherwise) in relation to the contents of, or use of, or otherwise in connection with, this website:
                                    [to the extent that the website is provided free-of-charge, for any direct loss;]
                                    for any indirect, special or consequential loss; or
                                    for any business losses, loss of revenue, income, profits or anticipated savings, loss of contracts or business relationships, loss of reputation or goodwill, or loss or corruption of information or data.
                                    These limitations of liability apply even if [NAME] has been expressly advised of the potential loss.
                                    Exceptions
                                    Nothing in this website disclaimer will exclude or limit any warranty implied by law that it would be unlawful to exclude or limit; and nothing in this website disclaimer will exclude or limit [NAME'S] liability in respect of any:
                                    death or personal injury caused by [NAME'S] negligence;
                                    fraud or fraudulent misrepresentation on the part of [NAME]; or
                                    matter which it would be illegal or unlawful for [NAME] to exclude or limit, or to attempt or purport to exclude or limit, its liability.
                                    Reasonableness
                                    By using this website, you agree that the exclusions and limitations of liability set out in this website disclaimer are reasonable.
                                    If you do not think they are reasonable, you must not use this website.
                                    Other parties
                                    [You accept that, as a limited liability entity, [NAME] has an interest in limiting the personal liability of its officers and employees. You agree that you will not bring any claim personally against [NAME'S] officers or employees in respect of any losses you suffer in connection with the website.]
                                    [Without prejudice to the foregoing paragraph,] you agree that the limitations of warranties and liability set out in this website disclaimer will protect [NAME'S] officers, employees, agents, subsidiaries, successors, assigns and sub-contractors as well as [NAME].
                                    Unenforceable provisions
                                    If any provision of this website disclaimer is, or is found to be, unenforceable under applicable law, that will not affect the enforceability of the other provisions of this website disclaimer.
                                    Indemnity
                                    You hereby indemnify [NAME] and undertake to keep [NAME] indemnified against any losses, damages, costs, liabilities and expenses (including without limitation legal expenses and any amounts paid by [NAME] to a third party in settlement of a claim or dispute on the advice of [NAME'S] legal advisers) incurred or suffered by [NAME] arising out of any breach by you of any provision of these terms and conditions[, or arising out of any claim that you have breached any provision of these terms and conditions].
                                    Breaches of these terms and conditions
                                    Without prejudice to [NAME'S] other rights under these terms and conditions, if you breach these terms and conditions in any way, [NAME] may take such action as [NAME] deems appropriate to deal with the breach, including suspending your access to the website, prohibiting you from accessing the website, blocking computers using your IP address from accessing the website, contacting your internet service provider to request that they block your access to the website and/or bringing court proceedings against you.
                                    Variation
                                    [NAME] may revise these terms and conditions from time-to-time. Revised terms and conditions will apply to the use of this website from the date of the publication of the revised terms and conditions on this website. Please check this page regularly to ensure you are familiar with the current version.
                                    Assignment
                                    [NAME] may transfer, sub-contract or otherwise deal with [NAME'S] rights and/or obligations under these terms and conditions without notifying you or obtaining your consent.
                                    You may not transfer, sub-contract or otherwise deal with your rights and/or obligations under these terms and conditions.
                                    Severability
                                    If a provision of these terms and conditions is determined by any court or other competent authority to be unlawful and/or unenforceable, the other provisions will continue in effect. If any unlawful and/or unenforceable provision would be lawful or enforceable if part of it were deleted, that part will be deemed to be deleted, and the rest of the provision will continue in effect.
                                    Entire agreement
                                    These terms and conditions [, together with [DOCUMENTS],] constitute the entire agreement between you and [NAME] in relation to your use of this website, and supersede all previous agreements in respect of your use of this website.
                                    Law and jurisdiction
                                    These terms and conditions will be governed by and construed in accordance with [GOVERNING LAW], and any disputes relating to these terms and conditions will be subject to the [non-]exclusive jurisdiction of the courts of [JURISDICTION].
                                    [Registrations and authorizations
                                    [[NAME] is registered with [TRADE REGISTER]. You can find the online version of the register at [URL]. [NAME'S] registration number is [NUMBER].]
                                    [[NAME] is subject to [AUTHORISATION SCHEME], which is supervised by [SUPERVISORY AUTHORITY].]
                                    [[NAME] is registered with [PROFESSIONAL BODY]. [NAME'S] professional title is [TITLE] and it has been granted in [JURISDICTION]. [NAME] is subject to the [RULES] which can be found at [URL].]
                                    [[NAME] subscribes to the following code[s] of conduct: [CODE(S) OF CONDUCT]. [These codes/this code] can be consulted electronically at [URL(S)].
                                    [[NAME'S] [TAX] number is [NUMBER].]]
                                    [NAME'S] details
                                    The full name of [NAME] is [FULL NAME].
                                    [[NAME] is registered in [JURISDICTION] under registration number [NUMBER].]
                                    [NAME'S] [registered] address is [ADDRESS].
                                    You can contact [NAME] by email to [EMAIL].

                                </div>



                                <h3><span class="accordion-heading ">Un-authorized usage</span></h3>
                                <div>
                                    <p>
                                        This system is for authorized users only. Individual use of this computer
                                        system and/or network without authority, or in excess of your authority,
                                        is strictly prohibited. Monitoring of transmissions or transactional
                                        information may be conducted to ensure the proper functioning and security
                                        of electronic communication resources. Anyone using this system expressly
                                        consents to such monitoring and is advised that if such monitoring reveals
                                        possible criminal activity or policy violation, system personnel may
                                        provide the evidence of such monitoring to law enforcement or to other
                                        senior officials for disciplinary action.
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