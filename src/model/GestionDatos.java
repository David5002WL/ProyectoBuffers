package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionDatos {

	public GestionDatos() {

	}

	//TODO: Implementa una función para abrir ficheros
public BufferedReader abrirFicheros(String ruta) throws FileNotFoundException {
		
		BufferedReader bf = new BufferedReader(new FileReader(ruta));
		
		return bf;
	}
	//TODO: Implementa una función para cerrar ficheros
public void cerrarFichero(BufferedReader bf) throws IOException {
	
	bf.close();
}


public boolean compararContenido (String fichero1, String fichero2) throws FileNotFoundException, IOException{
	//TODO: Implementa la función
	BufferedReader bFichero1 = abrirFicheros(fichero1);
	BufferedReader bFichero2 = abrirFicheros(fichero2);
	File flFichero1 = new File(fichero1);
	File flFichero2 = new File(fichero2);
	
	String lineaF1 = bFichero1.readLine();
	String lineaF2	= bFichero2.readLine();
	
	boolean xd = true;
	
	if(flFichero1.length() != flFichero2.length()) {
		xd = false;
	}else {
	
		do {
			
		if (!lineaF1.equals(lineaF2)) {
			xd = false;
			break;
			}
		
		lineaF1 = bFichero1.readLine();
		lineaF2	= bFichero2.readLine();
		}while(lineaF1 != null || lineaF2 != null) ;
			
		}
	
	return xd;
	}
	
public int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
	
	
	String linea;
	int lineaNum = 0;
	int ultima_aparicion = 0;
	
	// Abrimos el fichero para ver su contenido
    BufferedReader bFichero1;
		bFichero1 = abrirFicheros(fichero1);

    
    // Recorremos el fichero linea a linea buscando coincidencias
    while ((linea = bFichero1.readLine()) != null) {
		lineaNum++;
		if (linea.equalsIgnoreCase(palabra)) {
			if (primera_aparicion) {
				return lineaNum;
			} else {
				// Cada vez que encontramos la palabra, guardamos el numero de linea en una variable
				ultima_aparicion = lineaNum;
			}
		} else 
			ultima_aparicion = -1;	    	
    }
    return ultima_aparicion;
}	

public boolean ficheroExiste(File fichero) {
	return fichero.exists();
}

	public int copiarFichero(String origen, String destino) throws IOException {
	    FileInputStream input;       
	    FileOutputStream output;
	    int bytesLeidos = 0;
	    int bytesCopiados = 0;
	    //byte[] buffer;
	    
	    // Creamos un stream que nos permita leer los bytes del fichero de origen
	    input = new FileInputStream(origen);
	    // Creamos un stream de escritura sobre el fichero de destino
	    output = new FileOutputStream(destino);
	    
	    
	    // Mientras haya datos en el stream de lectura los copiamos en el fichero de destino   
	    while( (bytesLeidos = input.read()) != -1 ){
	    		bytesCopiados = bytesLeidos;
	    		output.write(bytesLeidos);
	    }
	    
	    // Cerramos los streams
	    input.close();
	    output.close();  
	    
	    return bytesCopiados;
	}
	
	public void guardar_libro(String identificador, String titulo, String autor, String año, String editor, String paginas) throws IOException {
		LibroVO libro;
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		
		// Creamos el objeto libro
		libro = new LibroVO(identificador, titulo, autor, año, editor, paginas);
		// Se crea el fichero donde guardaremos el libro
		fos = new FileOutputStream("libros\\"+identificador);
		// Guardamos el objeto libro en el fichero
		salida = new ObjectOutputStream(fos);
		salida.writeObject(libro);
	
        // Cerramos los streams
		if (fos != null)
			fos.close();
		if (salida != null)
			salida.close();
	}
	
	public LibroVO recuperar_libro (String identificador) throws IOException, ClassNotFoundException, FileNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		LibroVO libro = null;

		// Obtenemos el fichero donde esta guardado el libro
		fis = new FileInputStream("libros\\"+identificador);
		// Creamos un stream que nos permite reconstruir el objeto libro guardado anteriormente con el ObjectOutputStream 
		entrada = new ObjectInputStream(fis);
		// Leemos la información del objeto
		libro = (LibroVO) entrada.readObject();
	
        // Cerramos los streams
		if (fis != null)
			fis.close();
		if (entrada != null)
			entrada.close();	
	
		return libro;
	}
	
	public ArrayList<LibroVO> recuperar_todos() throws ClassNotFoundException, FileNotFoundException, IOException {
		File file = new File("libros");
		File[] ficheros;
		ArrayList<LibroVO> libros = new ArrayList<LibroVO>();
				
		// Obtenemos el listado de ficheros y los guardamos en un array
		ficheros = file.listFiles();
		for (int i=0; i<ficheros.length; i++){
			libros.add(recuperar_libro(ficheros[i].getName()));
		}
		return libros;		
	}
}
