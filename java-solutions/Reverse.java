import RJutils.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;


public class Reverse {
    public static void main(String[] args)throws IOException {

        LinkedList<LinkedList<Integer>> data = new LinkedList<>();

        Rj_scanner rrj = new Rj_scanner(System.in);
        Rj_scanner rrjLine;
        String temp;
        while((temp = rrj.readLine()) != null){
            rrjLine = new Rj_scanner(temp);
            data.addFirst(new LinkedList<>());

            String temp2;
            while((temp2 = rrjLine.nextInt()) != null){
                data.getFirst().addFirst(Integer.parseInt(temp2));
            }
        }

//        for (LinkedList<Integer> t1 : data) {
//            for (Integer t2 : t1) {
//
//                System.out.print(t2 + " ");
//            }
//            System.out.println();



        ListIterator<LinkedList<Integer>> it1 = data.listIterator(0);
        ListIterator it2;


        while (it1.hasNext()) {
            it2 = it1.next().listIterator(0);
            while (it2.hasNext()) {
                System.out.print(it2.next() + " ");
            }
                System.out.println();
        }
    }
}
