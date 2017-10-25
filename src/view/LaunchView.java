package view;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class LaunchView extends JFrame {

	private JButton comparar, buscar, copiar, btnGuardar, btnRecuperar, btnRecuperarTodos;
	private JTextArea textArea;
	private JTextField fichero1, fichero2, palabra, titulo, autor, año, editor, paginas;
	private JLabel label_f1, label_f2, label_pal, label_titulo, label_autor, label_año, label_editor, label_paginas;
	private JCheckBox primera;
	private JPanel consola, global;

	public LaunchView() {
		setBounds(200, 200, 1000, 450);
		setTitle("Proyecto Buffers");

		global = new JPanel();
		getContentPane().add(global, BorderLayout.CENTER);
		global.setLayout(null);

		consola = new JPanel();
		consola.setBounds(10, 5, 805, 286);
		global.add(consola);

		textArea = new JTextArea(15, 60);
		JScrollPane sp = new JScrollPane(textArea);
		consola.add(sp);
		textArea.setBounds(50, 50, 50, 50);
		textArea.setEditable(false);
		label_titulo = new JLabel("Titulo");
		label_titulo.setBounds(825, 21, 86, 14);
		global.add(label_titulo);

		// Libro
		titulo = new JTextField("", 10);
		titulo.setBounds(825, 46, 86, 20);
		global.add(titulo);
		label_autor = new JLabel("Autor");
		label_autor.setBounds(825, 82, 86, 14);
		global.add(label_autor);
		autor = new JTextField("", 10);
		autor.setBounds(825, 107, 86, 20);
		global.add(autor);
		label_año = new JLabel("Año");
		label_año.setBounds(825, 135, 86, 14);
		global.add(label_año);
		año = new JTextField("", 10);
		año.setBounds(825, 160, 86, 20);
		global.add(año);
		label_editor = new JLabel("Editor");
		label_editor.setBounds(825, 192, 86, 14);
		global.add(label_editor);
		editor = new JTextField("", 10);
		editor.setBounds(825, 217, 86, 20);
		global.add(editor);
		label_paginas = new JLabel("Paginas");
		label_paginas.setBounds(825, 246, 86, 14);
		global.add(label_paginas);
		paginas = new JTextField("", 10);
		paginas.setBounds(825, 271, 86, 20);
		global.add(paginas);

		btnGuardar = new JButton("Guardar libro");
		btnGuardar.setBounds(791, 307, 150, 26);
		global.add(btnGuardar);
		btnGuardar.setPreferredSize(new Dimension(150, 26));
		btnRecuperar = new JButton("Recuperar libro");
		btnRecuperar.setBounds(791, 339, 150, 26);
		global.add(btnRecuperar);
		btnRecuperar.setPreferredSize(new Dimension(150, 26));
		btnRecuperarTodos = new JButton("Recuperar Todos");
		btnRecuperarTodos.setBounds(791, 375, 150, 26);
		global.add(btnRecuperarTodos);
		btnRecuperarTodos.setPreferredSize(new Dimension(150, 26));

		label_f1 = new JLabel("Fichero 1:");
		label_f1.setBounds(129, 302, 59, 14);
		global.add(label_f1);

		fichero1 = new JTextField("", 10);
		fichero1.setBounds(187, 302, 86, 20);
		global.add(fichero1);
		label_f2 = new JLabel("Fichero 2:");
		label_f2.setBounds(282, 302, 59, 14);
		global.add(label_f2);
		fichero2 = new JTextField("", 10);
		fichero2.setBounds(340, 302, 86, 20);
		global.add(fichero2);
		label_pal = new JLabel("Palabra:");
		label_pal.setBounds(441, 302, 49, 14);
		global.add(label_pal);
		palabra = new JTextField("", 10);
		palabra.setBounds(491, 302, 86, 20);
		global.add(palabra);

		primera = new JCheckBox("Primera aparición");
		primera.setBounds(588, 298, 192, 23);
		global.add(primera);

		comparar = new JButton("Comparar contenido");
		comparar.setBounds(129, 354, 150, 26);
		global.add(comparar);
		comparar.setPreferredSize(new Dimension(150, 26));
		buscar = new JButton("Buscar palabra");
		buscar.setBounds(303, 354, 150, 26);
		global.add(buscar);
		buscar.setPreferredSize(new Dimension(150, 26));
		copiar = new JButton("Copiar fichero");
		copiar.setBounds(491, 354, 150, 26);
		global.add(copiar);
		copiar.setPreferredSize(new Dimension(150, 26));
	}

	public JButton getComparar() {
		return comparar;
	}

	public void setComparar(JButton comparar) {
		this.comparar = comparar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JButton getCopiar() {
		return copiar;
	}

	public void setCopiar(JButton copiar) {
		this.copiar = copiar;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JTextField getFichero1() {
		return fichero1;
	}

	public void setFichero1(JTextField fichero1) {
		this.fichero1 = fichero1;
	}

	public JTextField getFichero2() {
		return fichero2;
	}

	public void setFichero2(JTextField fichero2) {
		this.fichero2 = fichero2;
	}

	public JTextField getPalabra() {
		return palabra;
	}

	public void setPalabra(JTextField palabra) {
		this.palabra = palabra;
	}

	public JCheckBox getPrimera() {
		return primera;
	}

	public void setPrimera(JCheckBox primera) {
		this.primera = primera;
	}

	public void showError(String m) {
		JOptionPane.showMessageDialog(this.consola, m, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public JTextField getTitulo() {
		return titulo;
	}

	public void setTitulo(JTextField titulo) {
		this.titulo = titulo;
	}

	public JTextField getAutor() {
		return autor;
	}

	public void setAutor(JTextField autor) {
		this.autor = autor;
	}

	public JTextField getAño() {
		return año;
	}

	public void setAño(JTextField año) {
		this.año = año;
	}

	public JTextField getEditor() {
		return editor;
	}

	public void setEditor(JTextField editor) {
		this.editor = editor;
	}

	public JTextField getPaginas() {
		return paginas;
	}

	public void setPaginas(JTextField paginas) {
		this.paginas = paginas;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnRecuperar() {
		return btnRecuperar;
	}

	public void setBtnRecuperar(JButton btnRecuperar) {
		this.btnRecuperar = btnRecuperar;
	}

	public JButton getBtnRecuperarTodos() {
		return btnRecuperarTodos;
	}

	public void setBtnRecuperarTodos(JButton btnRecuperarTodos) {
		this.btnRecuperarTodos = btnRecuperarTodos;
	}

	public void limpiarCampos() {
		titulo.setText("");
		autor.setText("");
		año.setText("");
		editor.setText("");
		paginas.setText("");
	}

}