/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import beans.Stavka;
import java.io.Serializable;
import java.util.ArrayList;
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
public class KontrolerStavka implements Serializable{
    private long idstavka;
    private String naziv;
    private String opis;

    public KontrolerStavka() {
    }

    public long getIdstavka() {
        return idstavka;
    }

    public void setIdstavka(long idstavka) {
        this.idstavka = idstavka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    private List<SelectItem> listaStavki;
    private List<Stavka> listaStavkiObjekata;

    public List<SelectItem> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<SelectItem> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public List<Stavka> getListaStavkiObjekata() {
        return listaStavkiObjekata;
    }

    public void setListaStavkiObjekata(List<Stavka> listaStavkiObjekata) {
        this.listaStavkiObjekata = listaStavkiObjekata;
    }
    
    private Session sesija;
    
    public void ucitajStavke(){
        listaStavki = new ArrayList<>();
        listaStavkiObjekata = new ArrayList<>();
        try {
            sesija = HibernateUtilProjekat.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("FROM beans.Stavka");
            listaStavkiObjekata = query.list();
            sesija.getTransaction().commit();
            
            for (Stavka s : listaStavkiObjekata) {
                SelectItem sel = new SelectItem(s.getNaziv());
                if (!listaStavki.contains(sel)) {
                    listaStavki.add(sel);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesija.close();
        }
    }
}
