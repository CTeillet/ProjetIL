/**
 * 
 */
package GestionLivre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.IGestionLivre;
import interfaces.ILivre;
import interfaces.IObjet;
import interfaces.ISection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class GestionnaireLivre implements IGestionLivre{
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private ILivre livre;

	@Override
	public ILivre getLivre() {
		return livre;
		
	}

	@Override
	public List<ISection> getListeSections() {
        List<ISection> res = new ArrayList<>();
        if(livre.getSectionDepart()!=null)res.add(livre.getSectionDepart());
        if(livre.getSections().size()>0)res.addAll(livre.getSections());
        return res;
	}
	
	public void creerObjet(String nom, int numeroSection) {
		IObjet obj = new ObjetATrouver(getSectionNumero(numeroSection), nom);
		ajoutObjetLivre(obj);
	}

	public boolean supprimerObjet(Integer id) {
		for (ISection sect : livre.getSections()) {
			for (IObjet ob : sect.getObjetATrouver()) {
				if(ob.getId()==id) {
					sect.getObjetATrouver().remove(ob);
					return true;
				}
			}
		}
		return false;
	}

	private boolean ajoutSectionLivre(ISection section) {
		return livre.ajoutection(section);
	}

	private boolean ajoutObjetLivre(IObjet obj) {
		ISection sect = obj.getSection();
		return sect.addObject(obj);
		
	}

	@Override
	public List<ISection> melangeSection() {
		List<ISection> lsec = getListeSections();
		List<Integer> range = new ArrayList<Integer>();
		for (int i = 1; i < lsec.size(); i++) {
			range.add(i);
		}
		Collections.shuffle(range);
		List<ISection> res = new ArrayList<ISection>();
		res.add(livre.getSectionDepart());

		for (int i : range) {
			lsec.get(i).setId(res.size());
			res.add(lsec.get(i));
		}
		return res;
	}

	@Override
	public void setLivre(ILivre livre) {
		this.livre = livre;
	}
	
	public ISection getSectionNumero(int numeroSection) {
		if(numeroSection==Livre.NUMERODEPART) return livre.getSectionDepart();
		for (ISection sect : livre.getSections()) {
			if(sect.getNumero() == numeroSection) return sect;
		}
		return null;
	}
	
	@Override
	public void creerLivre(String s) {
		livre = new Livre(s);
	}

	public boolean addEnchainement(ISection depart, ISection arrive, List<IObjet> objs) {
        if(depart == null || arrive == null) return false;
        depart.add(new Enchainement(depart, arrive, objs));
        return true;
	}

	public boolean addSection(Integer key, String value) {
		return ajoutSectionLivre(new Section(key, value));
	}
	
    public List<IObjet> getAllObject() {
        List<IObjet> res = new ArrayList<>();
        if(getListeSections().size()!=0){
            for(ISection sect : getListeSections()){
                res.addAll(sect.getObjetATrouver());
            }
        }
        return res;
    }
    
    public boolean verifSectionNumber(int test){
		if(test==0) return false;
        if(livre.getSectionDepart()==null && livre.getSections().size()==0)return true;
        for(ISection section : getListeSections()){
            if(section.getNumero()==test) return false;
        }
        return true;
    }

	public void creerObjet(String key, ISection value) {
		IObjet obj = new ObjetATrouver(value, key);
		ajoutObjetLivre(obj);
	}
}