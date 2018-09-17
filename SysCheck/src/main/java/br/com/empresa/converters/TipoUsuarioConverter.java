package br.com.empresa.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.empresa.entidade.TipoUsuario;

@FacesConverter(value = "TipoUsuarioConverter", forClass = TipoUsuario.class)
public class TipoUsuarioConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (TipoUsuario) uiComponent.getAttributes().get(value);
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof TipoUsuario) {
			TipoUsuario entity = (TipoUsuario) value;

			if (entity != null && entity instanceof TipoUsuario && entity.getId() != null) {
				uiComponent.getAttributes().put(entity.getId() + " | " + entity.getNomTipoUsuario(), entity);

				return entity.getId() + " | " + entity.getNomTipoUsuario();
			}
		}

		return "";
	}
}
