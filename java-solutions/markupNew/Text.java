package markupNew;

public class Text implements Markupable {
    String data;


    public Text(String str) {
        data = str;
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {

            stringBuilder.append(data);
    }

}
