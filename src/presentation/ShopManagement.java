package presentation;

import java.util.Scanner;

public class ShopManagement {
    static CategoriesManagement categoriesManagement = new CategoriesManagement();
    static ProductManagement productManagement = new ProductManagement();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" ***************SHOP MENU****************\n" +
                    "1.Quản lý danh mục sản phẩm\n" +
                    "2.Quản lý sản phẩm\n" +
                    "3.Thoát\n");
            System.out.println("mời bạn nhập lụa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    categoriesManagement.displayCategoriesMenu();
                    break;
                case 2:
                    productManagement.displayProductMenu();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}
