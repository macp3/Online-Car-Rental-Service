<%@ page import="java.net.InetAddress" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <a class="logo" href=<%="http://"+ InetAddress.getLocalHost().getHostAddress().trim() +":8080/start"%> >SUVAMI</a>
    </div>
    <ul class="nav-links">
        <li class="nav-link"><a href="#">Dla klienta</a></li>
        <li class="nav-link"><a href="#">Oferta dla firm</a></li>
        <li class="nav-link"><a href="#">Kontakt</a></li>
    </ul>
    <div class="login-container">
        <button>
            <a href=<%="http://"+ InetAddress.getLocalHost().getHostAddress().trim() +":8080/login"%>
            ><img src="assets/icons/account_icon.svg" alt="User account"
            /></a>
        </button>
    </div>
</nav>
<main>
    <div class="container">
        <h2>Wypożycz pojazd</h2>
        <form method="post" action="/start">
            <div class="input-group">
                <img src="assets/icons/location_icon.svg" alt="location" />
                <input type="text" placeholder="Podaj miasto" id="city" name="city" />
            </div>

            <div class="input-group">
                <input type="date" name="StartDate"/>
                <input type="date" name="EndDate"/>
            </div>

            <button type="submit" class="btn">Szukaj</button>
            <p>${error}</p>
        </form>
    </div>
</main>
</body>
</html>
