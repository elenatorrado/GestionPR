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
import java.sql.Connection;

public class BajaArticulos implements WindowListener, ActionListener {
    private Frame ventanaBajaArticulo;
    private Label lblnombreart;
    private TextField txtnombreart;
    private Button btnaceptar;
    private Button btncancelar;
    private Datos datos;

    public BajaArticulos() {
        datos=new Datos();
        ventanaBajaArticulo = new Frame("Baja Articulo");
        lblnombreart = new Label("Nombre");
        txtnombreart = new TextField(35);
        btnaceptar = new Button("Aceptar");
        btncancelar = new Button("Cancelar");

        ventanaBajaArticulo.setLayout(new FlowLayout());
        ventanaBajaArticulo.setSize(300, 150);
        ventanaBajaArticulo.setResizable(false);
        ventanaBajaArticulo.setVisible(true);
        ventanaBajaArticulo.addWindowListener(this);
        ventanaBajaArticulo.setLocationRelativeTo(null);
        ventanaBajaArticulo.add(lblnombreart);
        ventanaBajaArticulo.add(txtnombreart);
        ventanaBajaArticulo.add(btnaceptar);
        ventanaBajaArticulo.add(btncancelar);

        btnaceptar.addActionListener(this);
        btncancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnaceptar)) {
            String nombreArticulo = txtnombreart.getText();
            boolean eliminacionCorrecta = datos.rellenarChoiceArticuloEliminar(nombreArticulo);
            if (eliminacionCorrecta) {
                System.out.println("El artículo ha sido eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el artículo.");
            }
            ventanaBajaArticulo.setVisible(false);
        } else if (e.getSource().equals(btncancelar)) {
            ventanaBajaArticulo.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
