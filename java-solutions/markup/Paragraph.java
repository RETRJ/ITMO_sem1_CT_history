package markup;

import java.util.List;

public class Paragraph extends Signs {

    public Paragraph(List<Markupable> l) {
        super.addLeft = "";
        super.addRight = "";
        super.addL2 = "";
        super.addR2 = "";
        super.data = l;
    }
}
