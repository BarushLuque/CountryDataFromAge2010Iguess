package mx.itson.aguacate.ui;

import mx.itson.aguacate.entities.Paises;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AguaUI extends JFrame {

    private JTable tablaPaises;
    private DefaultTableModel modelo;

    public AguaUI() {
        setTitle("Listado de Países");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear tabla y modelo
        modelo = new DefaultTableModel();
        modelo.addColumn("Code");
        modelo.addColumn("Name");
        modelo.addColumn("Continent");
        modelo.addColumn("Region");
        modelo.addColumn("Surface Area");
        modelo.addColumn("Indep. Year");
        modelo.addColumn("Population");
        modelo.addColumn("Life Exp.");
        modelo.addColumn("GNP");
        modelo.addColumn("Local Name");
        modelo.addColumn("Gov. Form");
        modelo.addColumn("Capital");
        modelo.addColumn("Code2");

        tablaPaises = new JTable(modelo);

        // Scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaPaises);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnCargar = new JButton("Cargar Datos");
        btnCargar.addActionListener(e -> cargarDatos());
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarRegistro());
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarRegistro());
        JButton btnAgregar = new JButton("Agregar Nuevo País");
        btnAgregar.addActionListener(e -> agregarNuevoPais());
        panelBotones.add(btnCargar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAgregar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        List<Paises> paises = Paises.getAll();
        modelo.setRowCount(0); // Limpiar la tabla

        for (Paises p : paises) {
            modelo.addRow(new Object[]{
                p.getCode(),
                p.getName(),
                p.getContinent(),
                p.getRegion(),
                p.getSurfacearea(),
                p.getIndepyear(),
                p.getPopulation(),
                p.getLifeexpectancy(),
                p.getGnp(),
                p.getLocalname(),
                p.getGovernmentform(),
                p.getCapital(),
                p.getCode2()
            });
        }

        JOptionPane.showMessageDialog(this, "Datos cargados: " + paises.size() + " países encontrados.");
    }

    private void agregarNuevoPais() {
        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Campos de entrada
        JTextField txtCode = new JTextField();
        JTextField txtName = new JTextField();
        JComboBox<String> cbContinent = new JComboBox<>(new String[]{
            "Asia", "Europe", "Africa", "Oceania", "North America", "South America"
        });
        JTextField txtRegion = new JTextField();
        JTextField txtSurfaceArea = new JTextField();
        JTextField txtIndepYear = new JTextField();
        JTextField txtPopulation = new JTextField();
        JTextField txtLifeExpectancy = new JTextField();
        JTextField txtGNP = new JTextField();
        JTextField txtLocalName = new JTextField();
        JComboBox<String> cbGovernmentForm = new JComboBox<>(new String[]{
            "Republic", "Monarchy", "Federal Republic", "Constitutional Monarchy"
        });
        JTextField txtCapital = new JTextField();
        JTextField txtCode2 = new JTextField();

        // Añadir campos al panel
        panel.add(new JLabel("Code (Only 3 words):"));
        panel.add(txtCode);
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Continent:"));
        panel.add(cbContinent);
        panel.add(new JLabel("Region:"));
        panel.add(txtRegion);
        panel.add(new JLabel("Surface Area:"));
        panel.add(txtSurfaceArea);
        panel.add(new JLabel("Independence Year:"));
        panel.add(txtIndepYear);
        panel.add(new JLabel("Population :"));
        panel.add(txtPopulation);
        panel.add(new JLabel("Life Expectancy (Less Of 100):"));
        panel.add(txtLifeExpectancy);
        panel.add(new JLabel("GNP:"));
        panel.add(txtGNP);
        panel.add(new JLabel("Local Name:"));
        panel.add(txtLocalName);
        panel.add(new JLabel("Government Form:"));
        panel.add(cbGovernmentForm);
        panel.add(new JLabel("Capital (Only Number code):"));
        panel.add(txtCapital);
        panel.add(new JLabel("Code2 (Only 2 words):"));
        panel.add(txtCode2);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Nuevo País",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Obtener valores
                String code = txtCode.getText();
                String name = txtName.getText();
                String continent = cbContinent.getSelectedItem().toString();
                String region = txtRegion.getText();
                double surfaceArea = Double.parseDouble(txtSurfaceArea.getText());
                int indepYear = Integer.parseInt(txtIndepYear.getText());
                int population = Integer.parseInt(txtPopulation.getText());
                double lifeExpectancy = Double.parseDouble(txtLifeExpectancy.getText());
                double gnp = Double.parseDouble(txtGNP.getText());
                String localName = txtLocalName.getText();
                String governmentForm = cbGovernmentForm.getSelectedItem().toString();
                String capital = txtCapital.getText();
                String code2 = txtCode2.getText();

                // Agregar país a la base de datos
                if (Paises.add(code, name, continent, region, surfaceArea, indepYear,
                        population, lifeExpectancy, gnp, localName, governmentForm, capital, code2)) {
                    JOptionPane.showMessageDialog(this, "País agregado exitosamente.");
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar el país.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, verifica los valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarRegistro() {
        int filaSeleccionada = tablaPaises.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro para editar.");
            return;
        }

        String code = modelo.getValueAt(filaSeleccionada, 0).toString(); // Obtener el código del país
        int poblacionActual = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 6).toString());

        String nuevaPoblacionStr = JOptionPane.showInputDialog(this, "Nueva población:", poblacionActual);

        try {
            int nuevaPoblacion = Integer.parseInt(nuevaPoblacionStr);
            if (nuevaPoblacion < 0) {
                JOptionPane.showMessageDialog(this, "La población no puede ser negativa.");
                return;
            }

            if (Paises.edit(code, nuevaPoblacion)) {
                JOptionPane.showMessageDialog(this, "Registro actualizado exitosamente.");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar la población.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Población inválida. Introduce un número válido.");
        }
    }

    private void eliminarRegistro() {
        int filaSeleccionada = tablaPaises.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro para eliminar.");
            return;
        }

        String code = modelo.getValueAt(filaSeleccionada, 0).toString();
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de eliminar el registro con código: " + code + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (Paises.delete(code)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente.");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AguaUI ventana = new AguaUI();
            ventana.setVisible(true);
        });
    }
}
