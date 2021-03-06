package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.wrapper.spotify.SpotifyApi;

public class Dao {
	
	private Connection connection = null;
	
	public Dao() {	
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection( 
						"jdbc:mysql://localhost/Projeto1", "root", "root");
			
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<Posts> getLista() {
		
		List<Posts> postsLista = new ArrayList<Posts>();
		
		try {	
			PreparedStatement stmt;
			stmt = connection.prepareStatement("SELECT * FROM Post");			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Posts post = new Posts();
				post.setId(rs.getInt("id"));
				post.setTitulo(rs.getString("titulo"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				post.setData(data);
				post.setUsuario(rs.getInt("usuario"));
				post.setTexto(rs.getString("texto"));
				postsLista.add(post);
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postsLista;
	}
	
	public void adiciona(Posts post) {
		
		String sql = "INSERT INTO Post" + "(titulo,data,usuario,texto) values(?,?,?,?)";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,post.getTitulo());
			stmt.setDate(2, new Date(post.getData().getTimeInMillis()));
			stmt.setInt(3,post.getUsuario());
			stmt.setString(4, post.getTexto());
			stmt.execute();
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		
		try {
			connection.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void altera(Posts post) {
		String sql = "UPDATE Post SET " + "titulo=?, data=?, usuario=?, texto=? WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,post.getTitulo());
			stmt.setDate(2, new Date(post.getData().getTimeInMillis()));
			stmt.setInt(3,post.getUsuario());
			stmt.setString(4, post.getTexto());
			stmt.setInt(5, post.getId());
			stmt.execute();
			stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void remove(Integer id) {
		PreparedStatement stmt;
		try {
			stmt = connection
			 .prepareStatement("DELETE FROM Post WHERE id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} 
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public List<Usuarios> getUsuarios() {
		
		List<Usuarios> usuariosLista = new ArrayList<Usuarios>();
		
		try {	
			PreparedStatement stmt;
			stmt = connection.prepareStatement("SELECT * FROM login");			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSteamID(rs.getString("steamid"));
				usuariosLista.add(usuario);
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuariosLista;
	}
	
	
	public List<Usuarios> getLogged() {
		
		List<Usuarios> usuariosLista = new ArrayList<Usuarios>();
		
		try {	
			PreparedStatement stmt;
			stmt = connection.prepareStatement("SELECT * FROM logged");			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setSteamID(rs.getString("SteamID"));
				usuario.setUsuario(rs.getString("usuario"));
				usuariosLista.add(usuario);
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuariosLista;
	}
	
	public void logged(Usuarios usuario) throws SQLException {
		String sql0 = "DELETE FROM Logged";
		PreparedStatement stmt0;
		
		try{
			stmt0 = connection.prepareStatement(sql0);
			stmt0.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO Logged" + "(usuario,steamid) values(?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2,usuario.getSteamID());
			stmt.execute();
			stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionaUsuario(Usuarios usuario) {
		
		String sql = "INSERT INTO Login" + "(id_usuario,usuario,senha,steamid) values(?,?,?,?)";
		PreparedStatement stmt;
		List<Usuarios> list = getUsuarios();
		Integer ultimo = 1;
		if (list.size() == 0) {
			ultimo = 1;
		}
		else {
			ultimo = list.get(list.size() - 1).getId() +1;
		}
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,ultimo);
			stmt.setString(2,usuario.getUsuario());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getSteamID());
			stmt.execute();
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alteraSenha (Usuarios usuario) {
		String sql = "UPDATE Login SET " + "senha=? WHERE usuario=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getSenha());
			stmt.setString(2,usuario.getUsuario());
			stmt.execute();
			stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeUsuario (Usuarios usuario) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Login WHERE usuario=?");
			stmt.setString(1, usuario.getUsuario());
			stmt.execute();
			stmt.close();
		} 
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
}
