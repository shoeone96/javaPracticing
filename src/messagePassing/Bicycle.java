package messagePassing;

// 이용할 자전거 class
public class Bicycle {
    // 처음 생성 시 user가 사용중이 아니기에 userId를 0으로 설정
    public Bicycle(String bicycleLocation, int bicycleId){
        this.bicycleId = bicycleId;
        this.bicycleLocation = bicycleLocation;
        this.usingCondition = false;
        this.userId = 0;
    }

    private final int bicycleId; // 자전거의 id
    private String bicycleLocation; // 자전거 현 위치
    private boolean usingCondition; // 사용중 여부
    private int userId; // 이용하는 사람의 id, 도난, 절도 등의 상황이 발생할 수 있어 사용 시 어떤 사람이 사용하는지 확인할 수 있게 설정

    public String getBicycleLocation() {
        return bicycleLocation;
    }

    public void setBicycleLocation(String bicycleLocation) {
        this.bicycleLocation = bicycleLocation;
    }

    public boolean getUsingCondition() {
        return usingCondition;
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setUsingCondition(boolean usingCondition) {
        this.usingCondition = usingCondition;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() { return userId;}
}
