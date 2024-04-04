package presentation;

import business.design.ICategoriesDesign;
import business.implement.CategoriesImplement;

import java.util.Scanner;

public class CategoriesManagement {
    public static ICategoriesDesign categoriesImplement = new CategoriesImplement();

    public void displayCategoriesMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("************CATEGORIES MENU************\n" +
                    "1. Nhập thông tin các danh mục\n" +
                    "2. Hiển thị thông tin các danh mục\n" +
                    "3. Cập nhật thông tin danh mục\n" +
                    "4. Xóa danh mục\n" +
                    "5. Cập nhật trạng thái danh mục\n" +
                    "6. Thoát\n");
            System.out.println("mời bạn nhập lựa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    categoriesImplement.addNew();
                    break;
                case 2:
                    categoriesImplement.displayAll();
                    break;
                case 3:
                    categoriesImplement.edit();
                    break;
                case 4:
                    categoriesImplement.delete();
                    break;
                case 5:
                    System.out.println("mời bạn nhập id cần đổi status");
                    int id = Integer.parseInt(scanner.nextLine());
                    categoriesImplement.changeStatus(id);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("lựa chon k hợp lệ mời bạn chọn lại");
            }
        }
    }
}
