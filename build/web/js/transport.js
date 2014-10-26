var browserType;
var jobj;


 

 

function createXHR() {
    // This function creates the correct form of the XMLHttpRequestObject based on the browser
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

function genericResponseHandler(response) {
    //this is a cheap way of processing the response -- preumably as text
//    document.getElementById("resultText").innerHTML+=response;
    // these next lines would deal with what was sent as a JSON object
    var JSONresp = JSON.parse(response)
    document.getElementById("modelBody").innerHTML="";
               for(each_name in JSONresp) {
                
                document.getElementById("modelBody").innerHTML += "<br />"+JSONresp[each_name]+"<br />";
              }
}

function buildSearch(method,value) {
    jobj={"call":method};
    var myform;
    var xhr = createXHR();
    xhr.onreadystatechange = processResponse;
  // debugger;
//   if ((myform=document.getElementById(value))!= null){
//      buildComplexJSON(myform);
//        }	
//     else{
//         if(value!=null){
//            jobj[value]=value;
//            }
//         }
//         
    jobj[value]=value;
//    document.getElementById("modelBody").innerHTML+=JSON.stringify(jobj);

    xhr.open("POST", method, true);
    xhr.send(JSON.stringify(jobj));
    
    // This generic internal function should probably have more error checking
    function processResponse() {
      // readyState of 4 signifies request is complete
      if (xhr.readyState == 4) {
	// status of 200 signifies sucessful HTTP call
        if (xhr.status == 200) {
          genericResponseHandler(xhr.responseText);
        }
      }
    }
}

