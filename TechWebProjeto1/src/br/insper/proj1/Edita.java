package br.insper.proj1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Editar")
public class Editar extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String tituloPost = " ";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString = "01-01-2001";
		Date date = null;
	
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String dataPost = sdf.format(calendar.getTime());
		
		
		Integer usuarioPost = 1;
		String textoPost = " ";
		
		Dao dao = new Dao();
		List<Posts> posts = dao.getLista();
		for (Posts post : posts ) {
			
			if (post.getId() == Integer.parseInt(request.getParameter("id"))){
				
				tituloPost = post.getTitulo();
				dataPost = sdf.format(post.getData().getTime());
				//String data = post.getData();
				//dataPost = new SimpleDateFormat("dd-MM-yyyy").parse(data);
				usuarioPost = post.getUsuario();
				textoPost = post.getTexto();
				
			}
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Titulo: <input type='text' name='titulo' value="+ tituloPost + "><br>");
		out.println("Data: <input type='date' name='data' value=" + dataPost +"><br>");
		out.println("Usuario: <input type='int' name='usuario' value=" + usuarioPost + "><br>");
		out.println("Texto: <input type='text' name='texto' value=" + textoPost + "><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dao dao = new Dao();
		Posts post = new Posts();
		post.setId(Integer.parseInt(request.getParameter("id")));
		post.setTitulo(request.getParameter("titulo"));
		post.setTexto(request.getParameter("texto"));
		post.setUsuario(Integer.valueOf(request.getParameter("usuario")));
		String data = request.getParameter("data");
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
			response.sendRedirect("posts.jsp");
			dao.close();	
		}
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}