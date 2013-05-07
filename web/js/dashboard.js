$(document).ready(function() {
    
    /* ******* */
    /* PROFILE */
    /* ******* */
    
    $('input#userEditBtn').click(function(e) {
        $('div#errMsg').html("");
        var school = $('input#school').val();
        var email = $('input#email').val();
        var error = false;
        if (school === "" && email === "") {
            error = true;
            $('div#errMsg').append('You must enter a new value for either the School or Email field.');
        }
        if (error) {
            e.preventDefault();
        }
    });
    
    $('input#changePasswordBtn').click(function(e) {
        $('div#errMsg').html("");
        var oldPassword = $('input#oldPassword').val();
        var newPassword = $('input#newChangePassword').val();
        var confirmPassword = $('input#confirmChangePassword').val();
        var error = false;
        if (oldPassword === "") {
            $('div#errMsg').append("The Password field cannot be blank.<br />");
            error = true;
        }
        if (newPassword === "") {
            $('div#errMsg').append("The New Password field cannot be blank.<br />");
            error = true;
        }
        if (confirmPassword === "") {
            $('div#errMsg').append("The Confirm Password field cannot be blank.<br />");
            error = true;
        }
        if (newPassword != confirmPassword && newPassword != "" && confirmPassword != "") {
            $('div#errMsg').append("The New Password and Confirm Password fields do not match.<br />");
            error = true;
        }
        if (error) {
            e.preventDefault();
        }
    });
    
    /* ********* */
    /* MY PAPERS */
    /* ********* */
    
});


