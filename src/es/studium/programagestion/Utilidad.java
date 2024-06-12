package es.studium.programagestion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Utilidad {


    public static void escrituraFicheroLog(String usuario, String mensaje) {
    	LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fecha.format(formateador);

        try 
        {
            FileWriter fw = new FileWriter("fichero.log", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);

            salida.println("usuario"+  "Fecha: [" + fechaFormateada + "]  Sentencia: " + mensaje);
            salida.close();
            bw.close();
            fw.close();
            System.out.println("¡Fichero.log actualizado!");
        }
        catch(IOException i)
        {
            System.out.println("Se produjo un error de Archivo");
        }
       
    }
    }



