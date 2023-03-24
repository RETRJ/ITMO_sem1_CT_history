package markupNew;

public class StrongEmphasis extends Signs {

    public StrongEmphasis(Result r, String tag) {
        super.data = r.getD();
        super.myTag = tag;
        super.isClosed = r.isEndClosed();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        if(isClosed) {
            stringBuilder.append("<strong>");
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
            stringBuilder.append("</strong>");
        }else{
            stringBuilder.append(myTag);
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
        }
    }
}

