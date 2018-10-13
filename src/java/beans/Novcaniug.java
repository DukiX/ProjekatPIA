/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Dusan
 */
public class Novcaniug {
    private long idnovcaniug;
    private long idkomp;
    private long idpaket;
    private double vrednost;
    private Date datum;
    private long idstatus;
    private int faktura;
    private int uplata;
    private Date datumuplate;
    private Date datumisticanjapaketa;
    
   private String stavke="";

   private boolean istekao=false;

    public boolean isIstekao() {
        return istekao;
    }

    public void setIstekao(boolean istekao) {
        this.istekao = istekao;
    }
   
    public String getStavke() {
        return stavke;
    }

    public void setStavke(String stavke) {
        this.stavke = stavke;
    }
    
    private String naziv;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Novcaniug() {
    }

    public Novcaniug(long idkomp, long idpaket, double vrednost, Date datum, long idstatus, int faktura, int uplata, Date datumuplate, Date datumisticanjapaketa) {
        this.idkomp = idkomp;
        this.idpaket = idpaket;
        this.vrednost = vrednost;
        this.datum = datum;
        this.idstatus = idstatus;
        this.faktura = faktura;
        this.uplata = uplata;
        this.datumuplate = datumuplate;
        this.datumisticanjapaketa = datumisticanjapaketa;
    }


    public long getIdnovcaniug() {
        return idnovcaniug;
    }

    public void setIdnovcaniug(long idnovcaniug) {
        this.idnovcaniug = idnovcaniug;
    }

    public long getIdkomp() {
        return idkomp;
    }

    public void setIdkomp(long idkomp) {
        this.idkomp = idkomp;
    }

    public long getIdpaket() {
        return idpaket;
    }

    public void setIdpaket(long idpaket) {
        this.idpaket = idpaket;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public long getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(long idstatus) {
        this.idstatus = idstatus;
    }

    public int getFaktura() {
        return faktura;
    }

    public void setFaktura(int faktura) {
        this.faktura = faktura;
    }

    public int getUplata() {
        return uplata;
    }

    public void setUplata(int uplata) {
        this.uplata = uplata;
    }

    public Date getDatumuplate() {
        return datumuplate;
    }

    public void setDatumuplate(Date datumuplate) {
        this.datumuplate = datumuplate;
    }

    public Date getDatumisticanjapaketa() {
        return datumisticanjapaketa;
    }

    public void setDatumisticanjapaketa(Date datumisticanjapaketa) {
        this.datumisticanjapaketa = datumisticanjapaketa;
    }
}
