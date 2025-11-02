import java.util.Scanner;

public   interface Payment {
    void pay(int amount);
}

class PayCash implements Payment {
    public void pay(int amount) {
        doSmt();
        System.out.println("Оплачено наличными: " + amount + "tenge");
        System.out.println("Вам предлагается купон нa слкд. покупку!");
    }

    public void doSmt() {

    }
}
class PayKaspi implements Payment {
    public void pay(int amount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("QR или перевод? ");
        String option  = sc.nextLine();
        if (option.equals("QR")) {
            System.out.println(amount + "  Вам прелдагаются бонусы в размере  1500 тенге");
        }else
            System.out.println("Олата прошла:   " + amount);
    }
}
class PayCredit implements Payment {
    public void pay(int amount) {
        System.out.println("Рассрочка:  " + amount);
    }
}

class PayCard implements Payment {
    public void pay(int amount) {
        System.out.println("Оплачено картой: " + amount + "tenge");
    }
}

