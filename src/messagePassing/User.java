package messagePassing;

import java.time.LocalTime;
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
    private boolean voucher;
    private LocalTime buyingVoucherTime;
    private LocalTime expireVoucherTime;

    public void setUsing(boolean using) {
        this.using = using;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    //	이용 가능 자전거 리스트 불러오기, 지역 상관없이 불러올 수 있음
    public void checkBicycleList(ArrayList<Bicycle> BicycleList) {
        ArrayList<Bicycle> usableBicycle = new ArrayList<>();
        for (Bicycle bicycle : BicycleList) {
            if (!bicycle.getUsingCondition()) {
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
      추가적으로 바우처 소유 여부를 체크한 후의 현재 시간과 바우처 만료 시간이 동일하면 바우처를 파기하고 사용할 수 없다고 전달
     */

    public void usingBicycle(Bicycle bicycle) {
        if(!voucher){
            System.out.println("이용권을 먼저 구매해주세요");
            return;
        }else if (LocalTime.now() == expireVoucherTime){
            voucher = false;
            System.out.println("이용권 이용 시간이 만료되었습니다.");
        }
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
            voucher = false;
            System.out.println("사용을 종료합니다");
        } else System.out.println("이 자전거를 사용하고 있지 않습니다");
    }

    /*
    이용권을 구매하는 메서드
    1시간 이용 가능한 1회권 1000원에 구매하는 것으로 설정
    만료시간을 구매한 시점보다 1시간 뒤로 설정
     */
    public void buyingVoucher(int money){
        if (money < 1000){
            System.out.println("따릉이 이용권은 1000원입니다");
        }else {
            voucher = true;
            buyingVoucherTime = LocalTime.now();
            expireVoucherTime = buyingVoucherTime.plusHours(1);
            System.out.println("이용권을 구매하였습니다.");
            System.out.println("이용권 시작 시간은 " + buyingVoucherTime.getHour() + "시 " + buyingVoucherTime.getMinute() + "분 " + buyingVoucherTime.getSecond() + "초 입니다");
            System.out.println("이용권 만료 시간은 " + expireVoucherTime.getHour() + "시 " + expireVoucherTime.getMinute() + "분 " + expireVoucherTime.getSecond() + "초 입니다");
        }
    }



}
