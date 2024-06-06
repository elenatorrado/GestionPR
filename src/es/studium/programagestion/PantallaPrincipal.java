	package es.studium.programagestion;

	import java.awt.Button;
import java.awt.FlowLayout;
	import java.awt.Frame;
	import java.awt.MenuBar;
	import java.awt.MenuItem;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.WindowEvent;
	import java.awt.event.WindowListener;
	import java.awt.Menu;

	public class PantallaPrincipal implements WindowListener, ActionListener {
		Frame pantallaPrincipal = new Frame ("Pantalla Principal");
		MenuBar Menu= new MenuBar();
		Menu mEmpleado= new Menu("Empleados");
		Menu mTickets= new Menu("Tickets");
		Menu mArticulos= new Menu("Artículos");
		Menu mCompras= new Menu("Compras");
		Datos datos=new Datos();
		

		//Añadimos los Items de cada tabla

		//Tabla Empleado

		MenuItem empleadoAlta= new MenuItem("Alta");
		MenuItem empleadoBaja= new MenuItem("Baja");
		MenuItem empleadoModificacion= new MenuItem("Modificación");
		MenuItem empleadoConsulta= new MenuItem("Consulta");

		//Tabla Tickets
		MenuItem ticketsAlta= new MenuItem("Alta");
		MenuItem ticketsBaja= new MenuItem("Baja");
		MenuItem ticketsModificacion= new MenuItem("Modificación");
		MenuItem ticketsConsulta= new MenuItem("Consulta");

		//Tabla Articulos
		MenuItem articulosAlta= new MenuItem("Alta");
		MenuItem articulosBaja= new MenuItem("Baja");
		MenuItem articulosModificacion= new MenuItem("Modificación");
		MenuItem articulosConsulta= new MenuItem("Consulta");

		// Tabla Compras
		MenuItem comprasAlta= new MenuItem("Alta");
		MenuItem comprasBaja= new MenuItem("Baja");
		MenuItem comprasModificacion= new MenuItem("Modificación");
		MenuItem comprasConsulta= new MenuItem("Consulta");
		
		//Boton Ayuda
		Button btnayuda= new Button("Ayuda");
		
	//Distrubuimos los elementos en la ventana
		 public PantallaPrincipal()
		    	{

			//Añadimos los parametros de la ventana
			pantallaPrincipal.setLayout(new FlowLayout());
			pantallaPrincipal.setSize(400,400);
			pantallaPrincipal.setResizable(false);
			pantallaPrincipal.setVisible(true);
			pantallaPrincipal.addWindowListener(this);
			pantallaPrincipal.setMenuBar(Menu);
			pantallaPrincipal.setLocationRelativeTo(null);
			
			//Añadimos los elementos a la barra de menu
			Menu.add(mEmpleado);
			Menu.add(mArticulos);
			Menu.add(mTickets);
			Menu.add(mCompras);
			
			

			mEmpleado.add(empleadoAlta);
			mEmpleado.add(empleadoBaja);
			mEmpleado.add(empleadoModificacion);
			mEmpleado.add(empleadoConsulta);

			
			mArticulos.add(articulosAlta);
			mArticulos.add(articulosBaja);
			mArticulos.add(articulosModificacion);
			mArticulos.add(articulosConsulta);

			mTickets.add(ticketsAlta);
			mTickets.add(ticketsBaja);
			mTickets.add(ticketsModificacion);
			mTickets.add(ticketsConsulta);

			mCompras.add(comprasAlta);
			mCompras.add(comprasBaja);
			mCompras.add(comprasModificacion);
			mCompras.add(comprasConsulta);
			
			
			
			
			//Añadimos el ActionListener Empleado
			empleadoConsulta.addActionListener(this);
			empleadoAlta.addActionListener(this);
			empleadoBaja.addActionListener(this);
			empleadoModificacion.addActionListener(this);
			//Añadimos ActionListenerArticulo
			articulosAlta.addActionListener(this);
			articulosBaja.addActionListener(this);
			articulosModificacion.addActionListener(this);
			articulosConsulta.addActionListener(this);
			//Añadimos el ActionListener Compra
			comprasConsulta.addActionListener(this);
			comprasAlta.addActionListener(this);
			comprasBaja.addActionListener(this);
			comprasModificacion.addActionListener(this);
			//Añadimos el ActionListener Tickets
			ticketsConsulta.addActionListener(this);
			ticketsAlta.addActionListener(this);
			ticketsBaja.addActionListener(this);
			ticketsModificacion.addActionListener(this);
			
			//BOTON AYUDA
			btnayuda.addActionListener(this);
			pantallaPrincipal.add(btnayuda);




		}


		//Creamos el main
		public static void main(String[] args) 
		{
			new PantallaPrincipal();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//Empleado
			if(e.getSource().equals(empleadoConsulta))
	        {
				new ConsultaEmpleado();
	        }
			 if (e.getSource().equals(empleadoAlta))
			{
				new AltaEmpleado();
			}
			 if (e.getSource().equals(empleadoBaja))
				{
					new BajaEmpleado();
				}
			 //Articulos
			 if (e.getSource().equals(articulosAlta))
				{
					new AltaArticulos();
				}
			 if (e.getSource().equals(articulosBaja))
				{
					new BajaArticulos();
				}
			 if (e.getSource().equals(articulosModificacion))
				{
					new ModificacionesArticulo();
				}
			 if (e.getSource().equals(articulosConsulta))
				{
					new ConsultaArticulo();
				}
			//Tickets
			 if (e.getSource().equals(ticketsAlta))
				{
					new AltaTickets();
				}
			 if (e.getSource().equals(ticketsBaja))
				{
					new BajaTickets();
				}
			 if (e.getSource().equals(ticketsModificacion))
				{
					new ModificacionesTickets();
				}
			 if (e.getSource().equals(ticketsConsulta))
				{
					new ConsultaTickets();
				}
			 
			//Compras
			 
			 if (e.getSource().equals(comprasAlta))
				{
					new AltaCompras();
				}
			 if (e.getSource().equals(comprasBaja))
				{
					new BajaCompras();
				}
			 if (e.getSource().equals(comprasModificacion))
				{
					new ModificacionesCompras(); 
				}
			 if (e.getSource().equals(comprasConsulta))
				{
					new ConsultaCompra();
				}
			 //Ayuda
			 if(e.getSource().equals(btnayuda)){
				 datos.ayuda();
				 
			 }
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