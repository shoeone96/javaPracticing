package objectiveOrientedProgramming.polymorphism;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class sellingPhone {
    public static void main(String [] args){
        Buyer buyer = new Buyer();

        IPhone iPhone = new IPhone(IphoneModel.iPhone13);
        Galaxy galaxy = new Galaxy(GalaxyModel.galaxy22);
        Xiaomi xiaomi = new Xiaomi(Xiaomimodel.xiaomi12);

        buyer.pick(iPhone);
        buyer.pick(galaxy);
        buyer.pick(xiaomi);
        buyer.summary();

        buyer.delete(xiaomi);
        buyer.summary();

        buyer.buy();
        buyer.summary();
        buyer.checkingMoney();

        buyer.pick(iPhone);
        buyer.pick(galaxy);
        buyer.pick(xiaomi);
        buyer.buy();
        buyer.checkingMoney();

        buyer.pick(iPhone);
        buyer.pick(galaxy);
        buyer.pick(xiaomi);
        buyer.buy();
        buyer.checkingMoney();
    }
}

class Buyer {
    private int money = 8000000;
    private ArrayList<Phone> cart = new ArrayList<>();
    DecimalFormat decimalFormat = new DecimalFormat("###,###");
    private int totalPrice = 0;


    public void pick(Phone phone) {
        cart.add(phone);
        totalPrice += phone.getPrice();
    }

    public void delete(Phone phone){
        cart.remove(phone);
        totalPrice -= phone.getPrice();
    }

    public void summary(){
        if (cart.size() == 0) System.out.println("장바구니에 담은 핸드폰이 없습니다.");
        else
        System.out.println("현재 총 " + cart.size() + "개의 핸드폰을 선택 하였습니다");
        System.out.println("총 금액은 " + decimalFormat.format(totalPrice) + "입니다");
    }

    public void buy(){
        if(money >= totalPrice){
            System.out.println("구매가 완료되었습니다.");
            money -= totalPrice;
            totalPrice = 0;
            cart.clear();
        }else System.out.println("잔액이 부족합니다.");
    }

    public void checkingMoney(){
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        System.out.println("남은 잔액은 " + decimalFormat.format(money) + "입니다.");
    }
}

class Phone {
    public Phone() {
    }

    public Phone(String model) {
        this.model = model;
    }

    private int price;
    private String model;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

enum IphoneModel {
    iPhone13Pro(1), iPhone13(2), iPhone13proMax(4);

    private final int value;

    IphoneModel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class IPhone extends Phone {
    public IPhone(IphoneModel iphoneModel) {
        super.setModel(iphoneModel.name());
        switch (iphoneModel.getValue()) {
            case 1 -> super.setPrice(1300000);
            case 2 -> super.setPrice(1200000);
            case 3 -> super.setPrice(1400000);
        }

    }

}

enum GalaxyModel {
    galaxy22(4), galaxyflip(5), galaxyfold(6);

    private final int value;

    GalaxyModel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class Galaxy extends Phone {
    public Galaxy(GalaxyModel galaxyModel){
        super.setModel(galaxyModel.name());
        switch (galaxyModel.getValue()){
            case 4 -> super.setPrice(1200000);
            case 5 -> super.setPrice(1300000);
            case 6 -> super.setPrice(1400000);
        }
    }

}

enum Xiaomimodel {
    xiaomi12(7), hongminote10(8);

    private final int value;
    Xiaomimodel(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }

}

class Xiaomi extends Phone {
    public Xiaomi(Xiaomimodel xiaomimodel){
        super.setModel(xiaomimodel.name());
        switch (xiaomimodel.getValue()){
            case 7 -> super.setPrice(1100000);
            case 8 -> super.setPrice(1050000);
        }
    }
}
