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
						"jdbc:mysql://localhost/banco", "root", "");
			
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
	
	public List<Pessoas> getLista() {
		
		List<Pessoas> pessoas = new ArrayList<Pessoas>();
		
		try {	
			PreparedStatement stmt;
			stmt = connection.prepareStatement("SELECT * FROM Pessoa");			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Pessoas pessoa = new Pessoas();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("nascimento"));
				pessoa.setNascimento(data);
				pessoa.setAltura(rs.getDouble("altura"));
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pessoas;
	}
	
	public void adiciona(Pessoas pessoa) {
		
		String sql = "INSERT INTO Pessoa" + "(nome,nascimento,altura) values(?,?,?)";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,pessoa.getNome());
			stmt.setDate(2, new Date(pessoa.getNascimento().getTimeInMillis()));
			stmt.setDouble(3,pessoa.getAltura());
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
	
	public void altera(Pessoas pessoa) {
		String sql = "UPDATE Pessoa SET " + "nome=?, nascimento=?, altura=? WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pessoa.getNome());
			stmt.setDate(2, new Date(pessoa.getNascimento().getTimeInMillis()));
			stmt.setDouble(3, pessoa.getAltura());
			stmt.setInt(4, pessoa.getId());
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
			 .prepareStatement("DELETE FROM Pessoa WHERE id=?");
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
