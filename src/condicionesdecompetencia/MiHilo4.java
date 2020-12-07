
package condicionesdecompetencia;

import static java.lang.Thread.sleep;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


public class MiHilo4 extends Thread{
    private JTextArea area;
    private Cerradura vc, vc2;
    private RecursoCompartido rc;
    private Lock mutex;
    private Interrupcion inter;
    private Dekker dk;
    private boolean pausa=false, vivo=true;
    private int op;
    public MiHilo4(JTextArea area, RecursoCompartido rc, Cerradura vc, Cerradura vc2, Lock mutex, Interrupcion inter, Dekker dk, int op){
        this.area=area;
        this.rc = rc;
        this.vc = vc;
        this.vc2=vc2;
        this.mutex=mutex;
        this.inter=inter;
        this.dk=dk;
        this.op=op;
    }
    public void run(){
        while(true){
            
            switch(op){
                
                case 1: if(!vc2.isVCerradura()){
                        if(!vc.isVCerradura()){
                        vc.setVCerradura(true);
                        rc.setRc(rc.getRc()+1);
                        area.append("Soy hilo 4 :"+rc.getRc()+"\n");
                        vc.setVCerradura(false);
                        try{
                        sleep((long)(Math.random()+1000));
                                synchronized(this){
                                if(pausa)
                                    wait();
                                if(!vivo)join();
                                }
                        }catch(Exception e){}
                        }
                        else
                            try{
                            sleep((long)(Math.random()*1000));
                                
                        }catch(Exception e){}
                        }
                        else
                            try{
                                sleep((long)(Math.random()*1000));
                                
                        }catch(Exception e){}
                        //System.out.println("entra al caso 1");
                        break;
                
                case 2: mutex.lock();
                        rc.setRc(rc.getRc()+1);
                        area.append("Soy hilo 4 :"+rc.getRc()+"\n");
                        mutex.unlock();
                        try{
                        sleep((long)(Math.random()*1000));
                        synchronized(this){
                        if(pausa)
                            wait();
                        if(!vivo)join();
                        }
                        }catch(Exception e){}
                        break;
                case 3: inter.bloquea();
                        rc.setRc(rc.getRc()+1);
                        area.append("Soy hilo 4 :"+rc.getRc()+"\n");
                        inter.desbloquea();
                        try{
                            sleep((long)(Math.random()*1000));
                            synchronized(this){
                        if(pausa)
                            wait();
                        if(!vivo)join();
                        }    
                        }catch(Exception e){}
                        break;
                case 4: dk.entra(3, 0);
                        rc.setRc(rc.getRc()+1);
                        area.append("Soy hilo 4 :"+rc.getRc()+"\n");
                        dk.sale(3, 0);
                        try{
                            sleep((long)(Math.random()*1000));
                        synchronized(this){
                        if(pausa)
                            wait();
                        if(!vivo)join();
                        }    
                        }catch(Exception e){}
                        break;
                default: break;
                }
            }
        }
    
    public void pausar(){
        this.pausa = true;
    }
    
    public void continuar(){
        synchronized(this){
            pausa = false;
            notifyAll();
        }
    }
    
    public void parar(){
        this.vivo=false;
    }
}
