package exportation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import interfaces.IEnchainement;
import interfaces.IExportation;
import interfaces.IGestionLivre;
import interfaces.IObjet;
import interfaces.ISection;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class GestionExportation implements IExportation {
	private static Font firstFont = new Font(Font.FontFamily.TIMES_ROMAN, 40,
			Font.BOLD);
	private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font txtFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL);
	private static Font objectFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.BOLD, BaseColor.BLUE);
	private static Font enchainementFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD, BaseColor.DARK_GRAY);

	IGestionLivre igl;


	public GestionExportation(IGestionLivre igl) {
		this.igl = igl;
	}


	@Override
	public void generationHTML(File file) {
		// TODO Module de remplacement de m?thode auto-g?n?r?
	}

	@Override
	public void genererVersionImprimable(File file) {
		// init doc
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		doc.open();
		try {
			Paragraph p = new Paragraph(igl.getLivre().getTitre(), firstFont);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			doc.newPage();
			doc.newPage();
			List<ISection> lsec = igl.melangeSection();
			//ecriture des chapitre
			for(ISection sec : lsec) {
				doc.add(new Paragraph("Section " + sec.getId(), titleFont));
				doc.add(new Paragraph(sec.getTexte(), txtFont));
				Set<IObjet> objs = sec.getObjetATrouver();
				if (objs != null) {
					doc.add(new Paragraph("objet recupéré :", txtFont));
					String ores = "";
					for (IObjet obj : objs) {
						ores += "- " + obj.getNomObjet() + "\n";
					}
					doc.add(new Paragraph(ores, objectFont));
				}
				Set<IEnchainement> ens = sec.getEnchainements();
				System.out.println(ens);
				if(ens != null) {
					doc.add(new Paragraph("option choisi :", txtFont));
					int choix = 1;
					for (IEnchainement en : ens) {
						String res = "choix" + choix++ + " : rdv section " + en.getArrive().getId() + " ";
						Set<IObjet> conds = en.getCondition();
						if(conds != null) {
							res += "si vous avez : \n";
							for(IObjet o : conds) {
								res += "- " + o.getNomObjet() + "\n";
							}
						}
						doc.add(new Paragraph(res, enchainementFont));
					}

				}
				doc.newPage();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally {
			doc.close();
		}
	}

}
