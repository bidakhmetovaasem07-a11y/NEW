
import java.util.Scanner;

public class sentence {
    public void info() {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        boolean isAdmin = false;

        System.out.println("Вы админ? (yes/no): ");
        if (sc.nextLine().

                equalsIgnoreCase("yes")) {
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

        if (cafeChoice.equalsIgnoreCase("Starbucks")) {
            cafe = new StarBucks();
        } else cafe = new Tami();


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
                int choice = sc.nextInt();
                sc.nextLine();


                if (choice == 1) {
                    System.out.print("Название: ");
                    String name = sc.nextLine();
                    System.out.print("Цена: ");
                    int price = sc.nextInt();
                    sc.nextLine();
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
            int totala = 0;
            totala += cafe.calculateOrder(item, qty);

            System.out.println("\nDo you want to add more? (yes/no):  ");
            String ans = sc.nextLine().trim().toLowerCase();

            if (ans.equals("no")) {
                int total = 0;
                total += cafe.calculateOrder(item, qty);

                int sumtotal = total + totala;
                System.out.println("Итого: " + sumtotal + "tenge");
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
            } else
                System.out.println("error");
        }
        System.out.println("=======ЧЕК=======");
    }
}