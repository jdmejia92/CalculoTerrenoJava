import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculoPension {
    // Variables globales para la categoría, promedio, pensión y rebaja
    private static String categoria;
    private static double promedio;
    private static double pensionActual;
    private static double descuento;
    private static double nuevaPension;

    public static void main(String[] args) {
        // Crear ventana principal
        JFrame frame = new JFrame("Cálculo de Pensión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Crear panel principal con diseño limpio
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Componentes de entrada
        JLabel labelCategoria = new JLabel("Categoría (A/B):");
        JTextField fieldCategoria = new JTextField(10);
        JLabel labelPromedio = new JLabel("Promedio Ponderado:");
        JTextField fieldPromedio = new JTextField(10);
        JButton buttonCalcular = new JButton("Calcular");
        JTextArea outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);

        // Configuración del diseño
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(labelCategoria, gbc);

        gbc.gridx = 1;
        mainPanel.add(fieldCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(labelPromedio, gbc);

        gbc.gridx = 1;
        mainPanel.add(fieldPromedio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(buttonCalcular, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(new JScrollPane(outputArea), gbc);

        frame.add(mainPanel);

        // Acción del botón calcular
        buttonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar entradas y calcular resultados
                try {
                    categoria = fieldCategoria.getText().toUpperCase();
                    promedio = Double.parseDouble(fieldPromedio.getText());

                    // Validar categoría
                    if (!categoria.equals("A") && !categoria.equals("B")) {
                        throw new IllegalArgumentException("Categoría inválida. Usa 'A' o 'B'.");
                    }

                    // Validar promedio
                    if (promedio < 0 || promedio > 20) {
                        throw new IllegalArgumentException("El promedio debe estar entre 0 y 20.");
                    }

                    // Calcular resultados
                    calcularPension();

                    // Mostrar resultados
                    outputArea.setText(String.format(
                        "Categoría: %s\nPromedio: %.2f\nPensión Actual: S/. %.2f\n" +
                        "Descuento: S/. %.2f\nNueva Pensión: S/. %.2f",
                        categoria, promedio, pensionActual, descuento, nuevaPension));
                } catch (NumberFormatException ex) {
                    outputArea.setText("Error: Ingresa un promedio numérico válido.");
                } catch (IllegalArgumentException ex) {
                    outputArea.setText("Error: " + ex.getMessage());
                }
            }
        });

        // Mostrar ventana
        frame.setVisible(true);
    }

    /**
     * Método que calcula la pensión actual, el descuento y la nueva pensión
     * según la categoría y el promedio.
     */
    private static void calcularPension() {
        // Determinar pensión base según la categoría
        pensionActual = (categoria.equals("A")) ? 800 : 600;

        // Calcular descuento según el promedio
        if (promedio >= 0 && promedio <= 12.99) {
            descuento = 0;
        } else if (promedio >= 13 && promedio <= 15.99) {
            descuento = pensionActual * 0.10;
        } else if (promedio >= 16 && promedio <= 20) {
            descuento = pensionActual * 0.25;
        }

        // Calcular nueva pensión
        nuevaPension = pensionActual - descuento;
    }
};