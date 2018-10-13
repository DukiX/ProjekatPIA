/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Dusan
 */
public class KompanijaPomocna {
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
    private String logolink;
    
    private Date datumIstekaPaketa;
    private Date datumPotpisivanjaPaketa;

    public Date getDatumPotpisivanjaPaketa() {
        return datumPotpisivanjaPaketa;
    }

    public void setDatumPotpisivanjaPaketa(Date datumPotpisivanjaPaketa) {
        this.datumPotpisivanjaPaketa = datumPotpisivanjaPaketa;
    }

    public Date getDatumIstekaPaketa() {
        return datumIstekaPaketa;
    }

    public void setDatumIstekaPaketa(Date datumIstekaPaketa) {
        this.datumIstekaPaketa = datumIstekaPaketa;
    }

    public KompanijaPomocna() {
    }

    public KompanijaPomocna(long id, String naziv, String adresa, String grad, int postanskibroj, String zemlja, String ziroracun, String valuta, String pib, String telefon1, String telefon2, String telefon3, String telefon4, String telefon5, String email1, String email2, String email3, String email4, String email5, String ime, String prezime, String kontaktemail, String kontakttel, String logolink) {
        this.idkompanija = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
        this.postanskibroj = postanskibroj;
        this.zemlja = zemlja;
        this.ziroracun = ziroracun;
        this.valuta = valuta;
        this.pib = pib;
        this.telefon1 = telefon1;
        this.telefon2 = telefon2;
        this.telefon3 = telefon3;
        this.telefon4 = telefon4;
        this.telefon5 = telefon5;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.email4 = email4;
        this.email5 = email5;
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktemail = kontaktemail;
        this.kontakttel = kontakttel;
        this.logolink = logolink;
    }

    public String getLogolink() {
        return logolink;
    }

    public void setLogolink(String logolink) {
        this.logolink = logolink;
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

}
