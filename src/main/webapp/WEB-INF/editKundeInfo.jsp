<%--
  Created by IntelliJ IDEA.
  User: oliverrasoli
  Date: 15/11/2021
  Time: 14.26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.inc" %>
<div class="container-fluid">
    <div class="jumbotron">
        <h2 style="text-align: center;"> Account Info </h2>
        <form action="EditServlet" method="post" id="editForm">

                <div class="row" style="justify-content: center;">
                    <div class="col-auto">
                        Email
                        <input type="text" class="form-control" name="editEmail" id="editEmail"
                               value="${sessionScope.loginKunde.email}">
                    </div>
                    <div class="col-auto">
                        Password
                        <input type="password" class="form-control" name="editPassword" id="editPassword"
                               value="${sessionScope.loginKunde.password}">
                    </div>
                </div>


            <br>
            <br>

            <h2 style="text-align: center;"> Personal Details </h2>


                <div class="row" style="justify-content: center;">
                    <div class="col-auto">
                        Navn
                        <input type="text" class="form-control" name="editName" id="editName"
                               value="${sessionScope.loginKunde.name}">
                    </div>
                    <div class="form-row">
                        <div class="col-7">
                            Adresse
                            <input type="text" class="form-control" name="editAdresse" id="editAdresse"
                                   value="${sessionScope.loginKunde.address}">
                        </div>
                        <div class="col">
                            Post Nr.
                            <input type="text" class="form-control" name="editPostNr" id="editPostNr"
                                   value="${sessionScope.loginKunde.postNr}">
                        </div>
                    </div>
                </div>

            <br>
            <br>
            <br>
            <div class="row" style="justify-content: center;">
                <div class="col-md-3">
                    <button type="submit" name="editAct" value="DiscardKnap" form="editForm"
                            class="btn btn-primary btn-lg">Kassér ændringer
                    </button>
                </div>
                <div class="col-md-3">
                    <button type="submit" name="editAct" value="gemKnap" form="editForm" class="btn btn-primary btn-lg">
                        Gem ændringer
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>