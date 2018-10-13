/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Dusan
 */
public class Oglas {

    private long idoglas;
    private String naslov;
    private String opis;
    private int praksa;
    private int posao;
    private Date vremeunosenja;
    private String oglasfajl;
    private long idkompanija;

    public Oglas() {
    }

    public Oglas(String naslov, String opis, int praksa, int posao, Date vremeunosenja, String oglasfajl, long idkompanija) {
        this.naslov = naslov;
        this.opis = opis;
        this.praksa = praksa;
        this.posao = posao;
        this.vremeunosenja = vremeunosenja;
        this.oglasfajl = oglasfajl;
        this.idkompanija = idkompanija;
    }

    public long getIdoglas() {
        return idoglas;
    }

    public void setIdoglas(long idoglas) {
        this.idoglas = idoglas;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getPraksa() {
        return praksa;
    }

    public void setPraksa(int praksa) {
        this.praksa = praksa;
    }

    public int getPosao() {
        return posao;
    }

    public void setPosao(int posao) {
        this.posao = posao;
    }

    public Date getVremeunosenja() {
        return vremeunosenja;
    }

    public void setVremeunosenja(Date vremeunosenja) {
        this.vremeunosenja = vremeunosenja;
    }

    public String getOglasfajl() {
        return oglasfajl;
    }

    public void setOglasfajl(String oglasfajl) {
        this.oglasfajl = oglasfajl;
    }

    public long getIdkompanija() {
        return idkompanija;
    }

    public void setIdkompanija(long idkompanija) {
        this.idkompanija = idkompanija;
    }

    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    
}
