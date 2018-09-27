import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daniel
 */

@WebServlet(value="/form")
public class FormAtualizar extends HttpServlet{
    private Connection conn;
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("O servlet iniciou!");
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Livros", "root", "root");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("uma nova requisição");
        
        String id=req.getParameter("id");
        
        PrintWriter out=resp.getWriter();
    
        try{
            PreparedStatement stm = conn.prepareStatement("select * from ebook where id=?");
            
            stm.setString(1, id);
            
            ResultSet result = stm.executeQuery();
            while(result.next()){
            
            String nome = result.getString("nome");
           
            out.print("<html><body><head>");
              out.print("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\n" +
"        <link href=\"https://fonts.googleapis.com/css?family=Fjalla+One\" rel=\"stylesheet\">");
            out.print("<style>body{\n" +
        "        margin: 0;\n" +
        "        padding: 0;\n" +
        "        width: 100%;\n" +
        "        background: url(img/back.jpg);\n" +
        "        font-family: 'Fjalla One', sans-serif;\n" +
        "        }input{\n" +
            "        height: 35px;\n" +
            "        width: 300px;\n" +
            "        font-size: 14px;\n" +
            "        margin-bottom: 20px;\n" +
            "        margin: 10px auto;\n" +
            "        border: none;\n" +
            "        border-radius: 6px;;\n" +
            "        background-color: #fff;\n" +
            "    }\n" +
            "        h1 ,label{\n" +
            "        font-size: 80px; \n" +
            "        color: white;\n" +
            "        font-weight: bold;\n" +
            "        }\n" +
            "        \n" +
            "        </style>");
            out.print("</head>");
            out.print("<div  class=\"container col-md-12 py-4\" align=\"center\" >");
            out.print("<h1 class=\" py-4 text-danger text-center\" style='font-size: 50px;font-weight: bold;'>Atualizar Lista</h1>");
  
            out.print("<form method=\"get\" action='atualizar'>");
             out.print("<div class=\"form-input\">");
            out.print("<input class=\"text-center\" type=\"text\" name='nome' value='"+nome+"'/>");
            out.print("</div>");
            out.print("<div class=\"form-input\">");
            out.print("<input class=\"text-center\" type=\"text\" name='id' value='"+id+"'/>");
            out.print("</div>");
            out.print("<button class=\"btn btn-danger btn-lg\" type=\"submit\">Atualizar</button>");
            out.print("</form>");
            out.print("</div>");
            out.print("</body></html>");
           
            }
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
            out.print("<div class=\"container py-5\" align=\"center\">");
            out.println("<h1 align=\"center\" style='color: white; font-size: 60px; padding:-top: 180px;'>Livro não Cadastrado</h1>");
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

