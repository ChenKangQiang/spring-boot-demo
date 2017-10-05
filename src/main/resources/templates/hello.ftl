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
Date: ${time?date} <br>
Time: ${time?time} <br>
Message: ${message} <br>
<div class="container">
    <a href="/login" class="btn btn-primary"><span class="fa fa-user"></span> SignIn</a>
    <a href="/logout" class="btn btn-danger">Logout <span class="fa fa-sign-out"></span> </a>
    <a href="/facebook" class="btn btn-primary">Facebook <span class="fa fa-facebook"></span> </a>
    <a href="/google" class="btn btn-danger"> Google <span class="fa fa-google-plus"></span> </a>
    <a href="/linkedin" class="btn btn-primary">LinkedIn <span class="fa fa-linkedin"></span> </a>
</div>

<div class="container">
    <table class="table">
        <caption>用户列表</caption>
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
            <td>17718907893</td>
        </tr>
        <tr>
            <td>lynn</td>
            <td>thl</td>
            <td>12857890238</td>
        </tr>
        </tbody>
    </table>
</div>

</body>

</html>