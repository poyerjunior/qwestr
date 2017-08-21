<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="empresa" scope="session" class="Model.Empresa" />
<c:choose>
    <c:when test="${empresa.id > 0}"> 
        <jsp:include page="header.jsp"/>
        <div class="container z-depth-3 margin-bt-20 padding-20">
            <div class="row margin-bt-10 txt-right margin-0">
                <a id="lnkNovo" data-id="0" class="waves-effect waves-light btn" href="#modal-cadastro"><i class="material-icons right">add</i>Novo</a>
            </div>
            <div class="row margin-bt-20 margin-0">
                <table id="lvLista" class="table table-striped table-bordered dt-responsive nowrap striped" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th class="acoes">Ações</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
        <div id="modal-cadastro" class="modal  modal-fixed-footer""> 
            <div class="modal-content">
                <h4>Novo registro</h4>
                <div class="row margin-0">
                    <form id="formCadastro" class="col s12">
                        <input id="command" type="hidden" type="text" name="id"/>
                        <div class="row margin-0">
                            <div class="input-field col s12">
                                <input id="txtNome" type="text" class="validate" name="nome">
                                <label for="txtNome">Nome</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <div class="left" id="dvMsg">
                </div>
                <a href="#" class="modal-action waves-effect waves-teal btn-flat lnkSalvar">Salvar</a>
                <a href="#" class="modal-action modal-close waves-effect waves-teal btn-flat">Fechar</a>
            </div>
        </div>
        <script type="text/javascript">
            var form = "formCadastro";
            var servlet = "ProcessaCadVagaCategoria"
            var dvMsg = "dvMsg";

            $(document).ready(function () {
                carregaPagina();
            });

            function carregaPagina() {
                var modalID = "modal-cadastro";

                getList();

                $(".menu-categorias").addClass("active");

                $('body').on('click', 'a.lnkEdit', function () {
                    getDetails($(this).data("id"));
                });

                $('body').on('click', 'a.lnkDelete', function () {
                    setDeletarModal($(this).data("id"));
                });

                $("#" + modalID + " .lnkSalvar").on('click', function () {
                    salvar();
                });

                $("#lnkNovo").on('click', function () {
                    novo(form, 0);
                });
            }

            function novo(form, id) {
                $('#' + form)[0].reset();
                $("#command").val("0");
                $('.modal-content').removeClass("none");
                Materialize.updateTextFields();
            }

            function salvar() {
                var tipoServlet = "INSERT";
                if (validaForm(form)) {
                    getLoaderBar(dvMsg);
                    $.ajax({
                        url: servlet + "?tipoServlet=" + tipoServlet,
                        type: "post",
                        data: $("#" + form).serialize(),
                        success: function (data) {
                            $("#command").val(data.id);
                            setToast("Dados salvos com sucesso!");
                            getList();
                            $('#modal-cadastro').modal('close');
                            removeLoader();
                        }
                    });
                }
            }

            function getList() {
                var tipoServlet = "GETLIST";
                $('#lvLista').DataTable({
                    destroy: true,
                    stateSave: true,
                    "ajax": servlet + "?tipoServlet=" + tipoServlet,
                    "columns": [
                        {"data": "nome"},
                        {"data": "Ações"}
                    ],
                    "initComplete": function (settings, json) {
                        $(".proximo").html("Próximo");
                    },
                    "columnDefs": [{
                            "targets": 1,
                            "data": null,
                            "render": function (data, type, full, meta) {
                                return "<div class=\"acoes\"><a class=\"lnkEdit\" href=\"#modal-cadastro\" data-id=\"" + full.id + "\"><i class=\"material-icons\">mode_edit</i></a>" +
                                        "<a class=\"lnkDelete\" href=\"#modal-deletar\" data-id=\"" + full.id + "\"><i class=\"material-icons\">delete</i></a></div>";
                            }
                        }]
                });
            }

            function getDetails(id) {
                var tipoServlet = "GETBYID";
                $('#' + form)[0].reset();
                getLoaderBar(dvMsg);
                $('.modal-content').addClass("none");
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet + "&id=" + id,
                    type: "get",
                    data: null,
                    success: function (data) {
                        data = JSON.parse(data);
                        JsonToForm(form, data);
                        $("#command").val(data.id);
                        removeLoader();
                        $('.modal-content').removeClass("none");
                    }
                });
            }

            function deletar(id) {
                var tipoServlet = "DELETE";
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet + "&id=" + id,
                    type: "post",
                    success: function (data) {
                        getList();
                    },
                    error: function (data) {
                        setToast("Não é possível deletar!");
                    }
                });
            }

            function teste() {
                var tipoServelet = "GETLIST";
                $.ajax({
                    type: "post",
                    url: "ProcessaCadVagaCategoria", //this is my servlet
                    data: "tipoServelet=" + tipoServelet,
                    success: function (result) {
                        console.log(result);
                    }
                });
            }

        </script>
    </c:when>
    <c:otherwise>
        É preciso estar logado para acessar esse conteúdo. <a href="index.jsp">Clique aqui para logar.</a>
    </c:otherwise>
</c:choose> 
