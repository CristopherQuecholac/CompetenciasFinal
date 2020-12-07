/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package condicionesdecompetencia;

/**
 *
 * @author LAP2019
 */
class Interrupcion {
    private boolean inter;
    Interrupcion(){
        inter=true;// true=activa la interrupción
    }
    public boolean isInter() {
        return inter;
    }
    public void setInter(boolean inter) {
        this.inter = inter;
    }
    
    public void bloquea(){
        this.setInter(false);
    }
    
    public void desbloquea(){
        this.setInter(true);
    }
}
