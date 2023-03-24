package markupNew;

public class Paragraph extends Signs {

    public Paragraph(Result r) {
        super.data = r.getD();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
            stringBuilder.append("<p>");
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
            stringBuilder.append("</p>");
    }
}
