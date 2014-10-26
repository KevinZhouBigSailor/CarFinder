<%-- 
    Document   : recommendation
    Created on : Apr 22, 2014, 7:42:40 PM
    Author     : xmrui_000
--%>
<%@page import="vo.StatusVO"%>
<%@page import="vo.StatusVO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page import="com.carsFinder.entity.CarInfo"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">


    <div class="tabbable" id="tabs-main1">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#panel-collaborative" data-toggle="tab">Collaborative</a>
            </li>
            <li>
                <a href="#panel-contentBased" data-toggle="tab">Content-Based</a>
            </li>
            <li>
                <a href="#panel-itemBased" data-toggle="tab">item-Based</a>
            </li>
            <li>
                <a href="#panel-expertReview" data-toggle="tab">Expert Review</a>
            </li>
            <li>
                <a href="#panel-Twitter" data-toggle="tab">Twitter Review</a>
            </li>
        </ul>

        <%-- tab content  --%>
        <div class="tab-content">
            <div class="tab-pane active" id="panel-collaborative">
                <p>&nbsp;</p>
                <%List<CarInfo> recomList = (List<CarInfo>) request.getAttribute("recomdcar");
                    if (recomList != null) {
                        int rowNum = 0;
                        if (recomList.size() % 3 == 0) {
                            rowNum = recomList.size() / 3;
                        } else {
                            rowNum = recomList.size() / 3 + 1;
                        }
                        for (int j = 0; j < rowNum; j++) {%>
                <div class="row">
                    <% for (int i = j * 3; i < (j + 1) * 3 && i < recomList.size(); i++) {%>
                    <div class="col-md-4">

                        <div class="thumbnail">
                            <img alt="300x200" src="<%=recomList.get(i).getImageUrl()%>" />
                            <div class="caption">
                                <h4>
                                    <%=recomList.get(i).getCarName()%>
                                </h4>
                                <p>
                                    <%=recomList.get(i).getExpertreview()%>
                                </p>
                            </div>
                        </div>
                    </div><%}%>
                </div>  
                <%}
                    }%>  

            </div>



            <div class="tab-pane" id="panel-contentBased">
                <p>&nbsp;</p>
                <%List<CarInfo> contntList = (List<CarInfo>) request.getAttribute("contntCar");
                    if (contntList != null) {
                        int rowNum = 0;
                        if (contntList.size() % 3 == 0) {
                              rowNum = contntList.size() / 3;
                          } else {
                              rowNum = contntList.size() / 3 + 1;
                          }
                          for (int j = 0; j < rowNum; j++) { %>
                <div class="row">
                    <% for (int i = j * 3; i < (j + 1) * 3 && i < contntList.size(); i++) {%>
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img alt="300x200" src="<%=contntList.get(i).getImageUrl()%>" />
                            <div class="caption">
                                <h4>
                                    <%=contntList.get(i).getCarName()%>
                                </h4>
                                <p>
                                    <%=contntList.get(i).getExpertreview()%>

                                </p>
                            </div>
                        </div>
                    </div><%}%>
                </div>     <%}
                        }%>                   
                <%List<CarInfo> contntCookieList = (List<CarInfo>) request.getAttribute("contntCarCookie");
                    if (contntCookieList != null) {
                        int rowNum = 0;
                        if (contntCookieList.size() % 3 == 0) {
                            rowNum = contntCookieList.size() / 3;
                                } else {
                                    rowNum = contntCookieList.size() / 3 + 1;
                                }
                                for (int j = 0; j < rowNum; j++) { %>
                <div class="row">
                    <% for (int i = j * 3; i < (j + 1) * 3 && i < contntCookieList.size(); i++) {%>
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img alt="300x200" src="<%=contntCookieList.get(i).getImageUrl()%>" />
                            <div class="caption">
                                <h4>
                                    <%=contntCookieList.get(i).getCarName()%>
                                </h4>
                                <p>
                                    <%=contntCookieList.get(i).getExpertreview()%>

                                </p>
                            </div>
                        </div>
                    </div><%}%>
                </div>     <%}
                        }%>                          
            </div>

            <div class="tab-pane" id="panel-itemBased">
                <p>&nbsp;</p>
                <div class="row">
                    <%List<CarInfo> itemCarList = (List<CarInfo>) request.getAttribute("itemCar");
                        if (itemCarList != null) {
                            for (int i = 0; i < itemCarList.size() && i < 6; i++) {%>
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <img alt="300x200" src="<%=itemCarList.get(i).getImageUrl()%>" />
                            <div class="caption">
                                <h4>
                                    <%=itemCarList.get(i).getCarName()%>
                                </h4>
                                <p>
                                    <%=itemCarList.get(i).getExpertreview()%><p>
                                        <% String makemodel = itemCarList.get(i).getCarName();
                                            String[] parts = makemodel.split(" ");
                                            String makeThis = parts[0];
                                            String modelThis = parts[1];
                                            int id = -i - 1;%>                          
                                    </p>
                            </div>
                        </div>
                    </div>
                    <%}
                        }%>
                </div>  


            </div>
            
            <div class="tab-pane" id="panel-expertReview">
                <p>&nbsp;</p>
                <%String expertReview = (String) request.getAttribute("expertReview");%>
                <div class="row">
                    <p><%=expertReview%></p>
                </div>
            </div>

              <div class="tab-pane" id="panel-Twitter">
                <div class="tabbable" id="tabs-975169">
                    <ul class="nav nav-pills">
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
                                    <%List<StatusVO> good_twitte = (List) request.getAttribute("goodtwitte");
                                                                if (good_twitte != null) {
                                                                    for (int i = 0; i < good_twitte.size() && i < 4; i++) {%>
                                    <div class="ui blue message" style="font-size:22px"><%=good_twitte.get(i).getText()%></div><%}
                                                                }%>
                                </s:iterator>
                            </div>

                        </div>
                        <div class="tab-pane" id="panel-109216">

                            <div class="ui raised segment" style="margin-left:40px;width:700px">
                                <s:iterator value="top5BadStatus">
                                    <%List<StatusVO> bad_twitte = (List) request.getAttribute("badtwitte");
                                                                if (bad_twitte != null) {
                                                                    for (int i = 0; i < bad_twitte.size() && i < 4; i++) {%>    
                                    <div class="ui red message" style="font-size:22px"><%=bad_twitte.get(i).getText()%></div><%}
                                                                }%>
                                </s:iterator>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   


        </div>
    </div>


</html>
