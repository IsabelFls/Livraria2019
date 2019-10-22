/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Autor;
import util.FormataData;

/**
 *
 * @author isabe
 */
@WebServlet(name = "AutorWS", urlPatterns = {"/admin/autor/AutorWS"})
public class AutorWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina;
        String acao = request.getParameter("txtacao");
        Autor obj;
        AutorDAO dao= new AutorDAO();
        Boolean deucerto;
        String msg;
        List<Autor> autores;
        RequestDispatcher destino;
        
        switch(String.valueOf(acao)){
            case "add":
                //abrir tela (talvez buscar dados)
                pagina= "add.jsp";
                break;
            case "edit":
                //abrir tela 
                pagina= "edit.jsp";
                //buscar dados
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtid")));
                request.setAttribute("obj", obj);
                break;
            case "del":
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
               autores= dao.listar();
               request.setAttribute("msg", msg);
               //enviar lista atualizada
               request.setAttribute("lista", autores);
               //abrir tela
               pagina= "list.jsp";
                break;
            default:
             //listar objetos
            String filtro= request.getParameter("txtfiltro");
            
            if(filtro == null){
                //lista todos
                autores= dao.listar();
            }
            else{
                //lista com filtro
                try {
                autores= dao.listar(filtro);
                } catch (Exception ex) {
                    autores = dao.listar();
                    msg= "Problema ao filtrar";
                    request.setAttribute("ms", msg);
                }
            }
            request.setAttribute("lista",autores);
            pagina= "list.jsp";
            break;
        }
        destino= request.getRequestDispatcher(pagina);
        destino.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
           
            Autor obj;
            String msg;
            String pagina;
            RequestDispatcher destino;
            List<Autor> autores;
            Boolean deucerto;
            AutorDAO dao= new AutorDAO();
            //receber dados
            String id= request.getParameter("txtid");
            String nome= request.getParameter("txtnome");
            String nacionalidade= request.getParameter("txtnacionalidade");
            String data= request.getParameter("txtdatanasc");
            String foto= request.getParameter("txtfoto");
            //formatar dados
            Date datanasc;
            datanasc = FormataData.formata(data, "yyyy-MM-dd");
            
            //teste para saber se tem que editar ou criar
            if(id != null){
                //busca o que existe
                obj= dao.buscarPorChavePrimaria(Long.parseLong(id));
            }
            else{
                //cria um novo gênero
                obj= new Autor();
            }
            //colocar dados no obj
            obj.setNome(nome);
            obj.setNacionalidade(nacionalidade);
            obj.setDatanasc(datanasc);
            obj.setFoto(foto);
            if(id!= null){
                deucerto= dao.alterar(obj);
                pagina = "list.jsp";
                autores= dao.listar();
                request.setAttribute("lista",autores);
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
                    msg= "Gênero adicionado com sucesso!";
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
