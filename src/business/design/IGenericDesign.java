package business.design;

import java.io.IOException;

public interface IGenericDesign<T, E> {
    void addNew();

    void displayAll();

    void edit();

    void delete();

    T findById(E id);
    void saveToFile() ; // Thêm phương thức lưu vào file
    void loadFromFile() ; // Thêm phương thức đọc từ file
    void createFile() throws IOException; // Thêm phương thức tạo file

}
