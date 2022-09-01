<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.08.2022
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="jspf/directive/page.jspf"%>
<%@ include file="jspf/directive/taglib.jspf"%>
<html>
<jstl:set var="title" value="Error" scope="page"/>
<%@ include file="jspf/head.jspf"%>
<body>
    <div>
        <h3 style="color:#ffffff;"><fmt:message key="command.error.message"/></h3>
        <jstl:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
        <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>


        <jstl:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

        <jstl:if test="${not empty code}">
            <h3>Error code: ${code}</h3>
        </jstl:if>

        <jstl:if test="${not empty message}">
            <h3>Message: ${message}</h3>
        </jstl:if>


        <jstl:if test="${not empty errorMessage and empty exception and empty code}">
            <h3>Error message: ${errorMessage}</h3>
        </jstl:if>

    </div>
</body>
</html>
