package Exceptoins;

public class InvalidNameException extends Exception{
    private String name;
    public String getName(){return name;}
    public InvalidNameException(String message, String name){

        super(message);
        this.name=name;
    }
}
