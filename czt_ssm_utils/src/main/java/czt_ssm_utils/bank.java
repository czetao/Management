package czt_ssm_utils;
import java.util.LinkedList;

import java.util.List;

public class bank {
    public static void main(String[] args) {

        int num = 6;
        List<Integer> all = new LinkedList<Integer>();
        for(int i = 1;i <= num;i++){
            all.add(i);
        }
        System.out.println(all);

        int i = 0;
        int s =all.size();
        //System.out.println(all[0]);
        for(int n = 1;n < num ;n++){

            i = (i + 3) % all.size();
            System.out.print(all.get(i));
            all.remove(i);
        }


        System.out.println("最后剩下的是第 " + all.get(0) + " 个人");



    }
}
