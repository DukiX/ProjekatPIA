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
public class Stavka {
    private long idstavka;
    private String naziv;
    private String opis;

    public Stavka() {
    }

    public Stavka(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
    }

    public long getIdstavka() {
        return idstavka;
    }

    public void setIdstavka(long idstavka) {
        this.idstavka = idstavka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
