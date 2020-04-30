const PUBLIC_KEY = "publicKey";

function decrypt() {
    const userSignIn = obtainUserSignIn();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/signIn",
        contentType: "application/json",
        data: JSON.stringify(userSignIn),
        statusCode: {
            200: function (data) {
                alert("Data Loaded: " + data.responseText);
            }
        }
    });
}

function obtainUserSignIn() {
    let loginInput = $("#loginInput").val();
    let passwordInput = $("#passwordInput").val();
    return {
        "login": loginInput,
        "password": encryptPassword(passwordInput)
    };
}

function encryptPassword(passwordInput) {
    let RsaEncrypt = new JSEncrypt();
    RsaEncrypt.setPublicKey(getPublicKey());
    return RsaEncrypt.encrypt(passwordInput)
}

function getPublicKey() {
    let publicKey = localStorage.getItem(PUBLIC_KEY);
    if (Object.is(publicKey, null)) {
        return loadPublicKey();
    } else {
        return publicKey;
    }
}

function loadPublicKey() {
    $.get("http://localhost:8080/key/public", function (data) {
        localStorage.setItem(PUBLIC_KEY, data);
        return data;
    });
}
