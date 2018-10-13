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
public class Kontakt {
    private long idkontakt;
    private String username;
    private long idkompanija;

    public Kontakt() {
    }

    public Kontakt(String username, long idkompanija) {
        this.username = username;
        this.idkompanija = idkompanija;
    }

    public long getIdkontakt() {
        return idkontakt;
    }

    public void setIdkontakt(long idkontakt) {
        this.idkontakt = idkontakt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getIdkompanija() {
        return idkompanija;
    }

    public void setIdkompanija(long idkompanija) {
        this.idkompanija = idkompanija;
    }
    
}
