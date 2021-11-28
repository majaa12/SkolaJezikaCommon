package rs.ac.bg.fon.nprog.common.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TerminKursa implements GenericEntity{

	 private long IDTermina;
	    private Date datumPocetka;
	    private Date datumZavrsetka;
	    private String raspored;
	    private int kapacitet;
	    private int brojCasova;
	    private BigDecimal cena;
	    private Kurs kurs;
	    private Profesor profesor;
	    private Adresa adresa;

	    public TerminKursa() {
	        this.cena = BigDecimal.ZERO;
	    }

	    public TerminKursa(long IDTermina, Date datumPocetka, Date datumZavrsetka, String raspored, int kapacitet, int brojCasova, BigDecimal cena, Kurs kurs, Profesor profesor, Adresa adresa) {
	        this.IDTermina = IDTermina;
	        this.datumPocetka = datumPocetka;
	        this.datumZavrsetka = datumZavrsetka;
	        this.raspored = raspored;
	        this.kapacitet = kapacitet;
	        this.brojCasova = brojCasova;
	        this.cena = cena;
	        this.kurs = kurs;
	        this.profesor = profesor;
	        this.adresa = adresa;
	    }

	    public Adresa getAdresa() {
	        return adresa;
	    }

	    public void setAdresa(Adresa adresa) {
	        this.adresa = adresa;
	    }

	    public long getIDTermina() {
	        return IDTermina;
	    }

	    public void setIDTermina(long IDTermina) {
	        this.IDTermina = IDTermina;
	    }

	    public Date getDatumPocetka() {
	        return datumPocetka;
	    }

	    public void setDatumPocetka(Date datumPocetka) {
	        this.datumPocetka = datumPocetka;
	    }

	    public Date getDatumZavrsetka() {
	        return datumZavrsetka;
	    }

	    public void setDatumZavrsetka(Date datumZavrsetka) {
	        this.datumZavrsetka = datumZavrsetka;
	    }

	    public String getRaspored() {
	        return raspored;
	    }

	    public void setRaspored(String raspored) {
	        this.raspored = raspored;
	    }

	    public int getKapacitet() {
	        return kapacitet;
	    }

	    public void setKapacitet(int kapacitet) {
	        this.kapacitet = kapacitet;
	    }

	    public int getBrojCasova() {
	        return brojCasova;
	    }

	    public void setBrojCasova(int brojCasova) {
	        this.brojCasova = brojCasova;
	    }

	    public BigDecimal getCena() {
	        return cena;
	    }

	    public void setCena(BigDecimal cena) {
	        this.cena = cena;
	    }

	    public Kurs getKurs() {
	        return kurs;
	    }

	    public void setKurs(Kurs kurs) {
	        this.kurs = kurs;
	    }

	    public Profesor getProfesor() {
	        return profesor;
	    }

	    public void setProfesor(Profesor profesor) {
	        this.profesor = profesor;
	    }

	    @Override
	    public String toString() {
	        return datumPocetka + " - " + datumZavrsetka + " " + raspored;
	    }

	    /* @Override
	    public String toString() {
	        return kurs + " " + profesor;
	    }*/
	    @Override
	    public int hashCode() {
	        int hash = 3;
	        hash = 47 * hash + (int) (this.IDTermina ^ (this.IDTermina >>> 32));
	        hash = 47 * hash + Objects.hashCode(this.datumPocetka);
	        hash = 47 * hash + Objects.hashCode(this.datumZavrsetka);
	        hash = 47 * hash + Objects.hashCode(this.raspored);
	        hash = 47 * hash + this.kapacitet;
	        hash = 47 * hash + this.brojCasova;
	        hash = 47 * hash + Objects.hashCode(this.cena);
	        hash = 47 * hash + Objects.hashCode(this.kurs);
	        hash = 47 * hash + Objects.hashCode(this.profesor);
	        hash = 47 * hash + Objects.hashCode(this.adresa);
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
	        final TerminKursa other = (TerminKursa) obj;
	        if (this.IDTermina != other.IDTermina) {
	            return false;
	        }
	        if (this.kapacitet != other.kapacitet) {
	            return false;
	        }
	        if (this.brojCasova != other.brojCasova) {
	            return false;
	        }
	        if (!Objects.equals(this.raspored, other.raspored)) {
	            return false;
	        }
	        if (!Objects.equals(this.datumPocetka, other.datumPocetka)) {
	            return false;
	        }
	        if (!Objects.equals(this.datumZavrsetka, other.datumZavrsetka)) {
	            return false;
	        }
	        if (!Objects.equals(this.cena, other.cena)) {
	            return false;
	        }
	        if (!Objects.equals(this.kurs, other.kurs)) {
	            return false;
	        }
	        if (!Objects.equals(this.profesor, other.profesor)) {
	            return false;
	        }
	        if (!Objects.equals(this.adresa, other.adresa)) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String getTableName() {
	        return "terminkursa";
	    }

	    @Override
	    public String getColumnNamesForInsert() {
	        return "DatumPocetka, DatumZavrsetka, Raspored, Kapacitet, BrojCasova, Cena, IDKursa, IDProfesora, IDAdrese";
	    }

	    @Override
	    public String getInsertValues() {
	        return "'" + new java.sql.Date(datumPocetka.getTime()) + "', '" + new java.sql.Date(datumZavrsetka.getTime()) + "', '"
	                + raspored + "', " + kapacitet + ", " + brojCasova + ", " + cena + ", " + kurs.getIDKursa() + ", " + profesor.getIDProfesora() + ", " + adresa.getIDAdrese();
	    }

	    @Override
	    public void setId(Long id) {
	        IDTermina = id;
	    }

	    @Override
	    public String getConditionForOne() {
	        return "IDTermina = " + IDTermina;
	    }

	    @Override
	    public String setUpdateValues() {
	        return "DatumPocetka = '" + new java.sql.Date(datumPocetka.getTime()) + "', DatumZavrsetka = '" + new java.sql.Date(datumZavrsetka.getTime()) + "', Raspored = '"
	                + raspored + "', Kapacitet = " + kapacitet + ", BrojCasova = " + brojCasova + ", Cena = " + cena + ", IDKursa = " + kurs.getIDKursa() + ", IDProfesora = " + profesor.getIDProfesora() + ", IDAdrese = " + adresa.getIDAdrese();
	    }

	    @Override
	    public GenericEntity fillFromRS(ResultSet rs) throws Exception {
	        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
	        List<GenericEntity> list = new ArrayList<>();
	        while (rs.next()) {
	            long idTK = rs.getLong("tk.IDTermina");
	            Date datPoc = rs.getDate("tk.DatumPocetka");
	            Date datZav = rs.getDate("tk.DatumZavrsetka");
	            String rasporedTK = rs.getString("tk.Raspored");
	            int kapacitetTK = rs.getInt("tk.Kapacitet");
	            int brCasovaTK = rs.getInt("tk.BrojCasova");
	            BigDecimal cenaTK = rs.getBigDecimal("tk.Cena");

	            long idK = rs.getLong("k.IDKursa");
	            String nazivK = rs.getString("k.Naziv");
	            Nivo nivoK = Nivo.valueOf(rs.getString("k.Nivo"));
	            TipKursa tipK = TipKursa.valueOf(rs.getString("k.TipKursa"));

	            long idJ = rs.getLong("j.IDJezika");
	            String nazivJ = rs.getString("j.Naziv");
	            Jezik j = new Jezik(idJ, nazivJ);

	            long idP = rs.getLong("tk.IDProfesora");
	            String imeP = rs.getString("p.Ime");
	            String prezimeP = rs.getString("p.Prezime");
	            String telefonP = rs.getString("p.Telefon");
	            String emailP = rs.getString("p.Email");

	            long idA = rs.getLong("tk.IDAdrese");
	            String ulicaA = rs.getString("a.Ulica");
	            int brojA = rs.getInt("a.Broj");

	            long idG = rs.getLong("a.IDGrada");
	            String nazivG = rs.getString("g.Naziv");

	            Grad g = new Grad(idG, nazivG, null);
	            Adresa a = new Adresa(idA, ulicaA, brojA, g);
	            Profesor p = new Profesor(idP, imeP, prezimeP, telefonP, emailP, j);
	            Kurs k = new Kurs(idK, nazivK, nivoK, tipK, j, null);
	            TerminKursa tk = new TerminKursa(idTK, datPoc, datZav, rasporedTK, kapacitetTK, brCasovaTK, cenaTK, k, p, a);

	            list.add(tk);
	        }
	        return list;
	    }

	    @Override
	    public String getJoinCondition() {
	        return " tk join kurs k on tk.IDKursa = k.IDKursa join profesor p on tk.IDProfesora = p.IDProfesora join jezik j on "
	                + "k.IDJezika = j.IDJezika join adresa a on tk.IDAdrese = a.IDAdrese join grad g on a.IDGrada = g.IDGrada";
	    }

	    @Override
	    public String getConditionForMore() {
	        return " where tk.IDKursa = " + kurs.getIDKursa();
	    }

	    @Override
	    public String getSort() {
	        return " order by tk.DatumPocetka";
	    }
}
