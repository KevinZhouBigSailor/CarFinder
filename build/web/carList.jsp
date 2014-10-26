<%-- 
    Document   : carList
    Created on : Apr 18, 2014, 1:58:08 AM
    Author     : xmrui_000
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.carsFinder.entity.ModelInfo"%>
<%@page import="com.carsFinder.entity.CarInfo"%>

<!DOCTYPE html>

<%@ page import="vo.StatusVO,java.util.*,com.carsFinder.entity.*,com.carsFinder.model.*" %>
<%@ page import="java.util.*,com.carsFinder.dao.*" %>
<jsp:useBean id="ab" scope="page" class="com.carsFinder.model.CarsBean"/>
<html xmlns="http://www.w3.org/1999/xhtml">
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
                                       String makeList="";
                                        for (int i = 0; i < makes.length; i++) {
                                                           makeList+="make="+makes[i]+"&&";
                                                                }
                                    String modelsSearch="";
                                        for (int i = 0; i < models.length; i++) {
                                                           modelsSearch+="model="+models[i]+"&&";
                                                                }
                                    String yearList="";
                                        for (int i = 0; i < years.length; i++) {
                                                           yearList+="year="+years[i]+"&&";
                                                                }%>
                   
                           
                                                     <% List<CarInfo> list = (List<CarInfo>) request.getAttribute("carList");
                                                                if (list != null) {
                                                                    int rowNum=0;
                                                                    if(list.size()%3==0){
                                                                    rowNum=list.size()/3;}else{
                                                                    rowNum=list.size()/3+1;}
                                                                    for (int j = 0; j < rowNum; j++) {%>
                                                        <div class="row">
                                                           <% for(int i=j*3;i<(j+1)*3;i++){%>
                                                            <div class="col-md-4" height-limited id="blockquote<%=i%>">
                                                                <div class="thumbnail">
                                                                    <h4 id="carName"><%=list.get(i).getCarName()%></h4>
                                                                    <img alt="300x200" src="<%=list.get(i).getImageUrl()%>" />
                                                                    <div class="caption">

                                                                        <h5> <%=list.get(i).getMileage()%> </h5><h5> <%=list.get(i).getDoorCount()%> </h5>
                                                                        <h5> <%=list.get(i).getColor()%></h5><h5> <%=list.get(i).getDriveTrain()%>  </h5> 
                                                                        <h5> <%=list.get(i).getEngineDescription()%></h5><h5> <%=list.get(i).getTransmission()%>  </h5> 

                                                                        <a class="btn btn-info" onclick="detail(this)" id="view<%=i%>" data-toggle="modal" data-target="#detail<%=i%>" href="<%=i%>/<%=list.get(i).getRecordID()%>/<%=list.get(i).getCarName()%>/<%=list.get(i).getYear()%>">View Detail</a>
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
                                                        </div>  <%}}%>
                                                        <%-- pagination --%>

                                                        <ul class="pager"   >
                                                            <%String pageNum = (String) request.getAttribute("pageNum");
                                                                
                                                                
                                                                String price = (String) request.getAttribute("price");
                                                                if(price==null){price="Any Price";}
                                                                String currentPage = (String) request.getAttribute("currentPage");
                                                                int nextPage = Integer.parseInt(currentPage) + 1;
                                                                int prevPage = Integer.parseInt(currentPage) - 1;
                                                                int pagCount = Integer.parseInt(pageNum);
                                                                if (prevPage != 0) {%>
                                                            <li class="previous pageChange" onclick="pageChange(this)" value="CarlistServlet?method=searchCar&&price=<%=price%>&&<%=yearList%><%=makeList%><%=modelsSearch%>page=<%=prevPage%>">
                                                                <a >Prev</a>
                                                            </li>
                                                            <%}else if(prevPage == 0) {%>
                                                            <li class="previous pageChange disabled">
                                                                <a >Prev</a>
                                                            </li>
                                                            <%}%>
                                                        <% if (nextPage < pagCount) {%>
                                                                    <li class="next pageChange" onclick="pageChange(this)" value="CarlistServlet?method=searchCar&&price=<%=price%>&&<%=yearList%><%=makeList%><%=modelsSearch%>&&page=<%=nextPage%>">
                                                                        <a>Next</a>
                                                                    </li><%}else if(nextPage == pagCount) {%>
                                                            <li class="next pageChange disabled" >
                                                                <a >Next</a>
                                                            </li>
                                                            <%}%>
                                                                    </ul>
                            

</html>