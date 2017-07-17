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
                            <th>Descri��o</th>
                            <th>Status</th>
                            <th class="acoes">A��es</th>
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

                $(".menu-empresa").addClass("active");

                $('body').on('click', 'a.lnkRemover', function () {
                    setRemover($(this).data("id"));
                });
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
                        {"data": "A��es"}
                    ],
                    "initComplete": function (settings, json) {
                        $(".proximo").html("Pr�ximo");
                    },
                    "columnDefs": [
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
                                return "<a href=\"#\" class=\"lnkRemover\" data-id=\"" + obj[0].id + "\">Remover</a>";
                            }
                        }
                    ]
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
                        setToast("N�o � poss�vel deletar!");
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
        � preciso estar logado para acessar esse conte�do. <a href="index.jsp">Clique aqui para logar.</a>
    </c:otherwise>
</c:choose> 