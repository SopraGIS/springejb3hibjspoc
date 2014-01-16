
<%@ include file="/WEB-INF/views/header.jsp" %>
<div class="container">

    <h1><fmt:message key="new.title"/></h1>

    <p><fmt:message key="new.description"/></p>

    <form:form modelAttribute="blogPost" method="post" class="well">
        <fieldset>
            <legend><fmt:message key="new.legend"/></legend>
            <p>
                <form:label	for="title" path="title"><fmt:message key="label.title"/></form:label>
                <form:input path="title" class="input-xxlarge"/>
            </p>
            <p>
                <form:label for="content" path="content"><fmt:message key="label.content"/></form:label>
                <form:textarea path="content" class="input-xxlarge" rows="9"/>
            </p>
            <p>
                <button type="submit" class="btn btn-primary"><fmt:message key="button.ok"/></button>
                <button class="btn"><fmt:message key="button.cancel"/></button>
            </p>
        </fieldset>
    </form:form>

</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>