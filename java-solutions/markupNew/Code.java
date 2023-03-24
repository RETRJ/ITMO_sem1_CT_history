package markupNew;

public class Code extends Signs {

    public Code(Result r, String tag) {
        super.data = r.getD();
        super.myTag = tag;
        super.isClosed = r.isEndClosed();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        if(isClosed) {
            stringBuilder.append("<code>");
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
            stringBuilder.append("</code>");
        }else{
            stringBuilder.append(myTag);
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
        }
    }
}

