<html>
<head>
</head>
<body>
<#assign url='${url}' >
<h1>
    File upload!!!
    bjUpload
</h1>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit"/>
</form>
<#if url != "">
    <img src="${url}"/>
</#if>
</body>
</html>