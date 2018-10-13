/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Kompanija;
import beans.Novcaniug;
import beans.Paket;
import beans.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtilProjekat;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class KontrolerUgovorN implements Serializable{

    private long idnovcaniug;
    private long idkomp;
    private long idpaket;
    private double vrednost;
    private Date datum;
    private long idstatus;
    private int faktura;
    private int uplata;
    private Date datumuplate;
    private Date datumisticanjapaketa;
    private String poruka;

    public KontrolerUgovorN() {
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public long getIdnovcaniug() {
        return idnovcaniug;
    }

    public void setIdnovcaniug(long idnovcaniug) {
        this.idnovcaniug = idnovcaniug;
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

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public long getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(long idstatus) {
        this.idstatus = idstatus;
    }

    public int getFaktura() {
        return faktura;
    }

    public void setFaktura(int faktura) {
        this.faktura = faktura;
    }

    public int getUplata() {
        return uplata;
    }

    public void setUplata(int uplata) {
        this.uplata = uplata;
    }

    public Date getDatumuplate() {
        return datumuplate;
    }

    public void setDatumuplate(Date datumuplate) {
        this.datumuplate = datumuplate;
    }

    public Date getDatumisticanjapaketa() {
        return datumisticanjapaketa;
    }

    public void setDatumisticanjapaketa(Date datumisticanjapaketa) {
        this.datumisticanjapaketa = datumisticanjapaketa;
    }

    private List<SelectItem> kompanije;
    private List<Kompanija> kompanijeObj;

    private List<SelectItem> paketi;
    private List<Paket> paketiObj;

    private List<SelectItem> statusi;
    private List<Status> statusiObj;

    public List<SelectItem> getKompanije() {
        return kompanije;
    }

    public void setKompanije(List<SelectItem> kompanije) {
        this.kompanije = kompanije;
    }

    public List<Kompanija> getKompanijeObj() {
        return kompanijeObj;
    }

    public void setKompanijeObj(List<Kompanija> kompanijeObj) {
        this.kompanijeObj = kompanijeObj;
    }

    public List<SelectItem> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<SelectItem> paketi) {
        this.paketi = paketi;
    }

    public List<Paket> getPaketiObj() {
        return paketiObj;
    }

    public void setPaketiObj(List<Paket> paketiObj) {
        this.paketiObj = paketiObj;
    }

    public List<SelectItem> getStatusi() {
        return statusi;
    }

    public void setStatusi(List<SelectItem> statusi) {
        this.statusi = statusi;
    }

    public List<Status> getStatusiObj() {
        return statusiObj;
    }

    public void setStatusiObj(List<Status> statusiObj) {
        this.statusiObj = statusiObj;
    }

    private Session sesija;

    public void ucitajPotrebno() {
        kompanije = new ArrayList<>();
        kompanijeObj = new ArrayList<>();

        paketi = new ArrayList<>();
        paketiObj = new ArrayList<>();

        statusi = new ArrayList<>();
        statusiObj = new ArrayList<>();

        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Kompanija");
            kompanijeObj = query.list();
            sesija.getTransaction().commit();
            sesija.close();

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query2 = sesija.createQuery("FROM beans.Paket");
            paketiObj = query2.list();
            sesija.getTransaction().commit();
            sesija.close();

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query3 = sesija.createQuery("FROM beans.Status");
            statusiObj = query3.list();
            sesija.getTransaction().commit();
            sesija.close();

            for (Kompanija kom : kompanijeObj) {
                SelectItem sel = new SelectItem(kom.getIdkompanija(), kom.getNaziv());
                if (!kompanije.contains(sel)) {
                    kompanije.add(sel);
                }
            }

            for (Paket pak : paketiObj) {
                SelectItem sel = new SelectItem(pak.getIdpaket(), pak.getNaziv());
                if (!paketi.contains(sel)) {
                    paketi.add(sel);
                }
            }

            for (Status stat : statusiObj) {
                SelectItem sel = new SelectItem(stat.getIdstatus(), stat.getNaziv());
                if (!statusi.contains(sel)) {
                    statusi.add(sel);
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

    private boolean fakturaB = false;
    private boolean uplataB = false;

    public boolean isFakturaB() {
        return fakturaB;
    }

    public void setFakturaB(boolean fakturaB) {
        this.fakturaB = fakturaB;
    }

    public boolean isUplataB() {
        return uplataB;
    }

    public void setUplataB(boolean uplataB) {
        this.uplataB = uplataB;
    }

    public String unosUgovora() {
        if (fakturaB) {
            faktura = 1;
        } else {
            faktura = 0;
        }
        if (uplataB) {
            uplata = 1;
        } else {
            uplata = 0;
        }
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Paket p = (Paket) sesija.get(Paket.class, izabraniPaket);
        sesija.close();

        int trajanjeGod = p.getTrajanje();
        
        vrednost = p.getVrednost();
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(datum);
        cal.add(Calendar.YEAR, trajanjeGod);
        datumisticanjapaketa = cal.getTime();

        try {
            Novcaniug nu = new Novcaniug(izabranaKompanija, izabraniPaket, vrednost, datum, izabraniStatus, faktura, uplata, datumuplate, datumisticanjapaketa);
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(nu);
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

    public boolean test() {
        if (!kompanije.isEmpty() && !statusi.isEmpty() && !paketi.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
