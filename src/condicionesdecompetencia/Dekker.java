/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package condicionesdecompetencia;

public class Dekker {
  private boolean[] flag=new boolean[4];
  private int turno;
  
  public Dekker(int turno){
      boolean[] flag={false, false, false, false};
      this.turno=turno;
  }
    public void entra(int i, int j){
        flag[i]=true;
        while(flag[j]){
            flag[i]=false;
            while(turno!=i);
            flag[i]=true;
        }
    }
    public void sale(int i, int j){
        turno=j;
        flag[i]=false;
    }

}
