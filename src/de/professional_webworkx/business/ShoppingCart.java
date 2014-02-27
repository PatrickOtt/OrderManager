package de.professional_webworkx.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import de.professional_webworkx.model.Article;

@Stateful
public class ShoppingCart {

	Map<Integer, Article> shoppingCart = new HashMap<>();
	
	public ShoppingCart() {
		
	}
	
	public void addArticle(int quantity, Article article) {
		this.shoppingCart.put(new Integer(quantity), article);
		Iterator<Entry<Integer, Article>> itr = shoppingCart.entrySet().iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().getValue().getArticleName());
		}
		addMessage(article.getArticleName() + " wurde in den Warenkorb gelegt");
	}
	
	public void remove(Article article) {
		this.shoppingCart.remove(article);
		addMessage(article.getArticleName() + " wurde entfernt");
	}
	
	public List<Article> getShoppingCart() {
		List<Article> articles = new ArrayList<>();
		Set<Entry<Integer, Article>> entrySet = shoppingCart.entrySet();
		Iterator<Entry<Integer, Article>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<Integer, Article> next = iterator.next();
			articles.add(next.getValue());
		}
		
		return articles;
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
