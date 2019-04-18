function getTopJobOffers(keyword,city){
	var urlPost = "http://18.191.247.235:8080/ws/getAllOffers",
	str = {"filter_keyword":keyword,"filter_city":city};
	var dataPost =$.param(str);
	$.ajax({
			type: "POST",
			url: urlPost,
			data: dataPost,
			contentType: "application/x-www-form-urlencoded",
			dataType: "json",
			success: function (data) {
				listJobs(data.message);
			},
			failure: function (data) {
				$(".job-listings-sec").html("<h3>There are no job offers available.</h3>")
			}
	});
}

function listJobs(data){
	$.each(data,function(i,d){
		var html = "<a href='" + d.path_image_company + "' class='job-listing rounded'>";
			html += "<div class='job-title-sec'><div class='c-logo'><img src='"+d.path_image_company+"' alt='' /> </div><h3>"+d.nameCompany+"</h3><span>"+d.position+"</span></div>";
			html += "<span class='job-lctn'><i class='la la-map-marker'></i>"+d.city+"</span>";
			html += "<span class='job-dates'><i class='la la-calendar-o'></i>"+d.date_init+" - "+d.date_end+"</span>";
			html += "<span class='job-is'>"+d.reward+" €</span>";
			html += "</a>";
		$(".job-listings-sec").append(html);
	});
}

$(document).ready(function(){
	var key = GetURLParameter("s");
	getTopJobOffers(key,"");
});
