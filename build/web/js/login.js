$(document).ready(function() {
	
	/* login validation */
	$('input#login').click(function(e) {
		e.stopPropagation();
		var loginUsername = $('input#loginUsername').val();
		loginUsername = $.trim(loginUsername);
		var loginPassword = $('input#loginPassword').val();
		loginPassword = $.trim(loginPassword);
		var errMsg = "";
		if (loginUsername === "") {
			errMsg += "Username field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#loginUsername').val(loginUsername);
		}
		if (loginPassword === "") {
			errMsg += "Password field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#loginPassword').val(loginPassword);
		}
		if (errMsg != "") {
			$('div#errMsg').html(errMsg);
		}
	});
	
	/* registration validation */
	$('input#register').click(function(e) {
		e.stopPropagation();
		var fname = $('input#fname').val();
		fname = $.trim(fname);
		var lname = $('input#lname').val();
		lname = $.trim(lname);
		var regUsername = $('input#regUsername').val();
		regUsername = $.trim(regUsername);
		var regPassword = $('input#regPassword').val();
		regPassword = $.trim(regPassword);
		var confPassword = $('input#confPassword').val();
		confPassword = $.trim(confPassword);
		var email = $('input#email').val();
		email = $.trim(email);
		var school = $('input#school').val();
		school = $.trim(school);
		var errMsg = "";
		if (fname === "") {
			errMsg += "First Name field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#fname').val(fname);
		}
		if (lname === "") {
			errMsg += "Last Name field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#lname').val(lname);
		}
		if (regUsername === "") {
			errMsg += "Username field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#regUsername').val(regUsername);
		}
		if (regPassword === "") {
			errMsg += "Password field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#regPassword').val(regPassword);
		}
		if (confPassword === "") {
			errMsg += "Confirm Password field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#confPassword').val(confPassword);
		}
		if (regPassword != "" && confPassword != "" && regPassword != confPassword) {
			errMsg += "The Confirm Password field must match the Password field.<br />";
			e.preventDefault();
		}
		if (email === "") {
			errMsg += "Email Address field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#email').val(email);
		}
		if (school === "") {
			errMsg += "College/University field cannot be blank.<br />";
			e.preventDefault();
		} else {
			$('input#school').val(school);
		}
		if (errMsg != "") {
			$('div#errMsg').html(errMsg);
		}
	});
});