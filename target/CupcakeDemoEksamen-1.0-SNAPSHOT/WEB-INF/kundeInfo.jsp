<%--
  Created by IntelliJ IDEA.
  User: oliverrasoli
  Date: 12/11/2021
  Time: 12.21
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../includes/header.inc" %>
<div class="container-fluid">
    <div class="jumbotron">
        <h2 style="text-align: center;"> Account Info </h2>
        <form action="EditServlet" method="get" id="editForm"> <!--Her skal servletten være som håndtere ændringer og ikke ændringer -->
            <form>
                <div class="row" style="justify-content: center">
                    <div class="col-auto">
                        Email
                        <input type="text" class="form-control" name="editEmail" id="editEmail"
                               style="background-color: #dddddd;" placeholder="${sessionScope.loginKunde.email}"
                               readonly>
                    </div>
                    <div class="col-auto">
                        Password
                        <input type="password" class="form-control" name="editPassword" id="editPassword"
                               style="background-color: #dddddd;" placeholder="*******" readonly>
                    </div>
                </div>
            </form>

            <br>
            <br>

            <h2 style="text-align: center;"> Personal Details </h2>

            <form>
                <div class="row" style="justify-content: center;">
                    <div class="col-auto">
                        Navn
                        <input type="text" class="form-control" name="editName" id="editName"
                               style="background-color: #dddddd;" placeholder="${sessionScope.loginKunde.name}"
                               readonly>
                    </div>
                    <div class="form-row">
                        <div class="col-7">
                            Adresse
                            <input type="text" class="form-control" name="editAdress" id="editAdress"
                                   style="background-color: #dddddd;" placeholder="${sessionScope.loginKunde.address}" readonly>
                        </div>
                        <div class="col">
                            Post Nr.
                            <input type="text" class="form-control" name="editPostNr" id="editPostNr"
                                   style="background-color: #dddddd;" placeholder="${sessionScope.loginKunde.postNr}" readonly>
                        </div>
                    </div>
                </div>
            </form>
            <br>
            <br>
            <br>
            <div class="row" style="justify-content: center; margin-left: 8%;">
                <div class="col-md-3">
                    <button type="submit" form="editForm" class="btn btn-primary btn-lg" >Edit info</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
