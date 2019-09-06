/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        
        switch(String.valueOf(acao)){
            case "add":
                //abrir tela (talvez buscar dados)
                
                break;
            case "edit":
                //abrir tela 
                
                //buscar dados
                
                break;
            case "del":
                //excluir
                
                break;
            default:
            //abrir tela
            //listar objetos    
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    
}
