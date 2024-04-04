package business.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, List<Product> productList, List<Categories> categoriesList) {
        this.productId = getProductIdInput();
        this.productName = getProductNameInput(productList);
        this.price = getPriceInput(scanner);
        this.description = getDescriptionInput(scanner);
        this.created = getCreatedInput(scanner);
        this.catalogId = selectCategoryId(scanner, categoriesList);
        this.productStatus = getProductStatusInput(scanner);
    }

    private int getProductStatusInput(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm:");
        do {
            try {
                int status = Integer.parseInt(scanner.nextLine());
                if (status == 0 || status == 1 || status == 2) {
                    return status;
                }
            } catch (NumberFormatException e) {
                System.err.println("Trạng thái chỉ nhận giá trị 0,1,2, vui lòng nhập lại");
            }
        } while (true);
    }

    private Date getCreatedInput(Scanner scanner) {
        System.out.println("mời bạn nhập ngày nhập sp");
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                this.created = sdf.parse(scanner.nextLine());
                return this.created;
            } catch (ParseException e) {
                System.err.println("sai định dạng dd/MM/yyyy");
            }
        }
    }

    private String getDescriptionInput(Scanner scanner) {
        // nhập description
        System.out.println("Nhập vào mô tả sản phẩm: ");
        while (true) {
            this.description = scanner.nextLine();
            if (this.description.isBlank()) {
                System.err.println("Vui lòng nhập lại");
            } else {
                return this.description;
            }
        }
    }

    private float getPriceInput(Scanner scanner) {
        // nhập giá
        System.out.println("Nhập giá sản phẩm: ");
        while (true) {
            try {
                this.price = Float.parseFloat(scanner.nextLine());
                if (price > 0) {
                    return price;
                } else {
                    System.err.println("Vui lòng nhập lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("giá phải là số nguyên, vui lòng nhập lại");
            }
        }
    }

    private int selectCategoryId(Scanner scanner, List<Categories> categoriesList) {
        while (true) {

            System.out.println("Chọn danh mục sản phẩm:");
            for (int i = 0; i < categoriesList.size(); i++) {
                if (categoriesList.get(i) != null) {
                    System.out.printf("%d. %s\n", i + 1, categoriesList.get(i).getCatalogName());
                }
            }
            System.out.print("Nhập số thứ tự của danh mục: ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= categoriesList.size() && categoriesList.get(choice - 1) != null) {
                return categoriesList.get(choice - 1).getCatalogId();
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }

        }
    }

    public String getProductIdInput() {
        String regex = "^([CSA])\\d{3}$";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập mã sản phẩm");
            String productIdInput = scanner.nextLine();
            if (productIdInput.matches(regex)) {
                // kt trùng lặp
                if (!productIdInput.equals(this.productId)) {
                    return productIdInput;
                }
            } else {
                System.err.println("không đúng định dạng");
            }
        }
    }

    public String getProductNameInput(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("mời bạn nhập tên sản phẩm");
        while (true) {
            String nameProductInput = scanner.nextLine();
            if (nameProductInput.length() >= 10 && nameProductInput.length() <= 50) {
                // check trùng lặp
                if (productList.stream().noneMatch(t -> t.getProductName().equals(nameProductInput))) {
                    return nameProductInput;
                }
                System.err.println("tên đã tồn tại, vui lòng nhập lại");
            } else {
                System.err.println("tên sản phẩm phải từ 10-> 50 kí tự, vui lòng nhập lại");
            }
        }
    }

    public void displayData(List<Categories> categoriesList) {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %.2f\n", this.productId, this.productName, this.price);
        System.out.printf("Mô tả: %s - Ngày nhập: %s\n", this.description, this.created.toString());
        String catalogName = "";
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() == this.catalogId) {
                catalogName = categoriesList.get(i).getCatalogName();
                break;
            }
        }
        System.out.printf("Danh mục: %s - Trạng thái: %s\n", catalogName,
                this.productStatus == 0 ? "Đang bán" : (this.productStatus == 1 ? "Hết hàng" : "Không bán"));
    }


}
