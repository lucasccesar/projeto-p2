package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    public Client(String name, String email, String password) {
        super(name, email, password);
    }

    private List<Borrowed> borrowedBooks = new ArrayList<>();

    public void addBorrowedBook(Borrowed borrowedBook){
        borrowedBooks.add(borrowedBook);
    }

    public void removeBorrowedBook(Borrowed borrowedBook){
        borrowedBooks.remove(borrowedBook);
    }

    public List<Borrowed> getBorrowedBooks(){
        return borrowedBooks;
    }

    @Override
    public String accountType() {
        return "client";
    }


}
