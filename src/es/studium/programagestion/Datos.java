//DATOS

package es.studium.programagestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					contenido = contenido + rs.getString("idEmpleado") + "-" + rs.getString("nombreEmpleado") + "-"
						+ rs.getString("apellidoEmpleado") + "-" + rs.getString("puestoEmpleado") + "\n";
			}
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL:" + e.toString());
			}

			return contenido;
		}

	// Consulta Tickets
		public String ConsultaTickets() {
			String contenido = "";
			String sentencia = "SELECT * FROM tickets;";

			try {
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = statement.executeQuery(sentencia);

			while (rs.next()) {
					contenido = contenido + rs.getString("idTickets") + "-" + rs.getString("descripcionTicket") + "-"
						+ rs.getString("fechaTickets") + "-" + rs.getString("importeTickets") + "-" + "\n";
			}
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL:" + e.toString());
			}

				return contenido;
			}
	
	// Consulta Usuario
		public String ConsultaUsuario() {
			String contenido = "";
			String sentencia = "SELECT * FROM usuario;";

			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = statement.executeQuery(sentencia);

			while (rs.next()) {
					contenido = contenido + rs.getString("idUsuario") + "-" + rs.getString("nombreUsuario") + "-"
						+ rs.getString("claveUsuario") + "\n";
			}
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
					contenido = contenido + rs.getString("idArticulo") + "-" + rs.getString("nombreArticulo") + "-"
						+ rs.getString("descripcionArticulo") + "-" + rs.getFloat("precioArticulo") + "-"
						+ rs.getInt("stockArticulo") + "-" + "\n";
					}
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL:" + e.toString());
			}

			return contenido;
		}

	// Consulta Compra
		public String ConsultaCompra() {
				String contenido = "";
				String sentencia = "SELECT * FROM compra;";

			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = statement.executeQuery(sentencia);

			while (rs.next()) {
					contenido = contenido + rs.getString("idArticulo") + "-" + rs.getInt("idTicketsFK") + "-"
						+ rs.getInt("idArticuloFK") + "\n";
			}
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL:" + e.toString());
			}

			return contenido;
			}
	// ALTAS

	// Alta Empleado
		public boolean empleadoAlta(String nombre, String apellido, String puesto) {
		boolean altaCorrecta = true;
		String sentenciaSQL = "INSERT INTO Empleado VALUES (NULL,'" + nombre + "', '" + apellido + "','" + puesto
				+ "');";
		System.out.println(sentenciaSQL);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentenciaSQL);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
			altaCorrecta = false;
		}

		return altaCorrecta;
		}

		// Alta Tickets
			public boolean ticketsAlta(String descripcionTicket, String fechaTicket, String importe) {
				boolean altaCorrecta = true;
				String sentenciaSQL = "INSERT INTO Empleado VALUES (NULL,'" + descripcionTicket + "', '" + fechaTicket + "','"
						+ importe + "');";
				System.out.println(sentenciaSQL);
		
				try {
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					statement.executeUpdate(sentenciaSQL);
				} catch (SQLException e) {
					System.out.println("Error en la sentencia SQL:" + e.toString());
					altaCorrecta = false;
				}

				return altaCorrecta;
			}
			
			// Alta Articulos
				public boolean articulosAlta(String nombreArticulo , String descripcionArticulo, float importeTickets, int stock) {
				boolean altaCorrecta = true;
				String sentenciaSQL = "INSERT INTO Empleado VALUES (NULL,'" + nombreArticulo  + "', '" + descripcionArticulo + "','"
						+ importeTickets+ "','"+ stock  + "');";
				System.out.println(sentenciaSQL);

				try {
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					statement.executeUpdate(sentenciaSQL);
				} catch (SQLException e) {
					System.out.println("Error en la sentencia SQL:" + e.toString());
					altaCorrecta = false;
				}

				return altaCorrecta;
			}	
			// Alta Usuario
				public boolean usuarioAlta(String nombreUsuario , String passwordUsuario, String password2) {
				boolean altaCorrecta = true;
				String sentenciaSQL = "INSERT INTO Empleado VALUES (NULL,'" + nombreUsuario  + "', '" + passwordUsuario + "');";
				System.out.println(sentenciaSQL);

				try {
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					statement.executeUpdate(sentenciaSQL);
				} catch (SQLException e) {
					System.out.println("Error en la sentencia SQL:" + e.toString());
					altaCorrecta = false;
				}

				return altaCorrecta;
			}			
				
	// BAJA

	// Baja Empleado
		public String[] rellenarChoiceEmpleado() {

		String elementos = "Elegir un Empleado...*";
		String sentencia = "SELECT * FROM empleado;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
					elementos = elementos 
							+ rs.getString("idEmpleado") 
							+ "-" + rs.getString("nombreEmpleado") 
							+ "-"
							+ rs.getString("apellidoEmpleado") 
							+ "-" + rs.getString("puestoEmpleado")
							+ "*";}
			} catch (SQLException e) {
				System.out.println(e);
			}
			return elementos.split("\\*");
		}

	// Baja Tickets
		public String[] rellenarChoiceTickets() {

			String elementos = "Elegir un Ticket...*";
			String sentencia = "SELECT * FROM tickets;";

			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = statement.executeQuery(sentencia);
			while (rs.next()) {
					elementos = elementos
							+ rs.getString("idTickets") 
							+ "-" + rs.getString("descripcionTickets") 
							+ "-"
							+ rs.getString("fechaTickets") 
							+ "-" + rs.getString("importeTickets")
							+ "*";
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
			return elementos.split("\\*");
		
		}
		// Baja Usuario
				public String[] rellenarChoiceUsuario() {

					String elementos = "Elegir un Usuario...*";
					String sentencia = "SELECT * FROM usuario;";

					try {
						statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						rs = statement.executeQuery(sentencia);
					while (rs.next()) {
							elementos = elementos
									+ rs.getString("idUsuario") 
									+ "-" + rs.getString("nombreUsuario") 
									+ "*";
						}
					} catch (SQLException e) {
						System.out.println(e);
					}
					return elementos.split("\\*");
				
				}
				
				// Baja Articulo
				public String[] rellenarChoiceArticulo() {

					String elementos = "Elegir un Articulo...*";
					String sentencia = "SELECT * FROM articulos;";

					try {
						statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						rs = statement.executeQuery(sentencia);
					while (rs.next()) {
							elementos = elementos
									+ rs.getString("idArticulo") 
									+ "-" + rs.getString("nombreArticulo") 
									+ "-"
									+ rs.getString("descripcionArticulo") 
									+ "-" 
									+ rs.getFloat("importeTickets")
									+ rs.getInt("stockArticulo") 
									+ "-" 
									+ rs.getInt("idTicketsFK") 
									+ "*";
						}
					} catch (SQLException e) {
						System.out.println(e);
					}
					return elementos.split("\\*");
				
					
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
}

				

