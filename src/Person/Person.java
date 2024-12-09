package Person;

public class Person {

    protected String name ;
    protected double balance;


    public Person(String name , double balance){
        this.name = name ;
        this.balance = balance ;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public  String toString(){
        return String.format("Name: %s Balance : %.2f" , name, balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
