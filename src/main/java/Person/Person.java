package Person;

public class Person {

    protected String name ;


    public Person(String name ){
        this.name = name ;


    }

    public String getName() {
        return name;
    }



    @Override
    public  String toString(){

        return String.format("%s" , name);
    }



}
