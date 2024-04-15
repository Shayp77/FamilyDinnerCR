package correctness;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private ArrayList<MenuItem> menuList = new ArrayList<>();
    private Scanner scan= new Scanner(System.in);
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 25;
    private static final int MIN_PRICE = 10000;
    private static final int MAX_PRICE = 1000000;
    private static final int DISCOUNT_LOW = 10;
    private static final int DISCOUNT_MEDIUM = 25;
    private static final int DISCOUNT_HIGH = 50;
    public static void main(String aargs[]) {
        new Main();
    }

    public Main(){
        int choice=0;
        do {
            displayMenu();
            choice=scan.nextInt();
            scan.nextLine();
            handleChoice(choice);
        } while(choice!=6);
        System.out.println("Exiting program");
    }

    private void displayMenu() {
        System.out.println("Family Restaurant");
        System.out.println("===============================");
        System.out.println("1. Add Regular Menu");
        System.out.println("2. Add Special Menu");
        System.out.println("3. Show All menu");
        System.out.println("4. Delete Regular Menu");
        System.out.println("5. Delete Special Menu");
        System.out.println("6. Exit");
        System.out.print("Choice [1-6]:");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addMenu("R", "Regular");
                break;
            case 2:
                addMenu("S", "Special");
                break;
            case 3:
                view();
                break;
            case 4:
            	deleteMenu(menuList, "R");
                break;
            case 5:
                deleteMenu(menuList, "S");
                break;
            default:
                System.out.println("Please input between 1-5 and 6 for exit");
                break;
        }
    }

    private void addMenu(String codePrefix, String menuType) {
        System.out.println("Add " + menuType + " Menu");
        System.out.println("===============================");
        String code = getCode(codePrefix);
        String name = getName();
        int price = getPrice();
        if (menuType.equals("Special")) {
            int discount = getDiscount();
            menuList.add(new Special(code, name, price, discount));
        } else {
            menuList.add(new Normal(code, name, price));
        }
        System.out.println("Add Success");
    }

    private String getCode(String prefix) {
        String code;
        do {
            System.out.print("Input Menu Code [" + prefix + "...]\t\t:");
            code = scan.nextLine();
            if (!Pattern.matches(prefix + "\\d{3}", code)) {
                System.out.println("Format kode salah. Harus diawali dengan " + prefix + " diikuti oleh 3 digit.");
            } else if (isCodeExists(code)) {
                System.out.println("Kode sudah digunakan. Silakan masukkan kode yang berbeda.");
            }
        } while (!Pattern.matches(prefix + "\\d{3}", code) || isCodeExists(code));
        return code;
    }

    private boolean isCodeExists(String code) {
        for (MenuItem item : menuList) {
            if (item.getKode().equals(code)) {
                return true;
            }
        }
        return false;
    }
    private String getName() {
        String name;
        do {
            System.out.print("Input menu name[5-25] \t\t:");
            name = scan.nextLine();
        } while (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH);
        return name;
    }
    private int getPrice() {
        int price;
        do {
            System.out.print("Input menu price[10000-100000]\t:");
            price = scan.nextInt();
            scan.nextLine();
        } while (price < MIN_PRICE || price > MAX_PRICE);
        return price;
    }
    private int getDiscount() {
        int discount;
        do {
            System.out.print("Input menu discount[10/25/50]\t:");
            discount = scan.nextInt();
            scan.nextLine();
        } while (discount != DISCOUNT_LOW && discount != DISCOUNT_MEDIUM && discount != DISCOUNT_HIGH);
        return discount;
    }
    public void deleteMenu(ArrayList<MenuItem> list, String codePrefix) {
        String kode;
        int idx = -1;
        System.out.println("Delete " + (codePrefix.equals("R") ? "Regular" : "Special") + " Menu");
        System.out.println("=========================");
        System.out.print("input menu code[" + codePrefix + "...]\t:");
        kode = scan.nextLine();
        if(Pattern.matches(codePrefix + "\\d{3}", kode)) {
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i).getKode().equals(kode)) {
                    idx = i;
                }
            }
        } else {
            System.out.println("Code is wrong");
            return;
        }
        if(idx == -1) {
            System.out.println("Code is wrong");
            return;
        } else {
            list.remove(idx);
            System.out.println("Delete Success");
            return;
        }
    }
    public void view() {
        if(menuList.size() == 0) {
            System.out.println("No items in the menu.");
        } else {
            System.out.println("Regular Menu");
            System.out.println("=================================");
            System.out.println("No.| Kode |\tNama\t| harga |");
            System.out.println("=================================");
            for(int i = 0; i < menuList.size(); i++) {
                MenuItem item = menuList.get(i);
                if(item instanceof Normal) {
                    System.out.println(i+1 + "   " + item.getKode() + " \t" + item.getNama() + "\t  " + item.getHarga());
                }
            }
            System.out.println();
            System.out.println("Special Menu");
            System.out.println("=========================================");
            System.out.println("No.| Kode |\tNama\t| harga | Diskon |");
            System.out.println("=========================================");
            for(int i = 0; i < menuList.size(); i++) {
                MenuItem item = menuList.get(i);
                if(item instanceof Special) {
                    System.out.println(i+1 + "   " + item.getKode() + " \t" + item.getNama() + "\t  " + item.getHarga() + "  " + ((Special)item).getDiskon() + "%");
                }
            }
        }
    }

    

}
