package RJutils;

import java.io.IOException;
import java.util.Arrays;

public class StrArray {
    private String[] arr;
    private int length = 0;
    private final double multi;


    public StrArray(int cap) {
        arr = new String[cap];
        multi = 2;

    }

    public StrArray(int cap, double m) {
        arr = new String[cap];
        multi = m;
    }

    public int GetCapacity() {
        return arr.length;
    }

    public int GetLength() {
        return length;
    }

    public String Get(int ind) throws IOException {
        if (ind >= length || ind < 0) {
            throw new IOException("Праизащел выход за границы массива APPEND :(");
        } else {
            return arr[ind];
        }
    }

    //    private void IncreaseArr(){
//        String[] temp = new String[(int)(arr.length* multi)+1];
//        System.arraycopy(arr,0,temp,0,arr.length);
//        arr = temp;
//    }
    private void IncreaseArr() {
        arr = Arrays.copyOf(arr, (int) (arr.length * multi) + 1);
    }

    public void Append(String num) {
        if (length >= arr.length) {
            IncreaseArr();
        }
        arr[length++] = num;
    }

    public int isExist(String s) {
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
}
