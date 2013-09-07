package de.professional_webworkx.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.professional_webworkx.model.Article;

// dem Converter geben wir noch einen Namen über die Annotation, darüber greifen wir 
// nachher aus der JSF Seite auf den Converter zu
@FacesConverter(value="articleConverter")
public class ArticleConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 * 
	 * durch implementieren des Interfaces Converter überschreiben wir und 
	 * auch zwei Methoden
	 * Die erste gibt ein Object zurück, die zweite einen String
	 */
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if(submittedValue.trim() == null || submittedValue.trim().isEmpty())
			return null;
		else
		{
			Article article = new Article();
			article.setArticleName(submittedValue);
			return article;
		}
		
	}

	// brauchen wir im Augenblick nicht
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
