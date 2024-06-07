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

public class ModificacionesTickets implements WindowListener, ActionListener {
    Frame ventanaModTic = new Frame("Modificaciones Tickets");
    Label lblDescripcionTicket = new Label("Ticket");
    Choice chcTickets = new Choice();
    Label lblNuevaDescripcion = new Label("Nueva Descripción");
    TextField txtNuevaDescripcion = new TextField(20);
    Label lblNuevoImporte = new Label("Nuevo Importe");
    TextField txtNuevoImporte = new TextField(30);
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgSeguro = new Dialog(ventanaModTic, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que quiere modificar este ticket?");
    Button btnSi = new Button("Si");
    Button btnNo = new Button("No");
    Datos datos = new Datos();

    public ModificacionesTickets() {
        // Configurar la ventana principal
        ventanaModTic.setLayout(new FlowLayout());
        ventanaModTic.setSize(370, 200);
        ventanaModTic.setResizable(false);
        ventanaModTic.setVisible(true);
        ventanaModTic.addWindowListener(this);
        ventanaModTic.setLocationRelativeTo(null);

        // Añadir elementos a la ventana principal
        ventanaModTic.add(lblDescripcionTicket);
        ventanaModTic.add(chcTickets);
        ventanaModTic.add(lblNuevaDescripcion);
        ventanaModTic.add(txtNuevaDescripcion);
        ventanaModTic.add(lblNuevoImporte);
        ventanaModTic.add(txtNuevoImporte);
        ventanaModTic.add(btnAceptar);
        ventanaModTic.add(btnCancelar);

        // Añadir listeners a los botones
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Configurar el diálogo de confirmación
        dlgSeguro.setLayout(new FlowLayout());
        dlgSeguro.setSize(300, 150);
        dlgSeguro.add(lblMensaje);
        dlgSeguro.add(btnSi);
        dlgSeguro.add(btnNo);
        dlgSeguro.setLocationRelativeTo(null);

        // Añadir listeners a los botones del diálogo
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);

        // Llenar la lista de tickets
        datos.conectar();
        for (String ticket : datos.rellenarChoiceTicketsMod()) {
            chcTickets.add(ticket);
        }
        datos.desconectar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            dlgSeguro.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            ventanaModTic.setVisible(false);
        } else if (e.getSource() == btnSi) {
            String ticketSeleccionado = chcTickets.getSelectedItem();
            String nuevaDescripcion = txtNuevaDescripcion.getText();
            String nuevoImporte = txtNuevoImporte.getText();
            if (!ticketSeleccionado.equals("Elegir un Ticket...") && !nuevaDescripcion.isEmpty() && !nuevoImporte.isEmpty()) {
                datos.conectar();
                datos.modificarTicket(ticketSeleccionado, nuevaDescripcion, nuevoImporte);
                datos.desconectar();
                dlgSeguro.setVisible(false);
                ventanaModTic.setVisible(false);
            }
        } else if (e.getSource() == btnNo) {
            dlgSeguro.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ventanaModTic.setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
