package de.professional_webworkx.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.professional_webworkx.annotations.ArticleNumber;
import de.professional_webworkx.business.ArticleService;
import de.professional_webworkx.model.Article;

@Model
public class ArticleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6136276561074918491L;

	private String articleNumber;
	private int articleCount;
	private String articleName;
	private String articleDescription;
	
	private Article selectedArticle;
	private List<Article> articles = new ArrayList<Article>();
	

	// EJB injezieren lassen
	@Inject
	ArticleService articleService;
	
	// Default Constructor
	public ArticleBean() {
		super();
	}
	
	// diese Methode wird beim Erstellen der Instanz der Bean ausgeführt
	@PostConstruct
	public void init() {
		// alle in der Datenbank befindlichen Artikel in die Liste laden
		articles = articleService.getArticles();
	}
	
	@ArticleNumber(regex="[A-Z]{2}-[0-9]{1,10}$", message="Format: BB-ZZZZZZZZZZ")
	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	// Mindestanzahl eines Artikels = 1
	@Min(1)
	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	
	// Artikelname min 3 und max 30 Zeichen lang
	// Groß- und Kleinbuchstaben sowie Ziffern 0-9 und - _ und Leerzeichen dürfen auch enthalten sein
	// Längenvorgabe 3 - 30
	// er darf nicht leer sein
	@NotNull(message="Geben Sie einen Artikelnamen ein!")
	@Pattern(regexp="[a-zA-Z0-9-_\\s+]{3,30}$", message="Artikelname entspricht nicht der Vorgabe")
	@Size(min=3, max=30, message="Artikelname ist zu lang!")
	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleDescription() {
		return articleDescription;
	}

	public void setArticleDescription(String articleDescription) {
			this.articleDescription = articleDescription;
	}
	
	public Article getSelectedArticle() {
		return selectedArticle;
	}

	public void setSelectedArticle(Article selectedArticle) {
		this.selectedArticle = selectedArticle;
	}

	/**
	 * Liefert eine Liste aller verfügbaren Artikel 
	 * @return List<Article>
	 */
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Article> complete(String query) {
		List<Article> suggestions = new ArrayList<Article>();
		for(Article article : articles) {
			if(article.getArticleName().startsWith(query) || article.getArticleDescription().startsWith(query)) {
				suggestions.add(article);
			}
		}
		
		return suggestions;
	}
	
	public void save() {
		Article article = new Article();
		// articleNumber am neu zu erstellenden Artikel setzen 
		article.setArticleNumber(articleNumber);
		article.setArticleCount(articleCount);
		article.setArticleDescription(articleDescription);
		article.setArticleName(articleName);
		
		// Article an die EJB senden
		articleService.save(article);
		
		addMessage("Artikel erfolgreich angelegt");
	}
	
	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
