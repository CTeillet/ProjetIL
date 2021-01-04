package interfaces;

import java.util.List;

public interface ILivre {
	public static final int NUMERODEPART = 1;
	public void setTitre(String titre);
	public String getTitre();
	public List<ISection> getSections();
	public ISection getSectionDepart();
	public void setSectionDepart(ISection section);
	public boolean ajoutection(ISection section);
}