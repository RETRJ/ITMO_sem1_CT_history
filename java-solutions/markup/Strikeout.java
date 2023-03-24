package markup;

import java.util.List;

public class Strikeout extends Signs {

    public Strikeout(List<Markupable> l) {
        super.addLeft = "~";
        super.addRight = "~";
        super.addL2 = "\\textst{";
        super.addR2 = "}";
        super.data = l;
    }
}
