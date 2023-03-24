package RJutils;

import java.io.IOException;
import java.util.Arrays;

public class IntArray {
    private int[] arr;
    private int length = 0;
    private final double multi;

    public IntArray(int cap) {
        arr = new int[cap];
        multi = 2;
    }

    public IntArray(int cap, double m) {
        arr = new int[cap];
        multi = m;
    }

    public int GetCapacity() {
        return arr.length;
    }

    public int GetLength() {
        return length;
    }

    public int Get(int ind) throws IOException {
        if (ind >= length || ind < 0) {
            throw new IOException("Праизащел выход за границы массива APPEND :(");
        } else {
            return arr[ind];
        }
    }

    public void addToInd(int ind, int num) throws IOException {
        if (ind >= length || ind < 0) {
            throw new IOException("Праизащел выход за границы массива ADD :(");
        } else {
            arr[ind] += num;
        }
    }

    public void set(int ind, int n) {
        arr[ind] = n;
    }

    //    private void IncreaseArr(){
//        int[] temp = new int[(int)(arr.length* multi)+1];
//        System.arraycopy(arr,0,temp,0,arr.length);
//        arr = temp;
//    }
    private void IncreaseArr() {
        arr = Arrays.copyOf(arr, (int) (arr.length * multi) + 1);
    }

    public void Append(int num) {
        if (length >= arr.length) {
            IncreaseArr();
        }
        arr[length++] = num;
    }
}
