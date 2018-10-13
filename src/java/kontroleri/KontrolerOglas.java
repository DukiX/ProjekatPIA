/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Donatorskiug;
import beans.Kompanija;
import beans.Kontakt;
import beans.Novcaniug;
import beans.Oglas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
public class KontrolerOglas implements Serializable {

    private long idoglas;
    private String naslov;
    private String opis;
    private boolean praksa;
    private boolean posao;
    private Date vremeunosenja;
    private String oglasfajl;
    private long idkompanija;

    private String poruka = "";

    UploadedFile oglasFileUploaded;

    public String getOglasfajl() {
        return oglasfajl;
    }

    public void setOglasfajl(String oglasfajl) {
        this.oglasfajl = oglasfajl;
    }

    public UploadedFile getOglasFileUploaded() {
        return oglasFileUploaded;
    }

    public void setOglasFileUploaded(UploadedFile oglasFileUploaded) {
        this.oglasFileUploaded = oglasFileUploaded;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public KontrolerOglas() {
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

    public boolean isPraksa() {
        return praksa;
    }

    public void setPraksa(boolean praksa) {
        this.praksa = praksa;
    }

    public boolean isPosao() {
        return posao;
    }

    public void setPosao(boolean posao) {
        this.posao = posao;
    }

    public Date getVremeunosenja() {
        return vremeunosenja;
    }

    public void setVremeunosenja(Date vremeunosenja) {
        this.vremeunosenja = vremeunosenja;
    }

    public long getIdkompanija() {
        return idkompanija;
    }

    public void setIdkompanija(long idkompanija) {
        this.idkompanija = idkompanija;
    }

    Session sesija;

    public String unosOglasa() {
        vremeunosenja = new Date();
        int praksaInt;
        int posaoInt;
        if (!posao && !praksa) {
            poruka = "Mora bar jedna opcija biti obelezena(posao il praksa)";
            return "unosoglasa";
        }
        if (praksa) {
            praksaInt = 1;
        } else {
            praksaInt = 0;
        }
        if (posao) {
            posaoInt = 1;
        } else {
            posaoInt = 0;
        }

        byte[] oglasFajl = oglasFileUploaded.getContents();
        if (oglasFajl != null) {
            if (oglasFajl.length > 50) {
                try {
                    String ext = "";
                    String fileName = oglasFileUploaded.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff,pdf";
                        return "";
                    }
                    if (oglasFajl.length > 3000000) {
                        poruka = "Preveliki fajl! Maksimalna velicina 3MB";
                        return "";
                    }
                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "oglas" + naslov + "." + ext;
                    oglasfajl = "oglas" + naslov + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(oglasFajl);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Kompanija k WHERE k.naziv = '" + izabranaKompanija + "'");
        List<Kompanija> listaKomp = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        idkompanija = listaKomp.get(0).getIdkompanija();

        Oglas oglas = new Oglas(naslov, opis, praksaInt, posaoInt, vremeunosenja, oglasfajl, idkompanija);
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(oglas);
            sesija.getTransaction().commit();
            poruka = "Uspesno unet oglas";
            return "unosoglasa";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unosoglasa";
        } finally {
            sesija.close();
        }
    }

    List<Kompanija> listaMojihKompanija;

    public List<Kompanija> getListaMojihKompanija() {
        return listaMojihKompanija;
    }

    public void setListaMojihKompanija(List<Kompanija> listaMojihKompanija) {
        this.listaMojihKompanija = listaMojihKompanija;
    }

    List<SelectItem> listaZaBiranje;

    public List<SelectItem> getListaZaBiranje() {
        return listaZaBiranje;
    }

    public void setListaZaBiranje(List<SelectItem> listaZaBiranje) {
        this.listaZaBiranje = listaZaBiranje;
    }

    public void ucitajMojeKompanije() {

        listaMojihKompanija = new ArrayList<>();
        listaZaBiranje = new ArrayList<>();

        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        Kontroler1 kontroler1 = (Kontroler1) elContext.getELResolver().getValue(elContext, null, "kontroler1");

        String username = kontroler1.getUsername();

        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Kontakt k WHERE k.username = '" + username + "'");
            List<Kontakt> listaKontakata = query.list();
            sesija.getTransaction().commit();
            sesija.close();

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();

            for (Kontakt kontakt : listaKontakata) {
                Kompanija kompanija = (Kompanija) sesija.get(Kompanija.class, kontakt.getIdkompanija());
                listaMojihKompanija.add(kompanija);
            }
            sesija.close();
            for (Kompanija komp : listaMojihKompanija) {

                if (aktivanUgovor(komp.getIdkompanija())) {

                    SelectItem sel = new SelectItem(komp.getNaziv());
                    if (!listaZaBiranje.contains(sel)) {
                        listaZaBiranje.add(sel);
                    }

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

    private String izabranaKompanija;

    public String getIzabranaKompanija() {
        return izabranaKompanija;
    }

    public void setIzabranaKompanija(String izabranaKompanija) {
        this.izabranaKompanija = izabranaKompanija;
    }

    private List<SelectItem> listaKompanija;
    private List<Kompanija> listaKompanijaObjekata;

    public List<SelectItem> getListaKompanija() {
        return listaKompanija;
    }

    public void setListaKompanija(List<SelectItem> listaKompanija) {
        this.listaKompanija = listaKompanija;
    }

    public List<Kompanija> getListaKompanijaObjekata() {
        return listaKompanijaObjekata;
    }

    public void setListaKompanijaObjekata(List<Kompanija> listaKompanijaObjekata) {
        this.listaKompanijaObjekata = listaKompanijaObjekata;
    }

    public void ucitajSveKompanije() {
        listaKompanija = new ArrayList<>();
        listaKompanijaObjekata = new ArrayList<>();
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Kompanija");
            listaKompanijaObjekata = query.list();
            sesija.getTransaction().commit();
            sesija.close();

            for (Kompanija kom : listaKompanijaObjekata) {

                if (aktivanUgovor(kom.getIdkompanija())) {

                    SelectItem sel = new SelectItem(kom.getNaziv());
                    if (!listaKompanija.contains(sel)) {
                        listaKompanija.add(sel);
                    }
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

    public String unosOglasaIt() {
        vremeunosenja = new Date();
        int praksaInt;
        int posaoInt;
        if (!posao && !praksa) {
            poruka = "Mora bar jedna opcija biti obelezena(posao il praksa)";
            return "unosoglasait";
        }
        if (praksa) {
            praksaInt = 1;
        } else {
            praksaInt = 0;
        }
        if (posao) {
            posaoInt = 1;
        } else {
            posaoInt = 0;
        }

        byte[] oglasFajl = oglasFileUploaded.getContents();
        if (oglasFajl != null) {
            if (oglasFajl.length > 50) {
                try {
                    String ext = "";
                    String fileName = oglasFileUploaded.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff,pdf";
                        return "";
                    }
                    if (oglasFajl.length > 3000000) {
                        poruka = "Preveliki fajl! Maksimalna velicina 3MB";
                        return "";
                    }
                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "oglas" + naslov + "." + ext;
                    oglasfajl = "oglas" + naslov + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(oglasFajl);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Kompanija k WHERE k.naziv = '" + izabranaKompanija + "'");
        List<Kompanija> listaKomp = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        idkompanija = listaKomp.get(0).getIdkompanija();

        Oglas oglas = new Oglas(naslov, opis, praksaInt, posaoInt, vremeunosenja, oglasfajl, idkompanija);
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(oglas);
            sesija.getTransaction().commit();
            poruka = "Uspesno unet oglas";
            return "unosoglasait";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unosoglasait";
        } finally {
            sesija.close();
        }
    }

    public String unosOglasaAd() {
        vremeunosenja = new Date();
        int praksaInt;
        int posaoInt;
        if (!posao && !praksa) {
            poruka = "Mora bar jedna opcija biti obelezena(posao il praksa)";
            return "unosoglasaad";
        }
        if (praksa) {
            praksaInt = 1;
        } else {
            praksaInt = 0;
        }
        if (posao) {
            posaoInt = 1;
        } else {
            posaoInt = 0;
        }

        byte[] oglasFajl = oglasFileUploaded.getContents();
        if (oglasFajl != null) {
            if (oglasFajl.length > 50) {
                try {
                    String ext = "";
                    String fileName = oglasFileUploaded.getFileName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0) {
                        ext = fileName.substring(i + 1);
                    }
                    if (!dobraExt(ext)) {
                        poruka = "Nedozvoljena ekstenzija slike! Dozvoljeni: jpg, png, tiff,pdf";
                        return "";
                    }
                    if (oglasFajl.length > 3000000) {
                        poruka = "Preveliki fajl! Maksimalna velicina 3MB";
                        return "";
                    }

                    String putanjaSlike = FajlKlasa.dohvatiPutanjuSlike();
                    putanjaSlike = putanjaSlike + "\\" + "oglas" + naslov + "." + ext;
                    oglasfajl = "oglas" + naslov + "." + ext;
                    File fajl = new File(putanjaSlike);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(oglasFajl);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Kompanija k WHERE k.naziv = '" + izabranaKompanija + "'");
        List<Kompanija> listaKomp = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        idkompanija = listaKomp.get(0).getIdkompanija();

        Oglas oglas = new Oglas(naslov, opis, praksaInt, posaoInt, vremeunosenja, oglasfajl, idkompanija);
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(oglas);
            sesija.getTransaction().commit();
            poruka = "Uspesno unet oglas";
            return "unosoglasaad";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "unosoglasaad";
        } finally {
            sesija.close();
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

    private List<Oglas> listaSvihOglasa;

    public List<Oglas> getListaSvihOglasa() {
        return listaSvihOglasa;
    }

    public void setListaSvihOglasa(List<Oglas> listaSvihOglasa) {
        this.listaSvihOglasa = listaSvihOglasa;
    }

    public void ucitajSveOglase() {
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Oglas");
        listaSvihOglasa = query.list();
        sesija.getTransaction().commit();
        sesija.close();
        for (Oglas o : listaSvihOglasa) {
            if (o.getOglasfajl() != null) {
                if (!o.getOglasfajl().equals("")) {
                    try {
                        String putanjaFajl = FajlKlasa.dohvatiPutanjuSlike();
                        putanjaFajl = putanjaFajl + "\\" + o.getOglasfajl();
                        File fajl = new File(putanjaFajl);
                        InputStream stream = new FileInputStream(fajl);
                        o.setFile(new DefaultStreamedContent(stream, null, o.getOglasfajl()));
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
