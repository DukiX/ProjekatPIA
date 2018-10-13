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
public class Paketstavka {
    private long idpaketstavka;
    private long idpaket;
    private long idstavka;

    public Paketstavka() {
    }

    public Paketstavka(long idpaket, long idstavka) {
        this.idpaket = idpaket;
        this.idstavka = idstavka;
    }

    public long getIdpaketstavka() {
        return idpaketstavka;
    }

    public void setIdpaketstavka(long idpaketstavka) {
        this.idpaketstavka = idpaketstavka;
    }

    public long getIdpaket() {
        return idpaket;
    }

    public void setIdpaket(long idpaket) {
        this.idpaket = idpaket;
    }

    public long getIdstavka() {
        return idstavka;
    }

    public void setIdstavka(long idstavka) {
        this.idstavka = idstavka;
    }
}
