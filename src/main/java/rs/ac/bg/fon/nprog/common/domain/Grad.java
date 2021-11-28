package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grad implements GenericEntity {

	private long IDGrada;
	private String naziv;
	private ArrayList<Adresa> adrese;

	public Grad() {
		this.adrese = new ArrayList<>();
	}

	public Grad(long IDGrada, String naziv, ArrayList<Adresa> adrese) {
		this.IDGrada = IDGrada;
		this.naziv = naziv;
		this.adrese = adrese;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public long getIDGrada() {
		return IDGrada;
	}

	public void setIDGrada(long IDGrada) {
		this.IDGrada = IDGrada;
	}

	public ArrayList<Adresa> getAdrese() {
		return adrese;
	}

	public void setAdrese(ArrayList<Adresa> adrese) {
		this.adrese = adrese;
	}

	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + (int) (this.IDGrada ^ (this.IDGrada >>> 32));
		hash = 41 * hash + Objects.hashCode(this.naziv);
		hash = 41 * hash + Objects.hashCode(this.adrese);
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
		final Grad other = (Grad) obj;
		if (this.IDGrada != other.IDGrada) {
			return false;
		}
		if (!Objects.equals(this.naziv, other.naziv)) {
			return false;
		}
		if (!Objects.equals(this.adrese, other.adrese)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "grad";
	}

	@Override
	public String getColumnNamesForInsert() {
		return "IDGrada, Naziv";
	}

	@Override
	public String getInsertValues() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public void setId(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getConditionForOne() {
		return "IDGrada = " + IDGrada;
	}

	@Override
	public String setUpdateValues() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
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
			long idG = rs.getLong("IDGrada");
			String nazivG = rs.getString("Naziv");

			Grad g = new Grad(idG, nazivG, null);
			list.add(g);
		}
		return list;
	}

	@Override
	public String getJoinCondition() {
		// return " g JOIN adresa a ON g.IDGrada = a.IDGrada";
		return "";
	}

	@Override
	public String getConditionForMore() {
		return "";
	}

	@Override
	public String getSort() {
		return " order by IDGrada";
	}

}
