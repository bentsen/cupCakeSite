<div class="container-fluid">
    <div class="jumbotron">
        <h2 style="text-align: center;"> Account Info </h2>
        <form action="EditServlet" method="post" id="editForm">
            <form>
                <div class="row" style="margin-left: 25%;">
                    <div class="col-auto">
                        <input type="text" class="form-control" name="editEmail" id="editEmail"
                                placeholder="${sessionScope.loginKunde.email}">
                    </div>
                    <div class="col-auto">
                        <input type="password" class="form-control" name="editPassword" id="editPassword"
                               placeholder="${sessionScope.loginKunde.password}">
                    </div>
                </div>
            </form>

            <br>
            <br>

            <h2 style="text-align: center;"> Personal Details </h2>

            <form>
                <div class="row" style="margin-left: 15%;">
                    <div class="col-auto">
                        <input type="text" class="form-control" name="editName" id="editName"
                                placeholder="${sessionScope.loginKunde.name}">
                    </div>
                    <div class="form-row">
                        <div class="col-7">
                            <input type="text" class="form-control" name="editAdress" id="editAdress"
                                   placeholder="${sessionScope.loginKunde.address}">
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" name="editPostNr" id="editPostNr"
                                   placeholder="${sessionScope.loginKunde.postNr}"
                        </div>
                    </div>
                </div>
            </form>
            <br>
            <br>
            <br>
