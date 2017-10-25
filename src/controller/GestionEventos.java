package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private ActionListener actionListener_comparar, actionListener_buscar, ActionListener_copiar,
			ActionListener_guardar, ActionListener_recuperar, ActionListener_recuperarTodos;

	public GestionEventos(GestionDatos model, LaunchView view) {
		this.model = model;
		this.view = view;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la función call_compararContenido
				try {
					call_compararContenido();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// TODO: Llamar a la función call_buscarPalabra
				call_buscarPalabra();
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);

		// Copiar

		ActionListener_copiar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_copiarFichero();
			}
		};
		view.getCopiar().addActionListener(ActionListener_copiar);

		// Guardar libro
		ActionListener_guardar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_guardarLibro();
			}
		};
		view.getBtnGuardar().addActionListener(ActionListener_guardar);

		// Recuperar libro
		ActionListener_recuperar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_recuperarLibro();
			}
		};
		view.getBtnRecuperar().addActionListener(ActionListener_recuperar);

		// Recuperar todos
		ActionListener_recuperarTodos = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_recuperarTodos();
			}
		};
		view.getBtnRecuperarTodos().addActionListener(ActionListener_recuperarTodos);

	}

	private int call_compararContenido() throws FileNotFoundException, IOException {

		// TODO: Llamar a la función compararContenido de GestionDatos
		// TODO: Gestionar excepciones
		try {

			boolean xd = model.compararContenido(view.getFichero1().getText(), view.getFichero2().getText());
			// Segun devuelva True o False monstrara un mensaje
			if (xd) {
				view.getTextArea().setText("Coinciden");
			} else {
				view.getTextArea().setText("No coinciden");
			}
		} catch (Exception e) {
			view.showError("Error: " + e);
		}
		return 1;
	}

	private int call_buscarPalabra() {

		// TODO: Llamar a la función buscarPalabra de GestionDatos
		// TODO: Gestionar excepciones

		int resultado;

		try {
			resultado = model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText().trim(),
					view.getPrimera().isSelected());
			// Si se produce alguna coincidencia
			System.out.println("Resultado: " + resultado);
			if (resultado > 0) {
				if (view.getPrimera().isSelected())
					view.getTextArea().setText("La palabra " + view.getPalabra().getText().trim()
							+ ", aparece por primera vez en la linea " + resultado);
				else
					view.getTextArea().setText("La palabra " + view.getPalabra().getText().trim()
							+ ", aparece por última vez en la linea " + resultado);
			} else if (resultado == -1) {
				view.getTextArea().setText("No se ha encontrado la palabra.");
			}

		} catch (FileNotFoundException e) {
			// Error por dejar un campo vacio
			if (view.getFichero1().getText().length() == 0)
				view.showError("Introduce un nombre de fichero en el campo \"Fichero 1\"");
			else
				// Error por un nombre inexistente
				view.showError("No existe ningún fichero con ese nombre.");
		} catch (IOException e) {
			view.showError("Se ha producido un error");
		}

		return 1;
	}

	private void call_copiarFichero() {

		int bytesCopiados = 0;

		try {
			bytesCopiados = model.copiarFichero(view.getFichero1().getText(), view.getFichero2().getText());
			view.getTextArea().setText("Fichero creado: \"" + view.getFichero2().getText() + "\". Se han copiado "
					+ bytesCopiados + " bytes.");
		} catch (IOException e) {
			view.showError("Se ha producido un error");
		}
	}

	private void call_guardarLibro() {
		String titulo, autor, año, editor, paginas;
		boolean guardadoCorrecto = true;
		int opcion = 0;

		// Obtencion de los valores introducidos por el usuario
		titulo = view.getTitulo().getText();
		autor = view.getAutor().getText();
		año = view.getAño().getText();
		editor = view.getEditor().getText();
		paginas = view.getPaginas().getText();

		// Comprobamos si titulo esta vacio
		if (titulo.length() != 0) {
			// Comprobamos si el fichero existe
			if (model.ficheroExiste(new File("libros\\" + titulo))) {
				opcion = sobreescribir();
				if (opcion == 0) { // Elige sobreescribir
					try {
						model.guardar_libro(titulo, titulo, autor, año, editor, paginas);
					} catch (IOException e) {
						guardadoCorrecto = false;
						view.showError("Se ha producido un error");
					}
				}
			}
		} else
			view.showError("El campo \"Titulo\" es obligatorio para crear un nuevo libro");
		view.limpiarCampos();

		if (opcion == 0 && guardadoCorrecto)
			view.getTextArea().setText("El libro \"" + titulo + "\" ha sido guardado correctamente");
	}

	private int sobreescribir() {
		int opcion = -1;
		opcion = JOptionPane.showConfirmDialog(view, "¿Quiere sobreescribir el fichero?", "El fichero ya existe",
				JOptionPane.YES_NO_OPTION);
		return opcion;
	}

	private void call_recuperarLibro() {
		String titulo;
		Libro libro;

		// Obtenemos el titulo del libro
		titulo = view.getTitulo().getText();
		if (titulo.length() != 0) {
			try {
				// Si existe, se muestra la informacion
				libro = model.recuperar_libro(titulo);
				view.getTextArea().setText(libro.toString());
			} catch (FileNotFoundException e) {
				view.showError("No se ha encontrado ninguna coincidencia");
			} catch (ClassNotFoundException | IOException e) {
				view.showError("Se ha producido un error");
			}
		}
	}

	private void call_recuperarTodos() {
		ArrayList<Libro> libros = new ArrayList<Libro>();
		StringBuilder resultado = new StringBuilder();
		String encabezado;

		try {
			libros = model.recuperar_todos();
			// Comprobamos si hemos obtenido algun resultado
			encabezado = libros.size() == 0 ? " NO HAY RESULTADOS" : " " + libros.size() + " RESULTADOS:\n";
			resultado.append(encabezado);
			for (int i = 0; i < libros.size(); i++) {
				resultado.append("  - " + libros.get(i).getTitulo() + "\n");
			}
			view.getTextArea().setText(resultado.toString());
		} catch (Exception e) {
			view.showError("Se ha producido un error");
		}
	}

}