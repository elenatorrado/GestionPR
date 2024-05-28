//Baja Compras
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

public class BajaCompras implements WindowListener, ActionListener {
	//Creamos los elementos del Alta Empleado
			Frame ventanaBajaCompras = new Frame ("Baja Compras");
			Label lblTickets= new Label("Tickets");
			Choice chctickets=new Choice();
			Label lblarticulos= new Label("Articulos");
			Choice chcarticulos=new Choice();
			Button btnaceptar= new Button("Aceptar");
			Button btncancelar= new Button("Cancelar");
			Dialog dlgSeguro=new Dialog(ventanaBajaCompras,"¿Seguro@?",true);
			Label lblMensaje=new Label("¿Seguro que quiere borrar?");
			Button btnSi=new Button("Si");
			Button btnNo=new Button("No");
			Datos datos=new Datos();
			//Ditribuimos los elementos en la ventana de Login
			public BajaCompras()
			{	//Añadimos los elementos a la ventana
				ventanaBajaCompras.setLayout(new FlowLayout());
				ventanaBajaCompras.setSize(250,160);
				ventanaBajaCompras.setResizable(false);
				ventanaBajaCompras.setVisible(true);
				ventanaBajaCompras.addWindowListener(this);
				ventanaBajaCompras.setLocationRelativeTo(null);

				//Añadimos los elementos a la ventana
				ventanaBajaCompras.add(lblTickets);
				ventanaBajaCompras.add(lblarticulos);
				ventanaBajaCompras.add(chctickets);
				ventanaBajaCompras.add(chcarticulos);
				ventanaBajaCompras.add(btnaceptar);
				ventanaBajaCompras.add(btncancelar);
				
			
				btnaceptar.addActionListener(this);
				btncancelar.addActionListener(this);
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
