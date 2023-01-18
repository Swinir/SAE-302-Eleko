package Data;

public class Etat {
    //Définie les trois états possibles du réseau

    private int pas;
    private int hvalue;

    public Etat(int pas, int hvalue) {
        this.pas = pas; //This is the hour
        this.hvalue = hvalue; //This is the value of the hour
    }
    public Integer getHvalue() {
        return hvalue;
    }

}
