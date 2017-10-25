package model;

import java.io.Serializable;

public class Libro implements Serializable {
	private String identificador;
	private String titulo;
	private String autor;
	private String a�o;
	private String editor;
	private String paginas;

	public Libro(String identificador, String titulo, String autor, String a�o, String editor, String paginas) {
		this.identificador = identificador;
		this.titulo = titulo;
		this.autor = autor;
		this.a�o = a�o;
		this.editor = editor;
		this.paginas = paginas;
	}

	@Override
	public String toString() {
		String descripcion = "Titulo: " + titulo + "\n Autor: " + autor + "\n A�o: " + a�o + "\n Editor: " + editor
				+ "\n P�ginas: " + paginas;
		return descripcion;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getA�o() {
		return a�o;
	}

	public void setA�o(String a�o) {
		this.a�o = a�o;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

}