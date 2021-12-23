<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Today's Forecast</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Alata&amp;display=swap">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
</head>

<body>
    <div class="modal fade" role="dialog" tabindex="-1" id="modal">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header" style="background: var(--bs-blue);">
                    <h4 class="modal-title" style="font-weight: bold;">Advanced search</h4><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4" style="border-right-width: 1.5px;border-right-style: solid;">
                                <p style="height: 50px;font-size: 19px;font-weight: bold;">Search by:</p>
                                <form>
                                    <div>
                                        <div class="form-check">
                                          <input class="form-check-input" type="checkbox" id="formCheck1" value="1" name="check" >
                                            <label class="form-check-label" for="formCheck1">City ID</label></div><input class="form-control" type="number">
                                        <div style="min-width: auto;height: 50px;"></div>
                                        <div class="form-check">
                                          <input class="form-check-input" type="checkbox" id="formCheck2" value="2" name="check" >
                                            <script type="text/javascript">
                                            $(".form-check-input").click("change", function() {
                                              $(".form-check-input").not(this).prop("checked", false);
                                            });
                                            </script>
                                            <label class="form-check-label" for="formCheck2">Name</label></div><input class="form-control" type="text" style="margin-bottom: 50px;">
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-4" style="border-right-width: 1.5px;border-right-style: solid;border-left-width: 1.5px;border-left-style: solid;">
                                <p style="min-height: 48px;font-size: 19px;font-weight: bold;height: 50px;">Select data and time:</p>
                                <form>
                                  <script>
                                  function getDataTime() {
                                    var d = new Date();
                                    var mm = d.getMonth() + 1;
                                    var gg = d.getDate();
                                    var h = d.getHours();
                                    var min = d.getMinutes();
                                    if (mm < 10)
                                      mm = "0" + mm;
                                    if (gg<10)
                                      gg = "0" + gg;
                                    if (h<10)
                                      h = "0" + h;
                                    if (min<10)
                                      min = "0" + min;
                                    var date = d.getFullYear() + "-" + mm + "-" + gg + "T" +h + ":"  +min;
                                    document.getElementById("data1").setAttribute("min", date);
                                  }
                                  </script>
                                  <label class="form-label" for="datafrom" style="margin-bottom: 2px;">From:</label>
                                  <input class="form-control" type="datetime-local" id ="data1" style="height: 38px;padding: 6px 12px;min-width: 130px;max-width: 225px;">
                                    <div style="min-width: auto;height: 50px;"></div><label class="form-label" style="margin-bottom: 2px;">to:</label>
                                    <input class="form-control" type="datetime-local" min = "2021-12-21T11:11" style="height: 38px;padding: 6px 12px;margin-bottom: 50px;min-width: 130px;max-width: 225px;">
                                </form>
                            </div>
                            <div class="col-md-4" style="border-left-width: 1.5px;border-left-style: solid;">
                                <p style="font-size: 19px;font-weight: bold;height: 50px;min-height: 48px;">Other options:</p>
                                <form><label class="form-label" style="margin-bottom: 2px;">Language:</label><select class="form-select">
                                        <option value="en" selected="">English</option>
                                        <option value="it">Italian</option>
                                        <option value="fr">French</option>
                                        <option value="ru">Russian</option>
                                        <option value="sp">Spanish</option>
                                    </select>
                                    <div style="min-width: auto;height: 50px;"></div>
                                </form>
                                <form><label class="form-label" style="margin-bottom: 2px;">Unit:</label><select class="form-select" style="margin-bottom: 30px;">
                                        <option value="metric" selected="">Celsius</option>
                                        <option value="standard">Kelvin</option>
                                        <option value="imperial">Fahrenheit</option>
                                    </select></form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-bs-dismiss="modal" style="background: rgb(255,255,255);">Close</button><button class="btn btn-primary" type="button">Apply</button></div>
            </div>
        </div>
    </div>
    <header class="text-center text-white masthead" style="font-size: 13px;background: url(&quot;assets/img/background.jpg&quot;);background-size: cover;">
        <nav class="navbar navbar-light navbar-expand-md fixed-top">
            <div class="container-fluid"><a class="navbar-brand" href="#"><img src="assets/img/logo.png"></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navcol-1" style="width: 1000px;">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item d-flex float-end flex-row-reverse" style="height: 50px;margin-top: 20px;"><button class="btn btn-primary d-flex float-end" type="button" data-bs-toggle="modal" data-bs-target="#modal" style="margin-top: 0px;border: 2px solid rgb(0,0,0);border-radius: 15px;color: #000000;font-size: 20px;font-family: Alata, sans-serif;font-weight: bold;background: var(--bs-white);">Advanced search</button></li>
                        <li class="nav-item" style="margin-top: 20px;"><a class="nav-link d-flex float-end" href="#about" style="font-size: 20px;font-family: Alata, sans-serif;font-weight: bold;color: rgb(0,0,0);height: 50px;">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="col-xl-9 mx-auto position-relative">
                    <h1 class="mb-5" style="color: rgb(0,0,0);">Build a landing page for your business or project and generate more leads!</h1>
                </div>
                <div class="col-md-10 col-lg-8 col-xl-7 mx-auto position-relative">
                    <form action="hello" method="post">
                        <div class="row">
                            <div class="col-12 col-md-9 mb-2 mb-md-0">
                              <input class="form-control form-control-lg" type="number" name="city" placeholder="Enter a city ID">
                            </div>
                            <div class="col-12 col-md-3"><button class="btn btn-primary btn-lg" type="submit" style="background: rgb(0,123,255);border-style: solid;border-color: rgb(0,0,0);border-radius: 15px;color: rgb(255,255,255);"><i class="fa fa-search"></i>&nbsp; Search</button></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </header>
    <section class="text-center bg-light features-icons">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
                        <div class="d-flex features-icons-icon"><i class="fa fa-certificate" style="width: 72px;height: 72px;margin: 20px;margin-right: 124px;margin-left: 124px;margin-bottom: 20px;margin-top: 20px;color: rgb(0,123,255);"></i></div>
                        <h3 style="color: #c67f07;">Certificated!</h3>
                        <p class="lead mb-0">The data is frequently updated based on the global and local weather models, satellites, radars and a vast network of weather stations.<br></p>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
                        <div class="d-flex features-icons-icon"><i class="fa fa-map" style="height: 72px;margin-top: 20px;margin-right: 124px;margin-bottom: 20px;margin-left: 124px;color: rgb(0, 123, 255);"></i></div>
                        <h3>All cities in the world</h3>
                        <p class="lead mb-0">Access current weather data for any location on Earth including over 200,000 cities!<br></p>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="mx-auto features-icons-item mb-5 mb-lg-0 mb-lg-3">
                        <div class="d-flex features-icons-icon"><i class="icon-check m-auto text-primary" data-bss-hover-animate="pulse"></i></div>
                        <h3>Easy to Use</h3>
                        <p class="lead mb-0">Simple, easy and intuitive navigation;<br></p>
                        <p class="lead mb-0">Fluid and flexible layout which adjusts according to screen size.<br></p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section id="about" class="showcase">
        <div class="container-fluid p-0">
            <div class="row g-0">
                <div class="col-lg-6 text-white order-lg-2 showcase-img" style="background: url(&quot;assets/img/code.jpg&quot;);"><span></span></div>
                <div class="col-lg-6 my-auto order-lg-1 showcase-text">
                    <h2>About</h2>
                    <p class="lead mb-0">When you use a theme created with Bootstrap, you know that the theme will look great on any device, whether it's a phone, tablet, or desktop the page will behave responsively!</p>
                </div>
            </div>
        </div>
    </section>
    <footer class="bg-light footer" style="height: 100px;">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 text-center text-lg-start my-auto h-100">
                    <ul class="list-inline mb-2">
                        <li class="list-inline-item"><a href="#about">About</a></li>
                    </ul>
                    <p class="text-muted small mb-4 mb-lg-0">© Brand 2021. All Rights Reserved.</p>
                </div>
                <div class="col-lg-6 text-center text-lg-end my-auto h-100">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item"><a href="https://www.univpm.it/Entra"><i class="fa fa-university fa-2x fa-fw" style="color: var(--bs-gray-900);"></i></a></li>
                        <li class="list-inline-item"><a href="https://github.com/andrea-giampieri-univpm/progetto-esame-po"><i class="fa fa-github fa-2x fa-fw" style="color: var(--bs-gray-900);"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
    <script src="assets/js/checkbox.js"></script>
</body>

</html>
