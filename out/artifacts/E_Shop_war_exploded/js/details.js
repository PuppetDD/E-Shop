function sub() {
    var number = Number(document.getElementById("text_box").value);
    if (number > 1) {
        document.getElementById("text_box").setAttribute("value", number - 1);
    }

}

function addN() {
    var number = Number(document.getElementById("text_box").value);
    document.getElementById("text_box").setAttribute("value", number + 1);
}

function checkStatus() {
    var name = document.getElementById("name").value
    if (name == "null") {
        event.preventDefault();
        alert("请先登录");
    }
}

function carinsert() {
    alert("成功加入购物车");
}

function choice() {
    document.getElementById("flag").setAttribute("value", "2");
}
        