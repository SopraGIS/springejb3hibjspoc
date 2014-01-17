<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container">

    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="hero-unit">
        <h1>blogboard.org</h1>
        <p><fmt:message key="blogboard.description"/></p>
        <p><a class="btn btn-primary btn-large" href="${newUrl}"><fmt:message key="blogboard.new"/></a></p>
    </div>

    <div class="row">
        <c:forEach var="article" items="${articles}">
            <div class="span4">
                <h2>${article.title}</h2>
                <p>${article.shortContent}&hellip;</p>
                <c:url var="articleUrl" value="/blogposts/${article.id}"/>
                <p><a class="btn" href="${articleUrl}">View full article &raquo;</a></p>
            </div>
        </c:forEach>
    </div>

</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>