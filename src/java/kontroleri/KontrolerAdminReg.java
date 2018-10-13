/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Korisnikp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.primefaces.model.UploadedFile;
import util.EmailValidacija;
import util.FajlKlasa;
import util.HibernateUtilProjekat;
import util.PasswordValidacija;
import util.SHA;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class KontrolerAdminReg implements Serializable {

    private String poruka;
    private String ime;
    private String prezime;
    private String email;
    private String institucija;
    private String username;
    private String password;
    private String passwordPotvrda;
    private String pol;
    private Date datumrodjenja;
    private UploadedFile slika;
    private String linkedin;

    private String odobren;

    public String getOdobren() {
        return odobren;
    }

    public void setOdobren(String odobren) {
        this.odobren = odobren;
    }

    private String tip;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    private boolean itmenadzer;
    private boolean admin;
    private boolean clantima;

    public boolean isClantima() {
        return clantima;
    }

    public void setClantima(boolean clantima) {
        this.clantima = clantima;
    }

    public KontrolerAdminReg() {
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordPotvrda() {
        return passwordPotvrda;
    }

    public void setPasswordPotvrda(String passwordPotvrda) {
        this.passwordPotvrda = passwordPotvrda;
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

    public UploadedFile getSlika() {
        return slika;
    }

    public void setSlika(UploadedFile slika) {
        this.slika = slika;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public boolean isItmenadzer() {
        return itmenadzer;
    }

    public void setItmenadzer(boolean itmenadzer) {
        this.itmenadzer = itmenadzer;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    private Session sesija;

    private byte[] slikaB = null;

    private String slikaLink;

    public String getSlikaLink() {
        return slikaLink;
    }

    public void setSlikaLink(String slikaLink) {
        this.slikaLink = slikaLink;
    }

    public String registracija() {
        poruka = "";
        if (!emailTest(email)) {
            return "odobravanjeunos";
        }
        if (!passwordTest(password)) {
            return "odobravanjeunos";
        }
        if (!password.equals(passwordPotvrda)) {
            poruka = "Lozinke se ne poklapaju!";
            return "odobravanjeunos";
        }
        if (admin && itmenadzer) {
            poruka = "Ne moze i itmenadzer i admin";
            return "odobravanjeunos";
        }
        try {
            byte[] salt = SHA.getSalt();
            //String passwordSHA = SHA.generateHash(password, salt);
            String passwordSHA = SHA.generateHashNoSalt(password);
            if (itmenadzer) {
                tip = "itmenadzer";
            } else if (admin) {
                tip = "admin";
            } else {

                tip = "clantima";
            }
            itmenadzer = false;
            admin = false;
            clantima = false;

            odobren = "cekanje";
            byte[] slikaB = null;
            if (slika != null) {
                slikaB = slika.getContents();
            } 
            if (slikaB != null) {
                if (slikaB.length > 50) {
                    try {
                        String ext = "";
                        String fileName = slika.getFileName();
                        int i = fileName.lastIndexOf('.');
                        if (i > 0) {
                            ext = fileName.substring(i + 1);
                        }
                        if (!dobraExt(ext)) {
                            poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff";
                            return "";
                        }
                        String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                        putanjaSlike = putanjaSlike + "\\" + "user" + username + "." + ext;
                        slikaLink = "user" + username + "." + ext;
                        File fajl = new File(putanjaSlike);
                        FileOutputStream output = new FileOutputStream(fajl);
                        output.write(slikaB);
                        output.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Korisnikp k = new Korisnikp(ime, prezime, email, institucija, username, passwordSHA, salt, pol, datumrodjenja, slikaLink, linkedin, tip, odobren);
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(k);
            sesija.getTransaction().commit();

            poruka = "Uspesna registracija";
            return "odobravanjeunos";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Username vec postoji u bazi";
            return "odobravanjeunos";
        } finally {
            sesija.close();
        }
    }

    public boolean passwordTest(String pa) {
        boolean p = PasswordValidacija.validate(pa);
        if (p) {
            poruka = "";
        } else {
            poruka = "Zahtevi za lozinku:min=8,max=12,bar 1 veliko i 3 mala slova, bar 1 broj i specijalan znak,pocetno slovo malo il veliko";
            return false;
        }
        return true;
    }

    public boolean emailTest(String mail) {
        boolean p = EmailValidacija.validate(mail);
        if (p) {
            poruka = "";
        } else {
            poruka = "E-mail nije u validnom formatu";
            return false;
        }
        return true;
    }

    private boolean dobraExt(String ext) {
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("tiff")) {
            return true;
        } else {
            return false;
        }
    }
}
