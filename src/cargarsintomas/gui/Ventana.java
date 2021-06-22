package cargarsintomas.gui;

import cargarsintomas.CargarSintomas;
import cargarsintomas.gestorficheros.Archivo;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Ventana extends JFrame implements ActionListener {

    //externo


    //componentes
    private JButton btnGuardar;
    private JLabel lblNombreSintoma;
    private JTextField txtNombreSintoma;
    private JComboBox cmbListaSintomas;
    private JTable tablaSintomas;
    private DefaultTableModel model;

    private Sintomas sintomas;
    private Archivo archivo;

    public Ventana(){

        this.archivo= new Archivo();
        tablaSintomas= new JTable();
        setSize(800,900);
        setLocationRelativeTo(null);
        cargarComponentes();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    synchronized(Ventana.this) {
                        Ventana.this.notify();
                    }
                } catch (Exception var5) {
                    System.out.println(var5.getMessage());
                    System.out.println("Error al guardar");
                }

            }
        });

        this.setVisible(true);
        synchronized(this) {
            try {
                this.wait();
            } catch (InterruptedException var8) {
            }

        }

    }

    private void cargarComponentes() {
        setLayout(null);
        lblNombreSintoma= new JLabel("Nombre");
        lblNombreSintoma.setBounds(20,20,100,20);
        txtNombreSintoma= new JTextField();
        txtNombreSintoma.setBounds(100,20,200,20);
        cmbListaSintomas= new JComboBox();
        cmbListaSintomas.setBounds(320,20,200,20);
        btnGuardar= new JButton("Guardar");
        btnGuardar.addActionListener(this);
        btnGuardar.setBounds(530,20,100,20);
        //cargando combo de sintomas despues de crear los componentes
        cargarCmbListaSintomas();
        //cargando tabla de sintomas registrados despues de crear el componente
        cargarTabla();


        //agregando los componentes al frame en general
        add(lblNombreSintoma);
        add(txtNombreSintoma);
        add(cmbListaSintomas);
        add(btnGuardar);
        add(tablaSintomas);
    }

    private void cargarTabla(){
        //cargarSintomas= new CargarSintomas();
        tablaSintomas.setBounds(80,50, 500,500);

        model= new DefaultTableModel();
        tablaSintomas.setModel(model);
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        /*List<Sintoma> listaSintomas= cargarSintomas.mostrarSintomasRegistrados();
        Object [] fila;
        for(int i=0; i< listaSintomas.size(); i++){
            fila= new Object[2];
            fila[0]= listaSintomas.get(i).toString();
            fila[1]= listaSintomas.get(i).getClass().getSimpleName();

            model.addRow(fila);
        }*/
        Sintomas sintomas= this.archivo.leerSintomasArchivo();
        Object [] fila;
        for(Sintoma s : sintomas){
            fila= new Object[2];
            fila[0]= s.toString();
            fila[1]= s.getClass().getSimpleName();

            model.addRow(fila);
        }

        //add(tablaSintomas);
    }

    private void cargarCmbListaSintomas(){
        //cargarSintomas= new CargarSintomas();
        this.archivo.obtenerClasesPaqueteSintomas().forEach(c -> {
            cmbListaSintomas.addItem(c.getSimpleName());
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombreClase= cmbListaSintomas.getSelectedItem().toString();
        String nombreSintoma= txtNombreSintoma.getText().toUpperCase();
        if(this.archivo.guardarSintoma(nombreClase,nombreSintoma) != null){
            JOptionPane.showMessageDialog(null,"Registrado Exitosamente","Mensaje",1);
            txtNombreSintoma.setText("");
            cargarTabla();

        }else{
            JOptionPane.showMessageDialog(null,"Este sintoma ya existe","Mensaje",0);
            txtNombreSintoma.setText("");
        }

        System.out.println(nombreClase + " " + nombreSintoma);
    }
}
