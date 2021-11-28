package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Profesor implements GenericEntity {

	private long IDProfesora;
	private String ime;
	private String prezime;
	private String telefon;
	private String email;
	private Jezik jezik;

	public Profesor() {
	}

	public Profesor(long IDProfesora, String ime, String prezime, String telefon, String email, Jezik jezik) {
		this.IDProfesora = IDProfesora;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
		this.jezik = jezik;
	}

	public Jezik getJezik() {
		return jezik;
	}

	public void setJezik(Jezik jezik) {
		this.jezik = jezik;
	}

	public long getIDProfesora() {
		return IDProfesora;
	}

	public void setIDProfesora(long IDProfesora) {
		this.IDProfesora = IDProfesora;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
