package br.insper.proj1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NovoUsuario")
public class NovoUsuario extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Dao dao = new Dao();
		
		Usuarios usuario = new Usuarios();
		

		usuario.setUsuario(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		dao.adicionaUsuario(usuario);
		
		response.sendRedirect("login.jsp");
		
		
		
	}
}