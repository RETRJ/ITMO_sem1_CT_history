package markupNew;

import java.util.List;

public class Result{
    private List<Markupable> d;
    private boolean endClosed = false;
    public Result (List<Markupable> d, boolean end){
        this.d = d;
        endClosed = end;
    }

    public List<Markupable> getD() {
        return d;
    }

    public boolean isEndClosed() {
        return endClosed;
    }
}
