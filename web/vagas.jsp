
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="row">
            <div class="input-field col s12">
                <input id="txtBuscar" type="text">
                <label for="txtBusca">Procurar vagas</label>
            </div>
            <div class="input-field col s12">
                <a id="lnkBuscar" class="waves-effect waves-light btn right"><i class="material-icons right">search</i>Buscar</a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="section">
        <div id="dvAguarde"></div>
        <div class="row content-vagas">

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
                data = JSON.parse(data);

                var result = "";
                console.log(data);
                jQuery.each(data.data, function (i, json) {
                    result += "<div class=\"col s12 m4\">" +
                            "<div class=\"card small\">" +
                            "<a href=\"vagadetalhe.jsp?id=" + json.id + "\">" +
                            "<div class=\"card-image waves-effect waves-block waves-light teal\" style=\"height: 60%;\">" +
                            "</div>" +
                            "</a>" +
                            "<div class=\"card-content\">" +
                            "<span class=\"card-title activator grey-text text-darken-4\">" + json.nome + "<i class=\"material-icons right\">more_vert</i></span>" +
                            "<p><a href=\"vagadetalhe.jsp\">Ver vaga</a></p>" +
                            "</div>" +
                            "<div class=\"card-reveal\">" +
                            "<span class=\"card-title grey-text text-darken-4\">" + json.nome + "<i class=\"material-icons right\">close</i></span>" +
                            "<p>" + json.descricao + "</p>" +
                            "</div>" +
                            "</div>" +
                            "</div>";
                });
                removeLoader();
                $(".content-vagas").html(result);
            }
        });
    }

</script>

