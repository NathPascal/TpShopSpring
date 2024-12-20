package fr.fms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;


@Service
public class IBusinessImpl {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public void createdData() {
	Category smartphone = categoryRepository.save(new Category("Smartphone"));
	Category tablet = categoryRepository.save(new Category("Tablet"));
	Category pc = categoryRepository.save(new Category("PC"));
	Category enceinte = categoryRepository.save(new Category("Enceinte"));
	
	articleRepository.save(new Article("Samsung","S9",500, smartphone));
	articleRepository.save(new Article("Samsung","S8",1000, smartphone));
	articleRepository.save(new Article("Xiaomi","MI10",100, smartphone));
	articleRepository.save(new Article("Xiaomi","MI11",250, smartphone));
	articleRepository.save(new Article("Hewlett Packard","S9",2000, smartphone));
	articleRepository.save(new Article("Iphone","10",1500, smartphone));
	
	
	articleRepository.save(new Article("Samsung","GalaxyTab",350, tablet));
	articleRepository.save(new Article("Apple","Ipad",350, tablet));
	
	articleRepository.save(new Article("Asus","R510",600, pc));
	
	articleRepository.save(new Article("OnePLus","9 Pro",200, enceinte));
	}
	
	public List<Article> showAllArticlesWithoutPagination() {
        return articleRepository.findAll();
    }
	
	public Page<Article> showAllArticlesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findAll(pageable);
	}
	


}
