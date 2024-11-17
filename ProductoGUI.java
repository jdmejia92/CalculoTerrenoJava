import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class ProductoGUI extends JFrame {
    // Atributos del producto
    private String nombre;
    private double precio;
    private int stock;

    // Componentes de la interfaz gráfica
    private JTextField txtNombre, txtPrecio, txtStock, txtDescuento;
    private JTextArea txtResultado;

    // Constructor de la clase para configurar la ventana
    public ProductoGUI() {
        setTitle("Gestión de Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel con un BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Usar GridBagLayout para controlar la posición de los componentes
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Establecer un margen de 20px alrededor del panel

        // Crear un objeto GridBagConstraints para controlar la posición de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4); // Establecer padding entre los componentes

        // Inicializar componentes
        txtNombre = new JTextField(20);
        txtPrecio = new JTextField(20);
        txtStock = new JTextField(20);
        txtDescuento = new JTextField(20);
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false); // El área de texto es solo para mostrar datos

        // Establecer un borde vacío para agregar padding a los campos de texto
        EmptyBorder padding = new EmptyBorder(10, 10, 10, 10); // Padding de 10px en todos los lados
        txtNombre.setBorder(padding);
        txtPrecio.setBorder(padding);
        txtStock.setBorder(padding);
        txtDescuento.setBorder(padding);
        txtResultado.setBorder(padding);

        // Botón de actualización
        JButton btnActualizar = new JButton("Actualizar");

        // Configurar la posición de los componentes con GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre del Producto:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Stock:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtStock, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Descuento (%):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtDescuento, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btnActualizar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Resultado:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Hacer que el área de texto ocupe dos columnas
        panel.add(new JScrollPane(txtResultado), gbc);

        // Agregar el panel principal a la ventana
        add(panel);

        // Configurar acción del botón "Actualizar"
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar que el nombre no esté vacío
                    if (txtNombre.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre del producto es obligatorio.");
                        return; // Si el nombre está vacío, salir del método sin hacer nada más
                    }

                    // Obtener datos de entrada
                    nombre = txtNombre.getText();
                    precio = Double.parseDouble(txtPrecio.getText());
                    stock = Integer.parseInt(txtStock.getText());

                    // Verificar si se debe aplicar descuento
                    double descuento = calcularDescuento();
                    double precioFinal = precio - descuento;

                    // Mostrar los resultados
                    txtResultado.setText("Producto: " + nombre +
                            "\nPrecio Original: S/. " + precio +
                            "\nStock: " + stock +
                            "\nDescuento aplicado: S/. " + descuento +
                            "\nPrecio con Descuento: S/. " + precioFinal);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos en los campos de Precio, Stock y Descuento.");
                }
            }
        });

        setSize(500, 400); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true); // Hacer visible la ventana
    }

    // Método para calcular el descuento basado en el stock y el valor ingresado en el campo de descuento
    private double calcularDescuento() {
        double porcentajeDescuento = 0;

        // Verificar si el campo de descuento tiene un valor
        try {
            // Si el campo de descuento no está vacío, tomar el valor introducido
            if (!txtDescuento.getText().trim().isEmpty()) {
                porcentajeDescuento = Double.parseDouble(txtDescuento.getText());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El descuento debe ser un número válido.");
            porcentajeDescuento = 0; // Si no es un número válido, no aplicar descuento
        }

        // Si el descuento es menor a 0, establecerlo a 0 (sin descuento)
        if (porcentajeDescuento < 0) {
            porcentajeDescuento = 0;
        }

        // Si el stock es insuficiente, no se aplica descuento
        if (stock < 5) {
            porcentajeDescuento = 0; // No se aplica descuento
        }

        return precio * (porcentajeDescuento / 100);
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductoGUI());
    }
}