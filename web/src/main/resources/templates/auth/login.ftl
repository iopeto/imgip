<#import "../layout/layout.ftl" as t >
<#assign title in t>Sign up</#assign>
<#assign head_bottom in t>
    <link href="/static/css/signin.css" rel="stylesheet">
</#assign>
<@t.mainPage>
<form class="form-signin" action="/login" method="POST">
    <h2 class="form-signin-heading">Please sign in</h2>
    <label for="inputEmail" class="sr-only">Username</label>
    <input name="username" id="username" class="form-control" placeholder="Username" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input name="password"type="password" id="password" class="form-control" placeholder="Password" required>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button class="btn btn-lg btn-primary btn-block">Sign in</button>
</form>
</@t.mainPage>

