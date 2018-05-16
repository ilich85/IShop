package com.ilich.model;

public class Category {

    private int idCategory;
    private String nameCategory;
    private int imageId;
    private int countOfProducts;

    public Category() {
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(int countOfProducts) {
        this.countOfProducts = countOfProducts;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Category category = (Category) object;

        if (idCategory != category.idCategory) return false;
        if (imageId != category.imageId) return false;
        if (countOfProducts != category.countOfProducts) return false;
        return nameCategory.equals(category.nameCategory);
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + nameCategory.hashCode();
        result = 31 * result + imageId;
        result = 31 * result + countOfProducts;
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", nameCategory='" + nameCategory + '\'' +
                ", imageId=" + imageId +
                ", countOfProducts=" + countOfProducts +
                '}';
    }
}
