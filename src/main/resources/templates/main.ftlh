<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


<#if user.role="ADMIN">
    <h1> Hello ${user.name}</h1>
   <form action="/delete" method="post">
       <input type="text" name="email" placeholder="введите почту пользователя">
       удалить пользователя <br>
       <button type="submit">Удалить </button>
   </form>

   <form action="/addAdmin" method="post">
       <input type="text" name="email" placeholder="выдать админку  пользователю">
       дать админку пользователю <br>
       <button type="submit">Отправить </button>
   </form>
    <#else>
        <h1> Hello ${user.name}</h1>
</#if>

</body>
</html>