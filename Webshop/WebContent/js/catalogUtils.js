if (!window.showModalDialog) {
 window.showModalDialog = function (arg1, arg2, arg3) {

	var w;
	var h;
	var resizable = "no";
	var scroll = "no";
	var status = "no";

	// get the modal specs
	var mdattrs = arg3.split(";");
	for (i = 0; i < mdattrs.length; i++) {
	   var mdattr = mdattrs[i].split(":");

	   var n = mdattr[0];
	   var v = mdattr[1];
	   if (n) { n = n.trim().toLowerCase(); }
	   if (v) { v = v.trim().toLowerCase(); }

	   if (n == "dialogheight") {
		  h = v.replace("px", "");
	   } else if (n == "dialogwidth") {
		  w = v.replace("px", "");
	   } else if (n == "resizable") {
		  resizable = v;
	   } else if (n == "scroll") {
		  scroll = v;
	   } else if (n == "status") {
		  status = v;
	   }
	}

	var left = window.screenX + (window.outerWidth / 2) - (w / 2);
	var top = window.screenY + (window.outerHeight / 2) - (h / 2);
	var targetWin = window.open(arg1, arg1, 'toolbar=no, location=no, directories=no, status=' + status + ', menubar=no, scrollbars=' + scroll + ', resizable=' + resizable + ', copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
	targetWin.focus();
 };
}

function openNewProductPopup() {
    var result = window.showModalDialog("createNewProductPopup.jsp", this.form, "dialogWidth:500px; dialogHeight:350px; center:yes");
}

function openNewAccountPopup() {
    var result = window.showModalDialog("createNewAccountPopup.jsp", this.form, "dialogWidth:500px; dialogHeight:350px; center:yes");
}

/*
function openDeleteCartPopup() {
    var result = window.showModalDialog("deleteCartPopup.jsp", this.form, "dialogWidth:500px; dialogHeight:350px; center:yes");
}*/

function setButtonPressed(value) {
	document.getElementById("buttonPressed").value=value;
}

function setSelectedProductId(value) {
	document.getElementById("selectedProductId").value=value;
}

function setSelectedOrderId(value) {
	document.getElementById("selectedOrderId").value=value;
}

function onSubmitForm()
{
  var form = document.getElementById("listForm");
	
  var buttonPressed = document.getElementById("buttonPressed").value;
  if(buttonPressed == 'DeleteProduct') {
	  form.action ="ProductHandlingServlet";
  } 
  
  if(buttonPressed == 'AddToCart') {
	  form.action ="CartHandlingServlet";
  } 
  
  if(buttonPressed == 'DeleteCart') {
	  form.action ="CartHandlingServlet";
  } 
  
  if(buttonPressed == 'DeleteProductFromCart') {
	  form.action ="CartHandlingServlet";
  }
  
  if(buttonPressed == 'DeleteOrder') {
	  form.action ="OrderHandlingServlet";
	 
	}

  return true;
}
