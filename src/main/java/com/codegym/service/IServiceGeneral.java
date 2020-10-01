package com.codegym.service;

import java.util.Optional;

public interface IServiceGeneral<T> {
	Optional<T> findById(Long id);
	Iterable<T> findAll();
	void save(T entity);
	void deleteById(Long id);
}
