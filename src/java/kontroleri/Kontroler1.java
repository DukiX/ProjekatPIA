/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Kontakt;
import beans.Korisnikp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Past;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
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
public class Kontroler1 implements Serializable {

    private Session sesija;

    private String ime;
    private String prezime;
    private String email;
    private String institucija;
    private String username;
    private String password;
    private String passwordPotvrda;
    private String pol;
    @Past
    private Date datumrodjenja;
    private UploadedFile slika;
    private String linkedin;
    private String tip;
    private String odobren;

    private String slikaLink;

    public String getSlikaLink() {
        return slikaLink;
    }

    public void setSlikaLink(String slikaLink) {
        this.slikaLink = slikaLink;
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

    boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    private String noviPassword;

    public String getNoviPassword() {
        return noviPassword;
    }

    public void setNoviPassword(String noviPassword) {
        this.noviPassword = noviPassword;
    }

    byte[] slikaB = null;

    public byte[] getSlikaB() {
        return slikaB;
    }

    public void setSlikaB(byte[] slikaB) {
        this.slikaB = slikaB;
    }

    private String poruka;

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Session getSesija() {
        return sesija;
    }

    public void setSesija(Session sesija) {
        this.sesija = sesija;
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

    private boolean itmenadzer = false;
    private boolean admin = false;
    private boolean clantima = false;

    public boolean isClantima() {
        return clantima;
    }

    public void setClantima(boolean clantima) {
        this.clantima = clantima;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isItmenadzer() {
        return itmenadzer;
    }

    public void setItmenadzer(boolean itmenadzer) {
        this.itmenadzer = itmenadzer;
    }

    public void ucitajFajl(FileUploadEvent event) {
        this.slika = event.getFile();
    }

    public String registracija() {
        poruka = "";
        if (!emailTest(email)) {
            return "registracija";
        }
        if (!passwordTest(password)) {
            return "registracija";
        }
        if (!password.equals(passwordPotvrda)) {
            poruka = "Lozinke se ne poklapaju!";
            return "registracija";
        }
        if (admin && itmenadzer) {
            poruka = "Ne moze i itmenadzer i admin";
            return "registracija";
        }

        byte[] salt = null;
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
        try {
            Korisnikp k = new Korisnikp(ime, prezime, email, institucija, username, passwordSHA, salt, pol, datumrodjenja, slikaLink, linkedin, tip, odobren);
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(k);
            sesija.getTransaction().commit();

            poruka = "Uspesna registracija";
            return "registracija";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Username vec postoji u bazi";
            return "registracija";
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    public String login() {
        slikaB = null;
        try {
            poruka = "";
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Korisnikp k = (Korisnikp) sesija.get(Korisnikp.class, username);
            if (k == null) {
                poruka = "Ne postoji zadati username";
                return "index";
            } else {
                byte[] salt = k.getSalt();
                //String passwordSHA = SHA.generateHash(password, salt);
                String passwordSHA = SHA.generateHashNoSalt(password);
                if (k.getOdobren().equals("odobren")) {
                    if (k.getPasswordsha().equals(passwordSHA)) {
                        loggedIn = true;
                        slikaLink = k.getSlika();
                        ime = k.getIme();
                        prezime = k.getPrezime();
                        email = k.getEmail();
                        institucija = k.getInstitucija();
                        pol = k.getPol();
                        datumrodjenja = k.getDatumrodjenja();
                        linkedin = k.getLinkedin();
                        tip = k.getTip();
                        if (tip.equals("clantima")) {
                            admin = false;
                            itmenadzer = false;
                            clantima = true;
                            poruka = "Dobrodosli";
                            return "clantima";
                        } else if (tip.equals("itmenadzer")) {
                            admin = false;
                            itmenadzer = true;
                            clantima = false;
                            poruka = "Dobrodosli";
                            return "itmenadzer";
                        } else {
                            admin = true;
                            itmenadzer = false;
                            clantima = false;
                            poruka = "Dobrodosli";
                            return "admin";
                        }
                    } else {
                        poruka = "Pogresan password";
                        return "index";
                    }
                } else {
                    poruka = "Niste odobreni korisnik";
                    return "index";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "index";
        } finally {
            sesija.close();
        }

    }

    private byte[] pomoc;

    public StreamedContent sl() {
        /*
        try {
            //URL defaultSlikaUrl = getClass().getResource("user.png");
            //System.out.print(defaultSlikaUrl);
            File fnew = new File("C:\\Users\\Dusan\\Desktop\\brt.png");
            //File fnew = new File(defaultSlikaUrl.toURI());
            BufferedImage originalImage = ImageIO.read(fnew);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            byte[] imageInByte = baos.toByteArray();
            InputStream scElse = new ByteArrayInputStream(imageInByte);
            DefaultStreamedContent rez = new DefaultStreamedContent(scElse);
            scElse.close();
            return rez;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler1.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return null;

        /*if (slikaB != null) {
                //InputStream sc = new ByteArrayInputStream(slikaB);
                //InputStream sc = new ByteArrayInputStream(pomoc);
                ByteArrayInputStream sc = new ByteArrayInputStream(pomoc);
                System.out.print("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                sc.close();
                return new DefaultStreamedContent(sc);
            } else {
                return null;
                try {
                File fnew = new File("C:\\Users\\Dusan\\Documents\\NetBeansProjects\\ProjekatV1\\web\\resources\\slike\\user.png");
                BufferedImage originalImage = ImageIO.read(fnew);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "png", baos);
                byte[] imageInByte = baos.toByteArray();
                InputStream scElse = new ByteArrayInputStream(imageInByte);
                DefaultStreamedContent rez = new DefaultStreamedContent(scElse);
                return rez;
                } catch (IOException ex) {
                Logger.getLogger(Kontroler1.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.print("333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(Kontroler1.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public StreamedContent defaultSl() {
        /*try {
            URL defaultSlikaUrl = getClass().getResource("user.png");
            //System.out.print(defaultSlikaUrl);
            //File fnew = new File("C:\\Users\\Dusan\\Documents\\NetBeansProjects\\ProjekatV1\\web\\resources\\slike\\user.png");
            File fnew = new File(defaultSlikaUrl.toURI());
            BufferedImage originalImage = ImageIO.read(fnew);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            byte[] imageInByte = baos.toByteArray();
            InputStream scElse = new ByteArrayInputStream(imageInByte);
            DefaultStreamedContent rez = new DefaultStreamedContent(scElse);
            scElse.close();
            return rez;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler1.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return null;
    }

    public String promeniPassword() {
        poruka = "";
        if (!passwordTest(noviPassword)) {
            return "promena";
        }
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Korisnikp k = (Korisnikp) sesija.get(Korisnikp.class, username);
            if (k == null) {
                poruka = "Ne postoji zadati username";
                return "promena";
            } else {
                byte[] salt = k.getSalt();
                //String passwordSHA = SHA.generateHash(password, salt);
                String passwordSHA = SHA.generateHashNoSalt(password);
                if (k.getPasswordsha().equals(passwordSHA)) {
                    salt = SHA.getSalt();
                    //passwordSHA = SHA.generateHash(noviPassword, salt);
                    passwordSHA = SHA.generateHashNoSalt(noviPassword);
                    k.setSalt(salt);
                    k.setPasswordsha(passwordSHA);
                    sesija.update(k);
                    sesija.getTransaction().commit();
                    poruka = "Uspeno promenjena lozinka";
                    return "promena";
                } else {
                    poruka = "Pogresan password";
                    return "promena";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "promena";
        } finally {
            sesija.close();
        }
    }

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public boolean passwordTest(String pa) {
        boolean p = PasswordValidacija.validate(pa);
        if (p) {
            poruka = "";
        } else {
            poruka = "Zahtevi za lozinku:min=8,max=12,bar 1 veliko i 3 mala slova, bar 1 broj i specijalan znak,pocetno slovo malo il veliko, ne vise od dva identicna uzastopna karaktera";
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

    public String logout() {
        loggedIn = false;
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "index";
    }

    /*public String photoURL(){
        URL defaultSlikaUrl = getClass().getResource("slika"+username+".png");
        System.out.print(defaultSlikaUrl.toString());
        return defaultSlikaUrl.toString();
    }*/
    private List<Korisnikp> listaKorisnikaObjekata;

    public List<Korisnikp> getListaKorisnikaObjekata() {
        return listaKorisnikaObjekata;
    }

    public void setListaKorisnikaObjekata(List<Korisnikp> listaKorisnikaObjekata) {
        this.listaKorisnikaObjekata = listaKorisnikaObjekata;
    }

    public void ucitajKorisnike() {
        listaKorisnikaObjekata = new ArrayList<>();
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Korisnikp k");
            listaKorisnikaObjekata = query.list();
            sesija.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesija.close();
        }
    }

    private String izabraniKorisnik;

    public String getIzabraniKorisnik() {
        return izabraniKorisnik;
    }

    public void setIzabraniKorisnik(String izabraniKorisnik) {
        this.izabraniKorisnik = izabraniKorisnik;
    }

    private boolean azuriraj = false;

    public boolean isAzuriraj() {
        return azuriraj;
    }

    public void setAzuriraj(boolean azuriraj) {
        this.azuriraj = azuriraj;
    }

    public boolean moja(long idkomp) {
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Kontakt WHERE username = '" + username + "' AND idkompanija = '" + idkomp + "'");
            List<Kontakt> listaKontakata = query.list();
            sesija.getTransaction().commit();
            sesija.close();

            if (listaKontakata.isEmpty()) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } 

    private boolean dobraExt(String ext) {
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("tiff")) {
            return true;
        } else {
            return false;
        }
    }

}
