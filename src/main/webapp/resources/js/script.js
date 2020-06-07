/* ----- button click event in confirm popup  ----- */
var tempId = [];
function popConfirmCallBack(data, okCallBack, text) {

	var flag = true;
	
	tempId.push(data);

	$("#exampleModal").modal({
		backdrop : "static"
	});
	$("#save").click(function() {
		if (typeof okCallBack == "function") {

			if (tempId[tempId.length - 1] == data && flag) {
				okCallBack(data);
				flag = false;
			}
		}

		return true;
	});
	$("#infoModal").modal({
		backdrop : "static"
	});
}
/*------ dynamic create alert message ------ */
function bootstrap_alert(elem, alertType, message, timeout) {
	$(elem)
			.show()
			.html(
					'<div class="alert '
							+ alertType
							+ ' alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
							+ message + '</div>');
	if (timeout || timeout === 0) {
		setTimeout(function() {
			$(".alert").remove();
		}, timeout);
	}
};
/*------ disappear alert message ------ */
window.setTimeout(function() {
	$(".alert").fadeTo(500, 0).slideUp(500, function() {
		$(this).remove();
	});
}, 9000);/* alert box fade out timer */

/*-- show error message ---*/
function showError(mainForm,subId, errMsg) {

	$('#' + mainForm + ' #errMsg').text(errMsg);
	$('#' + mainForm + ' #errMsg').css('color', 'red');
	$('#' + mainForm + ' #'+ subId).css('border-color', 'red');

}

function removeErrorMsg(mainForm,subId) {


	$('#' + mainForm + ' #'+ subId).css('border-color', '');
	$('#' + mainForm + ' #errMsg').text("");
}
