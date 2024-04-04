package business.design;

import business.entity.Categories;

public interface ICategoriesDesign extends IGenericDesign<Categories,Integer> {
    void changeStatus(Integer id);
}
