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
    Frame ventanaBajaCompras = new Frame("Baja Compras");
    Label lblCompras = new Label("Compras");
    Choice chcCompras = new Choice();
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgSeguro = new Dialog(ventanaBajaCompras, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que quiere borrar?");
    Button btnSi = new Button("Si");
    Button btnNo = new Button("No");
    Dialog dlgConfirmacion = new Dialog(ventanaBajaCompras, "Baja Correcta", true);
    Label lblConfirmacion = new Label("La compra ha sido eliminada correctamente.");

    Datos datos = new Datos();

    public BajaCompras() {
        ventanaBajaCompras.setLayout(new FlowLayout());
        ventanaBajaCompras.setSize(250, 100);
        ventanaBajaCompras.setResizable(false);
        ventanaBajaCompras.setVisible(true);
        ventanaBajaCompras.addWindowListener(this);
        ventanaBajaCompras.setLocationRelativeTo(null);

        ventanaBajaCompras.add(lblCompras);
        ventanaBajaCompras.add(chcCompras);
        ventanaBajaCompras.add(btnAceptar);
        ventanaBajaCompras.add(btnCancelar);

        datos.conectar();
        String[] elementosCompras = datos.rellenarChoiceCompraBaja();

        for (String choiceRelleno : elementosCompras) {
            chcCompras.add(choiceRelleno);
        }

        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            dlgSeguro.setLayout(new FlowLayout());
            dlgSeguro.setSize(200, 100);
            dlgSeguro.add(lblMensaje);
            dlgSeguro.add(btnSi);
            dlgSeguro.add(btnNo);
            dlgSeguro.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            ventanaBajaCompras.setVisible(false);
        } else if (e.getSource() == btnSi) {
            String idCompra = chcCompras.getSelectedItem().split("-")[0];
            if (datos.eliminarCompra(Integer.parseInt(idCompra), "usuario_actual")) {
                dlgSeguro.setVisible(false);
                ventanaBajaCompras.setVisible(false);
                dlgConfirmacion.setLayout(new FlowLayout());
                dlgConfirmacion.setSize(300, 100);
                dlgConfirmacion.add(lblConfirmacion);
                dlgConfirmacion.setVisible(true);
            } else {
                System.err.println("Error al eliminar la compra.");
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
        ventanaBajaCompras.setVisible(false);
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
