package model;

public class Employee extends User{

    public Employee(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public String accountType() {
        return "employee";
    }

}
