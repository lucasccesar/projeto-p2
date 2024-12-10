package model;

import java.time.LocalDate;

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
        return String.format("Livro: %s, Emprestado por: %s, Data do empréstimo: %s, Data de devolução: %s",
                book, borrower.getName(), borrowedDate, dueDate);
    }
}