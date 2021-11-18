<%--
  Created by IntelliJ IDEA.
  User: oliverrasoli
  Date: 11/11/2021
  Time: 11.48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.inc" %>

<div class="jumbotron" style="padding: 2%;">

    <form action="RegisterServlet" method="post">
        <div class="form-group" style="padding-right: 30%; padding-left: 30%;">
            <label for="kundeNavn">Navn</label>
            <input type="text" class="form-control" id="kundeNavn" name="kundeNavn" placeholder="Navn">
        </div>
        <div class="form-group" style="padding-right: 30%; padding-left: 30%;">
            <label for="kundeAdresse">Adresse</label>
            <input type="text" class="form-control" id="kundeAdresse" name="kundeAdresse" placeholder="Adresse">
        </div>
        <div class="form-group" style="padding-right: 30%; padding-left: 30%;">
            <label for="kundePostNr">Post Nr.</label>
            <input type="text" class="form-control" id="kundePostNr" name="kundePostNr" placeholder="Post Nr.">
        </div>
        <div class="form-group" style="padding-right: 30%; padding-left: 30%;">
            <label for="kundeEmail">Email <span style="color: darkred;">-${requestScope.fejlBesked}</span> </label>
            <input type="email" class="form-control" id="kundeEmail" name="kundeEmail" placeholder="Email">
        </div>
        <div class="form-group" style="padding-right: 30%; padding-left: 30%;">
            <label for="kundePassword">Password</label>
            <input type="password" class="form-control" id="kundePassword" name="kundePassword" placeholder="Password">
        </div>


        <br>
        <button type="submit" class="btn btn-primary" value="regButton" style="margin-left: 42%">Registrer dig her!</button>
    </form>

</div>

</body>
</html>
