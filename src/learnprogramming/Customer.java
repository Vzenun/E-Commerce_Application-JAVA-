package learnprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Customer {
    private final String username;
    private final String password;
    private double wallet_amt;
    private double status_discount;
    private int current_status;
    private ArrayList<Integer> coupons=new ArrayList<>();

    private HashMap<Double, Product> cart_Prod = new HashMap<>();

    public ArrayList<Integer> getCoupons() {
        return coupons;
    }

    private HashMap<Double, Integer> cart_deal = new HashMap<>();

    public void setCurrent_status(int current_status) {
        this.current_status = current_status;
    }

    public int getCurrent_status() {
        return current_status;
    }

    public Boolean reduceWallet_amt(double amt) {
        if(this.wallet_amt-amt>=0){
            this.wallet_amt-=amt;
            return true;
        }
        else{
            return false;
        }
    }

    public void increaseWallet_amt(int amt) {
        this.wallet_amt+=amt;
        System.out.println("Amount successfully added.");
    }

    public double getWallet_amt() {
        return wallet_amt;
    }

    public void printCurrent_status() {
        System.out.print("CURRENT STATUS: ");
        if(this.current_status==2){
            System.out.println("ELITE");
        }
        else if(this.current_status==1){
            System.out.println("PRIME");
        }
        else if(this.current_status==0){
            System.out.println("NORMAL");
        }
    }

    // by default current status of customer is normal which is 0;
    //1 means prime
    //2 means elite
    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet_amt=1000; //by default it is set to 1000;
        this.status_discount=0; //by default 0% since wew are normal
        this.current_status=0; //by default 0 since wew are normal status
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getStatus_discount() {
        return status_discount;
    }

    public HashMap<Double, Product> getCart_Prod() {
        return cart_Prod;
    }

    public HashMap<Double, Integer> getCart_deal() {
        return cart_deal;
    }

    public void emptyCart(){
        this.cart_deal.clear();
        this.cart_Prod.clear();
    }

    public void setStatus_discount(double status_discount) {
        this.status_discount = status_discount;
    }

    public Double delivery_fee(double total_mrp){
        if(this.current_status==0){
            double fee=100+(5*(total_mrp/100));
            //System.out.println("Delivery charges: "+fee);
            return fee;
        }
        else if(this.current_status==1){
            double fee=100+(2*(total_mrp/100));
            //System.out.println("Delivery charges: "+fee);
            return fee;
        }
        else{
            //System.out.println("Delivery charges: 100");
            return 100.0;
        }
    }

    public void coupon_generator(){
        Random rand=new Random();
        int n=rand.nextInt(10);
        if(n%2==0){
            if(this.current_status==2){
                System.out.println("You have won four coupons.You can see them in view coupons option. Congratulations!!");
                for(int i=0;i<4;i++){
                    this.coupons.add(rand.nextInt(9)+5);
                }
            }
            else if(this.current_status==1){
                System.out.println("You have won two coupons.You can see them in view coupons option. Congratulations!!");
                for(int i=0;i<2;i++){
                    this.coupons.add(rand.nextInt(9)+5);
                }
            }
        }
        else{
            if(this.current_status==2){
                System.out.println("You have won three coupons.You can see them in view coupons option. Congratulations!!");
                for(int i=0;i<3;i++){
                    this.coupons.add(rand.nextInt(9)+5);
                }
            }
            else if(this.current_status==1){
                System.out.println("You have won one coupon.You can see them in view coupons option. Congratulations!!");
                for(int i=0;i<1;i++){
                    this.coupons.add(rand.nextInt(9)+5);
                }
            }
        }
    }
    public void delivery(){
        if(this.current_status==0){
            System.out.println("Order placed. It will be delivered in 7-10 days.");
        }
        else if(this.current_status==1){
            System.out.println("Order placed. It will be delivered in 3-6 days.");
        }
        else{
            System.out.println("Your order will be delivered within 2 days");
        }
    }

    public Boolean quantity_check(){
        for (HashMap.Entry<Double, Product> entry : this.cart_Prod.entrySet()) {
            int a=0;
            a+=entry.getValue().getQuantity();
            for (HashMap.Entry<Double, Integer> entry1 : this.cart_deal.entrySet()) {
                if(Store.Deals.get(entry1.getKey()).get(0)==entry.getValue().getProduct_id()  || Store.Deals.get(entry1.getKey()).get(1)==entry.getValue().getProduct_id()){
                    a+=entry1.getValue();
                }
            }
            for (HashMap.Entry<Category, ArrayList<Product>> entry2 : Store.Category_product.entrySet()) {
                for (int i = 0; i < Store.Category_product.get(entry2.getKey()).size(); i++){
                    if(Store.Category_product.get(entry2.getKey()).get(i).getProduct_id()==entry.getValue().getProduct_id()){
                        if(a>Store.Category_product.get(entry2.getKey()).get(i).getQuantity()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
