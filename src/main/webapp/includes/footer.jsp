<%@ page contentType="text/html;charset=UTF-8" %>
        <footer class="mt-auto text-white-50">
            <%
                Integer countAttempts = (Integer) session.getAttribute("countAttempts");
            %>
            <p>${text['footer.attempts_complete']} <%=(countAttempts == null ? 0 : countAttempts)%></p>
        </footer>
        </div>
        <script><%@include file="../js/app.js"%></script>
    </body>
</html>
