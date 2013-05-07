$(document).ready(function() {

	/* main header register/login button hover color change */
	$('div.headerBtns').hover(function() {
			$(this).stop().animate({backgroundColor: '#FFC04D'}, 200);
		}, 
		function() {
			$(this).stop().animate({backgroundColor: '#FF8C00'}, 75);
		}
	);
	
	/* main header register/login button functionality */
	$('div.headerBtns').click(function() {
		var text = $.trim($(this).text());
		if (text === "Logout") {
			window.location.href = "/cpsm/logout";
		} else {
			$('div#errMsg').text('');
			var btn = $(this).attr('id');
			if (btn === "register") {
				$('div#recoverForm').hide();
				$('div#loginFormContainer').hide();
				$('div#registrationForm').show();
			} else if (btn === "login") {
				$('div#recoverForm').hide();
				$('div#registrationForm').hide();
				$('div#loginFormContainer').show();
			} else if (btn == "recover") {
				$('div#loginFormContainer').hide();
				$('div#registrationForm').hide();
				$('div#errMsg').html('An email will be sent with your username and password to the registered email address.');
				$('div#recoverForm').show();
			}
		}
	});
        
        $('div#myAccount').click(function() {
            $('div.mainDisplay').hide();
            $('div.dashDisplay').hide();
            $('div.tab').removeClass('tabActive').removeClass('tabHover');
            $('div#dataTab').addClass('tabActive');
            $('div#userProfile').show();
            $('div#userDataDisplay').show();
            console.log("User Profile");
        });
        
        $('div#myPapers').click(function() {
            $('div.mainDisplay').hide();
            $('div.dashDisplay').hide();
            $('div.tab').removeClass('tabActive').removeClass('tabHover');
            $('div#paperDisplayTab').addClass('tabActive');
            $('div#userPapers').show();
            $('div#userPaperDisplay').show();
            console.log("User Papers");
        });
        
        $('div#conferences').click(function() {
            $('div.mainDisplay').hide();
            $('div.dashDisplay').hide();
            $('div.tab').removeClass('tabActive').removeClass('tabHover');
            $('div#conferenceTab').addClass('tabActive');
            $('div#userConferences').show();
            $('div#conferenceDisplay').show();
            console.log("User Conferences");
        });
        
        $('div#admin').click(function() {
            $('div.mainDisplay').hide();
            $('div.dashDisplay').hide();
            $('div.tab').removeClass('tabActive').removeClass('tabHover');
            //$(this).addClass('tabActive');
            $('div#admin').show();
            $('div#').show();
            console.log("Admin");
        });
	
});