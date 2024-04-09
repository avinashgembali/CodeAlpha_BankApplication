package CodeAlpha;

import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args){
        BankAccount[] accounts = {
                new BankAccount("avinash",34812,1000,1081),
                new BankAccount("lokesh",87889,5000,2034),
                new BankAccount("sai",98790,6000,4477)
        };
        Scanner scanner = new Scanner(System.in);
        int attempts = 3;
        BankAccount account = null;
        while(attempts > 0){
            System.out.println("enter account number : ");
            int acc = scanner.nextInt();
            account = findAccount(accounts,acc);
            if(account != null){
                break;
            }
            else{
                attempts--;
                if(attempts > 0){
                    System.out.println("account not found please try again attempts left are : "+attempts);
                }
                else {
                    System.out.println("extended maximum attempts,please contact customer support");
                    return;
                }
            }
        }
        int pinAttempts = 3;
        while(pinAttempts > 0){
            System.out.println("enter the pin : ");
            int enteredPin = scanner.nextInt();
            if(account.matchPin(enteredPin)){
                int choice = selectOption(scanner);
                switch(choice){
                    case 1 :
                        System.out.println("enter amount to be deposited : ");
                        int depositAmount = scanner.nextInt();
                        account.depositAmount(depositAmount);
                        break;
                    case 2 :
                        System.out.println("enter amount to be withdrawn : ");
                        int withdrawAmount = scanner.nextInt();
                        account.withdrawAmount(withdrawAmount);
                        break;
                    case 3 :
                        System.out.println("your balance is : "+account.getBalance());
                        break;
                    case 4 :
                        System.out.println("exiting");
                        break;
                    default:
                        System.out.println("invalid option");
                        break;
                }
                break;
            }
            else{
                pinAttempts--;
                System.out.println("invalid pin.Atteempts lefft : "+pinAttempts);
            }
        }
    }
    public static BankAccount findAccount(BankAccount[] accounts,int accNumber){
        for(BankAccount account : accounts){
            if(account.getAccountNumber() == accNumber){
                return account;
            }
        }
        return null;
    }
    public static int selectOption(Scanner scanner){
        System.out.println("1.deposit amount");
        System.out.println("2.withdraw amount");
        System.out.println("3.check balance");
        System.out.println("4.exit");
        System.out.println("enter your choice : ");
        return scanner.nextInt();
    }
}
class BankAccount{
    private String name;
    private int accountNumber;
    private int balance;
    private int pin;
    public BankAccount(String name,int accountNumber,int balance,int pin){
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }
    public int getBalance(){
        return balance;
    }
    public void depositAmount(int amount){
        this.balance += amount;
        System.out.println("amount has been deposited.Remaining balance : " + this.balance);
    }
    public void withdrawAmount(int amount){
        if(this.balance < amount){
            System.out.println("Insufficient Balance");
        }
        else {
            this.balance -= amount;
            System.out.println("amount has been withdeawn.Remaining balance : " + this.balance);
        }
    }
    public boolean matchPin(int pin){
        return this.pin == pin;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
}
