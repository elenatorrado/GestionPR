package es.studium.programagestion;
import java.awt.*;
import java.awt.event.*;

public class ModificacionesCompras implements WindowListener, ActionListener {
    Frame ventanaModCom = new Frame("Modificaciones Compras");
    Label lblIdCompra = new Label("ID de la Compra");
    Choice chcIdCompra = new Choice();
    Label lblNuevoIdCompra = new Label("Nuevo ID de Compra");
    TextField txtNuevoIdCompra = new TextField(30);
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    Dialog dlgConfirmacion = new Dialog(ventanaModCom, "¿Seguro?", true);
    Label lblMensaje = new Label("¿Seguro que desea modificar esta compra?");
    Button btnSi = new Button("Si");
    Button btnNo = new Button("No");
    Datos datos = new Datos();

    public ModificacionesCompras() {
        // Configurar la ventana principal
        ventanaModCom.setLayout(new FlowLayout());
        ventanaModCom.setSize(300, 200);
        ventanaModCom.setResizable(false);
        ventanaModCom.setVisible(true);
        ventanaModCom.addWindowListener(this);
        ventanaModCom.setLocationRelativeTo(null);

        // Añadir elementos a la ventana principal
        ventanaModCom.add(lblIdCompra);
        ventanaModCom.add(chcIdCompra);
        ventanaModCom.add(lblNuevoIdCompra);
        ventanaModCom.add(txtNuevoIdCompra);
        ventanaModCom.add(btnAceptar);
        ventanaModCom.add(btnCancelar);

        // Añadir listeners a los botones
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Configurar el cuadro de diálogo de confirmación
        dlgConfirmacion.setLayout(new FlowLayout());
        dlgConfirmacion.setSize(300, 150);
        dlgConfirmacion.add(lblMensaje);
        dlgConfirmacion.add(btnSi);
        dlgConfirmacion.add(btnNo);
        dlgConfirmacion.setLocationRelativeTo(null);

        // Añadir listeners a los botones del cuadro de diálogo
        btnSi.addActionListener(this);
        btnNo.addActionListener(this);

        // Llenar la lista de compras
        datos.conectar();
        for (String compra : datos.rellenarChoiceComprasMod()) {
            chcIdCompra.add(compra);
        }
        datos.desconectar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAceptar) {
            dlgConfirmacion.setVisible(true);
        } else if (e.getSource() == btnCancelar) {
            ventanaModCom.setVisible(false);
        } else if (e.getSource() == btnSi) {
            String compraSeleccionada = chcIdCompra.getSelectedItem();
            String nuevoIdCompra = txtNuevoIdCompra.getText();
            if (!compraSeleccionada.equals("Elegir una Compra...") && !nuevoIdCompra.isEmpty()) {
                datos.conectar();
                datos.modificarCompra(compraSeleccionada, nuevoIdCompra);
                datos.desconectar();
                dlgConfirmacion.setVisible(false);
                ventanaModCom.setVisible(false);
            }
        } else if (e.getSource() == btnNo) {
            dlgConfirmacion.setVisible(false);
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
