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
public class Status {
    private long idstatus;
    private String naziv;

    public Status() {
    }

    public Status(String naziv) {
        this.naziv = naziv;
    }

    public long getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(long idstatus) {
        this.idstatus = idstatus;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
