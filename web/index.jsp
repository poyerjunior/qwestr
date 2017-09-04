<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nice</title>
        <!--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> -->
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
        <style type="text/css">
            html{
                height: 100%;
            }
            body{
                height: 100%;
                background-color:rgba(0, 150, 136, 0.13) !important;
            }
            
            main{
                padding-bottom: 140px;
            }
            @media only screen and (min-width: 993px){
                .container.box-cadastre {
                    width: 290px;
                } 
                main{
                    padding-bottom: 64px;
                }
            }
        </style>
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
        <main>
            <nav class="teal" role="navigation">
                <div class="nav-wrapper container">
                    <a id="logo-container" href="#" class="brand-logo">Nice Jobs</a>
                    <ul class="right hide-on-med-and-down">
                        <li><a href="#modal-login" onclick="$('#formLogin')[0].reset();">Login</a></li>
                    </ul>

                    <a href="#" data-activates="nav-mobile" class="button-collapse white-text"><i class="material-icons">menu</i></a>
                </div>
            </nav>
            <ul id="nav-mobile" class="side-nav">
                <li><a href="#modal-login" onclick="$('#formLogin')[0].reset();">Login</a></li>
            </ul>
        </main>
        <div id="index-banner" class="parallax-container alinhar-centro" style="margin-top:-64px;">
            <div class="section no-pad-bot no-pad-top">
                <div class="z-depth-4 container box-cadastre" style="margin: 0 auto; background-color: #fafafa; padding: 20px;">
                    <h3 class="header center teal-text text-lighten-2">Nice Jobs</h3>
                    <div class="row center">
                        <h5 class="header col s12 light"></h5>
                    </div>
                    <div class="row center">
                        <a id="lnkCadastre" onclick="$('#formCadastro')[0].reset();resetModalCadastro(0);" href="#modal-cadastro" class="btn-large waves-effect waves-light teal lighten-1">Cadastre-se</a>
                    </div>
                    <br><br>
                </div>
            </div>
        </div>
        <div id="modal-login" class="modal">
            <div class="modal-content">
                <h4>Login</h4>
                <div class="row">
                    <form id="formLogin" class="col s12">
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="email" type="email" class="validate" name="email">
                                <label for="email">E-mail</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="senha" type="password" class="validate" name="senha">
                                <label for="senha">Senha</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-action waves-effect waves-green btn-flat" onclick="logar();">Logar</a>
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Fechar</a>
            </div>
        </div>
        <div id="modal-cadastro" class="modal modal-fixed-footer">
            <div class="modal-content">
                <h4>Cadastre-se</h4>
                <div class="row">
                    <form id="formCadastro" class="col s12">
                        <input id="command" type="hidden" type="text" name="id"/>
                        <div class="row">
                            <span>
                                <input name="grouptipo" type="radio" id="rblCandidato" checked="checked" value="0"/>
                                <label for="rblCandidato">Candidato</label>
                            </span>
                            <span>
                                <input name="grouptipo" type="radio" id="rblEmpresa" value="1"/>
                                <label for="rblEmpresa">Empresa</label>
                            </span>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input id="txtNome" type="text" class="validate" name="nome">
                                <label for="txtNome">Nome</label>
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
                                <input id="txtSenha" type="password" class="validate" name="senha">
                                <label for="txtSenha">Senha</label>
                            </div>
                            <div class="input-field col s6">
                                <input id="txtConfirmaSenha" type="password" class="validate" name="confirmasenha">
                                <label for="txtConfirmaSenha">Confirma Senha:</label>
                            </div>
                        </div>
                        <div class="row row-candidato">
                            <div class="input-field col s6">
                                <input id="txtCpf" type="text" class="validate cpfmask" name="cpf">
                                <label for="txtCpf">Cpf</label>
                            </div>
                            <div class="input-field col s6">
                                <div class="file-field input-field">
                                    <div class="btn">
                                        <span>Currículo</span>
                                        <input id="txtCurriculo" type="file" name="caminho" class="validate">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input id="txtCurriculo2" class="file-path" type="text" name="nomefile" class="validate">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-empresa none">
                            <div class="input-field col s6">
                                <input id="txtCnpj" type="text" class="cnpjmask" name="cnpj">
                                <label for="txtCnpj">Cnpj</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-action waves-effect waves-green btn-flat" onclick="cadastrar();">Cadastrar</a>
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Fechar</a>
            </div>
        </div>
        <script type="text/javascript">
            var files;

            $(document).ready(function () {
                carregaPagina();
            });

            function carregaPagina() {

                $('input[type=radio][name=grouptipo]').change(function () {
                    resetModalCadastro(this.value);
                });

                $("#formLogin input").on('keyup', function (e) {
                    if (e.which == 13) {
                        logar();
                    }
                });

                // Add events
                $('input[type=file]').on('change', prepareUpload);
                $('#formCadastro').on('submit', uploadFiles);

            }

            function logar() {
                if (validaInput("formLogin")) {
                    $.ajax({
                        type: "POST",
                        url: "ProcessaLogin",
                        data: $("#formLogin").serialize(),
                        success: function (data)
                        {
                            if ($.trim(data) == "ERRO") {
                                setMsg('Usuário ou senha inválidos!');
                            } else {
                                window.location = data;
                            }
                        }
                    });
                }
            }

            function cadastrar() {
                if (!verfSenha()) {
                    setMsg('Senhas distintas!');
                    return false;
                }
                if (validaInput("formCadastro")) {
                    $.ajax({
                        type: "POST",
                        url: "ProcessaCadastro",
                        data: $("#formCadastro").serialize() + "&curriculo=" + $("#txtCurriculo").val(),
                        success: function (data)
                        {
                            if ($.trim(data) == "ERRO_USUARIO_CADASTRADO") {
                                setMsg('E-mail já utilizado!');
                            } else {
                                if ($('input[name="grouptipo"]:checked').val() == "0") {
                                    data = JSON.parse(data);
                                    $("#command").val(data.id);
                                    $("#formCadastro").submit();
                                } else {
                                    $('#modal-cadastro').modal('close');
                                    setMsg('Emrpesa cadastrada com sucesso!');
                                }
                            }
                        }
                    });
                }
            }

            function prepareUpload(event) {
                files = event.target.files;
            }

            function uploadFiles(event) {
                event.stopPropagation(); // Stop stuff happening
                event.preventDefault(); // Totally stop stuff happening

                // START A LOADING SPINNER HERE

                // Create a formdata object and add the files
                var data = new FormData();
                $.each(files, function (key, value)
                {
                    data.append(key, value);
                });
                $.ajax({
                    url: 'ProcessaUpload?id=' + $("#command").val(),
                    type: 'POST',
                    data: data,
                    cache: false,
                    processData: false, // Don't process the files
                    contentType: false, // Set content type to false as jQuery will tell the server its a query string request
                    success: function (data, textStatus, jqXHR)
                    {
                        if (typeof data.error === 'undefined')
                        {
                            // Success so call function to process the form
                            //submitForm(event, data);
                            $('#modal-cadastro').modal('close');
                            setMsg('Dados cadastrados com sucesso!');
                        } else
                        {
                            // Handle errors here
                            console.log('ERRORS: ' + data.error);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        // Handle errors here
                        console.log('ERRORS: ' + textStatus);
                        // STOP LOADING SPINNER
                    }
                });
            }

            function verfSenha() {
                if (($("#txtSenha").val() != $("#txtConfirmaSenha").val()))
                    return false;
                else
                    return true;
            }

            function resetModalCadastro(id) {
                $("#command").val(0);
                if (id == '0') {
                    $('.row-candidato').removeClass('none');
                    $('.row-empresa').addClass('none');
                    $('#txtCnpj').val("");
                    $("#txtCnpj").removeClass("validate");
                    $("#txtCpf").addClass("validate");
                    $("#txtCurriculo").addClass("validate");
                    $("#txtCurriculo2").addClass("validate");
                } else if (id == '1') {
                    $('.row-candidato').addClass('none');
                    $('.row-empresa').removeClass('none');
                    $('#txtCpf').val("");
                    $("#txtCnpj").addClass("validate");
                    $("#txtCpf").removeClass("validate");
                    $("#txtCurriculo").removeClass("validate");
                    $("#txtCurriculo2").removeClass("validate");
                }
            }

            function validaInput(idForm) {
                var erro = false;
                $('#' + idForm + ' .validate').each(function () {
                    if (($(this).val() == "") || ($(this).hasClass('invalid'))) {
                        erro = true;
                    }
                });
                if (erro) {
                    setMsg('Preencha os campos corretamente!');
                    return false;
                } else {
                    return true;
                }
            }

            function setMsg(msg) {
                $('.toast').remove();
                Materialize.toast(msg, 4000);
            }
        </script>
        <jsp:include page="footer.jsp"/>