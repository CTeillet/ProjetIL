/**
 * 
 */
package graphe;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.embed.swing.SwingFXUtils;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

import GestionLivre.GestionnaireLivre;
import interfaces.IEnchainement;
import interfaces.IGraphe;
import interfaces.IObjet;
import interfaces.ISection;
import javafx.scene.image.Image;
/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author teill
 * @!generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Graphe implements IGraphe{
	private GestionnaireLivre gl;
	
	
    public Graphe(GestionnaireLivre gl) {
		super();
		this.gl = gl;
	}


	@Override
    public Image afficherGraphe() {
        DefaultDirectedGraph<String, DefaultEdge> g=genererGrapheEnchainements();
        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
    
        BufferedImage capture = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.getColor("#f4f4f4"), true, null);
        Image image = SwingFXUtils.toFXImage(capture, null);
        return image;
}


    @Override
    public DefaultDirectedGraph<String, DefaultEdge> genererGrapheEnchainements() {
        DefaultDirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
        for(ISection sec : gl.getListeSections()) {
        	g.addVertex(sec.getNumero()+"");
        }
        for(ISection sec : gl.getListeSections()) {
        	for(IEnchainement enchainement : sec.getEnchainements()) {
        		g.addEdge(enchainement.getDepart().getNumero()+"",enchainement.getArrive().getNumero()+"");
        	}
        }
        
        return g;    
    }

    @Override
    public List<Integer> trouverSectionsInatteignables() {
    	List<ISection> sec = gl.getListeSections();
    	List<Integer> li = IntStream.of(new int[sec.size()])
                .boxed()
                .collect(Collectors.toList());
        List<IObjet> backpack=new ArrayList<>();
        ISection section= gl.getSectionNumero(gl.getLivre().NUMERODEPART);
        List<Integer> zeros=new ArrayList<>();
        for(Integer i : parcoursSection(li,backpack,section)) {
        	if(i==0)zeros.add(i);
        }
        return zeros;  
    }
    private List<Integer> parcoursSection(List<Integer> li, List<IObjet> backpack, ISection section){
        li.set(section.getNumero(),1);
        backpack.addAll(section.getObjetATrouver());
        for(IEnchainement ench : section.getEnchainements()) {
        	if(backpack.contains(ench.getCondition())) {
        		li = parcoursSection(li, backpack, ench.getArrive());
        	}
        }
        
        return li;
    }
}