//AltaArticulos

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

public class AltaArticulos implements WindowListener, ActionListener{
	//Creamos los elementos del Alta Empleado
		Frame ventanaAltaArticulo = new Frame ("Alta Articulo");
		Label lblnombreart= new Label("Nombre");
		TextField txtnombreart= new TextField(15);
		Label lbldescripcion= new Label("Descripcion");
		TextField txtdescripcion= new TextField(15);
		Label lblprecio= new Label("Precio");
		TextField txtprecio= new TextField(15);
		Label lblstock= new Label("Stock");
		TextField txtstock= new TextField(15);
		Label lblticket= new Label("Ticket");
		Choice chctickets=new Choice();
		Button btnaceptar= new Button("Aceptar");
		Button btncancelar= new Button("Cancelar");
		Datos datAlta=new Datos();
		Dialog dlgAlta=new Dialog(ventanaAltaArticulo,"Alta",true);
		Label lblAlta=new Label("Alta realizada correctamente");
		//Ditribuimos los elementos en la ventana de Login
		public AltaArticulos()
		{	//Añadimos los elementos a la ventana
			ventanaAltaArticulo.setLayout(new FlowLayout());
			ventanaAltaArticulo.setSize(190,350);
			ventanaAltaArticulo.setResizable(false);
			ventanaAltaArticulo.setVisible(true);
			ventanaAltaArticulo.addWindowListener(this);
			ventanaAltaArticulo.setLocationRelativeTo(null);

			//Añadimos los elementos a la ventana
			ventanaAltaArticulo.add(lblnombreart);
			ventanaAltaArticulo.add(txtnombreart);
			ventanaAltaArticulo.add(lbldescripcion);
			ventanaAltaArticulo.add(txtdescripcion);
			ventanaAltaArticulo.add(lblprecio);
			ventanaAltaArticulo.add(txtprecio);
			ventanaAltaArticulo.add(lblstock);
			ventanaAltaArticulo.add(txtstock);
			ventanaAltaArticulo.add(lblticket);
			ventanaAltaArticulo.add(chctickets);
			ventanaAltaArticulo.add(btnaceptar);
			ventanaAltaArticulo.add(btncancelar);
			//Añadimos los botones
			btnaceptar.addActionListener(this);
			btncancelar.addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//Con esto si pulsamos cancelar se borra el texto escrito
			if(e.getSource().equals(btncancelar))
			{
				txtnombreart.setText("");
				txtdescripcion.setText("");
				txtprecio.setText("");
				txtstock.setText("");
				txtnombreart.requestFocus();
			}
			//Si clicamos en el botón aceptar 
			if(e.getSource().equals(btnaceptar)) {
				//Concetamos la BD con el Objeto previamente creado
				datAlta.conectar();
				//Comprobamos que la sentencia fue ejecutada correctamente y que los datos son correctos
				String articulo=txtnombreart.getText();
				String descripcion=txtdescripcion.getText();
				String precio=txtprecio.getText();
				boolean altaCorrecta=datAlta.empleadoAlta(articulo, descripcion, precio);

				//Añadimos el dialogo a la ventana del Alta
				dlgAlta.setLayout(new FlowLayout());
				dlgAlta.setSize(250,160);
				dlgAlta.setResizable(false);
				dlgAlta.addWindowListener(this);

				//Si el Alta es correcta
				if(altaCorrecta==true) 
				{
					lblAlta.setText("Alta Realizada Correctamente");
					dlgAlta.add(lblAlta);
				}
				//Si no se ha realizado correctamente el Alta
				else
				{
					lblAlta.setText("No se ha realizado el alta correctamente");
				}

				dlgAlta.setVisible(true);
			}
		}
			
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowClosing(WindowEvent e) {
			ventanaAltaArticulo.setVisible(false);	
			
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