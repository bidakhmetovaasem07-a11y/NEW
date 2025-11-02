import java.util.ArrayList;
import java.util.Scanner;

//
//abstract class Establishment {
//
//    protected String name;
//    protected ArrayList<MenuItem> menu = new ArrayList<>();
//    Scanner sc = new Scanner(System.in);
//    protected int discount = 0;
//
//    public Establishment(String name) {
//        this.name = name;
//    }
//
//    public void getDiscount(int discount) {
//        this.discount = discount;
//        System.out.println("Скидка установлена: " + discount + "%");
//    }
//
//    public void welcome() {
//        System.out.println("Welcome to " + name + "!");
//    }
//
//    public void showMenu() {
//        System.out.println("---- MENU ----");
//
//       //while (true) {
//            for (int i = 0; i < menu.size(); i++) {
//                MenuItem item = menu.get(i);
//                System.out.println((i + 1) + ". " + item.getName() + " - " + item.getPrice() + "tenge");
//        }
//    }
//
//    public int calculateOrder(int num, int quantity) {
//        if (num < 1 || num > menu.size()) {
//            System.out.println("Нет такого блюда!");
//            return 0;
//        }
//        int price = menu.get(num - 1).getPrice() * quantity;
//
//        if (discount > 0) {
//            price = price - (price * discount / 100);
//        }
//        return  price;
//    }
//
//
//
//    public void addMenuItem(String name, int price) {
//        menu.add(new MenuItem(name, price));
//        System.out.println("Добавлено: " + name);
//    }
//
//    public void removeMenuItem(String name) {
//        for (int i = 0; i < menu.size(); i++) {
//            if (menu.get(i).getName().equalsIgnoreCase(name)) {
//                System.out.println("Удалено: " + name);
//                menu.remove(i);
//                return;
//            }
//        }
//        System.out.println("Такого блюда нет.");
//    }
//}
class StarBucks extends Establishment {

    public StarBucks() {
        super("Starbucks");
        menu.add(new MenuItem("Latte", 1800));
        menu.add(new MenuItem("Americano", 1700));
        menu.add(new MenuItem("Tea", 800));
        menu.add(new MenuItem("Sandwich", 1000));
    }
}

class Tami extends Establishment {

    public Tami() {
        super("Tami");
        menu.add(new MenuItem("Lagman", 3000));
        menu.add(new MenuItem("Pizza", 2000));
        menu.add(new MenuItem("Tea", 700));
        menu.add(new MenuItem("Manty", 1600));
    }
}
interface Payment {
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
class Admin {
    private final String password = "123456789";

    public boolean login(String input) {
        return password.equals(input);
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        boolean isAdmin = false;

        System.out.println("Вы админ? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Введите пароль: ");
            if (admin.login(sc.nextLine())) {
                isAdmin = true;
                System.out.println("Админ режим активирован ");
            } else {
                System.out.println("Неверный пароль ");

            }
        }

        System.out.println("Выберите кафе: Starbucks / Tami");
        String cafeChoice = sc.nextLine();
        Establishment cafe;

        if (cafeChoice.equalsIgnoreCase("Starbucks")) cafe = new StarBucks();
        else cafe = new Tami();

        cafe.welcome();
        cafe.showMenu();


        if (isAdmin) {
            while (true) {
                System.out.println("\nАдмин меню:");
                System.out.println("1. Добавить блюдо");
                System.out.println("2. Удалить блюдо");
                System.out.println("3. Показать меню");
                System.out.println("4. Установить скидку");
                System.out.println("5. Выход");
                int choice = sc.nextInt(); sc.nextLine();



                if (choice == 1) {
                    System.out.print("Название: ");
                    String name = sc.nextLine();
                    System.out.print("Цена: ");
                    int price = sc.nextInt(); sc.nextLine();
                    cafe.addMenuItem(name, price);
                } else if (choice == 2) {
                    System.out.print("Название блюда для удаления: ");
                    cafe.removeMenuItem(sc.nextLine());
                } else if (choice == 3) {
                    cafe.showMenu();
                } else if (choice == 4) {
                    System.out.println("Введите скидку:  ");
                    int discount = sc.nextInt();
                    sc.nextLine();
                    cafe.getDiscount(discount);
                } else break;
            }
        }

      while (true) {
          System.out.println("\nВведите номер блюда:");
          int item = sc.nextInt();
          System.out.println("Количество:");
          int qty = sc.nextInt();
          String line = sc.nextLine();
          cafe.showMenu();
         int  totala = 0;
          totala  += cafe.calculateOrder(item, qty);

         System.out.println("\nDo you want to add more? (yes/no):  ");
           String ans = sc.nextLine().trim().toLowerCase();

          if (ans.equals("no")) {
              int total = 0;
              total += cafe.calculateOrder(item, qty);

              int sumtotal = total + totala;
              System.out.println("Итого: " + sumtotal  + "tenge");
              System.out.println("Метод оплаты: 1. Cash 2. Рассрочка 3. Kaspi 4. Card");
              int pay = sc.nextInt();
              Payment payment;
              if (pay == 1)
                  payment = new PayCash();
              else if (pay == 2) {
                  payment = new PayCredit();
              } else if (pay == 3) {
                  payment = new PayKaspi();
              } else
                  payment = new PayCard();
              payment.pay(sumtotal);
              break;
          }
          if (ans.equals("yes")) {
              int total = 0;
              total += cafe.calculateOrder(item, qty);
              continue;
          }
          else
              System.out.println("error");
      }
        System.out.println("=======ЧЕК=======");
    }

    public static void pay(Payment payment) {

    }
}
