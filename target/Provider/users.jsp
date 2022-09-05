<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.07.2022
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="jspf/directive/page.jspf"%>
<%@ include file="jspf/directive/taglib.jspf"%>
<html>
<jstl:set var="title" value="Subscribers" scope="page"/>
<%@ include file="jspf/head.jspf"%>
<body>
<%@ include file="jspf/header.jspf" %>
<main>
    <div class="container commands">
        <button class="command" id="add-button-user"><fmt:message key="subscriber.button.admin.add"/></button>
        <div class="command">
            <select required id="inputSort" class="form-control" name="users_sort" onchange="location = this.value;">
                <option selected value="controller?action=users"><fmt:message key="subscriber.add.form.select.default"/></option>
                <option value="controller?action=users&sort=a-z">A-z</option>
                <option value="controller?action=users&sort=z-a">Z-a</option>
            </select>
        </div>
    </div>
    <div class="container users">
        <jstl:forEach items="${requestScope.user_list}" var="user">
            <div class="card" id ="${user.name}">
                <h3>${user.login}</h3>
                <div class="user">
                    <p>
                            ${user.surname} ${user.name}
                    </p>
                </div>
                <div class="info">
                    <p>
                        <fmt:message key="profile.user.balance"/> ${user.balance}
                    </p>
                    <p>
                        <jstl:choose>
                            <jstl:when test="${user.status == true}">
                                <fmt:message key="profile.user.status.active"/>
                            </jstl:when>
                            <jstl:otherwise>
                                <fmt:message key="profile.user.status.blocked"/>
                            </jstl:otherwise>
                        </jstl:choose>
                    </p>
                </div>
                <div class="container buttons-user">
                    <form method="post" action="controller?action=delete_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <button type="Submit" class="form-button"><fmt:message key="subscriber.button.admin.delete"/></button>
                    </form>
                    <form method="post" action="controller?action=status_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <button type="Submit" class="form-button">
                            <jstl:choose>
                                <jstl:when test="${user.status == true}">
                                    <fmt:message key="subscriber.button.admin.block"/>
                                </jstl:when>
                                <jstl:otherwise>
                                    <fmt:message key="subscriber.button.admin.unblock"/>
                                </jstl:otherwise>
                            </jstl:choose>
                        </button>
                    </form>
                </div>
            </div>
        </jstl:forEach>
    </div>
    <div class="container pages">
        <jstl:if test="${currentPage != 1}">
            <td><a href="controller?action=get_records&page=${currentPage - 1}&sort=${requestScope.sort}"><fmt:message key="pages.previous"/></a></td>
        </jstl:if>

        <table>
            <tr>
                <jstl:forEach begin="1" end="${noOfPages}" var="i">
                    <jstl:choose>
                        <jstl:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </jstl:when>
                        <jstl:otherwise>
                            <td><a href="controller?action=get_records&page=${i}&sort=${requestScope.sort}">${i}</a></td>
                        </jstl:otherwise>
                    </jstl:choose>
                </jstl:forEach>
            </tr>
        </table>

        <jstl:if test="${currentPage lt noOfPages}">
            <td><a href="controller?action=get_records&page=${currentPage + 1}&sort=${requestScope.sort}"><fmt:message key="pages.next"/></a></td>
        </jstl:if>
    </div>
</main>
<div id="add-modal-user" class="modal">
    <div class="modal-content">
        <span class="close" id="close-add-user">&times;</span>
        <div class="container add">
            <form method="post" class="form" action="controller?action=add_user">
                <h3><fmt:message key="subscriber.add.form.h3"/></h3>
                <div class="form-group">
                    <label><fmt:message key="subscriber.add.form.label.login"/></label>
                    <input required type="text" name="login_user" class="form-input" placeholder='<fmt:message key="subscriber.add.form.label.login"/>'>
                </div>
                <div class="form-group">
                    <label><fmt:message key="subscriber.add.form.label.name"/></label>
                    <input required type="text" name="name_user" class="form-input" placeholder='<fmt:message key="subscriber.add.form.label.name"/>' pattern="[a-zA-Z]+" title='<fmt:message key="message.name"/>'>
                </div>
                <div class="form-group">
                    <label><fmt:message key="subscriber.add.form.label.surname"/></label>
                    <input trequired ype="text" name="surname_user" class="form-input" placeholder='<fmt:message key="subscriber.add.form.label.surname"/>' pattern="[a-zA-Z]+" title='<fmt:message key="message.surname"/>'>
                </div>
                <div class="form-group">
                    <label><fmt:message key="subscriber.add.form.label.password"/></label>
                    <input required type="password" name="password_user" class="form-input" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title='<fmt:message key="message.password"/>'>
                </div>
                <div class="form-group">
                    <div>
                        <label><fmt:message key="subscriber.add.form.label.balance"/></label>
                        <input required type="number" name="balance_user" class="form-input" placeholder="0.00" min="0">
                    </div>
                    <div>
                        <label for="inputRole"><fmt:message key="subscriber.add.form.role"/></label>
                        <select required id="inputRole" class="form-control" name="user_role">
                            <option>User</option>
                            <option>Admin</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <button type="Submit" class="form-button"><fmt:message key="subscriber.button.admin.add"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/scriptsUser.js"></script>
</body>
</html>
