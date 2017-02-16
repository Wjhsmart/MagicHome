
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
		
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
        
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});

var checkCode = "";
function changeCode(obj) {
	var lenOverall = 4;
	var check_code_str = "3456789abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY";
	var len = check_code_str.length;
	checkCode = "";
	for (var i = 0; i < lenOverall; i++) {
		var index = Math.floor(Math.random() * len);
		checkCode += check_code_str.charAt(index);
	}
	obj.value = checkCode;
}

function login() {
	var code = document.getElementById("captcha");
	var codeHint = document.getElementById("codeHint");
	var codeVal = code.value;
	if (codeVal != '' && codeVal.toLowerCase() == checkCode.toLowerCase()) {
		return true;
	}
	codeHint.innerHTML = "验证码有误";
	return false;
}