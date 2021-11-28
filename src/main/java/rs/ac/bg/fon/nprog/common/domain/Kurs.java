package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kurs implements GenericEntity{

	private long IDKursa;
    private String naziv;
    private Nivo nivo;
    private TipKursa tipKursa;
    private Jezik jezik;
    private ArrayList<TerminKursa> termini;

    public Kurs() {
        this.termini = new ArrayList<>();
    }

    public Kurs(long IDKursa, String naziv, Nivo nivo, TipKursa tipKursa, Jezik jezik, ArrayList<TerminKursa> termini) {
        this.IDKursa = IDKursa;
        this.naziv = naziv;
        this.nivo = nivo;
        this.tipKursa = tipKursa;
        this.jezik = jezik;
        this.termini = termini;
    }

    public Jezik getJezik() {
        return jezik;
    }

    public void setJezik(Jezik jezik) {
        this.jezik = jezik;
    }

    public long getIDKursa() {
        return IDKursa;
    }

    public void setIDKursa(long IDKursa) {
        this.IDKursa = IDKursa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Nivo getNivo() {
        return nivo;
    }

    public void setNivo(Nivo nivo) {
        this.nivo = nivo;
    }

    public TipKursa getTipKursa() {
        return tipKursa;
    }

    public void setTipKursa(TipKursa tipKursa) {
        this.tipKursa = tipKursa;
    }

    public ArrayList<TerminKursa> getTermini() {
        return termini;
    }

    public void setTermini(ArrayList<TerminKursa> termini) {
        this.termini = termini;
    }

    public boolean exist(TerminKursa termin) {

        for (TerminKursa t : termini) {
            if (t.getKapacitet() == termin.getKapacitet()
                    && (t.getCena().compareTo(termin.getCena()) == 0)
                    && t.getBrojCasova() == termin.getBrojCasova()
                    && t.getRaspored().equals(termin.getRaspored())
                    && (t.getDatumPocetka().compareTo(termin.getDatumPocetka()) == 0)
                    && (t.getDatumZavrsetka().compareTo(termin.getDatumZavrsetka()) == 0)                    
                    && t.getAdresa().equals(termin.getAdresa())
                    && t.getProfesor().equals(termin.getProfesor())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.IDKursa ^ (this.IDKursa >>> 32));
        hash = 71 * hash + Objects.hashCode(this.naziv);
        hash = 71 * hash + Objects.hashCode(this.nivo);
        hash = 71 * hash + Objects.hashCode(this.tipKursa);
        hash = 71 * hash + Objects.hashCode(this.jezik);
        hash = 71 * hash + Objects.hashCode(this.termini);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kurs other = (Kurs) obj;
        if (this.IDKursa != other.IDKursa) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (this.nivo != other.nivo) {
            return false;
        }
        if (this.tipKursa != other.tipKursa) {
            return false;
        }
        if (!Objects.equals(this.jezik, other.jezik)) {
            return false;
        }
        if (!Objects.equals(this.termini, other.termini)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "kurs";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "Naziv, Nivo, TipKursa, IDJezika";
    }

    @Override
    public String getInsertValues() {
        return "'" + naziv + "', '" + nivo.toString() + "', '" + tipKursa.toString() + "', " + jezik.getIDJezika();
    }

    @Override
    public void setId(Long id) {
        IDKursa = id;
    }

    @Override
    public String getConditionForOne() {
        return "IDKursa = " + IDKursa;
    }

    @Override
    public String setUpdateValues() {
        return "Naziv = '" + naziv + "', Nivo = '" + nivo.toString() + "', TipKursa = '" + tipKursa.toString() + "', IDJezika = " + jezik.getIDJezika();
    }

    @Override
    public GenericEntity fillFromRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            IDKursa = rs.getLong("k.IDKursa");
            naziv = rs.getString("k.Naziv");
            nivo = Nivo.valueOf(rs.getString("k.Nivo"));
            tipKursa = TipKursa.valueOf(rs.getString("k.TipKursa"));

            long idJ = rs.getLong("j.IDJezika");
            String nazivJ = rs.getString("j.Naziv");
            jezik = new Jezik(idJ, nazivJ);

            return this;
        }
        throw new Exception("Kurs ne postoji u bazi!");
    }

    @Override
    public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            long idK = rs.getLong("k.IDKursa");
            String nazivK = rs.getString("k.Naziv");
            Nivo nivoK = Nivo.valueOf(rs.getString("k.Nivo"));
            TipKursa tipK = TipKursa.valueOf(rs.getString("k.TipKursa"));

            long idJ = rs.getLong("j.IDJezika");
            String nazivJ = rs.getString("j.Naziv");
            Jezik j = new Jezik(idJ, nazivJ);

            Kurs k = new Kurs(idK, nazivK, nivoK, tipK, j, null);
            list.add(k);
        }
        return list;
    }

    @Override
    public String getJoinCondition() {
        return " k join jezik j on k.IDJezika = j.IDJezika";
    }

    @Override
    public String getConditionForMore() {
        String rez;
        if (jezik == null && nivo == null && tipKursa == null && (naziv == null || naziv.equals(""))) {
            return "";
        } else {
            rez = " where";
            if (jezik != null) {
                rez += " j.IDJezika = " + jezik.getIDJezika() + " and";
            }
            if (nivo != null) {
                rez += " k.Nivo = '" + nivo.toString() + "' and";
            }
            if (tipKursa != null) {
                rez += " k.TipKursa = '" + tipKursa.toString() + "' and";
            }
            if (naziv != null && !naziv.equals("")) {
                rez += " k.Naziv like '%" + naziv + "%' and";
            }

        }
        return rez.substring(0, rez.length() - 4);
    }

    @Override
    public String getSort() {
        return " order by k.Nivo, k.TipKursa";
    }
}
