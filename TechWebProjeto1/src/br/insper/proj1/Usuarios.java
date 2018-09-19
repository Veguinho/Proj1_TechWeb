package br.insper.proj1;

public class Usuarios {
	
	private int id_usuario;
	private String usuario;
	private String senha;
	
	public Integer getId() {
		return this.id_usuario;
	}
	
	public void setId(Integer id) {
		this.id_usuario = id;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
		
	}
	
	public String getUsuario() {
		return this.usuario;
		
	}
	
	public void setSenha (String senha) {
		this.senha = senha;
		
	}
	
	public String getSenha() {
		return this.senha;
		
	}

}
