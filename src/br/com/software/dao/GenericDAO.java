package br.com.software.dao;

import java.util.List;

public interface GenericDAO<T> {

	int save(T clazz) throws Exception;
	
	void update(T clazz, int id) throws Exception;
	
	void delete(int id) throws Exception;
	
	T findById(int id) throws Exception;
	
	List<T> findAll() throws Exception;

}
