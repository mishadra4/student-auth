package com.md.sa.facade.converter;

public interface GenericConverter<E, D> {

    E convertToEntity(D dto);

    D convertToDTO(E entity);

}
