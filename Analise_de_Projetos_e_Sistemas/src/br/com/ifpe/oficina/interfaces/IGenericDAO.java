package br.com.ifpe.oficina.interfaces;

import java.util.List;
import java.util.function.Predicate;

public interface IGenericDAO<T> {
	void create(T object);
	T read (T object);
	void update (int id ,T object);
	void delete (T object);
	List<T> viewAll();
	T search(Predicate<T> filter);
}
