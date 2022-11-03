package overriding;

import java.util.Arrays;

public class Main{
    public static void main(String [] args){
        Train train = new Train();
        KTX ktx = new KTX();
        Train ktx1 = new KTX();
        System.out.println(train.speed);
        System.out.println(train.getPrice());

        train.selectOption();
        System.out.println("============================");
        ktx.selectOption();

    }
}

class Train {
    private int price = 10000;
    int speed = 150;
    private String [] food = {"호두과자", "바나나 우유", "과자"};

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getSpeed() { return speed;    }
    public void setSpeed(int speed) { this.speed = speed;    }

    public void selectOption(){
        System.out.println(Arrays.toString(food)+ " 음식 옵션이 있습니다.");
    }

    public void introduce(){
        System.out.println("ktx는 train보다 많은 서비스를 제공합니다.");
    }
}


class KTX extends Train{
    private int price = 20000;
    private int speed = 300;
    private String [] food = {"호두과자", "바나나 우유", "과자"};
    private String [] entertain = {"TV","Computer","Nintendo"};

    public void selectOption(){
        super.selectOption();
        System.out.println(Arrays.toString(entertain) + " 놀이 옵션이 있습니다.");
    }
}

