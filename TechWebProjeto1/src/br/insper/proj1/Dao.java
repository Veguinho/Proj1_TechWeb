package br.insper.proj1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Dao {
	
	private Connection connection = null;
	
	public Dao() {	
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection( 
						"jdbc:mysql://localhost/Projeto1", "root", "");
			
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
		String sql = "UPDATE Post SET " + "titulo=?, data=?, usuario=?, texto=?, WHERE id=?";
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
}
