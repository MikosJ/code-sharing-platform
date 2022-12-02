function send() {
    console.log(document.getElementById("time_restriction").value);
    console.log(document.getElementById("views_restriction").value);
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction").value
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    try {
        xhr.send(json);
    } catch (error) {
        alert("Provide time restriction and view restriction values, for no restrictions enter 0.")
    }

    if (xhr.status === 200) {
        alert("Success!");
    }
}