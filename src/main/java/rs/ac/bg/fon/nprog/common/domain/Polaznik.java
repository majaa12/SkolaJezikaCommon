package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja Polaznika koji upisuje kurs i implementira interfejs
 * GenericEntity.
 *
 * Polaznik ima svoj IDPolaznika tipa long, kao i ime, prezime, jmbg, telefon,
 * email i adresu koji su tipa String.
 * 
 * @author Maja
 * @version 0.1
 */
public class Polaznik implements GenericEntity {

	/**
	 * IDPolaznika kao long.
	 */
	private long IDPolaznika;
	/**
	 * Ime polaznika kao String.
	 */
	private String ime;
	/**
	 * Prezime polaznika kao String.
	 */
	private String prezime;
	/**
	 * JMBG polaznika kao String.
	 */
	private String jmbg;
	/**
	 * Telefon polaznika kao String.
	 */
	private String telefon;
	/**
	 * Email polaznika kao String.
	 */
	private String email;
	/**
	 * Adresa polaznika kao String.
	 */
	private String adresa;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase Polaznik i nista
	 * vise.
	 */
	public Polaznik() {
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase Polaznik i
	 * postavlja pocetne vrednosti za atribute IDPolaznika, ime, prezime, jmbg,
	 * telefon, email i adresa.
	 * 
	 * @param IDPolaznika Id polaznika kao long.
	 * @param ime         Ime polaznika kao String.
	 * @param prezime     Prezime polaznika kao String.
	 * @param jmbg        JMBG polaznika kao String.
	 * @param telefon     Telefon polaznika kao String.
	 * @param email       Email polaznika kao String.
	 * @param adresa      Adresa polaznika kao String.
	 */
	public Polaznik(long IDPolaznika, String ime, String prezime, String jmbg, String telefon, String email,
			String adresa) {
		this.IDPolaznika = IDPolaznika;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.telefon = telefon;
		this.email = email;
		this.adresa = adresa;
	}

	/**
	 * Vraca adresu polaznika.
	 * 
	 * @return adresa polaznika kao String.
	 */
	public String getAdresa() {
		return adresa;
	}

	/**
	 * Postavlja adresu polaznika ako ona nije null i nije prazan String.
	 * 
	 * @param adresa Adresa polaznika kao String.
	 * @throws java.lang.NullPointerException ako je prosledjena adresa null.
	 * @throws java.lang.RuntimeException     ako je prosledjena adresa prazan
	 *                                        String.
	 */
	public void setAdresa(String adresa) {
		if (adresa == null) {
			throw new NullPointerException("Adresa polaznika ne sme biti null vrednost!");
		}
		if (adresa.isEmpty()) {
			throw new RuntimeException("Adresa polaznika ne sme biti prazan string!");
		}
		this.adresa = adresa;
	}

	/**
	 * Vraca id polaznika.
	 * 
	 * @return IDPolaznika kao long.
	 */
	public long getIDPolaznika() {
		return IDPolaznika;
	}

	/**
	 * Postavlja id polaznika.
	 * 
	 * @param IDPolaznika Id polaznika kao long.
	 */
	public void setIDPolaznika(long IDPolaznika) {
		this.IDPolaznika = IDPolaznika;
	}

	/**
	 * Vraca ime polaznika.
	 * 
	 * @return ime polaznika kao String.
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja ime polaznika ako ono nije null i nije prazan String.
	 * 
	 * @param ime Ime polaznika kao String.
	 * @throws java.lang.NullPointerException ako je prosledjeno ime null.
	 * @throws java.lang.RuntimeException     ako je prosledjeno ime prazan String.
	 */
	public void setIme(String ime) {
		if (ime == null) {
			throw new NullPointerException("Ime polaznika ne sme biti null vrednost!");
		}
		if (ime.isEmpty()) {
			throw new RuntimeException("Ime polaznika ne sme biti prazan string!");
		}
		this.ime = ime;
	}

	/**
	 * Vraca prezime polaznika.
	 * 
	 * @return prezime polaznika kao String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Postavlja prezime polaznika ako ono nije null i nije prazan String.
	 * 
	 * @param prezime Prezime polaznika kao String.
	 * @throws java.lang.NullPointerException ako je prosledjeno prezime null.
	 * @throws java.lang.RuntimeException     ako je prosledjeno prezime prazan
	 *                                        String.
	 */
	public void setPrezime(String prezime) {
		if (prezime == null) {
			throw new NullPointerException("Prezime polaznika ne sme biti null vrednost!");
		}
		if (prezime.isEmpty()) {
			throw new RuntimeException("Prezime polaznika ne sme biti prazan string!");
		}
		this.prezime = prezime;
	}

	/**
	 * Vraca jmbg polaznika.
	 * 
	 * @return jmbg polaznika kao String.
	 */
	public String getJmbg() {
		return jmbg;
	}

	/**
	 * Postavlja jmbg polaznika ako ono nije null i ima 13 cifara.
	 * 
	 * @param jmbg JMBG polaznika kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen jmbg null.
	 * @throws java.lang.RuntimeException     ako prosledjen jmbg nema 13 cifara.
	 * 
	 */
	public void setJmbg(String jmbg) {
		if (jmbg == null) {
			throw new NullPointerException("JMBG polaznika ne sme biti null vrednost!");
		}
		if (!(jmbg.length() == 13)) {
			throw new RuntimeException("JMBG polaznika mora imati 13 cifara!");
		}
		this.jmbg = jmbg;
	}

	/**
	 * Vraca telefon polaznika.
	 * 
	 * @return telefon polaznika kao String.
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Postavlja telefon polaznika ako on nije null i nije prazan String.
	 * 
	 * @param telefon Telefon polaznika kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen telefon null.
	 * @throws java.lang.RuntimeException     ako je prosledjen telefon prazan
	 *                                        String.
	 */
	public void setTelefon(String telefon) {
		if (telefon == null) {
			throw new NullPointerException("Telefon polaznika ne sme biti null vrednost!");
		}
		if (telefon.isEmpty()) {
			throw new RuntimeException("Telefon polaznika ne sme biti prazan string!");
		}
		this.telefon = telefon;
	}

	/**
	 * Vraca email polaznika.
	 * 
	 * @return email polaznika kao String.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Postavlja email polaznika ako on nije null, nije prazan String i sadrzi
	 * {@literal @}
	 * 
	 * @param email Email polaznika kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen email null.
	 * @throws java.lang.RuntimeException     ako je prosledjen email prazan String.
	 * @throws java.lang.RuntimeException     ako prosledjen ne sadrzi {@literal @}.
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException("Email polaznika ne sme biti null vrednost!");
		}
		if (email.isEmpty()) {
			throw new RuntimeException("Email polaznika ne sme biti prazan string!");
		}
		if (!email.contains("@")) {
			throw new RuntimeException("Email mora sadrzati @");
		}
		this.email = email;
	}

	/**
	 * Vraca ime i prezime polaznika kao jedan String.
	 */
	@Override
	public String toString() {
		return ime + " " + prezime;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + (int) (this.IDPolaznika ^ (this.IDPolaznika >>> 32));
		hash = 73 * hash + Objects.hashCode(this.ime);
		hash = 73 * hash + Objects.hashCode(this.prezime);
		hash = 73 * hash + Objects.hashCode(this.jmbg);
		hash = 73 * hash + Objects.hashCode(this.telefon);
		hash = 73 * hash + Objects.hashCode(this.email);
		return hash;
	}

	/**
	 * Poredi dva polaznika i vraca true ako su ista, a false ako nisu. Polaznici se
	 * porede po imenu, prezimenu, jmbg-u, telefonu i email-u i svi kriterijumi
	 * moraju da budu ista.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase Polaznik i imaju ista imena,
	 *         prezimena, jmbg, telefone i email-ove
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
		final Polaznik other = (Polaznik) obj;
		if (!Objects.equals(this.ime, other.ime)) {
			return false;
		}
		if (!Objects.equals(this.prezime, other.prezime)) {
			return false;
		}
		if (!Objects.equals(this.jmbg, other.jmbg)) {
			return false;
		}
		if (!Objects.equals(this.telefon, other.telefon)) {
			return false;
		}
		if (!Objects.equals(this.email, other.email)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "polaznik";
	}

	@Override
	public String getColumnNamesForInsert() {
		return "Ime, Prezime, Jmbg, Telefon, Email, Adresa";
	}

	@Override
	public String getInsertValues() {
		return "'" + ime + "', '" + prezime + "', '" + jmbg + "', '" + telefon + "', '" + email + "', '" + adresa + "'";
	}

	@Override
	public void setId(Long id) {
		IDPolaznika = id;
	}

	@Override
	public String getConditionForOne() {
		return "IDPolaznika = " + IDPolaznika;
	}

	@Override
	public String setUpdateValues() {
		return "Ime = '" + ime + "', Prezime = '" + prezime + "', Jmbg = '" + jmbg + "', " + "Telefon = '" + telefon
				+ "', Email = '" + email + "', Adresa = '" + adresa + "'";
	}

	@Override
	public GenericEntity fillFromRS(ResultSet rs) throws Exception {
		if (rs.next()) {
			IDPolaznika = rs.getLong("IDPolaznika");
			ime = rs.getString("Ime");
			prezime = rs.getString("Prezime");
			jmbg = rs.getString("Jmbg");
			telefon = rs.getString("Telefon");
			email = rs.getString("Email");
			adresa = rs.getString("Adresa");

			return this;
		}
		throw new Exception("Polaznik ne postoji u bazi!");
	}

	@Override
	public String getJoinCondition() {
		return "";
	}

	@Override
	public String getConditionForMore() {
		String rez;

		if (ime.equals("") && prezime.equals("")) {
			return "";
		} else {
			rez = " where";
			if (!ime.equals("")) {
				rez += " Ime like '" + ime + "%' and";
			}
			if (!prezime.equals("")) {
				rez += " Prezime like '" + prezime + "%' and";
			}
		}
		return rez.substring(0, rez.length() - 4);
	}

	@Override
	public String getSort() {
		return " order by IDPolaznika";
	}

	@Override
	public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
		List<GenericEntity> list = new ArrayList<>();
		while (rs.next()) {
			long idP = rs.getLong("IDPolaznika");
			String imeP = rs.getString("Ime");
			String prezimeP = rs.getString("Prezime");
			String jmbgP = rs.getString("Jmbg");
			String telefonP = rs.getString("Telefon");
			String emailP = rs.getString("Email");
			String adresaP = rs.getString("Adresa");
			Polaznik p = new Polaznik(idP, imeP, prezimeP, jmbgP, telefonP, emailP, adresaP);
			list.add(p);
		}
		return list;
	}
}
