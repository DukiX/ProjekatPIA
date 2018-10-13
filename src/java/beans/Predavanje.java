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
public class Predavanje {

    private long idpredavanje;
    private String naslovsrp;
    private String nasloveng;
    private String opissrp;
    private String opiseng;
    private Date datum;
    private Date vreme;
    private String sala;
    private String imepredavaca;
    private String biopredavaca;
    private String fajl;
    private long idkompanije;

    public Predavanje() {
    }

    public Predavanje(String naslovsrp, String nasloveng, String opissrp, String opiseng, Date datum, Date vreme, String sala, String imepredavaca, String biopredavaca, String fajl, long idkompanije) {
        this.naslovsrp = naslovsrp;
        this.nasloveng = nasloveng;
        this.opissrp = opissrp;
        this.opiseng = opiseng;
        this.datum = datum;
        this.vreme = vreme;
        this.sala = sala;
        this.imepredavaca = imepredavaca;
        this.biopredavaca = biopredavaca;
        this.fajl = fajl;
        this.idkompanije = idkompanije;
    }


    public long getIdpredavanje() {
        return idpredavanje;
    }

    public void setIdpredavanje(long idpredavanje) {
        this.idpredavanje = idpredavanje;
    }

    public String getNaslovsrp() {
        return naslovsrp;
    }

    public void setNaslovsrp(String naslovsrp) {
        this.naslovsrp = naslovsrp;
    }

    public String getNasloveng() {
        return nasloveng;
    }

    public void setNasloveng(String nasloveng) {
        this.nasloveng = nasloveng;
    }

    public String getOpissrp() {
        return opissrp;
    }

    public void setOpissrp(String opissrp) {
        this.opissrp = opissrp;
    }

    public String getOpiseng() {
        return opiseng;
    }

    public void setOpiseng(String opiseng) {
        this.opiseng = opiseng;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }


    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getImepredavaca() {
        return imepredavaca;
    }

    public void setImepredavaca(String imepredavaca) {
        this.imepredavaca = imepredavaca;
    }

    public String getBiopredavaca() {
        return biopredavaca;
    }

    public void setBiopredavaca(String biopredavaca) {
        this.biopredavaca = biopredavaca;
    }

    public String getFajl() {
        return fajl;
    }

    public void setFajl(String fajl) {
        this.fajl = fajl;
    }

    public long getIdkompanije() {
        return idkompanije;
    }

    public void setIdkompanije(long idkompanije) {
        this.idkompanije = idkompanije;
    }
    
    private StreamedContent streamedfile;

    public StreamedContent getStreamedfile() {
        return streamedfile;
    }

    public void setStreamedfile(StreamedContent streamedfile) {
        this.streamedfile = streamedfile;
    }
}
