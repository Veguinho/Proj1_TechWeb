package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.*;


@Controller
public class MainController{

	
	@RequestMapping(value= "Registro", method =  RequestMethod.POST)
	public String Registro() {
		return("cadastro");
	}
	
	@RequestMapping("ConfereLogin")

	public String ConfereLogin(@RequestAttribute("usuario") String user, @RequestAttribute("senha") String pass) {
		Dao dao = new Dao();
		List<Usuarios> usuariosLista = dao.getUsuarios();
		
		for (Usuarios usuario : usuariosLista ) {
			
			if (usuario.getUsuario().equals(user)) {
				
				System.out.println("PASSOU O USUARIO!");
				
				if (usuario.getSenha().equals(pass)){
					
					System.out.println("PASSOU A SENHA!");				
					return ("post");
				}			
			}
		}
		return("login");
		}
	
	
	
	@RequestMapping("Cria")
	public String Cria(HttpServletResponse response, @RequestAttribute("titulo") String titulo, @RequestAttribute("usuario") Integer usuario, @RequestAttribute("texto") String texto, @RequestAttribute("data") String data) throws IOException {
		Dao dao = new Dao();
		Posts post = new Posts();
		post.setTitulo(titulo);
		post.setUsuario(Integer.valueOf(usuario));
		post.setTexto(texto);
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
		return ("login");
	}
	
	@RequestMapping("Edita")
	
	public String Edita(HttpServletResponse response, @RequestAttribute("id") String id,@RequestAttribute("titulo") String titulo,@RequestAttribute("texto") String texto, @RequestAttribute("usuario") Integer usuario,@RequestAttribute("data") String data) throws IOException {
		
		Dao dao = new Dao();
		Posts post = new Posts();
		post.setId(Integer.parseInt(id));
		post.setTitulo(titulo);
		post.setTexto(texto);
		post.setUsuario(usuario);
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
		return ("Posts");
		
	}
	
	@RequestMapping("EditaLogin")
	
	public String EditaLogin(@RequestAttribute("usuario") String usuario_atual, @RequestAttribute("senha") String senha) {
		
		Dao dao = new Dao();
		List<Usuarios> usuarios = dao.getUsuarios();
		for (Usuarios usuario : usuarios ) {
			
			if (usuario.getUsuario().equals(usuario_atual)){
				
				usuario.setSenha(senha);
				
				dao.alteraSenha(usuario);
				System.out.println("SENHA ALTERADA!");
				dao.close();
				return("login");		
			}	
			
	}
	return("EditaLogin");
}
	@RequestMapping("Remove")
	
	public String Remove(@RequestAttribute("id") String id) {
		Dao dao = new Dao();
		dao.remove(Integer.parseInt(id));
		dao.close();
		return("posts");
	}
	
	@RequestMapping("NovoUsuario")
	
	public String NovoUsuario(@RequestAttribute("usuario") String novo_usuario, @RequestAttribute("senha") String senha) {
		Dao dao = new Dao();
		
		Usuarios usuario = new Usuarios();
		

		usuario.setUsuario(novo_usuario);
		usuario.setSenha(senha);
		dao.adicionaUsuario(usuario);
		
		return("login");
	}
	
	@RequestMapping("RemoveUsuario")
	
	public String RemoveUsuario(@RequestAttribute("usuario") String usuario_atual, @RequestAttribute("senha") String senha) {
		
		boolean stop = false;
		Dao dao = new Dao();
		List<Usuarios> usuarios = dao.getUsuarios();
		for (Usuarios usuario : usuarios ) {
			
			if (usuario.getUsuario().equals(usuario_atual)){
				
				usuario.setSenha(senha);
				
				dao.removeUsuario(usuario);
				System.out.println("USUARIO APAGADO!");
				dao.close();
				return("login");	
			}
		}	
		System.out.println("ESSE USUARIO NAO EXISTE!");
		return("DeletarLogin");
		
	}
	
}


	
	
	

	
	
	

