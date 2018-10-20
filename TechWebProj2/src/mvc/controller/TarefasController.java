package mvc.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.Dao;
import mvc.model.Posts;
import mvc.model.Usuarios;

@Controller
public class TarefasController {
	@RequestMapping("/")
	public String execute() {
		return "login";
 }
	
	@RequestMapping("Registro")
	public String Registro() {
		return("cadastro");
	}
	
	@RequestMapping("ConfereLogin")
	public String ConfereLogin(@RequestParam("usuario") String user, @RequestParam("senha") String pass) {
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
	return pass;
}
	@RequestMapping("Cria")
	public String Cria(HttpServletResponse response, @RequestParam("titulo") String titulo, @RequestParam("usuario") Integer usuario, @RequestParam("texto") String texto, @RequestParam("data") String data) throws IOException {
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
	
	public String Edita(HttpServletResponse response, @RequestParam("id") String id,@RequestParam("titulo") String titulo,@RequestParam("texto") String texto, @RequestParam("usuario") Integer usuario,@RequestParam("data") String data) throws IOException {
		
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
	
	public String EditaLogin(@RequestParam("usuario") String usuario_atual, @RequestParam("senha") String senha) {
		
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
	
	public String Remove(@RequestParam("id") String id) {
		Dao dao = new Dao();
		dao.remove(Integer.parseInt(id));
		dao.close();
		return("posts");
	}
	
	@RequestMapping(value = "NovoUsuario", method = {RequestMethod.GET})
	public String NovoUsuario(HttpServletRequest request) {		
		Dao dao = new Dao();
		Usuarios usuario = new Usuarios();
		System.out.println("Passou");

		usuario.setUsuario(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		dao.adicionaUsuario(usuario);
		
		return "login";
	}

	
	@RequestMapping("RemoveUsuario")
	
	public String RemoveUsuario(@RequestParam("usuario") String usuario_atual, @RequestParam("senha") String senha) {
		
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
