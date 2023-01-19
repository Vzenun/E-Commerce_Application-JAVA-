package learnprogramming;

public class Category {
    private String category_name;
    private int category_id;

    public Category(String category_name,int category_id) {
        this.category_name = category_name;
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public int getCategory_id() {
        return category_id;
    }
    public void printCategory(){
        System.out.println("Category name: "+this.category_name);
        System.out.println("Category Id: "+this.category_id);
    }
}
