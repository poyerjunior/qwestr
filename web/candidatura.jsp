<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="candidato" scope="session" class="Model.Candidato" />
<c:choose>
    <c:when test="${candidato.id > 0}"> 
        <jsp:include page="header.jsp"/>
        <div class="container z-depth-3 margin-bt-20 padding-20">
            <div class="row margin-bt-20 margin-0">
                <table id="lvLista" class="table table-striped table-bordered dt-responsive nowrap striped" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Vaga</th>
                            <th>Categoria</th>
                            <th class="acoes">Descrição</th>
                            <th>Status</th>
                            <th class="acoes">Ações</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
        <script type="text/javascript">
            var form = "formCadastro";
            var servlet = "ProcessaCandidatura"
            var dvMsg = "dvMsg";

            $(document).ready(function () {
                carregaPagina();
            });

            function carregaPagina() {
                getList();

                $(".menu-minhas-candidaturas").addClass("active");

                $('body').on('click', 'a.lnkDelete', function () {
                    setRemover($(this).data("id"));
                });
                
                visualizaNotificacao();
            }

            function getList() {
                var tipoServlet = "GETLIST";
                $('#lvLista').DataTable({
                    destroy: true,
                    stateSave: true,
                    "ajax": servlet + "?tipoServlet=" + tipoServlet,
                    "columns": [
                        {"data": "nome"},
                        {"data": "VagaCategoria.nome"},
                        {"data": "descricao"},
                        {"data": "Aprovacao"},
                        {"data": "Ações"}
                    ],
                    "initComplete": function (settings, json) {
                        $(".proximo").html("Próximo");
                    },
                    "columnDefs": [
                        {
                            "targets": 2,
                            "data": null,
                            "render": function (data, type, full, meta) {
                                return "<div class=\"dv-tooltip-table-hover acoes\"><i class=\"material-icons\" style=\"color:#009688 !important;\">remove_red_eye</i><div class=\"dv-tooltip-table z-depth-3\">" + full.descricao + "</div></div>";
                            }
                        },
                        {
                            "targets": 3,
                            "data": null,
                            "render": function (data, type, full, meta) {
                                var obj = full.lstcandidatura.filter(function (el) {
                                    return (el.Vaga.id == full.id);
                                });
                                return obj[0].CandidaturaStatus.nome;
                            }
                        },
                        {
                            "targets": 4,
                            "data": null,
                            "render": function (data, type, full, meta) {
                                var obj = full.lstcandidatura.filter(function (el) {
                                    return (el.Vaga.id == full.id);
                                });
                                return "<div class=\"acoes\"><a href=\"#\" class=\"lnkDelete\" data-id=\"" + obj[0].id + "\"><i class=\"material-icons\">delete</i></a></div>";
                            }
                        }
                    ]
                });
            }

            function visualizaNotificacao() {
                var servlet = "ProcessaNotificacao";
                var tipoServlet = "setVisualizacao";
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet,
                    type: "post",
                    data: null,
                    success: function (data) {
                        $(".dv-notificacao").hide();
                    }
                });
            }

            function setRemover(idCandidatura) {
                var tipoServlet = "SETREMOVET";
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet + "&idCandidatura=" + idCandidatura,
                    type: "post",
                    success: function (data) {
                        getList();
                    },
                    error: function (data) {
                        setToast("Não é possível deletar!");
                    }
                });
            }

        </script>
    </c:when>
    <c:otherwise>
        É preciso estar logado para acessar esse conteúdo. <a href="index.jsp">Clique aqui para logar.</a>
    </c:otherwise>
</c:choose> 