<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Login</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width"/>
    <base href="/"/>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/webjars/font-awesome/css/font-awesome.min.css"/>
</head>
<body>
<div class="container">
    <table class="table">
        <caption>${sysUser}</caption>
        <thead>
          <tr>
              <th>First Name</th>
              <th>Last Name</th>
              <th>User Name</th>
          </tr>
        </thead>
        <tbody>
          <tr>
              <td>aehyok</td>
              <td>leo</td>
              <td>@aehyok</td>
          </tr>
          <tr>
              <td>lynn</td>
              <td>thl</td>
              <td>@lynn</td>
          </tr>
          <tr>
              <td>lynn</td>
              <td>thl</td>
              <td>@lynn</td>
          </tr>
          <#list userList as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.phone}</td>
            </tr>
          </#list>
        </tbody>
    </table>
</div>
</body>
</html>