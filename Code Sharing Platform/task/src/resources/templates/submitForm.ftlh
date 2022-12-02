<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script type="text/javascript" src="/send.js"></script>
    <link type="text/css" href="/newCode.css" rel="stylesheet">
</head>
<body>
<div id="code_input">
    <textarea id="code_snippet">Paste your code snippet here</textarea>
</div>

<div class="snippet_sending">
    <br>
    <label for="time_restriction" class="time_restriction">Time restriction: </label><input id="time_restriction" type="text"/>
    <label for="views_restriction" class="view_restriction">View restriction: </label><input id="views_restriction" type="text"/>
</div>
<div id="button_center">
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</div>
</body>

</html>
