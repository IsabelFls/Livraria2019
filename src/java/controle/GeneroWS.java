/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.GeneroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Genero;

@WebServlet(name = "GeneroWS", urlPatterns = {"/admin/Genero/GeneroWS"})
public class GeneroWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // txtacao indica qual case será usado
        RequestDispatcher destino;
        String pagina;
        String acao = request.getParameter("txtacao");
        Genero obj;
        List<Genero> generos;
        GeneroDAO dao= new GeneroDAO();
        Boolean deucerto;
        String msg;
        
        switch(String.valueOf(acao)){
            case "add":
                //abrir tela (talvez buscar dados)
                pagina= "add.jsp";
                break;
            case "edit":
                //buscar dados
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtid")));
                request.setAttribute("obj", obj);
                //abrir tela 
                pagina= "edit.jsp";
                break;
            case "del":
                //excluir
                //buscar dados
                obj = dao.buscarPorChavePrimaria(Long.parseLong(request.getParameter("txtid")));
                deucerto = dao.excluir(obj);
                if(deucerto){
                    msg= "Deletado com sucesso";
                }
                else{
                    msg= "Problema ao deletar";
                }
                //setar lista atualizada
               generos= dao.listar();
               request.setAttribute("msg", msg);
               //enviar lista atualizada
               request.setAttribute("lista", generos);
               //abrir tela
               pagina= "list.jsp";
                
                break;
            default:
            //listar objetos
            String filtro= request.getParameter("txtfiltro");
            
            if(filtro == null){
                //lista todos
                generos= dao.listar();
            }
            else{
                //lista com filtro
                try {
                generos= dao.listar(filtro);
                } catch (Exception ex) {
                    generos = dao.listar();
                    msg= "Problema ao filtrar";
                    request.setAttribute("ms", msg);
                }
            }
            request.setAttribute("lista",generos);
            pagina= "list.jsp";
            break;
        }
        
        destino= request.getRequestDispatcher(pagina);
        destino.forward(request,response);
    }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
            Genero obj;
            String msg;
            String pagina;
            RequestDispatcher destino;
            List<Genero> generos;
            Boolean deucerto;
            GeneroDAO dao= new GeneroDAO();
            //receber dados
            String id= request.getParameter("txtid");
            String nome= request.getParameter("txtnome");
            //teste para saber se tem que editar ou criar
            if(id != null){
                //busca o que existe
                obj= dao.buscarPorChavePrimaria(Long.parseLong(id));
            }
            else{
                //cria um novo gênero
                obj= new Genero();
            }
            //colocar dados no obj
            obj.setNome(nome);
            
            if(id!= null){
                deucerto= dao.alterar(obj);
                pagina = "list.jsp";
                generos= dao.listar();
                request.setAttribute("lista",generos);
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
