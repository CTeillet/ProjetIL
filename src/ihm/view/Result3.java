package ihm.view;

import java.util.List;

import interfaces.IObjet;
import interfaces.ISection;

public class Result3 {
    private ISection first;
    private ISection snd;
    private List<IObjet> trd;

    public Result3(ISection first, ISection snd, List<IObjet> trd) {
        this.first = first;
        this.snd = snd;
        this.trd = trd;
    }

    public ISection getFirst() {
        return first;
    }

    public ISection getSnd() {
        return snd;
    }

    public List<IObjet> getTrd() {
        return trd;
    }
}
