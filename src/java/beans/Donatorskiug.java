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
public class Donatorskiug {
    private long iddonatorskiug;
    private long idkomp;
    private long idpaket;
    private double procvrednost;
    private String opis;
    private int kolicina;
    private Date datumugovora;
    private long idstatus;
    private Date datumisporuke;
    private String komentar;
    private Date datumisticanjapaketa;
    
    private boolean istekao=false;

    public boolean isIstekao() {
        return istekao;
    }

    public void setIstekao(boolean istekao) {
        this.istekao = istekao;
    }

    private String naziv;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
     
   private String stavke="";

    public String getStavke() {
        return stavke;
    }

    public void setStavke(String stavke) {
        this.stavke = stavke;
    }
    
    public Donatorskiug() {
    }

    public Donatorskiug(long idkomp, long idpaket, double procvrednost, String opis, int kolicina, Date datumugovora, long idstatus, Date datumisporuke, String komentar, Date datumisticanjapaketa) {
        this.idkomp = idkomp;
        this.idpaket = idpaket;
        this.procvrednost = procvrednost;
        this.opis = opis;
        this.kolicina = kolicina;
        this.datumugovora = datumugovora;
        this.idstatus = idstatus;
        this.datumisporuke = datumisporuke;
        this.komentar = komentar;
        this.datumisticanjapaketa = datumisticanjapaketa;
    }

    public long getIddonatorskiug() {
        return iddonatorskiug;
    }

    public void setIddonatorskiug(long iddonatorskiug) {
        this.iddonatorskiug = iddonatorskiug;
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

    public double getProcvrednost() {
        return procvrednost;
    }

    public void setProcvrednost(double procvrednost) {
        this.procvrednost = procvrednost;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Date getDatumugovora() {
        return datumugovora;
    }

    public void setDatumugovora(Date datumugovora) {
        this.datumugovora = datumugovora;
    }

    public long getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(long idstatus) {
        this.idstatus = idstatus;
    }

    public Date getDatumisporuke() {
        return datumisporuke;
    }

    public void setDatumisporuke(Date datumisporuke) {
        this.datumisporuke = datumisporuke;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Date getDatumisticanjapaketa() {
        return datumisticanjapaketa;
    }

    public void setDatumisticanjapaketa(Date datumisticanjapaketa) {
        this.datumisticanjapaketa = datumisticanjapaketa;
    }
    
    
}
