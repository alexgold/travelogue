package com.travelogue.services.db;
import static com.travelogue.persist.OfyService.ofy;

import java.util.List;

import com.travelogue.entities.MessageData;

public class MessageDBService extends DBService{
	public MessageDBService() {
		super();
	}
	
	public List<MessageData> load(int offset,int limit)
	{
		return ofy().load().type(MessageData.class).limit(limit).offset(offset).list();
	}
}
