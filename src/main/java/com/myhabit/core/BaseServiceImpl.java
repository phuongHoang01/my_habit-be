package com.myhabit.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.myhabit.common.helper.SendDataOrConvertDataFromClient;
import com.myhabit.common.helper.SendDataOrConvertDataFromClientImpl;

public abstract class BaseServiceImpl<E extends BaseEntity>
										implements BaseService<E> {
	private BaseRepository<E> repository;
	
	public BaseServiceImpl(BaseRepository<E> repository) {
		this.repository = repository;
	}

	public List<E> findAllByActive(boolean active) {
		return this.repository.findAllByActive(active);
	}
	
	public Optional<E> findByIdAndActive(String id, boolean active) {
		final Optional<E> found = this.repository.findByIdAndActive(id, active);
		
		if(found.isPresent()) {
			return found;
		}
		
		return Optional.empty();
	}
	
}
