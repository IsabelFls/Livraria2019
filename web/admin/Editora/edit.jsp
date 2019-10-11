
<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Adiciona Genero</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form action="EditoraWS" method="POST">
            <div class="row">
                <div class="col-md-3 pr-md-1">
                    <div class="form-group">
                        <label>Id</label>
                        <input type="text" class="form-control" name="txtid" placeholder=Id readonly= "true" value="${obj.id}">
                    </div>
                </div>
                <div class="col-md-5 pr-md-1">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" class="form-control" name="txtnome" placeholder="Nome" value="${obj.nome}">
                    </div>
                    <div class="form-group">
                        <label>Endereço</label>
                        <input type="text" class="form-control" name="txtendereco" placeholder="Endereço" value="${obj.endereco}">
                    </div>
                    <div class="form-group">
                        <label>Data de fundação</label>
                        <input type="date" class="form-control" name="txtdatafund" placeholder="Data de fundação" value="<fmt:formatDate value='${obj.datafund}' pattern='yyyy-MM-dd'/>">
                    </div>
                    <div class="form-group">
                        <label>Telefone</label>
                        <input type="text" class="form-control" name="txttelefone" placeholder="Telefone" value="${obj.telefone}">
                    </div>
                    <div class="form-group">
                        <label>Logo</label>
                        <input type="text" class="form-control" name="txtlogo" placeholder="Telefone" value="${obj.logo}">
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
        
            <div class="alert alert-primary alert-dismissible fade show" role="alert">
                ERRO
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <i class="tim-icons icon-simple-remove"></i>
                </button>
            </div>
        
    </div>
</div>
</div>
<%@include file="../rodape.jsp" %>