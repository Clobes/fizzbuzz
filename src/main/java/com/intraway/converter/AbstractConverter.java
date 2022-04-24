package com.intraway.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.intraway.util.Constants;

public abstract class AbstractConverter<E, D> {

	public abstract D fromEntity(E entity);
	public abstract E fromDTO(D dto);

	public List<D> fromEntity(List<E> entities){
		if(Objects.isNull(entities )) {
			throw new IllegalArgumentException(Constants.CONVERTER_ERROR);
		}
		return entities.stream()
			.map(e -> fromEntity(e))
			.collect(Collectors.toList());
	}

}
