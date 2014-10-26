 $ (Document). Ready (function () {
    var make = $ ("# selectMake");   
     var model = $ ("# selectModel");  
    
    
      make.change (function () {   
       model.empty ();   
       var params = {make: $ ('# selectMake option: selected').val ()};   
       $. Post ("carSearch_findModelByMake.action", params, function (data) {   
       alert (data);  
       
       var dataObj = eval ("(" + data + ")"); // convert json object 
       $.each (dataObj, function (index, item) {   
         var tempOption = document.createElement ("option");   
         tempOption.value = item.modelName;   
         tempOption.innerHTML = item.modelName;   
         model.append (tempOption);   
         });   
       }, "Json");   
     });
})
