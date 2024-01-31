<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.javarush.dokhrimchuk.simplewebquest.service.UserService" %>
<header class="mb-5">
  <%
    int lastSlash = request.getRequestURI().lastIndexOf('/');
    String subUri = request.getRequestURI().substring(lastSlash);
  %>
  <nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="./"><h3 class="float-md-start mb-0">SimpleWebQuest</h3></a>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link <%=(subUri.equals("/welcome.jsp") ? "active" : "")%>" aria-current="page" href="./">
              ${text['header.title_home']}
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link <%=(subUri.equals("/quest.jsp") ? "active" : "")%>" href="./quest">
              ${text['header.title_quest']}
            </a>
          </li>
          <%
            if(!UserService.isLoggedIn(request)) {
          %>
            <li class="nav-item">
              <a class="nav-link <%=(subUri.equals("/login.jsp") ? "active" : "")%>" href="./login">
                ${text['header.title_login']}
              </a>
            </li>
          <% } else { %>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                ${text['header.title_hello']} <%=UserService.getAuthUser(request).getName()%>
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="./logout">${text['header.title_logout']}</a></li>
              </ul>
            </li>
          <% } %>
        </ul>
      </div>
    </div>
  </nav>
</header>