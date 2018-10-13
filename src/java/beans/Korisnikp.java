/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import org.hibernate.Session;
import util.HibernateUtilProjekat;

/**
 *
 * @author Dusan
 */
public class Korisnikp {

    public Korisnikp() {
    }

    public Korisnikp(String ime, String prezime, String email, String institucija, String username, String passwordsha, byte[] salt, String pol, Date datumrodjenja, String slika, String linkedin, String tip, String odobren) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.institucija = institucija;
        this.username = username;
        this.passwordsha = passwordsha;
        this.salt = salt;
        this.pol = pol;
        this.datumrodjenja = datumrodjenja;
        this.slika = slika;
        this.linkedin = linkedin;
        this.tip = tip;
        this.odobren = odobren;
    }

    private String ime;
    private String prezime;
    private String email;
    private String institucija;
    private String username;
    private String passwordsha;
    private byte[] salt;
    private String pol;
    private Date datumrodjenja;
    private String slika;
    private String linkedin;
    private String tip;
    private String odobren;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordsha() {
        return passwordsha;
    }

    public void setPasswordsha(String passwordsha) {
        this.passwordsha = passwordsha;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getDatumrodjenja() {
        return datumrodjenja;
    }

    public void setDatumrodjenja(Date datumrodjenja) {
        this.datumrodjenja = datumrodjenja;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOdobren() {
        return odobren;
    }

    public void setOdobren(String odobren) {
        this.odobren = odobren;
    }

    public boolean odobriDugme() {
        if (odobren.equals("cekanje")) {
            return true;
        } else {
            return false;
        }
    }
    
    private Session sesija;
    private String poruka;

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    public String odobriKorisnika(){
        poruka="";
        try {
            
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            
            Korisnikp k = (Korisnikp) sesija.get(Korisnikp.class, username);
            k.setOdobren("odobren");
            sesija.update(k);
            
            sesija.getTransaction().commit();

            poruka="Uspesno odobravanje";
            return "odobravanjeunos";
        } catch (Exception e) {
            e.printStackTrace();
            poruka="Greska pri odobravanju";
            return "odobravanjeunos";
        } finally {
            sesija.close();
        }
    }
    
    public String obrisiKorisnika(){
        poruka="";
        try {
            
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            
            Korisnikp kor = (Korisnikp) sesija.load(Korisnikp.class, username);
            if(kor!=null){
                sesija.delete(kor);
            }
            
            sesija.getTransaction().commit();

            poruka="Uspesno brisanje";
            return "odobravanjeunos";
        } catch (Exception e) {
            e.printStackTrace();
            poruka="Greska pri brisanju";
            return "odobravanjeunos";
        } finally {
            sesija.close();
        }
    }
}
