/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.GeneroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Genero;

@WebServlet(name = "GeneroWS", urlPatterns = {"/admin/genero/GeneroWS"})
public class GeneroWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // txtacao indica qual case será usado
        RequestDispatcher destino;
        String pagina;
        String acao = request.getParameter("txtacao");
        
        
        switch(String.valueOf(acao)){
            case "add":
                //abrir tela (talvez buscar dados)
                pagina= "add.jsp";
                break;
            case "edit":
                //abrir tela 
                pagina= "edit.jsp";
                //buscar dados
                
                break;
            case "del":
                //excluir
                pagina= "list.jsp";
                //buscar dados
                break;
            default:
            //abrir tela
            pagina= "list.jsp";
            //listar objetos
            String filtro= request.getParameter("txtfiltro");
            
            if(filtro == null){
                //lista todos
            }
            else{
                //lista com filtro 
            }
            break;
        }
        destino= request.getRequestDispatcher(pagina);
        destino.forward(request,response);
    }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Genero obj;
            GeneroDAO dao= new GeneroDAO();
            //receber dados
            String id= request.getParameter("txtid");
            String nome= request.getParameter("txtnome");
            //tratar dados
            
            //teste para saber se tem que editar ou crir
            if(id != null){
                //busca o que existe
                obj= dao.buscarPorChavePrimaria(Long.parseLong(id));
            }
            else{
                //cria um novo gênero
                obj= new Genero();
            }
    
    }

}
