package messagePassing;

import java.util.ArrayList;

// 어플 이용자
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

    //	이용 가능 자전거 리스트 불러오기
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

    /*
      자전거 이용하기
      message passing 반영
      자전거 이용시 자전거의 이용중 여부, 자전거를 이용하는 사람의 ID, 자전거 이용자의 자전거 이용중 여부가 변경
     */

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

    /*
     자전거 반납하기
     message passing 반영
     자전거 반납 시 자전거와 사용자의 위치, 자전거와 사용자의 이용중 여부가 변경
     추가적으로 자전거에서 현 자전거 이용자의 ID가 0으로 변경
     */
    public void returnBicycle(Bicycle bicycle, String location) {
        if (bicycle.getUsingCondition()) {
            bicycle.setUsingCondition(false);
            bicycle.setUserId(0);
            bicycle.setBicycleLocation(location);
            setUserLocation(location);
            setUsing(false);
            System.out.println("사용을 종료합니다");
        } else System.out.println("이 자전거를 사용하고 있지 않습니다");
    }
}
