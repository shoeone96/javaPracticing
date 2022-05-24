package messagePassing;

import java.util.ArrayList;

public class Main {
    public static void main(String [] args){
        User user1 = new User(11, "Songpa");
        ArrayList<Bicycle> bicycleList = new ArrayList<>();

        Bicycle bicycle1 = new Bicycle("Songpa", 1);
        Bicycle bicycle2 = new Bicycle("Gangnam", 2);
        Bicycle bicycle3 = new Bicycle("Songpa", 3);
        Bicycle bicycle4 = new Bicycle("Songpa", 4);
        Bicycle bicycle5 = new Bicycle("Gangnam", 5);
        Bicycle bicycle6 = new Bicycle("Songpa", 6);
        Bicycle bicycle7 = new Bicycle("Songpa", 7);
        Bicycle bicycle8 = new Bicycle("Incheon", 8);
        Bicycle bicycle9 = new Bicycle("Incheon", 9);
        Bicycle bicycle10 = new Bicycle("Songpa", 10);

        bicycleList.add(bicycle1);
        bicycleList.add(bicycle2);
        bicycleList.add(bicycle3);
        bicycleList.add(bicycle4);
        bicycleList.add(bicycle5);
        bicycleList.add(bicycle6);
        bicycleList.add(bicycle7);
        bicycleList.add(bicycle8);
        bicycleList.add(bicycle9);
        bicycleList.add(bicycle10);

        user1.checkBicycleList(user1.getUserLocation(), bicycleList);
        System.out.println(bicycle3.getBicycleLocation());
        user1.usingBicycle(bicycle3);
        System.out.println(bicycle3.getUserId());
        user1.usingBicycle(bicycle2);
        user1.returnBicycle(bicycle3, "Gangnam");
        System.out.println(bicycle3.getBicycleLocation());

    }
}
