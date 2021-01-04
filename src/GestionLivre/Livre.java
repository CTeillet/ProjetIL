package GestionLivre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

import interfaces.ILivre;
import interfaces.ISection;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author teill
* @!generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class Livre implements ILivre, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2734584094966973419L;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @!generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private List<ISection> sections;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @!generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private String titre;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private ISection sectionDepart;
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public void setTitre(String titre) {
		this.titre = titre;
	}
	@Override
	public String getTitre() {
		return titre;
	}
	public List<ISection> getSections() {
		return sections;
	}
	public ISection getSectionDepart() {
		return sectionDepart;
	}
	public void setSectionDepart(ISection sectionDepart) {
		this.sectionDepart = sectionDepart;
	}
	
	public Livre() {
		sections = new ArrayList<>();
	}
	
	public Livre(String titre) {
		this();
		this.titre = titre;
	}
	
	public boolean ajoutection(ISection sect) {
        if(sect.getNumero()==NUMERODEPART){
            sectionDepart = sect;
            return true;
        }else{
            return sections.add(sect);
        }
	}

	@Override
	public String toString() {
		return "Livre : " + titre;
	}


	
}