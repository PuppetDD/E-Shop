function addCar() {
    var box = document.getElementsByName("cartCheckBox");
    var count = 0;
    for (var i = 0; i < c_id.length; i++) {
        if (box[i].checked) {
            count = count + Number(document.getElementById(c_id[i]).value);
        }
    }
    document.getElementById("totalprice").setAttribute("value", count);
}