<%-- 
    Document   : candidato
    Created on : 06/06/2017, 21:40:45
--%>

<jsp:include page="header.jsp"/>
<div class="margin-nav">
</div>
<div class="container">
    <div class="row">
        <form class="col s12">
            <div class="row">
                <div class="input-field col s12">
                    <input id="first_name" type="text" class="validate">
                    <label for="first_name">Procurar vagas</label>
                </div>
                <div class="input-field col s12">
                    <a class="waves-effect waves-light btn right"><i class="material-icons right">search</i>Buscar</a>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="container">
    <div class="section">
        <div class="row">
            <div class="col s12 m4">
                <div class="card small">
                    <div class="card-image waves-effect waves-block waves-light">
                        <a href="vagadetalhe.jsp"><img src="imagens/background1.jpg"></a>
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4">Analista de sistemas<i class="material-icons right">more_vert</i></span>
                        <p><a href="vagadetalhe.jsp">Ver vaga</a></p>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                        <p>Here is some more information about this product that is only revealed once clicked on.</p>
                    </div>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="card small">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img src="imagens/background2.jpg">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4">Programador<i class="material-icons right">more_vert</i></span>
                        <p><a href="#">This is a link</a></p>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                        <p>Here is some more information about this product that is only revealed once clicked on.</p>
                    </div>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="card small">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img src="imagens/background3.jpg">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4">Gerente de projeto<i class="material-icons right">more_vert</i></span>
                        <p><a href="#">This is a link</a></p>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                        <p>Here is some more information about this product that is only revealed once clicked on.</p>
                    </div>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="card small">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img src="imagens/background1.jpg">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4">Analista de sistemas<i class="material-icons right">more_vert</i></span>
                        <p><a href="#">This is a link</a></p>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                        <p>Here is some more information about this product that is only revealed once clicked on.</p>
                    </div>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="card small">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img src="imagens/background2.jpg">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4">Programador<i class="material-icons right">more_vert</i></span>
                        <p><a href="#">This is a link</a></p>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                        <p>Here is some more information about this product that is only revealed once clicked on.</p>
                    </div>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="card small">
                    <div class="card-image waves-effect waves-block waves-light">
                        <img src="imagens/background3.jpg">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4">Gerente de projeto<i class="material-icons right">more_vert</i></span>
                        <p><a href="#">This is a link</a></p>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                        <p>Here is some more information about this product that is only revealed once clicked on.</p>
                    </div>
                </div>
            </div>
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
<jsp:include page="footer.jsp"/>

