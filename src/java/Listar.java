
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/listar")
public class Listar extends HttpServlet{
    private Connection conn;
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("O servlet iniciou!");
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
           conn = DriverManager.getConnection("jdbc:mysql://localhost/livros", "root", "root");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        System.out.println("uma nova requisição");

        
        PrintWriter out=resp.getWriter();
    
        try{
            PreparedStatement stm = conn.prepareStatement("select * from ebook");
            ResultSet result = stm.executeQuery();
                 out.print("<html><head>");
                 out.print("<meta charset=\"utf-8\">");
            out.print("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\n" +
"        <link href=\"https://fonts.googleapis.com/css?family=Fjalla+One\" rel=\"stylesheet\">");
            out.print("<style> body{\n" +
            "        margin: 0;\n" +
            "        padding: 0;\n" +
            "        width: 100%;\n" +
            "        background: url(img/back.jpg);\n" +
            "        font-family: 'Fjalla One', sans-serif;\n" +
            "        }table,th,td{border: none; border-radius: 50px 20px; padding: 10px;}</style>");
            out.print("</head><body>");
            out.print("<div class=\"container col-md-12 py-5\" align=\"center\">");
            out.print("<h1 class=\" py-4 text-danger text-center\" style='font-size: 50px;font-weight: bold;'>Lista de Livros</h1>");
            out.println("<table class=\"outset\" style='background: linear-gradient(to bottom, rgba(255,255,255,0.15) 0%, rgba(0,0,0,0.15) 100%), radial-gradient(at top center, rgba(255,255,255,0.40) 0%, rgba(0,0,0,0.40) 120%) #989898; \n" +
" background-blend-mode: multiply,multiply;);font-size: 30px;font-weight: bold;color: white;'>");
            out.print("<tr><th class=\"text-danger text-center\">Nome</th> <th class=\"text-danger\">Id</th class=\"text-danger\"><th class=\"text-danger\">Excluir</th><th class=\"text-danger\">Atualizar</th></tr>");
            while(result.next()){
               
                String nome= result.getString("nome");
                String id = result.getString("id");
       
                
                out.println("<tr>");
              
                out.println("<td class=\"text-center\">"+nome+"</td>");
                out.println("<td class=\"text-center\">"+id+"</td>");
                
            
                out.println("<td text-align:center'><a href='excluir?id="+id+"'><img style='width:30px;' src='./img/delete.png'/></a></td>");
                out.println("<td text-align:center'><a href='form?id="+id+"'><img style='width:30px;' src='./img/pencil1.png'/></a></td>");
                out.println("</tr>");
               
                        
            }
            out.println("</table>");
             out.print(" <br><a href=\"index.html\"> <button class=\"btn btn-danger btn-lg\" type=\"submit\">HOME</button></a>");
             out.print(" <a href=\"cadastro.html\"><button class=\"btn btn-danger btn-lg\" type=\"submit\" value=\"cadastra\">Cadastrar Livros</button>\n" +
"            </a>    ");
             
            out.print("</div>");
            out.print("</head></body></html>");
        }catch(Exception e){
           out.print("<html><head>");
            out.print("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\n" +
"        <link href=\"https://fonts.googleapis.com/css?family=Fjalla+One\" rel=\"stylesheet\">");
            out.print("<style> body{\n" +
            "        margin: 0;\n" +
            "        padding: 0;\n" +
            "        width: 100%;\n" +
            "        background: url(img/back.jpg);\n" +
            "        font-family: 'Fjalla One', sans-serif;\n" +
            "        }</style>");
            out.print("</head><body>");
            out.print("<div class=\"container py-4\" align=\"center\">");
            out.println("<h1 align=\"center\" style='color: white; font-size: 60px; padding:-top: 180px;'>Livro não cadastrado</h1>");
            out.print("<a href=\"listar\"><button class=\"btn btn-danger btn-lg\" type=\"submit\">Listar Livros</button>");
            out.print(" <a href=\"cadastro.html\"><button class=\"btn btn-danger btn-lg\" type=\"submit\" value=\"cadastra\">Cadastrar Livros</button>");
            out.print("</div>");
            out.print("</body></html>");
            e.printStackTrace();
        }
// super.service(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("O servlet foi destroido!");
        
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("A conexão foi fechada");
        }
        
    }
    
    
    
}

