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

public class AltaArticulos implements WindowListener, ActionListener {
    // Creamos los elementos del Alta Articulo
    Frame ventanaAltaArticulo = new Frame("Alta Articulo");
    Label lblnombreart = new Label("Nombre");
    TextField txtnombreart = new TextField(15);
    Label lbldescripcion = new Label("Descripcion");
    TextField txtdescripcion = new TextField(25);
    Label lblprecio = new Label("Precio");
    TextField txtprecio = new TextField(25);
    Label lblstock = new Label("Stock");
    TextField txtstock = new TextField(25);
    Label lblticket = new Label("Ticket");
    Choice chctickets = new Choice();
    Button btnaceptar = new Button("Aceptar");
    Button btncancelar = new Button("Cancelar");
    Datos datAlta = new Datos();
    Dialog dlgAlta = new Dialog(ventanaAltaArticulo, "Alta", true);
    Label lblAlta = new Label("Alta realizada correctamente");

    // Distribuimos los elementos en la ventana de Alta
    public AltaArticulos() {
        // Añadimos los elementos a la ventana
        ventanaAltaArticulo.setLayout(new FlowLayout());
        ventanaAltaArticulo.setSize(250, 350);
        ventanaAltaArticulo.setResizable(false);
        ventanaAltaArticulo.setVisible(true);
        ventanaAltaArticulo.addWindowListener(this);
        ventanaAltaArticulo.setLocationRelativeTo(null);

        // Añadimos los elementos a la ventana
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

        // Conectar y rellenar Choice de tickets
        datAlta.conectar();
        String[] elementos = datAlta.rellenarChoiceTicketsAlta();
        for (String choiceRelleno : elementos) {
            chctickets.add(choiceRelleno);
        }

        // Añadimos los botones
        btnaceptar.addActionListener(this);
        btncancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Con esto si pulsamos cancelar se borra el texto escrito
        if (e.getSource().equals(btncancelar)) {
            txtnombreart.setText("");
            txtdescripcion.setText("");
            txtprecio.setText("");
            txtstock.setText("");
            txtnombreart.requestFocus();
        }
        // Si clicamos en el botón aceptar
        if (e.getSource().equals(btnaceptar)) {
            // Conectamos la BD con el Objeto previamente creado
            datAlta.conectar();
            // Comprobamos que la sentencia fue ejecutada correctamente y que los datos son correctos
            String nombre = txtnombreart.getText();
            String descripcion = txtdescripcion.getText();
            float precio = Float.parseFloat(txtprecio.getText());
            int stock = Integer.parseInt(txtstock.getText());

            boolean altaCorrecta = datAlta.articulosAlta(nombre, descripcion, precio, stock);

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
        ventanaAltaArticulo.setVisible(false);
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
