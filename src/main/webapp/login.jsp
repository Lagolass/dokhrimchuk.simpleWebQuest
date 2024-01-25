<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@include file="includes/head.jsp"%>
<%@include file="includes/header.jsp"%>

    <main class="px-3">
        <form action="./login" class="form-control" method="post">
            <div class="form-text"><h5>${text['login.title']}</h5></div>
            <div class="form-floating mb-3">
                <input type="email" name="email" class="form-control" id="floatingInput" placeholder="${text['login.email_placeholder']}">
                <label for="floatingInput">${text['login.email_label']}</label>
            </div>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="${text['login.password_placeholder']}">
                <label for="floatingPassword">${text['login.password_label']}</label>
            </div>
            <div class="text-danger"><%=request.getAttribute("error")%></div>
            <button class="btn btn-primary mt-2" type="submit">${text['login.btn_login']}</button>
        </form>
    </main>
<%@include file="includes/footer.jsp"%>
