package br.com.empresa.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.empresa.entidade.Equipamento;

@FacesConverter(value = "EquipamentoConverter", forClass = Equipamento.class)
public class EquipamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (Equipamento) uiComponent.getAttributes().get(value);
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof Equipamento) {
			Equipamento entity = (Equipamento) value;

			if (entity != null && entity instanceof Equipamento && entity.getId() != null) {
				uiComponent.getAttributes().put(entity.getId() + " | " + entity.getNomEquipamento(), entity);

				return entity.getId() + " | " + entity.getNomEquipamento();
			}
		}

		return "";
	}

}
