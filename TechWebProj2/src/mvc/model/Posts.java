package mvc.model;

import java.util.Calendar;

public class Posts {
	
	private Integer id;
	private String titulo;
	private Calendar data;
	private Integer usuario;
	private String texto;
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Calendar getData() {
		return this.data;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public Integer getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	public String getTexto() {
		return this.texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
