<%-- 
    Document   : header
    Created on : 05/06/2017, 20:45:41
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nice</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.bootstrap.min.css">
        <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/dataTables.responsive.min.js"></script>
        <script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript" src="js/responsive.bootstrap.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
        <script src="js/jquery.mask.js"></script>
    </head>
    <script type="text/javascript">
        $(document).ready(function () {
            carregaMaster();
        });

        function carregaMaster() {
            
            $('.modal').modal();
            
            $(".dtmask").mask("99/99/9999");
            $(".telmask").mask("(99) 9999-9999");
            $(".cpfmask").mask("999.999.999-99");
            $(".rgmask").mask("99.999.999-9");
            $(".intmask").mask("9999999999");
            $(".rgcnhmask").mask("99999999999999999999");
            $(".cepmask").mask("99.999-999");
            $(".cnpjmask").mask("99.999.999/9999-99");
            
        }

    </script>
    <body>
        <nav class="teal" role="navigation">
            <div class="nav-wrapper container">
                <a id="logo-container" href="#" class="brand-logo">Logo</a>
                <ul class="right hide-on-med-and-down">
                    <li><a href="vagas.jsp">Vagas</a></li>
                    <li><a href="#modal-login" onclick="$('#formLogin')[0].reset();">Login</a></li>
                </ul>
                <ul id="nav-mobile" class="side-nav">
                    <li><a href="#">Navbar Link</a></li>
                </ul>
                <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
            </div>
        </nav>    

