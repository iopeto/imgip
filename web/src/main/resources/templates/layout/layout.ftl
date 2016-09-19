<#macro mainPage>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="icon" href="/favicon.ico">
        <link href="/static/css/bootstrap.min.css" rel="stylesheet">

        <title>${title!""}</title>

        ${head_bottom!""}
    </head>
    <body>
        <div class="container">
            <#nested >
        </div>
    </body>
</html>
</#macro>