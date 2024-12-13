package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class TpShopApplication implements CommandLineRunner {
	
	
	private static Scanner scan = new Scanner(System.in);
	private static IBusinessImpl business = new IBusinessImpl(); 

	public static void main(String[] args) {
		SpringApplication.run(TpShopApplication.class, args);
		
		System.out.println("Bonjour et bienvenu dans note application de gestion d'articles!\n");
		
		int choice = 0;
		while(choice != 3) {
			displayMenu();
			choice = scanInt();
			switch(choice) {
				case 1 : showAllArticlesWithoutPagination();				
					break;					
				case 2 : showAllArticlesWithPagination();
					break;					
				case 3 : System.out.println("à bientôt :)");
					break;	
				default : System.out.println("veuillez saisir une valeur entre 1 et 3");
			}
		}
			
	}
	
	

	

	private static int scanInt() {
		while (!scan.hasNextInt()) {
            System.out.println("Veuillez saisir un nombre valide.");
            scan.next();
        }
        return scan.nextInt();
	}

	@Override
	public void run(String...args) throws Exception{
		business.createdData();

	}
		
					
				
	public static void displayMenu() {	
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Afficher tous les articles sans pagination");
		System.out.println("2 : Afficher tous les articles avec pagination");
		System.out.println("****************************************************************************");
		System.out.println("3 : Sortir du programme");}
		
		}
