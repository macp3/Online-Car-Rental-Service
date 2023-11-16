<%@ page import="java.net.InetAddress" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Suvami</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
<nav>
    <div class="logo-container">
        <a class="logo" href=<%="http://"+ InetAddress.getLocalHost().getHostAddress().trim() +":8080/start"%>>SUVAMI</a>
    </div>
    <ul class="nav-links">
        <li class="nav-link"><a href="#">Dla klienta</a></li>
        <li class="nav-link"><a href="#">Oferta dla firm</a></li>
        <li class="nav-link"><a href="#">Kontakt</a></li>
    </ul>
    <div class="login-container">
        <button>
            <a href="/login"
            ><img src="assets/icons/back_icon.svg" alt="User account"
            /></a>
        </button>
    </div>
</nav>
<main>
    <div class="container">
        <h2>Rejestracja</h2>
        <form class="input-container" method="post" action="/register">
            <div class="account-data">
                <div class="input-group">
                    <img src="assets/icons/email_icon.svg" alt="email_icon" />
                    <input type="email" placeholder="Podaj adres email" id="email" name="EMAIL" />
                </div>
                <div class="input-group">
                    <img src="assets/icons/password_icon.svg" alt="password_icon" />
                    <input type="password" placeholder="Podaj hasło" id="password" name="PASSWORD" />
                </div>
                <div class="input-group">
                    <img src="assets/icons/password_icon.svg" alt="password_icon" />
                    <input
                            type="password"
                            placeholder="Powtórz hasło"
                            id="CONFIRMPASSWORD"
                            name="CONFIRMPASSWORD"
                    />
                </div>
            </div>

            <div class="user-data">
                <div class="input-group">
                    <input class="user" type="text" placeholder="Imię" id="name" name="FIRSTNAME" />
                    <input
                            class="user"
                            type="text"
                            placeholder="Nazwisko"
                            id="secondName"
                            name="LASTNAME"
                    />
                </div>
                <div class="input-group">
                    <input class="user" type="text" placeholder="Miasto" id="city" name="CITY" />
                    <input
                            class="user flex-4"
                            type="text"
                            placeholder="Kod pocztowy"
                            id="cityCode"
                            name="CITYCODE"
                    />
                </div>
                <div class="input-group">
                    <input class="user" type="text" placeholder="Ulica" id="street" name="STREETNAME" />
                    <input
                            class="user flex-3"
                            type="number"
                            placeholder="Nr. domu"
                            id="houseNr"
                            name="HOUSENUM"
                    />
                </div>
                <div class="input-group">
                    <input
                            class="user"
                            type="text"
                            placeholder="Numer telefonu"
                            id="phoneNum"
                            name="PHONENUMBER"
                    />
                </div>
            </div>
            <p>${error}</p>

            <div class="checkbox-container">
                <input type="checkbox" name="" id="check1"/>
                <label for="check1"
                >Oświadczam, że ukończyłem 18 lat i zapoznałem się z treścią i
                    akcpetuję postanowienia regulaminu, wyrażając zgodę na
                    przetwarzanie danych osobowych</label
                >
            </div>
            <div class="checkbox-container">
                <input type="checkbox" name="" id="check2" />
                <label for="check2"
                >Oświadczam, że dane podane w formularzu są zgodne z prawdą</label
                >
            </div>

            <button type="submit" id="createAccount" class="btn">Utwórz konto</button>
            <a class="help-text register" href=<%="http://"+ InetAddress.getLocalHost().getHostAddress().trim() +":8080/login"%>>
            Masz już konto? Zaloguj się</a>
        </form>
    </div>
</main>
</body>
</html>
