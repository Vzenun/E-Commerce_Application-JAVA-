package learnprogramming;

import java.util.ArrayList;

public class Admin {
    private String username;
    private String Password;

    public Admin(String username, String password) {
        this.username = username;
        Password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Password;
    }
    public void add_category(){
        System.out.print("Add category ID: ");
        int an=Input.input_sim();
        if(!Store.category_Search(an)){
            System.out.print("Add name of the category: ");
            String name=Input.input_string();

            Category category=new Category(name,an);

            ArrayList<Product> a=new ArrayList<>();

            System.out.println("Add a product: ");
            System.out.print("Product name: ");
            String nam=Input.input_string();
            System.out.print("Product ID: ");
            double id=Input.input_id();
            System.out.print("Price: ");
            double price=Input.input_id();
            System.out.print("Quantity: ");
            int quantity=Input.input_sim();
            System.out.print("Specifications(In one line space separated): ");
            String specifications=Input.input_string();

            Product product=new Product(name,an,id,nam,price,quantity,specifications);

            a.add(product);
            Store.Category_product.put(category,a);
        }
        else{
            System.out.println("Dear Admin, the category ID is already used!!! Please set a different and a unique category ID");
            this.add_category();
        }
    }
    public void add_product(Category category){
        System.out.println("Add a product: ");
        System.out.print("Product name: ");
        String nam=Input.input_string();
        System.out.print("Product ID: ");
        double id=Input.input_id();
        System.out.print("Price: ");
        double price=Input.input_id();
        System.out.print("Quantity: ");
        int quantity=Input.input_sim();
        System.out.print("Specifications(In one line space separated): ");
        String specifications=Input.input_string();

        Product product=new Product(category.getCategory_name(),category.getCategory_id(),id,nam,price,quantity,specifications);
        Store.Category_product.get(category).add(product);
    }

    public void delete_category(Category category){
        ArrayList<Product> b=Store.Category_product.remove(category);
    }
    public void delete_product(Category category){
        System.out.print("Enter product ID: ");
        double id=Input.input_id();
        if(Store.product_search(category,id)){
            for (int i = 0; i < Store.Category_product.get(category).size(); i++){
                if(Store.Category_product.get(category).get(i).getProduct_id()==id){
                    del_prod(Store.Category_product.get(category).get(i),category);
                }
            }
            if(Store.Category_product.get(category).size()==0){
                System.out.println("As the list of product belonging to the specific id is 0 Now u have 2 options:");
                System.out.println("1) Add product.");
                System.out.println("2) delete category");
                int n=Input.input_num();
                if(n==1){
                    add_product(category);
                }
                else if(n==2){
                    delete_category(category);
                }
            }
        }
        else{
            System.out.println("No such product is available with the given id.");
        }
    }

    public void del_prod(Product product,Category category){
        Store.Category_product.get(category).remove(product);
    }
    public void add_give_away_deal(){
        System.out.println("Dear Admin give the Product IDs you want to combine and giveaway a deal for");
        System.out.print("Add deal ID: ");
        double it=Input.input_id();
        System.out.print("Enter the First Product ID: ");
        double a=Input.input_id();
        System.out.print("Enter the Second Product ID: ");
        double b=Input.input_id();
        ArrayList<Double> arr=new ArrayList<>();
        arr.add(a);
        arr.add(b);
        System.out.print("Combined give away deal for elite customers: ");
        double p1=Input.input_id();
        System.out.print("Combined give away deal for prime customers: ");
        double p2=Input.input_id();
        System.out.print("Combined give away deal for normal customers: ");
        double p3=Input.input_id();
        arr.add(p1);
        arr.add(p2);
        arr.add(p3);
        Store.Deals.put(it,arr);
    }
}
