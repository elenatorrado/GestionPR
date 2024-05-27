


//Altas Tickets
package es.studium.programagestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaTickets<TextArea> implements WindowListener, ActionListener {
	//Creamos los elementos del Alta Empleado
	Frame ventanaAltaTickets = new Frame ("Alta Tickets");
	Label lbldescripcion= new Label("Descripción");
	TextField txtdescripcion= new TextField(15);
	Label lblfecha= new Label("Fecha");
	TextField txtfecha= new TextField(15);
	Label lblimporte= new Label("importe");
	TextField txtimporte= new TextField(15);
	Label lblempleado=new Label("Empleado");
	Choice chcempleado= new Choice();
	Button btnaceptar= new Button("Aceptar");
	Button btncancelar= new Button("Cancelar");
	Datos datAltaTickets=new Datos();
	Dialog dlgAltaTickets=new Dialog(ventanaAltaTickets,"Alta",true);
	Label lblAltaTickets=new Label("Alta realizada correctamente");
	//Ditribuimos los elementos en la ventana de Login
		public AltaTickets()
		{
			ventanaAltaTickets.setLayout(new FlowLayout());
			ventanaAltaTickets.setSize(250,190);
			ventanaAltaTickets.setResizable(false);
			ventanaAltaTickets.setVisible(true);
			ventanaAltaTickets.addWindowListener(this);
			ventanaAltaTickets.setLocationRelativeTo(null);

			//Añadimos los elementos a la ventana
			ventanaAltaTickets.add(lbldescripcion);
			ventanaAltaTickets.add(txtdescripcion);
			ventanaAltaTickets.add(lblfecha);
			ventanaAltaTickets.add(txtfecha);
			ventanaAltaTickets.add(lblimporte);
			ventanaAltaTickets.add(txtimporte);
			ventanaAltaTickets.add(lblempleado);
			ventanaAltaTickets.add(chcempleado);
			ventanaAltaTickets.add(btnaceptar);
			ventanaAltaTickets.add(btncancelar);
			//Añadimos los botones
			btnaceptar.addActionListener(this);
			btncancelar.addActionListener(this);
		}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Con esto si pulsamos cancelar se borra el texto escrito
				
			}
		
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
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