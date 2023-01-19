package learnprogramming;

public class Product extends Category{
    private double discount_normal;
    private double discount_prime;
    private double discount_elite;
    private double product_id;
    private String product_name;
    private double price;
    private int quantity;
    private String specifications;



    public void setProduct_id(double product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public void setDiscount_normal(double discount_normal) {
        this.discount_normal = discount_normal;
    }

    public void setDiscount_prime(double discount_prime) {
        this.discount_prime = discount_prime;
    }

    public void setDiscount_elite(double discount_elite) {
        this.discount_elite = discount_elite;
    }

    public double getDiscount_normal() {
        return discount_normal;
    }

    public double getDiscount_prime() {
        return discount_prime;
    }

    public double getDiscount_elite() {
        return discount_elite;
    }

    public double getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSpecifications() {
        return specifications;
    }

    public Product(String category_name, int category_id, double product_id, String product_name, double price, int quantity, String specifications) {
        super(category_name, category_id);
        this.discount_normal=0;
        this.discount_elite=0;
        this.discount_prime=0;
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.quantity=quantity;
        this.specifications=specifications;
    }

    public void printProduct(){
        System.out.println("Product name: "+this.product_name);
        System.out.println("Product price per unit: "+this.price);
        System.out.println("Product Id: "+this.product_id);
        System.out.println("Product Specifications: "+this.specifications);
        System.out.println("Quantity: "+this.quantity);
    }
    public Product(Product product,int quantity){
        super(product.getCategory_name(), product.getCategory_id());
        this.discount_normal=product.getDiscount_normal();
        this.discount_elite=product.getDiscount_elite();
        this.discount_prime=product.getDiscount_prime();
        this.product_id = product.getProduct_id();
        this.product_name = product.getProduct_name();
        this.price = product.getPrice();
        this.quantity=quantity;
        this.specifications=product.getSpecifications();
    }

}
