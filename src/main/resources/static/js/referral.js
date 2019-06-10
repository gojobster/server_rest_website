function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function endorse (id) {
    var token, idEndorser, name, email, relation, idOffer, description;
    var applyTo = $(id);
    token = applyTo.attr("data-token");
    idEndorser = applyTo.attr("data-iduser");
    name = $("#candidateName").val();
    email = $("#candidateEmail").val();
    relation = $("#candidateRelation").val();
    idOffer = applyTo.attr("data-idOffer");
    description = $("#candidateDescription").val();

    var url = ws_local_url + "recomend";

    if (name == "" || email == "" || description == "" || !isValidEmail(email))
        return false;

    var urlPost = url,
        str = {"token":token,
            "id_endorser": idEndorser,
            "id_offer": idOffer,
            "name": name,
            "endorserCandidateRelation": relation,
            "description": description,
            "email_candidate": email};

    var dataPost =$.param(str);
    $(".page-loading").show();
    $.ajax({
        type: "POST",
        url: urlPost,
        data: dataPost,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            $(".page-loading").hide();
            if(data.message == "OK"){
                $(".account-popup-area").show();
                var str = "<h3>La recomendación ha sido enviada</h3><a href='/'><button>OK</button></a>";
                $(".account-popup-area .account-popup").append(str);
            }
                //location.reload();
            else
                alert("Error al recomendar: " + data.error);
        },
        failure: function (data) {
            $(".page-loading").hide();
            $(".account-popup-area").show();
            var str = "<h3>No se ha podido enviar la recomendación</h3><a href='/'><button>OK</button></a>";
            $(".account-popup-area .account-popup").append(str);
        }
    });

}