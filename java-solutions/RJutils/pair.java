package RJutils;

public class pair<T extends Comparable <T>,T2 extends Comparable <T2>> implements Comparable<pair<T,T2>> {
    private T first;
    private T2 second;
    public pair(T a,T2 b){
        first = a;
        second = b;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public boolean isEq(T t){
        if(t.getClass().equals(first.getClass()) && first.equals(t)){
            return true;
        }else{
            return false;
        }
    }

    @Override public int compareTo(pair<T,T2> p){
        return first.compareTo(p.first);
    }

    @Override
    public String toString(){
        return first.toString()+" "+second.toString();
    }

}
