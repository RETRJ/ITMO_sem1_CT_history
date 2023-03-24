package markup;

import java.util.List;

public class Strong extends Signs {

    public Strong(List<Markupable> l) {
        super.addLeft = "__";
        super.addRight = "__";
        super.addL2 = "\\textbf{";
        super.addR2 = "}";
        super.data = l;
    }
}
