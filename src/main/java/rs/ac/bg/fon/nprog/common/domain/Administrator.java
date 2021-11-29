package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja administratora sistema i implementira interfejs
 * GenericEntity
 *
 * Administrator ima svoj IDAdministratora tipa long, kao i ime, prezime,
 * korisnickoIme i lozinku kao String vrednosti.
 * 
 * @author Maja
 * @version 0.1
 */

public class Administrator implements GenericEntity {

	/**
	 * IDAdministratora tipa long.
	 */
	private long IDAdministratora;

	/**
	 * Ime tipa String.
	 */
	private String ime;

	/**
	 * Prezime tipa String.
	 */
	private String prezime;

	/**
	 * Korisnicko ime tipa String.
	 */
	private String korisnickoIme;

	/**
	 * Lozinka tipa String.
	 */
	private String lozinka;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase Administrator i
	 * nista vise.
	 */
	public Administrator() {
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase Administrator i
	 * postavlja pocetne vrednosti za atribute IDAdministratora, ime, prezime,
	 * korisnickoIme i lozinka
	 * 
	 * @param IDAdministratora Id administratora kao long
	 * @param ime              Ime administratora kao String
	 * @param prezime          Prezime administratora kao String
	 * @param korisnickoIme    Korisnicko ime administratora kao String
	 * @param lozinka          Lozinka administratora kao String
	 */
	public Administrator(long IDAdministratora, String ime, String prezime, String korisnickoIme, String lozinka) {
		this.IDAdministratora = IDAdministratora;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}

	/**
	 * Vraca lozinku administratora.
	 * 
	 * @return Lozinka administratora kao String vrednost.
	 */
	public String getLozinka() {
		return lozinka;
	}

	/**
	 * Postavlja lozinku administratora ako ona nije null i nije prazan String.
	 * 
	 * @param lozinka Lozinka administratora kao String vrednost.
	 * @throws java.lang.NullPointerException ako je prosledjena lozinka null.
	 * @throws java.lang.RuntimeException     ako je prosledjena lozinka prazan
	 *                                        String.
	 */
	public void setLozinka(String lozinka) {
		if (lozinka == null) {
			throw new NullPointerException("Lozinka ne sme biti null vrednost!");
		}
		if (lozinka.isEmpty()) {
			throw new RuntimeException("Lozinka ne sme biti prazan string!");
		}
		this.lozinka = lozinka;
	}

	/**
	 * Vraca id administratora.
	 * 
	 * @return IDAdministratora kao long
	 */
	public long getIDAdministratora() {
		return IDAdministratora;
	}

	/**
	 * Postavlja id administratora.
	 * 
	 * @param IDAdministratora Id administratora kao long.
	 */
	public void setIDAdministratora(long IDAdministratora) {
		this.IDAdministratora = IDAdministratora;
	}

	/**
	 * Vraca ime administratora.
	 * 
	 * @return ime administratora kao String.
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja ime administratora ako ono nije null i nije prazan String.
	 * 
	 * @param ime Ime administratora kao String.
	 * @throws java.lang.NullPointerException ako je prosledjeno ime null.
	 * @throws java.lang.RuntimeException     ako je prosledjeno ime prazan String.
	 */
	public void setIme(String ime) {
		if (ime == null) {
			throw new NullPointerException("Ime ne sme biti null vrednost!");
		}
		if (ime.isEmpty()) {
			throw new RuntimeException("Ime ne sme biti prazan string!");
		}
		this.ime = ime;
	}

	/**
	 * Vraca prezime administratora.
	 * 
	 * @return prezime administratora kao String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Postavlja prezime administratora ako ono nije null i nije prazan String.
	 * 
	 * @param prezime Prezime administratora kao String.
	 * @throws java.lang.NullPointerException ako je prosledjeno prezime null.
	 * @throws java.lang.RuntimeException     ako je prosledjeno prezime prazan
	 *                                        String.
	 */
	public void setPrezime(String prezime) {
		if (prezime == null) {
			throw new NullPointerException("Prezime ne sme biti null vrednost!");
		}
		if (prezime.isEmpty()) {
			throw new RuntimeException("Prezime ne sme biti prazan string!");
		}
		this.prezime = prezime;
	}

	/**
	 * Vraca korisnicko ime administratora.
	 * 
	 * @return korisnickoIme administratora kao String.
	 */
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	/**
	 * Postavlja korisnicko ime administratora ako ono nije null i nije prazan
	 * String.
	 * 
	 * @param korisnickoIme Korisnicko ime administratora kao String.
	 * @throws java.lang.NullPointerException ako je prosledjeno korisnicko ime
	 *                                        null.
	 * @throws java.lang.RuntimeException     ako je prosledjeno korisnicko ime
	 *                                        prazan String.
	 */
	public void setKorisnickoIme(String korisnickoIme) {
		if (korisnickoIme == null) {
			throw new NullPointerException("Korisnicko ime ne sme biti null vrednost!");
		}
		if (korisnickoIme.isEmpty()) {
			throw new RuntimeException("Korisnicko ime ne sme biti prazan string!");
		}
		this.korisnickoIme = korisnickoIme;
	}

	/**
	 * Vraca ime i prezime administratora kao jedan String.
	 */
	@Override
	public String toString() {
		return ime + " " + prezime;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.korisnickoIme);
		hash = 37 * hash + Objects.hashCode(this.lozinka);
		return hash;
	}

	/**
	 * Poredi dva administratora i vraca true ako su isti, a false ako nisu.
	 * Administratori se porede po korisnickom imenu i lozinki i oba moraju da budu
	 * ista.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase Administrator i imaju ista
	 *         korisnicka imena i lozinke
	 *         <li>false u svim ostalim slucajevima
	 *         </ul>
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
		final Administrator other = (Administrator) obj;
		if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
			return false;
		}
		if (!Objects.equals(this.lozinka, other.lozinka)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "administrator";
	}

	@Override
	public String getColumnNamesForInsert() {
		return "Ime, Prezime, KorisnickoIme, Lozinka";
	}

	@Override
	public String getInsertValues() {
		return ime + ", " + prezime + ", " + korisnickoIme + ", " + lozinka;
	}

	@Override
	public void setId(Long id) {
		IDAdministratora = id;
	}

	@Override
	public String getConditionForOne() {
		return "KorisnickoIme = '" + korisnickoIme + "' AND Lozinka = '" + lozinka + "'";
	}

	@Override
	public String setUpdateValues() {
		return "Ime = '" + ime + "', Prezime = '" + prezime + "', KorisnickoIme = '" + korisnickoIme + "', Lozinka = '"
				+ lozinka + "'";
	}

	@Override
	public GenericEntity fillFromRS(ResultSet rs) throws Exception {
		while (rs.next()) {
			IDAdministratora = rs.getLong("IDAdministratora");
			ime = rs.getString("Ime");
			prezime = rs.getString("Prezime");
			korisnickoIme = rs.getString("KorisnickoIme");
			lozinka = rs.getString("Lozinka");
			return this;
		}
		throw new Exception("Administrator ne postoji u bazi!");
	}

	@Override
	public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
		List<GenericEntity> list = new ArrayList<>();
		while (rs.next()) {
			long idA = rs.getLong("IDAdministratora");
			String imeA = rs.getString("Ime");
			String prezimeA = rs.getString("Prezime");
			String korisnickoImeA = rs.getString("KorisnickoIme");
			String lozinkaA = rs.getString("Lozinka");
			Administrator a = new Administrator(idA, imeA, prezimeA, korisnickoImeA, lozinkaA);
			list.add(a);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		return "";
	}

	@Override
	public String getConditionForMore() {
		return "";
	}

	@Override
	public String getSort() {
		return " order by IDAdministratora";
	}

}
