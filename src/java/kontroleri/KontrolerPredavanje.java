/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Kompanija;
import beans.Predavanje;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import util.FajlKlasa;
import util.HibernateUtilProjekat;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class KontrolerPredavanje implements Serializable {

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

    private UploadedFile unetiFajl;

    private String poruka = "";

    public KontrolerPredavanje() {
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

    public UploadedFile getUnetiFajl() {
        return unetiFajl;
    }

    public void setUnetiFajl(UploadedFile unetiFajl) {
        this.unetiFajl = unetiFajl;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public long getIdkompanije() {
        return idkompanije;
    }

    public void setIdkompanije(long idkompanije) {
        this.idkompanije = idkompanije;
    }

    private Session sesija;
    private String izabranaKompanija;

    public String getIzabranaKompanija() {
        return izabranaKompanija;
    }

    public void setIzabranaKompanija(String izabranaKompanija) {
        this.izabranaKompanija = izabranaKompanija;
    }

    public String unesiPredavanje() {
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Kompanija k WHERE k.naziv = '" + izabranaKompanija + "'");
        List<Kompanija> listaKomp = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        idkompanije = listaKomp.get(0).getIdkompanija();

        byte[] predavanjeFajl = unetiFajl.getContents();
        if (predavanjeFajl != null) {
            if (predavanjeFajl.length > 50) {
                try {
                    String ext = "";
                    String fileName = unetiFajl.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff,pdf";
                        return "";
                    }
                    if (predavanjeFajl.length > 3000000) {
                        poruka = "Preveliki fajl! Maksimalna velicina 3MB";
                        return "";
                    }

                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "predavanje" + naslovsrp + "." + ext;
                    fajl = "predavanje" + naslovsrp + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(predavanjeFajl);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Predavanje p = new Predavanje(naslovsrp, nasloveng, opissrp, opiseng, datum, vreme, sala, imepredavaca, biopredavaca, fajl, idkompanije);

        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(p);
            sesija.getTransaction().commit();
            poruka = "Uspesno uneto predavanje";
            return "unosopredavanja";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unospredavanja";
        } finally {
            sesija.close();
        }
    }

    public String unesiPredavanjeIt() {
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Kompanija k WHERE k.naziv = '" + izabranaKompanija + "'");
        List<Kompanija> listaKomp = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        idkompanije = listaKomp.get(0).getIdkompanija();
        byte[] predavanjeFajl = unetiFajl.getContents();
        if (predavanjeFajl != null) {
            if (predavanjeFajl.length > 50) {
                try {
                    String ext = "";
                    String fileName = unetiFajl.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff,pdf";
                        return "";
                    }
                    if (predavanjeFajl.length > 3000000) {
                        poruka = "Preveliki fajl! Maksimalna velicina 3MB";
                        return "";
                    }

                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "predavanje" + naslovsrp + "." + ext;
                    fajl = "predavanje" + naslovsrp + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(predavanjeFajl);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Predavanje p = new Predavanje(naslovsrp, nasloveng, opissrp, opiseng, datum, vreme, sala, imepredavaca, biopredavaca, fajl, idkompanije);

        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(p);
            sesija.getTransaction().commit();
            poruka = "Uspesno uneto predavanje";
            return "unospredavanjait";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unospredavanjait";
        } finally {
            sesija.close();
        }
    }

    public String unesiPredavanjeAd() {
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Kompanija k WHERE k.naziv = '" + izabranaKompanija + "'");
        List<Kompanija> listaKomp = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        idkompanije = listaKomp.get(0).getIdkompanija();
        byte[] predavanjeFajl = unetiFajl.getContents();
        if (predavanjeFajl != null) {
            if (predavanjeFajl.length > 50) {
                try {
                    String ext = "";
                    String fileName = unetiFajl.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff,pdf";
                        return "";
                    }
                    if (predavanjeFajl.length > 3000000) {
                        poruka = "Preveliki fajl! Maksimalna velicina 3MB";
                        return "";
                    }

                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "predavanje" + naslovsrp + "." + ext;
                    fajl = "predavanje" + naslovsrp + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(predavanjeFajl);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Predavanje p = new Predavanje(naslovsrp, nasloveng, opissrp, opiseng, datum, vreme, sala, imepredavaca, biopredavaca, fajl, idkompanije);

        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(p);
            sesija.getTransaction().commit();
            poruka = "Uspesno uneto predavanje";
            return "unosopredavanjaad";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unospredavanjaad";
        } finally {
            sesija.close();
        }
    }

    private List<Predavanje> listaBuducihPredavanja;

    public List<Predavanje> getListaBuducihPredavanja() {
        return listaBuducihPredavanja;
    }

    public void setListaBuducihPredavanja(List<Predavanje> listaBuducihPredavanja) {
        this.listaBuducihPredavanja = listaBuducihPredavanja;
    }

    private List<Predavanje> listaProslihPredavanja;

    public List<Predavanje> getListaProslihPredavanja() {
        return listaProslihPredavanja;
    }

    public void setListaProslihPredavanja(List<Predavanje> listaProslihPredavanja) {
        this.listaProslihPredavanja = listaProslihPredavanja;
    }

    public void ucitajPredavanja() {
        Date danas = new Date();
        java.sql.Date dat = new java.sql.Date(danas.getTime());
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Predavanje p WHERE p.datum >= '" + dat + "'");
        listaBuducihPredavanja = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query2 = sesija.createQuery("FROM beans.Predavanje p WHERE p.datum < '" + dat + "'");
        listaProslihPredavanja = query2.list();
        sesija.getTransaction().commit();
        sesija.close();

        for (Predavanje p : listaBuducihPredavanja) {
            if (p.getFajl() != null) {
                if (!p.getFajl().equals("")) {
                    try {
                        String putanjaFajl = FajlKlasa.dohvatiPutanjuSlike();
                        putanjaFajl = putanjaFajl + "\\" + p.getFajl();
                        File fajlP = new File(putanjaFajl);
                        InputStream stream = new FileInputStream(fajlP);
                        p.setStreamedfile(new DefaultStreamedContent(stream, null, p.getFajl()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (Predavanje p : listaProslihPredavanja) {
            if (p.getFajl() != null) {
                if (!p.getFajl().equals("")) {
                    try {
                        String putanjaFajl = FajlKlasa.dohvatiPutanjuSlike();
                        putanjaFajl = putanjaFajl + "\\" + p.getFajl();
                        File fajlP = new File(putanjaFajl);
                        InputStream stream = new FileInputStream(fajlP);
                        p.setStreamedfile(new DefaultStreamedContent(stream, null, p.getFajl()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void ucitajStreamPredavanja(){
        for (Predavanje p : listaBuducihPredavanja) {
            if (p.getFajl() != null) {
                if (!p.getFajl().equals("")) {
                    try {
                        String putanjaFajl = FajlKlasa.dohvatiPutanjuSlike();
                        putanjaFajl = putanjaFajl + "\\" + p.getFajl();
                        File fajlP = new File(putanjaFajl);
                        InputStream stream = new FileInputStream(fajlP);
                        p.setStreamedfile(new DefaultStreamedContent(stream, null, p.getFajl()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (Predavanje p : listaProslihPredavanja) {
            if (p.getFajl() != null) {
                if (!p.getFajl().equals("")) {
                    try {
                        String putanjaFajl = FajlKlasa.dohvatiPutanjuSlike();
                        putanjaFajl = putanjaFajl + "\\" + p.getFajl();
                        File fajlP = new File(putanjaFajl);
                        InputStream stream = new FileInputStream(fajlP);
                        p.setStreamedfile(new DefaultStreamedContent(stream, null, p.getFajl()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean dobraExt(String ext) {
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("tiff") || ext.equalsIgnoreCase("pdf")) {
            return true;
        } else {
            return false;
        }
    }

}
