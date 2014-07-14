package com.travelogue.services.db;
import static com.travelogue.persist.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

@Slf4j
public class DBService {
	public <E> E save(E object)
	{
		ofy().save().entity(object).now();
		return object;
	}

	public <E> Result<Map<Key<E>, E>> storeList(List<E> list)
	{
		return save(list);
	}
	
	public <E> Result<Map<Key<E>, E>> save(List<E> list)
	{
		return ofy().save().entities(list);
	}
	
	public <E> Map<Key<E>, E> saveNow(List<E> list)
	{
		return ofy().save().entities(list).now();
	}
	
	public <E> Key<E> createKey(E object)
	{
		return ofy().createKey(object);
	}
	
	public <E> List<E> load(List<Key<E>> keys)
	{
		return new ArrayList<E>(ofy().load().keys(keys).values());
	}
	
	public <E> E load(Key<E> key)
	{
		return ofy().load().key(key).now();
	}

	public <E> void delete(E object)
	{
		if(object!=null)
			ofy().delete().entity(object);
	}
	
	public <E> void deleteAll(Class<E> type)
	{
		ofy().delete().keys(ofy().load().type(type).keys());
	}
	
	public <E> void deleteAllNow(Class<E> type)
	{
		ofy().delete().keys(ofy().load().type(type).keys()).now();
	}
}
