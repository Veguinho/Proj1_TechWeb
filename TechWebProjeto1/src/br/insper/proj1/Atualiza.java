package br.insper.proj1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/atualiza")
public class Atualiza extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("ID: <input type='number' name='id'><br>");
		out.println("Titulo: <input type='text' name='titulo'><br>");
		out.println("Data: <input type='date' name='data'><br>");
		out.println("Texto: <input type='text' name='texto'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dao dao = new Dao();
		Posts post = new Posts();
		post.setId(Integer.valueOf(request.getParameter("id")));
		post.setTitulo(request.getParameter("titulo"));
		post.setTexto(request.getParameter("texto"));
		String data = request.getParameter("nascimento");
		Date dateToday;
		
		try {
			dateToday = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(data);
			Calendar dataHoje = Calendar.getInstance();
			dataHoje.setTime(dateToday);
			post.setData(dataHoje);
			dao.altera(post);
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("atualizado" + post.getTitulo());
			out.println("</body></html>");
			dao.close();	
		}
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}