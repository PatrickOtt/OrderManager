package de.professional_webworkx.presentation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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
	private final double MWST	= 1.19;
	
	
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
	
	// diese Methode gibt uns den Netto-Gesamtpreis zurück
	public BigDecimal getTotalWithoutTax() {
		double total = 0;
		
		// das nette an einer Liste ist, das man sich einen Iterator von ihr besorgen kann und damit
		// einmal durch die gesamte Liste laufen kann und sich die Weter holen kann, die man haben
		// möchte, in diesem Fall also die Einzelpreise
		
		// Shift + Alt + L öffnen diesen Dialog um die Rückgabevariable zu erstellen
		Iterator<Article> itr = shoppingCart.iterator();
		while(itr.hasNext()) {
			// jeder Artikel im Warenkorb wird geholt und der entsprechende Preis wird aufsummiert
			total += itr.next().getArticlePrice();
		}
		return new BigDecimal(total);
	}
	
	// Gesamtpreis Brutto
	public BigDecimal getTotal() {
		return new BigDecimal(getTotalWithoutTax().doubleValue()*MWST);
	}
	// diese Methode leert den Warenkorb komplett
	public void deleteShoppingCart() {
		int articleCount = shoppingCart.size();
		shoppingCart.clear();
		addMessage("Warenkorb wurde geleert - es wurden " + articleCount + " entfernt!");
	}
	
	// damit können wir dann gleich anzeigen, das ein Artikel 
	// hinzugefügt oder entfernt wurde
	public void addMessage(String summary) {
		// aktuelle FacesContext Instance besorgen
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
	}

}
