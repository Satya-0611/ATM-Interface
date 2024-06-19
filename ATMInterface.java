import java.util.Scanner;
import java.util.ArrayList;
public class ATMInterface {
    public static void main(String args[]){
        System.out.println("---------------------------------Welcome to an ATM interface----------------------------------");
        System.out.println("\nYou need to resister first to perform operations");
        Account ac1 = new Account();
        ac1.resister();
    }
}

class Account {
    String name;
    String username;
    String password;
    double accountNo;
    double balance;
    ArrayList<String> transactionHistory;
    Account(){
        transactionHistory = new ArrayList<>();
    }

    protected void resister(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your full name below");
        this.name = sc.nextLine();
        System.out.println("\nEnter a user name for acoount with no white space");
        this.username = sc.next();
        System.out.println("\nCreate a new Password for your account");
        if(sc.hasNext()) sc.nextLine(); //to clear input buffer
        this.password = sc.nextLine();
        System.out.println("\nChoose your account number and enter");
        this.accountNo = sc.nextDouble();
        System.out.println("\nDo you want to Deposit any initial balance into your account, type y for yes and n for no");
        char option = sc.next().charAt(0);
        if(option == 'y')
        {
            System.out.println("\nEnter ammount to Deposit");
            this.balance = sc.nextDouble();
            transactionHistory.add("Initially deposited ammount " + balance);
        }

        System.out.println("\n\n------------------------------Thank you for choosing us-------------------------------");
        System.out.println("Your Account created sucessfully, keep your Account details secret :)");

        int choice = 0; // to take input untill user give a valid input
        while(choice != 2){
            System.out.println("Now enter a choice \n1 - login \n2 - exit");
            choice = sc.nextInt();
            if(choice == 1){
                checkLogin();
                break;
            } else if(choice == 2){
                System.exit(0);
            } else {
                System.out.println("Enter a valid choice, try again");
            }
        }
    }

    boolean login(){
        System.out.println("\n\n---------------------------Welcome to login page------------------------------");
        System.out.println("\nEnter your username below");
        Scanner sc = new Scanner(System.in);
        String loginUsername = sc.next();
        System.out.println("\nNow enter password to login");
        if(sc.hasNext()) sc.nextLine(); //to clear input buffer
        String loginPassword = sc.nextLine();

        if((loginUsername.equals(this.username)) && (loginPassword.equals(this.password))){
            return true;
        } else {
            return false;
        }
    }

    void checkLogin(){
        if(login()){
            System.out.println("\nLogin sucessfull");
            menu();
        } else {
            System.out.println("incorrect login information, try again");
        }
    }

    void deposit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter balance to deposit");
        double temp = sc.nextDouble();
        this.balance += temp;
        transactionHistory.add("deposited ammount " + temp);

        System.out.println("\nBalance deposited sucessfully \nDo you want to display your balance on screen, type y - yes or n - No");
        char choice = sc.next().charAt(0);
        if(choice == 'y'){
            System.out.println("Your balance is: " + balance);
        }
    }

    void withdraw(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter ammount to withdraw");
        double temp = sc.nextDouble();
        if(temp > balance) {
            System.out.println("\nInsufficient Balance to withdraw");
            return;
        }
        balance -= temp;
        transactionHistory.add("withdrawn ammount " + temp);

        System.out.println("\nBalance withdrawn sucessfully \nDo you want to display your balance on screen, type y - yes or n - No");
        char choice = sc.next().charAt(0);
        if(choice == 'y'){
            System.out.println("Your balance is: " + balance);
        }
    }

    void transfer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter recepiant name to transfer");
        String recepiant = sc.nextLine();
        System.out.println("Enter ammount to transfer to " + recepiant);
        double temp = sc.nextDouble();
        if(temp > balance) {
            System.out.println("\nInsufficient Balance to transfer");
            return;
        }
        balance -= temp;
        transactionHistory.add("Transfered ammount " + temp + " to " + recepiant);

        System.out.println("Ammount transfered sucessfully");
    }

    void menu(){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while((choice < 6) || (choice >= 0)){
            System.out.println("\n1 - Deposit \n 2 - withdraw() \n  3 - Transfer() \n   4 - balance History \n    5 - check Balance \n     6 - exit");
            System.out.println("\nEnter your choice below");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                deposit();
                break;

                case 2:
                withdraw();
                break;

                case 3:
                transfer();
                break;

                case 4:
                for(int i = 0; i<transactionHistory.size(); i++){
                    System.out.println((i+1) + " - " + transactionHistory.get(i));
                }
                break;

                case 5:
                System.out.println("Your balance is: " + balance);
                break;

                case 6:
                System.exit(0);
            }
        }
    }
}
