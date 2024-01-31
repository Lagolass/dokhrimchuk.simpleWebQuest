<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.javarush.dokhrimchuk.simplewebquest.model.Answer" %>
<%@ page import="java.util.List" %>
<% List<Answer> answers = (List<Answer>) request.getAttribute("answers");
    if(!answers.isEmpty()) {
        for (int i = 0; i < answers.size(); i++) { %>
    <li class="list-group-item float-start">
        <div class="form-check">
            <input class="form-check-input" id="ans<%=answers.get(i).getId()%>" type="radio" name="answer"
                   value="<%=answers.get(i).getNextQuestionId()%>"/>
            <label class="form-check-label" for="ans<%=answers.get(i).getId()%>">
                <%=answers.get(i).getText()%>
            </label>
        </div>
    </li>
    <%
        }
    }
%>