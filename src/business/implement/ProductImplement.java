package business.implement;

import business.design.IProductDesign;
import business.entity.Categories;
import business.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static business.implement.CategoriesImplement.categoriesList;

public class ProductImplement implements IProductDesign, Serializable {
    private static final String FILE_PATH = "product.txt";
    static List<Product> productList = new ArrayList<>();

    public ProductImplement() {
        loadFromFile();
    }

    @Override
    public void addNew() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời bạn nhập số sản phẩm muốn thêm");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Product product = new Product();
            product.inputData(scanner, productList, categoriesList);
            productList.add(product);
            saveToFile();
        }

    }

    @Override
    public void displayAll() {
        if (productList.isEmpty()) {
            System.out.println("danh sách sản phẩm rỗng");
            return;
        }
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).displayData(categoriesList);
        }

    }

    @Override
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập id danh mục cần cập nhật thông tin");
        try {
            String idUpdate = scanner.nextLine();
            // thông tin trước khi update
            System.out.println("thông tin cũ là: ");
            findById(idUpdate).displayData(categoriesList);
            //cho người dùng nhập update
            System.out.println("mời bạn nhập thông tin cần update");
            findById(idUpdate).inputData(scanner, productList, categoriesList);
        } catch (NumberFormatException e) {
            System.err.println("id là số nguyên, vui lòng nhập lại");
        } catch (NullPointerException e) {
            System.err.println("id không tồn tại");
        }

    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời bạn nhập id sẩn phẩm cần xóa");
        String idDelete = scanner.nextLine();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(idDelete)) {
                productList.remove(i);
                break;
            }
        }

    }

    @Override
    public Product findById(String id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(id)) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(productList);
            System.out.println("Dữ liệu đã được lưu vào file thành công.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Đã xảy ra lỗi khi lưu dữ liệu vào file: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            productList = (List<Product>) objectInputStream.readObject();
            System.out.println("Dữ liệu đã được đọc từ file thành công.");
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file. Tạo file mới.");
            productList = new ArrayList<>();
            saveToFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Đã xảy ra lỗi khi đọc dữ liệu từ file: " + e.getMessage());
        }
    }

    @Override
    public void createFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("file dã được tạo ");
        } else {
            System.out.println(" dã tồn tại");
        }

    }


    @Override
    public void sortProductByPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập giá thấp nhất:");
        float minPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập giá cao nhất:");
        float maxPrice = Float.parseFloat(scanner.nextLine());
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm trong khoảng giá này.");
        } else {
            for (Product product : result) {
                product.displayData(categoriesList);
            }
        }

    }

    @Override
    public void searchProductByName() {
        productList.sort(Comparator.comparing(Product::getPrice));
        System.out.println("Sản phẩm đã được sắp xep theo giá");

    }

    @Override
    public void searchProductByRange() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm muốn tìm:");
        String name = scanner.nextLine();
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm.");
        } else {
            for (Product product : result) {
                product.displayData(categoriesList);
            }
        }

    }
}
