package presentation;

import business.design.IProductDesign;
import business.implement.ProductImplement;

import java.util.Scanner;

public class ProductManagement {
    public static IProductDesign productImplement = new ProductImplement();

    public void displayProductMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" *******************PRODUCT MANAGEMENT*****************\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8. Thoát\n");
            System.out.println("mời bạn nhập lựa chọn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productImplement.addNew();
                    break;
                case 2:
                    productImplement.displayAll();
                    break;
                case 3:
                    productImplement.sortProductByPrice();
                    break;
                case 4:
                    productImplement.edit();
                    break;
                case 5:
                    productImplement.delete();
                    break;
                case 6:
                    productImplement.searchProductByName();
                    break;
                case 7:
                    productImplement.searchProductByRange();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("lựa chọn k hợp lệ mời bạn chọn lại");
            }

        }
    }
}


