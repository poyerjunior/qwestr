<jsp:include page="header.jsp"/>
<div class="container margin-bt-10 txt-right">
    <a id="lnkNovo" data-id="0" class="waves-effect waves-light btn" href="#modal-cadastro"><i class="material-icons right">add</i>Novo</a>
</div>
<div class="container margin-bt-20">
    <table id="lvLista" class="table table-striped table-bordered dt-responsive nowrap striped" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Nome</th>
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
                <input id="command" type="hidden" value="0" name="id"/>
                <div class="row margin-0">
                    <div class="input-field col s2">
                        <input id="txtNome" type="text" class="validate" name="nome">
                        <label for="txtNome">Nome</label>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="modal-footer">
        <a href="#" class="modal-action waves-effect waves-teal btn-flat lnkSalvar">Salvar</a>
        <a href="#" class="modal-action modal-close waves-effect waves-teal btn-flat">Fechar</a>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        carregaPagina();
    });

    function carregaPagina() {
        var modalID = "modal-cadastro";

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
            novo("formCadastro", 0);
        });
    }

    function getList() {
        var tipoServelet = "GETLIST";
        $('#lvLista').DataTable({
            destroy: true,
            stateSave: true,
            "ajax": "ProcessaCadVagaCategoria?tipoServelet=" + tipoServelet,

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