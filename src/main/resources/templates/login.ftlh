<#--<#import "/spring.ftl" as spring>-->
<#--<#import "main.ftlh" as main>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">

    <div>
        <#if error!false >
            <p style="color:red">Неверные имя пользователя или пароль !</p>
        </#if>
    </div>

    <div>
        <label class="flex flex-v-center ">
            <span class="label-input-text">Адрес почты:</span>
            <input type="text" name="username" placeholder="ваш адрес почты" value="${(dto.email)!''}" autofocus>
        </label>
    </div>
    <div>
        <label class="flex flex-v-center label-input-text">
            <span class="label-input-text">Пароль:</span>
            <input type="text" name="password" placeholder="пароль" value="">
        </label>
    </div>
    <div>
        <button type="submit">войти</button>
    </div>
</form>
</body>
</html>