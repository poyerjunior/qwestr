<%-- 
    Document   : index
    Created on : 05/06/2017, 20:37:01
--%>
<jsp:include page="header.jsp"/>
<div class="margin-nav">
</div>
<div id="index-banner" class="parallax-container">
    <div class="section no-pad-bot">
        <div class="container">
            <br><br>
            <h1 class="header center teal-text text-lighten-2">Nice Vagas</h1>
            <div class="row center">
                <h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>
            </div>
            <div class="row center">
                <a id="lnkCadastre" onclick="$('#formCadastro')[0].reset();" href="#modal-cadastro" class="btn-large waves-effect waves-light teal lighten-1">Cadastre-se</a>
            </div>
            <br><br>
        </div>
    </div>
    <div class="parallax"><img src="imagens/background1.jpg" alt="Unsplashed background img 1"></div>
</div>
<div class="container">
    <div class="section">
        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text"><i class="material-icons">flash_on</i></h2>
                    <h5 class="center">Speeds up development</h5>

                    <p class="light">We did most of the heavy lifting for you to provide a default stylings that incorporate our custom components. Additionally, we refined animations and transitions to provide a smoother experience for developers.</p>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text"><i class="material-icons">group</i></h2>
                    <h5 class="center">User Experience Focused</h5>

                    <p class="light">By utilizing elements and principles of Material Design, we were able to create a framework that incorporates components and animations that provide more feedback to users. Additionally, a single underlying responsive system across all platforms allow for a more unified user experience.</p>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="icon-block">
                    <h2 class="center brown-text"><i class="material-icons">settings</i></h2>
                    <h5 class="center">Easy to work with</h5>

                    <p class="light">We have provided detailed documentation as well as specific code examples to help new users get started. We are also always open to feedback and can answer any questions a user may have about Materialize.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="parallax-container valign-wrapper">
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row center">
                <h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>
            </div>
        </div>
    </div>
    <div class="parallax"><img src="imagens/background2.jpg" alt="Unsplashed background img 2"></div>
</div>
<div class="container">
    <div class="section">

        <div class="row">
            <div class="col s12 center">
                <h3><i class="mdi-content-send brown-text"></i></h3>
                <h4>Contact Us</h4>
                <p class="left-align light">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque id nunc nec volutpat. Etiam pellentesque tristique arcu, non consequat magna fermentum ac. Cras ut ultricies eros. Maecenas eros justo, ullamcorper a sapien id, viverra ultrices eros. Morbi sem neque, posuere et pretium eget, bibendum sollicitudin lacus. Aliquam eleifend sollicitudin diam, eu mattis nisl maximus sed. Nulla imperdiet semper molestie. Morbi massa odio, condimentum sed ipsum ac, gravida ultrices erat. Nullam eget dignissim mauris, non tristique erat. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;</p>
            </div>
        </div>

    </div>
</div>
<div class="parallax-container valign-wrapper">
    <div class="section no-pad-bot">
        <div class="container">
            <div class="row center">
                <h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>
            </div>
        </div>
    </div>
    <div class="parallax"><img src="imagens/background3.jpg" alt="Unsplashed background img 3"></div>
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
<div id="modal-cadastro" class="modal">
    <div class="modal-content">
        <h4>Cadastre-se</h4>
        <div class="row">
            <form id="formCadastro" class="col s12">
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
                        <input id="txtNome" type="text" class="validate">
                        <label for="txtNome">Nome</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="txtEmail" type="text" class="validate">
                        <label for="txtEmail">E-mail</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input id="txtSenha" type="password" class="validate">
                        <label for="txtSenha">Senha</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="txtConfirmaSenha" type="password" class="validate">
                        <label for="txtConfirmaSenha">Confirma Senha:</label>
                    </div>
                </div>
                <div class="row row-candidato">
                    <div class="input-field col s6">
                        <input id="txtCpf" type="text" class="validate cpfmask">
                        <label for="txtCpf">Cpf</label>
                    </div>
                    <div class="input-field col s6">
                        <div class="file-field input-field">
                            <div class="btn">
                                <span>Currículo</span>
                                <input type="file">
                            </div>
                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row row-empresa none">
                    <div class="input-field col s6">
                        <input id="txtCnpj" type="text" class="validate cnpjmask">
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
    $(document).ready(function () {
        carregaPagina();
    });

    function carregaPagina() {

        $('input[type=radio][name=grouptipo]').change(function () {
            if (this.value == '0') {
                $('.row-candidato').removeClass('none');
                $('.row-empresa').addClass('none');
                $('#txtCnpj').val("");
            } else if (this.value == '1') {
                $('.row-candidato').addClass('none');
                $('.row-empresa').removeClass('none');
                $('#txtCpf').val("");
            }
        });

    }

    function logar() {
        if (validaInput("formLogin")) {
            //$.post('ProcessaLogin', $('#formLogin').serialize());
            $.ajax({
                type: "POST",
                url: "ProcessaLogin",
                data: $("#formLogin").serialize(),
                success: function (data)
                {
                    if (data == "ERRO") {
                        Materialize.toast('Usuário ou senha inválidos!', 4000);
                    } else {
                        window.location = "candidato.jsp";
                    }
                }
            });
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
            Materialize.toast('Preencha os campos corretamente!', 4000);
            return false;
        } else {
            return true;
        }
    }
</script>
<jsp:include page="footer.jsp"/>