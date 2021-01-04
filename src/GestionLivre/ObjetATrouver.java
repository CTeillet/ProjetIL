package GestionLivre;
/**
 * 
 */

import java.io.Serializable;

import interfaces.IObjet;
import interfaces.ISection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ObjetATrouver implements IObjet, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8597381361045662127L;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private ISection section;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private String nom;
	private int id = cmp++;
	private static int cmp = 0;
	
	@Override
	public void entrerNomObjet(String nom) {
		this.nom = nom;
		
	}
	@Override
	public String getNomObjet() {
		return nom;
	}
	public ISection getSection() {
		return section;
	}
	
	public void setSection(ISection section) {
		this.section = section;
	}
	public ObjetATrouver(ISection section, String nom) {
		super();
		this.section = section;
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return nom + " " + section;
	}
}