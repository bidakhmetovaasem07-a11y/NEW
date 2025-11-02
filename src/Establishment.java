import java.util.ArrayList;
import java.util.Scanner;

abstract class Establishment {

    protected String name;
    protected ArrayList<MenuItem> menu = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    protected int discount = 0;

    public Establishment(String name) {
        this.name = name;
    }

    public void getDiscount(int discount) {
        this.discount = discount;
        System.out.println("Скидка установлена: " + discount + "%");
    }

    public void welcome() {
        System.out.println("Welcome to " + name + "!");
    }

    public void showMenu() {
        System.out.println("---- MENU ----");

        //while (true) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getPrice() + "tenge");
        }
    }

    public int calculateOrder(int num, int quantity) {
        if (num < 1 || num > menu.size()) {
            System.out.println("Нет такого блюда!");
            return 0;
        }
        int price = menu.get(num - 1).getPrice() * quantity;

        if (discount > 0) {
            price = price - (price * discount / 100);
        }
        return  price;
    }



    public void addMenuItem(String name, int price) {
        menu.add(new MenuItem(name, price));
        System.out.println("Добавлено: " + name);
    }

    public void removeMenuItem(String name) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println("Удалено: " + name);
                menu.remove(i);
                return;
            }
        }
        System.out.println("Такого блюда нет.");
    }
}