<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>

<style>
    .button {
        width: 300px;

    }

    .form-check {
        margin-right: 3%;
    }

    .table-striped {
        background-color: #d6b387;
        color: #e9ecef
    }

    tr.table-striped {
        background-color: #e9ecef;
    }

    .ToIndex {
        margin-left: 50%;
    }

    .DinMail {
        width: 300px;
    }

    .Checker {
        margin-left: 20px;
    }

</style>
<body>

<div class="container-fluid">
    <table class="table table-striped">
        <th> Bunde:</th>
        <th> Toppings:</th>
        <th> Antal:</th>
        <th> I Alt:</th>
        <c:forEach var="cupcakeItem" items="${sessionScope.cupcakeList}">
            <tr>
                <td>${cupcakeItem.bunde.navn}</td>
                <td>${cupcakeItem.topping.navn}</td>
                <td> ${cupcakeItem.antal} stk</td>
                <td> ${cupcakeItem.pris},- kr.</td>
            </tr>
        </c:forEach>
        <tr>
            <td>Ialt</td>
            <td></td>
            <td></td>
            <td> ${sessionScope.samletPris},- kr</td>

        </tr>
    </table>
</div>


<form action="CheckoutServlet" method="post">

    <div class="container-fluid">
        <button class=" btn btn-primary button">Check out</button>
    </div>

    <div class="form-check Checker">
        <input class="form-check-input " type="checkbox" name="info" id="emailCheck">
        <label class="form-check-label" for="emailCheck">
            Jeg vil gerne modtage min order på mail.
        </label>
    </div>

    <br/>

    <div class="container-fluid">
        <label for="OrderEmail" class="form-label">Email address</label>
        <input type="email" class="form-control DinMail" id="OrderEmail" placeholder="Din mail">
    </div>
</form>


<form action="index.jsp">
    <div>
        <button class="btn-primary ToIndex" type="submit">Til forsiden</button>
    </div>
</form>


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