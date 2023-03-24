package markup;

import java.util.List;

public class Emphasis extends Signs {

    public Emphasis(List<Markupable> l) {
        super.addLeft = "*";
        super.addRight = "*";
        super.addL2 = "\\emph{";
        super.addR2 = "}";
        super.data = l;
    }
}
