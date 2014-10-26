/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function pageChange(event)
{
 //$(".pageChange").live('click', function(){
        var url=$(event).attr("value");
        //alert(url);
        pageChange(url);
   // });


	
                
                
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

function pageChange(url) {
    //validate information user typed
    if(url!=null){  
        var xhr = createXHR();
        xhr.onreadystatechange = processResponse;
      
        xhr.open("POST", url, true);
        xhr.send();
    
        // This generic internal function should probably have more error checking
        function processResponse() {
        // readyState of 4 signifies request is complete
            if (xhr.readyState == 4) {
                 // status of 200 signifies sucessful HTTP call
                if (xhr.status == 200) {
                    pageChangeResponseHandler(xhr.responseText);
                }
            }
        }
    }
}


function pageChangeResponseHandler(response) {
    //alert(response);
   // alert(document.getElementById("carList").innerHTML);
    document.getElementById("carList").innerHTML=response;			

}
}