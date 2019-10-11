
package controle;

import dao.EditoraDAO;
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
import modelo.Editora;
import util.FormataData;

/**
 *
 * @author isabe
 */
@WebServlet(name = "EditoraWS", urlPatterns = {"/admin/Editora/EditoraWS"})
public class EditoraWS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String pagina;
        String acao = request.getParameter("txtacao");
        Editora obj;
        EditoraDAO dao= new EditoraDAO();
        Boolean deucerto;
        String msg;
        List<Editora> editoras;
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
               editoras= dao.listar();
               request.setAttribute("msg", msg);
               //enviar lista atualizada
               request.setAttribute("lista", editoras);
               //abrir tela
               pagina= "list.jsp";
                break;
            default:
             //listar objetos
            String filtro= request.getParameter("txtfiltro");
            
            if(filtro == null){
                //lista todos
                editoras= dao.listar();
            }
            else{
                //lista com filtro
                try {
                editoras= dao.listar(filtro);
                } catch (Exception ex) {
                    editoras = dao.listar();
                    msg= "Problema ao filtrar";
                    request.setAttribute("ms", msg);
                }
            }
            request.setAttribute("lista",editoras);
            pagina= "list.jsp";
            break;
        }
        destino= request.getRequestDispatcher(pagina);
        destino.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             Editora obj;
            String msg;
            String pagina;
            RequestDispatcher destino;
            List<Editora> autores;
            Boolean deucerto;
            EditoraDAO dao= new EditoraDAO();
            //receber dados
            String id= request.getParameter("txtid");
            String nome= request.getParameter("txtnome");
            String endereco= request.getParameter("txtendereco");
            String data= request.getParameter("txtdatafund");
            String foto= request.getParameter("txtlogo");
            String telefone= request.getParameter("txttelefone");
            //formatar dados
            Date datafund;
            datafund = FormataData.formata(data, "yyyy-MM-dd");
            
            //teste para saber se tem que editar ou criar
            if(id != null){
                //busca o que existe
                obj= dao.buscarPorChavePrimaria(Long.parseLong(id));
            }
            else{
                //cria um novo gênero
                obj= new Editora();
            }
            //colocar dados no obj
            obj.setNome(nome);
            obj.setEndereco(endereco);
            obj.setDatafund(datafund);
            obj.setLogo(foto);
            obj.setTelefone(telefone);
            
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
