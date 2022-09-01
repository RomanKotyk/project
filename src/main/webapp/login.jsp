<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.07.2022
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="jspf/directive/page.jspf"%>
<%@ include file="jspf/directive/taglib.jspf"%>
<html>
<jstl:set var="title" value="Login" scope="page"/>
<%@ include file="jspf/head.jspf"%>
<body>
    <%@ include file="jspf/header.jspf" %>
    <main>
        <div class="container login">
            <form method="post" class="form" action="controller?action=login">
                <div class="form-header">
                    <h3><fmt:message key="login.form.h3"/> </h3>
                    <p><fmt:message key="login.form.description"/></p>
                    <jstl:if test="${sessionScope.user == null}">
                        <p style="color: red"><fmt:message key="login.error.message"/></p>
                    </jstl:if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-input" name="login" placeholder='<fmt:message key="subscriber.add.form.label.login"/>'>
                </div>
                <div class="form-group">
                    <input type="password" class="form-input" name="password" placeholder='<fmt:message key="subscriber.add.form.label.password"/>'>
                </div>
                <div class="form-group">
                    <button class="form-button" type="submit"><fmt:message key="login.form.login"/></button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
