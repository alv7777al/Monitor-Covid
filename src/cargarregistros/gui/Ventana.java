package cargarregistros.gui;

import cargarregistros.CargarRegistros;
import cargarregistros.gestorficheros.Archivo;
import cargarregistros.gestorficheros.IniciarClasesSintoma;
import cargarsintomas.CargarSintomas;
import monitor.Registro;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class Ventana extends JFrame implements ActionListener {

    private CargarSintomas cargarSintomas;
    //private Sintomas sintomas;
    private Sintomas sintomasDisponibles;
    private Sintomas sintomasPaciente;
    //private IniciarClasesSintoma iniciarClasesSintoma;
    private CargarRegistros cargarRegistros;

    private Archivo archivo;
    private IniciarClasesSintoma iniciarClasesSintoma;


    private JLabel lblTextoFecha;
    private JLabel lblFecha;
    private JTable tablaSintomas;
    private DefaultTableModel model;
    private JButton btnVerResultado;

    public Ventana(Sintomas sintomasDisponibles, Sintomas sintomasPaciente){
        //cargarSintomas= new CargarSintomas();
        this.sintomasDisponibles= sintomasDisponibles;
        this.sintomasPaciente= sintomasPaciente;
        //this.sintomas= new Sintomas();
        //iniciarClasesSintoma= new IniciarClasesSintoma();
        //fecha= new Fecha();
        setSize(1000,900);
        tablaSintomas= new JTable();
        setLocationRelativeTo(null);
        cargarComponentes();
        seleccionarSintomas();

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

    public void cargarComponentes(){
        setLayout(null);
        this.archivo= new Archivo();
        this.iniciarClasesSintoma= new IniciarClasesSintoma();
        lblTextoFecha= new JLabel("Fecha : ");
        lblTextoFecha.setBounds(20,20,50,20);
        lblFecha= new JLabel(new Date().toString());
        lblFecha.setBounds(80,20,500,20);
        cargarTabla();
        btnVerResultado = new JButton("Guardar Registro");
        btnVerResultado.setBounds(80,600,200,20);
        btnVerResultado.addActionListener(this);

        add(lblTextoFecha);
        add(lblFecha);
        add(tablaSintomas);
        add(btnVerResultado);
    }

    private void cargarTabla(){
        tablaSintomas.setBounds(80,50, 800,500);

        model= new DefaultTableModel();
        tablaSintomas.setModel(model);
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Accion");
        //Sintomas sintomas= this.sintomasDisponibles;
        Sintomas sintomas= archivo.leerSintomasArchivo();
        Object [] fila;
        for(Sintoma s : sintomas){
            fila= new Object[2];
            fila[0]= s.toString();
            fila[1]= s.getClass().getSimpleName();

            model.addRow(fila);
        }
        addCheckBox(2,tablaSintomas);
    }

    private void addCheckBox(int col, JTable tabla){
        TableColumn c= tabla.getColumnModel().getColumn(col);
        c.setCellEditor(tabla.getDefaultEditor(Boolean.class));
        c.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
    }

    private void seleccionarSintomas(){
        //sintomas= new Sintomas();
        tablaSintomas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaSintomas.rowAtPoint(evt.getPoint());
                int col = tablaSintomas.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    String nombreSintoma= (String) tablaSintomas.getValueAt(row,0);
                    String nombreClase= (String) tablaSintomas.getValueAt(row,1);
                    //System.out.println(nombreClase + " " + nombreSintoma);
                    Sintoma sintoma= iniciarClasesSintoma.iniciarClaseSintoma(nombreClase, nombreSintoma);
                    System.out.println("Sintoma seleccionado " + sintoma);
                    sintomasPaciente.add(sintoma);
                    //sintomas.add(sintoma);

                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(sintomasPaciente.iterator().hasNext()){
            //cargarRegistros= new CargarRegistros(sintomas);
            Registro regitro = archivo.guardarRegistro(sintomasPaciente);
            JOptionPane.showMessageDialog(null,"Registrado Exitosamente \nTomada en : " + regitro.getFecha(),"Mensaje",1);
            //sintomas= null;
            cargarTabla();
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar al menos un sintoma","Mensaje",0);
        }

        /*cargarRegistros= new CargarRegistros(sintomas);
        Registros registros= cargarRegistros.getRegistros();
        Sintomas sintomasL= registros.peek().getSintomas();
        for(Registro r : registros){
            System.out.print("Fecha : " + r.getFecha());
            for(Sintoma s : sintomasL){
                System.out.print(" Sintoma" + s.toString());
            }
            System.out.println();
        }

         /*if(sintomas.iterator().hasNext()){
             cargarSintomas.cargarSintomas(sintomas);

             Monitor monitor= new Monitor(cargarSintomas);
             monitor.monitorear();
             JOptionPane.showMessageDialog(null,"Diagnostico : " + monitor.getResultado(),"Registro Exitoso",1);
         }else{
             JOptionPane.showMessageDialog(null,"Debe seleccionar al menos un sintoma","Mensaje",0);
         }*/
    }

}
