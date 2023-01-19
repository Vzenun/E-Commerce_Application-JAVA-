package learnprogramming;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void admin_priviliges(Admin admin){
        System.out.println("Welcome " + admin.getUsername()+"!!!");
        System.out.println("Please choose any one of the following actions:");
        System.out.println("   1) Add category");
        System.out.println("   2) Delete category");
        System.out.println("   3) Add Product");
        System.out.println("   4) Delete Product");
        System.out.println("   5) Set Discount on Product");
        System.out.println("   6) Add giveaway deal");
        System.out.println("   7) Back");
        int n=Input.input_num();
        if(n==1){ //adding a category
            admin.add_category();
            admin_priviliges(admin);
        }
        else if(n==2){ //deleting a category
            System.out.print("Enter category ID: ");
            int an=Input.input_sim();
            if(Store.category_Search(an)){
                for (HashMap.Entry<Category, ArrayList<Product>> entry : Store.Category_product.entrySet()) {
                    if(entry.getKey().getCategory_id()==an){
                        System.out.print("");
                        admin.delete_category(entry.getKey());
                    }
                }
            }
            else{
                System.out.println("No such category available with this id.");
            }
            admin_priviliges(admin);
        }
        else if(n==3){ //adding a product
            System.out.print("Enter category ID: ");
            int an=Input.input_sim();
            if(Store.category_Search(an)){
                for (HashMap.Entry<Category, ArrayList<Product>> entry : Store.Category_product.entrySet()) {
                    if(entry.getKey().getCategory_id()==an){
                        admin.add_product(entry.getKey());
                    }
                }
                admin_priviliges(admin);
            }
            else{
                System.out.println("No such category available with this id.Try again or add a new category first");
                admin_priviliges(admin);
            }
        }
        else if(n==4){ //deleting a product
            System.out.print("Enter category ID: ");
            int an=Input.input_sim();
            if(Store.category_Search(an)){
                for (HashMap.Entry<Category, ArrayList<Product>> entry : Store.Category_product.entrySet()) {
                    if(entry.getKey().getCategory_id()==an){
                        System.out.print("");
                        admin.delete_product(entry.getKey());
                    }
                }
            }
            else{
                System.out.println("No such category available with this id.Try again");
            }
            admin_priviliges(admin);
        }
        else if(n==5){ // Set Discount on Product
            System.out.println("Dear Admin give the Product ID you want to add discount for");
            System.out.print("Enter the Product ID: ");
            double a=Input.input_id();
            if(!Store.prod_search(a)){
                System.out.println("No such product available with the given id.Try again.");
            }
            admin_priviliges(admin);
        }
        else if(n==6){ // Add giveaway deals
            admin.add_give_away_deal();
            admin_priviliges(admin);
        }
        if(n==7){ //Back
            welcome();
        }
    }

    public static void customer_sign_up(){
        System.out.print("Enter name: ");
        String name=Input.input_string();
        System.out.print("Enter password: ");
        String password=Input.input_string();
        if(!Store.customer_Search(name,password)){
            Customer customer1=new Customer(name,password);
            Store.customer_num++;
            Store.Customer_record.put(Store.customer_num,customer1);
            System.out.println("Customer successfully registered!!");
        }
        else{
            System.out.println("Customer is already registered!!");
        }
        customer();

    }

    public static void customer_priviliges(Customer customer){
        System.out.println("Welcome "+customer.getUsername());
        System.out.println("   1) Browse products");
        System.out.println("   2) Browse deals");
        System.out.println("   3) Add a product to cart");
        System.out.println("   4) Add products in deal to cart");
        System.out.println("   5) View coupons");
        System.out.println("   6) Check account balance");
        System.out.println("   7) View cart");
        System.out.println("   8) Empty cart");
        System.out.println("   9) Checkout cart");
        System.out.println("   10) Upgrade customer status");
        System.out.println("   11) Add amount to wallet");
        System.out.println("   12) back");
        int n=Input.input_num();
        if(n==1){ // Browse products
            explore_catalog();
            customer_priviliges(customer);
        }
        else if(n==2){ // Browse deals
            avail_deal();
            customer_priviliges(customer);
        }
        else if(n==3){ // Add a product to cart
            System.out.print("Enter the product id:");
            double id=Input.input_id();
            if(Store.prod_sea(id)){
                System.out.print("Enter the quantity:");
                int qty=Input.input_sim();
                for (HashMap.Entry<Category, ArrayList<Product>> entry : Store.Category_product.entrySet()) {
                    for (int i = 0; i < Store.Category_product.get(entry.getKey()).size(); i++){
                        if(Store.Category_product.get(entry.getKey()).get(i).getProduct_id()==id){
                            Product p=new Product(Store.Category_product.get(entry.getKey()).get(i),qty);
                            customer.getCart_Prod().put(id,p);
                            System.out.println("Item successfully added to the cart.");
                        }
                    }
                }
            }
            else{
                System.out.println("Product id is invalid Try again.");
            }
            customer_priviliges(customer);
        }
        else if(n==4){ // Add products in deal to cart
            System.out.println("Enter the deal id:");
            double id=Input.input_id();
            if(Store.Deals.containsKey(id)){
                System.out.println("Enter the quantity:");
                int qty=Input.input_sim();
                customer.getCart_deal().put(id,qty);
            }
            else{
                System.out.println("Deal id is invalid Try again.");
            }
            customer_priviliges(customer);
        }
        else if(n==5){ // View coupons
            if(customer.getCoupons().size()==0){
                System.out.println("No available coupons:");
            }
            else{
                System.out.println("All available coupons are: ");
                for(int i=0;i<customer.getCoupons().size();i++){
                    System.out.println(customer.getCoupons().get(i));
                }
            }
            customer_priviliges(customer);
        }
        else if(n==6){ // Check account balance
            System.out.println("Your account balance is "+customer.getWallet_amt());
            customer_priviliges(customer);
        }
        else if(n==7){ // View cart
            System.out.println("Products in the cart:");
            for (HashMap.Entry<Double, Product> entry : customer.getCart_Prod().entrySet()) {
                entry.getValue().printProduct();
            }
            for (HashMap.Entry<Double, Integer> entry : customer.getCart_deal().entrySet()) {
                System.out.println("Products in deal in cart are: ");
                System.out.println("Deal Id: "+entry.getKey());
                System.out.println("Quantity: "+entry.getValue());
                System.out.println("Deal on product id " + Store.Deals.get(entry.getKey()).get(0)+" and product id "+Store.Deals.get(entry.getKey()).get(1)+"is: ");
                if(customer.getCurrent_status()==2){
                    System.out.println(Store.Deals.get(entry.getKey()).get(2));
                }
                if(customer.getCurrent_status()==1){
                    System.out.println(Store.Deals.get(entry.getKey()).get(3));
                }
                if(customer.getCurrent_status()==0){
                    System.out.println(Store.Deals.get(entry.getKey()).get(4));
                }
            }
            customer_priviliges(customer);
        }
        else if(n==8){ // Empty cart
            customer.emptyCart();
            System.out.println("Cart successfully emptied!");
            customer_priviliges(customer);

        }
        else if(n==9){ // Checkout cart
            if(!customer.quantity_check()){
                System.out.println("Cannot place order as some items are out of stock!!");
                customer_priviliges(customer);
            }
            double bill=0;
            for (HashMap.Entry<Double, Integer> entry : customer.getCart_deal().entrySet()) {
                bill+=Store.Deals.get(entry.getKey()).get(4-customer.getCurrent_status())*entry.getValue();
            }
            double bill1=bill;
            for (HashMap.Entry<Double, Product> entry : customer.getCart_Prod().entrySet()) {
                bill+=entry.getValue().getPrice()*entry.getValue().getQuantity();
            }
            int vid=0;
            if(vid==0){
                for (HashMap.Entry<Double, Product> entry : customer.getCart_Prod().entrySet()) {
                    if(customer.getCurrent_status()==0){
                        bill1+=(100-entry.getValue().getDiscount_normal())/100*entry.getValue().getPrice()*entry.getValue().getQuantity();
                    }
                    else if(customer.getCurrent_status()==1){
                        bill1+=(100-Math.max(entry.getValue().getDiscount_prime(),5))/100*entry.getValue().getPrice()*entry.getValue().getQuantity();
                    }
                    else if(customer.getCurrent_status()==2){
                        bill1+=(100-Math.max(entry.getValue().getDiscount_elite(),10))/100*entry.getValue().getPrice()*entry.getValue().getQuantity();
                    }
                }
                double home=customer.delivery_fee(bill);
                bill1+=home;
                if(bill1> customer.getWallet_amt()){
                    System.out.println("Insufficient balance!! Please try again");
                    customer_priviliges(customer);
                }
                else{
                    System.out.println("Your order is placed successfully. Details:");
                    for (HashMap.Entry<Double, Product> entry : customer.getCart_Prod().entrySet()) {
                        entry.getValue().printProduct();
                    }
                    for (HashMap.Entry<Double, Integer> entry : customer.getCart_deal().entrySet()) {
                        System.out.println("Products in deal in cart are: ");
                        System.out.println("Deal Id: "+entry.getKey());
                        System.out.println("Quantity: "+entry.getValue());
                        System.out.println("Deal on product id " + Store.Deals.get(entry.getKey()).get(0)+" and product id "+Store.Deals.get(entry.getKey()).get(1)+"is: ");
                        if(customer.getCurrent_status()==2){
                            System.out.println(Store.Deals.get(entry.getKey()).get(2));
                        }
                        if(customer.getCurrent_status()==1){
                            System.out.println(Store.Deals.get(entry.getKey()).get(3));
                        }
                        if(customer.getCurrent_status()==0){
                            System.out.println(Store.Deals.get(entry.getKey()).get(4));
                        }
                    }
                    System.out.println("Delivery charges: "+home);
                    System.out.println("Total cost = "+bill1);
                    customer.reduceWallet_amt(bill1);
                    customer.delivery();
                    if(bill1>=5000){
                        customer.coupon_generator();
                    }
                    customer.emptyCart();
                }
            }
            customer_priviliges(customer);
        }
        else if(n==10){ // Upgrade customer status
            customer.printCurrent_status();
            System.out.print("Choose new status: ");
            String name=Input.input_string();
            if(name.equals("ELITE") && customer.getCurrent_status()==1){
                if(!customer.reduceWallet_amt(100)){
                    System.out.println("Sorry you do not have enough credit balance to upgrade your status.");
                }
                else{
                    System.out.println("Status updated to ELITE");
                    customer.setCurrent_status(2);
                    customer.setStatus_discount(10);
                }
                customer_priviliges(customer);
            }
            else if(name.equals("ELITE") && customer.getCurrent_status()==0){
                if(!customer.reduceWallet_amt(300)){
                    System.out.println("Sorry you do not have enough credit balance to upgrade your status.");
                }
                else{
                    System.out.println("Status updated to ELITE");
                    customer.setCurrent_status(2);
                    customer.setStatus_discount(10);
                }
                customer_priviliges(customer);
            }
            else if(name.equals("ELITE") && customer.getCurrent_status()==2){
                System.out.println("Status remains ELITE");
                customer_priviliges(customer);
            }
            else if(name.equals("PRIME") && customer.getCurrent_status()==0){
                if(!customer.reduceWallet_amt(200)){
                    System.out.println("Sorry you do not have enough credit balance to upgrade your status.");
                }
                else{
                    System.out.println("Status updated to PRIME");
                    customer.setStatus_discount(5);
                    customer.setCurrent_status(1);
                }
                customer_priviliges(customer);
            }
            else if(name.equals("PRIME") && customer.getCurrent_status()==1){
                System.out.println("Status remains PRIME");
                customer_priviliges(customer);
            }
            else if(name.equals("NORMAL") && customer.getCurrent_status()==0){
                System.out.println("Status remains NORMAL");
                customer_priviliges(customer);
            }
            else{
                System.out.println("SORRY YOU CANNOT DOWNGRADE YOUR CURRENT STATUS!!");
                customer_priviliges(customer);
            }
        }
        else if(n==11){ // Add amount to wallet
            int amt=Input.input_amt();
            customer.increaseWallet_amt(amt);
            customer_priviliges(customer);
        }
        else if(n==12){ // Back to customer
            System.out.println("Bye "+customer.getUsername()+"!!");
            customer();
        }
    }

    public static void customer_login(){
        System.out.print("Enter name: ");
        String name=Input.input_string();
        System.out.print("Enter password: ");
        String password=Input.input_string();
        if(Store.customer_Search(name,password)){
            for (HashMap.Entry<Integer, Customer> entry : Store.Customer_record.entrySet()) {
                if(password.equals(entry.getValue().getPassword()) && name.equals(entry.getValue().getUsername())){
                    customer_priviliges(entry.getValue());
                }
            }
        }
        else{
            System.out.println("Sorry Wrong credentials.");
            customer();
        }
    }

    public static void customer(){
        System.out.println("   1) Sign up");
        System.out.println("   2) Log in");
        System.out.println("   3) Back");
        int n=Input.input_num();
        if(n==1){ // signing up as the customer
            customer_sign_up();
        }
        else if(n==2){ // Log in as a customer
            customer_login();
        }
        else if(n==3){ // Back to welcome function
            welcome();
        }
    }

    public static void admin(){
        System.out.println("Dear Admin,");
        System.out.println("Please enter your username and password");
        System.out.print("Username: ");
        String user = Input.input_string();
        System.out.print("Password: ");
        String pass = Input.input_string();
        if(Input.user1.getUsername().equals(user)&&Input.user1.getPassword().equals(pass)){
            admin_priviliges(Input.user1);
        }
        else if(Input.user2.getUsername().equals(user)&&Input.user2.getPassword().equals(pass)){ //exit the application
            admin_priviliges(Input.user2);
        }
        else{
            System.out.println("Not the authorized admin!!! ");
            welcome();
        }
    }
    public static void welcome(){
        System.out.println("WELCOME TO FLIPZON:");
        System.out.println("   1) Enter as Admin");
        System.out.println("   2) Explore the Product Catalog");
        System.out.println("   3) Show Available Deals");
        System.out.println("   4) Enter as Customer");
        System.out.println("   5) Exit the Application");
        int n=Input.input_num();
        if(n==1){ //enter as an admin
            admin();
        }
        else if(n==2){ //Explore the Product Catalog
            explore_catalog();
            welcome();
        }
        else if(n==3){ // Show Available Deals
            avail_deal();
            welcome();
        }
        else if(n==4){ // Enter as Customer
            customer();
        }
        else if(n==5){ // exit the application
            System.exit(0);
        }
    }
    public static void explore_catalog(){
        if(Store.Category_product.size()==0){
            System.out.println("Nothing to show yet.");
            return;
        }
        for (HashMap.Entry<Category, ArrayList<Product>> entry : Store.Category_product.entrySet()) {
            entry.getKey().printCategory();
            System.out.println("Products belonging to this category are: ");
            for (int i = 0; i < Store.Category_product.get(entry.getKey()).size(); i++){
                Store.Category_product.get(entry.getKey()).get(i).printProduct();
                System.out.println();
            }
        }
    }
    public static void avail_deal(){
        if(Store.Deals.size()==0){
            System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
            return;
        }
        for (HashMap.Entry<Double, ArrayList<Double>> entry : Store.Deals.entrySet()) {
            System.out.println("Deals available are: ");
            System.out.println("Get away deal Id: "+entry.getKey());
            System.out.println("Getaway deal on product id " + entry.getValue().get(0)+" on product id "+entry.getValue().get(1)+": ");
            System.out.println("For Elite customers only at "+entry.getValue().get(2));
            System.out.println("For Prime customers only at "+entry.getValue().get(3));
            System.out.println("For Normal customers only at "+entry.getValue().get(4));
        }
    }
    public static void main(String[] args){
        Store.customer_num=0;
        welcome();
    }
}
