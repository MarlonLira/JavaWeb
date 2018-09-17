package br.com.empresa.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.empresa.entidade.CheckList;

@FacesConverter(value = "CheckListConverter", forClass = CheckList.class)
public class CheckListConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (CheckList) uiComponent.getAttributes().get(value);
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof CheckList) {
			CheckList entity = (CheckList) value;

			if (entity != null && entity instanceof CheckList && entity.getId() != null) {
				uiComponent.getAttributes().put(entity.getId() + " | " + entity.getNomCheckList(), entity);

				return entity.getId() + " | " + entity.getNomCheckList();
			}
		}

		return "";
	}
}
