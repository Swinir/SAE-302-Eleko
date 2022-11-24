package SAE302.eleko;

public class Etat {
    //Définie les trois états possibles du réseau

    private int etat;

    public Etat(int etat) {
        this.etat = etat;
    }

    private String getEtat_Colored() {
        if (etat == 1) {
            return "Green";
        } else if (etat == 2) {
            return "Orange";
        } else if (etat == 3) {
            return "Red";
        }
        else {
            return "Gray";
        }
    }

}
