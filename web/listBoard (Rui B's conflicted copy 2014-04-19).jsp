<%-- 
    Document   : listBoard
    Created on : Apr 18, 2014, 1:58:08 AM
    Author     : xmrui_000
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page import="vo.StatusVO,java.util.*,com.carsFinder.entity.*,com.carsFinder.model.*" %>

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

                            <div class="bs-header" id="content">
                                <div class="container" id="header">
                                    <%String make = (String) request.getAttribute("make");
    String model = (String) request.getAttribute("model");%>
                                    <h2>Used <%=make%> <%=model%> Car</h2>

                                </div>
                            </div>

                            <div class="container">
                                <div class="row clearfix">
                                    <div class="col-md-2 column">
                                        <h3>
                                            Your Recent Search:
                                        </h3>
                                        <p><%List tags = (List) request.getAttribute("tags");
                            if (tags != null) {
                                for (int i = 0; i < tags.size(); i++) {%>
                                            <span class="label label-info"><%=tags.get(i)%></span> <%}
                             }%>
                                        </p>
                                        <h4>Modify Your Search:</h4>
                                        <div class="panel-group" id="panel-236824">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-236824" href="#panel-element-363070">Make</a>
                                                </div>
                                                <div id="panel-element-363070" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        Acura
                                                    </div>
                                                    <div class="panel-body">
                                                        BMW
                                                    </div>
                                                    <div class="panel-body">
                                                        Mercedes-Benz
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <a class="panel-title" data-toggle="collapse" data-parent="#panel-236824" href="#panel-element-150811">Model</a>
                                                </div>
                                                <div id="panel-element-150811" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        328xi
                                                    </div>
                                                    <div class="panel-body">
                                                        335xi
                                                    </div>
                                                    <div class="panel-body">
                                                        530i
                                                    </div>
                                                    <div class="panel-body">
                                                        760i
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <a class="panel-title" data-toggle="collapse" data-parent="#panel-236824" href="#panel-element-150811">Year</a>
                                                </div>
                                                <div id="panel-element-150811" class="panel-collapse collapse">
                                                    <div class="panel-body">
                                                        2001
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-1 column">
                                    </div>
                                    <div class="col-md-9 column">
                                        <h3>
                Twitter Reviews:
            </h3>
            <div class="tabbable" id="tabs-975169">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#panel-539015" data-toggle="tab">Postive</a>
                    </li>
                    <li>
                        <a href="#panel-109216" data-toggle="tab">Negetive</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="panel-539015">
                        
                                <div class="ui raised segment" style="margin-left:40px;width:700px">
                                <s:iterator value="top5GoodStatus">
                                                        <%List<StatusVO> good_twitte = (List)request.getAttribute("goodtwitte");
                                                                if(good_twitte!=null){ for(int i=0;i<good_twitte.size();i++){%>
                                    <div class="ui blue message" style="font-size:22px"><%=good_twitte.get(i).getText()%></div><%}}%>
                                </s:iterator>
                                </div>
                         
                    </div>
                    <div class="tab-pane" id="panel-109216">
                        
                                <div class="ui raised segment" style="margin-left:40px;width:700px">
                                <s:iterator value="top5BadStatus">
                                                                <%List<StatusVO> bad_twitte = (List)request.getAttribute("badtwitte");
                                                                if(bad_twitte!=null){ for(int i=0;i<bad_twitte.size();i++){%>    
                                    <div class="ui red message" style="font-size:22px"><%=bad_twitte.get(i).getText()%></div><%}}%>
                                </s:iterator>
                    </div>
                </div>
            </div>
            </div>
                                        <h3>
                                            Recommend Cars:
                                        </h3>
                                        <div class="row">
                                            <%List<CarInfo> recomList = (List<CarInfo>)request.getAttribute("recomdcar");
                                            if(recomList!=null){
                                                 for(int i =0;i<recomList.size()&&i<3;i++){%>
                                            <div class="col-md-4">
                                                <div class="thumbnail">
                                                    <img alt="300x200" src="<%=recomList.get(i).getImageUrl()%>" />
                                                    <div class="caption">
                                                        <h4>
                                                            <%=recomList.get(i).getCarName()%>
                                                        </h4>
                                                        <p>
                                                            <%=recomList.get(i).getExpertreview()%><p>
                                                            <a class="btn btn-info btn-sm" href="#">Detail</a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                           <%}}%>
                                            
                                        </div>
                                        <h3>
                                            Cars List:
                                        </h3>
                                        <% List<CarInfo> list = (List<CarInfo>) request.getAttribute("carList");
                                            if (list != null) {
                                                for (int i = 0; i < list.size(); i++) {

                                        %>
                                        <blockquote id="blockquote<%=i%>">
                                            <h4 id="carName"><%=list.get(i).getCarName()%></h4>
                                            <h5 id="year"><%=list.get(i).getYear()%></h5>
                                            <img alt="300x200" src="<%=list.get(i).getImageUrl()%>" />

                                            <button  class="btn btn-info"  >
                                                <a class="expertReview" id="view<%=i%>" data-toggle="modal" data-target="#detail<%=i%>" href="<%=i%>/<%=make%>/<%=model%>/<%=list.get(i).getYear()%>">View Detail</a>
                                            </button>
                                            <div class="modal fade" id="detail<%=i%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                            <h4 class="modal-title" id="myModalLabel"><%=list.get(i).getCarName()%></h4>
                                                        </div>
                                                        <div id="modal-body<%=i%>" class="modal-body">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default btn-group-sm" data-dismiss="modal">Close</button>
                                                            <button type="button" class="btn btn-block btn-group-sm" data-dismiss="modal">Add to Wish Lish</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </blockquote>
                                        <%}
                                }%>
                                         <ul class="pagination ">
                                             <%String pageNum = (String)request.getAttribute("pageNum"); 
                                            String year = (String)request.getAttribute("year");
                                            String price = (String)request.getAttribute("price");
                                            String currentPage = (String)request.getAttribute("currentPage");
                                            int nextPage =  Integer.parseInt(currentPage)+1;
                                            int prevPage =  Integer.parseInt(currentPage)-1;
                                            int pagCount = Integer.parseInt(pageNum);
                                             if(prevPage!=0){%>
                                            <li>
                                                <a href="CarlistServlet?method=pageCar&&year=<%=year%>&&price=<%=price%>&&make=<%=make%>&&model=<%=model%>&&page=<%=prevPage%>">Prev</a>
                                            </li>
                                            <%}
                                            for(int i=1;i<pagCount;i++){%>
                                            <li>
                                                <a href="CarlistServlet?method=pageCar&&year=<%=year%>&&price=<%=price%>&&make=<%=make%>&&model=<%=model%>&&page=<%=i%>"><%=i%></a>
                                            </li>
                                            <%}
                                            if(nextPage!=pagCount){%>
                                            <li>
                                                <a href="CarlistServlet?method=pageCar&&year=<%=year%>&&price=<%=price%>&&make=<%=make%>&&model=<%=model%>&&page=<%=nextPage%>">Next</a>
                                            </li><%}%>
                                        </ul>
                                    </div>
                                </div>
                            </div>


                            <script src="js/jquery.min.js"></script> 
                            <script src="js/bootstrap.min.js"></script>
                            <script type="text/javascript" src="js/json2.js"></script>
            <script type="text/javascript" src="js/make_model_switch.js"></script>
                        </body>
                        </html>

