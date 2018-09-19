package br.insper.proj1;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConfereLogin")
public class ConfereLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfereLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Boolean autorizado = false;
		
		Dao dao = new Dao();
		List<Usuarios> usuariosLista = dao.getUsuarios();
		
		for (Usuarios usuario : usuariosLista ) {
			
			if (usuario.getUsuario().equals(request.getParameter("usuario"))) {
				
				System.out.println("PASSOU O USUARIO!");
				
				if (usuario.getSenha().equals(request.getParameter("senha"))){
					
					System.out.println("PASSOU A SENHA!");
					response.sendRedirect("posts.jsp");					
					autorizado = true;	
				}				
			}
	
		}
			
		if (autorizado == false) {
		
			System.out.println("ACESSO NEGADO!");
			response.sendRedirect("login.jsp");	
		}
			

	}


}
