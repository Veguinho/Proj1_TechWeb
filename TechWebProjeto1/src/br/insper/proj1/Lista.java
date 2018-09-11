package br.insper.proj1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lista")
public class Lista extends HttpServlet {
	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		Dao dao = new Dao();
		List<Posts> pessoas = dao.getLista();
		PrintWriter out = response.getWriter();
		
		out.println("<html><body><table border='1'>");
		out.println("<tr><td>ID</td><td>Titulo</td>" + "<td>Data</td><td>Usuario</td><td>Texto</td></tr>");

		
		for (Posts pessoa : pessoas) {
			out.println("<tr><td>" + pessoa.getId() + "</td>");
			out.println("<td>" + pessoa.getTitulo() + "</td>");
			out.println("<td>" + pessoa.getData().getTime() + "</td>");
			out.println("<td>" + pessoa.getUsuario() + "</td></tr>");
			out.println("<td>" + pessoa.getTexto() + "</td></tr>");
		}
		
		out.println("</table></body></html>");
		
		dao.close();
	
	 }
}
