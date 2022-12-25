package Testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Tester{
    public static void main(String[] args) {

        HashMap <String, List<Integer>>hash=new HashMap<>();
        List<Integer>list=new ArrayList<>();
        list.add(1);
        list.add(2);
        hash.put("devi",list);
        System.out.println(hash.get("devi").add(3));
        System.out.println(hash.get("devi"));


    }
}

