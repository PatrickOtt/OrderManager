package de.professional_webworkx.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import de.professional_webworkx.model.Article;

@Named
@SessionScoped
/*
 * Die Bean bleibt uns für die gesamte Session erhalten, also einem
 * User zugeordnet, somit können wir den Warenkorb für die gesamten
 * Browsersession erhalten.
 */
public class ShoppingCartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1332117572442977016L;
	
	// ShoppingCart
	private List<Article> shoppingCart = new ArrayList<>();
	
	public ShoppingCartBean() {
		
	}
	
	/**
	 * Den gesamten ShoppingCart ausgeben
	 * @return
	 */
	public List<Article> getShoppingCart() {
		return shoppingCart;
	}

	public void addArticle(Article article) {
		this.shoppingCart.add(article);
		addMessage(article.getArticleName() + " wurde dem Warenkorb hinzugefügt");
	}

	public void removeArticle(Article article) {
		this.shoppingCart.remove(article);
		addMessage(article.getArticleName() + " wurde aus dem Warenkorb entfernt");
	}
	
	public String showShoppingCart() {
		return "shoppingCart";
	}
	
	// damit können wir dann gleich anzeigen, das ein Artikel 
	// hinzugefügt oder entfernt wurde
	public void addMessage(String summary) {
		// aktuelle FacesContext Instance besorgen
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
	}

}
