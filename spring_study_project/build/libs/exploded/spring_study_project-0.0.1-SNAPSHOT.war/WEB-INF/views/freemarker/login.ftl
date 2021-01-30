<!doctype html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
    </head>
    <body>
        <#assign msg="${msg}"/>
        <form action="/login" method="post">
            <table>
                <tr>
                    <td>
                        <label for="username">아이디</label>
                    </td>
                    <td>
                        <input type="text" name="username" hint="id" id="username">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">패스워드</label>
                    </td>
                    <td>
                        <input type="password" name="password" hint="password" id="password">
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="로그인"></td>
                </tr>
            </table>

        </form>
    </body>

    <#if msg!="">
        <script>
            alert("${msg}");
        </script>
    </#if>


</html>