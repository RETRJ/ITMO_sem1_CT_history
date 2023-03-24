package markupNew;

import java.util.List;

public abstract class Signs implements Markupable {

    protected boolean isClosed = false;
    protected String myTag;
    protected List<Markupable> data;

    public void setClosed() {
        isClosed = true;
    }
}
