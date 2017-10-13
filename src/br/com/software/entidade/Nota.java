package br.com.software.entidade;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Nota implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String titulo;

	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + id;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	// converte objetos Java para JSON e retorna JSON como String
	public static String converterFromJson(Nota nota) {
		Gson gson = new Gson();
		return gson.toJson(nota);
	}

	// converte string JSON para objeto Java
	public static <T> T converterFromEntity(String jsonString, Class<T> clazz) {
		GsonBuilder builder = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss");
		Gson gson = builder.create();
		return gson.fromJson(jsonString, clazz);
	}

	@Override
	public String toString() {
		return "Nota [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}

}
