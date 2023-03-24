package markupNew;

public class Emphasis extends Signs {

    public Emphasis(Result r, String tag) {
        super.data = r.getD();
        super.myTag = tag;
        super.isClosed = r.isEndClosed();
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        if(isClosed) {
            stringBuilder.append("<em>");
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
            stringBuilder.append("</em>");
        }else{
            stringBuilder.append(myTag);
            for (var t : data) {
                t.toHtml(stringBuilder);
            }
        }
    }
}

