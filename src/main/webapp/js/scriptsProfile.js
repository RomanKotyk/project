var replenish_modal_user = document.getElementById("replenish-modal-user");
var replenish_btn_user = document.getElementById("replenish-button-user");
var replenish_span_user = document.getElementById("close-replenish-user");

replenish_btn_user.onclick = function() {
    replenish_modal_user.style.display = "block";
}

replenish_span_user.onclick = function() {
    replenish_modal_user.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == replenish_modal_user) {
        replenish_modal_user.style.display = "none";
    }
}