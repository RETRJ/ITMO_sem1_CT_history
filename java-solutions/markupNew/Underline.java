package markupNew;

public class Underline extends Signs {

    public Underline(Result r, String tag) {
        super.data = r.getD();
        super.myTag = tag;
        super.isClosed = r.isEndClosed();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        if(isClosed) {
            stringBuilder.append("<u>");
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
            stringBuilder.append("</u>");
        }else{
            stringBuilder.append(myTag);
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
        }
    }
}