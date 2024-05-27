


//Baja Tickets


	package es.studium.programagestion;

	import java.awt.Button;
	import java.awt.Choice;
	import java.awt.Dialog;
	import java.awt.FlowLayout;
	import java.awt.Frame;
	import java.awt.Label;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.WindowEvent;
	import java.awt.event.WindowListener;

	public class BajaTickets implements WindowListener, ActionListener{
		Frame ventanaBajaTickets=new Frame("Baja Tickets");
		Label lblBajaTickets=new Label("Elegir Ticket a Borrar: ");
		Choice chcTickets=new Choice();
		Button btnAceptar=new Button("Aceptar");
		Dialog dlgSeguro=new Dialog(ventanaBajaTickets,"¿Seguro@?",true);
		Label lblMensaje=new Label("¿Seguro que quiere borrar?");
		Button btnSi=new Button("Si");
		Button btnNo=new Button("No");
		Datos datos=new Datos();

		public BajaTickets() {

			//Añadimos los elementos a la ventana
			ventanaBajaTickets.setLayout(new FlowLayout());
			ventanaBajaTickets.setSize(250,160);
			ventanaBajaTickets.setResizable(false);
			ventanaBajaTickets.setVisible(true);
			ventanaBajaTickets.addWindowListener(this);
			ventanaBajaTickets.setLocationRelativeTo(null);

			//Añadimos
			ventanaBajaTickets.add(lblBajaTickets);
			datos.conectar();
			String[]elementos=datos.rellenarChoiceTickets();
			for(String choiceRelleno:elementos) {
				chcTickets.add(choiceRelleno);
			}
			ventanaBajaTickets.add(btnAceptar);

			//Añadimos los botones
			btnAceptar.addActionListener(this);
			btnSi.addActionListener(this);
			btnNo.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub

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
