/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
		$('#progressbar').progressbar({
			warningMarker: 50,
			dangerMarker: 75,
			maximum: 100,
			step: 1
		});

		$('#progressbar').on("positionChanged", function (e) {
			viewModel.percent(e.percent);
		});

		ko.applyBindings(viewModel);
	});

	var viewModel = {
		percent: ko.observable(0),
		step: ko.observable(1),
		maximum: ko.observable(100),
		position: ko.observable(80),
		interval: undefined,
		isRunning: ko.observable(false),

		stepIt: function () {
			$('#progressbar').progressbar('stepIt');
		},

		reset: function () {
			$('#progressbar').progressbar('reset');
		},

		start: function () {
			if (this.isRunning())
				return;

			this.reset();
			this.isRunning(true);

			var self = this;
			this.interval = setInterval(function () {
				$('#progressbar').progressbar('stepIt');
				if (self.percent() >= 100) {
					clearInterval(self.interval);
					self.interval = undefined;
					self.isRunning(false);
				}
			}, 250);
		}
	};

	viewModel.formattedPercent = ko.computed(function () {
		return this.percent() + '%';
	}, viewModel);

	viewModel.maximum.subscribe(function (newValue) {
		$('#progressbar').progressbar('setMaximum', newValue);
	}, viewModel);

	viewModel.position.subscribe(function (newValue) {
		$('#progressbar').progressbar('setPosition', newValue);
	}, viewModel);

	viewModel.step.subscribe(function (newValue) {
		$('#progressbar').progressbar('setStep', newValue);
	}, viewModel);

function detail(event)
{

    var review = $(event).attr("href");
    var valeur = 0;
  
    expertReview("DetailServlet", review);


    function createXHR() {
        // This function creates the correct form of the XMLHttpRequestObject based on the browser
        // @author Prof.Spring
        if (window.XMLHttpRequest) { // Mozilla, Safari,...
            XHR = new XMLHttpRequest();
            if (XHR.overrideMimeType) {
                XHR.overrideMimeType('text/html');
            }
            browserType = "Mozilla"
            return XHR;
        } //end mozilla attempt
        if (window.ActiveXObject) { // IE
            try {
                XHR = new ActiveXObject("Msxml2.XMLHTTP");
                browserType = "IE";
                return XHR;
            }
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    browserType = "IE";
                }
                catch (e) {
                    alert("Your browser does not support AJAX!");
                    browserType = "Unknown"
                    return null;
                }
            }
        }//end IE attempt 
        return null;
    }

    function expertReview(method, value) {
        //validate information user typed
        if (value != null) {
            var xhr = createXHR();
            xhr.onreadystatechange = processResponse;
            var parameters = value.split("/");
            var id = parameters[0];
            //alert("id:"+parameters[1]);
            var recordId = "recordID=" + parameters[1];
            var name = "carname=" + parameters[2];
            var year = "year=" + parameters[3];
            method += "?" + recordId + "&&" + name + "&&" + year;
            xhr.open("POST", method, true);
            xhr.send();

            // This generic internal function should probably have more error checking
            function processResponse() {
                // readyState of 4 signifies request is complete
                if (xhr.readyState == 4) {
                    // status of 200 signifies sucessful HTTP call
                    if (xhr.status == 200) {
                        expertReviewResponseHandler(xhr.responseText, id);
                    }
                }
            }
        }
    }


    function expertReviewResponseHandler(response, id) {
        //alert("!");

        //}
        //alert(id);
        //document.getElementByClassName("modal-body").innerHTML="";
        document.getElementById("modal-body" + id).innerHTML = response;

    }
}