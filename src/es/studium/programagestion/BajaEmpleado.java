
//Baja Empleado

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

public class BajaEmpleado implements WindowListener, ActionListener{
	//Creamos los elementos
	Frame ventanaBaja=new Frame("Baja Empleado");
	Label lblBaja=new Label("Elegir Empleado a Borrar: ");
	Choice choEmpleados=new Choice();
	Button btnAceptar=new Button("Aceptar");
	Button btncancelar= new Button("Cancelar");
	Dialog dlgSeguro=new Dialog(ventanaBaja,"¿Seguro@?",true);
	Label lblMensaje=new Label("¿Seguro que quiere borrar?");
	Button btnSi=new Button("Si");
	Button btnNo=new Button("No");
	
	Datos datos=new Datos();

	public BajaEmpleado() {

		//Añadimos los elementos a la ventana
		ventanaBaja.setLayout(new FlowLayout());
		ventanaBaja.setSize(250,160);
		ventanaBaja.setResizable(false);
		ventanaBaja.setVisible(true);
		ventanaBaja.addWindowListener(this);
		ventanaBaja.setLocationRelativeTo(null);

		//Añadimos
		ventanaBaja.add(lblBaja);
		datos.conectar();
		String[]elementos=datos.rellenarChoiceEmpleado();
		for(String choiceRelleno:elementos) {
			choEmpleados.add(choiceRelleno);
		}
		ventanaBaja.add(choEmpleados);
		ventanaBaja.add(btnAceptar);


		//Añadimos los botones
		btnAceptar.addActionListener(this);
		btncancelar.addActionListener(this);
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