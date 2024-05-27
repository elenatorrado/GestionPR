//Modificaciones Articulo
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

	public class ModificacionesArticulo implements WindowListener, ActionListener {
		Frame ventanaModArt = new Frame("Modificaciones Articulo");
		Label lblnombreart = new Label("Nombre");
		Choice chcnombreart=new Choice();
		Button btnaceptar = new Button("Aceptar");
		Button btncancelar = new Button("Cancelar");
		Dialog dlgSeguro=new Dialog( ventanaModArt,"¿Seguro@?",true);
		Label lblMensaje=new Label("¿Seguro que quiere modificar la tabla Articulo?");
		Button btnSi=new Button("Si");
		Button btnNo=new Button("No");
		Datos datos=new Datos();
		public ModificacionesArticulo()
		{	//Añadimos los elementos a la ventana
			ventanaModArt.setLayout(new FlowLayout());
			ventanaModArt.setSize(250,160);
			ventanaModArt.setResizable(false);
			ventanaModArt.setVisible(true);
			ventanaModArt.addWindowListener(this);
			ventanaModArt.setLocationRelativeTo(null);

			//Añadimos los elementos a la ventana
			ventanaModArt.add(lblnombreart);
			ventanaModArt.add(chcnombreart);
			ventanaModArt.add(btnaceptar);
			ventanaModArt.add(btncancelar);
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
