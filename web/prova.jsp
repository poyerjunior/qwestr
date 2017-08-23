<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="candidato" scope="session" class="Model.Candidato" />
<c:choose>
    <c:when test="${candidato.id > 0}"> 
        <jsp:include page="header.jsp"/>
        <div class="container z-depth-3 margin-bt-20 padding-20">
            <div id="dv-aguarde-prova" class="row margin-bt-20 margin-0 center-align">
                <a href="#" class="waves-effect waves-light btn" onclick="iniciarProva(this);">Iniciar prova</a>
            </div>
            <div class="row margin-bt-20 margin-0">
                <div id="questao">

                </div>
            </div>
        </div>
        <script type="text/javascript">
            var servlet = "ProcessaProva"
            var dvMsg = "dvMsg";
            var prova;

            $(document).ready(function () {
                carregaPagina();
            });

            function carregaPagina() {

                $(".menu-prova").addClass("active");

            }

            function iniciarProva(e) {
                atzProva();
                $(e).remove();
            }

            function atzProva() {
                getLoaderBar("dv-aguarde-prova");
                var tipoServlet = "getProva";
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet,
                    type: "post",
                    data: null,
                    success: function (data) {
                        prova = JSON.parse(data);
                        getQuestao();
                    }
                });
            }

            function getQuestao() {
                var questao = "";
                if (prova.lstQuestao.length > 0) {
                    questao = "<div id=\"enunciado\">" +
                            prova.lstQuestao[0].enunciado +
                            "</div>" +
                            "<div id=\"alternativas\">" +
                            "<p>" +
                            "<span class=\"nome-alternativa\">A</span>" +
                            "<input value=\"a\" name=\"rblAlternativa\" type=\"radio\" id=\"alt-a\" />" +
                            "<label for=\"alt-a\">" + prova.lstQuestao[0].a + "</label>" +
                            "</p>" +
                            "<p>" +
                            "<span class=\"nome-alternativa\">B</span>" +
                            "<input value=\"b\" name=\"rblAlternativa\" type=\"radio\" id=\"alt-b\" />" +
                            "<label for=\"alt-b\">" + prova.lstQuestao[0].b + "</label>" +
                            "</p>" +
                            "<p>" +
                            "<span class=\"nome-alternativa\">C</span>" +
                            "<input value=\"c\" name=\"rblAlternativa\" type=\"radio\" id=\"alt-c\" />" +
                            "<label for=\"alt-c\">" + prova.lstQuestao[0].c + "</label>" +
                            "</p>" +
                            "<p>" +
                            "<span class=\"nome-alternativa\">D</span>" +
                            "<input value=\"d\" name=\"rblAlternativa\" type=\"radio\" id=\"alt-d\" />" +
                            "<label for=\"alt-d\">" + prova.lstQuestao[0].d + "</label>" +
                            "</p>" +
                            "<p>" +
                            "<span class=\"nome-alternativa\">E</span>" +
                            "<input value=\"e\" name=\"rblAlternativa\" type=\"radio\" id=\"alt-e\" />" +
                            "<label for=\"alt-e\">" + prova.lstQuestao[0].e + "</label>" +
                            "</p>" +
                            "</div>" +
                            "<div class=\"row margin-bt-20 margin-0 right\">" +
                            "<a onclick=\"responder();\" class=\"waves-effect waves-light btn\">Responder</a>" +
                            "</div>";
                    $("#questao").html(questao);
                    removeLoader();
                } else {
                    getAcertos();
                }


            }

            function responder() {
                getLoaderBar("dv-aguarde-prova");
                var resposta = $('input[name="rblAlternativa"]:checked').val();
                var tipoServlet = "setResposta";
                $("#questao").html("");
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet,
                    type: "post",
                    data: "resposta=" + resposta + "&idQuestao=" + prova.lstQuestao[0].id,
                    success: function (data) {
                        atzProva();
                    }
                });
            }

            function getAcertos() {
                var tipoServlet = "getAcertos";
                $.ajax({
                    url: servlet + "?tipoServlet=" + tipoServlet,
                    type: "post",
                    data: null,
                    success: function (data) {
                        $("#questao").html("Você concluiu a prova com " + data + " acerto(s).");
                        removeLoader();
                    }
                });
            }

        </script>
    </c:when>
    <c:otherwise>
        É preciso estar logado para acessar esse conteúdo. <a href="index.jsp">Clique aqui para logar.</a>
    </c:otherwise>
</c:choose> 