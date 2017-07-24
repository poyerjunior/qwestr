<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="candidato" scope="session" class="Model.Candidato" />
<c:choose>
    <c:when test="${candidato.id > 0}"> 
        <jsp:include page="header.jsp"/>
        <div class="container z-depth-3 margin-bt-20 padding-20">
            <div class="row margin-0">
                <div class="row margin-0">
                    <div class="input-field col s12">
                        <input id="txtBuscar" type="text">
                        <label for="txtBusca">Procurar vagas</label>
                    </div>
                    <div class="input-field col s12">
                        <a id="lnkBuscar" class="waves-effect waves-light btn right"><i class="material-icons right">search</i>Buscar</a>
                    </div>
                </div>
            </div>
            <div class="row margin-0">
                <div class="section">
                    <div id="dvAguarde"></div>
                    <div class="row content-vagas margin-0">

                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            var dvMsg = "dvAguarde";
            var servlet = "ProcessaVagas"
            var qtd = 6;
            $(document).ready(function () {
                carregaPagina();
            });

            function carregaPagina() {

                $(".menu-vagas").addClass("active");

                $(window).scroll(function () {
                    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
                        qtd += 6;
                        getLista();
                    }
                });

                $("#txtBuscar").on('keyup', function (e) {
                    if (e.which == 13) {
                        getLista();
                    }
                });

                $("#lnkBuscar").on('click', function () {
                    getLista();
                });
                getLista();
            }

            function getLista() {
                var tipoServlet = "GETLIST";
                var p1 = $("#txtBuscar").val();
                getLoaderBar(dvMsg);
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet + "&qtd=" + qtd + "&p1=" + p1,
                    type: "get",
                    data: null,
                    success: function (data) {
                        var result = "";
                        data = JSON.parse(data);
                        console.log(data.data);
                        if (data.data.length == 0) {
                            result = "Nenhuma vaga encontrada."
                        } else {
                            jQuery.each(data.data, function (i, json) {
                                result += "<div class=\"col s12 m4\">" +
                                        "<div class=\"card small\">" +
                                        "<a href=\"ProcessaVagaDetalhe?tipoServlet=GETBYID&id=" + json.id + "\">" +
                                        "<div class=\"card-image waves-effect waves-block waves-light teal\" style=\"height: 60%;\">" +
                                        "<div class=\"card-categoria\">"+json.VagaCategoria.nome+"</div>" +
                                        "</div>" +
                                        "</a>" +
                                        "<div class=\"card-content\">" +
                                        "<span class=\"card-title activator grey-text text-darken-4\">" + json.nome + "<i class=\"material-icons right\">more_vert</i></span>" +
                                        "<p><a href=\"ProcessaVagaDetalhe?tipoServlet=GETBYID&id=" + json.id + "\">Ver vaga</a></p>" +
                                        "</div>" +
                                        "<div class=\"card-reveal\">" +
                                        "<span class=\"card-title grey-text text-darken-4\">" + json.nome + "<i class=\"material-icons right\">close</i></span>" +
                                        "<p>" + json.descricao + "</p>" +
                                        "</div>" +
                                        "</div>" +
                                        "</div>";
                            });
                        }

                        removeLoader();
                        $(".content-vagas").html(result);
                    }
                });
            }

        </script>
    </c:when>
    <c:otherwise>
        É preciso estar logado para acessar esse conteúdo. <a href="index.jsp">Clique aqui para logar.</a>
    </c:otherwise>
</c:choose> 

