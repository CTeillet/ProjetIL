package GestionLivre;
/**
 * 
 */

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import interfaces.IEnchainement;
import interfaces.IObjet;
import interfaces.ISection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Enchainement implements IEnchainement, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2549705095742446465L;
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
	private int id;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private ISection sectionArrive;
    
    /** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
    */

    private Set<IObjet> conditions = new HashSet<>();

    private static int cpt = 0;

    public Enchainement(ISection depart, ISection arrive)
    {
        id = cpt;
        cpt++;
        this.sectionDepart = depart;
        this.sectionArrive = arrive;
    }
    
	public Enchainement(ISection depart, ISection arrive, List<IObjet> objs) {
		this(depart, arrive);
		conditions.addAll(objs);
	}

	@Override
	public ISection getDepart() {
		return sectionDepart;
	}
	@Override
	public void entrerSectionArrivee(ISection s) {
		sectionArrive = s;
	}
	@Override
	public void entrerSectionDepart(ISection s) {
		sectionDepart = s;
	}
	@Override
	public ISection getArrive() {
		return sectionArrive;
	}
	@Override
    public void ajouterCondition(IObjet... cond) {
        for(IObjet o : cond)
            conditions.add(o);
	}
	@Override
	public Set<IObjet> getCondition() {
		return conditions;
	}

	@Override
	public String toString() {
		//if(condition.size()>0) res.append(", ")
		return "Enchainement " + sectionDepart + " - " + sectionArrive;
	}
}