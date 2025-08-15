package controlador;

import modelo.Pedido;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.panelDashBoardPuntoVenta;

public class ControladorPedido {

    private Pedido modelo;
    private panelDashBoardPuntoVenta vista; // reemplaza con el nombre de tu clase de vista

    public ControladorPedido() {
        this.modelo = new Pedido();
        this.vista =new panelDashBoardPuntoVenta();
        manejadorEventos();
        
        LlenarTablaPedido();
        calcularTotales();
    }

    public panelDashBoardPuntoVenta getVista() {
        return vista;
    }

    public void setVista(panelDashBoardPuntoVenta vista) {
        this.vista = vista;
    }
    
    // Método para asociar botones con acciones
    private void manejadorEventos() {
        vista.btnAgregarproducto.addActionListener(e -> Ingresar());
        vista.NuevaVentabtn.addActionListener(e -> vaciarTabla());
        vista.BuscarIdbtn.addActionListener(e -> buscarPorId());
        vista.BorrarProductobtn.addActionListener(e -> eliminarPorCodigo());
        vista.Borrarporidbtn.addActionListener(e -> eliminarPorId());
        vista.ticketbtn.addActionListener(e -> generarTicket());
    }
    private void Ingresar(){
    String codigo = vista.txtCodigo.getText();
        String cantidad = vista.txtCantidad.getText();
        modelo.setCodigoProducto(codigo);
        modelo.setCantidadProducto(cantidad);
        if (modelo.Agregar()) {
            JOptionPane.showMessageDialog(vista, "Producto agregado correctamente del pedido");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al agregar producto del pedido");
        }
        limpiarCajasTexto();
        LlenarTablaPedido();
        calcularTotales();
    }
    private void vaciarTabla() {
        if (modelo.vaciarTabla()) {
            JOptionPane.showMessageDialog(vista, "Tabla vaciada correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al vaciar la tabla");
        }
        limpiarCajasTexto();
        LlenarTablaPedido();
        calcularTotales();
        this.vista.txtCodigo.requestFocus();
    }
    public void LlenarTablaPedido(){
         this.vista.tablaproducto.setModel(obtenerPedido());
     }
    private DefaultTableModel obtenerPedido() {
        // Llamamos al método buscar para obtener la lista
        String encabezadoTabla[]={"ID", "Código", "Nombre", "Cantidad", "Precio", "Precio Final"};
        DefaultTableModel modeloTabla = new DefaultTableModel(encabezadoTabla, 0);
        
        Object[] fila = new Object[modeloTabla.getColumnCount()];
        for (Pedido pedido : this.modelo.buscar()) {
                fila[0] = pedido.getIdProducto();
                fila[1] = pedido.getCodigoProducto();
                fila[2] = pedido.getNombreProducto();
                fila[3] = pedido.getPrecioProducto();
                fila[4] = pedido.getCantidadProducto();
                fila[5] = pedido.getPreFinProducto();
                modeloTabla.addRow(fila);
            }
         return modeloTabla;
     }
    private void buscarPorId() {
        try {
            this.modelo.setIdProducto(Integer.parseInt(this.vista.txtProdId.getText()));
            if (modelo.BuscarPorId(this.modelo.getIdProducto())) {
                this.vista.txtCodigo.setText(this.modelo.getCodigoProducto());
                this.vista.txtCantidad.setText(this.modelo.getCantidadProducto());
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar pedido");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID inválido");
        }
    }
    private void eliminarPorCodigo() {
        String codigo = vista.txtCodigo.getText();
        String cantidad = vista.txtCantidad.getText();
        modelo.setCodigoProducto(codigo);
        modelo.setCantidadProducto(cantidad);
        if (modelo.eliminarCod()) {
            JOptionPane.showMessageDialog(vista, "Producto eliminado correctamente del pedido");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al eliminar producto del pedido");
        }
        limpiarCajasTexto();
        LlenarTablaPedido();
        calcularTotales();
    }

    private void eliminarPorId() {
        try {
            int id = Integer.parseInt(vista.txtProdId.getText());
            modelo.setIdProducto(id);
            if (modelo.eliminarid(id)) {
                JOptionPane.showMessageDialog(vista, "Pedido eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar pedido");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID inválido");
        }
        limpiarCajasTexto();
        LlenarTablaPedido();
        calcularTotales();
    }

    
    //Fin del metodo validar cajas de teexto
    //Metodo para limpiar cajas de texto
    public void limpiarCajasTexto(){
        this.vista.txtCodigo.setText("");
        this.vista.txtCantidad.setText("");
        this.vista.txtProdId.setText("");
     
    }
    private void calcularTotales() {
    try {
        double subtotal = 0;
        double iva = 0;
        double total = 0;

        // Recorremos todas las filas de la tabla
        for (int i = 0; i < vista.tablaproducto.getRowCount(); i++) {
            Object valor = vista.tablaproducto.getValueAt(i, 5); // columna 6 (índice 5)
            if (valor != null) {
                // Convertir a double, dependiendo del tipo de dato de la tabla
                subtotal += Double.parseDouble(valor.toString());
            }
        }

        // Calcular IVA (por ejemplo 16%)
        iva = subtotal * 0.16;
        total = subtotal + iva;

        // Colocar los valores en las cajas de texto
        vista.txtSubtotal.setText(String.format("%.2f", subtotal));
        vista.txtIVA.setText(String.format("%.2f", iva));
        vista.txtTola.setText(String.format("%.2f", total));

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vista, "Error al calcular totales: valor numérico inválido");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(vista, "Error al calcular totales: " + e.getMessage());
    }
}

    public static void main(String[] args) {
        //Crear objeto controlador
        ControladorPedido controlador=new ControladorPedido();
        controlador.vista.setVisible(true);
        controlador.vista.setLocationRelativeTo(null);
    }

private void generarTicket() {
    try {
        StringBuilder ticket = new StringBuilder();

        // Encabezado del ticket
        ticket.append("********** TICKET DE VENTA **********\n");
        ticket.append("no nos hacemos responsables por inconformidades en la compra\n");
        ticket.append("Fecha y Hora: ").append(java.time.LocalDateTime.now()).append("\n");
        ticket.append("-------------------------------------\n");
        ticket.append(String.format("%-5s %-15s %-7s %-7s\n", "Cant", "Producto", "Precio", "Total"));
        ticket.append("-------------------------------------\n");

        // Recorrer la tabla para listar productos
        for (int i = 0; i < vista.tablaproducto.getRowCount(); i++) {
            String nombre = vista.tablaproducto.getValueAt(i, 2).toString(); // Nombre Producto
            String cantidad = vista.tablaproducto.getValueAt(i, 3).toString(); // Cantidad
            String precio = vista.tablaproducto.getValueAt(i, 4).toString(); // Precio Unitario
            String total = vista.tablaproducto.getValueAt(i, 5).toString(); // Total del producto (cantidad * precio)

            ticket.append(String.format("%-5s %-15s %-7s %-7s\n", cantidad, nombre, precio, total));
        }

        ticket.append("-------------------------------------\n");
        // Subtotales
        ticket.append(String.format("Subtotal: %s\n", vista.txtSubtotal.getText()));
        ticket.append(String.format("IVA: %s\n", vista.txtIVA.getText()));
        ticket.append(String.format("TOTAL: %s\n", vista.txtTola.getText()));
        ticket.append("*************************************\n");
        ticket.append("¡Gracias por su compra!\n");

        // Mostrar en un JTextArea o JOptionPane
        JOptionPane.showMessageDialog(vista, ticket.toString(), "Ticket de Venta", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(vista, "Error al generar ticket: " + e.getMessage());
    }
    vaciarTabla();
}
}

