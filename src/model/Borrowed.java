package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Borrowed {

    private Book book;
    private User borrower;
    private LocalDate borrowedDate;
    private LocalDate dueDate;

    public Borrowed(Book book, User borrower, LocalDate borrowedDate, LocalDate dueDate) {
        this.book = book;
        this.borrower = borrower;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Livro: %s, Pego Emprestado por: %s, Data do empréstimo: %s, Data limite de devolução: %s",
                book, borrower.getName(),borrowedDate.format(formatter), dueDate.format(formatter));
    }
}