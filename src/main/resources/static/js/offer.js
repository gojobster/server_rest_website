function user_registration_temp(id_endorser, id_offer, email_candidate) {
    var url = ws_local_url + "recomend";

    var urlPost = url,
        str = {"id_endorser":id_endorser,
            "id_offer":id_offer,
            "email_candidate":email_candidate};

    var dataPost =$.param(str);
    $.ajax({
        type: "POST",
        url: urlPost,
        data: dataPost,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            if(data.message == "OK")
                location.reload();
            else
                alert("Error al crear usuario: " + data.error);
        },
        failure: function (data) {
            alert("Error al crear el usuario");
        }
    });
}

function apply (id) {
    var idcandidate, code, token;
    var applyTo = $(id);
    idcandidate = applyTo.attr("data-iduser");
    id_offer = applyTo.attr("data-offer");
    code = applyTo.attr("data-code");
    token = applyTo.attr("data-token");

    var url = ws_local_url + "apply";

    var urlPost = url,
        str = {"token":token,
            "id_candidate":idcandidate,
            "id_offer":id_offer,
            "code":code};

    var dataPost =$.param(str);
    $.ajax({
        type: "POST",
        url: urlPost,
        data: dataPost,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            if(data.message == "OK")
                location.reload();
            else
                alert("Error al aplicar: " + data.error);
        },
        failure: function (data) {
            alert("Error al aplicar");
        }
    });
}