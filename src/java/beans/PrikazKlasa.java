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
public class PrikazKlasa {

    private long idKomp;
    private String nazivKomp;
    private long idPaket;
    private String nazivPaket;

    public long getIdKomp() {
        return idKomp;
    }

    public void setIdKomp(long idKomp) {
        this.idKomp = idKomp;
    }

    public String getNazivKomp() {
        return nazivKomp;
    }

    public void setNazivKomp(String nazivKomp) {
        this.nazivKomp = nazivKomp;
    }

    public long getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(long idPaket) {
        this.idPaket = idPaket;
    }

    public String getNazivPaket() {
        return nazivPaket;
    }

    public void setNazivPaket(String nazivPaket) {
        this.nazivPaket = nazivPaket;
    }

    @Override
    public String toString() {
        return "Naziv: " + this.getNazivKomp() + " Paket: " + this.getNazivPaket();
    }
;
}
