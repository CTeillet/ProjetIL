package interfaces;
/**
 * 
 */

import java.util.List;
import java.util.Set;

import javafx.scene.image.Image;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface IGraphe {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Image afficherGraphe();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public DefaultDirectedGraph<String, DefaultEdge> genererGrapheEnchainements();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<Integer> trouverSectionsInatteignables();
}