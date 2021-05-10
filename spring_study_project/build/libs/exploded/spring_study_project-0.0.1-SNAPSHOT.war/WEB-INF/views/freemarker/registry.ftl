<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/bj/login.css"/>
</head>
<body>
<#assign msg="${msg}"/>
<div class="sidenav">
    <div class="login-main-text">
        <h2>We are Calendar<br> RegistryPage</h2>
        <p>계정 생성</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form action="/registry" method="post">
                <div class="form-group">
                    <label>ID</label>
                    <input type="text" name="username" class="form-control" placeholder="ID">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-black">가입신청</button>
                <a href="/login"> <button type="button" class="btn btn-black">돌아가기</button></a>
            </form>
        </div>
    </div>
</div>
</body>

<#if msg!="">
    <script>
        alert("${msg}");
    </script>
</#if>

</html>