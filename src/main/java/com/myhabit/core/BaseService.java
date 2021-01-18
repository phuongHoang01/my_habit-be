package com.myhabit.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<E extends BaseEntity> {
	public List<E> findAllByActive(boolean active);
	public Optional<E> findByIdAndActive(String id, boolean active);
}
