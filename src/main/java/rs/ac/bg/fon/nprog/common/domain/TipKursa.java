package rs.ac.bg.fon.nprog.common.domain;

import java.io.Serializable;

/**
 * 
 * Enum koji predstavlja moguce tipove kurseva koji se pohadjaju u razlicite
 * svrhe.
 * 
 * @author Maja
 * @version 0.1
 */

public enum TipKursa implements Serializable {

	/**
	 * Poslovni tip kursa
	 */
	Poslovni,
	
	/**
	 * Konverzacijski tip kursa
	 */
	Konverzacijski,
	
	/**
	 * Deciji tip kursa
	 */
	Deciji,
	
	/**
	 * Opsti tip kursa
	 */
	Opsti;

}
