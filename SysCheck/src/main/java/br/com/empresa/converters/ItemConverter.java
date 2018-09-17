package br.com.empresa.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.empresa.entidade.Item;

@FacesConverter(value = "ItemConverter", forClass = Item.class)
public class ItemConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (Item) uiComponent.getAttributes().get(value);
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof Item) {
			Item entity = (Item) value;

			if (entity != null && entity instanceof Item && entity.getId() != null) {
				uiComponent.getAttributes().put(entity.getId() + " | " + entity.getNomItem(), entity);

				return entity.getId() + " | " + entity.getNomItem();
			}
		}

		return "";
	}
}
