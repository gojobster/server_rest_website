$(document).ready(function(){
	$("#login").click(function () {
		loginLinkedin();
	});
	$("#logout").click(function () {
		document.cookie = 'token=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		window.document.location.reload();
	});
});

function loginLinkedin() {
	const clientID = "77sx2eal8s9cco";
	// const urlRedirect = local_url +"login";
	// const urlRedirect = "http://localhost:8080/login";
	const state = "9876544561238qwerty";
	const scope= "r_liteprofile%20r_emailaddress";
	// const scope= "r_basicprofile";
	const url = "https://www.linkedin.com/oauth/v2/authorization?response_type=code"
		+ "&client_id=" + clientID
		+ "&redirect_uri=" + local_url + "login"
		+ "&state=" + state
		+ "&scope=" + scope;

	window.document.location.href = url;
}
