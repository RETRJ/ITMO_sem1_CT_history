package markup;

import java.util.List;

public class Text implements Markupable {
    String data;

    public Text(String str) {
        data = str;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(data);
    }

    public void toTex(StringBuilder stringBuilder) {
        stringBuilder.append(data);
    }
}
