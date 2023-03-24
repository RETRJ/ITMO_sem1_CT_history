package markup;

import java.util.List;

public abstract class Signs implements Markupable {
    protected List<Markupable> data;
    protected String addLeft;
    protected String addL2;
    protected String addRight;
    protected String addR2;

    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(addLeft);
        for (var t : data) {
            t.toMarkdown(stringBuilder);
        }
        stringBuilder.append(addRight);
    }

    public void toTex(StringBuilder stringBuilder) {
        stringBuilder.append(addL2);
        for (var t : data) {
            t.toTex(stringBuilder);
        }
        stringBuilder.append(addR2);
    }
}
