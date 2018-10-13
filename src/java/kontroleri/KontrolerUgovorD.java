/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Donatorskiug;
import beans.Novcaniug;
import beans.Paket;
import beans.Paketstavka;
import beans.Stavka;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtilProjekat;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class KontrolerUgovorD implements Serializable {

    private long iddonatorskiug;
    private long idkomp;
    private long idpaket;
    private double procvrednost;
    private String opis;
    private int kolicina;
    private Date datumugovora;
    private long idstatus;
    private Date datumisporuke;
    private String komentar;
    private Date datumisticanjapaketa;
    private String poruka;

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public KontrolerUgovorD() {
    }

    public long getIddonatorskiug() {
        return iddonatorskiug;
    }

    public void setIddonatorskiug(long iddonatorskiug) {
        this.iddonatorskiug = iddonatorskiug;
    }

    public long getIdkomp() {
        return idkomp;
    }

    public void setIdkomp(long idkomp) {
        this.idkomp = idkomp;
    }

    public long getIdpaket() {
        return idpaket;
    }

    public void setIdpaket(long idpaket) {
        this.idpaket = idpaket;
    }

    public double getProcvrednost() {
        return procvrednost;
    }

    public void setProcvrednost(double procvrednost) {
        this.procvrednost = procvrednost;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Date getDatumugovora() {
        return datumugovora;
    }

    public void setDatumugovora(Date datumugovora) {
        this.datumugovora = datumugovora;
    }

    public long getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(long idstatus) {
        this.idstatus = idstatus;
    }

    public Date getDatumisporuke() {
        return datumisporuke;
    }

    public void setDatumisporuke(Date datumisporuke) {
        this.datumisporuke = datumisporuke;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Date getDatumisticanjapaketa() {
        return datumisticanjapaketa;
    }

    public void setDatumisticanjapaketa(Date datumisticanjapaketa) {
        this.datumisticanjapaketa = datumisticanjapaketa;
    }

    private Session sesija;

    private Long izabranaKompanija;
    private Long izabraniPaket;
    private Long izabraniStatus;

    public Long getIzabranaKompanija() {
        return izabranaKompanija;
    }

    public void setIzabranaKompanija(Long izabranaKompanija) {
        this.izabranaKompanija = izabranaKompanija;
    }

    public Long getIzabraniPaket() {
        return izabraniPaket;
    }

    public void setIzabraniPaket(Long izabraniPaket) {
        this.izabraniPaket = izabraniPaket;
    }

    public Long getIzabraniStatus() {
        return izabraniStatus;
    }

    public void setIzabraniStatus(Long izabraniStatus) {
        this.izabraniStatus = izabraniStatus;
    }

    public String unosUgovora() {
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Paket p = (Paket) sesija.get(Paket.class, izabraniPaket);
        sesija.close();

        int trajanjeGod = p.getTrajanje();

        Calendar cal = Calendar.getInstance();
        cal.setTime(datumugovora);
        cal.add(Calendar.YEAR, trajanjeGod);
        datumisticanjapaketa = cal.getTime();

        try {
            Donatorskiug du = new Donatorskiug(izabranaKompanija, izabraniPaket, procvrednost, opis, kolicina, datumugovora, izabraniStatus, datumisporuke, komentar, datumisticanjapaketa);
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(du);
            sesija.getTransaction().commit();
            sesija.close();

            poruka = "Uspesno unet ugovor";
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska";
            return "";
        }
    }

    private List<Novcaniug> listaNovcani;
    private List<Donatorskiug> listaDonatorski;

    public List<Donatorskiug> getListaDonatorski() {
        return listaDonatorski;
    }

    public void setListaDonatorski(List<Donatorskiug> listaDonatorski) {
        this.listaDonatorski = listaDonatorski;
    }

    public List<Novcaniug> getListaNovcani() {
        return listaNovcani;
    }

    public void setListaNovcani(List<Novcaniug> listaNovcani) {
        this.listaNovcani = listaNovcani;
    }

    public void ucitajMojePakete() {

        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        KontrolerKomp kontrolerKomp = (KontrolerKomp) elContext.getELResolver().getValue(elContext, null, "kontrolerKomp");

        long idkompanije = kontrolerKomp.getIdkompanija();
        kontrolerKomp.setPoruka("");

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Novcaniug n WHERE n.idkomp = '" + idkompanije + "'");
        listaNovcani = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        Date danas = new Date();

        for (Novcaniug nov : listaNovcani) {
            if (nov.getDatumisticanjapaketa().after(danas)) {
                nov.setIstekao(false);
            } else {
                nov.setIstekao(true);
            }
        }

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query2 = sesija.createQuery("FROM beans.Donatorskiug d WHERE d.idkomp = '" + idkompanije + "'");
        listaDonatorski = query2.list();
        sesija.getTransaction().commit();
        sesija.close();

        for (Donatorskiug don : listaDonatorski) {
            if (don.getDatumisticanjapaketa().after(danas)) {
                don.setIstekao(false);
            } else {
                don.setIstekao(true);
            }
        }

    }

    private List<Novcaniug> listaNovcaniSvi;

    public List<Novcaniug> getListaNovcaniSvi() {
        return listaNovcaniSvi;
    }

    public void setListaNovcaniSvi(List<Novcaniug> listaNovcaniSvi) {
        this.listaNovcaniSvi = listaNovcaniSvi;
    }

    private List<Donatorskiug> listaDonatorskiSvi;

    public List<Donatorskiug> getListaDonatorskiSvi() {
        return listaDonatorskiSvi;
    }

    public void setListaDonatorskiSvi(List<Donatorskiug> listaDonatorskiSvi) {
        this.listaDonatorskiSvi = listaDonatorskiSvi;
    }

    public void ucitajPakete() {
        /*Date danas = new Date();
        java.sql.Date dat = new java.sql.Date(danas.getTime());
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Novcaniug n WHERE n.datumisticanjapaketa >= '" + dat + "'");
        listaNovcaniSvi = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query2 = sesija.createQuery("FROM beans.Donatorskiug d WHERE d.datumisticanjapaketa >= '" + dat + "'");
        listaDonatorskiSvi = query2.list();
        sesija.getTransaction().commit();
        sesija.close();

        List<Paket> paketi = new ArrayList<>();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query3 = sesija.createQuery("FROM beans.Paket");
        paketi = query3.list();
        sesija.getTransaction().commit();
        sesija.close();

        for (Donatorskiug d : listaDonatorskiSvi) {
            for (Paket p : paketi) {
                if (d.getIdpaket() == p.getIdpaket()) {
                    d.setNaziv(p.getNaziv());
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query4 = sesija.createQuery("FROM beans.Paketstavka WHERE idpaket = '"+d.getIdpaket()+"'");
                    List<Paketstavka> pslist = query4.list();
                    sesija.getTransaction().commit();
                    sesija.close();
                    StringBuilder sb = new StringBuilder();
                    for (Paketstavka ps : pslist) {
                        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                        sesija.beginTransaction();
                        Stavka st = (Stavka) sesija.get(Stavka.class, ps.getIdstavka());
                        sesija.close();
                        sb.append(st.getOpis());
                        sb.append(", \n");
                    }
                    d.setStavke(sb.toString());
                    break;
                }
            }
        } 

        for (Novcaniug n : listaNovcaniSvi) {
            for (Paket p : paketi) {
                if (n.getIdpaket() == p.getIdpaket()) {
                    n.setNaziv(p.getNaziv());
                    sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                    sesija.beginTransaction();
                    Query query4 = sesija.createQuery("FROM beans.Paketstavka WHERE idpaket = '"+n.getIdpaket()+"'");
                    List<Paketstavka> pslist = query4.list();
                    sesija.getTransaction().commit();
                    sesija.close();
                    StringBuilder sb = new StringBuilder();
                    for (Paketstavka ps : pslist) {
                        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                        sesija.beginTransaction();
                        Stavka st = (Stavka) sesija.get(Stavka.class, ps.getIdstavka());
                        sesija.close();
                        sb.append(st.getOpis());
                        sb.append(", \n");
                    }
                    n.setStavke(sb.toString());
                    break;
                }
            }
        }*/

        paketi = new ArrayList<>();

        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query3 = sesija.createQuery("FROM beans.Paket");
        paketi = query3.list();
        sesija.getTransaction().commit();
        sesija.close();

        for (Paket p : paketi) {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query4 = sesija.createQuery("FROM beans.Paketstavka WHERE idpaket = '" + p.getIdpaket() + "'");
            List<Paketstavka> pslist = query4.list();
            sesija.getTransaction().commit();
            sesija.close();
            StringBuilder sb = new StringBuilder();
            for (Paketstavka ps : pslist) {
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Stavka st = (Stavka) sesija.get(Stavka.class, ps.getIdstavka());
                sesija.close();
                sb.append(st.getOpis());
                sb.append(", \n");
            }
            p.setStavke(sb.toString());
        }
    }
    private List<Paket> paketi;

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }
}
