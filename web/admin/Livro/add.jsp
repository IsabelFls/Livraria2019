<%@include file="../cabecalho.jsp" %>
<div class="card">
    <div class="card-header">
        <h5 class="title">Adiciona Livro</h5>
    </div>
    <div class="card-body">
        <!--MODIFICAR PARA ADD-->
        <form  action="UploadWS" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="urldestino" value="LivroWS">
            <div class="row">
                <div class="col-md-5 pr-md-1">
                    <div class="form-group">
                        <label>Título</label>
                        <input type="text" name="txttitulo" required class="form-control" placeholder="Título" >
                    </div>
                    <div class="form-group">
                        <label>Páginas</label>
                        <input type="number" name="txtpaginas" required class="form-control" placeholder="Nome" >
                    </div>
                    <div class="form-group">
                        <label>Gênero</label>
                        <select class="form-control">
                            <c:forEach items="${generos}" var="gen">
                                <option value="${gen.id}">${gen.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Editora</label>
                        <select class="form-control">
                            <c:forEach items="${editoras}" var="edt">
                                <option value="${edt.id}">${edt.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Autores</label>
                        <div class="row">
                            <c:forEach items="${autores}" var="aut">
                            <div class="col-md-3">
                                <div class="custom-control custom-checkbox">
                                    <input value="${aut.id}" type="checkbox" class="custom-control-input" name="txtautores" id="u${aut.id}">
                                    <label class="custom-control-label" for="$u{aut.id}">${aut.nome}</label>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>ISBN</label>
                        <input type="number" name="txtisbn" required class="form-control" placeholder="ISBN" >
                    </div>
                    <div class="form-group">
                        <label>Idioma</label>
                        <input type="text" name="txtidioma" required class="form-control" placeholder="Idioma" >
                    </div>
                    <div class="form-group">
                        <label>Data de lançamento</label>
                        <input type="date" name="txtdatalancamento" required class="form-control" placeholder="Data de lançamento" >
                    </div>
                    <div class="form-group-file">
                    <div class="form-group">
                        <label>Sinopse</label>
                        <textarea rows="4" cols="80" class="form-control" placeholder="Sinopse"></textarea>
                    </div>
                    <div class="form-group-file">
                    <div class="form-group-file">
                        <label>Foto 1</label>
                        <input type="file" name="txtfoto1" required class="form-control form-control-file" placeholder="Foto" >
                    </div>
                    <div class="form-group-file">
                        <label>Foto 2</label>
                        <input type="file" name="txtfoto2" required class="form-control form-control-file" placeholder="Foto" >
                    </div>
                    <div class="form-group-file">
                        <label>Foto 3</label>
                        <input type="file" name="txtfoto3" required class="form-control form-control-file" placeholder="Foto" >
                    </div>    
                </div>
            </div>
            <button class="btn btn-primary btn-round text-center" type="submit">
                <i class="tim-icons icon-cloud-upload-94"></i> Salvar
            </button> 
            <a class="btn btn-primary btn-round text-center" href="LivroWS?txtacao=list">
                <i class="tim-icons icon-bullet-list-67"></i> Listar
            </a>
     
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