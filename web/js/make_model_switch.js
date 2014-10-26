$(document).ready(function(){
    $("#selectMake").change(function(){
        var checkText=$("#selectMake").find("option:selected").text();
        modelSearch("CarSearch",checkText);
    });
    
    
//    $(".expertReview").on("click",function(){
//        var review=$(this).attr("href");
//        expertReview("DetailServlet",review);
//    });
     $("#selectMake2").change(function(){
        var checkText=$("#selectMake2").find("option:selected").text();
        modelSearch2("CarSearch",checkText);
    });
    
   
});

function createXHR() {
    // This function creates the correct form of the XMLHttpRequestObject based on the browser
    // @author Prof.Spring
    if (window.XMLHttpRequest) { // Mozilla, Safari,...
        XHR = new XMLHttpRequest();
        if (XHR.overrideMimeType) {
            XHR.overrideMimeType('text/html');
            }
        browserType="Mozilla"
        return XHR;
    } //end mozilla attempt
    if (window.ActiveXObject) { // IE
	try {
            XHR = new ActiveXObject("Msxml2.XMLHTTP");
            browserType="IE";
            return XHR;
	} 
        catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                browserType="IE";
            }
            catch (e) {
                alert("Your browser does not support AJAX!");
                browserType="Unknown"
                return null;
                }
            }
    }//end IE attempt 
return null;
}

function modelSearch(method,value) {
    //validate information user typed
    if(value!=null){  
        var xhr = createXHR();
        xhr.onreadystatechange = processResponse;
      
        xhr.open("POST", method, true);
        xhr.send(value);
    
        // This generic internal function should probably have more error checking
        function processResponse() {
        // readyState of 4 signifies request is complete
            if (xhr.readyState == 4) {
                 // status of 200 signifies sucessful HTTP call
                if (xhr.status == 200) {
                    modelSearchResponseHandler(xhr.responseText);
                }
            }
        }
    }
}

function expertReview(method,value) {
    //validate information user typed
    if(value!=null){  
        var xhr = createXHR();
        xhr.onreadystatechange = processResponse;
        var parameters=value.split("/");
		var id=parameters[0];
                //alert("id:"+parameters[1]);
                var recordId="recordID="+parameters[1];
		var name="carname="+parameters[2];
		var year="year="+parameters[3];
		method+="?"+recordId+"&&"+name+"&&"+year;
        xhr.open("POST", method, true);
        xhr.send();
    
        // This generic internal function should probably have more error checking
        function processResponse() {
        // readyState of 4 signifies request is complete
            if (xhr.readyState == 4) {
                 // status of 200 signifies sucessful HTTP call
                if (xhr.status == 200) {
                    expertReviewResponseHandler(xhr.responseText,id);
                }
            }
        }
    }
}









function expertReviewResponseHandler(response,id) {
    //alert("!");
  
    //}
    //alert(id);
    //document.getElementByClassName("modal-body").innerHTML="";
    document.getElementById("modal-body"+id).innerHTML=response;

}


function modelSearchResponseHandler(response) {
    
    var models=new Array();
    models=response.split(",");
    var selectmodel="";
    //Add preview in the model
    for(var i=0;i<models.length;i++){
        selectmodel+="<option>"+models[i]+"</option>";
    }
    document.getElementById("selectModel").innerHTML=selectmodel;

}

function modelSearch2(method,value) {
    //validate information user typed
    if(value!=null){  
        var xhr = createXHR();
        xhr.onreadystatechange = processResponse;
      
        xhr.open("POST", method, true);
        xhr.send(value);
    
        // This generic internal function should probably have more error checking
        function processResponse() {
        // readyState of 4 signifies request is complete
            if (xhr.readyState == 4) {
                 // status of 200 signifies sucessful HTTP call
                if (xhr.status == 200) {
                    modelSearchResponseHandler2(xhr.responseText);
                }
            }
        }
    }
}

function modelSearchResponseHandler2(response) {
    
   var models=new Array();
    models=response.split(",");
    var selectmodel="";
    //Add preview in the model
    for(var i=0;i<models.length;i++){
        selectmodel+="<option>"+models[i]+"</option>";
    }
    document.getElementById("selectModel2").innerHTML=selectmodel;


}
