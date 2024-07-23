package br.com.ifpe.oficina.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.ifpe.oficina.interfaces.IGenericDAO;

public class GenericDAO<T> implements IGenericDAO<T> {
	
	List<T> list = new ArrayList<T>();

	@Override
	public void create(T object) {
		list.add(object);
	}

	@Override
	public T read(Predicate<T> filter) {
		return this.list.stream().filter(filter).findFirst().orElse(null);
	}

	@Override
	public void update(int id, T object) {
	}

	@Override
	public void delete(T object) {
		list.remove(object);
	}

	@Override
	public List<T> viewAll() {
		return list;
	}
}
