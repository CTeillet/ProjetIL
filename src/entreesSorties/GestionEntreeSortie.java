/**
 * 
 */
package entreesSorties;

import java.io.*;

import exception.ProblemeOuvertureFichier;
import interfaces.IEntreeSortie;
import interfaces.IGestionLivre;
import interfaces.ILivre;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @!generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class GestionEntreeSortie implements IEntreeSortie{
	IGestionLivre gl;
	
	public GestionEntreeSortie(IGestionLivre gl) {
		this.gl = gl;
	}
	
	@Override
	public void chargerLivre(File fichier)  throws ProblemeOuvertureFichier{
		try(FileInputStream fis = new FileInputStream(fichier);){
			ObjectInputStream ois = new ObjectInputStream(fis);
			gl.setLivre((ILivre)ois);
			gl.setLivre((ILivre)ois);
			fis.close();
		}catch(FileNotFoundException f) {
			throw new ProblemeOuvertureFichier();
		} catch (IOException e) {
			throw new ProblemeOuvertureFichier();
		}
	}

	@Override
	public boolean sauvegardeLivre(File choose) throws ProblemeOuvertureFichier {
		ILivre lv = gl.getLivre();
		try(FileOutputStream fos = new FileOutputStream(choose);){
		      ObjectOutputStream oos = new ObjectOutputStream(fos);
		      oos.writeObject(lv);
		      return true;
		}catch(FileNotFoundException f) {
			throw new ProblemeOuvertureFichier();
		} catch (IOException e) {
			throw new ProblemeOuvertureFichier();
		}
	}
}