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
			Label lblcompras= new Label(" Elige la Compra que quieres borrar");
			Choice chccompras=new Choice();
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
				ventanaBajaCompras.add(lblcompras);
				ventanaBajaCompras.add(chctickets);
				ventanaBajaCompras.add(chccompras);
				ventanaBajaCompras.add(btnaceptar);
				ventanaBajaCompras.add(btncancelar);
				//Añadimos
				ventanaBajaCompras.add(lblTickets);
				datos.conectar();
				String[]elementos=datos.rellenarChoiceTickets();
				for(String choiceRelleno:elementos) {
					chctickets.add(choiceRelleno);
				}
				ventanaBajaCompras.add(btnSi);

				//Añadimos los botones
				btnaceptar.addActionListener(this);
				btncancelar.addActionListener(this);
				btnSi.addActionListener(this);
				btnNo.addActionListener(this);
			}
				
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (e.getSource() == btnaceptar) {
			            // Mostrar diálogo de confirmación
			            dlgSeguro.setLayout(new FlowLayout());
			            dlgSeguro.setSize(200, 100);
			            dlgSeguro.add(lblMensaje);
			            dlgSeguro.add(btnSi);
			            dlgSeguro.add(btnNo);
			            dlgSeguro.setVisible(true);
			        } else if (e.getSource() == btncancelar) {
			            ventanaBajaCompras.setVisible(false);
			        } else if (e.getSource() == btnSi) {
			            // Eliminar la compra seleccionada
			            String idCompra = chccompras.getSelectedItem().split("-")[0];
			            if (datos.eliminarCompra(Integer.parseInt(idCompra), "usuario_actual")) {
			                System.out.println("Compra eliminada con éxito.");
			            } else {
			                System.err.println("Error al eliminar la compra.");
			            }
			            dlgSeguro.setVisible(false);
			            ventanaBajaCompras.setVisible(false);
			        } else if (e.getSource() == btnNo) {
			            dlgSeguro.setVisible(false);
			        }
			    }

				
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaBajaCompras.setVisible(false);
				
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
