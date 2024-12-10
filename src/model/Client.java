package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    public Client(String name, String email, String password) {
        super(name, email, password);
    }

    private List<Borrowed> userBorrowedBooks = new ArrayList<>();

    public void addBorrowedBook(Borrowed borrowedBook){
        userBorrowedBooks.add(borrowedBook);
    }

    public void removeBorrowedBook(Borrowed borrowedBook){
        userBorrowedBooks.remove(borrowedBook);
    }

    public List<Borrowed> getBorrowedBooks(){
        return userBorrowedBooks;
    }

    @Override
    public String accountType() {
        return "client";
    }


}
