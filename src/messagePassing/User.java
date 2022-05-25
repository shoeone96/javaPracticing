package messagePassing;

import java.time.LocalDateTime;
import java.util.ArrayList;

// 어플 이용자
public class User {
    // 자전거에서 아무도 이용안할 때 user 의 id를 0으로 만들기 때문에 새로 생성되는 userId는 0이어서는 안된다
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

    private int id; // 이용자의 ID
    private String userLocation;  // 이용자 현재 위치
    private boolean using; // 자전거 이용중 여부
    private boolean voucher;  // 이용권 소유 여부
    private LocalDateTime buyingVoucherTime;  // 바우처 구매 시간
    private LocalDateTime expireVoucherTime;  // 바우처 만료 시간

    public void setUsing(boolean using) {
        this.using = using;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    //	이용 가능 자전거 리스트 불러오기, 지역 상관없이 불러올 수 있다, 어느 자전거가 어느 위치에 있는지도 알려준다
    public void checkBicycleList(ArrayList<Bicycle> BicycleList) {
        ArrayList<Bicycle> usableBicycle = new ArrayList<>();
        for (Bicycle bicycle : BicycleList) {
            if (!bicycle.getUsingCondition()) {
                usableBicycle.add(bicycle);
            }
        }
        System.out.println("현재 이용 가능한 자전거와 자전거의 위치는 다음과 같습니다");
        for (Bicycle bicycle : usableBicycle) {
            System.out.println("자전거 id: " + bicycle.getBicycleId() + ", 자전거 위치: " + bicycle.getBicycleLocation());
        }
    }

    /*
      자전거 이용하기
      message passing 반영
      자전거 이용시 자전거의 이용중 여부, 자전거를 이용하는 사람의 ID, 자전거 이용자의 자전거 이용중 여부가 변경
      추가적으로 바우처 소유 여부를 체크 후, 현재 시간과 바우처 만료 시간 비교해 만료시간이 되었거나 초과하면 바우처를 파기하고 사용할 수 없다고 전달
     */
    public void usingBicycle(Bicycle bicycle) {
        if (!voucher) {
            System.out.println("이용권을 먼저 구매해주세요");
            return;
        } else if (LocalDateTime.now().isAfter(expireVoucherTime) || LocalDateTime.now().isEqual(expireVoucherTime) ) {
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
    public void buyingVoucher(int money) {
        if (money < 1000) {
            System.out.println("따릉이 이용권은 1000원입니다");
        } else {
            voucher = true;
            buyingVoucherTime = LocalDateTime.now();
            expireVoucherTime = buyingVoucherTime.plusHours(1);
            System.out.println("이용권을 구매하였습니다.");
            System.out.println("거스름돈은 " + (money - 1000) + "원 입니다");
            System.out.println("이용권 시작 시간은 " + buyingVoucherTime.getHour() + "시 " + buyingVoucherTime.getMinute() + "분 " + buyingVoucherTime.getSecond() + "초 입니다");
            System.out.println("이용권 만료 시간은 " + expireVoucherTime.getHour() + "시 " + expireVoucherTime.getMinute() + "분 " + expireVoucherTime.getSecond() + "초 입니다");
        }
    }
}
