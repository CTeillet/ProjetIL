package interfaces;

/**
 * 
 */

import java.util.Set;

import GestionLivre.Enchainement;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author teill
* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface ISection {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param numero
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void entrerNumeroSection(Integer numero);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param texte
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void entrerTexteSection(String texte);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public String getTexte();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<IEnchainement> getEnchainements();
	
	public Set<IObjet> getObjetATrouver();

	public int getNumero();

	public boolean addObject(IObjet obj);

	public void add(Enchainement enchainement);

	boolean setId(int id);

	int getId();
}