//Consulta Tickets

	package es.studium.programagestion;

	import java.awt.Button;
	import java.awt.FlowLayout;
	import java.awt.Frame;
	import java.awt.TextArea;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.WindowEvent;
	import java.awt.event.WindowListener;

	public class ConsultaTickets implements WindowListener, ActionListener{

		//Creamos los elementos del Consulta Empleado
				Frame ventanaConsultaTickets = new Frame ("Consulta Tickets");
				TextArea consulta=new TextArea(10,30);
				Button btnexportar= new Button("Exportar a PDF");
				Datos datconsultas= new Datos();
		//Ditribuimos los elementos en la ventana de Login
				public ConsultaTickets()
				{	//Añadimos los elementos a la ventana
						ventanaConsultaTickets.setLayout(new FlowLayout());
						ventanaConsultaTickets.setSize(350,260);
						ventanaConsultaTickets.setResizable(false);
						ventanaConsultaTickets.setVisible(true);
						ventanaConsultaTickets.addWindowListener(this);
						ventanaConsultaTickets.setLocationRelativeTo(null);
				
						//Añadimos los elementos a la ventana
							ventanaConsultaTickets.add(consulta);
							ventanaConsultaTickets.add(btnexportar);
						
						//Añadimos los botones
							btnexportar.addActionListener(this);
						//Concetamos el objeto datconsulta//TextArea
							datconsultas.conectar();
							consulta.append("id - Descripcion - Fecha - Importe - Empleado  ");
							consulta.append(datconsultas.ConsultaTickets());
						//Desconectamos la base de datos
							datconsultas.desconectar();
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource().equals(btnexportar)) {
						new ExportarTickets("Consulta Tickets.pdf");
					}
				}
					
				
				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void windowClosing(WindowEvent e) {
					ventanaConsultaTickets.setVisible(false);
					
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
