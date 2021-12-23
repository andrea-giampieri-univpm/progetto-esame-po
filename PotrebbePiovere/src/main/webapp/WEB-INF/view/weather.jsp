<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Today's Forecast</title>
    <link rel="stylesheet" href="asset/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Alata&amp;display=swap">
    <link rel="stylesheet" href="asset/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="asset/fonts/ionicons.min.css">
    <link rel="stylesheet" href="asset/css/Footer-Basic.css">
</head>

<body>
    <header class="text-center text-white masthead" style="font-size: 13px;background: url(&quot;asset/img/background%202.jpg&quot;) top / cover repeat;height: 1000px;">
        <nav class="navbar navbar-light navbar-expand-md fixed-top">
            <div class="container-fluid"><a class="navbar-brand" href="#"><img src="asset/img/logo.png"></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-1" style="width: 1000px;">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item" style="margin-top: 20px;"><a class="nav-link d-flex float-end" href="" style="font-size: 20px;font-family: Alata, sans-serif;font-weight: bold;color: rgb(0,0,0);height: 50px;">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container" style="color: rgb(0,0,0);background: #ffffff;border-style: solid;border-radius: 35px;">
            <div class="row">
                <div class="col-md-6" style="border-right-width: 1.5px;border-right-style: solid;">
                    <div class="row">
                        <div class="col">
                            <div class="row">
                                <div class="col" style="background: var(--bs-blue);border-style: none;">
                                    <h1>${city}</h1>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <h3 style="text-align: left;">${country}</h3>
                                </div>
                                <div class="col">
                                    <h3>Date and Time</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="row">
                                <div class="col"><i class="fa fa-star" style="font-size: 100px;margin-top: 25px;margin-bottom: 25px;"></i></div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <h1>23&degC</h1>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <h2>${description}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div style="height: 35px;"></div>
                            <p class="text-start" style="margin-left: 20px;">Temperature: &deg${simbol}</p>
                            <p class="text-start" style="margin-left: 20px;">Pressure: hPA</p>
                            <p class="text-start" style="margin-left: 20px;">Humidity: %</p>
                            <p class="text-start" style="margin-left: 20px;">Cloudy: %</p>
                            <p class="text-start" style="margin-left: 20px;">Wind: m/s</p>
                            <p class="text-start" style="margin-left: 20px;">Rain: mm/h</p>
                            <p class="text-start" style="margin-left: 20px;">Snow: mm/h</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div style="height: 60px;"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6" style="border-left-width: 1.5px;border-left-style: solid;">
                    <div class="row">
                        <div class="col" style="background: var(--bs-blue);">
                            <h1>STATISTICS</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <h2 class="text-start">Range:</h2>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div style="height: 30px;"></div>
                            <h3>Pressure</h3>
                            <div style="height: 30px;"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col" style="margin-left: 20px;">
                            <p>MIN:    ${pressMin}  hPa</p>
                            <p>MAX:    ${pressMax}  hPa</p>
                            <p>AVERAGE:    ${presAverage}  hPa</p>
                            <p>VARIANCE:    ${pressVariance}  hPa</p>
                            <div style="height: 35px;"></div>
                        </div>
                    </div>
                    <div style="height: 50px;"><button class="btn btn-primary btn-lg d-flex float-end" type="button" style="border-style: solid;border-color: rgb(0,0,0);border-radius: 15px;margin: 0px;margin-right: 20px;">BACK</button></div>
                </div>
            </div>
        </div>
    </header>
    <footer class="footer-basic" style="height: 130px;">
        <div class="container">
            <div class="row">
                <div class="col-md-6 d-flex float-end">
                    <ul class="list-inline" style="width: 100%;">
                        <li class="list-inline-item"><a href="">About</a></li>
                    </ul>
                </div>
                <div class="col-md-6 d-flex float-start">
                    <p class="d-block copyright" style="height: 30px;width: 100%;margin-top: 6px;">Company Name © 2021</p>
                </div>
            </div>
        </div>
        <div class="social" style="height: 100%;"><a href="https://github.com/andrea-giampieri-univpm/progetto-esame-po"><i class="icon ion-social-github"></i></a><a href="https://www.univpm.it/Entra"><i class="fa fa-university"></i></a></div>
    </footer>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>
