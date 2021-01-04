package GestionLivre;

import java.io.Serializable;
import java.util.HashSet;

/**
 * 
 */

import java.util.Set;

import interfaces.IEnchainement;
import interfaces.IObjet;
import interfaces.ISection;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author teill
* @!generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Section implements ISection, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2843513115894130478L;

	private Set<IEnchainement> enchainements = new HashSet<>();
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	*/
	private Set<IObjet> objetATrouver = new HashSet<>();
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private int numero;
	
	private int id = cmp++;
	private static int cmp = 0;
	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private String texte;
	
	public Section(Integer key, String value) {
		this.numero = key;
		this.texte = value;
	}
	@Override
	public void entrerNumeroSection(Integer numero) {
		this.numero = numero;
		
	}
	@Override
	public void entrerTexteSection(String texte) {
		this.texte = texte;
		
	}
	@Override
	public String getTexte() {
		return texte;
	}
	
	public int getNumero() {
		return numero;
	}
	
	@Override
	public Set<IEnchainement> getEnchainements() {
		return enchainements;
	}
	
	public Set<IObjet> getObjetATrouver() {
		return objetATrouver;
	}
	public boolean addObject(IObjet e) {
		return objetATrouver.add(e);
	}
	@Override
	public void add(Enchainement enchainement) {
		enchainements.add(enchainement);
	}
	
	@Override 
	public int getId() {
		return id;
	}
	
	@Override
	public boolean setId(int id) {
		this.id = id;
		return true;
	}

	@Override
	public String toString() {
		return "Section " + numero ;
	}
}