//DATOS

package es.studium.programagestion;

import java.awt.Choice;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class Datos {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gestionbd";
	String login = "Admin";
	String password = "admin";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;



	Datos() {
	}

	public boolean conectar() {
		boolean conexionCorrecta = true;
		// Cargar el Driver
		try {
			Class.forName(driver);
			System.out.println("Se ha cargado el driver correctamente");
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}

		// Establecer la conexión con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
			System.out.println("Conectado");
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}

	//Comprobacion de Credenciales

	public boolean comprobarCredenciales(String usuario, String password) {
		boolean credencialesCorrectas = true;
		String sentencia = "SELECT * FROM Usuario " + "WHERE nombreUsuario='" + usuario + "' "
				+ "AND passwordUsuario = SHA2('" + password + "', 256);";
		System.out.println(sentencia);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if (!rs.next()) {
				// Credenciales incorrectas
				credencialesCorrectas = false;
			}
		}

		catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());

		}

		return credencialesCorrectas;
	}

	//Desconectar

	public void desconectar() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar " + e.toString());
		}
	}
	// CONSULTAS(COMPLETADAS)

	// Consulta Empleado
	public String ConsultaEmpleado() {
		String contenido = "";
		String sentencia = "SELECT * FROM empleado;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				contenido = contenido + "\n"+ rs.getString("idEmpleado") + "-" + rs.getString("nombreEmpleado") + "-"
						+ rs.getString("apellidoEmpleado") + "-" + rs.getString("puestoEmpleado") + "\n";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}

		return contenido;
	}

	// Consulta Tickets
	//Dar Formato a la fecha
	public String formatofecha(Date fecha) {
		SimpleDateFormat fechaNueva = new SimpleDateFormat("dd/MM/yyyy");
		return fechaNueva.format(fecha);
	}
	//Hacemos la consulta con JOIN
	public String ConsultaTickets() {
		String contenido = "";
		String sentencia = "SELECT t.idTickets, t.descripcionTickets, t.fechaTickets, t.importeTickets, " +
				"e.nombreEmpleado, e.apellidoEmpleado " +
				"FROM tickets t " +
				"JOIN empleado e ON t.idEmpleadoFK = e.idEmpleado;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				Date fecha = rs.getDate("fechaTickets");
				String nuevaFecha = formatofecha(fecha);
				contenido += "\n"
						+ rs.getString("idTickets") + "-"
						+ rs.getString("descripcionTickets") + "-"
						+ nuevaFecha + "-"
						+ rs.getString("importeTickets") + "-"
						+ rs.getString("nombreEmpleado") + " " + rs.getString("apellidoEmpleado") + "\n";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}

		return contenido;
	}



	// Consulta Articulo
	public String ConsultaArticulo() {
		String contenido = "";
		String sentencia = "SELECT * FROM articulos;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				contenido = contenido + "\n"+ rs.getString("idArticulo") + "-" + rs.getString("nombreArticulo") + "-"
						+ rs.getString("descripcionArticulo") + "-" + rs.getFloat("precioArticulo") + "-"
						+ rs.getInt("stockArticulo") + "-" + "\n";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}

		return contenido;
	}

	// Consulta Compra
	public String ConsultaCompra() {
		String contenido = "";
		String sentencia = "SELECT c.idCompra, t.descripcionTickets, a.nombreArticulo " +
				"FROM compra c " +
				"JOIN tickets t ON c.idTicketsFK = t.idTickets " +
				"JOIN articulos a ON c.idArticulosFK = a.idArticulo;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				contenido += "\n" + rs.getString("idCompra") + "-" 
						+ rs.getString("descripcionTickets") + "-" 
						+ rs.getString("nombreArticulo") + "\n";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}

		return contenido;
	}

	// ALTAS

	//  Empleado
	public boolean empleadoAlta(String nombre, String apellido, String puesto) {
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO Empleado VALUES (NULL,'" + nombre + "', '" + apellido + "','" + puesto
				+ "');";
		System.out.println(sentenciaSQL);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);
		} 
		catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentenciaSQL);

		return altaCorrecta;
	}

	// Tickets
	// Método para dar de alta un ticket
	public boolean ticketsAlta(String descripcion, String fecha, String importe, String idEmpleado) {
		boolean altaCorrecta = true;

		// Convertir fecha al formato MySQL
		String fechaMySQL = fecha.substring(6, 10) + "-" + fecha.substring(3, 5) + "-" + fecha.substring(0, 2);

		String sentenciaSQL = "INSERT INTO tickets VALUES (NULL, '" + descripcion + "', '" + fechaMySQL + "', " + importe + ", " + idEmpleado + ");";
		System.out.println(sentenciaSQL);

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sentenciaSQL);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentenciaSQL);

		return altaCorrecta;
	}
	// Método para rellenar el Choice con los IDs de los empleados
	public String[] rellenarChoiceEmpleados() {
		String elementos = "Elegir un Empleado...*";
		String sentencia = "SELECT idEmpleado FROM empleado;";

		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				elementos += rs.getString("idEmpleado") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.err.println(e);
		}

		return elementos.split("\\*");
	}


	// Articulos
	// Método para dar de alta un artículo
	public boolean articulosAlta(String nombreArticulo, String descripcionArticulo, float precioArticulo, int stockArticulo, int idTicketsFK) {
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO articulos VALUES (NULL, '" + nombreArticulo + "', '"+ descripcionArticulo  + "', " + precioArticulo+", " +stockArticulo + ", "+ idTicketsFK +  ");";
		System.out.println(sentenciaSQL);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentenciaSQL);
		return altaCorrecta;
	}

	// Método para rellenar el Choice con los tickets
	public String[] rellenarChoiceTicketsAlta() {
		String elementos = "Elegir un Ticket...*";
		String sentencia = "SELECT idTickets FROM tickets;";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos += rs.getString("idTickets") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.err.println(e);
		}
		return elementos.split("\\*");
	}

	//COMPRAS


	// Método para dar de alta una compra
	public boolean comprasAlta(String idCompra, String idTicketsFK, String idArticuloFK) {
		boolean altaCorrecta = true;

		String sentenciaSQL = "INSERT INTO compra VALUES (" + idCompra + ", " + idTicketsFK + ", " + idArticuloFK + ");";
		System.out.println(sentenciaSQL);

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sentenciaSQL);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentenciaSQL);

		return altaCorrecta;
	}

	// Método para rellenar el Choice con los tickets
	public String[] rellenarChoiceTicketsAltaCompra() {
		String elementos = "Elegir un Ticket...*";
		String sentencia = "SELECT idTickets FROM tickets;";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos += rs.getString("idTickets") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.err.println(e);
		}
		return elementos.split("\\*");
	}
	// Método para rellenar el Choice con los tickets
	public String[] rellenarChoiceArticuloAltaCompra() {
		String elementos = "Elegir un Articulo...*";
		String sentencia = "SELECT idArticulo FROM articulo;";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos += rs.getString("idArticulo") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.err.println(e);
		}
		return elementos.split("\\*");
	}

	// Método para rellenar el Choice con los artículos
	public String[] rellenarChoiceArticulosAltaCompra() {
		String elementos = "Elegir un Artículo...*";
		String sentencia = "SELECT idArticulo FROM articulos;";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos += rs.getString("idArticulo")  + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.err.println(e);
		}
		return elementos.split("\\*");
	}


	// BAJA
	//Empleado
	public String[] rellenarChoiceEmpleado() {
		String elementosCadena = "Elegir un empleado...*";
		String sentencia = "SELECT idEmpleado, CONCAT(nombreEmpleado, ' ', apellidoEmpleado) AS nombre_completo FROM empleado;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				elementosCadena += rs.getString("idEmpleado") + "-" + rs.getString("nombre_completo") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
		}
		return elementosCadena.split("\\*");
	}

	public boolean eliminarEmpleado(int idEmpleado, String usuario) {
		boolean eliminado = false;
		String sentencia = "DELETE FROM empleado WHERE idEmpleado = " + idEmpleado + ";";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			eliminado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Utilidad.escrituraFicheroLog("Admin",sentencia);
		return eliminado;
	}





	// Baja Tickets
	// Método para rellenar el Choice con los tickets
	public void rellenarChoiceTickets(Choice choice) {
		String sentencia = "SELECT idTickets, descripcionTickets FROM tickets";
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sentencia);

			// Limpiamos el Choice antes de añadir nuevos elementos
			choice.removeAll();

			// Añadimos un elemento predeterminado
			choice.add("Elegir un Ticket...");

			// Añadimos los tickets al Choice
			while (rs.next()) {
				int idTicket = rs.getInt("idTickets");
				String descripcion = rs.getString("descripcionTickets");
				choice.add(idTicket + "-" + descripcion);
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.err.println("Error al obtener los tickets: " + e.getMessage());
		}
	}

	// Método para eliminar un ticket
	public boolean eliminarTicket(int idTickets, String usuario) {
		boolean eliminado = false;
		try {
			// Eliminar los artículos relacionados con el ticket
			String sentencia = "DELETE FROM articulos WHERE idTicketsFK = ?";
			PreparedStatement psEliminarArticulos = connection.prepareStatement(sentencia);
			psEliminarArticulos.setInt(1, idTickets);
			psEliminarArticulos.executeUpdate();

			// Eliminar el ticket
			String eliminarTicket = "DELETE FROM tickets WHERE idTickets = ?";
			PreparedStatement psEliminarTicket = connection.prepareStatement(eliminarTicket);
			psEliminarTicket.setInt(1, idTickets);
			psEliminarTicket.executeUpdate();

			eliminado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  Utilidad.escrituraFicheroLog(usuario, "Eliminar ticket con idTickets: " + idTickets);
		return eliminado;
	}




	// Baja Articulo
	//Rellenar choice
	public void rellenarChoiceArticuloEliminar(Choice choice) {
		String sentencia = "SELECT idArticulo, nombreArticulo FROM articulos;";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);


			// Añadimos un elemento predeterminado
			choice.add("Elegir un Artículo...");

			// Añadimos los artículos al Choice
			while (rs.next()) {
				int idArticulo = rs.getInt("idArticulo");
				String nombreArticulo = rs.getString("nombreArticulo");
				choice.add(idArticulo + "-" + nombreArticulo);
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
			 
		} catch (SQLException e) {
			System.err.println("Error al obtener los artículos: " + e.getMessage());
		}
	}
	//Eliminar Articulo
	public boolean eliminarArticulo(int idArticulo) {
		boolean eliminado = false;
		String eliminarCompras = "DELETE FROM compra WHERE idArticulosFK = ?";
		String eliminarArticulo = "DELETE FROM articulos WHERE idArticulo = ?";
		try {
			// Eliminar compras que dependen del artículo
			PreparedStatement psEliminarCompras = connection.prepareStatement(eliminarCompras);
			psEliminarCompras.setInt(1, idArticulo);
			psEliminarCompras.executeUpdate();

			// Eliminar el artículo
			PreparedStatement psEliminarArticulo = connection.prepareStatement(eliminarArticulo);
			psEliminarArticulo.setInt(1, idArticulo);
			psEliminarArticulo.executeUpdate();

			eliminado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 Utilidad.escrituraFicheroLog("Admin", "Eliminar artículo con idArticulo: " + idArticulo);
		return eliminado;
	}


	//Baja Compras


	// Método para rellenar el Choice con las compras
	public String[] rellenarChoiceCompraBaja() {
		String elementos = "Elegir una Compra...*";
		String sentencia = "SELECT idCompra FROM compra;";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos += rs.getString("idCompra") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.err.println("Error al obtener las compras: " + e.getMessage());
		}
		
		return elementos.split("\\*");
	}

	// Método para eliminar una compra
	public boolean eliminarCompra(int idCompra, String usuario) {
		boolean eliminado = false;
		String sentencia = "DELETE FROM compra WHERE idCompra = " + idCompra + ";";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sentencia);
			eliminado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Utilidad.escrituraFicheroLog("Admin",sentencia);
		return eliminado;
	}

	//PDF
	public void process(Table tabla,String registro,PdfFont fuente, boolean Cabecera)
	{
		StringTokenizer tokenizer=new StringTokenizer(registro,"-");
		while(tokenizer.hasMoreTokens()) {
			if(Cabecera) {
				tabla.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(fuente)));
			}
			else {
				tabla.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(fuente)));
			}
			
		}


	}
	//MODIFICACION

	//TICKETS
	//Método para obtener la lista de tickets
	public String[] rellenarChoiceTicketsMod() {
		String elementos = "Elegir un Ticket...*";
		String sentencia = "SELECT * FROM tickets;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos = elementos 
						+ rs.getString("idTickets") + "-" 
						+ rs.getString("descripcionTickets") + "-" 
						+ rs.getString("fechaTickets") + "-" 
						+ rs.getString("importeTickets") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return elementos.split("\\*");
	}

	// Método para modificar el ticket
	public boolean modificarTicket(String ticketSeleccionado, String nuevaDescripcion, String nuevoImporte) {
		boolean modificacionCorrecta = true;
		String idTicket = ticketSeleccionado.split("-")[0];
		String sentencia = "UPDATE tickets SET descripcionTickets = '" + nuevaDescripcion + "', importeTickets = " + nuevoImporte + " WHERE idTickets = " + idTicket + ";";
		System.out.println(sentencia);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			modificacionCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentencia);

		return modificacionCorrecta;
	}
	//ARTICULOS
	public String[] rellenarChoiceArticulosMod() {
		String elementos = "Elegir un Artículo...*";
		String sentencia = "SELECT * FROM articulos;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos = elementos 
						+ rs.getString("idArticulo") + "-" 
						+ rs.getString("nombreArticulo") + "-" 
						+ rs.getString("descripcionArticulo") + "-" 
						+ rs.getString("precioArticulo") + "-" 
						+ rs.getString("stockArticulo") + "*";
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return elementos.split("\\*");
	}

	public boolean modificarArticulo(String articuloSeleccionado, String nuevoNombre, String nuevaDescripcion, float nuevoPrecio, int nuevoStock) {
		boolean modificacionCorrecta = true;
		String idArticulo = articuloSeleccionado.split("-")[0];
		String sentencia = "UPDATE articulos SET nombreArticulo = '" + nuevoNombre + "', descripcionArticulo = '" + nuevaDescripcion + "', precioArticulo = " + nuevoPrecio + ", stockArticulo = " + nuevoStock + " WHERE idArticulo = " + idArticulo + ";";
		System.out.println(sentencia);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			modificacionCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentencia);
		return modificacionCorrecta;
	}
	//EMPLEADOS
	//Rellenamos el choice
	public String[] rellenarChoiceEmpleadosMod() {
		String elementos = "Elegir un Empleado...*";
		String sentencia = "SELECT * FROM empleado;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos = elementos 
						+ rs.getString("idEmpleado") + "-" 
						+ rs.getString("nombreEmpleado") + "-" 
						+ rs.getString("apellidoEmpleado") + "-" 
						+ rs.getString("puestoEmpleado") + "*";
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		Utilidad.escrituraFicheroLog("Admin",sentencia);
		return elementos.split("\\*");
	}

	//Modificamos Empleado
	public boolean modificarEmpleado(String empleadoSeleccionado, String nuevoNombre, String nuevoApellido, String nuevoPuesto) {
		boolean modificacionCorrecta = true;
		String idEmpleado = empleadoSeleccionado.split("-")[0];
		String sentencia = "UPDATE empleado SET nombreEmpleado = '" + nuevoNombre + "', apellidoEmpleado = '" + nuevoApellido + "', puestoEmpleado = '" + nuevoPuesto + "' WHERE idEmpleado = " + idEmpleado + ";";
		System.out.println(sentencia);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			modificacionCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentencia);

		return modificacionCorrecta;
	}

	//Compras
	// Método para rellenar la lista de compras en la ventana de modificaciones
	public String[] rellenarChoiceComprasMod() {
		String elementos = "Elegir una Compra...*";
		String sentencia = "SELECT * FROM compra;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				elementos = elementos
						+ rs.getString("idCompra") + "-"+ rs.getString("idTicketsFK") + "-"+ rs.getString("idArticulosFK")+"*";
				// Aquí puedes añadir las otras columnas si las necesitas para la lista de compras
			}
			Utilidad.escrituraFicheroLog("Admin",sentencia);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return elementos.split("\\*");
	}

	// Método para modificar una compra
	public boolean modificarCompra(String compraSeleccionada, String nuevoIdCompra) {
		boolean modificacionCorrecta = true;
		String idCompra = compraSeleccionada.split("-")[0];
		String sentencia = "UPDATE compra SET idCompra = '" + nuevoIdCompra + "' WHERE idCompra = " + idCompra + ";";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		}
		catch (SQLException e) {
			System.out.println("Error en la sentencia SQL: " + e.toString());
			modificacionCorrecta = false;
		}
		Utilidad.escrituraFicheroLog("Admin",sentencia);
		return modificacionCorrecta;
	}

	//Ayuda 
	//Método ayuda
	public void ayuda()
	{
		try
		{
			Runtime.getRuntime().exec("hh.exe Ayuda.chm");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
}			





