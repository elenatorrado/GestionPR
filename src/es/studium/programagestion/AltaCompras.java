

//AltasCompras
package es.studium.programagestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AltaCompras implements WindowListener, ActionListener{
	//Creamos los elementos del Alta Empleado
		Frame ventanaAltaArticulo = new Frame ("Alta Compras");
		Label lblTickets= new Label("Tickets");
		TextField txtnombreart= new TextField(15);
		Label lblArticulos= new Label("Articulos");
		TextField txtdescripcion= new TextField(15);
		Button btnaceptar= new Button("Aceptar");
		Button btncancelar= new Button("Cancelar");
		Datos datAlta=new Datos();
		Dialog dlgAlta=new Dialog(ventanaAltaArticulo,"Altas",true);
		Label lblAlta=new Label("Alta realizada correctamente");
		//Ditribuimos los elementos en la ventana de Login
		public AltaCompras()
		{	//Añadimos los elementos a la ventana
			ventanaAltaArticulo.setLayout(new FlowLayout());
			ventanaAltaArticulo.setSize(250,160);
			ventanaAltaArticulo.setResizable(false);
			ventanaAltaArticulo.setVisible(true);
			ventanaAltaArticulo.addWindowListener(this);
			ventanaAltaArticulo.setLocationRelativeTo(null);

			//Añadimos los elementos a la ventana
			ventanaAltaArticulo.add(lblTickets);
			ventanaAltaArticulo.add(txtnombreart);
			ventanaAltaArticulo.add(lblArticulos);
			ventanaAltaArticulo.add(txtdescripcion);
			ventanaAltaArticulo.add(btnaceptar);
			ventanaAltaArticulo.add(btncancelar);
			//Añadimos los botones
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

