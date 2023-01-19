package learnprogramming;

import java.util.Scanner;

public class Input {
    public static int input_num(){
        System.out.print("Enter the choice: ");
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }
    public static int input_amt(){
        System.out.print("Enter the amount: ");
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }
    public static String input_string(){
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }
    public static int input_sim(){
        Scanner sc=new Scanner(System.in);
        return sc.nextInt();
    }
    public static double input_id(){
        Scanner sc=new Scanner(System.in);
        return sc.nextDouble();
    }

    //Hardcoded password and username of the admins
    public static Admin user1=new Admin("Vidur","2021364"); //encapsulated
    public static Admin user2=new Admin("A","A"); //encapsulated


}
