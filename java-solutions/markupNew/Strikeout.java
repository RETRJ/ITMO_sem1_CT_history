package markupNew;

public class Strikeout extends Signs {

    public Strikeout(Result r, String tag) {
        super.data = r.getD();
        super.myTag = tag;
        super.isClosed = r.isEndClosed();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        if(isClosed) {
            stringBuilder.append("<s>");
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
            stringBuilder.append("</s>");
        }else{
            stringBuilder.append(myTag);
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
        }
    }
}
