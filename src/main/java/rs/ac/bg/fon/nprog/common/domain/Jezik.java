package rs.ac.bg.fon.nprog.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jezik implements GenericEntity {

	private long IDJezika;
	private String naziv;

	public Jezik() {
	}

	public Jezik(long IDJezika, String naziv) {
		this.IDJezika = IDJezika;
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		if (naziv == null) {
			throw new NullPointerException("Naziv jezika ne sme biti null vrednost!");
		}
		if (naziv.isEmpty()) {
			throw new RuntimeException("Naziv jezika ne sme biti prazan string!");
		}
		this.naziv = naziv;
	}

	public long getIDJezika() {
		return IDJezika;
	}

	public void setIDJezika(long IDJezika) {
		this.IDJezika = IDJezika;
	}

	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 31 * hash + (int) (this.IDJezika ^ (this.IDJezika >>> 32));
		hash = 31 * hash + Objects.hashCode(this.naziv);
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
		final Jezik other = (Jezik) obj;
		if (this.IDJezika != other.IDJezika) {
			return false;
		}
		if (!Objects.equals(this.naziv, other.naziv)) {
			return false;
		}
		return true;
	}

	@Override
	public String getTableName() {
		return "jezik";
	}

	@Override
	public String getColumnNamesForInsert() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
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
		return "IDJezika = " + IDJezika;
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
			long idJ = rs.getLong("IDJezika");
			String nazivJ = rs.getString("Naziv");

			Jezik j = new Jezik(idJ, nazivJ);
			list.add(j);
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
		return " order by IDJezika";
	}
}
