/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Donatorskiug;
import beans.Kompanija;
import beans.KompanijaPomocna;
import beans.Novcaniug;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class KontrolerClanTima implements Serializable{

    private List<Kompanija> listaNovijih;

    public List<Kompanija> getListaNovijih() {
        return listaNovijih;
    }

    public void setListaNovijih(List<Kompanija> listaNovijih) {
        this.listaNovijih = listaNovijih;
    }

    private Session sesija;

    private List<KompanijaPomocna> listPom;
    private List<KompanijaPomocna> listPom2;

    public List<KompanijaPomocna> getListPom2() {
        return listPom2;
    }

    public void setListPom2(List<KompanijaPomocna> listPom2) {
        this.listPom2 = listPom2;
    }

    public List<KompanijaPomocna> getListPom() {
        return listPom;
    }

    public void setListPom(List<KompanijaPomocna> listPom) {
        this.listPom = listPom;
    }

    public void ucitavanje() {
        listPom = new ArrayList<>();
        listPom2 = new ArrayList<>();
        sesija = HibernateUtilProjekat.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("FROM beans.Kompanija");
        listaNovijih = query.list();
        sesija.getTransaction().commit();
        sesija.close();

        Date danas = new Date();

        for (Kompanija kp : listaNovijih) {
            List<Date> datumPotpisivanjaList = dohvatiDatumPotpisivanja(kp.getIdkompanija());
            List<Date> istekList = dohvatiIstek(kp.getIdkompanija());

            if (!datumPotpisivanjaList.isEmpty()) {
                for (Date potpisivanje : datumPotpisivanjaList) {
                    KompanijaPomocna kompomoc = new KompanijaPomocna(kp.getIdkompanija(), kp.getNaziv(), kp.getAdresa(), kp.getGrad(), kp.getPostanskibroj(), kp.getZemlja(), kp.getZiroracun(), kp.getValuta(), kp.getPib(), kp.getTelefon1(), kp.getTelefon2(), kp.getTelefon3(), kp.getTelefon4(), kp.getTelefon5(), kp.getEmail1(), kp.getEmail2(), kp.getEmail3(), kp.getEmail4(), kp.getEmail5(), kp.getIme(), kp.getPrezime(), kp.getKontaktemail(), kp.getKontakttel(), kp.getLogolink());
                    kompomoc.setDatumPotpisivanjaPaketa(potpisivanje);
                    listPom.add(kompomoc);
                }
            }
            if (!istekList.isEmpty()) {
                for (Date istek : istekList) {
                    if (istek.after(danas)) {
                        KompanijaPomocna kompomoc = new KompanijaPomocna(kp.getIdkompanija(), kp.getNaziv(), kp.getAdresa(), kp.getGrad(), kp.getPostanskibroj(), kp.getZemlja(), kp.getZiroracun(), kp.getValuta(), kp.getPib(), kp.getTelefon1(), kp.getTelefon2(), kp.getTelefon3(), kp.getTelefon4(), kp.getTelefon5(), kp.getEmail1(), kp.getEmail2(), kp.getEmail3(), kp.getEmail4(), kp.getEmail5(), kp.getIme(), kp.getPrezime(), kp.getKontaktemail(), kp.getKontakttel(), kp.getLogolink());
                        kompomoc.setDatumIstekaPaketa(istek);
                        listPom2.add(kompomoc);
                    }
                }
            }
        }
    }

    private List<Date> dohvatiDatumPotpisivanja(long idkomp) {
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Novcaniug nu WHERE nu.idkomp = '" + idkomp + "'");
            List<Novcaniug> u = query.list();
            sesija.getTransaction().commit();
            sesija.close();
            List<Date> listaDatuma = new ArrayList<>();

            for (Novcaniug un : u) {
                listaDatuma.add(un.getDatum());
            }

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query2 = sesija.createQuery("FROM beans.Donatorskiug du WHERE du.idkomp = '" + idkomp + "'");
            List<Donatorskiug> u2 = query2.list();
            sesija.getTransaction().commit();
            sesija.close();

            for (Donatorskiug ugov : u2) {
                listaDatuma.add(ugov.getDatumugovora());
            }

            return listaDatuma;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }

    private List<Date> dohvatiIstek(long idkomp) {
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Novcaniug nu WHERE nu.idkomp = '" + idkomp + "'");
            List<Novcaniug> u = query.list();
            sesija.getTransaction().commit();
            sesija.close();
            List<Date> listaDatuma = new ArrayList<>();

            for (Novcaniug un : u) {
                listaDatuma.add(un.getDatumisticanjapaketa());
            }

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query2 = sesija.createQuery("FROM beans.Donatorskiug du WHERE du.idkomp = '" + idkomp + "'");
            List<Donatorskiug> u2 = query2.list();
            sesija.getTransaction().commit();
            sesija.close();

            for (Donatorskiug ugov : u2) {
                listaDatuma.add(ugov.getDatumisticanjapaketa());
            }

            return listaDatuma;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (sesija.isOpen()) {
                sesija.close();
            }
        }
    }
}
