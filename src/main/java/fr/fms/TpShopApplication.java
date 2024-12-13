package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Article;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TpShopApplication implements CommandLineRunner {
    @Autowired
    private IBusinessImpl business;
    
    private Scanner scan = new Scanner(System.in);
    private static final int DEFAULT_PAGE_SIZE = 5;

    public static void main(String[] args) {
        SpringApplication.run(TpShopApplication.class, args);
    }
    
    private int scanInt() {
        while (!scan.hasNextInt()) {
            System.out.println("Veuillez saisir un nombre valide.");
            scan.next(); // Clear the invalid input
        }
        return scan.nextInt();
    }
    
    private void showAllArticlesWithoutPagination() {
        List<Article> articles = business.showAllArticlesWithoutPagination();
        printTable(articles);
    }
    
    private void showAllArticlesWithPagination(int page) {             
        Page<Article> articlesPage = business.showAllArticlesWithPagination(page, DEFAULT_PAGE_SIZE);
        printTable(articlesPage.getContent());
        System.out.println("Page " + (page + 1) + " sur " + articlesPage.getTotalPages());
    }

    private void printTable(List<Article> articles) {
        String leftAlignFormat = "| %-4d | %-20s | %-20s | %-10.2f | %-10s |%n";

        System.out.format("+------+----------------------+----------------------+------------+------------+%n");
        System.out.format("| ID   | Brand                | Description          | Price      | Category   |%n");
        System.out.format("+------+----------------------+----------------------+------------+------------+%n");
        for (Article article : articles) {
            System.out.format(leftAlignFormat, article.getId(), article.getBrand(), article.getDescription(), article.getPrice(), article.getCategory().getName());
        }
        System.out.format("+------+------------+----------------------+------------+----------------------+%n");
    }

    @Override
    public void run(String...args) throws Exception {
        business.createdData();
        runApplication();
    }

    public void runApplication() {
        int choice = 0;
        
        while(choice != 3) {
            displayMenu();
            choice = scanInt();
            switch(choice) {
                case 1 : showAllArticlesWithoutPagination();                
                    break;                    
                case 2 : 
                	int currentPage = 0;
                	boolean continuePaging = true;
                	while (continuePaging) {
                		showAllArticlesWithPagination(currentPage);
                		System.out.println("1 : Page suivante | 2 : Page précédente | 3 : Retour au menu principal");
                		int navChoice = scanInt();
                		if (navChoice == 1) {
                			currentPage++;
                		}else if (navChoice == 2 && currentPage > 0) {
                			currentPage--;
                		}else if (navChoice == 3) {
                			continuePaging = false;
                		}else {
                			System.out.println("Choix invalide, veuillez réessayer");
                		}
                	}
                    break;                    
                case 3 : System.out.println("A bientôt :)");
                    break;    
                default : System.out.println("veuillez saisir une valeur entre 1 et 3");
            }
        }
    }

    public static void displayMenu() {    
        System.out.println("\nPour réaliser une action, tapez le code correspondant");
        System.out.println("1 : Afficher tous les articles sans pagination");
        System.out.println("2 : Afficher tous les articles avec pagination");
        System.out.println("******************************************************");
        System.out.println("3 : Sortir du programme");
    }
}