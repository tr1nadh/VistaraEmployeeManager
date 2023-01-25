function submitForm() {
    let name = document.getElementById("name-field").value;
    let password = document.getElementById("password-field").value;
    let email = document.getElementById("email-field").value;

    if (isStringEBN(name) || isStringEBN(password) || isStringEBN(email)) {
        window.alert('Fields cannot be empty or blank');
    } else {
        document.getElementById("form").submit();
    }
}

function isStringEBN(str) {
    return (!str || str.trim().length === 0 || str == "null")
}