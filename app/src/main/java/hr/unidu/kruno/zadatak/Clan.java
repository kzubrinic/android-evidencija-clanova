package hr.unidu.kruno.zadatak;

import java.util.Objects;

public class Clan {
    private String ime, oib, adresa, telefon;
    private int clanarina;
    private boolean privola;
    public Clan(){}
    public Clan(String ime, String oib, String adresa, String telefon, int clanarina, boolean privola) {
        this.ime = ime;
        this.oib = oib;
        this.adresa = adresa;
        this.telefon = telefon;
        this.clanarina = clanarina;
        this.privola = privola;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getClanarina() {
        return clanarina;
    }

    public void setClanarina(int clanarina) {
        this.clanarina = clanarina;
    }

    public boolean isPrivola() {
        return privola;
    }

    public void setPrivola(boolean privola) {
        this.privola = privola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clan clan = (Clan) o;
        return oib.equals(clan.oib);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oib);
    }
}
