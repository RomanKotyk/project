var add_modal_user = document.getElementById("add-modal-user");
var add_btn_user = document.getElementById("add-button-user");
var add_span_user = document.getElementById("close-add-user");

add_btn_user.onclick = function() {
    add_modal_user.style.display = "block";
}

add_span_user.onclick = function() {
    add_modal_user.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == add_modal_user) {
        add_modal_user.style.display = "none";
    }
}
