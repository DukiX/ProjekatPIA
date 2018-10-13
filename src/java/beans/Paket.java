/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Dusan
 */
public class Paket {
    private long idpaket;
    private String naziv;
    private double vrednost;
    private int trajanje;
    private int maxbrojgodisnje;

    public Paket() {
    }

    public Paket(String naziv, double vrednost, int trajanje, int maxbrojgodisnje) {
        this.naziv = naziv;
        this.vrednost = vrednost;
        this.trajanje = trajanje;
        this.maxbrojgodisnje = maxbrojgodisnje;
    }

    public long getIdpaket() {
        return idpaket;
    }

    public void setIdpaket(long idpaket) {
        this.idpaket = idpaket;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getMaxbrojgodisnje() {
        return maxbrojgodisnje;
    }

    public void setMaxbrojgodisnje(int maxbrojgodisnje) {
        this.maxbrojgodisnje = maxbrojgodisnje;
    }
    
    private String stavke;

    public String getStavke() {
        return stavke;
    }

    public void setStavke(String stavke) {
        this.stavke = stavke;
    }
}
