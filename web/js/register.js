function SendForm() {
    if (loginForm.User_id.value == "" || loginForm.User_password.value == "") {
        event.preventDefault();
        alert("请填写用户名或密码！");
        return false;
    }
    return true;
}

function SendRegisterForm() {
    if (registerForm.User_id.value == "" || registerForm.User_password.value == "" || registerForm.User_name.value == "" || registerForm.User_number.value == "" || registerForm.User_address.value == "") {
        event.preventDefault();
        alert("请将信息填写完整！");
        return false;
    }
    return true;
}

function tipRegister() {
    alert("用户已注册，请登录！");
}

function changecolor() {
    var obj = document.getElementById("navbar-btn1");
    obj.style.backgroundColor = "#00c1d1";
}

function changecolor2() {
    var obj = document.getElementById("navbar-btn2");
    obj.style.backgroundColor = "#00c1d1";
}

function changecolor3() {
    var obj = document.getElementById("navbar-btn3");
    obj.style.backgroundColor = "#00c1d1";
}

function changecolor4() {
    var obj = document.getElementById("navbar-btn4");
    obj.style.backgroundColor = "#00c1d1";
}