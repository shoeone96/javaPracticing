package messagePassing;

public class Bicycle {
    public Bicycle(String bicycleLocation, int bicycleId){
        this.bicycleId = bicycleId;
        this.bicycleLocation = bicycleLocation;
        this.usingCondition = false;
    }

    private String bicycleLocation;
    private final int bicycleId;
    private boolean usingCondition;
    private int userId;

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
}
