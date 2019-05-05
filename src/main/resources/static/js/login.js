$(document).ready(function(){
	$("#login").click(function () {
		const clientID = "77sx2eal8s9cco";
		const urlRedirect = "http://localhost:8080/login";
		const state = "9876544561238qwerty";
		const scope= "r_liteprofile%20r_emailaddress";
		const url = "https://www.linkedin.com/oauth/v2/authorization?response_type=code"
			+ "&client_id=" + clientID
			+ "&redirect_uri=" + urlRedirect
			+ "&state=" + state
			+ "&scope=" + scope;

		window.document.location.href = url;
	});
	$("#logout").click(function () {
		document.cookie = 'token=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		window.document.location.reload();
	});
});
