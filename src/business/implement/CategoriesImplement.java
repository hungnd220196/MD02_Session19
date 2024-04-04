package business.implement;

import business.design.ICategoriesDesign;
import business.entity.Categories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static business.implement.ProductImplement.productList;

public class CategoriesImplement implements ICategoriesDesign, Serializable {

    private static final String FILE_PATH = "categories.txt";
    static List<Categories> categoriesList = new ArrayList<>();

    public CategoriesImplement() {
        loadFromFile();
    }

    @Override
    public void addNew() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời bạn nhập số danh mục muốn thêm");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Categories categories = new Categories();
            categories.inputData(scanner, categoriesList);
            categoriesList.add(categories);
            saveToFile();
        }

    }

    @Override
    public void displayAll() {
        if (categoriesList.isEmpty()) {
            System.out.println("danh sách sản phẩm rỗng");
            return;
        }
        for (Categories categories : categoriesList) {
            categories.displayData();
        }

    }

    @Override
    public void edit() {
        loadFromFile();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập id danh mục cần cập nhật thông tin");
        try {

            int idUpdate = Integer.parseInt(scanner.nextLine());
            // thông tin trước khi update
            System.out.println("thông tin cũ là: ");
            findById(idUpdate).displayData();
            //cho người dùng nhập update
            System.out.println("mời bạn nhập thông tin cần update");
            findById(idUpdate).inputData(scanner, categoriesList);
            saveToFile();
        } catch (NumberFormatException e) {
            System.err.println("id là số nguyên, vui lòng nhập lại");
        } catch (NullPointerException e) {
            System.err.println("id không tồn tại");
        }
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời bạn nhập id danh mục cần xóa");
        int idDelete = Integer.parseInt(scanner.nextLine());
        //kiểm tra có trong mảng không

        if (findById(idDelete) != null) {
            // kiểm tra xem có sp hay không
            boolean isExist = false;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getCatalogId() == idDelete) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("Danh mục đang chứa sản phẩm không thể xóa");

            } else {
                //thực hiện xóa
                for (int i = 0; i < categoriesList.size(); i++) {
                    if (categoriesList.get(i).getCatalogId() == idDelete) {
                        categoriesList.remove(i);
                        saveToFile();
                        System.out.println("đã xóa thành công danh muc có id " + idDelete);
                    }
                }
            }
        } else {
            System.err.println("id không tồn tại");
        }
    }

    @Override
    public Categories findById(Integer id) {
        for (Categories categories : categoriesList) {
            if (categories.getCatalogId() == id) {
                return categories;
            }
        }
        return null;
    }

    @Override
    public void saveToFile() {
        //Các phương thức lưu và dọc dữ liệu từ tệp
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            objectOutputStream.writeObject(categoriesList);
            System.out.println("Dữ liệu đã đc luuw thành công");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            categoriesList = (List<Categories>) objectInputStream.readObject();
            System.out.println("Dữ liệu đã được đọc từ file thành công.");
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file. Tạo file mới.");
            categoriesList = new ArrayList<>();
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
            System.out.println("FIle đuược tạo ok");
        } else {
            System.out.println("Đã tồn tại");
        }
    }


    @Override
    public void changeStatus(Integer id) {
        for (Categories categories : categoriesList) {
            if (categories.getCatalogId() == id) {
                categories.setCatalogStatus(!categories.getCatalogStatus());
                System.out.println("Trạng thái đã đổi thành công");
            }
        }

    }
}
