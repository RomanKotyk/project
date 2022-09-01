<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.07.2022
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="jspf/directive/page.jspf"%>
<%@ include file="jspf/directive/taglib.jspf"%>

<html>
<jstl:set var="title" value="Provider" scope="page"/>
<%@ include file="jspf/head.jspf"%>
<body>
    <%@ include file="jspf/header.jspf" %>
    <main>
        <div class="container cards">
            <jstl:forEach items="${requestScope.best_tariffs}" var="best_tariff">
                <div class="card">
                    <h3>${best_tariff.name}</h3>
                    <div class="description">
                        <p>
                            ${best_tariff.description}
                        </p>
                    </div>
                    <div class="info">
                        <p>
                             <fmt:message key="tariff.card.price"/>${best_tariff.price}
                        </p>
                        <p>
                            <fmt:message key="tariff.card.service"/>${best_tariff.service.name}
                        </p>
                    </div>
                </div>
            </jstl:forEach>
        </div>
    </main>
</body>
</html>
