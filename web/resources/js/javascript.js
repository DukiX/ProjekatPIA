function passTest() {
    var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$/;
    var passBox = document.getElementById("password");
    var pass = passBox.value;
    window.alert("usao");
    if (reg.test(pass)) {

        passBox.style.backgroundColor = "green";
    } else {
        passBox.style.backgroundColor = "red";
    }
    return;
}

function nazad() {
    window.history.back();
}

