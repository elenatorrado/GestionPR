//Consulta Articulos

	package es.studium.programagestion;

	import java.awt.Button;
	import java.awt.FlowLayout;
	import java.awt.Frame;
	import java.awt.TextArea;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.WindowEvent;
	import java.awt.event.WindowListener;

	public class ConsultaArticulo implements WindowListener, ActionListener{

		//Creamos los elementos del Consulta Articulo
				Frame ventana = new Frame ("Consulta Articulo");
				TextArea consulta=new TextArea(10,40);
				Button btnexportar= new Button("Exportar a PDF");
				Datos datconsultas= new Datos();
		//Ditribuimos los elementos en la ventana de Login
				public ConsultaArticulo()
				{	//Añadimos los elementos a la ventana
						ventana.setLayout(new FlowLayout());
						ventana.setSize(350,260);
						ventana.setResizable(false);
						ventana.setVisible(true);
						ventana.addWindowListener(this);
						ventana.setLocationRelativeTo(null);
				
						//Añadimos los elementos a la ventana
							ventana.add(consulta);
							ventana.add(btnexportar);
						
						//Añadimos los botones
							btnexportar.addActionListener(this);
						//Concetamos el objeto datconsulta//TextArea
							datconsultas.conectar();
							consulta.append("id - Nombre - Fecha - Stock - id Tickets");
							consulta.append(datconsultas.ConsultaTickets());
						//Desconectamos la base de datos
							datconsultas.desconectar();
				}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(btnexportar)) {
				new ExportarArticulo("Consulta Articulo.pdf");
			}
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			
			ventana.setVisible(false);
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
