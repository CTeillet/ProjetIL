package ihm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Pair;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import GestionLivre.GestionnaireLivre;
import entreesSorties.GestionEntreeSortie;
import exception.ProblemeOuvertureFichier;
import exportation.GestionExportation;
import graphe.Graphe;
import interfaces.IEnchainement;
import interfaces.ILivre;
import interfaces.IObjet;
import interfaces.ISection;

public class Controller {
    private final GestionnaireLivre lv = new GestionnaireLivre();
    private final Graphe g = new Graphe(lv);
    private final CustomDialog cd = new CustomDialog(this);
    private final GestionEntreeSortie es = new GestionEntreeSortie(lv);
    private final GestionExportation gE = new GestionExportation(lv);
    private final int TAILLETEXTE = 18;
    private List<Integer> inatteignables;

    @SuppressWarnings("rawtypes")
    @FXML
    private TreeView tree;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ImageView imView;
    @FXML
    private Label labTexte;
    @FXML
    private Label labStatus;
    @FXML
    private Button toolBut;



    protected GestionnaireLivre getLv() {
        return lv;
    }

    @FXML
    private void createEnchainement(){
        if(!verifLivre()){
            cd.errorDialog("Pas de Livre crÃ©e").showAndWait();
        }else{
            if(verifSections(2)){
                cd.errorDialog("Il faut au moins deux sections pour crÃ©er un enchainement").showAndWait();
            }else{
                Optional<Result3> result = cd.customEnchainement().showAndWait();
                result.ifPresent(result3 -> {
                    lv.addEnchainement(result3.getFirst(), result3.getSnd(), result3.getTrd());
                    createTreeView();
                    refreshLabelStatus();
                });
            }
        }

    }

    @FXML
    private void createLivre(){
        if(verifLivre()){
            Optional<ButtonType> result;
            result = cd.confirmationDialog("Etes vous sures de vouloir Ã©craser le livre existant ?")
                    .showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                Optional<String> res = cd.customLivre().showAndWait();
                res.ifPresent(s ->{
                    lv.creerLivre(s);;
                    createTreeView();
                    refreshLabelStatus();
                });

            }
        }else{
            Optional<String> res = cd.customLivre().showAndWait();
            res.ifPresent(s -> {
                lv.creerLivre(s);
                createTreeView();
                refreshLabelStatus();
            });
        }
    }

    @FXML
    private void createSection(){
        if(!verifLivre()){
            cd.errorDialog("Pas de Livre crÃ©e").showAndWait();
        }else {
            Optional<Pair<Integer, String>> result = cd.customISection().showAndWait();
            result.ifPresent(integerStringPair -> lv.addSection(integerStringPair.getKey(),
                                                                integerStringPair.getValue()));
            createTreeView();
            refreshLabelStatus();
        }
    }
    @FXML
    private void createObjet() {
        if(!verifLivre()){
            cd.errorDialog("Pas de Livre crÃ©e").showAndWait();
        }else {
            if(verifSections(1)){
                cd.errorDialog("Une section est nÃ©cessaire pour crÃ©er un Objet").showAndWait();
            }else {
                Optional<Pair<String, ISection>> result = cd.customObjet().showAndWait();
                result.ifPresent(stringSectionPair -> lv.creerObjet(stringSectionPair.getKey(), stringSectionPair.getValue()));
                createTreeView();
                refreshLabelStatus();
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void createTreeView(){
        //Racine de l'arbre
        TreeItem root;
        root = new TreeItem(getLv().getLivre());

        //Section des Sections :
        for(ISection section : getLv().getListeSections()){
            setTreeItem(root, section);
        }

        //Ajout de la racine au TreeView
        tree.setRoot(root);
        tree.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if(t1!=null){
                Object temp = ((TreeItem)t1).getValue();
                if(temp instanceof IEnchainement){
                	IEnchainement ench = (IEnchainement) temp;
                    labTexte.setText(ench.toString());
                    //Creation des Textes
                    Text tx = new Text("Section de dÃ©part : ");
                    tx.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                    Text tx1 = new Text(ench.getDepart()+"\n");
                    tx1.setFont(Font.font("Helvetica", TAILLETEXTE));
                    Text tx2 = new Text("Section d'arrivÃ©e : ");
                    tx2.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                    Text tx3 = new Text(ench.getArrive()+"\n");
                    tx3.setFont(Font.font("Helvetica", TAILLETEXTE));
                    Text tx4 = new Text("Condition : \n");
                    tx4.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                    if(ench.getCondition().size()==0){
                        Text tx5 = new Text("Aucune\n");
                        tx5.setFont(Font.font("Helvetica", TAILLETEXTE));

                        //Ajout des Textes au TextFlow
                        textFlow.getChildren().setAll(tx, tx1, tx2, tx3, tx4, tx5);
                    }else{
                        textFlow.getChildren().setAll(tx, tx1, tx2, tx3, tx4);
                        for(IObjet obj : ench.getCondition()){
                            Text txx = new Text("\t-"+obj+"\n");
                            txx.setFont(Font.font("Arial", FontPosture.ITALIC, TAILLETEXTE));
                            textFlow.getChildren().add(txx);
                        }
                    }

                }else{
                    if(temp instanceof  ISection){
                        ISection sect = (ISection) temp;
                        labTexte.setText(sect.toString());
                        int nbObjetsSect = sect.getObjetATrouver().size();
                        int nbEnchainement = sect.getEnchainements().size();
                        Text tx = new Text("NumÃ©ro : ");
                        tx.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                        Text tx1 = new Text(sect.getNumero()+"\n");
                        tx1.setFont(Font.font("Helvetica", TAILLETEXTE));
                        Text tx2 = new Text("Texte : ");
                        tx2.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                        Text tx3 = new Text(sect.getTexte()+"\n");
                        tx3.setFont(Font.font("Helvetica", TAILLETEXTE));
                        Text tx4 = new Text("Nombre d'objets dans la sections : ");
                        tx4.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                        Text tx5 = new Text(nbObjetsSect+"");
                        tx5.setFont(Font.font("Helvetica", TAILLETEXTE));
                        Text tx6 = new Text("Nombre d'enchainements dans la sections : ");
                        tx6.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                        Text tx7 = new Text(nbEnchainement+"");
                        tx7.setFont(Font.font("Helvetica", TAILLETEXTE));
                        textFlow.getChildren().setAll(tx, tx1, tx2, tx3, tx4, tx5, tx7);
                    }else{
                        if(temp instanceof ILivre){
                            ILivre lv = (ILivre) temp;
                            labTexte.setText(lv.toString());
                            int nbSections = 0;
                            if(lv.getSectionDepart()!=null) nbSections++;
                            nbSections+=lv.getSections().size();
                            Text tx = new Text("Titre : ");
                            tx.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                            Text tx1 = new Text(lv.getTitre()+"\n");
                            tx1.setFont(Font.font("Helvetica", TAILLETEXTE));
                            Text tx2 = new Text("Nombre de Sections : ");
                            tx.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                            Text tx3 = new Text(nbSections+"\n");
                            textFlow.getChildren().setAll(tx, tx1, tx2, tx3);
                        }else{
                            if(temp instanceof String){
                                String str = (String) temp;
                                labTexte.setText(str);
                                Text tx = new Text(str);
                                tx.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                                textFlow.getChildren().setAll(tx);
                            }else{
                                if(temp instanceof IObjet){
                                    IObjet obj = (IObjet) temp;
                                    labTexte.setText(obj.toString());
                                    Text tx = new Text("Nom : ");
                                    tx.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                                    Text tx1 = new Text(obj.getNomObjet()+"\n");
                                    tx1.setFont(Font.font("Helvetica", TAILLETEXTE));
                                    Text tx2 = new Text("Section : ");
                                    tx2.setFont(Font.font("Helvetica", FontWeight.BOLD, TAILLETEXTE));
                                    Text tx3 = new Text(obj.getSection()+"\n");
                                    tx3.setFont(Font.font("Helvetica", TAILLETEXTE));
                                    textFlow.getChildren().setAll(tx, tx1, tx2, tx3);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void setTreeItem(TreeItem treeItem, ISection section){
        TreeItem sect = new TreeItem(section);

        //Ajouts des enchainements Ã  l'arbre
        if(section.getEnchainements().size()>0) {
            TreeItem ench = new TreeItem("Enchainements");
            for (IEnchainement enchainement : section.getEnchainements()) {
                TreeItem enchT = new TreeItem(enchainement);
                ench.getChildren().add(enchT);
            }
            sect.getChildren().add(ench);
        }

        if(section.getObjetATrouver().size()>0) {
            TreeItem objs = new TreeItem("Objets");
            for (IObjet objet : section.getObjetATrouver()) {
                TreeItem objT = new TreeItem(objet);
                objs.getChildren().add(objT);
            }
            sect.getChildren().add(objs);
        }
        treeItem.getChildren().add(sect);
    }

    private boolean verifLivre(){
        return !(getLv().getLivre() == null);
    }

    private boolean verifSections(int nb) {
        return getLv().getListeSections().size() < nb;
    }

    public void refreshLabelStatus(){
        if(lv.getLivre()!=null && lv.getLivre().getSectionDepart()!= null && getLv().getListeSections().size()>1) {
            inatteignables = g.trouverSectionsInatteignables();
            if (inatteignables.size() == 0) {
                labStatus.setText("Aucun problÃ¨mes");
                toolBut.setDisable(true);
                toolBut.setVisible(false);
            } else {
                labStatus.setText("Il y a " + inatteignables.size() + " sections inatteignables");
                toolBut.setDisable(false);
                toolBut.setVisible(true);
            }
        }
    }

    @FXML
    private void toolButAction(){
        if(inatteignables.size()>0){
            StringBuilder s = new StringBuilder();
            for(Integer i : inatteignables){
                s.append("\t- ").append("Section ").append(i).append("\n");
            }
            cd.problemesGraphe(s.toString()).showAndWait();
        }
    }
    
    @FXML
    private void menuExportPDF(ActionEvent event) {
    	File choose = fileChooser(event, false, "pdf");
    	if(choose!=null) {
    		gE.genererVersionImprimable(choose);
    	}
    }
    
    @FXML
    private void menuSaveLivre(ActionEvent event) {
    	File choose = fileChooser(event, false, "ldvh");
    	if(choose!=null) {
    		try {
				es.sauvegardeLivre(choose);
			} catch (ProblemeOuvertureFichier e) {
				//e.printStackTrace();
			}
    	}
    }
    
    @FXML
    private void menuChargerLivre(ActionEvent event) {
    	File choose = fileChooser(event, true, "ldvh");
    	if(choose!=null) {
            try {
                es.chargerLivre(choose);
            } catch (ProblemeOuvertureFichier problemeOuvertureFichier) {
                //problemeOuvertureFichier.printStackTrace();
            }
        }
    }
    
    private File fileChooser(ActionEvent event, boolean test, String format) {
    	 FileChooser fileChooser = new FileChooser();
    	 if(test) {
    		 fileChooser.setTitle("Ouverture du Fichier");
    	 }else {
    		 fileChooser.setTitle("Sauvegarde du Fichier");
    	 }
    	 fileChooser.getExtensionFilters().addAll(
    	         new ExtensionFilter("Fichier " + format, "."+format),
    	         new ExtensionFilter("All Files", "*.*"));

        MenuItem menuItem = (MenuItem)event.getTarget();
        ContextMenu cm = menuItem.getParentPopup();
        Scene scene = cm.getScene();
        Window window = scene.getWindow();
    	 if(test) {
    		 return fileChooser.showOpenDialog(window);
    	 }else {
    		 return fileChooser.showSaveDialog(window);
    	 }
    	
	}

	@FXML
    private void generateGraph(ActionEvent event){
        if(lv.getLivre()!=null && lv.getLivre().getSectionDepart()!= null && getLv().getListeSections().size()>1){
            Image img = g.afficherGraphe();
            imView.setImage(img);
        }
    }
}
