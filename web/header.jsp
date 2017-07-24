<%-- 
    Document   : header
    Created on : 05/06/2017, 20:45:41
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        function getDadosEmpresa() {
            var servlet = "ProcessaDados"
            var tipoServlet = "GETBYIDEMPRESA";
            var form = "formDadosEmpresa";
            var dvMsg = "dvMsgDadosEmpresa";
            $('#' + form)[0].reset();
            getLoaderBar(dvMsg);
            $('.modal-content').addClass("none");
            $.ajax({
                url: servlet + "?tipoServlet=" + tipoServlet,
                type: "get",
                data: null,
                success: function (data) {
                    data = JSON.parse(data);
                    JsonToForm(form, data);
                    removeLoader();
                    $('.modal-content').removeClass("none");
                }
            });
        }
        
        function getDadosCandidato() {
            var servlet = "ProcessaDados"
            var tipoServlet = "GETBYIDCANDIDATO";
            var form = "formDadosCandidato";
            var dvMsg = "dvMsgDadosCandidato";
            $('#' + form)[0].reset();
            getLoaderBar(dvMsg);
            $('.modal-content').addClass("none");
            $.ajax({
                url: servlet + "?tipoServlet=" + tipoServlet,
                type: "get",
                data: null,
                success: function (data) {
                    data = JSON.parse(data);
                    JsonToForm(form, data);
                    removeLoader();
                    $('.modal-content').removeClass("none");
                }
            });
        }

        function salvarDadosEmpresa() {
            var tipoServlet = "UPDATEEMPRESA";
            var servlet = "ProcessaDados"
            var form = "formDadosEmpresa";
            var dvMsg = "dvMsgDadosEmpresa";

            if (!verfSenhaEmpresa()) {
                setToast("Senhas dinstintas!");
                return false;
            }
            if (validaForm(form)) {
                getLoaderBar(dvMsg);
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet,
                    type: "post",
                    data: $("#" + form).serialize(),
                    success: function (data) {
                        setToast("Dados salvos com sucesso!");
                        $('#modal-dados-empresa').modal('close');
                        removeLoader();
                    }
                });
            }
        }
        
        function salvarDadosCandidato() {
            var tipoServlet = "UPDATECANDIDATO";
            var servlet = "ProcessaDados"
            var form = "formDadosCandidato";
            var dvMsg = "dvMsgDadosCandidato";

            if (!verfSenhaCandidato()) {
                setToast("Senhas dinstintas!");
                return false;
            }
            if (validaForm(form)) {
                getLoaderBar(dvMsg);
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet,
                    type: "post",
                    data: $("#" + form).serialize(),
                    success: function (data) {
                        setToast("Dados salvos com sucesso!");
                        $('#modal-dados-candidato').modal('close');
                        removeLoader();
                    }
                });
            }
        }

        function verfSenhaEmpresa() {
            if (($("#txtSenhaEmpresa").val() != $("#txtConfirmaSenhaEmpresa").val())) {
                return false;
            } else {
                return true;
            }

        }
        
        function verfSenhaCandidato() {
            if (($("#txtDadosCandidatoSenha").val() != $("#txtDadosCandidatoConfirmaSenha").val())) {
                return false;
            } else {
                return true;
            }

        }


    </script>
    <body>
        <jsp:useBean id="empresa" scope="session" class="Model.Empresa" />
        <main style="padding-bottom: 64px;">
            <nav class="teal" role="navigation">
                <div class="nav-wrapper container">
                    <a id="logo-container" href="#" class="brand-logo">Logo</a>
                    <ul class="right hide-on-med-and-down">
                        <c:choose>
                            <c:when test="${empresa.id > 0}"> 
                                <li class="menu-categorias"><a href="vagacategoria.jsp">Categorias</a></li>
                                <li class="menu-minhas-vagas"><a href="vaga.jsp">Minhas vagas</a></li>
                                <li><a href="#modal-dados-empresa" onclick="getDadosEmpresa();">Meus dados</a></li>
                                </c:when>
                                <c:otherwise>
                                <li class="menu-minhas-candidaturas"><a href="candidatura.jsp">Minhas candidaturas</a></li>
                                <li class="menu-vagas"><a href="vagas.jsp">Vagas</a></li>
                                <li><a href="#modal-dados-candidato" onclick="getDadosCandidato();">Meus dados</a></li>
                                </c:otherwise>
                            </c:choose> 
                        <li><a href="ProcessaLogout">Logout</a></li>
                    </ul>
                    <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
                </div>
            </nav> 
            <ul id="nav-mobile" class="side-nav">
                <li><a href="vagas.jsp">Vagas</a></li>
                <li><a href="vagacategoria.jsp">Categoria de Vaga</a></li>
                <li><a href="vaga.jsp">Cadastro de Vagas</a></li>
                <li><a href="#modal-dados-empresa"  onclick="getDadosEmpresa();">Meus dados</a></li>
                <li><a href="ProcessaLogout">Logout</a></li>
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
        <div id="modal-dados-empresa" class="modal modal-fixed-footer">
            <div class="modal-content">
                <h5>Alteração de dados</h5>
                <div class="row">
                    <form id="formDadosEmpresa" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="txtDadosEmpresaNome" type="text" class="validate" name="nome">
                                <label for="txtDadosEmpresaNome">Nome</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="txtDadosEmpresaEmail" type="text" class="validate" name="email">
                                <label for="txtDadosEmpresaEmail">E-mail</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="txtSenhaEmpresa" type="password" class="validate" name="senha">
                                <label for="txtSenhaEmpresa">Senha</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="txtDadosEmpresaConfirmaSenhaEmpresa" type="password" class="validate" name="confirmasenha">
                                <label for="txtDadosEmpresaConfirmaSenhaEmpresa">Confirma Senha:</label>
                            </div>
                        </div>
                        <div class="row row-empresa none">
                            <div class="input-field col s6">
                                <input id="txtDadosEmpresaCnpj" type="text" class="cnpjmask" name="cnpj">
                                <label for="txtDadosEmpresaCnpj">Cnpj</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <div class="left" id="dvMsgDadosEmpresa">
                </div>
                <a id="lnkSalvarDadosEmpresa" href="#" onclick="salvarDadosEmpresa();" class="modal-action waves-effect waves-teal btn-flat">Salvar</a>
                <a href="#" class="modal-action modal-close waves-effect waves-teal btn-flat">Fechar</a>
            </div>
        </div>
        <div id="modal-dados-candidato" class="modal modal-fixed-footer">
            <div class="modal-content">
                <h5>Alteração de dados</h5>
                <div class="row">
                    <form id="formDadosCandidato" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="txtDadosCandidatoNome" type="text" class="validate" name="nome">
                                <label for="txtDadosCandidatoNome">Nome</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="txtEmail" type="text" class="validate" name="email">
                                <label for="txtEmail">E-mail</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <input id="txtDadosCandidatoSenha" type="password" class="validate" name="senha">
                                <label for="txtDadosCandidatoSenha">Senha</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="txtDadosCandidatoConfirmaSenha" type="password" class="validate" name="confirmasenha">
                                <label for="txtDadosCandidatoConfirmaSenha">Confirma Senha:</label>
                            </div>
                        </div>
                        <div class="row row-candidato">
                            <div class="input-field col s6">
                                <input id="txtDadosCandidatoCpf" type="text" class="validate cpfmask" name="cpf">
                                <label for="txtDadosCandidatoCpf">Cpf</label>
                            </div>
                            <div class="input-field col s6">
                                <div class="file-field input-field">
                                    <div class="btn">
                                        <span>Currículo</span>
                                        <input id="txtDadosCandidatoCurriculo" type="file" name="caminho" class="">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input id="txtDadosCandidatoCurriculo2" class="file-path" type="text" name="nomefile" class="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <div class="left" id="dvMsgDadosCandidato">
                </div>
                <a id="lnkSalvarDadosEmpresa" href="#" onclick="salvarDadosCandidato();" class="modal-action waves-effect waves-teal btn-flat">Salvar</a>
                <a href="#" class="modal-action modal-close waves-effect waves-teal btn-flat">Fechar</a>
            </div>
        </div>
