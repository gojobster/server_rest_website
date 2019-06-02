function endorse (id) {
    var token, idEndorser, name, email, relation, idOffer, description;
    var applyTo = $(id);
    token = applyTo.attr("data-token");
    idEndorser = applyTo.attr("data-iduser");
    name = applyTo.attr("data-name");
    email = applyTo.attr("data-email");
    // relation = applyTo.attr("data-relation");
    // idOffer = applyTo.attr("data-id-offer");
    // description = applyTo.attr("data-description");

    var url = ws_local_url + "recomend";

    // var urlPost = url,
    //     str = {"token":token,
    //         "id_candidate":idCandidate,
    //         "id_endorser": idEndorser,
    //         "id_offer": idOffer,
    //         "name": name,
    //         "endorserCandidateRelation": relation,
    //         "description": description,
    //         "email_candidate": email};
    //
    // var dataPost =$.param(str);
    // $.ajax({
    //     type: "POST",
    //     url: urlPost,
    //     data: dataPost,
    //     contentType: "application/x-www-form-urlencoded",
    //     dataType: "json",
    //     success: function (data) {
    //         if(data.message == "OK")
    //             location.reload();
    //         else
    //             alert("Error al recomendar: " + data.error);
    //     },
    //     failure: function (data) {
    //         alert("Error al recomendar");
    //     }
    // });
}