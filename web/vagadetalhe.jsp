<%-- 
    Document   : vagadetalhe
    Created on : 07/06/2017, 20:47:18
--%>

<jsp:include page="header.jsp"/>
<div class="container z-depth-3 margin-bt-20 padding-50 cinza-claro">
    <div class="section margin-0" style="padding:0px;">
        <h4 class="margin-0">${vaga.nome}</h4>
        <span class="spn-categoria">Categoria: ${vagacategoria.nome}</span>
        <p>
            ${vaga.descricao}
        </p>
        <div class="divider"></div>
    </div>
    <div class="row  margin-0">
        <div class="input-field col s12">
            <a class="waves-effect waves-light btn right"><i class="material-icons right">add</i>Candidatar-se</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        carregaPagina();
    });

    function carregaPagina() {

    }

</script>