//Baja Articulo
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

public class BajaArticulos implements WindowListener, ActionListener {
	//Creamos los elementos del Baja Empleado
			Frame ventanaBajaArticulo = new Frame ("Alta Articulo");
			Label lblnombreart= new Label("Nombre");
			TextField txtnombreart= new TextField(15);
			Label lbldescripcion= new Label("Descripcion");
			TextField txtdescripcion= new TextField(15);
			Label lblprecio= new Label("Precio");
			TextField txtprecio= new TextField(15);
			Label lblstock= new Label("Stock");
			TextField txtstock= new TextField(15);
			Label lblticket= new Label("Ticket");
			Choice chcarticulo=new Choice();
			Button btnaceptar= new Button("Aceptar");
			Button btncancelar= new Button("Cancelar");
			Dialog dlgSeguro=new Dialog(ventanaBajaArticulo,"¿Seguro@?",true);
			Label lblMensaje=new Label("¿Seguro que quiere borrar?");
			Button btnSi=new Button("Si");
			Button btnNo=new Button("No");
			Datos datos=new Datos();
			//Ditribuimos los elementos en la ventana de Login
			public BajaArticulos()
			{	//Añadimos los elementos a la ventana
				ventanaBajaArticulo.setLayout(new FlowLayout());
				ventanaBajaArticulo.setSize(420,180);
				ventanaBajaArticulo.setResizable(false);
				ventanaBajaArticulo.setVisible(true);
				ventanaBajaArticulo.addWindowListener(this);
				ventanaBajaArticulo.setLocationRelativeTo(null);

				//Añadimos los elementos a la ventana
				ventanaBajaArticulo.add(lblnombreart);
				ventanaBajaArticulo.add(txtnombreart);
				ventanaBajaArticulo.add(lbldescripcion);
				ventanaBajaArticulo.add(txtdescripcion);
				ventanaBajaArticulo.add(lblprecio);
				ventanaBajaArticulo.add(txtprecio);
				ventanaBajaArticulo.add(lblstock);
				ventanaBajaArticulo.add(txtstock);
				ventanaBajaArticulo.add(lblticket);
				ventanaBajaArticulo.add(chcarticulo);
				ventanaBajaArticulo.add(btnaceptar);
				ventanaBajaArticulo.add(btncancelar);
				
				//Añadimos
				ventanaBajaArticulo.add(lblticket);
				datos.conectar();
				String[]elementos=datos.rellenarChoiceTickets();
				for(String choiceRelleno:elementos) {
					chcarticulo.add(choiceRelleno);
				}
				ventanaBajaArticulo.add(chcarticulo);
				ventanaBajaArticulo.add(btnaceptar);

				//Añadimos los botones
				btnaceptar.addActionListener(this);
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

