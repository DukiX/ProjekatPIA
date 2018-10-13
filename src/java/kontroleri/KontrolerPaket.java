/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Paket;
import beans.Paketstavka;
import beans.Stavka;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtilProjekat;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class KontrolerPaket implements Serializable{

    private long idpaket;
    private String naziv;
    private double vrednost;
    private int trajanje;
    private int maxbrojgodisnje;

    private String poruka;

    public KontrolerPaket() {
    }

    public long getIdpaket() {
        return idpaket;
    }

    public void setIdpaket(long idpaket) {
        this.idpaket = idpaket;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getMaxbrojgodisnje() {
        return maxbrojgodisnje;
    }

    public void setMaxbrojgodisnje(int maxbrojgodisnje) {
        this.maxbrojgodisnje = maxbrojgodisnje;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    private Session sesija;

    public String dodajPaket() {
        if (izabraneStavke.isEmpty()) {
            poruka = "Morate izabrati bar jednu stavku";
            return "formiranjepaketa";
        }
        Paket p = new Paket(naziv, vrednost, trajanje, maxbrojgodisnje);
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(p);
            sesija.getTransaction().commit();
            sesija.close();

            long idPa = p.getIdpaket();

            for (String stavka : izabraneStavke) {
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Query query = sesija.createQuery("FROM beans.Stavka k WHERE k.naziv = '" + stavka + "'");
                List<Stavka> privremena = query.list();
                sesija.getTransaction().commit();
                sesija.close();

                long idSt = privremena.get(0).getIdstavka();

                Paketstavka ps = new Paketstavka(idPa, idSt);
                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                sesija.save(ps);
                sesija.getTransaction().commit();
                sesija.close();

            }

            poruka = "Uspesno dodavanje paketa";
            return "formiranjepaketa";
        } catch (Exception e) {
            e.printStackTrace();
            poruka = "Greska pri dodavanju paketa";
            return "formiranjepaketa";
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    private List<String> izabraneStavke;

    public List<String> getIzabraneStavke() {
        return izabraneStavke;
    }

    public void setIzabraneStavke(List<String> izabraneStavke) {
        this.izabraneStavke = izabraneStavke;
    }

}
