<%--
  Created by IntelliJ IDEA.
  User: oliverrasoli
  Date: 09/11/2021
  Time: 11.29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.inc" %>

<div class="container-fluid">
    <div class="jumbotron" style="padding: 2%;">
        <h1 class="display-4"><b>Velkommen ombord </b></h1>
        <h4> Venligst login her: </h4>
        <form action="LoginServlet" method="post">
            <div class="form-group" style="padding-right: 30%; padding-left: 30%;">
                <label for="loginEmail">Email address</label>
                <input type="email" class="form-control" id="loginEmail" name="loginEmail" aria-describedby="emailHelp"
                       placeholder="Email">
                <small id="emailHelp" class="form-text text-muted" style="padding-left:0.5%;">Din email er vores
                    hemmelighed</small>
            </div>
            <div class="form-group" style="padding-right: 30%; padding-left: 30%;">
                <label for="loginPassword">Password</label>
                <input type="password" class="form-control" id="loginPassword" name="loginPassword"
                       placeholder="Password">
            </div>
            <div style="color: darkred; margin-left: 37%;">${requestScope.fejlBesked}</div>
            <br>
            <button type="submit" class="btn btn-primary" value="loginButton" style="margin-left: 45%;">Log ind</button>
        </form>
        <br>
        <form action="RegisterServlet">
            <button type="submit" class="btn btn-primary" value="regButton" style="margin-left: 42%">Registrer dig
                her!
            </button>
        </form>
    </div>
</div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
