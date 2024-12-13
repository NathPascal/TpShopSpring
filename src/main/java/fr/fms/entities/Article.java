package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String description;
	private double price;
	
	@ManyToOne
	private Category category;
	
	public Article() {
	}
	
	public Article(String brand, String description, double price, Category category) {
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	 public Long getId() {
	        return id;
	    }

	    public String getBrand() {
	        return brand;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public Category getCategory() {
	        return category;
	    }
	
	@Override
    public String toString() {
       return "Article [id=" + id + ", brand=" + brand + ", description=" + description + ", price=" + price + ", category=" + category + "]";
		
    }

}
