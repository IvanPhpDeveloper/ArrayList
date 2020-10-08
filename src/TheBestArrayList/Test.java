package TheBestArrayList;


import java.util.ArrayList;
import java.util.ListIterator;

public class Test {



    public static void main(String[] args) {

        TheBestArrayList<String> color_list= new TheBestArrayList<String>();
        ArrayList <String> sample=new ArrayList<>();


       color_list.add("white");
        color_list.add("black");
        color_list.add("red");
        color_list.add("black");
        color_list.add(null);


        sample.add("green");
        sample.add("black");
        sample.add("red");
        sample.add("green");
        sample.add(null);

//        color_list.ensureCapacity(10); ???
        color_list.add("blue");



        for(String col:color_list){
            System.out.println(col);
        }

        int size=color_list.size();
        System.out.println("ArraySize: "+size);

        if(color_list.isEmpty()){
            System.out.println("The ArrayList is empty");
        }else{
            System.out.println("The ArrayList is not empty");
        }

        System.out.print("Is list contains the student Tom?");
        System.out.println(color_list.contains("white"));
        System.out.print("Is list contains the student Tom?");
        System.out.println(color_list.contains("dark"));



        int indexOf=color_list.indexOf("black");
        System.out.println("The color White is at index " + indexOf);

        int lastIndexOf=color_list.lastIndexOf("black");
        System.out.println("The last occurrence of White color is at :" + lastIndexOf);

        Object[] obj = color_list.toArray();


        System.out.println("Printing elements from first to last:");
        for (Object value : obj) {
            System.out.println("Color = " + value);
        }


        for (int i = 0; i < 5; i++)
        {
            System.out.println(color_list.get(i));
        }

        color_list.set(2,"yellow");
        for (int i = 0; i < 5; i++)
        {
            System.out.println(color_list.get(i));
        }
        color_list.add(5,"pink");
        color_list.remove(3);
        System.out.println("New size"+size);

        for (int i = 0; i <5 ; i++) {
            System.out.println("Fresh color list is " + color_list.get(i));
        }


        ArrayList<String> color_list1 = new ArrayList<String>(7);

        // use add() method to add values in the list
        color_list.add("White");
        color_list.add("Black");
        color_list.add("Red");
        color_list.add("White");
        color_list.add("Yellow");
        color_list.add("Red");
        color_list.add("White");


        color_list1.remove("White");
        color_list1.remove("Red");

        for (int i = 0; i < 5; i++)
        {
            System.out.println(color_list.get(i));
        }

        color_list.addAll(sample);
        for (int i = 0; i < 10; i++)
        {
            System.out.println(color_list.get(i));
            System.out.println(color_list.size());
        }


        sample.removeAll(color_list);
        System.out.println("First List :"+ color_list);
        System.out.println("Second List :"+ sample);


        sample.retainAll(color_list);
        System.out.println("First List :"+ color_list);
        System.out.println("Second List :"+ sample);




        ListIterator itrf = color_list.listIterator(1);
        ListIterator itrb = color_list.listIterator(2);


        System.out.println("Iterating in forward direction from 2nd position");
        while(itrf.hasNext())
            System.out.println(itrf.next());



        System.out.println("Iterating in backward direction from 2nd position");

        while(itrb.hasPrevious())
            System.out.println(itrb.previous());

        ArrayList<String>  new_color_list=new ArrayList<>(color_list.subList(1,3));
        System.out.println("New color list: "+new_color_list);




    }
}


