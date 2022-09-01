function showTariffs(id){
    if (document.getElementById(id.id).style.display == "none") {
        document.getElementById(id.id).style.display = "flex";
    }else{
        document.getElementById(id.id).style.display = "none";
    }

};

var add_modal_tariff = document.getElementById("add-modal-tariff");
var add_btn_tariff = document.getElementById("add-button-tariff");
var add_span_tariff = document.getElementById("close-add-tariff");

add_btn_tariff.onclick = function() {
    add_modal_tariff.style.display = "block";
}

add_span_tariff.onclick = function() {
    add_modal_tariff.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == add_modal_tariff) {
        add_modal_tariff.style.display = "none";
    }
}



function showEditModal(id) {
    document.getElementById(id.id).style.display = "block";
}

function closeEditModal(id){
    document.getElementById(id.id).style.display = "none";
}