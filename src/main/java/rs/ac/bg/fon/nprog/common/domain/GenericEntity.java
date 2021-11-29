package rs.ac.bg.fon.nprog.common.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 * 
 * Javni interfejs koji predstavlja opsti entitet koga implementiraju sve
 * domenske klase sistema.
 * 
 * @author Maja
 * @version 0.1
 */
public interface GenericEntity extends Serializable {

	/**
	 * Vraca naziv tabele iz baze kao String.
	 * 
	 * @return Naziv tabele kao String.
	 */
	String getTableName();

	/**
	 * Vraca nazive kolona tabele u koje ce biti upisana neka vrednost.
	 * 
	 * @return Nazivi kolona tabele kao jedan String.
	 */
	String getColumnNamesForInsert();

	/**
	 * Vraca konkretne vrednosti za kolone tabele koje treba ubaciti.
	 * 
	 * @return Vrednosti kolona tabele kao jedan String.
	 */
	String getInsertValues();

	/**
	 * Postavlja vrednost id-a tabele iz baze kao vrednost id-a odredjene domenske
	 * klase.
	 * 
	 * @param id Id izvucen iz baze kao Long.
	 */
	void setId(Long id);

	/**
	 * Vraca uslov pretrage za 'where' deo upita koji treba jedinstveno da
	 * identifikuje slog tabele.
	 * 
	 * @return Naziv kolone tabele i njenu vrednost za jedan slog u bazi kao String.
	 */
	String getConditionForOne();

	/**
	 * Vraca nazive kolona tabele i njihove nove vrednosti kako bi se podaci
	 * azurirali.
	 * 
	 * @return Nazive kolona tabele i njihove vrednosti za jedan slog kao String.
	 */
	String setUpdateValues();

	/**
	 * 
	 * @param rs Slogovi iz baze kao ResultSet.
	 * @return GenericEntity (Opsti entitet) dobijen materijalizacijom iz
	 *         ResultSeta.
	 * @throws Exception ako je rs null i trazeni objekat ne postoji u bazi.
	 */
	GenericEntity fillFromRS(ResultSet rs) throws Exception;

	/**
	 * 
	 * @param rs Slogovi iz baze kao ResultSet.
	 * @return lista GenericEntity (opstih entiteta) dobijenih materijalizacijom iz
	 *         ResultSeta.
	 * @throws Exception ako je rs null i nema rezultata pretrage.
	 */
	List<GenericEntity> fillListFromRS(ResultSet rs) throws Exception;

	/**
	 * Vraca uslov za spajanje dve ili vise tabela.
	 * 
	 * @return Join uslov kao String.
	 */
	String getJoinCondition();

	/**
	 * Vraca uslov pretrage za 'where' deo upita koji moze biti po vise kriterijuma
	 * i vise slogova tabele mogu zadovoljiti te kriterijume.
	 * 
	 * @return Nazive kolona tabele i njihove vrednosti za jedan slog u bazi kao
	 *         String.
	 */
	String getConditionForMore();

	/**
	 * Vraca uslov za sortiranje rezultata upita.
	 * 
	 * @return Order by uslov kao String.
	 */
	String getSort();
}
