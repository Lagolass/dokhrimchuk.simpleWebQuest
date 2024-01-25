<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@include file="includes/head.jsp"%>
<%@include file="includes/header.jsp"%>
    <main class="px-3">
        <%
            String question = (String) request.getAttribute("question");
        %>
        <form id="formQuest" class="card" action="./quest" method="post">
            <input type="hidden" name="action" value="nextStage">
            <div class="card-header">
                <h5 class="text-question"><%=question%></h5>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush list-answers">
                    <%@include file="includes/listAnswers.jsp"%>
                </ul>
            </div>
            <div class="card-footer">
                <div class="col-sm-12 text-center">
                    <button class="btn btn-primary btn-submit hidden" type="submit">${text['quest.btn_continue']}</button>
                    <a class="btn btn-primary btn-restart hidden" href="./quest">${text['quest.btn_restart']}</a>
                </div>
            </div>
        </form>
    </main>
<%@include file="includes/footer.jsp"%>
