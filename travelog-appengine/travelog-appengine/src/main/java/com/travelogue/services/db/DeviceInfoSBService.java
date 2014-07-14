package com.travelogue.services.db;

import static com.travelogue.persist.OfyService.ofy;

import java.util.List;

import com.travelogue.entities.DeviceInfo;

public class DeviceInfoSBService extends DBService{
	public DeviceInfo load(String deviceRegistrationID)
	{
		return ofy().load().type(DeviceInfo.class).filter("deviceRegistrationID",deviceRegistrationID).first().now();
	}
	public DeviceInfo load(Long id)
	{
		return ofy().load().type(DeviceInfo.class).id(id).now();
	}
	public List<DeviceInfo> load(int offset,int limit)
	{
		return ofy().load().type(DeviceInfo.class).limit(limit).offset(offset).list();
	}
}
