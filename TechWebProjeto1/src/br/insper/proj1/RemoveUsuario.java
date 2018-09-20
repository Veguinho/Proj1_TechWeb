package br.insper.proj1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveUsuario")
public class RemoveUsuario extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean stop = false;
		Dao dao = new Dao();
		List<Usuarios> usuarios = dao.getUsuarios();
		for (Usuarios usuario : usuarios ) {
			
			if (usuario.getUsuario().equals(request.getParameter("usuario"))){
				
				usuario.setSenha(request.getParameter("senha"));
				
				dao.removeUsuario(usuario);
				System.out.println("USUARIO APAGADO!");
				dao.close();
				response.sendRedirect("login.jsp");
				stop = true;
				break;			
			}
		}
		if (stop==false) {	
		System.out.println("ESSE USUARIO NAO EXISTE!");
		response.sendRedirect("DeletarLogin.jsp");
		}
		
	}
}
