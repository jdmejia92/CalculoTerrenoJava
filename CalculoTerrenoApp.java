import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CalculoTerrenoApp {
    public static void main(String[] args) {
        // Crear una ventana principal con título y tamaño inicial
        JFrame frame = new JFrame("Cálculo de Terreno Rectangular");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Crear panel principal con padding para mantener un diseño limpio
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15)); // Agregar padding alrededor del contenido
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Separación entre los componentes para legibilidad

        // Constante del precio por metro cuadrado
        final double PRECIO_METRO_CUADRADO = 1250.0;

        // Formato para el precio con dos decimales y separación de miles
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Crear componentes de entrada y salida
        JLabel labelLargo = new JLabel("Largo (m):");
        JTextField fieldLargo = new JTextField();
        JLabel labelAncho = new JLabel("Ancho (m):");
        JTextField fieldAncho = new JTextField();
        JButton buttonCalcularArea = new JButton("Área");
        JButton buttonCalcularPerimetro = new JButton("Perímetro");
        JButton buttonCalcularPrecio = new JButton("Precio");
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false); // Evitar modificaciones del área de resultados por el usuario

        // Configurar diseño con GridBagLayout para flexibilidad en la disposición de componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(labelLargo, gbc);

        gbc.gridx = 1;
        mainPanel.add(fieldLargo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(labelAncho, gbc);

        gbc.gridx = 1;
        mainPanel.add(fieldAncho, gbc);

        // Crear un panel separado para los botones con FlowLayout para alinearlos horizontalmente
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(buttonCalcularArea);
        buttonPanel.add(buttonCalcularPerimetro);
        buttonPanel.add(buttonCalcularPrecio);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Hacer que los botones ocupen toda la fila
        mainPanel.add(buttonPanel, gbc);

        // Configurar el área de resultados para que ocupe toda una fila y sea redimensionable
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0; // Permitir que el área crezca dinámicamente si se redimensiona la ventana
        mainPanel.add(new JScrollPane(outputArea), gbc);

        // Agregar el panel principal al frame
        frame.add(mainPanel);

        // Agregar funcionalidad a los botones con validación de datos y manejo de excepciones
        buttonCalcularArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar que las entradas sean numéricas y positivas
                    double largo = Double.parseDouble(fieldLargo.getText());
                    double ancho = Double.parseDouble(fieldAncho.getText());
                    if (largo <= 0 || ancho <= 0) 
                        throw new IllegalArgumentException("Las dimensiones deben ser positivas."); // Validación de entrada
                    // Calcular y mostrar el área
                    double area = largo * ancho;
                    outputArea.setText("Área: " + area + " m²");
                } catch (NumberFormatException ex) {
                    // Manejar errores en la entrada numérica
                    outputArea.setText("Error: Ingresa valores numéricos válidos.");
                } catch (IllegalArgumentException ex) {
                    // Mostrar errores específicos al usuario
                    outputArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        buttonCalcularPerimetro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar que las entradas sean numéricas y positivas
                    double largo = Double.parseDouble(fieldLargo.getText());
                    double ancho = Double.parseDouble(fieldAncho.getText());
                    if (largo <= 0 || ancho <= 0) 
                        throw new IllegalArgumentException("Las dimensiones deben ser positivas."); // Validación de entrada
                    // Calcular y mostrar el perímetro
                    double perimetro = 2 * (largo + ancho);
                    outputArea.setText("Perímetro: " + perimetro + " m");
                } catch (NumberFormatException ex) {
                    // Manejar errores en la entrada numérica
                    outputArea.setText("Error: Ingresa valores numéricos válidos.");
                } catch (IllegalArgumentException ex) {
                    // Mostrar errores específicos al usuario
                    outputArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        buttonCalcularPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar que las entradas sean numéricas y positivas
                    double largo = Double.parseDouble(fieldLargo.getText());
                    double ancho = Double.parseDouble(fieldAncho.getText());
                    if (largo <= 0 || ancho <= 0) 
                        throw new IllegalArgumentException("Las dimensiones deben ser positivas."); // Validación de entrada
                    // Calcular el precio formateado y mostrarlo al usuario
                    double area = largo * ancho;
                    double precio = area * PRECIO_METRO_CUADRADO;
                    outputArea.setText("Precio del terreno: S/. " + df.format(precio)); // Formato para mejor legibilidad
                } catch (NumberFormatException ex) {
                    // Manejar errores en la entrada numérica
                    outputArea.setText("Error: Ingresa valores numéricos válidos.");
                } catch (IllegalArgumentException ex) {
                    // Mostrar errores específicos al usuario
                    outputArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }
};