<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
  <meta charset="UTF-8">
  <title>Code</title>
  <link rel="stylesheet"
        href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
  <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
    <link rel="stylesheet" href="/latest.css">
</head>
<body>
<h2>Single code for given UUID</h2>

<div class="code_block" id="block_background">
    <label for="code_snippet">Code snippet: </label><pre id="code_snippet"><code>${codeBody}</code></pre>
    <label for="load_date">Date of publication: </label><p id="load_date">${date}</p>
    <#if time_restriction == true>
        <#if (time>=0)>
            <span id="time_restriction">Time restriction: ${time}</span>
        </#if>
    </#if>
    <br>
    <#if views_restriction == true>
        <#if (views>=0)>
            <span id="views_restriction">View restriction: ${views}</span>
        </#if>
    </#if>
</div>
</body>
</html>