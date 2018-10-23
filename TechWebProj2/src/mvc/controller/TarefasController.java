package mvc.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SearchResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.Dao;
import mvc.model.Posts;
import mvc.model.SearchPlaylistsExample;
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
	
	@RequestMapping("Main")
	public String Posts() {
		return("posts");
	}
	
	@RequestMapping("ConfereLogin")
	public ModelAndView ConfereLogin(@RequestParam("usuario") String user, @RequestParam("senha") String pass) throws SQLException {
	Dao dao = new Dao();
	List<Usuarios> usuariosLista = dao.getUsuarios();
	List<Usuarios> usuariosLogado = dao.getLogged();

	
	for (Usuarios usuario : usuariosLista ) {
		
		if (usuario.getUsuario().equals(user)) {
			
			
			if (usuario.getSenha().equals(pass)){
				
				dao.logged(usuariosLogado.get(0));
				return new ModelAndView("redirect:/Main");
			}			
		}
	}
	System.out.println("Aqui 2");

	return new ModelAndView("redirect:/Registro");
}
	@RequestMapping("Cria")
	public ModelAndView Cria(HttpServletResponse response, @RequestParam("titulo") String titulo) throws SpotifyWebApiException, IOException, JSONException {
		SearchPlaylistsExample playlist = new SearchPlaylistsExample();
		String play = playlist.teste(titulo);
		return new ModelAndView("redirect:" + play +"/");
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
	public ModelAndView NovoUsuario(HttpServletRequest request) {		
		Dao dao = new Dao();
		Usuarios usuario = new Usuarios();

		usuario.setUsuario(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setSteamID(request.getParameter("SteamID"));
		dao.adicionaUsuario(usuario);
		return new ModelAndView("redirect:/");
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
