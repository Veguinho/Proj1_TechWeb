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

@WebServlet("/Cria")
public class Cria extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Dao dao = new Dao();
		
		Posts post = new Posts();
		
		post.setTitulo(request.getParameter("titulo"));
		post.setUsuario(Integer.valueOf(request.getParameter("usuario")));
		String data = request.getParameter("data");
		post.setTexto(request.getParameter("texto"));
		
		java.util.Date dateToday;
		
		try {
			dateToday = new SimpleDateFormat("dd-MM-yyyy").parse(data);
			Calendar dataDoPost = Calendar.getInstance();
			dataDoPost.setTime(dateToday);
			post.setData(dataDoPost);
			dao.adiciona(post);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("posts.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = new Dao();
		Posts post = new Posts();
		
		post.setTitulo(request.getParameter("titulo"));
		post.setUsuario(Integer.valueOf(request.getParameter("usuario")));
		String data = request.getParameter("data");
		post.setTexto(request.getParameter("texto"));
		java.util.Date dateToday;
		
		
		
		try {
			dateToday = new SimpleDateFormat("dd-MM-yyyy").parse(data);
			Calendar dataDoPost = Calendar.getInstance();
			dataDoPost.setTime(dateToday);
			post.setData(dataDoPost);
			dao.adiciona(post);
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("adicionado" + post.getTitulo());
			out.println("</body></html>");
			dao.close();
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
