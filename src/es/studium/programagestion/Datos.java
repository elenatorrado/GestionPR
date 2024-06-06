//DATOS

package es.studium.programagestion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
					contenido = contenido + rs.getString("idEmpleado") + "-" + rs.getString("nombreEmpleado") + "-"
						+ rs.getString("apellidoEmpleado") + "-" + rs.getString("puestoEmpleado") + "\n";
			}
			} catch (SQLException e) {
				System.out.println("Error en la sentencia SQL:" + e.toString());
			}

			return contenido;
		}

	// Consulta Tickets
		public String formatofecha(Date fecha) {
			SimpleDateFormat fechaNueva= new SimpleDateFormat("dd/mm/yyyy");
		return fechaNueva.format(fecha);
		}
		
		public String ConsultaTickets() {
			String contenido = "";
			String sentencia = "SELECT * FROM tickets;";

			try {
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				Date fecha=rs.getDate("fechaTickets");
				String nuevaFecha=formatofecha(fecha);
					contenido += contenido 
						+ rs.getString("idTickets") + "-" 
						+ rs.getString("descripcionTickets") 
						+ "-"
						+nuevaFecha
						+ "-" + rs.getString("importeTickets") 
						+ "-"
						+ "-" + rs.getString("idEmpleadoFK") + "\n";
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
					contenido = contenido 
							+ rs.getString("idUsuario") + "-" 
							+ rs.getString("nombreUsuario") +"-"
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
					contenido = contenido + rs.getString("idCompra") + "-" + rs.getInt("idTicketsFK") + "-"
						+ rs.getInt("idArticulosFK") + "\n";
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
				// Método para rellenar el Choice con los empleados
			    public String[] rellenarChoiceEmpleado() {
			        String elementos = "Elegir un Empleado...*";
			        String sentencia = "SELECT * FROM empleados;";
			        try {
			            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            rs = statement.executeQuery(sentencia);
			            while (rs.next()) {
			                elementos = elementos + rs.getString("idEmpleado") + "-" + rs.getString("nombreEmpleado") + "*";
			            }
			        } catch (SQLException e) {
			            System.err.println(e);
			        }
			        return elementos.split("\\*");
			    }

			    // Método para eliminar un empleado
			    public void eliminarEmpleado(int idEmpleado, String usuario) {
			        String sentencia = "DELETE FROM empleados WHERE idEmpleado = " + idEmpleado + ";";
			        try {
			            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            statement.executeUpdate(sentencia);
			            utilidad.escrituraFicheroLog(usuario, sentencia);
			        } catch (SQLException sqle) {
			            utilidad.escrituraFicheroLog(usuario, "Error en la sentencia SQL " + sqle.getMessage());
			            // System.out.println("Error en la sentencia SQL: " + sqle.toString());
			        }
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
		// Método para eliminar un ticket
	    public void eliminarTicket(int idTicket, String usuario) {
	        String sentencia = "DELETE FROM tickets WHERE idTicket = " + idTicket + ";";
	        try {
	            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            statement.executeUpdate(sentencia);
	            utilidad.escrituraFicheroLog(usuario, sentencia);
	        } catch (SQLException sqle) {
	            utilidad.escrituraFicheroLog(usuario, "Error en la sentencia SQL " + sqle.getMessage());
	            // System.out.println("Error en la sentencia SQL: " + sqle.toString());
	        }
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
				//Metodo Eliminar Usuario 
				public void eliminarUsuario(int idUsuario, String usuario) {
				    String sentencia = "DELETE FROM usuarios WHERE idUsuario = " + idUsuario + ";";
				    try {
				        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				        statement.executeUpdate(sentencia);
				        utilidad.escrituraFicheroLog(usuario, sentencia);
				    } catch (SQLException sqle) {
				        utilidad.escrituraFicheroLog(usuario, "Error en la sentencia SQL " + sqle.getMessage());
				        // System.out.println("Error en la sentencia SQL: " + sqle.toString());
				    }
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
				// Método para eliminar un artículo
				public void eliminarArticulo(String idArticulo, String usuario) {
				    String sentencia = "DELETE FROM articulo WHERE idArticulo = " + idArticulo + ";";

				    try {
				        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				        statement.executeUpdate(sentencia);
				        utilidad.escrituraFicheroLog(usuario, sentencia);
				    } catch (SQLException sqle) {
				        utilidad.escrituraFicheroLog(usuario, "Error en la sentencia SQL " + sqle.getMessage());
				        // System.out.println("Error en la sentencia SQL: " + sqle.toString());
				    }
				}
				
				//Baja Compras
				  // Método para rellenar el Choice con las compras
			    public String[] rellenarChoiceCompras() {
			        String elementos = "Elegir una Compra...*";
			        String sentencia = "SELECT * FROM compras;";
			        try {
			            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            rs = statement.executeQuery(sentencia);
			            while (rs.next()) {
			                elementos = elementos + rs.getString("idCompra") + "-" + rs.getString("idTicketsFK") + "-" + rs.getString("idArticulosFK")+"*";
			            }
			        } catch (SQLException e) {
			            System.err.println(e);
			        }
			        return elementos.split("\\*");
			    }

			    // Método para eliminar una compra
			    public boolean eliminarCompra(int idCompra, String usuario) {
			        String sentencia = "DELETE FROM compras WHERE idCompra = " + idCompra + ";";
			        try {
			            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            statement.executeUpdate(sentencia);
			            utilidad.escrituraFicheroLog(usuario, sentencia);
			        } catch (SQLException sqle) {
			            utilidad.escrituraFicheroLog(usuario, "Error en la sentencia SQL " + sqle.getMessage());
			            // System.out.println("Error en la sentencia SQL: " + sqle.toString());
			        }
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
				
				//USUARIOS
				// Método para obtener la lista de usuarios
			    public String[] RellenarchcUsuarioMod() {
			        String elementos = "Elegir un Usuario...*";
			        String sentencia = "SELECT * FROM usuario;";

			        try {
			            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            rs = statement.executeQuery(sentencia);
			            while (rs.next()) {
			                elementos = elementos + rs.getString("nombreUsuario") + "*";
			            }
			        } catch (SQLException e) {
			            System.out.println(e);
			        }
			        return elementos.split("\\*");
			    }

			    // Método para modificar el usuario
			    public boolean modificarUsuario(String nombreUsuario, String nuevoValor) {
			        boolean modificacionCorrecta = true;
			        String sentencia = "UPDATE usuario SET claveUsuario = SHA2('" + nuevoValor + "', 256) WHERE nombreUsuario = '" + nombreUsuario + "';";
			        System.out.println(sentencia);

			        try {
			            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            statement.executeUpdate(sentencia);
			        } catch (SQLException e) {
			            System.out.println("Error en la sentencia SQL:" + e.toString());
			            modificacionCorrecta = false;
			        }

			        return modificacionCorrecta;
			    }
			
		
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

    return modificacionCorrecta;
}



//Ayuda 
//Método ayuda
public void ayuda()
{
    try
    {
        Runtime.getRuntime().exec("hh.exe manual_usuario.chm");
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }
}
}			
				
				
	
				

