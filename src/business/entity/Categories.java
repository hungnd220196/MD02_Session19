package business.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Categories implements Serializable {
    private int catalogId;
    static int nextId = 1;
    private String catalogName;
    private String descriptions;
    private Boolean catalogStatus;

    public Categories() {
        catalogId = nextId++;
    }

    public Categories(int catalogId, String catalogName, String descriptions, Boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(Boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, List<Categories> categoriesList) {
        this.catalogName = getInputCatalogName(categoriesList);
        this.descriptions = getDescriptionInput(scanner);
        this.catalogStatus = getStatusInput();
    }

    private String getDescriptionInput(Scanner scanner) {
        // nhập description
        System.out.println("Nhập vào mô tả sản phẩm: ");
        while (true) {
            this.descriptions = scanner.nextLine();
            if (this.descriptions.isBlank()) {
                System.err.println("Vui lòng nhập lại");
            } else {
                return this.descriptions;
            }
        }
    }

    public boolean getStatusInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời bạn nhập trạng thái");
        do {
            String statusInput = scanner.nextLine();
            if (statusInput.equals("true") || statusInput.equals("false")) {
                return Boolean.parseBoolean(statusInput);
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận true hoặc false");
            }
        } while (true);

    }

    public String getInputCatalogName(List<Categories> categoriesList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời bạn nhập tên danh mục");
        while (true) {
            String catalogNameInput = scanner.nextLine();
            //check rỗng
            if (!catalogNameInput.isBlank() && catalogNameInput.length() <= 50) {
                // kiểm tra trùng lặp
                if (categoriesList.stream().noneMatch(t -> t.getCatalogName().equals(catalogNameInput))) {
                    return catalogNameInput;
                }
                System.err.println("tên đã tồn tại, vui lòng nhập lại");
            } else {
                System.err.println("tên danh muc không được để trống và tối đa 50 kí tự");
            }
        }
    }

    public void displayData() {
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Mô tả: %s - Trạng thái: %s\n ", this.catalogId, this.catalogName, this.descriptions, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
