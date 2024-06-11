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

public class AltaCompras implements WindowListener, ActionListener {
    // Creamos los elementos del Alta Compras
    Frame ventanaAltaCompras = new Frame("Alta Compras");
    Label lblTickets = new Label("Tickets");
    Choice chTickets = new Choice();
    Label lblArticulos = new Label("Articulos");
    Choice chArticulos = new Choice();
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Datos datAlta = new Datos();
    Dialog dlgAlta = new Dialog(ventanaAltaCompras, "Altas", true);
    Label lblAlta = new Label("Alta realizada correctamente");

    // Distribuimos los elementos en la ventana de Alta
    public AltaCompras() {
        // Conectar a la base de datos
        datAlta.conectar();

        // Añadimos los elementos a la ventana
        ventanaAltaCompras.setLayout(new FlowLayout());
        ventanaAltaCompras.setSize(250, 200);
        ventanaAltaCompras.setResizable(false);
        ventanaAltaCompras.setVisible(true);
        ventanaAltaCompras.addWindowListener(this);
        ventanaAltaCompras.setLocationRelativeTo(null);

        // Añadimos los elementos a la ventana
        ventanaAltaCompras.add(lblTickets);
        ventanaAltaCompras.add(chTickets);
        ventanaAltaCompras.add(lblArticulos);
        ventanaAltaCompras.add(chArticulos);
        ventanaAltaCompras.add(btnAceptar);
        ventanaAltaCompras.add(btnCancelar);

        // Rellenamos los Choice con los datos de la base de datos
        String[] tickets = datAlta.rellenarChoiceTicketsAltaCompra();
        for (String ticket : tickets) {
            chTickets.add(ticket);
        }
        String[] articulos = datAlta.rellenarChoiceArticulosAltaCompra();
        for (String articulo : articulos) {
            chArticulos.add(articulo);
        }

        // Añadimos los botones
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Si pulsamos cancelar se borra el texto escrito
        if (e.getSource().equals(btnCancelar)) {
            chTickets.select(0);
            chArticulos.select(0);
            chTickets.requestFocus();
        }
        // Si clicamos en el botón aceptar
        if (e.getSource().equals(btnAceptar)) {
            // Comprobamos que la conexión está establecida
            if (datAlta.connection == null) {
                System.out.println("Error: No hay conexión a la base de datos");
                return;
            }

            // Comprobamos que la sentencia fue ejecutada correctamente y que los datos son correctos
            String idTicket = chTickets.getSelectedItem().split("-")[0]; // Obtener solo el id del ticket
            String idArticulo = chArticulos.getSelectedItem().split("-")[0]; // Obtener solo el id del artículo

            boolean altaCorrecta = datAlta.comprasAlta(null, idTicket, idArticulo);

            // Añadimos el dialogo a la ventana del Alta
            dlgAlta.setLayout(new FlowLayout());
            dlgAlta.setSize(250, 160);
            dlgAlta.setResizable(false);
            dlgAlta.addWindowListener(this);

            // Si el Alta es correcta
            if (altaCorrecta) {
                lblAlta.setText("Alta realizada correctamente");
            } else {
                lblAlta.setText("No se ha realizado el alta correctamente");
            }
            dlgAlta.add(lblAlta);
            dlgAlta.setVisible(true);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // No implementado
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // No implementado
    }
}
