<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@include file="includes/head.jsp"%>
<%@include file="includes/header.jsp"%>
    <main class="px-3">
        <h1>${text['welcome.title']}</h1>
        <p class="lead">${text['welcome.text']}</p>
        <p class="lead">
            <a href="quest" class="btn btn-lg btn-secondary fw-bold border-white bg-white">${text['welcome.btn']}</a>
        </p>
    </main>
<%@include file="includes/footer.jsp"%>
