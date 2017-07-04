<jsp:include page="header.jsp"/>
<div class="container margin-bt-10 txt-right">
    <a id="lnkNovo" data-id="0" class="waves-effect waves-light btn" href="#modal-cadastro"><i class="material-icons right">add</i>Novo</a>
</div>
<div class="container margin-bt-20">
    <table id="lvLista" class="table table-striped table-bordered dt-responsive nowrap striped" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Categoria</th>
                <th>Prova</th>
                <th class="acoes">Ações</th>
            </tr>
        </thead>
    </table>
</div>
<div id="modal-cadastro" class="modal modal-fixed-footer"> 
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
                <div class="row margin-0">
                    <div class="input-field col s12">
                        <textarea id="txtDescricao" class="materialize-textarea" maxlength="4000" data-length="4000" name="descricao"></textarea>
                        <label for="txtDescricao">Descriçãoo</label>
                    </div>
                </div>
                <div class="row margin-0">
                    <div class="input-field col s12">
                        <select id="ddlVagaCategoria" class="select-material" name="vagacategoria">
                        </select>
                        <label>Categoria</label>
                    </div>
                </div>
                <div class="row" style="margin-left: 12px;">
                    <label>Realizar prova?</label>
                </div>
                <div class="row" style="margin-left: 12px;">
                    <div class="switch">
                        <label>
                            Não
                            <input id="chkProva" type="checkbox" name="prova">
                            <span class="lever"></span>
                            Sim
                        </label>
                    </div>
                    <div class="clearfixr"></div>
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
    var servlet = "ProcessaCadVaga"
    var dvMsg = "dvMsg";
    $(document).ready(function () {
        carregaPagina();
    });
    function carregaPagina() {
        var modalID = "modal-cadastro";
        getComboVagaCategoria();
        getList();
        $(".menu-empresa").addClass("active");
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
        $('#txtDescricao').trigger('autoresize');
        $('#ddlVagaCategoria').material_select();
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
                    verfLogado(data);
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
                {"data": "descricao"},
                {"data": "VagaCategoria.nome"},
                {"data": "prova"},
                {"data": "Ações"}
            ],
            "initComplete": function (settings, json) {
                $(".proximo").html("Próximo");
            },
            "columnDefs": [
                {
                    "targets": 3,
                    "data": null,
                    "render": function (data, type, full, meta) {
                        if (data)
                            return "Sim";
                        else
                            return "Não";
                    }
                },
                {
                    "targets": 4,
                    "data": null,
                    "render": function (data, type, full, meta) {
                        return "<div class=\"acoes\"><a class=\"lnkEdit\" href=\"#modal-cadastro\" data-id=\"" + full.id + "\"><i class=\"material-icons\">mode_edit</i></a>" +
                                "<a class=\"lnkDelete\" href=\"#modal-deletar\" data-id=\"" + full.id + "\"><i class=\"material-icons\">delete</i></a></div>";
                    }
                }
            ]
        });
    }

    function getDetails(id) {
        var tipoServlet = "GETBYID";
        $('#' + form)[0].reset();
        $('#txtDescricao').trigger('autoresize');
        Materialize.updateTextFields();
        getLoaderBar(dvMsg);
        $('.modal-content').addClass("none");
        $.ajax({
            url: servlet + "?tipoServlet=" + tipoServlet + "&id=" + id,
            type: "get",
            data: null,
            success: function (data) {
                verfLogado(data);
                data = JSON.parse(data);
                JsonToForm(form, data);
                $('#ddlVagaCategoria').val(data.VagaCategoria.id);
                $("#command").val(data.id);
                $('#ddlVagaCategoria').material_select();
                $('#chkProva').prop('checked', data.prova);
                removeLoader();
                $('.modal-content').removeClass("none");
            }
        });
    }

    function getComboVagaCategoria() {
        var tipoServlet = "GETCOMBOVAGACATEGORIA";
        $.ajax({
            url: servlet + "?tipoServlet=" + tipoServlet,
            type: "get",
            data: null,
            success: function (data) {
                verfLogado(data);
                data = JSON.parse(data);
                var options = "<option disabled disabled selected value=\"0\">Selecione a categoria</option>";
                jQuery.each(data.data, function (i, json) {
                    options += "<option value=\"" + json.id + "\">" + json.nome + "</option>";
                });
                $("#ddlVagaCategoria").html(options);
                updateSelect();
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
            url: "ProcessaCadVaga", //this is my servlet
            data: "tipoServlet=" + tipoServelet,
            success: function (result) {
                alert(result);
                console.log(result);
            }
        });
    }

</script>