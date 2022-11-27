<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script type="text/javascript" th:src="@{/send.js}"></script>
    <link rel="stylesheet" href="newCode.css">
</head>
<body>
<div class="snippet_sending">
    <textarea id="code_snippet">Paste your code snippet here</textarea>
    <label for="time_restriction" class="time_restriction">Time restriction: </label><input id="time_restriction" type="text"/>
    <label for="views_restriction" class="view_restriction">View restriction: </label><input id="views_restriction" type="text"/>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</div>
</body>
<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": document.getElementById("time_restriction").value,
            "views": document.getElementById("views_restriction").value
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status === 200) {
            alert("Success!");
        }
    }
</script>
</html>
