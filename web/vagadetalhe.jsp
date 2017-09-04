<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="candidato" scope="session" class="Model.Candidato" />
<c:choose>
    <c:when test="${candidato.id > 0}"> 
        <jsp:include page="header.jsp"/>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div class="container z-depth-3 margin-bt-20 padding-50 cinza-claro">
            <div class="section margin-0" style="padding:0px;">
                <h4 class="margin-0">${vaga.nome}</h4>
                <span class="spn-categoria">Categoria: ${vagacategoria.nome}</span>
                <pre>
                    ${vaga.descricao}
                </pre>
                <div class="divider"></div>
            </div>
            <div class="row  margin-0">
                <c:choose>
                    <c:when test="${isCandidato > 0}"> 
                        <div class="input-field col s12">
                            <a onclick="deleteCandidatar(${Candidatura.id});" class="waves-effect waves-light btn right"><i class="material-icons right">delete</i>Remover candidatura</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="input-field col s12">
                            <a onclick="setCandidatar(${vaga.id});" class="waves-effect waves-light btn right"><i class="material-icons right">add</i>Candidatar-se</a>
                        </div>
                    </c:otherwise>
                </c:choose>  
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                carregaPagina();
            });

            function carregaPagina() {
                $("pre").html($("pre").html().trim());
            }

            function setCandidatar(idVaga) {
                var tipoServlet = "SETCANDIDATURA";
                $.ajax({
                    type: "post",
                    url: "ProcessaVagaDetalhe", //this is my servlet
                    data: "tipoServlet=" + tipoServlet + "&idVaga=" + idVaga,
                    success: function (result) {
                        if ($.trim(result) == "ERRO_PROVA") {
                            setToast("É preciso realizar a prova para se candidatar à esta vaga.");
                        } else {
                            location.reload();
                        }
                    }
                });
            }

            function deleteCandidatar(idCandidatura) {
                var tipoServlet = "DELETECANDIDATURA";
                $.ajax({
                    type: "post",
                    url: "ProcessaVagaDetalhe", //this is my servlet
                    data: "tipoServlet=" + tipoServlet + "&idCandidatura=" + idCandidatura,
                    success: function (result) {
                        location.reload();
                    }
                });
            }

        </script>
    </c:when>
    <c:otherwise>
        É preciso estar logado para acessar esse conteúdo. <a href="index.jsp">Clique aqui para logar.</a>
    </c:otherwise>
</c:choose> 