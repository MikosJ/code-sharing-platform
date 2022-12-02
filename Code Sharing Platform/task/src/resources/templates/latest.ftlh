<html lang="en">
<head>
    <title>Latest</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/latest.css">
    <link rel="stylesheet"
          target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<h2>Latest public code snippets</h2>
<div class="code_list">
    <br>
    <#list latest10 as cl>
        <div class="code_block">
            <label for="code_snippet">Code snippet: </label><pre id="code_snippet" class="test"><code>${cl.code}</code></pre>
            <label for="load_date">Date of publication: </label><p id="load_date">${cl.date}</p>
        </div>
        <br>
    </#list>
</div>


</body>
</html>