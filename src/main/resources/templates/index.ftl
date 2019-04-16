<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#list users?age desc as user>
${name}
    $
</#list>
</body>
</html>