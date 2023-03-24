import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class My2DIntArray2 {
    private int[][] arr;
    private int nL = 0;
    private int maxL = -1;


    public My2DIntArray2() {
        arr = new int[0][1];
    }


    public int[] get(int ind) throws RuntimeException {
        if (ind < arr.length) {
            return arr[ind];
        } else {
            throw new RuntimeException("каким образом это вызвалось?");
        }
    }

    public boolean exists(int y, int x) {
        if (y >= arr.length) {
            return false;
        } else if (x >= arr[y].length) {
            return false;
        } else {
            return true;
        }
    }

    public int getLength() {
        return arr.length;
    }

    public int getChildLength(int ind) {
        return arr[ind].length;
    }

    public int getMaxX() {
        return maxL;
    }

    public void shrinkX() {
        if (maxL < nL) {
            maxL = nL;
        }
        arr[arr.length - 1] = Arrays.copyOf(arr[arr.length - 1], nL);
    }

    private void increaseArrY() {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = new int[1];
        if (maxL < nL) {
            maxL = nL;
        }
        nL = 0;
        //System.out.println("ARR_Y +: "+arr.length);
    }

    private void increaseArrX(int ind) {
        arr[ind] = Arrays.copyOf(arr[ind], arr[ind].length * 2);
    }

    public void appendY() {
        increaseArrY();
    }

    public void appendX(int n) {
        if (nL >= arr[arr.length - 1].length) {
            increaseArrX(arr.length - 1);
        }

        get(arr.length - 1)[nL] = n;
        nL++;
    }

}

public class ReverseTranspose {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                My2DIntArray2 data = new My2DIntArray2();
                String temp;
                StringBuilder now = new StringBuilder();
                while ((temp = reader.readLine()) != null) {
                    data.appendY();
                    for (int i = 0; i < temp.length(); i++) {
                        if (!Character.isWhitespace(temp.charAt(i))) {
                            now.append(temp.charAt(i));
                        } else {
                            if (!now.isEmpty()) {
                                data.appendX(Integer.parseInt(now.toString()));
                            }
                            now = new StringBuilder();
                        }
                    }
                    if (!now.isEmpty()) {
                        data.appendX(Integer.parseInt(now.toString()));
                    }
                    now = new StringBuilder();
                    data.shrinkX();
                }

//                for(int i = 0; i < data.getLength();i++){
//                    for(int j = 0; j < data.getChildLength(i);j++){
//                        System.out.print(String.valueOf(data.get(i)[j]) + " ");
//                    }
//                    System.out.println();
//                }

//                for(int i = data.getLength()-1; i >= 0;i--){
//                    for(int j = data.getChildLength(i)-1; j >= 0;j--){
//                        System.out.print(String.valueOf(data.get(i)[j]) + " ");
//                    }
//                    System.out.println();
//                }


                for (int j = 0; j < data.getMaxX(); j++) {//x
                    for (int i = 0; i < data.getLength(); i++) {//y

                        if (data.exists(i, j)) {
                            System.out.print(String.valueOf(data.get(i)[j]) + " ");
                        }


                    }
                    System.out.println();
                }


            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
