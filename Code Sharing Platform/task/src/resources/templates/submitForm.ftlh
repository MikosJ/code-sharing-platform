<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script type="text/javascript" th:src="@{/send.js}"></script>
</head>
<body>
<textarea id="code_snippet">Paste your code snippet here</textarea>
<button id="send_snippet" type="submit" onclick="send()">Submit</button>
</body>
</html>
