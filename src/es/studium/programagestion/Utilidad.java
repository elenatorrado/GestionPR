package es.studium.programagestion;


	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class Utilidad {
	    
	    private static final String LOG_FILE_PATH = "programa_gestion.log"; // Ruta del archivo log
	    
	    public static void escrituraFicheroLog(String usuario, String mensaje) {
	        BufferedWriter writer = null;
	        try {
	            writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true));
	            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	            writer.write(timestamp + " - " + usuario + " - " + mensaje);
	            writer.newLine();
	        } catch (IOException e) {
	            System.out.println("Error al escribir en el archivo de log: " + e.toString());
	        } finally {
	            try {
	                if (writer != null) {
	                    writer.close();
	                }
	            } catch (IOException e) {
	                System.out.println("Error al cerrar el BufferedWriter: " + e.toString());
	            }
	        }
	    }
	}


