function isValidLength(element, length) {
	return element.length <= length ? true : false;
}

function isValidLetter(letter) {
	var pattern = /^[a-zA-Z\s]*$/;
	return pattern.test(letter);
}

function isValidPhoneNo(phoneNo) {
	var pattern1 = /^[0-9,.\-\+\s]*$/;
	var pattern2 = /^[၀-၉,.\-\+\s]*$/;
	if (pattern1.test(phoneNo) || pattern2.test(phoneNo)) {
		return true;
	}
	return false;
}

function isValidEmail(email) {
	var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return pattern.test(email.trim());
}
function isValidDateTime(dateStr) {
	var pattern = /^\d{1,2}\/\d{1,2}\/\d{4}\s\d{1,2}:\d{1,2}\s([AP]M)?$/;
	return pattern.test(dateStr);
}

function isValidImage(image) {
	var checkimg = image.toLowerCase();
	if (checkimg.match(/(\.jpg|\.png|\.gif|\.GIF|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
		return true;
	}
	return false;
}

function checkField(descValue, value, required, min, max, type) {
	if (required == true) {
		if (value == null || value == "" || typeof value == "undefined"
				|| value.length == "0" || !value.trim() || Number(value) <= 0) {
			return descValue + " is required value.";
		}
	}
	if (min != null || min > 0) {
		if (value.length < min) {
			return descValue + " must be greater than " + min + " characters.";
		}
	}
	if (max != null || max > 0) {
		if (value.length > max) {
			return descValue + " must not be greater than " + max
					+ " characters.";
		}
	}

	if (type != null) {
		if (value != null && value.length > 0) {
			if (type == 'l') {// for only letter
				if (!isValidLetter(value)) {
					return descValue + " allow only letter";
				}
			}
			if (type == 'p') {// for phone Number
				if (!isValidPhoneNo(value)) {
					return descValue
							+ " is not valid format, it allow only number, space, coma(,), (-),(+)";
				}
			}
			if (type == 'e') {// for email
				if (!isValidEmail(value)) {
					return descValue
							+ " is not valid format. Use this \'example@gmail.com\'.";
				}
			}

			if (type == 'n') { // for number float and integer
				if (!$.isNumeric(value)) {
					return descValue + " is not valid number.";
				}
			}
		}
		if (type == 's') {
			if (value < 0) {
				return "Please select " + descValue + ".";
			}
		}

		if (type == 'w') {
			if (!isValidPassword(value)) {
				return descValue + " is not valid password.";
			}
		}
	}
	return false;
}

function isValidPassword(password) {
	var pattern = /^[A-Za-z0-9#?!@$%^&*()+-]{8,}$/;
	return pattern.test(password);
}
function clearText(formId) {
	$(formId + " input[type='text']").val('');
	$(formId + " input[type='hidden']").val('-1');
	$(formId + " select").val('-1');
	$(formId + " .error-msg").text('');
}