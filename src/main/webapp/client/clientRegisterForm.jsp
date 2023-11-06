<%@ page import="java.net.InetAddress" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Jazda z SUVami</title>
</head>
<body>
<h1> Zarejestruj się
</h1>
<form method="post" action="/client/register">

    <label for="EMAIL">Email</label>
    <input type="email" id="EMAIL" name="EMAIL"><br>

    <label for="FIRSTNAME">Imię</label>
    <input type="text" id="FIRSTNAME" name="FIRSTNAME"><br>

    <label for="LASTNAME">Nazwisko</label>
    <input type="text" id="LASTNAME" name="LASTNAME"><br>

    <label for="PASSWORD">Hasło</label>
    <input type="password" id="PASSWORD" name="PASSWORD"><br>

    <label for="CONFIRMPASSWORD">Potwierdź hasło</label>
    <input type="password" id="CONFIRMPASSWORD" name="CONFIRMPASSWORD"><br>

    <label for="PHONENUMBER">Numer telefonu</label>
    <input type="tel" id="PHONENUMBER" name="PHONENUMBER"><br>

    <label for="BILLINGADDRESS">Adres rozliczeniowy (opcjonalnie)</label>
    <input type="text" id="BILLINGADDRESS" name="BILLINGADDRESS"><br>

    <p>Preferowana forma płatności</p>
    <input type="radio" id="Card" name="PREFFEREDPAYMENTID" value="1" checked>
    <label for="Card">Karta</label><br>
    <input type="radio" id="TraditionalTransfer" name="PREFFEREDPAYMENTID" value="2">
    <label for="TraditionalTransfer">Przelew tradycyjny</label><br>
    <input type="radio" id="BLIK" name="PREFFEREDPAYMENTID" value="3">
    <label for="BLIK">BLIK</label><br>

    <button type="submit">Zarejestruj się</button><br>

    <p>${error}</p>

</form>
<p>lub</p><br>
<a href=<%="http://"+ InetAddress.getLocalHost().getHostAddress().trim() +":8080/user/login"%>>
    <button>Zaloguj się</button>
</a><br>

</body>
</html>