package service;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private User currentUser;

    public boolean isUsersNull(){
        return users.isEmpty();
    }

    public boolean isLogged(String email, String password){
        for(User user : users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void signup(String name, String email, String password, String accountType){
        User user;
        if(accountType.equals("cliente")){
            user = new Client(name, email, password);
        } else{
            user = new Employee(name, email, password);
        }
        users.add(user);
        setCurrentUser(user);
    }

    public boolean isEmailInUse(String email){
        for(User user : users){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public void logout(){
        currentUser = null;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void showAvailableBooks(){
        if(!books.isEmpty()){
            System.out.println("Livros Disponíveis:");
            for(int i = 0; i < books.size(); i++){
                System.out.printf("%d. %s", i+1, books.get(i));
                System.out.println();
            }
            System.out.print("\n");
        } else{
            System.out.println("Nenhum livro disponível.");
        }
    }

    public void borrowBook(String title, Client user) {
        Book currentBook = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                LocalDate agora = LocalDate.now();
                Borrowed borrowed = new Borrowed(book, user, agora, agora.plusDays(14));
                user.addBorrowedBook(borrowed);
                System.out.println("Livro " + title + " pego emprestado com sucesso.");
                currentBook = book;
            }
        }
        if(currentBook != null){
            books.remove(currentBook);
        }
    }

    public void returnBook(String title, Client user) {
        Book currentBook = null;
        Borrowed currentBorrowed = null;
        List<Borrowed> borrowedBooks = user.getBorrowedBooks();
        for (Borrowed borrowed : borrowedBooks) {
            if (borrowed.getBook().getTitle().equalsIgnoreCase(title)) {
                currentBorrowed = borrowed;
                currentBook = borrowed.getBook();
                System.out.println("Livro " + title + " devolvido com sucesso.");
            }
        }
        if(currentBook != null){
            user.removeBorrowedBook(currentBorrowed);
            books.add(currentBook);
        }
    }

    public void addBook(String title, String author) {
        Book newBook = new Book(title, author);
        books.add(newBook);
        System.out.println("Livro adicionado com sucesso!");
    }

    public void removeBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                System.out.println("Livro " + title + " removido com sucesso.");
            }
        }
    }
}
