package br.insper.proj1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Remove")
public class Remove extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Dao dao = new Dao();
		dao.remove(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("posts.jsp");
		dao.close();
		
	}
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Dao dao = new Dao();
	 dao.remove(Integer.parseInt(request.getParameter("id")));
	 response.sendRedirect("posts.jsp");
	 dao.close();
	 }
}
