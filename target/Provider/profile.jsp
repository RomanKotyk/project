<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.08.2022
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="jspf/directive/page.jspf"%>
<%@ include file="jspf/directive/taglib.jspf"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<jstl:set var="title" value="Profile" scope="page"/>
<%@ include file="jspf/head.jspf"%>
<body>
    <%@ include file="jspf/header.jspf" %>
    <main>
        <div class="container user-info">
            <div class="card-user-info">
                <h3>${sessionScope.user.login}</h3>
                <div class="user">
                    <p>
                        ${sessionScope.user.surname} ${sessionScope.user.name}
                    </p>
                </div>
                <div class="info">
                    <p>
                        <fmt:message key="profile.user.balance"/> ${sessionScope.user.balance} <fmt:message key="profile.user.currency"/>
                    </p>
                    <p>
                        <jstl:choose>
                            <jstl:when test="${sessionScope.user.status == true}">
                                <fmt:message key="profile.user.status.active"/>
                            </jstl:when>
                            <jstl:otherwise>
                                <fmt:message key="profile.user.status.blocked"/>
                            </jstl:otherwise>
                        </jstl:choose>
                    </p>
                </div>
                <div class="container buttons-user">
                    <button type="Submit" class="form-button" id="replenish-button-user"><fmt:message key="profile.user.button.replenish"/></button>
                </div>
            </div>
            <div class="list-tariffs">
                <tags:showSubscription list="${sessionScope.user.tariffList}"/>
            </div>
        </div>
    </main>
    <div id="replenish-modal-user" class="modal">
        <div class="modal-content">
            <span class="close" id="close-replenish-user">&times;</span>
            <div class="container add">
                <form method="post" class="form" action="controller?action=replenish_balance_user">
                    <h3><fmt:message key="profile.user.replenish.form.h3"/></h3>
                    <div class="form-group">
                        <div>
                            <label><fmt:message key="profile.user.replenish.amount"/></label>
                            <input required type="number" name="amount_replenish" class="form-input" placeholder="0.00">
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="Submit" class="form-button"><fmt:message key="profile.user.button.replenish"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="js/scriptsProfile.js"></script>
</body>
</html>
