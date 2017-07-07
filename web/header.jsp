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

            updateSelect();

            $(".lnkConfExcluir").on('click', function () {
                deletar($("#command-delete").val());
            });

        }

        function JsonToForm(form, data, span) {
            $.each(data, function (name, val) {
                var $el = $('#' + form + ' [name="' + name + '"]'), type = $el.attr('type');
                switch (type) {
                    case 'checkbox':
                        $el.attr('checked', 'checked');
                        break;
                    case 'radio':
                        $el.filter('[value="' + val + '"]').attr('checked', 'checked');
                        break;
                    default:
                        if (!span) {
                            $el.val(val);
                        } else {
                            $el.html(val);
                        }
                }
            });
            Materialize.updateTextFields();
        }

        function validaForm(idForm) {
            var ok = true;
            $("#" + idForm + " .validate").each(function () {
                if (($(this).hasClass("invalid")) || ($(this).val() == "")) {
                    $(this).addClass("invalid");
                    setToast("Preencha os campos corretamente.");
                    ok = false;
                    return false;
                }
            });
            return ok;
        }

        function setDeletarModal(id) {
            $("#command-delete").val(id);
        }

        function getLoaderBar(idDiv) {
            $("#" + idDiv).html("<div class=\"progress\"><div class=\"indeterminate\"></div></div>");
        }

        function removeLoader() {
            $(".progress").remove();
        }

        function setToast(msg) {
            $('.toast').remove();
            Materialize.toast(msg, 4000);
        }

        function updateSelect() {
            $('.select-material').material_select('destroy');
            $('.select-material').material_select();
        }

        function verfLogado(data) {
            if ($.trim(data) == "ERRO_DESLOGAR") {
                window.location = "/NiceVagas";
            }
        }


    </script>
    <body>
        <main style="padding-bottom: 64px;">
            <nav class="teal" role="navigation">
                <div class="nav-wrapper container">
                    <a id="logo-container" href="#" class="brand-logo">Logo</a>
                    <ul class="right hide-on-med-and-down">
                        <li><a href="vagas.jsp">Vagas</a></li>
                        <li><a href="vagacategoria.jsp">Categoria de Vaga</a></li>
                        <li><a href="vaga.jsp">Cadastro de Vagas</a></li>
                    </ul>

                    <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
                </div>
            </nav> 
            <ul id="nav-mobile" class="side-nav">
                <li><a href="vagas.jsp">Vagas</a></li>
                <li><a href="vagacategoria.jsp">Categoria de Vaga</a></li>
                <li><a href="vaga.jsp">Cadastro de Vagas</a></li>
            </ul>
        </main>
        <div id="modal-deletar" class="modal modal-fixed-footer">
            <div class="modal-content">
                <input id="command-delete" type="hidden" value="0" />
                <h5>Confirma exclusão?</h5>
            </div>
            <div class="modal-footer">
                <a href="#" class="modal-action modal-close waves-effect waves-teal btn-flat lnkConfExcluir">Sim</a>
                <a href="#" class="modal-action modal-close waves-effect waves-teal btn-flat">Não</a>
            </div>
        </div>
