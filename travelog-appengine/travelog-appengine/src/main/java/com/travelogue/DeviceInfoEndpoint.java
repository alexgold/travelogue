package com.travelogue;

import com.travelogue.entities.DeviceInfo;
import com.travelogue.services.db.DeviceInfoSBService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

@Api(name = "deviceinfoendpoint", namespace = @ApiNamespace(ownerDomain = "test.com", ownerName = "test.com", packagePath = ""))
public class DeviceInfoEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@ApiMethod(name = "listDeviceInfo")
	public CollectionResponse<DeviceInfo> listDeviceInfo(
			@Nullable @Named("cursor") int offset,
			@Nullable @Named("limit") int limit) {

		DeviceInfoSBService mgr = new DeviceInfoSBService();
		List<DeviceInfo> result = mgr.load(offset, limit);

		return CollectionResponse.<DeviceInfo> builder().setItems(result)
				.setNextPageToken(""+offset+limit).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getDeviceInfo")
	public DeviceInfo getDeviceInfo(@Named("id") Long id) {
		DeviceInfoSBService mgr = new DeviceInfoSBService();
		return mgr.load(id);
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param deviceinfo the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertDeviceInfo")
	public DeviceInfo insertDeviceInfo(DeviceInfo deviceinfo) {
		DeviceInfoSBService mgr = new DeviceInfoSBService();
		if(mgr.load(deviceinfo.getDeviceRegistrationID()) == null)
		{
			mgr.save(deviceinfo);
		}
		return deviceinfo;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param deviceinfo the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateDeviceInfo")
	public DeviceInfo updateDeviceInfo(DeviceInfo deviceinfo) {
		DeviceInfoSBService mgr = new DeviceInfoSBService();
		DeviceInfo old = mgr.load(deviceinfo.getDeviceRegistrationID());
		if(old != null)
		{
			old.setFields(deviceinfo);
		}
		return deviceinfo;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeDeviceInfo")
	public void removeDeviceInfo(@Named("id") String deviceRegistrationID) {
		DeviceInfoSBService mgr = new DeviceInfoSBService();
		mgr.delete(mgr.load(deviceRegistrationID));
	}
}
