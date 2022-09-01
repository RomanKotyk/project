<%@ attribute name="list" type="java.util.ArrayList" required="true"%>
<%@ include file="../../jspf/directive/taglib.jspf"%>
<jstl:forEach items="${list}" var="tariff">
    <div class="tariff">
        <p>
                ${tariff.name}
        </p>
        <p>
                ${tariff.description}
        </p>
        <p>
                ${tariff.price} <fmt:message key="profile.user.currency"/>
        </p>
    </div>
</jstl:forEach>