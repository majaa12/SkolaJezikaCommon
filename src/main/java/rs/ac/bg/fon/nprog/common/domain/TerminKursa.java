package rs.ac.bg.fon.nprog.common.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja TerminKursa koji se moze izabrati na odredjenom kursu
 * i implementira interfejs GenericEntity.
 *
 * TerminKursa ima svoj IDTermina tipa long, datumPocetka i datumZavrsetka tipa
 * Date, raspred tipa String, kapacitet, brojCasova tipa int, cenu tipa
 * BigDecimal, kurs tipa Kurs, profesora tipa Profesor, adresu tipa Adresa
 * 
 * @author Maja
 * @version 0.1
 */
public class TerminKursa implements GenericEntity {

	/**
	 * IDTermina tipa long.
	 */
	private long IDTermina;
	/**
	 * Datum pocetka tipa Date.
	 */
	private Date datumPocetka;
	/**
	 * Datum zavrsetka tipa Date.
	 */
	private Date datumZavrsetka;
	/**
	 * Raspored tipa String koji odredjuje dane u nedelji i satnicu odrzavanja
	 * termina tog kursa
	 */
	private String raspored;
	/**
	 * Kapacitet polaznika koje taj termin kursa moze da podrzi kao int.
	 */
	private int kapacitet;
	/**
	 * Broj casova u okviru termina kursa kao int.
	 */
	private int brojCasova;
	/**
	 * Cena termina kao BigDecimal.
	 */
	private BigDecimal cena;
	/**
	 * Kurs kome taj termin pripada kao objekat klase Kurs.
	 */
	private Kurs kurs;
	/**
	 * Profesor koji poducava u tom terminu kao objekat klase Profesor.
	 */
	private Profesor profesor;
	/**
	 * Adresa odrzavanja tog termina kao objekat klase Adresa.
	 */
	private Adresa adresa;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase TerminKursa i
	 * inicijalizuje cenu na BigDecimal vrednost nula.
	 */
	public TerminKursa() {
		this.cena = BigDecimal.ZERO;
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase TerminKursa i
	 * postavlja pocetne vrednosti za atribute IDTermina, datumPocetka,
	 * datumZavrsetka, raspored, kapacitet, brojCasova, cena, kursa, profesor,
	 * adresa.
	 * 
	 * @param IDTermina      Id termina kao long.
	 * @param datumPocetka   Datum pocetka odrzavanja termina kao Date.
	 * @param datumZavrsetka Datum zavrsetka odrzavanja termina kao Date.
	 * @param raspored       Raspored odrzavanja termina kao String.
	 * @param kapacitet      Kapacitet polaznika termina kao int.
	 * @param brojCasova     Broj casova termina kao int.
	 * @param cena           Cena termina kao BigDecimal.
	 * @param kurs           Kurs kome termin pripada kao objekat klase Kurs.
	 * @param profesor       Profesor koji poducava u tom terminu kao objekat klase
	 *                       Profesor.
	 * @param adresa         Adresa na kojoj se odrzava termin kao objekat klase
	 *                       Adresa.
	 */
	public TerminKursa(long IDTermina, Date datumPocetka, Date datumZavrsetka, String raspored, int kapacitet,
			int brojCasova, BigDecimal cena, Kurs kurs, Profesor profesor, Adresa adresa) {
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

	/**
	 * Vraca adresu odrzavanja termina.
	 * 
	 * @return adresa tipa Adresa.
	 */
	public Adresa getAdresa() {
		return adresa;
	}

	/**
	 * Postavlja adresu termina kursa ako ona nije null.
	 * 
	 * @param adresa Adresa odrzavanja termina kursa kao Adresa.
	 * @throws java.lang.NullPointerException ako je prosledjena adresa null.
	 */
	public void setAdresa(Adresa adresa) {
		if (adresa == null) {
			throw new NullPointerException("Adresa termina kursa ne sme biti null vrednost!");
		}
		this.adresa = adresa;
	}

	/**
	 * Vraca id termina kursa.
	 * 
	 * @return IDTermina kursa kao long.
	 */
	public long getIDTermina() {
		return IDTermina;
	}

	/**
	 * Postavlja id termina kursa.
	 * 
	 * @param IDTermina kursa kao long.
	 */
	public void setIDTermina(long IDTermina) {
		this.IDTermina = IDTermina;
	}

	/**
	 * Vraca datum pocetka odrzavanja termina kursa.
	 * 
	 * @return datumPocetka odrzavanja termina kursa kao Date.
	 */
	public Date getDatumPocetka() {
		return datumPocetka;
	}

	/**
	 * Postavlja datum pocetka odrzavanja termina kursa ako on nije null.
	 * 
	 * @param datumPocetka odrzavanja termina kursa kao Date.
	 * @throws java.lang.NullPointerException ako je prosledjen datum pocetka null.
	 */
	public void setDatumPocetka(Date datumPocetka) {
		if (datumPocetka == null) {
			throw new NullPointerException("Datum pocetka ne sme biti null vrednost!");
		}
		this.datumPocetka = datumPocetka;
	}

	/**
	 * Vraca datum zavrsetka odrzavanja termina kursa.
	 * 
	 * @return datumZavrsetka odrzavanja termina kursa kao Date.
	 */
	public Date getDatumZavrsetka() {
		return datumZavrsetka;
	}

	/**
	 * Postavlja datum zavrsetka odrzavanja termina kursa ako on nije null.
	 * 
	 * @param datumZavrsetka odrzavanja termina kursa kao Date.
	 * @throws java.lang.NullPointerException ako je prosledjen datum zavrsetka
	 *                                        null.
	 */
	public void setDatumZavrsetka(Date datumZavrsetka) {
		if (datumZavrsetka == null) {
			throw new NullPointerException("Datum zavrsetka ne sme biti null vrednost!");
		}
		this.datumZavrsetka = datumZavrsetka;
	}

	/**
	 * Vraca raspored odrzavanja termina kursa.
	 * 
	 * @return raspored odrzavanja termina kursa kao String.
	 */
	public String getRaspored() {
		return raspored;
	}

	/**
	 * Postavlja raspored termina kursa ako on nije null i nije prazan String.
	 * 
	 * @param raspored Raspored termina kursa kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen raspored null.
	 * @throws java.lang.RuntimeException     ako je prosledjen raspored prazan
	 *                                        String.
	 */
	public void setRaspored(String raspored) {
		if (raspored == null) {
			throw new NullPointerException("Raspored ne sme biti null vrednost!");
		}
		if (raspored.isEmpty()) {
			throw new RuntimeException("Raspored ne sme biti prazan string!");
		}
		this.raspored = raspored;
	}

	/**
	 * Vraca kapacitet polaznika koji mogu pohadjati odredjeni termin kursa.
	 * 
	 * @return kapacitet termina kursa kao int.
	 */
	public int getKapacitet() {
		return kapacitet;
	}

	/**
	 * Postavlja kapacitet termina kursa ako je on izmedju 6 i 10 polaznika.
	 * 
	 * @param kapacitet termina kursa kao int.
	 * @throws java.lang.RuntimeException ako prosledjen kapacitet nije u opsegu od
	 *                                    6 do 10.
	 */
	public void setKapacitet(int kapacitet) {
		if (kapacitet < 6 || kapacitet > 10) {
			throw new RuntimeException("Kapacitet mora biti izmedju 6 i 10!");
		}
		this.kapacitet = kapacitet;
	}

	/**
	 * Vraca broj casova termina kursa.
	 * 
	 * @return brojCasova termina kursa.
	 */
	public int getBrojCasova() {
		return brojCasova;
	}

	/**
	 * Postavlja broj casova termina kursa ako je on izmedju 35 i 60.
	 * 
	 * @param brojCasova termina kursa kao int.
	 * @throws java.lang.RuntimeException ako prosledjen broj casova nije u opsegu
	 *                                    od 35 do 60.
	 */
	public void setBrojCasova(int brojCasova) {
		if (brojCasova < 35 || brojCasova > 60) {
			throw new RuntimeException("Termini kurseva moraju imati izmedju 35 i 60 casova!");
		}
		this.brojCasova = brojCasova;
	}

	/**
	 * Vraca cenu termina kursa.
	 * 
	 * @return cena termina kursa kao BigDecimal.
	 */
	public BigDecimal getCena() {
		return cena;
	}

	/**
	 * Postavlja cenu termina kursa ako je ona veca od 0;
	 * 
	 * @param cena termina kursa kao BigDecimal.
	 * @throws java.lang.RuntimeException ako prosledjena cena nije veca od nule.
	 */
	public void setCena(BigDecimal cena) {
		if (cena.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Cena mora biti veca od 0!");
		}
		this.cena = cena;
	}

	/**
	 * Vraca kurs kome termin pripada.
	 * 
	 * @return kurs kome termin pripada tipa Kurs.
	 */
	public Kurs getKurs() {
		return kurs;
	}

	/**
	 * Postavlja kurs kome termin pripada ako on nije null.
	 * 
	 * @param kurs kome termin pripada kao objekat klase Kurs.
	 * @throws java.lang.NullPointerException ako je prosledjen kurs null.
	 */
	public void setKurs(Kurs kurs) {
		if (kurs == null) {
			throw new NullPointerException("Kurs ne sme biti null vrednost!");
		}
		this.kurs = kurs;
	}

	/**
	 * Vraca profesora koji predaje u tom terminu.
	 * 
	 * @return profesor koji predaje u tom terminu kao objekat klase Profesor.
	 */
	public Profesor getProfesor() {
		return profesor;
	}

	/**
	 * Postavlja profesor koji predaje u tom terminu ako on nije null.
	 * 
	 * @param profesor koji predaje u tom terminu kao objekat klase Profesor.
	 * @throws java.lang.NullPointerException ako je prosledjen profesor null.
	 */
	public void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("Profesor ne sme biti null vrednost!");
		}
		this.profesor = profesor;
	}

	/**
	 * Vraca datum pocetka, datum zavrsetka i raspored odrzavanja termina kursa kao
	 * jedan String.
	 */
	@Override
	public String toString() {
		return datumPocetka + " - " + datumZavrsetka + " " + raspored;
	}

	/*
	 * @Override public String toString() { return kurs + " " + profesor; }
	 */
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

	/**
	 * Poredi dva termina kursa i vraca true ako su isti, a false ako nisu. Termini
	 * kurseva se porede po id-u, kapacitetu, broju casova, rasporedu, datumu
	 * pocetka, datumu zavrsetka, ceni, kursu, profesoru i adresi i svi kriterijumi moraju da
	 * budu isti.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase TerminKursa i imaju iste id-jeve, kapacitete,
	 *         broj casova, rasporede, datume pocetka i zavrsetka, cene, kurseve, profesore, i adrese
	 *         <li>false u svim ostalim slucajevima
	 *         </ul>
	 *
	 */
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
		return "'" + new java.sql.Date(datumPocetka.getTime()) + "', '" + new java.sql.Date(datumZavrsetka.getTime())
				+ "', '" + raspored + "', " + kapacitet + ", " + brojCasova + ", " + cena + ", " + kurs.getIDKursa()
				+ ", " + profesor.getIDProfesora() + ", " + adresa.getIDAdrese();
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
		return "DatumPocetka = '" + new java.sql.Date(datumPocetka.getTime()) + "', DatumZavrsetka = '"
				+ new java.sql.Date(datumZavrsetka.getTime()) + "', Raspored = '" + raspored + "', Kapacitet = "
				+ kapacitet + ", BrojCasova = " + brojCasova + ", Cena = " + cena + ", IDKursa = " + kurs.getIDKursa()
				+ ", IDProfesora = " + profesor.getIDProfesora() + ", IDAdrese = " + adresa.getIDAdrese();
	}

	@Override
	public GenericEntity fillFromRS(ResultSet rs) throws Exception {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
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
			TerminKursa tk = new TerminKursa(idTK, datPoc, datZav, rasporedTK, kapacitetTK, brCasovaTK, cenaTK, k, p,
					a);

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
