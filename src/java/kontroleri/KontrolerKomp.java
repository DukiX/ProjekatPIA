/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Donatorskiug;
import beans.Kompanija;
import beans.KompanijaPomocna;
import beans.Kontakt;
import beans.Korisnikp;
import beans.Novcaniug;
import beans.Paket;
import beans.PrikazKlasa;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.timeline.TimelineEvent;
import util.FajlKlasa;
import util.HibernateUtilProjekat;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class KontrolerKomp implements Serializable {

    private long idkompanija;
    private String naziv;
    private String adresa;
    private String grad;
    private int postanskibroj;
    private String zemlja;
    private String ziroracun;
    private String valuta;
    private String pib;
    private String telefon1;
    private String telefon2;
    private String telefon3;
    private String telefon4;
    private String telefon5;
    private String email1;
    private String email2;
    private String email3;
    private String email4;
    private String email5;
    private String ime;
    private String prezime;
    private String kontaktemail;
    private String kontakttel;
    private String odgovoran;
    private String logolink;

    private UploadedFile logo;

    private String webadresa;

    public String getWebadresa() {
        return webadresa;
    }

    public void setWebadresa(String webadresa) {
        this.webadresa = webadresa;
    }

    private String poruka;

    public KontrolerKomp() {
    }

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon1) {
        this.telefon1 = telefon1;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getTelefon3() {
        return telefon3;
    }

    public void setTelefon3(String telefon3) {
        this.telefon3 = telefon3;
    }

    public String getTelefon4() {
        return telefon4;
    }

    public void setTelefon4(String telefon4) {
        this.telefon4 = telefon4;
    }

    public String getTelefon5() {
        return telefon5;
    }

    public void setTelefon5(String telefon5) {
        this.telefon5 = telefon5;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public UploadedFile getLogo() {
        return logo;
    }

    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    public long getIdkompanija() {
        return idkompanija;
    }

    public void setIdkompanija(long idkompanija) {
        this.idkompanija = idkompanija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public int getPostanskibroj() {
        return postanskibroj;
    }

    public void setPostanskibroj(int postanskibroj) {
        this.postanskibroj = postanskibroj;
    }

    public String getZemlja() {
        return zemlja;
    }

    public void setZemlja(String zemlja) {
        this.zemlja = zemlja;
    }

    public String getZiroracun() {
        return ziroracun;
    }

    public void setZiroracun(String ziroracun) {
        this.ziroracun = ziroracun;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getEmail4() {
        return email4;
    }

    public void setEmail4(String email4) {
        this.email4 = email4;
    }

    public String getEmail5() {
        return email5;
    }

    public void setEmail5(String email5) {
        this.email5 = email5;
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

    public String getKontaktemail() {
        return kontaktemail;
    }

    public void setKontaktemail(String kontaktemail) {
        this.kontaktemail = kontaktemail;
    }

    public String getKontakttel() {
        return kontakttel;
    }

    public void setKontakttel(String kontakttel) {
        this.kontakttel = kontakttel;
    }

    public String getOdgovoran() {
        return odgovoran;
    }

    public void setOdgovoran(String odgovoran) {
        this.odgovoran = odgovoran;
    }

    public String getLogolink() {
        return logolink;
    }

    public void setLogolink(String logolink) {
        this.logolink = logolink;
    }

    private Session sesija;

    public void ucitajFajl(FileUploadEvent event){
        this.logo=event.getFile();
    }
    
    public String unosKompanije() {
        poruka = "";

        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        Kontroler1 kontroler1 = (Kontroler1) elContext.getELResolver().getValue(elContext, null, "kontroler1");

        odgovoran = kontroler1.getUsername();
        byte[] slika = logo.getContents();
        if (slika != null) {
            if (slika.length > 50) {
                try {
                    String ext = "";
                    String fileName = logo.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff, ai, eps, pdf";
                        return "";
                    }
                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "logo" + naziv + "." + ext;
                    logolink = "logo" + naziv + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(slika);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Kompanija kom = new Kompanija(naziv, adresa, grad, postanskibroj, zemlja, ziroracun, valuta, pib, telefon1, telefon2, telefon3, telefon4, telefon5, email1, email2, email3, email4, email5, ime, prezime, kontaktemail, kontakttel, logolink, webadresa);
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(kom);
            sesija.getTransaction().commit();
            sesija.close();

            Kontakt kon = new Kontakt(odgovoran, kom.getIdkompanija());
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(kon);
            sesija.getTransaction().commit();

            poruka = "Uspesno dodavanje kompanije";
            return "unoskomp";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unoskomp";
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    private List<SelectItem> listaKorisnika;
    private List<Korisnikp> listaKorisnikaObjekata;

    public List<SelectItem> getListaKorisnika() {
        return listaKorisnika;
    }

    public void setListaKorisnika(List<SelectItem> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public List<Korisnikp> getListaKorisnikaObjekata() {
        return listaKorisnikaObjekata;
    }

    public void setListaKorisnikaObjekata(List<Korisnikp> listaKorisnikaObjekata) {
        this.listaKorisnikaObjekata = listaKorisnikaObjekata;
    }

    public void ucitajKorisnike() {
        listaKorisnika = new ArrayList<>();
        listaKorisnikaObjekata = new ArrayList<>();
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Korisnikp k WHERE k.tip = 'clantima' AND k.odobren = 'odobren'");
            listaKorisnikaObjekata = query.list();
            sesija.getTransaction().commit();

            for (Korisnikp kor : listaKorisnikaObjekata) {
                SelectItem sel = new SelectItem(kor.getUsername());
                if (!listaKorisnika.contains(sel)) {
                    listaKorisnika.add(sel);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    private String izabraniKorisnik;

    public String getIzabraniKorisnik() {
        return izabraniKorisnik;
    }

    public void setIzabraniKorisnik(String izabraniKorisnik) {
        this.izabraniKorisnik = izabraniKorisnik;
    }

    private List<String> izabraniKorisnici;

    public List<String> getIzabraniKorisnici() {
        return izabraniKorisnici;
    }

    public void setIzabraniKorisnici(List<String> izabraniKorisnici) {
        this.izabraniKorisnici = izabraniKorisnici;
    }

    public String unosKompanijeIt() {
        poruka = "";
        byte[] slika = logo.getContents();
        if (slika != null) {
            if (slika.length > 50) {
                try {
                    String ext = "";
                    String fileName = logo.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff, ai, eps, pdf";
                        return "";
                    }
                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "logo" + naziv + "." + ext;
                    logolink = "logo" + naziv + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(slika);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Kompanija kom = new Kompanija(naziv, adresa, grad, postanskibroj, zemlja, ziroracun, valuta, pib, telefon1, telefon2, telefon3, telefon4, telefon5, email1, email2, email3, email4, email5, ime, prezime, kontaktemail, kontakttel, logolink, webadresa);
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(kom);
            sesija.getTransaction().commit();
            sesija.close();

            for (String kor : izabraniKorisnici) {
                Kontakt kon = new Kontakt(kor, kom.getIdkompanija());
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                sesija.save(kon);
                sesija.getTransaction().commit();
                sesija.close();
            }
            poruka = "Uspesno dodavanje kompanije";
            return "unoskompit";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unoskompit";
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    public String unosKompanijeAd() {
        poruka = "";

        byte[] slika = logo.getContents();
        if (slika != null) {
            if (slika.length > 50) {
                try {
                    String ext = "";
                    String fileName = logo.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff, ai, eps, pdf";
                        return "";
                    }
                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "logo" + naziv + "." + ext;
                    logolink = "logo" + naziv + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(slika);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Kompanija kom = new Kompanija(naziv, adresa, grad, postanskibroj, zemlja, ziroracun, valuta, pib, telefon1, telefon2, telefon3, telefon4, telefon5, email1, email2, email3, email4, email5, ime, prezime, kontaktemail, kontakttel, logolink, webadresa);
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(kom);
            sesija.getTransaction().commit();
            sesija.close();

            for (String kor : izabraniKorisnici) {
                Kontakt kon = new Kontakt(kor, kom.getIdkompanija());
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                sesija.save(kon);
                sesija.getTransaction().commit();
                sesija.close();
            }
            poruka = "Uspesno dodavanje kompanije";
            return "unoskompad";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unoskompad";
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    public void onSelect(TimelineSelectEvent e) {
        TimelineEvent timelineEvent = e.getTimelineEvent();

        PrikazKlasa pr = (PrikazKlasa) timelineEvent.getData();

        long idkomp = pr.getIdKomp();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Kompanija komp = (Kompanija) sesija.get(Kompanija.class, idkomp);

        this.idkompanija = komp.getIdkompanija();
        this.naziv = komp.getNaziv();
        this.adresa = komp.getAdresa();
        this.grad = komp.getGrad();
        this.postanskibroj = komp.getPostanskibroj();
        this.zemlja = komp.getZemlja();
        this.ziroracun = komp.getZiroracun();
        this.valuta = komp.getValuta();
        this.pib = komp.getPib();
        this.telefon1 = komp.getTelefon1();
        this.telefon2 = komp.getTelefon2();
        this.telefon3 = komp.getTelefon3();
        this.telefon4 = komp.getTelefon4();
        this.telefon5 = komp.getTelefon5();
        this.email1 = komp.getEmail1();
        this.email2 = komp.getEmail2();
        this.email3 = komp.getEmail3();
        this.email4 = komp.getEmail4();
        this.email5 = komp.getEmail5();
        this.ime = komp.getIme();
        this.prezime = komp.getPrezime();
        this.kontaktemail = komp.getKontaktemail();
        this.kontakttel = komp.getKontakttel();
        this.logolink = komp.getLogolink();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(contextPath + "/faces/dosijekompanije.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kompanija:", timelineEvent.getData().toString());
        RequestContext.getCurrentInstance().showMessageInDialog(msg);*/
    }

    public String detaljno(KompanijaPomocna komp) {

        this.idkompanija = komp.getIdkompanija();
        this.naziv = komp.getNaziv();
        this.adresa = komp.getAdresa();
        this.grad = komp.getGrad();
        this.postanskibroj = komp.getPostanskibroj();
        this.zemlja = komp.getZemlja();
        this.ziroracun = komp.getZiroracun();
        this.valuta = komp.getValuta();
        this.pib = komp.getPib();
        this.telefon1 = komp.getTelefon1();
        this.telefon2 = komp.getTelefon2();
        this.telefon3 = komp.getTelefon3();
        this.telefon4 = komp.getTelefon4();
        this.telefon5 = komp.getTelefon5();
        this.email1 = komp.getEmail1();
        this.email2 = komp.getEmail2();
        this.email3 = komp.getEmail3();
        this.email4 = komp.getEmail4();
        this.email5 = komp.getEmail5();
        this.ime = komp.getIme();
        this.prezime = komp.getPrezime();
        this.kontaktemail = komp.getKontaktemail();
        this.kontakttel = komp.getKontakttel();
        this.logolink = komp.getLogolink();

        return "dosijekompanije";
    }

    private List<Kompanija> listaKompanijaObjekata;
    private List<SelectItem> listaKompanija;

    public List<Kompanija> getListaKompanijaObjekata() {
        return listaKompanijaObjekata;
    }

    public void setListaKompanijaObjekata(List<Kompanija> listaKompanijaObjekata) {
        this.listaKompanijaObjekata = listaKompanijaObjekata;
    }

    public List<SelectItem> getListaKompanija() {
        return listaKompanija;
    }

    public void setListaKompanija(List<SelectItem> listaKompanija) {
        this.listaKompanija = listaKompanija;
    }

    public void ucitajKompIKor() {
        izabraniKorisnik = "";
        izabranaKompanija = null;
        listaKorisnika = new ArrayList<>();
        listaKorisnikaObjekata = new ArrayList<>();
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Korisnikp k WHERE k.tip = 'clantima'  AND k.odobren = 'odobren'");
            listaKorisnikaObjekata = query.list();
            sesija.getTransaction().commit();
            sesija.close();

            for (Korisnikp kor : listaKorisnikaObjekata) {
                SelectItem sel = new SelectItem(kor.getUsername());
                if (!listaKorisnika.contains(sel)) {
                    listaKorisnika.add(sel);
                }
            }

            listaKompanija = new ArrayList<>();
            listaKompanijaObjekata = new ArrayList<>();

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query2 = sesija.createQuery("FROM beans.Kompanija");
            listaKompanijaObjekata = query2.list();
            sesija.getTransaction().commit();
            sesija.close();

            for (Kompanija kom : listaKompanijaObjekata) {
                SelectItem sel = new SelectItem(kom.getIdkompanija(), kom.getNaziv());
                if (!listaKompanija.contains(sel)) {
                    listaKompanija.add(sel);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long izabranaKompanija;

    public Long getIzabranaKompanija() {
        return izabranaKompanija;
    }

    public void setIzabranaKompanija(Long izabranaKompanija) {
        this.izabranaKompanija = izabranaKompanija;
    }

    public boolean testKontakt() {
        if (listaKorisnika.isEmpty() || listaKompanija.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String unosKontakta() {
        poruka = "";
        try {
            Kontakt kon = new Kontakt(izabraniKorisnik, izabranaKompanija);
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(kon);
            sesija.getTransaction().commit();
            sesija.close();

            poruka = "Uspesno unet kontakt";
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "";
        }
    }

    private boolean azuriranje = false;

    public boolean isAzuriranje() {
        return azuriranje;
    }

    public void setAzuriranje(boolean azuriranje) {
        this.azuriranje = azuriranje;
    }

    public void onSelect2(KompanijaPomocna komp) {

        this.idkompanija = komp.getIdkompanija();
        this.naziv = komp.getNaziv();
        this.adresa = komp.getAdresa();
        this.grad = komp.getGrad();
        this.postanskibroj = komp.getPostanskibroj();
        this.zemlja = komp.getZemlja();
        this.ziroracun = komp.getZiroracun();
        this.valuta = komp.getValuta();
        this.pib = komp.getPib();
        this.telefon1 = komp.getTelefon1();
        this.telefon2 = komp.getTelefon2();
        this.telefon3 = komp.getTelefon3();
        this.telefon4 = komp.getTelefon4();
        this.telefon5 = komp.getTelefon5();
        this.email1 = komp.getEmail1();
        this.email2 = komp.getEmail2();
        this.email3 = komp.getEmail3();
        this.email4 = komp.getEmail4();
        this.email5 = komp.getEmail5();
        this.ime = komp.getIme();
        this.prezime = komp.getPrezime();
        this.kontaktemail = komp.getKontaktemail();
        this.kontakttel = komp.getKontakttel();
        this.logolink = komp.getLogolink();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(contextPath + "/faces/dosijekompanije.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kompanija:", timelineEvent.getData().toString());
        RequestContext.getCurrentInstance().showMessageInDialog(msg);*/
    }

    public String unesiIzmene() {
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Kompanija kom = (Kompanija) sesija.get(Kompanija.class, idkompanija);

            kom.setAdresa(adresa);
            kom.setGrad(grad);
            kom.setPostanskibroj(postanskibroj);
            kom.setZemlja(zemlja);
            kom.setZiroracun(ziroracun);
            kom.setValuta(valuta);
            kom.setPib(pib);
            kom.setTelefon1(telefon1);
            kom.setTelefon2(telefon2);
            kom.setTelefon3(telefon3);
            kom.setTelefon4(telefon4);
            kom.setTelefon5(telefon5);
            kom.setEmail1(email1);
            kom.setEmail2(email2);
            kom.setEmail3(email3);
            kom.setEmail4(email4);
            kom.setEmail5(email5);
            kom.setIme(ime);
            kom.setPrezime(prezime);
            kom.setKontaktemail(kontaktemail);
            kom.setKontakttel(kontakttel);

            byte[] slika = logo.getContents();
            if (slika != null) {
                if (slika.length > 50) {
                    try {
                        String ext = "";
                        String fileName = logo.getFileName();
                        int i = fileName.lastIndexOf('.');
                        if (i > 0) {
                            ext = fileName.substring(i + 1);
                        }
                        if (!dobraExt(ext)) {
                            poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff, ai, eps, pdf";
                            return "";
                        }
                        String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                        putanjaSlike = putanjaSlike + "\\" + "logo" + naziv + "." + ext;
                        logolink = "logo" + naziv + "." + ext;
                        File fajl = new File(putanjaSlike);
                        FileOutputStream output = new FileOutputStream(fajl);
                        output.write(slika);
                        output.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            kom.setLogolink(logolink);

            sesija.update(kom);
            sesija.getTransaction().commit();
            sesija.close();

            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            Kontroler1 kontroler1 = (Kontroler1) elContext.getELResolver().getValue(elContext, null, "kontroler1");
            kontroler1.setAzuriraj(false);

            poruka = "Uspesno uneti podaci";
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "";
        }
    }
    private String nazivPaketaPretraga;
    private String nazivKompanijePretraga;
    private boolean aktuelne = false;

    public String getNazivPaketaPretraga() {
        return nazivPaketaPretraga;
    }

    public void setNazivPaketaPretraga(String nazivPaketaPretraga) {
        this.nazivPaketaPretraga = nazivPaketaPretraga;
    }

    public String getNazivKompanijePretraga() {
        return nazivKompanijePretraga;
    }

    public void setNazivKompanijePretraga(String nazivKompanijePretraga) {
        this.nazivKompanijePretraga = nazivKompanijePretraga;
    }

    public boolean isAktuelne() {
        return aktuelne;
    }

    public void setAktuelne(boolean aktuelne) {
        this.aktuelne = aktuelne;
    }

    private List<Kompanija> pretragaList;

    public List<Kompanija> getPretragaList() {
        return pretragaList;
    }

    public void setPretragaList(List<Kompanija> pretragaList) {
        this.pretragaList = pretragaList;
    }

    public String pretraga() {
        try {
            pretragaList = new ArrayList<>();
            if (nazivPaketaPretraga.equals("") && nazivKompanijePretraga.equals("")) {
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Query query = sesija.createQuery("FROM beans.Kompanija");
                pretragaList = query.list();
                sesija.getTransaction().commit();
                sesija.close();

                if (aktuelne) {
                    List<Kompanija> pomocna = new ArrayList<>(pretragaList);
                    for (Kompanija komp : pomocna) {
                        if (!aktivanUgovor(komp.getIdkompanija())) {
                            pretragaList.remove(komp);
                        }
                    }
                }
            } else if (nazivPaketaPretraga.equals("") && !nazivKompanijePretraga.equals("")) {
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Query query = sesija.createQuery("FROM beans.Kompanija k WHERE k.naziv LIKE '%" + nazivKompanijePretraga + "%'");
                pretragaList = query.list();
                sesija.getTransaction().commit();
                sesija.close();

                if (aktuelne) {
                    List<Kompanija> pomocna = new ArrayList<>(pretragaList);
                    for (Kompanija komp : pomocna) {
                        if (!aktivanUgovor(komp.getIdkompanija())) {
                            pretragaList.remove(komp);
                        }
                    }
                }
            } else if (!nazivPaketaPretraga.equals("") && nazivKompanijePretraga.equals("")) {
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Query query = sesija.createQuery("FROM beans.Paket p WHERE p.naziv LIKE '%" + nazivPaketaPretraga + "%'");
                List<Paket> listaPaketa = query.list();
                sesija.getTransaction().commit();
                sesija.close();

                List<Novcaniug> listaNovcaniSaPaketom = new ArrayList<>();

                for (Paket p : listaPaketa) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query2 = sesija.createQuery("FROM beans.Novcaniug n WHERE n.idpaket = '" + p.getIdpaket() + "'");
                    listaNovcaniSaPaketom = query2.list();
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                List<Donatorskiug> listaDonatorskiSaPaketom = new ArrayList<>();

                for (Paket p : listaPaketa) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query2 = sesija.createQuery("FROM beans.Donatorskiug d WHERE d.idpaket = '" + p.getIdpaket() + "'");
                    listaDonatorskiSaPaketom = query2.list();
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                for (Novcaniug n : listaNovcaniSaPaketom) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query3 = sesija.createQuery("FROM beans.Kompanija k WHERE k.idkompanija = '" + n.getIdkomp() + "'");
                    pretragaList.addAll(query3.list());
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                for (Donatorskiug d : listaDonatorskiSaPaketom) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query3 = sesija.createQuery("FROM beans.Kompanija k WHERE k.idkompanija = '" + d.getIdkomp() + "'");
                    pretragaList.addAll(query3.list());
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                List<Kompanija> pom = new ArrayList<>(pretragaList);
                pretragaList.clear();
                for (Kompanija k : pom) {
                    boolean postoji = false;
                    for (Kompanija k2 : pretragaList) {
                        if (k.getNaziv().equals(k2.getNaziv())) {
                            postoji = true;
                        }
                    }
                    if (!postoji) {
                        pretragaList.add(k);
                    }
                }

                if (aktuelne) {
                    List<Kompanija> pomocna = new ArrayList<>(pretragaList);
                    for (Kompanija komp : pomocna) {
                        if (!aktivanUgovor(komp.getIdkompanija())) {
                            pretragaList.remove(komp);
                        }
                    }
                }
            } else if (!nazivPaketaPretraga.equals("") && !nazivKompanijePretraga.equals("")) {
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Query query = sesija.createQuery("FROM beans.Paket p WHERE p.naziv LIKE '%" + nazivPaketaPretraga + "%'");
                List<Paket> listaPaketa = query.list();
                sesija.getTransaction().commit();
                sesija.close();

                List<Novcaniug> listaNovcaniSaPaketom = new ArrayList<>();

                for (Paket p : listaPaketa) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query2 = sesija.createQuery("FROM beans.Novcaniug n WHERE n.idpaket = '" + p.getIdpaket() + "'");
                    listaNovcaniSaPaketom = query2.list();
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                List<Donatorskiug> listaDonatorskiSaPaketom = new ArrayList<>();

                for (Paket p : listaPaketa) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query2 = sesija.createQuery("FROM beans.Donatorskiug d WHERE d.idpaket = '" + p.getIdpaket() + "'");
                    listaDonatorskiSaPaketom = query2.list();
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                for (Novcaniug n : listaNovcaniSaPaketom) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query3 = sesija.createQuery("FROM beans.Kompanija k WHERE k.idkompanija = '" + n.getIdkomp() + "' AND k.naziv LIKE '%" + nazivKompanijePretraga + "%'");
                    pretragaList.addAll(query3.list());
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                for (Donatorskiug d : listaDonatorskiSaPaketom) {
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query3 = sesija.createQuery("FROM beans.Kompanija k WHERE k.idkompanija = '" + d.getIdkomp() + "'AND k.naziv LIKE '%" + nazivKompanijePretraga + "%'");
                    pretragaList.addAll(query3.list());
                    sesija.getTransaction().commit();
                    sesija.close();
                }

                List<Kompanija> pom = new ArrayList<>(pretragaList);
                pretragaList.clear();
                for (Kompanija k : pom) {
                    boolean postoji = false;
                    for (Kompanija k2 : pretragaList) {
                        if (k.getNaziv().equals(k2.getNaziv())) {
                            postoji = true;
                        }
                    }
                    if (!postoji) {
                        pretragaList.add(k);
                    }
                }

                if (aktuelne) {
                    List<Kompanija> pomocna = new ArrayList<>(pretragaList);
                    for (Kompanija komp : pomocna) {
                        if (!aktivanUgovor(komp.getIdkompanija())) {
                            pretragaList.remove(komp);
                        }
                    }
                }
            }
            poruka = "";
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "";
        }
    }

    public boolean aktivanUgovor(long idkomp) {
        Date danas = new Date();
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Novcaniug nu WHERE nu.idkomp = '" + idkomp + "'");
            List<Novcaniug> u = query.list();
            sesija.getTransaction().commit();
            sesija.close();
            if (u.isEmpty()) {
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Query query2 = sesija.createQuery("FROM beans.Donatorskiug du WHERE du.idkomp = '" + idkomp + "'");
                List<Donatorskiug> u2 = query2.list();
                sesija.getTransaction().commit();
                sesija.close();

                for (Donatorskiug dug : u2) {
                    Date datumist = dug.getDatumisticanjapaketa();
                    if (datumist.after(danas)) {
                        return true;
                    }
                }

                return false;

            } else {

                for (Novcaniug nug : u) {
                    Date datumist = nug.getDatumisticanjapaketa();
                    if (datumist.after(danas)) {
                        return true;
                    }
                }

                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Query query2 = sesija.createQuery("FROM beans.Donatorskiug du WHERE du.idkomp = '" + idkomp + "'");
                List<Donatorskiug> u2 = query2.list();
                sesija.getTransaction().commit();
                sesija.close();

                for (Donatorskiug dug : u2) {
                    Date datumist = dug.getDatumisticanjapaketa();
                    if (datumist.after(danas)) {
                        return true;
                    }
                }

                return false;

            }
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    private Kompanija selected;

    public Kompanija getSelected() {
        return selected;
    }

    public void setSelected(Kompanija selected) {
        this.selected = selected;
    }

    public void onSelect3(Kompanija komp) {

        this.idkompanija = komp.getIdkompanija();
        this.naziv = komp.getNaziv();
        this.adresa = komp.getAdresa();
        this.grad = komp.getGrad();
        this.postanskibroj = komp.getPostanskibroj();
        this.zemlja = komp.getZemlja();
        this.ziroracun = komp.getZiroracun();
        this.valuta = komp.getValuta();
        this.pib = komp.getPib();
        this.telefon1 = komp.getTelefon1();
        this.telefon2 = komp.getTelefon2();
        this.telefon3 = komp.getTelefon3();
        this.telefon4 = komp.getTelefon4();
        this.telefon5 = komp.getTelefon5();
        this.email1 = komp.getEmail1();
        this.email2 = komp.getEmail2();
        this.email3 = komp.getEmail3();
        this.email4 = komp.getEmail4();
        this.email5 = komp.getEmail5();
        this.ime = komp.getIme();
        this.prezime = komp.getPrezime();
        this.kontaktemail = komp.getKontaktemail();
        this.kontakttel = komp.getKontakttel();
        this.logolink = komp.getLogolink();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(contextPath + "/faces/dosijekompanije.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kompanija:", timelineEvent.getData().toString());
        RequestContext.getCurrentInstance().showMessageInDialog(msg);*/
    }

    private List<Kompanija> listaSvihKomp;

    public List<Kompanija> getListaSvihKomp() {
        return listaSvihKomp;
    }

    public void setListaSvihKomp(List<Kompanija> listaSvihKomp) {
        this.listaSvihKomp = listaSvihKomp;
    }

    private List<Paket> listaSvihPaketa;

    public List<Paket> getListaSvihPaketa() {
        return listaSvihPaketa;
    }

    public void setListaSvihPaketa(List<Paket> listaSvihPaketa) {
        this.listaSvihPaketa = listaSvihPaketa;
    }

    public void ucitajAktuelneKompanije() {
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Paket");
        listaSvihPaketa = query.list();
        sesija.getTransaction().commit();
        sesija.close();
        pretragaKomp();
        return;
        /*listaSvihKomp = new ArrayList<>();
        
        List<Novcaniug> listaNovcaniSvi;
        List<Donatorskiug> listaDonatorskiSvi;

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Novcaniug");
        listaNovcaniSvi = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query2 = sesija.createQuery("FROM beans.Donatorskiug");
        listaDonatorskiSvi = query2.list();
        sesija.getTransaction().commit();
        sesija.close();

        for (Novcaniug n : listaNovcaniSvi) {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Kompanija k = (Kompanija) sesija.get(Kompanija.class, n.getIdkomp());
            sesija.close();
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Paket p = (Paket) sesija.get(Paket.class, n.getIdpaket());
            sesija.close();
            k.setNazivpaketa(p.getNaziv());
            listaSvihKomp.add(k);
        }
        
        for (Donatorskiug n : listaDonatorskiSvi) {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Kompanija k = (Kompanija) sesija.get(Kompanija.class, n.getIdkomp());
            sesija.close();
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Paket p = (Paket) sesija.get(Paket.class, n.getIdpaket());
            sesija.close();
            k.setNazivpaketa(p.getNaziv());
            listaSvihKomp.add(k);
        }*/

    }

    private String pretragaNaziv = "";

    public String getPretragaNaziv() {
        return pretragaNaziv;
    }

    public void setPretragaNaziv(String pretragaNaziv) {
        this.pretragaNaziv = pretragaNaziv;
    }

    public String pretragaKomp() {
        listaSvihKomp = new ArrayList<>();

        List<Novcaniug> listaNovcaniSvi;
        List<Donatorskiug> listaDonatorskiSvi;

        Date danas = new Date();
        java.sql.Date sqldanas = new java.sql.Date(danas.getTime());

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Novcaniug WHERE datumisticanjapaketa > '" + sqldanas + "'");
        listaNovcaniSvi = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query2 = sesija.createQuery("FROM beans.Donatorskiug WHERE datumisticanjapaketa > '" + sqldanas + "'");
        listaDonatorskiSvi = query2.list();
        sesija.getTransaction().commit();
        sesija.close();

        for (Novcaniug n : listaNovcaniSvi) {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query3 = sesija.createQuery("FROM beans.Kompanija k WHERE k.idkompanija = '" + n.getIdkomp() + "' AND k.naziv LIKE '%" + pretragaNaziv + "%'");
            List<Kompanija> kl = query3.list();
            sesija.close();
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Paket p = (Paket) sesija.get(Paket.class, n.getIdpaket());
            sesija.close();
            if (!kl.isEmpty()) {
                Kompanija k = kl.get(0);
                k.setNazivpaketa(p.getNaziv());
                k.setOpisKompanije("Adresa: " + k.getAdresa() + " Grad: " + k.getGrad() + " Zemlja: " + k.getZemlja());
                listaSvihKomp.add(k);
            }

        }

        for (Donatorskiug n : listaDonatorskiSvi) {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query3 = sesija.createQuery("FROM beans.Kompanija k WHERE k.idkompanija = '" + n.getIdkomp() + "' AND k.naziv LIKE '%" + pretragaNaziv + "%'");
            List<Kompanija> kl = query3.list();
            sesija.close();
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Paket p = (Paket) sesija.get(Paket.class, n.getIdpaket());
            sesija.close();
            if (!kl.isEmpty()) {
                Kompanija k = kl.get(0);
                k.setNazivpaketa(p.getNaziv());
                k.setOpisKompanije("Adresa: " + k.getAdresa() + " Grad: " + k.getGrad() + " Zemlja: " + k.getZemlja());
                listaSvihKomp.add(k);
            }
        }
        return "";
    }

    public String ucitajPakete(long idpaket) {
        listaSvihKomp = new ArrayList<>();

        Date danas = new Date();
        java.sql.Date sqldanas = new java.sql.Date(danas.getTime());

        List<Novcaniug> listaNovcaniSvi;
        List<Donatorskiug> listaDonatorskiSvi;

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Novcaniug n WHERE n.idpaket = '" + idpaket + "' AND n.datumisticanjapaketa > '" + sqldanas + "'");
        listaNovcaniSvi = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query2 = sesija.createQuery("FROM beans.Donatorskiug d WHERE d.idpaket = '" + idpaket + "' AND d.datumisticanjapaketa > '" + sqldanas + "'");
        listaDonatorskiSvi = query2.list();
        sesija.getTransaction().commit();
        sesija.close();

        for (Novcaniug n : listaNovcaniSvi) {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Kompanija k = (Kompanija) sesija.get(Kompanija.class, n.getIdkomp());
            sesija.close();
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Paket p = (Paket) sesija.get(Paket.class, idpaket);
            sesija.close();
            k.setNazivpaketa(p.getNaziv());
            k.setOpisKompanije("Adresa: " + k.getAdresa() + " Grad: " + k.getGrad() + " Zemlja: " + k.getZemlja());
            listaSvihKomp.add(k);
        }

        for (Donatorskiug n : listaDonatorskiSvi) {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Kompanija k = (Kompanija) sesija.get(Kompanija.class, n.getIdkomp());
            sesija.close();
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Paket p = (Paket) sesija.get(Paket.class, idpaket);
            sesija.close();
            k.setNazivpaketa(p.getNaziv());
            k.setOpisKompanije("Adresa: " + k.getAdresa() + " Grad: " + k.getGrad() + " Zemlja: " + k.getZemlja());
            listaSvihKomp.add(k);
        }
        return "";
    }

    private boolean dobraExt(String ext) {
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("tiff") || ext.equalsIgnoreCase("ai") || ext.equalsIgnoreCase("eps") || ext.equalsIgnoreCase("pdf")) {
            return true;
        } else {
            return false;
        }
    }
}
