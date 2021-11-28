package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Administrator implements GenericEntity {

	private long IDAdministratora;
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;

	public Administrator() {
	}

	public Administrator(long IDAdministratora, String ime, String prezime, String korisnickoIme, String lozinka) {
		this.IDAdministratora = IDAdministratora;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public long getIDAdministratora() {
		return IDAdministratora;
	}

	public void setIDAdministratora(long IDAdministratora) {
		this.IDAdministratora = IDAdministratora;
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

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

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
