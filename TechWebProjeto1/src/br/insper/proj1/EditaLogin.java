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

@WebServlet("/EditarLogin")
public class EditarLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		boolean stop = false;
		
		Dao dao = new Dao();
		List<Usuarios> usuarios = dao.getUsuarios();
		for (Usuarios usuario : usuarios ) {
			
			if (usuario.getUsuario().equals(request.getParameter("usuario"))){
				
				usuario.setSenha(request.getParameter("senha"));
				
				dao.alteraSenha(usuario);
				System.out.println("SENHA ALTERADA!");
				dao.close();
				response.sendRedirect("login.jsp");
				stop = true;
				break;
			}
		}
		if(stop==false) {
		System.out.println("ESSE USUARIO NAO EXISTE!");
		response.sendRedirect("EditaLogin.jsp");
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeUsuario = " ";
		String senhaUsuario = " ";
		
		Dao dao = new Dao();
		List<Usuarios> usuarios = dao.getUsuarios();
		for (Usuarios usuario : usuarios ) {
			
			if (usuario.getUsuario() == request.getParameter("usuario")){
				
				usuario.setSenha(request.getParameter("senha"));
				
				dao.alteraSenha(usuario);
				System.out.println("SENHA ALTERADA!");
				dao.close();
				response.sendRedirect("login.jsp");

				
			}
			
			else {
				System.out.println("ESSE USUARIO NAO EXISTE!");
				response.sendRedirect("EditaLogin.jsp");
			}
		}
	}
}
	