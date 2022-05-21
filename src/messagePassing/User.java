package messagePassing;

import java.util.ArrayList;

public class User {
    public User(int id, String location) {
        if (id != 0) {
            this.id = id;
        } else {
            System.out.println("id는 0일 수 없습니다");
            return;
        }
        this.userLocation = location;
        this.using = false;
    }

    private int id;
    private String userLocation;
    private boolean using;

    public void setUsing(boolean using) {
        this.using = using;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public void checkBicycleList(String userLocation, ArrayList<Bicycle> BicycleList) {
        ArrayList<Bicycle> usableBicycle = new ArrayList<>();
        for (Bicycle bicycle : BicycleList) {
            if (userLocation.equals(bicycle.getBicycleLocation()) && !bicycle.getUsingCondition()) {
                usableBicycle.add(bicycle);
            }
        }
        for (Bicycle bicycle : usableBicycle) {
            System.out.println("자전거 id: " + bicycle.getBicycleId() + ", 자전거 위치: " + bicycle.getBicycleLocation());
        }
    }

    public void usingBicycle(Bicycle bicycle) {
        if (using) {
            System.out.println("이미 자전거를 이용중입니다");
        } else {
            if (bicycle.getUsingCondition()) {
                System.out.println("사용중인 자전거입니다");
            } else {
                bicycle.setUsingCondition(true);
                bicycle.setUserId(id);
                setUsing(true);
                System.out.println("자전거를 이용합니다");
            }
        }

    }

    public void returnBicycle(Bicycle bicycle, String location) {
        if (bicycle.getUsingCondition()) {
            bicycle.setUsingCondition(false);
            bicycle.setUserId(0);
            bicycle.setBicycleLocation(location);
            setUserLocation(location);
            System.out.println("사용을 종료합니다");
        } else System.out.println("이 자전거를 사용하고 있지 않습니다");
    }
}
