package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Polaznik implements GenericEntity {

	private long IDPolaznika;
	private String ime;
	private String prezime;
	private String jmbg;
	private String telefon;
	private String email;
	private String adresa;

	public Polaznik() {
	}

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		if (adresa == null) {
			throw new NullPointerException("Adresa polaznika ne sme biti null vrednost!");
		}
		if (adresa.isEmpty()) {
			throw new RuntimeException("Adresa polaznika ne sme biti prazan string!");
		}
		this.adresa = adresa;
	}

	public long getIDPolaznika() {
		return IDPolaznika;
	}

	public void setIDPolaznika(long IDPolaznika) {
		this.IDPolaznika = IDPolaznika;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		if (ime == null) {
			throw new NullPointerException("Ime polaznika ne sme biti null vrednost!");
		}
		if (ime.isEmpty()) {
			throw new RuntimeException("Ime polaznika ne sme biti prazan string!");
		}
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		if (prezime == null) {
			throw new NullPointerException("Prezime polaznika ne sme biti null vrednost!");
		}
		if (prezime.isEmpty()) {
			throw new RuntimeException("Prezime polaznika ne sme biti prazan string!");
		}
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		if (jmbg == null) {
			throw new NullPointerException("JMBG polaznika ne sme biti null vrednost!");
		}
		if (!(jmbg.length() == 13)) {
			throw new RuntimeException("JMBG polaznika mora imati 13 cifara!");
		}
		this.jmbg = jmbg;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		if (telefon == null) {
			throw new NullPointerException("Telefon polaznika ne sme biti null vrednost!");
		}
		if (telefon.isEmpty()) {
			throw new RuntimeException("Telefon polaznika ne sme biti prazan string!");
		}
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

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
