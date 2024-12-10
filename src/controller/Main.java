package controller;

import model.Client;
import model.User;
import service.Library;

import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static Library library;
    static User user;

    public static void main(String[] args) {
        library = new Library();
        loginSignup();
    }

    public static void loginSignup() {
        String accountType;
        String name;
        String email;
        String password;
        String loginOrSignup = "";

        if (!library.isUsersNull()) {
            System.out.print("Deseja entrar ou criar uma nova conta? ");
            loginOrSignup = scan.next();
            System.out.print("\n");
            while(!loginOrSignup.equals("entrar") && !loginOrSignup.equals("criar")) {
                System.out.println("Digite novamente");
                System.out.print("Deseja entrar ou criar uma nova conta? ");
                loginOrSignup = scan.next();
                System.out.print("\n");
            }
        }

        if (loginOrSignup.equals("entrar")) {
            System.out.print("Digite seu email: ");
            email = scan.next();
            System.out.print("Digite sua senha: ");
            password = scan.next();

            while (!library.isLogged(email, password)) {
                System.out.println("\nEmail ou senha incorretos.");
                System.out.print("Deseja tentar novamente (s/n)? ");
                if (scan.next().equals("s")) {
                    System.out.print("\nDigite seu email: ");
                    email = scan.next();
                    System.out.print("Digite sua senha: ");
                    password = scan.next();
                } else {
                    loginSignup();
                }
            }
        } else if (library.isUsersNull() || loginOrSignup.equals("criar")) {
            System.out.print("Deseja se cadastrar como cliente ou funcionário? ");
            accountType = scan.next();
            scan.nextLine();
            while (!(accountType.equals("cliente") || accountType.equals("funcionario"))) {
                System.out.print("Deseja se cadastrar como cliente ou funcionário? ");
                accountType = scan.next();
                scan.nextLine();
            }
            System.out.print("\nDigite seu nome: ");
            name = scan.nextLine();
            System.out.print("Digite seu email: ");
            email = scan.next();

            while (library.isEmailInUse(email)) {
                System.out.println("\nEsse email já está cadastrado.");
                System.out.print("Digite outro email: ");
                email = scan.next();
                System.out.print("\n");
            }

            System.out.print("Digite sua senha: ");
            password = scan.next();

            library.signup(name, email, password, accountType);
        }

        user = library.getCurrentUser();
        System.out.printf("\nOlá, %s! O que deseja fazer? ", user.getName());
        showActions();
    }

    public static void showActions() {
        int action;
        System.out.printf("\n1. Visualizar livros disponíveis (%d livro(s) disponível(is))", library.getBooks().size());
        if (user.accountType().equals("client")) {
            System.out.println("\n2. Pegar livro emprestado");
            System.out.println("3. Devolver livro");
            System.out.println("4. Visualizar livros para devolver");
            System.out.println("5. Sair da conta");

            action = scan.nextInt();
            System.out.print("\n");
            doClientAction(action);
        } else {
            System.out.println("\n2. Adicionar livro");
            System.out.println("3. Remover livro");
            System.out.printf("4. Visualizar livros emprestados (%d livro(s) emprestado(s))\n", library.getBorrowedBooks().size());
            System.out.println("5. Sair da conta");

            action = scan.nextInt();
            System.out.print("\n");
            doEmployeeAction(action);
        }
    }

    public static void doClientAction(int action) {
        if (action == 1) {
            library.showAvailableBooks();
        } else if (action == 2) {
            if(library.getBooks().size() == 0){
                System.out.println("Nenhum livro disponível para pegar emprestado");
            } else{
                System.out.print("Digite o título do livro que deseja pegar emprestado: ");
                String title = scan.next();
                library.borrowBook(title, (Client) user);
            }
        } else if (action == 3) {
            Client client = (Client) user;
            if(client.getBorrowedBooks().size() == 0){
                System.out.println("Nenhum livro disponível para devolver");
            } else{
                System.out.print("Digite o título do livro que deseja devolver: ");
                String title = scan.next();
                library.returnBook(title, (Client) user);
            }
        } else if (action == 4) {
            Client client = (Client) user;
            library.showBooksToReturn(client);
        } else if (action == 5) {
            library.logout();
            loginSignup();
        }

        if (action != 5) {
            showActions();
        }
    }

    public static void doEmployeeAction(int action) {
        if (action == 1) {
            library.showAvailableBooks();
        } else if (action == 2) {
            System.out.print("Digite o título do livro: ");
            String title = scan.next();
            System.out.print("Digite o autor do livro: ");
            String author = scan.next();
            library.addBook(title, author);
        } else if (action == 3) {
            System.out.print("Digite o título do livro que deseja remover: ");
            String title = scan.next();
            library.removeBook(title);
        } else if (action == 4) {
            library.showBorrowedBooks();
        } else if (action == 5) {
            library.logout();
            loginSignup();
        }

        if (action != 5) {
            showActions();
        }
    }
}
