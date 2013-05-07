$(document).ready(function() {
	
    var school = $('p#schoolDisplay').val();
    var email = $('p#emailDisplay').val();

    $('div.tab').hover(function() {
        var tabState = $(this).hasClass('tabActive');
        if (!tabState) {
            $(this).toggleClass('tabHover');
        }
    });
	
    $('div.tab').click(function() {
        var reset = "";
        $('div.tab').removeClass('tabActive').removeClass('tabHover');
        $(this).addClass('tabActive');
        var id = $(this).attr('id');
        var displays = $('div.dashDisplay');
        // console.log(id, displays);
        if (id === "dataTab") {
            displays.hide();
            $('input#changePasswordBtn').hide();
            $('input#userEditBtn').hide();
            $('div#userDataDisplay').show();
            $('input#oldPassword').val(reset);
            $('input#newChangePassword').val(reset);
            $('input#confirmChangePassword').val(reset);
            $('div#errMsg').html(reset);
        } else if (id === "dataEditTab") {
            displays.hide();
            $('input#changePasswordBtn').hide();
            $('input#school').val(school);
            $('input#email').val(email);
            $('div#userDataEdit').show();
            $('input#userEditBtn').show();
            $('input#oldPassword').val(reset);
            $('input#newChangePassword').val(reset);
            $('input#confirmChangePassword').val(reset);
            $('div#errMsg').html(reset);
        } else if (id === "changePasswordTab") {
            displays.hide();
            $('input#userEditBtn').hide();
            $('div#userChangePassword').show();
            $('input#changePasswordBtn').show();
            $('div#errMsg').html(reset);
        } else if (id === "paperDisplayTab") {
            displays.hide();
            $('div#userPaperDisplay').show();
            $('div#errMsg').html(reset);
        } else if (id === "paperReviewTab") {
            displays.hide();
            $('div#userReviewDisplay').show();
            $('div#errMsg').html(reset);
        } else if (id === "paperHistoryTab") {
            displays.hide();
            $('div#submitPaperDisplay').show();
            $('div#errMsg').html(reset);
        } else if (id === "conferenceTab") {
            displays.hide();
            $('div#conferenceDisplay').show();
            $('div#errMsg').html(reset);
        } else if (id === "myConferenceTab") {
            displays.hide();
            $('div#myConferenceDisplay').show();
            $('div#errMsg').html(reset);
        } else if (id === "createConferenceTab") {
            displays.hide();
            $('div#createConferenceDisplay').show();
            $('div#errMsg').html(reset);
        }
    });
	
    $('div#updateInfoBtn').hover(function() {
        $(this).toggleClass('updateInfoBtnHover');
    });
	
    $('div#changePasswordBtn').hover(function() {
        $(this).toggleClass('changePasswordBtnHover');
    });
	
});