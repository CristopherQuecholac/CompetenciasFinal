package condicionesdecompetencia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CondicionesDeCompetencia extends JFrame{
    private JTextArea area;
    private JScrollPane scroll;
    private JTextArea area2;
    private JScrollPane scroll2;
    private JTextArea area3;
    private JScrollPane scroll3;
    private JTextArea area4;
    private JScrollPane scroll4;
    private JButton boton;
    private JButton pausar;
    private JButton parar;
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    private MiHilo t;
    private MiHilo2 t2;
    private MiHilo3 t3;
    private MiHilo4 t4;
    private RecursoCompartido rc;
    private Cerradura vc, vc2;
    private Lock mutex;
    private boolean pausa=false;
    private Interrupcion inter;
    private Dekker dk;
    
    private int op=1;
    public CondicionesDeCompetencia(){
        setSize(800,400);
        setTitle("Condiciones De Competencia");
        MisComponentes();
    }
    private void MisComponentes(){
        area = new JTextArea();
        scroll = new JScrollPane(area);
        area2 = new JTextArea();
        scroll2 = new JScrollPane(area2);
        area3 = new JTextArea();
        scroll3 = new JScrollPane(area3);
        area4 = new JTextArea();
        scroll4 = new JScrollPane(area4);
        menuBar=new JMenuBar();
        menu1=new JMenu("Algoritmos");
        menuBar.add(menu1);
        menuItem1=new JMenuItem("Cerradura");
	menu1.add(menuItem1);
	menuItem1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                op=1;System.out.println("1");
            }
        });
	menuItem2=new JMenuItem("Interrupciones");
	menu1.add(menuItem2);
	menuItem2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                op=2;System.out.println("2");
            }
        });
        menuItem3=new JMenuItem("mutex");
	menu1.add(menuItem3);
	menuItem3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                op=3;System.out.println("3");
            }
        });
        menuItem4=new JMenuItem("Dekker");
	menu1.add(menuItem4);
	menuItem4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                op=4;System.out.println("4");
            }
        });
        boton = new JButton("Inicio");
        pausar = new JButton("Pausar");
        parar = new JButton("Parar");
        rc = new RecursoCompartido();
        vc = new Cerradura();
        vc2= new Cerradura();
        mutex = new ReentrantLock(true);
        inter = new Interrupcion();
        dk= new Dekker(0);
        
                
        getContentPane().setLayout(null);
        getContentPane().add(menuBar);
        menuBar.setBounds(0, 0, 1200, 20);
        getContentPane().add(scroll);
        scroll.setBounds(150,50,100,300);
        getContentPane().add(scroll2);
        scroll2.setBounds(300,50,100,300); 
        getContentPane().add(scroll3);
        scroll3.setBounds(450,50,100,300);
        getContentPane().add(scroll4);
        scroll4.setBounds(600,50,100,300);
        getContentPane().add(boton);
        boton.setBounds(0,100,100,25);
        boton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                area.setText(null);
                area2.setText(null);
                area3.setText(null);
                area4.setText(null);
                rc.setRc(0);
                t = new MiHilo(area,rc,vc, vc2, mutex, inter, dk, op);
                t2= new MiHilo2(area2,rc,vc, vc2, mutex, inter, dk, op);
                t3 = new MiHilo3(area3,rc,vc, vc2, mutex, inter, dk,op);
                t4 = new MiHilo4(area4,rc,vc, vc2, mutex, inter, dk,op);
                t.start();
                t2.start();
                t3.start();
                t4.start();
                //boton.setEnabled(false);
                pausa=true;
            }
        });
        pausar.setBounds(0, 150, 100, 25);
        getContentPane().add(pausar);
        pausar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(pausa){
                    pausar.setText("Continuar");
                    t.pausar();
                    t2.pausar();
                    t3.pausar();
                    t4.pausar();
                    pausa=false;
                }else{
                    pausar.setText("Pausar");
                    t.continuar();
                    t2.continuar();
                    t3.continuar();
                    t4.continuar();
                    pausa=true;
                }
            }
        });
        
        parar.setBounds(0,200,100,25);
        getContentPane().add(parar);
        parar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t.parar();
                t2.parar();
                t3.parar();
                t4.parar();
                boton.setEnabled(false);
            }
        });
        
        
    }
    public static void main(String[] args) {
        CondicionesDeCompetencia fr = new CondicionesDeCompetencia();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


