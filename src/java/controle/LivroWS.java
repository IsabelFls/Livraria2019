/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorDAO;
import dao.EditoraDAO;
import dao.GeneroDAO;
import dao.LivroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Autor;
import modelo.Editora;
import modelo.Genero;
import modelo.Livro;
import util.FormataData;

/**
 *
 * @author isabe
 */
@WebServlet(name = "LivroWS", urlPatterns = {"/admin/livro/LivroWS"})
public class LivroWS extends HttpServlet {
    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String pagina;
            String acao = request.getParameter("txtacao");
            LivroDAO dao = new LivroDAO();
            List<Livro> lista = null;
            Livro obj;
            Long id;
            Boolean deucerto;
            String msg;
            List<Livro> livros;
            
            AutorDAO autordao= new AutorDAO();
            List<Autor> autores;
            
            EditoraDAO editoradao= new EditoraDAO();
            List<Editora> editoras;
            
            GeneroDAO generodao= new GeneroDAO();
            List<Genero>generos;
            
            pagina="add.jsp";
        switch(String.valueOf(acao)){
            case "add":
                //abrir tela (talvez buscar dados)
                 //recebe lista de clientes
                autordao = new AutorDAO();
                autores = autordao.listar();
                request.setAttribute("editoras", autores);
               
                editoradao= new EditoraDAO();
                editoras= editoradao.listar();
                request.setAttribute("editoras", editoras);
                
                generodao= new GeneroDAO();
                generos= generodao.listar();
                request.setAttribute("genros", generos);
                
                pagina = "add.jsp"; 
                
                break;
            case "edit":
               
                autordao = new AutorDAO();
                autores = autordao.listar();
                request.setAttribute("autores", autores);
               
                generodao = new GeneroDAO();
                generos = generodao.listar();
                request.setAttribute("generos", generos);
                
                editoradao= new EditoraDAO();
                editoras= editoradao.listar();
                request.setAttribute("editoras", editoras);
                
                //pega o id do objeto a ser editado
                id = Long.parseLong(request.getParameter("txtid"));
                //busca ele no banco de dados pelo id
                obj = dao.buscarPorChavePrimaria(id);
                request.setAttribute("evento", obj);
                dao = new LivroDAO();
                lista = dao.listar();
                request.setAttribute("eventos", lista);
                pagina = "edit.jsp";
                            
                break;
            case "del":
                //excluir
                //excluir
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtid")));
                deucerto = dao.excluir(obj);
                if(deucerto){
                    msg= "Deletado com sucesso";
                }
                else{
                    msg= "Problema ao deletar";
                }
                //setar lista atualizada
               livros= dao.listar();
               request.setAttribute("msg", msg);
               //enviar lista atualizada
               request.setAttribute("lista", livros);
               //abrir tela
               pagina= "list.jsp";
                break;
            default:
            //abrir tela
            //listar objetos    
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            Livro obj;
            String msg;
            String pagina;
            RequestDispatcher destino;
            List<Livro> livros;
            Boolean deucerto;
            LivroDAO dao= new LivroDAO();
            
            //receber dados
            String id= request.getParameter("txtid");
            String nome= request.getParameter("txttitulo");
            String sinopse= request.getParameter("txtsinopse");
            String paginas= request.getParameter("txtpaginas");
            String isbn= request.getParameter("txtisbn");
            String datalancamento= request.getParameter("txtdatalancamento");
            String idioma= request.getParameter("txtidioma");
            String foto1= request.getParameter("txtfoto1");
            String foto2= request.getParameter("txtfoto2");
            String foto3= request.getParameter("txtfoto3");
            
            //formatar dados
            Integer pags;
            pags= Integer.parseInt(paginas);
            Date datalanc;
            datalanc = FormataData.formata(datalancamento, "yyyy-MM-dd");
            
            //pegar dados de outras classes
                Long id_genero = Long.parseLong(request.getParameter("txtgenero"));
                GeneroDAO generodao = new GeneroDAO();
                Genero genero = generodao.buscarPorChavePrimaria(id_genero);
                
                Long id_editora = Long.parseLong(request.getParameter("txteditora"));
                EditoraDAO editoradao = new EditoraDAO();
                Editora editora = editoradao.buscarPorChavePrimaria(id_editora);
                
                //recebe ids que enviei no add
                String[] ids_autores= request.getParameterValues("txtautores");
                //cria lista em branco
                List<Autor> autores = (List<Autor>) new ArrayList();;
                //cria daos
                AutorDAO autordao= new AutorDAO();  
                
                //percorre ids
                for(String id_aut:ids_autores){
                    //cria objeto
                    Autor autor= autordao.buscarPorChavePrimaria(Long.parseLong(id_aut));
                    //adiciona na lista
                    autores.add(autor);
                }
            
            //teste para saber se tem que editar ou criar
            if(id != null){
                //busca o que existe
                obj= dao.buscarPorChavePrimaria(Long.parseLong(id));
            }
            else{
                //cria um novo gênero
                obj= new Livro();
            }
            //colocar dados no obj
            obj.setTitulo(nome);
            obj.setData(datalanc);
            obj.setIdioma(idioma);
            obj.setSinopse(sinopse);
            obj.setPaginas(pags);
            obj.setIsbn(isbn);
            obj.setFoto1(foto1);
            obj.setFoto2(foto2);
            obj.setFoto3(foto3);
            obj.setData(datalanc);
            obj.setAutores(autores);
            obj.setGenero(genero);
            obj.setEditora(editora);
            
            if(id!= null){
                deucerto= dao.alterar(obj);
                pagina = "list.jsp";
                livros= dao.listar();
                request.setAttribute("lista",livros);
                if(deucerto){
                    msg= "Gênero alterado com sucesso!";
                }
                else{
                    msg= "Problema ao editar Gênero :( ";
                }
            }
            else{
                deucerto= dao.incluir(obj);
                pagina= "add.jsp";
                if(deucerto){
                    msg= "Editora adicionado com sucesso!";
                }
                else{
                    msg= "Problema ao adicionar Gênero :(";
                }
            }
            request.setAttribute("msg", msg);
            destino= request.getRequestDispatcher(pagina);
            destino.forward(request, response);
    }

}
