package de.professional_webworkx.presentation;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

// entspricht @Named und @RequestScoped
@Model
public class NavigationsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuItem item;
	private MenuModel menuModel;
	
	public NavigationsBean() {
		menuModel = initMenu();
	}

	private MenuModel initMenu() {
		
		menuModel = new DefaultMenuModel();
		
		// neues Menuitem erzeuugen
		item = new MenuItem();
		item.setValue("Startseite");
		item.setIcon("ui-icon-home");
		item.setOutcome("index");
		
		menuModel.addMenuItem(item);
		
		Submenu submenu = new Submenu();
		submenu.setLabel("Artikel");
		
		item = new MenuItem();
		item.setValue("Artikel erstellen");
		item.setOutcome("article");
		item.setIcon("ui-icon-plusthick");
		
		submenu.getChildren().add(item);
		
		item = new MenuItem();
		item.setValue("Artikel listen");
		item.setOutcome("listArticles");
		
		submenu.getChildren().add(item);
		
		// wie aus z.B. Swing gewohnt, fügen wir dem MenuModel über die entsprechenden
		// addXXX Methoden die Items / Submenus hinzu
		menuModel.addSubmenu(submenu);
		
		return menuModel;
	}
	
	public MenuModel getMenuModel() {
		return menuModel;
	}
}
