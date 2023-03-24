package markupNew;

public class Header extends Signs{
    private final int lvl;

    public Header(Result r, int lvl) {
        this.lvl = lvl;
        super.data = r.getD();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append("<h").append(lvl).append(">");
        for (var t : data) {
            t.toHtml(stringBuilder);
        }
        stringBuilder.append("</h").append(lvl).append(">");
    }
}
