<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Adiciona Editora</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="EditoraWS" method="POST">
            <div class="row">
                <div class="col-md-5 pr-md-1">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" name="txtnome" required class="form-control" placeholder="Nome" >
                    </div>
                    <div>
                        <label>Endereço</label>
                        <input type="text" name="txtendereco" required class="form-control" placeholder="Endereço" >
                    </div>
                    <div>
                        <label>Data de fundação</label>
                        <input type="date" name="txtdatafund" required class="form-control" placeholder="Data de fundação" >
                    </div>
                    <div>
                        <label>Telefone</label>
                        <input type="text" name="txttelefone" required class="form-control" placeholder="Telefone" >
                    </div>
                    <div>
                        <label>Logo</label>
                        <input type="text" name="txtlogo" required class="form-control" placeholder="Logo" >
                    </div>
                </div>
            </div>

            <button class="btn btn-primary btn-round text-center" type="submit">
                <i class="tim-icons icon-cloud-upload-94"></i> Salvar
            </button> 
            <a class="btn btn-primary btn-round text-center" href="EditoraWS?txtacao=list">
                <i class="tim-icons icon-bullet-list-67"></i> Listar
            </a>
        </form>
    </div>

    <div class="card-footer">
        <c:if test="${msg!= nul}">
            <div class="alert alert-primary alert-dismissible fade show" role="alert">
                ${msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
        </c:if>
    </div>
</div>
<%@include file="../rodape.jsp" %>