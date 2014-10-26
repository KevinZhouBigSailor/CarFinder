<%@page import="com.carsFinder.entity.ModelInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page import="java.util.*,com.carsFinder.dao.*" %>
<jsp:useBean id="cb" scope="page" class="com.carsFinder.model.CarsBean"/>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="<%=basePath%>">
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <script type="text/javascript" src="js/jquery.min.js"></script>
            <script type="text/javascript" src="js/make_model_switch.js"></script>

            <link rel="stylesheet" href="css/index.css" type="text/css" />
            <!-- Latest compiled and minified CSS -->
            <!--<link rel="stylesheet" href="css/bootstrap.min.css">-->
            <link rel="stylesheet" href="css/bootstrap.css">
                <link rel="stylesheet" href="css/carousel.css">
                    <link rel="stylesheet" href="css/bootswatch.less">
                        <link rel="stylesheet" href="css/variables.less">
                            <title>CarsFinder</title>
                            </head>

                            <body>
                                <div class="navbar-wrapper">
                                    <div class="container">

                                        <div class="navbar navbar-inverse navbar-static-top" role="navigation">
                                            <div class="container">
                                                <div class="navbar-header">
                                                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                                        <span class="sr-only">Toggle navigation</span>
                                                        <span class="icon-bar"></span>
                                                        <span class="icon-bar"></span>
                                                        <span class="icon-bar"></span>
                                                    </button>
                                                    <a class="navbar-brand" href="#">Cars Finder</a>
                                                </div>
                                                <div class="navbar-collapse collapse">
                                                    <ul class="nav navbar-nav navbar-right">
                                                        <li><a href="#" data-toggle="modal" data-target="#myModal" >Hello! Mr.Car</a></li>
                                                        <li class="dropdown">
                                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Your Menu<b class="caret"></b></a>
                                                            <ul class="dropdown-menu">
                                                            <li><a href="#">Your Wish List</a></li>
                                                            <li><a href="#">Your Recommendations</a></li>
                                                            <li><a href="#">Exit</a></li>
                                                        </ul>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>




                                <!-- Carousel
                                 ================================================== -->
                                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                                    <div id="container">
                                        <div id="search" class="carousel-caption jumbotron">
                                            <h2>Find Cars for Sale!</h2>
                                            <p>&nbsp;</p>
                                            <form class="form-inline" action="CarlistServlet?method=searchCar&&page=first" method=post>
                                                <fieldset>
                                                    <div class="form-group">
                                                        <label for="select" class="col-lg-3 control-label">Make:</label>
                                                        <div class="col-lg-8">
                                                            <%
                                                                List<ModelInfo> list = (List<ModelInfo>)cb.getMake();
                                                                if (!list.isEmpty()) {%>
                                                            <select class="form-control" id="selectMake" name="make">
                                                                <option>---Please Choose Your Make---</option>
                                                                <%for (int i = 0; i < list.size(); i++) {

                                                                            out.println("<option value=" + list.get(i).getMake() + ">" + list.get(i).getMake() + "</option>");
                                                                        }
                                                                    }%>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <p>&nbsp;</p>
                                                    </fieldset>
                                                    <fieldset>
                                                    <div class="form-group">
                                                        <label for="select" class="col-lg-3 control-label">&nbsp;&nbsp;&nbsp;Model:</label>
                                                        <div class="col-lg-8">
                                                            <select class="form-control" id="selectModel" name="model">
                                                                
                                                                <option value="">---Please Choose Your Model---</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <p>&nbsp;</p>
                                                    </fieldset>
                                                    <fieldset>
                                                    <div class="form-group">
                                                        <label for="select" class="col-lg-3 control-label">Year:</label>
                                                        <div class="col-lg-8">
                                                            <select class="form-control" id="selectPrice" name="year">
                                                                  <option value="Any Year">Any Year</option>
                                                                <%for(int i = 2000;i<=2014;i++){%>
                                                                <option value="<%=i%>"><%=i%></option>
                                                                <%}%>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <p>&nbsp;</p>
                                                    <!-- <div class="form-group">
                                                        <label for="select" class="col-lg-3 control-label">Price:</label>
                                                        <div class="col-lg-8">
                                                            <select class="form-control3" id="selectPrice" name="price">
                                                                
                                                                <%for(int i=0;i<200000;i+=5000){%>
                                                                <option value="<%=i%>"><%=i%> - <%=i+5000%></option>
                                                                <%}%>
                                                            </select>
                                                        </div>
                                                    </div> -->
                                                 
                                                    <div class="form-group">
                                                        <div class="col-lg-8">
                                                            <button type="submit" class="btn-right btn-primary btn-lg">Search</button>
                                                        </div>
                                                    </div>

                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Indicators -->
                                    <ol class="carousel-indicators">
                                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                        <li data-target="#myCarousel" data-slide-to="1"></li>
                                        <li data-target="#myCarousel" data-slide-to="2"></li>
                                        <li data-target="#myCarousel" data-slide-to="3"></li>
                                        <li data-target="#myCarousel" data-slide-to="4"></li>
                                    </ol>
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <img src="image/2014_hamann_bmw_m6_mirr6r-2560x1440.jpg" alt="First slide">         
                                        </div>
                                        <div class="item">
                                            <img alt="Second slide" src="image/2015_ford_mustang_2-2560x1600.jpg">    
                                        </div>
                                        <div class="item">
                                            <img src="image/2014_lexus_is_awd_by_gordon_ting-2560x1600.jpg" alt="Third slide">
                                        </div>
                                        <div class="item">
                                            <img src="image/2013_g_power_bmw_m3_hurricane_rs-2560x1600.jpg" alt="Third slide">
                                        </div>
                                        <div class="item">
                                            <img src="image/2014_lamborghini_veneno_roadster-2560x1600.jpg" alt="Third slide">
                                        </div>
                                    </div>
                                    <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
                                    <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
                                </div>


                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">User Login</h4>
                                            </div>
                                            <form class="form-horizontal">

                                                <div class="modal-body">
                                                    <div class="form-group">  

                                                        <label for="username" class="col-lg-2 control-label">Username</label>
                                                        <div class="col-lg-10">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">  

                                                        <label for="username" class="col-lg-2 control-label">Password</label>
                                                        <div class="col-lg-10">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary">Login</button>
                                                </div>

                                            </form>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal-dialog -->
                                </div><!-- /.modal -->
                                <script src="js/jquery.min.js"></script> 
                                <script src="js/bootstrap.min.js"></script>
                                <script src="js/modelSearch.js"></script>
                            </body>
                            </html>
