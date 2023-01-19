package learnprogramming;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    public static HashMap<Integer, Customer> Customer_record = new HashMap<>();
    public static HashMap<Double, ArrayList<Double>> Deals = new HashMap<>();
    public static HashMap<Category, ArrayList<Product>> Category_product = new HashMap<>();
    public static int customer_num=0;

    public static Boolean customer_Search(String name,String password){
        for (HashMap.Entry<Integer, Customer> entry : Customer_record.entrySet()) {
            if(password.equals(entry.getValue().getPassword()) && name.equals(entry.getValue().getUsername())){
                return true;
            }
        }
        return false;
    }
    //public static HashMap<Category, ArrayList<Product> cart = new ArrayList<>()> Customer_record = new HashMap<>();
    public static Boolean category_Search(int id){
        for (HashMap.Entry<Category, ArrayList<Product>> entry : Category_product.entrySet()) {
            if(entry.getKey().getCategory_id()==id){
                return true;
            }
        }
        return false;
    }
    public static Boolean product_search(Category category,double id){
        for (int i = 0; i < Category_product.get(category).size(); i++){
            if(Category_product.get(category).get(i).getProduct_id()==id){
                return true;
            }
        }
        return false;
    }

    public static Boolean prod_search(double id){
        for (HashMap.Entry<Category, ArrayList<Product>> entry : Category_product.entrySet()) {
            for (int i = 0; i < Category_product.get(entry.getKey()).size(); i++){
                if(Category_product.get(entry.getKey()).get(i).getProduct_id()==id){
                    System.out.print("Enter discount for Elite customers respectively (in % terms): ");
                    double p1=Input.input_id();
                    System.out.print("Enter discount for Prime customers respectively (in % terms): ");
                    double p2=Input.input_id();
                    System.out.print("Enter discount for Normal customers respectively (in % terms): ");
                    double p3=Input.input_id();
                    Category_product.get(entry.getKey()).get(i).setDiscount_normal(p3);
                    Category_product.get(entry.getKey()).get(i).setDiscount_prime(p2);
                    Category_product.get(entry.getKey()).get(i).setDiscount_elite(p1);
                    return true;
                }
            }
        }
        return false;
    }
    public static Boolean prod_sea(double id){
        for (HashMap.Entry<Category, ArrayList<Product>> entry : Category_product.entrySet()) {
            for (int i = 0; i < Category_product.get(entry.getKey()).size(); i++){
                if(Category_product.get(entry.getKey()).get(i).getProduct_id()==id){
                    return true;
                }
            }
        }
        return false;
    }

}
