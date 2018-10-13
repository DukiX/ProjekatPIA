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
import beans.Paket;
import beans.PrikazKlasa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;
import util.HibernateUtilProjekat;

/**
 *
 * @author Dusan
 */
@ManagedBean
@SessionScoped
public class TimelinePregled implements Serializable {

    private TimelineModel model;

    public TimelineModel getModel() {
        return model;
    }

    public void setModel(TimelineModel model) {
        this.model = model;
    }

    public TimelinePregled() {
    }

    private Session sesija;

    private List<Novcaniug> listaNovcani;
    private List<Donatorskiug> listaDonatorski;

    public List<Novcaniug> getListaNovcani() {
        return listaNovcani;
    }

    public void setListaNovcani(List<Novcaniug> listaNovcani) {
        this.listaNovcani = listaNovcani;
    }

    public List<Donatorskiug> getListaDonatorski() {
        return listaDonatorski;
    }

    public void setListaDonatorski(List<Donatorskiug> listaDonatorski) {
        this.listaDonatorski = listaDonatorski;
    }

    private String poruka;

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    private Date min;
    private Date max;

    public Date getMin() {
        return min;
    }

    public void setMin(Date min) {
        this.min = min;
    }

    public Date getMax() {
        return max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

    public void formirajTabelu() {

        listPom = new ArrayList<>();
        listPomIstekli = new ArrayList<>();

        zoomMin = 1000L * 60 * 60 * 24 * 31 * 3;

        zoomMax = 1000L * 60 * 60 * 24 * 31 * 12 * 4;

        min = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        max = cal.getTime();

        model = new TimelineModel();
        cal = Calendar.getInstance();
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Novcaniug");
            listaNovcani = query.list();
            sesija.getTransaction().commit();
            sesija.close();

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query2 = sesija.createQuery("FROM beans.Donatorskiug");
            listaDonatorski = query2.list();
            sesija.getTransaction().commit();
            sesija.close();

            for (Novcaniug nov : listaNovcani) {

                PrikazKlasa pom = new PrikazKlasa();
                pom.setIdPaket(nov.getIdpaket());
                pom.setIdKomp(nov.getIdkomp());

                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Kompanija komPom = (Kompanija) sesija.get(Kompanija.class, nov.getIdkomp());
                pom.setNazivKomp(komPom.getNaziv());
                sesija.getTransaction().commit();
                sesija.close();

                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Paket pakPom = (Paket) sesija.get(Paket.class, nov.getIdpaket());
                pom.setNazivPaket(pakPom.getNaziv());
                sesija.getTransaction().commit();
                sesija.close();

                Date datumIsticanja = nov.getDatumisticanjapaketa();
                cal.setTime(datumIsticanja);
                model.add(new TimelineEvent(pom, cal.getTime()));
            }

            for (Donatorskiug nov : listaDonatorski) {

                PrikazKlasa pom = new PrikazKlasa();
                pom.setIdPaket(nov.getIdpaket());
                pom.setIdKomp(nov.getIdkomp());

                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Kompanija komPom = (Kompanija) sesija.get(Kompanija.class, nov.getIdkomp());
                pom.setNazivKomp(komPom.getNaziv());
                sesija.getTransaction().commit();
                sesija.close();

                sesija = HibernateUtilProjekat.getSessionFactory().openSession();
                sesija.beginTransaction();
                Paket pakPom = (Paket) sesija.get(Paket.class, nov.getIdpaket());
                pom.setNazivPaket(pakPom.getNaziv());
                sesija.getTransaction().commit();
                sesija.close();

                Date datumIsticanja = nov.getDatumisticanjapaketa();
                cal.setTime(datumIsticanja);
                model.add(new TimelineEvent(pom, cal.getTime()));
            }

            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query3 = sesija.createQuery("FROM beans.Kompanija");
            lista20 = query3.list();
            sesija.getTransaction().commit();
            sesija.close();

            Date danas = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(danas);
            c.add(Calendar.MONTH, 6);
            Date meseci6 = c.getTime();

            c.setTime(danas);
            c.add(Calendar.MONTH, -6);
            Date preMeseci6 = c.getTime();

            for (Kompanija kp : lista20) {
                List<Date> istekList = dohvatiIstek(kp.getIdkompanija());

                if (!istekList.isEmpty()) {
                    for (Date istek : istekList) {
                        if (istek.before(meseci6) && istek.after(danas)) {
                            KompanijaPomocna kompomoc = new KompanijaPomocna(kp.getIdkompanija(), kp.getNaziv(), kp.getAdresa(), kp.getGrad(), kp.getPostanskibroj(), kp.getZemlja(), kp.getZiroracun(), kp.getValuta(), kp.getPib(), kp.getTelefon1(), kp.getTelefon2(), kp.getTelefon3(), kp.getTelefon4(), kp.getTelefon5(), kp.getEmail1(), kp.getEmail2(), kp.getEmail3(), kp.getEmail4(), kp.getEmail5(), kp.getIme(), kp.getPrezime(), kp.getKontaktemail(), kp.getKontakttel(), kp.getLogolink());
                            kompomoc.setDatumIstekaPaketa(istek);
                            listPom.add(kompomoc);
                        }
                        if (istek.after(preMeseci6) && istek.before(danas)) {
                            KompanijaPomocna kompomoc = new KompanijaPomocna(kp.getIdkompanija(), kp.getNaziv(), kp.getAdresa(), kp.getGrad(), kp.getPostanskibroj(), kp.getZemlja(), kp.getZiroracun(), kp.getValuta(), kp.getPib(), kp.getTelefon1(), kp.getTelefon2(), kp.getTelefon3(), kp.getTelefon4(), kp.getTelefon5(), kp.getEmail1(), kp.getEmail2(), kp.getEmail3(), kp.getEmail4(), kp.getEmail5(), kp.getIme(), kp.getPrezime(), kp.getKontaktemail(), kp.getKontakttel(), kp.getLogolink());
                            kompomoc.setDatumIstekaPaketa(istek);
                            listPomIstekli.add(kompomoc);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Kompanija> lista20;
    private List<KompanijaPomocna> listPom;

    private List<KompanijaPomocna> listPomIstekli;

    public List<KompanijaPomocna> getListPomIstekli() {
        return listPomIstekli;
    }

    public void setListPomIstekli(List<KompanijaPomocna> listPomIstekli) {
        this.listPomIstekli = listPomIstekli;
    }

    public List<KompanijaPomocna> getListPom() {
        return listPom;
    }

    public void setListPom(List<KompanijaPomocna> listPom) {
        this.listPom = listPom;
    }

    public List<Kompanija> getLista20() {
        return lista20;
    }

    public void setLista20(List<Kompanija> lista20) {
        this.lista20 = lista20;
    }

    private long zoomMin;
    private long zoomMax;

    public long getZoomMin() {
        return zoomMin;
    }

    public void setZoomMin(long zoomMin) {
        this.zoomMin = zoomMin;
    }

    public long getZoomMax() {
        return zoomMax;
    }

    public void setZoomMax(long zoomMax) {
        this.zoomMax = zoomMax;
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

                for(Donatorskiug dug:u2){
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

                for(Donatorskiug dug:u2){
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

    private boolean isticeZaManjeOd6(long idkomp) {
        Date danas = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(danas);
        c.add(Calendar.MONTH, 6);
        Date meseci6 = c.getTime();

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

                if (u2.isEmpty()) {
                    return false;
                } else {
                    Donatorskiug du = u2.get(0);
                    Date datumist2 = du.getDatumisticanjapaketa();
                    if (meseci6.before(datumist2)) {
                        return false;
                    } else {
                        return true;
                    }
                }

            } else {
                Novcaniug nu = u.get(0);
                Date datumist = nu.getDatumisticanjapaketa();
                if (meseci6.before(datumist)) {
                    return false;
                } else {
                    return true;
                }
            }
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
    
    private KompanijaPomocna selected;

    public KompanijaPomocna getSelected() {
        return selected;
    }

    public void setSelected(KompanijaPomocna selected) {
        this.selected = selected;
    }
    
    private KompanijaPomocna selected2;

    public KompanijaPomocna getSelected2() {
        return selected2;
    }

    public void setSelected2(KompanijaPomocna selected2) {
        this.selected2 = selected2;
    }
}
