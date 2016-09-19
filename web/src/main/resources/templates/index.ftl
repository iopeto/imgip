<#import "layout/layout.ftl" as t >
<#assign title in t>Main</#assign>
<#assign head_bottom in t>
    <link href="/static/css/main.css" rel="stylesheet">
</#assign>
<@t.mainPage>
<div class="panel panel-default">
    <div class="panel-heading">Images</div>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Image Name</th>
            <th>Image MimeType</th>
            <th>Image Size</th>
        </tr>
        </thead>
        <tbody>
        <#list images as image>
            <tr>
                <th scope="row">${image?counter}</th>
                <td><a href="/img/${image.name}" target="_blank">${image.name}</a></td>
                <td>${image.mimeType}</td>
                <td>${image.bytes?size}</td>
            </tr>
        <#else>
            <tr>
                <td colspan="3">No images...</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<div class="panel panel-default">
    <div class="panel-heading">Dumps</div>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Dump Date</th>
            <th>Dump Image Name</th>
            <th>Dump</th>
        </tr>
        </thead>
        <tbody>
            <#list dumps as dump>
            <tr>
                <th scope="row">${dump?counter}</th>
                <td>${dump.imgName}</td>
                <td>${dump.stringDate}</td>
                <td>${dump.dump}</td>
            </tr>
            <#else>
            <tr>
                <td colspan="3">No dumps...</td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>

<div class="panel panel-default">
    <div class="panel-heading">Add image</div>
    <div class="panel-body">
        <form class="form-signin" action="/upload" method="POST" enctype="multipart/form-data">
            <div class="input-group">
                <input type="file" name="file" class="form-control" required="true">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <span class="input-group-btn">
                    <button type="submit"  class="btn btn-default" type="button">Upload</button>
                </span>
            </div>
        </form>
    </div>
</div>
</@t.mainPage>
