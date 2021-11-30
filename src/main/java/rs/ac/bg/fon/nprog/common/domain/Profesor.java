package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa koja predstavlja Profesora koji je angazovan na kursu odredjenog jezika
 * i implementira interfejs GenericEntity.
 *
 * Profesor ima svoj IDProfesora tipa long, kao i ime, prezime, telefon, email
 * koji su tipa String i jezik tipa Jezik.
 * 
 * @author Maja
 * @version 0.1
 */
public class Profesor implements GenericEntity {

	/**
	 * IDProfesora kao long.
	 */
	private long IDProfesora;
	/**
	 * Ime profesora kao String.
	 */
	private String ime;
	/**
	 * Prezime profesora kao String.
	 */
	private String prezime;
	/**
	 * Telefon profesora kao String.
	 */
	private String telefon;
	/**
	 * Email profesora kao String.
	 */
	private String email;
	/**
	 * Jezik profesora kao objekat klase Jezik.
	 */
	private Jezik jezik;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase Profesor i nista
	 * vise.
	 */
	public Profesor() {
	}

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase Profesor i
	 * postavlja pocetne vrednosti za atribute IDProfesora, ime, prezime, telefon,
	 * email i jezik.
	 * 
	 * @param IDProfesora Id profesora kao long.
	 * @param ime         Ime profesora kao String.
	 * @param prezime     Prezime profesora kao String.
	 * @param telefon     Telefon profesora kao String.
	 * @param email       Email profesora kao String.
	 * @param jezik       Jezik profesora kao objekat klase Jezik
	 */
	public Profesor(long IDProfesora, String ime, String prezime, String telefon, String email, Jezik jezik) {
		this.IDProfesora = IDProfesora;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
		this.jezik = jezik;
	}

	/**
	 * Vraca jezik koji profesor poducava.
	 * 
	 * @return jezik profesora kao Jezik.
	 */
	public Jezik getJezik() {
		return jezik;
	}

	/**
	 * Postavlja jezik profesora ako on nije null.
	 * 
	 * @param jezik Jezik profesora kao objekat klase Jezik.
	 * @throws java.lang.NullPointerException ako je prosledjen jezik null.
	 */
	public void setJezik(Jezik jezik) {
		if (jezik == null) {
			throw new NullPointerException("Jezik profesora ne sme biti null vrednost!");
		}
		this.jezik = jezik;
	}

	/**
	 * Vraca id profesora.
	 * 
	 * @return IDProfesora kao long.
	 */
	public long getIDProfesora() {
		return IDProfesora;
	}

	/**
	 * Postavlja id profesora.
	 * 
	 * @param IDProfesora Id profesora kao long.
	 */
	public void setIDProfesora(long IDProfesora) {
		this.IDProfesora = IDProfesora;
	}

	/**
	 * Vraca ime profesora.
	 * 
	 * @return ime profesora kao String.
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja ime profesora ako ono nije null i nije prazan String.
	 * 
	 * @param ime Ime profesora kao String.
	 * @throws java.lang.NullPointerException ako je prosledjeno ime null.
	 * @throws java.lang.RuntimeException     ako je prosledjeno ime prazan String.
	 */
	public void setIme(String ime) {
		if (ime == null) {
			throw new NullPointerException("Ime profesora ne sme biti null vrednost!");
		}
		if (ime.isEmpty()) {
			throw new RuntimeException("Ime profesora ne sme biti prazan string!");
		}
		this.ime = ime;
	}

	/**
	 * Vraca prezime profesora.
	 * 
	 * @return prezime profesora kao String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Postavlja prezime profesora ako ono nije null i nije prazan String.
	 * 
	 * @param prezime Prezime profesora kao String.
	 * @throws java.lang.NullPointerException ako je prosledjeno prezime null.
	 * @throws java.lang.RuntimeException     ako je prosledjeno prezime prazan
	 *                                        String.
	 */
	public void setPrezime(String prezime) {
		if (prezime == null) {
			throw new NullPointerException("Prezime profesora ne sme biti null vrednost!");
		}
		if (prezime.isEmpty()) {
			throw new RuntimeException("Prezime profesora ne sme biti prazan string!");
		}
		this.prezime = prezime;
	}

	/**
	 * Vraca telefon profesora.
	 * 
	 * @return telefon profesora kao String.
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Postavlja telefon profesora ako on nije null i nije prazan String.
	 * 
	 * @param telefon Telefon profesora kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen telefon null.
	 * @throws java.lang.RuntimeException     ako je prosledjen telefon prazan
	 *                                        String.
	 */
	public void setTelefon(String telefon) {
		if (telefon == null) {
			throw new NullPointerException("Telefon profesora ne sme biti null vrednost!");
		}
		if (telefon.isEmpty()) {
			throw new RuntimeException("Telefon profesora ne sme biti prazan string!");
		}
		this.telefon = telefon;
	}

	/**
	 * Vraca email profesora.
	 * 
	 * @return email profesora kao String.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Postavlja email profesora ako on nije null, nije prazan String i sadrzi
	 * {@literal @}
	 * 
	 * @param email Email profesora kao String.
	 * @throws java.lang.NullPointerException ako je prosledjen email null.
	 * @throws java.lang.RuntimeException     ako je prosledjen email prazan String.
	 * @throws java.lang.RuntimeException     ako prosledjen ne sadrzi {@literal @}.
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException("Email profesora ne sme biti null vrednost!");
		}
		if (email.isEmpty()) {
			throw new RuntimeException("Email profesora ne sme biti prazan string!");
		}
		if (!email.contains("@")) {
			throw new RuntimeException("Email mora sadrzati @");
		}
		this.email = email;
	}

	/**
	 * Vraca ime, prezime profesora i jezik koji poducava kao jedan String.
	 */
	@Override
	public String toString() {
		return ime + " " + prezime + " - " + jezik.getNaziv();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + (int) (this.IDProfesora ^ (this.IDProfesora >>> 32));
		hash = 73 * hash + Objects.hashCode(this.ime);
		hash = 73 * hash + Objects.hashCode(this.prezime);
		hash = 73 * hash + Objects.hashCode(this.telefon);
		hash = 73 * hash + Objects.hashCode(this.email);
		hash = 73 * hash + Objects.hashCode(this.jezik);
		return hash;
	}

	/**
	 * Poredi dva profesora i vraca true ako su ista, a false ako nisu. Profesori se
	 * porede po id-u, imenu, prezimenu, telefonu, email-u i jeziku i svi kriterijumi
	 * moraju da budu ista.
	 * 
	 * @return
	 *         <ul>
	 *         <li>true ako su oba objekta klase Profesor i imaju iste id-jeve, imena,
	 *         prezimena, telefone, email-ove i jezike.
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
		final Profesor other = (Profesor) obj;
		if (this.IDProfesora != other.IDProfesora) {
			return false;
		}
		if (!Objects.equals(this.ime, other.ime)) {
			return false;
		}
		if (!Objects.equals(this.prezime, other.prezime)) {
			return false;
		}
		if (!Objects.equals(this.telefon, other.telefon)) {
			return false;
		}
		if (!Objects.equals(this.email, other.email)) {
			return false;
		}
		if (!Objects.equals(this.jezik, other.jezik)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "profesor";
	}

	@Override
	public String getColumnNamesForInsert() {
		return "Ime, Prezime, Telefon, Email, IDJezika";
	}

	@Override
	public String getInsertValues() {
		return "'" + ime + "', '" + prezime + "', '" + telefon + "', '" + email + "', " + jezik.getIDJezika();
	}

	@Override
	public void setId(Long id) {
		IDProfesora = id;
	}

	@Override
	public String getConditionForOne() {
		return "p.IDJezika = " + jezik.getIDJezika();
	}

	@Override
	public String setUpdateValues() {
		return "Ime = '" + ime + "', Prezime = '" + prezime + "', Telefon = '" + telefon + "', Email = '" + email
				+ "', IDJezika = " + jezik.getIDJezika();
	}

	@Override
	public GenericEntity fillFromRS(ResultSet rs) throws Exception {
		if (rs.next()) {
			IDProfesora = rs.getLong("IDProfesora");
			ime = rs.getString("Ime");
			prezime = rs.getString("Prezime");
			telefon = rs.getString("Telefon");
			email = rs.getString("Email");
			long id = rs.getLong("IDJezika");
			String naziv = rs.getString("Naziv");
			jezik = new Jezik(id, naziv);

			return this;
		}
		throw new Exception("Profesor ne postoji u bazi!");
	}

	@Override
	public List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception {
		List<GenericEntity> list = new ArrayList<>();
		while (rs.next()) {
			long idP = rs.getLong("p.IDProfesora");
			String imeP = rs.getString("p.Ime");
			String prezimeP = rs.getString("p.Prezime");
			String telefonP = rs.getString("p.Telefon");
			String emailP = rs.getString("p.Email");

			long idJ = rs.getLong("j.IDJezika");
			String nazivJ = rs.getString("j.Naziv");
			Jezik j = new Jezik(idJ, nazivJ);

			Profesor p = new Profesor(idP, imeP, prezimeP, telefonP, emailP, j);
			list.add(p);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		return " p join jezik j on p.IDJezika = j.IDJezika";
	}

	@Override
	public String getConditionForMore() {

		String rez;

		if (ime == null && prezime == null && jezik == null) {
			return "";
		} else {
			rez = " where";
			if (jezik != null) {
				rez += " p.IDJezika = " + jezik.getIDJezika() + " and";
			}
			if (ime != null) {
				rez += " p.Ime like '" + ime + "%' and";
			}
			if (prezime != null) {
				rez += " p.Prezime like '" + prezime + "%' and";
			}
		}
		return rez.substring(0, rez.length() - 4);
	}

	@Override
	public String getSort() {
		return " order by IDProfesora";
	}
}
