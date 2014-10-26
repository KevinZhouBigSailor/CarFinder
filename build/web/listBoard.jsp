<%-- 
    Document   : listBoard
    Created on : Apr 18, 2014, 1:58:08 AM
    Author     : xmrui_000
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.carsFinder.entity.ModelInfo"%>
<%@page import="com.carsFinder.entity.CarInfo"%>

<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page import="vo.StatusVO,java.util.*,com.carsFinder.entity.*,com.carsFinder.model.*" %>
<%@ page import="java.util.*,com.carsFinder.dao.*" %>
<jsp:useBean id="ab" scope="page" class="com.carsFinder.model.CarsBean"/>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="stylesheet" href="css/index.css" type="text/css" />
        <!-- Latest compiled and minified CSS -->
        <!--<link rel="stylesheet" href="css/bootstrap.min.css">-->
        <link rel="stylesheet" href="css/bootstrap.css">
            <link rel="stylesheet" href="css/carousel.css">
                <link rel="stylesheet" href="css/bootswatch.less">
                    <link rel="stylesheet" href="css/variables.less">
                        <link rel="stylesheet" href="css/semantic.css" />
                        <title>List Board</title>
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
                                                <a class="navbar-brand" href="#">Project name</a>
                                            </div>
                                            <div class="navbar-collapse collapse">
                                                <ul class="nav navbar-nav navbar-right">
                                                    <li><a href="#" data-toggle="modal" data-target="#myModal" >Hello! Mr.Car</a></li>
                                                    <li class="dropdown">
                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Your Menu<b class="caret"></b></a>
                                                        <ul class="dropdown-menu">
                                                            <li><a href="#">Action</a></li>
                                                            <li><a href="#">Another action</a></li>
                                                            <li><a href="#">Something else here</a></li>
                                                            <li class="divider"></li>
                                                            <li><a href="#">Separated link</a></li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <%-- header --%>
                            <div class="bs-header" id="content">
                                <div class="container" id="header">
                                    <%String[] makes = (String[]) request.getAttribute("make");
                                        String makesShow = "";
                                        if (makes.length > 1) {
                                            makesShow = "vehicles";
                                        } else {
                                            makesShow = makes[0];
                                        }
                                        String[] models = (String[]) request.getAttribute("model");
                                        String modelShow = "";
                                        if (models.length > 1) {

                                        } else {
                                            modelShow = models[0];
                                        }
                                        String[] years = (String[]) request.getAttribute("year");
                                        String yearShow = "";
                                        if (years.length > 1) {

                                        } else {
                                            yearShow = years[0];
                                        }
                                        String makeList = "";
                                        for (int i = 0; i < makes.length; i++) {
                                            makeList += "make=" + makes[i] + "&&";
                                        }
                                        String modelsSearch = "";
                                        for (int i = 0; i < models.length; i++) {
                                            modelsSearch += "model=" + models[i] + "&&";
                                        }
                                        String yearList = "";
                                        for (int i = 0; i < years.length; i++) {
                                            yearList += "year=" + years[i] + "&&";
                                        }%>

                                    <div class="page-header">
                                        <h1> <small>Used Car - <%=yearShow%> <%=makesShow%> <%=modelShow%></small></h1>
                                    </div>
                                </div>
                            </div>


                            <%-- left side tool --%>
                            <div class="container">
                                <div class="row clearfix">
                                    <div class="col-md-2 column">
                                        <h4>Your Recent Search:</h4>
                                        <%List tags = (List) request.getAttribute("tags");
                                            if (tags != null) {
                                                for (int i = 0; i < tags.size(); i++) {
                                                    if (i % 3 == 0 && i != 0) {%>
                                        <p>&nbsp;</p><%}%>
                                        <span class="label label-info"><%=tags.get(i)%></span> <%}
                                            }%>

                                        <%-- modify search part popover --%>
                                        <div id="popover-make" class="hide">
                                            <div class="row">
                                                <form action="CarlistServlet?method=searchCar&&model=Any Model&&year=Any Year&&page=first" method=post onsubmit="modifySearch(this)">
                                                    <div class="col-md-12 column">
                                                        <div class="form-group">
                                                            <% List<ModelInfo> list2 = (List<ModelInfo>) ab.getMake();
                                                                if (!list2.isEmpty()) {
                                                                    for (int i = 0; i < 10; i++) {%>
                                                            <div  class="checkbox-inline">
                                                                <label>  <input name="make" type="checkbox" value="<%= list2.get(i).getMake()%>"><p> <%= list2.get(i).getMake()%></p> </label>
                                                            </div>      <% }
                                                                }%>
                                                        </div>
                                                        <button type="submit" class="btn btn-info btn-sm btn-right">View Result</button>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>

                                        <div id="popover-model" class="hide">
                                            <div class="row">
                                                <form action="CarlistServlet?method=searchCar&&year=Any Year&&<%=makeList%>page=first" method=post onsubmit="modifySearch(this)">
                                                    <div class="col-md-12 column">
                                                        <div class="form-group">
                                                            <% List modelList = (List) request.getAttribute("modelList");
                                                                if (!modelList.isEmpty()) {
                                                                    for (int i = 0; i < modelList.size(); i++) {%>
                                                            <div  class="checkbox-inline">
                                                                <label>  <input name="model" type="checkbox" value="<%= modelList.get(i)%>"><p> <%= modelList.get(i)%></p> </label>
                                                            </div>      <% }
                                                                }%>
                                                        </div>
                                                        <button type="submit" class="btn btn-info btn-sm btn-right">View Result</button>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>

                                        <div id="popover-year" class="hide">
                                            <div class="row">
                                                <form action="CarlistServlet?method=searchCar&&<%=makeList%><%=modelsSearch%>page=first" method=post onsubmit="modifySearch(this)">
                                                    <div class="col-md-12 column">
                                                        <div class="form-group">
                                                            <% List yearCheck = (List) request.getAttribute("yearList");
                                                                if (!yearCheck.isEmpty()) {
                                                                    for (int i = 0; i < yearCheck.size(); i++) {%>
                                                            <div  class="checkbox-inline">
                                                                <label>  <input name="year" type="checkbox" value="<%= yearCheck.get(i)%>"><p> <%= yearCheck.get(i)%></p> </label>
                                                            </div>      <% }
                                                                }%>
                                                        </div>
                                                        <button type="submit" class="btn btn-info btn-sm btn-right">View Result</button>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>

                                        <%-- modify search part --%>
                                        <h4>&nbsp;</h4>                
                                        <h4>Modify Your Search:</h4>
                                        <form class="form-inline" action="CarlistServlet?method=searchCar" method=post>
                                            <div class="panel-group" id="panel-236824">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-236824" href="#panel-element-363070">Make</a>
                                                        <span class="glyphicon glyphicon-align-right glyphicon-chevron-right" style="float:right" data-toggle="make" data-placement="top"></span>
                                                    </div>
                                                    <div id="panel-element-363070" class="panel-collapse collapse active">
                                                        <%  for (int i = 0; i < makes.length; i++) {
                                                        %>
                                                        <div class="panel-body"><%=makes[i]%></div>
                                                        <%}%>
                                                    </div>
                                                </div>
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-236824" href="#panel-element-150811">Model</a>
                                                        <span class="glyphicon glyphicon-align-right glyphicon-chevron-right" style="float:right" data-toggle="model" data-placement="top"></span>
                                                    </div>
                                                    <div id="panel-element-150811" class="panel-collapse collapse active">
                                                        <% for (int i = 0; i < models.length; i++) {%>
                                                        <div class="panel-body">  <%=models[i]%></div><%}%>
                                                    </div>
                                                </div>
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-236824" href="#panel-element-150812">Year</a>
                                                        <span class="glyphicon glyphicon-align-right glyphicon-chevron-right" style="float:right" data-toggle="year" data-placement="top"></span>
                                                    </div>
                                                    <div id="panel-element-150812" class="panel-collapse collapse">

                                                    </div>
                                                </div>

                                            </div>
                                        </form>
                                    </div>

                                    <%-- middle side  --%>
                                    <div class="col-md-1 column">
                                    </div>



                                    <%-- right main  --%>
                                    <div class="col-md-9 column">
                                        <%-- tab controller  --%>
                                        <div class="tabbable" id="tabs-main1">
                                            <ul class="nav nav-pills">
                                                <li class="active">
                                                    <a href="#panel-carlist" data-toggle="tab"><span class="badge pull-right"><%String resultCount = (String) request.getAttribute("resultCount");%><%=resultCount%></span>Search Result</a>
                                                </li>
                                            </ul>

                                            <%-- tab content  --%>
                                            <div class="tab-content"> 

                                                <%-- list content  --%>
                                                <div class="tab-pane active" id="panel-carlist">
                                                    <h3>
                                                        Cars List:
                                                    </h3>
                                                    <div id="carList" class="row">

                                                        <% List<CarInfo> list = (List<CarInfo>) request.getAttribute("carList");
                                                            if (list != null) {
                                                                int rowNum = 0;
                                                                if (list.size() % 3 == 0) {
                                                                    rowNum = list.size() / 3;
                                                                } else {
                                                                    rowNum = list.size() / 3 + 1;
                                                                }
                                                                for (int j = 0; j < rowNum; j++) {%>
                                                        <div class="row">
                                                            <% for (int i = j * 3; i < (j + 1) * 3; i++) {%>
                                                            <div class="col-md-4 height-limited" id="blockquote<%=i%>">
                                                                <div class="thumbnail">
                                                                    <h4 id="carName"><%=list.get(i).getCarName()%></h4>
                                                                    <img alt="300x200" src="<%=list.get(i).getImageUrl()%>" />
                                                                    <div class="caption">

                                                                        <h5> <%=list.get(i).getMileage()%> </h5><h5> <%=list.get(i).getDoorCount()%> </h5>
                                                                        <h5> <%=list.get(i).getColor()%></h5><h5> <%=list.get(i).getDriveTrain()%>  </h5> 
                                                                        <h5> <%=list.get(i).getEngineDescription()%></h5><h5> <%=list.get(i).getTransmission()%>  </h5> 

                                                                        <a class="btn btn-info"  id="view<%=i%>" data-toggle="modal" data-target="#detail<%=i%>"  onclick="detail(this)" href="<%=i%>/<%=list.get(i).getRecordID()%>/<%=list.get(i).getCarName()%>/<%=list.get(i).getYear()%>">View Detail</a>
                                                                        <div class="modal fade" id="detail<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                                                        <h4 class="modal-title" id="myModalLabel"><%=list.get(i).getCarName()%></h4>
                                                                                    </div>
                                                                                    <div id="modal-body<%=i%>" class="modal-body">
                                                                                       <div class="outer_div">
                                                                                            <div class="inner_div">
                                                                                                <div id="percent_count">     
                                                                                                
                                                                                                </div>
                                                                                                 </div></div>
                                                                                    </div>
                                                                                    <div class="modal-footer">
                                                                                        <button type="button" class="btn btn-default btn-group-sm" data-dismiss="modal">Close</button>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>  <%}%>
                                                        </div>  <%}
                                                            }%>
                                                        <%-- pagination --%>

                                                        <ul class="pager"   >
                                                            <%String pageNum = (String) request.getAttribute("pageNum");

                                                                String price = (String) request.getAttribute("price");
                                                                if (price == null) {
                                                                    price = "Any Price";
                                                                }
                                                                String currentPage = (String) request.getAttribute("currentPage");
                                                                int nextPage = Integer.parseInt(currentPage) + 1;
                                                                int prevPage = Integer.parseInt(currentPage) - 1;
                                                                int pagCount = Integer.parseInt(pageNum);
                                                                if (prevPage != 0) {%>
                                                            <li class="previous pageChange" onclick="pageChange(this)" value="CarlistServlet?method=searchCar&&price=<%=price%>&&<%=yearList%><%=makeList%><%=modelsSearch%>page=<%=prevPage%>">
                                                                <a >Prev</a>
                                                            </li>
                                                            <%} else if (prevPage == 0) {%>
                                                            <li class="previous pageChange disabled">
                                                                <a >Prev</a>
                                                            </li>
                                                            <%}%>
                                                            <% if (nextPage < pagCount) {%>
                                                            <li class="next pageChange" onclick="pageChange(this)" value="CarlistServlet?method=searchCar&&price=<%=price%>&&<%=yearList%><%=makeList%><%=modelsSearch%>&&page=<%=nextPage%>">
                                                                <a>Next</a>
                                                            </li><%} else if (nextPage > pagCount) {%>
                                                            <li class="next pageChange disabled" >
                                                                <a >Next</a>
                                                            </li>
                                                            <%}%>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <%-- end car list --%>
                                                <%-- recommendation --%>
                                                <div class="tab-pane" id="panel-recommendation">  
                                                    <h3>
                                                        Recommend Cars:
                                                    </h3>

                                                </div>
                                                <%-- end recommendation --%>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script src="js/jquery.min.js"></script> 
                            <script src="js/bootstrap.min.js"></script>
                            <script type="text/javascript" src="js/json2.js"></script>
                            <script type="text/javascript" src="js/make_model_switch.js"></script>
                            <script type="text/javascript" src="js/popover.js"></script>
                            <script type="text/javascript" src="js/pagination.js"></script>
                            <script type="text/javascript" src="js/detail.js"></script>
                        <script type="text/javascript" src="js/progressbar.js"></script></body>
                        </html>

