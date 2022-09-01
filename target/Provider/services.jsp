<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.07.2022
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="jspf/directive/page.jspf"%>
<%@ include file="jspf/directive/taglib.jspf"%>
<html>
<jstl:set var="title" value="Services" scope="page"/>
<%@ include file="jspf/head.jspf"%>
<body>
    <%@ include file="jspf/header.jspf" %>
    <main>
        <div class="container commands">
            <jstl:if test="${sessionScope.user.admin == true}">
                <button class="command" id="add-button-tariff"><fmt:message key="service.button.admin.add"/></button>
            </jstl:if>
            <div class="command">
                <select required id="sort_methods" class="form-control" name="tariffs_sort" onchange="location = this.value;">
                    <option value="controller?action=services">Choose...</option>
                    <option value="controller?action=services&a-z=true">A-z</option>
                    <option value="controller?action=services&z-a=true">Z-a</option>
                    <option value="controller?action=services&cheap-expensive=true">cheap-expensive</option>
                    <option value="controller?action=services&expensive-cheap=true">expensive-cheap</option>
                </select>
            </div>
        </div>
        <jstl:forEach items="${requestScope.service_tariffs}" var="service">
            <div class="container cards">
                <div class="card" id = "service${service.name}" onclick="showTariffs(${service.name})">
                    <h3>${service.name}</h3>
                    <div class="description">
                        <p>
                            ${service.description}
                        </p>
                    </div>
                </div>
                <div class="container-carousel" id="${service.name}">
                    <jstl:forEach items="${service.tariffList}" var="tariff">
                        <div class="card-carousel ${tariff.name}">
                            <h3>${tariff.name}</h3>
                            <div class="description">
                                <p>
                                   ${tariff.description}
                                </p>
                            </div>
                            <div class="info">
                                <p>
                                    <fmt:message key="tariff.card.price"/>${tariff.price}
                                </p>
                                <p>
                                    <fmt:message key="tariff.card.service"/>${service.name}
                                </p>
                            </div>
                            <jstl:if test="${not empty sessionScope.user}">
                                <div class="container buttons">
                                    <form method="post" action="controller?action=download_pdf">
                                        <input type="hidden" name="id_tariff" value="${tariff.id}">
                                        <button type="Submit" class="form-button"><fmt:message key="service.button.download"/></button>
                                    </form>
                                    <jstl:if test="${sessionScope.user.admin == true}">
                                        <div class="edit-delete">
                                            <button id="edit-button-tariff" class="form-button" onclick="showEditModal(${tariff.name})"><fmt:message key="service.button.admin.edit"/></button>
                                            <form method="post" action="controller?action=delete_tariff">
                                                <input type="hidden" name="id_tariff" value="${tariff.id}">
                                                <button type="Submit" class="form-button"><fmt:message key="service.button.admin.delete"/></button>
                                            </form>
                                        </div>
                                    </jstl:if>
                                    <jstl:if test="${sessionScope.user.admin == false}">
                                        <jstl:choose>
                                            <jstl:when test="${fn:contains(sessionScope.user.tariffList, tariff.id)}">
                                                <button type="submit" class="form-button" disabled>Subscribed</button>
                                            </jstl:when>
                                            <jstl:otherwise>
                                                <jstl:if test="${sessionScope.user.status == true}">
                                                    <form method="post" action="controller?action=subscribe">
                                                        <input type="hidden" name="tariff_id" value="${tariff.id}">
                                                        <button type="submit" class="form-button">Subscribe</button>
                                                    </form>
                                                </jstl:if>
                                            </jstl:otherwise>
                                        </jstl:choose>
                                    </jstl:if>
                                </div>
                            </jstl:if>
                        </div>
                        <div id="${tariff.name}" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeEditModal(${tariff.name})">&times;</span>
                                <div class="container edit">
                                    <form method="post" class="form" action="controller?action=edit_tariff">
                                        <h3><fmt:message key="form.edit.tariff.h3"/></h3>
                                        <div class="form-group">
                                            <label><fmt:message key="form.add.tariff.name"/></label>
                                            <input required type="text" name="name_tariff" class="form-input" placeholder='<fmt:message key="form.add.tariff.name"/>' value="${tariff.name}">
                                        </div>
                                        <div class="form-group">
                                            <label><fmt:message key="form.add.tariff.description"/></label>
                                            <input trequired ype="text" name="description_tariff" class="form-input" placeholder="<fmt:message key="form.add.tariff.description"/>" value="${tariff.description}">
                                        </div>
                                        <div class="form-group">
                                            <div>
                                                <label><fmt:message key="form.add.tariff.price"/></label>
                                                <input required type="number" name="price_tariff" class="form-input" placeholder="0.00" value="${tariff.price}">
                                            </div>
                                        </div>
                                        <input type="hidden" name="id_tariff" value="${tariff.id}">
                                        <div class="form-group">
                                            <button type="Submit" class="form-button"><fmt:message key="form.edit.tariff.save"/></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </jstl:forEach>
                </div>
            </div>
        </jstl:forEach>
    </main>
    <div id="add-modal-tariff" class="modal">
        <div class="modal-content">
            <span class="close" id="close-add-tariff">&times;</span>
            <div class="container add">
                <form method="post" class="form" action="controller?action=add_tariff">
                    <h3><fmt:message key="form.add.tariff.h3"/></h3>
                    <div class="form-group">
                        <label><fmt:message key="form.add.tariff.name"/></label>
                        <input required type="text" name="name_tariff" class="form-input" placeholder='<fmt:message key="form.add.tariff.name"/>'>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="form.add.tariff.description"/></label>
                        <input trequired ype="text" name="description_tariff" class="form-input" placeholder='<fmt:message key="form.add.tariff.description"/>'>
                    </div>
                    <div class="form-group">
                        <div>
                            <label><fmt:message key="form.add.tariff.price"/></label>
                            <input required type="number" name="price_tariff" class="form-input" placeholder="0.00">
                        </div>
                        <div>
                            <label for="inputState"><fmt:message key="form.add.tariff.service"/></label>
                            <select required id="inputState" class="form-control" name="service_name_add">
                                <option>Internet</option>
                                <option>IPTV</option>
                                <option>TV</option>
                                <option>Phone</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="Submit" class="form-button"><fmt:message key="form.add.tariff.submit"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<script type="text/javascript" src="js/scriptsTariff.js"></script>
</body>
</html>
