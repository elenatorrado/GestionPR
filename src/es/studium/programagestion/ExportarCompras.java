package es.studium.programagestion;

import java.awt.Desktop;
import java.io.File;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class ExportarCompras {

	Datos exportarpdf= new Datos();
	public static final String DEST="Consulta Tickets.pdf";

		public ExportarCompras(String dest)
		{
			try {
				//Indicamos el PDF Writter
					PdfWriter writer  =new PdfWriter(DEST);
				//Indicamos el PDF Document
					PdfDocument document=new PdfDocument(writer);
				//Indicamos el Document
					Document documentar=new Document(document,PageSize.A4.rotate());
				//Establecemos Margenes
					documentar.setMargins(20, 20, 20, 20);
				//Indicamos un PdfFont
					PdfFont fuente=PdfFontFactory.createFont(StandardFonts.HELVETICA);
				//Cabecera Negrita
					PdfFont fuenteNegrita=PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
				//Ancho y Creacion de la Tabla
					Table tabla=new Table(UnitValue.createPercentArray(new float[] {1,2})).useAllAvailableWidth();
				//Conexion a BD
					exportarpdf.conectar();
				//Array para guardar lo que devuelve la consulta
					String[] registro= exportarpdf.ConsultaCompra().split("\n");
				//Añadimos la cabecera
					exportarpdf.process(tabla,"Tickets - Articulos ",fuenteNegrita, true);
				//Añadimos los registros de la tabla empleado
					for(int i=0;i<registro.length;i++) {
						exportarpdf.process(tabla,registro[i],fuente, false);
					}
				//Documento añadimos la tabla
					documentar.add(tabla);
				//Desconectamos de la base de datos
					exportarpdf.desconectar();
					documentar.close();
				//Abrimos el Pdf
					Desktop.getDesktop().open(new File(DEST));
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
}
