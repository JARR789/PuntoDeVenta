/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Pedido;
import vista.panelDashBoardPuntoVenta;

/**
 *
 * @author aurit
 */
public class ControladorDashBoardPuntoVenta {
    //Atributos
    private panelDashBoardPuntoVenta vista;
    private Pedido modelo;

    public ControladorDashBoardPuntoVenta(panelDashBoardPuntoVenta vista, Pedido modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        ManejadordeEventos();
    }

    public panelDashBoardPuntoVenta getVista() {
        return vista;
    }

    public void setVista(panelDashBoardPuntoVenta vista) {
        this.vista = vista;
    }

    public Pedido getModelo() {
        return modelo;
    }

    public void setModelo(Pedido modelo) {
        this.modelo = modelo;
    }
    
   // Manejador de eventos
    private void ManejadordeEventos() {
        this.vista.BuscarIdbtn.addActionListener(e -> buscarProducto());
        this.vista.getTxtCodigo().addActionListener(e -> buscarProducto());
        this.vista.btnAgregarproducto.addActionListener(e -> agregarALaVenta());
        this.vista.BorrarProductobtn.addActionListener(e -> quitarDeLaVenta());
        this.vista.FinalizarVentaBTN.addActionListener(e -> finalizarVenta());
        this.vista.btnCancelarVenta.addActionListener(e -> cancelarVenta());
    }

    // Buscar producto por ID
    private void buscarProducto() {
        try {
            int id = Integer.parseInt(vista.getTxtCodigo().getText());
            Producto p = productoDAO.buscarPorId(id);

            if (p == null) {
                JOptionPane.showMessageDialog(vista, "Producto no encontrado");
            } else {
                vista.txtNombre.setText(p.getNombre());
                vista.txtPrecio.setText(String.valueOf(p.getPrecio()));
                vista.txtStock.setText(String.valueOf(p.getCantidad()));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Código inválido");
        }
    }

    // Agregar producto a la venta
    private void agregarALaVenta() {
        try {
            int id = Integer.parseInt(vista.getTxtCodigo().getText());
            String nombre = vista.txtNombre.getText();
            double precio = Double.parseDouble(vista.txtPrecio.getText());
            int cantidad = Integer.parseInt(vista.txtCantidad.getText());

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(vista, "Cantidad inválida");
                return;
            }

            double importe = precio * cantidad;

            modeloTabla.addRow(new Object[]{id, nombre, precio, cantidad, importe});
            actualizarTotales();
            limpiarCamposProducto();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Datos numéricos inválidos");
        }
    }

    // Quitar producto de la venta
    private void quitarDeLaVenta() {
        int fila = vista.tablaVenta.getSelectedRow();
        if (fila >= 0) {
            modeloTabla.removeRow(fila);
            actualizarTotales();
        } else {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para eliminar");
        }
    }

    // Finalizar venta (mostrar ticket)
    private void finalizarVenta() {
        generarTicket();
        modeloTabla.setRowCount(0); // Vaciar tabla
        actualizarTotales();
    }

    // Cancelar venta
    private void cancelarVenta() {
        modeloTabla.setRowCount(0);
        actualizarTotales();
    }

    // Actualizar subtotal, IVA y total
    public void actualizarTotales() {
        double subtotal = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            subtotal += (double) modeloTabla.getValueAt(i, 4); // Columna importe
        }
        double iva = subtotal * 0.16;
        double total = subtotal + iva;

        vista.lblSubtotal.setText(String.format("%.2f", subtotal));
        vista.lblIva.setText(String.format("%.2f", iva));
        vista.lblTotal.setText(String.format("%.2f", total));
    }

    // Generar ticket
    private void generarTicket() {
        StringBuilder ticket = new StringBuilder();
        ticket.append("===== Ticket de Venta =====\n");
        ticket.append("ID\tProducto\tPrecio\tCant\tImporte\n");

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            ticket.append(modeloTabla.getValueAt(i, 0)).append("\t")
                  .append(modeloTabla.getValueAt(i, 1)).append("\t")
                  .append(modeloTabla.getValueAt(i, 2)).append("\t")
                  .append(modeloTabla.getValueAt(i, 3)).append("\t")
                  .append(modeloTabla.getValueAt(i, 4)).append("\n");
        }

        ticket.append("\nSubtotal: ").append(vista.lblSubtotal.getText());
        ticket.append("\nIVA: ").append(vista.lblIva.getText());
        ticket.append("\nTotal: ").append(vista.lblTotal.getText());
        ticket.append("\n===========================\n");

        JOptionPane.showMessageDialog(vista, ticket.toString());
    }

    // Limpiar campos después de agregar producto
    private void limpiarCamposProducto() {
        vista.getTxtCodigo().setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.txtCantidad.setText("");
    }
    
 
}
        
        
    
    
    

