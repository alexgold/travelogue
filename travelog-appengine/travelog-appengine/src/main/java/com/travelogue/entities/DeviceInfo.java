package com.travelogue.entities;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * An entity for Android device information.
 * 
 * Its associated endpoint, DeviceInfoEndpoint.java, was directly generated from
 * this class - the Google Plugin for Eclipse allows you to generate endpoints
 * directly from entities!
 * 
 * DeviceInfoEndpoint.java will be used for registering devices with this App
 * Engine application. Registered devices will receive messages broadcast by
 * this application over Google Cloud Messaging (GCM). If you'd like to take a
 * look at the broadcasting code, check out MessageEndpoint.java.
 * 
 * For more information, see
 * http://developers.google.com/eclipse/docs/cloud_endpoints.
 * 
 * NOTE: This DeviceInfoEndpoint.java does not use any form of authorization or
 * authentication! If this app is deployed, anyone can access this endpoint! If
 * you'd like to add authentication, take a look at the documentation.
 */
@Entity
// DeviceInfoEndpoint has NO AUTHENTICATION - it is an OPEN ENDPOINT!
public class DeviceInfo {

	/*
	 * The Google Cloud Messaging registration token for the device. This token
	 * indicates that the device is able to receive messages sent via GCM.
	 */
	@Id
	@Getter
	@Setter
	private String deviceRegistrationID;

	/*
	 * Some identifying information about the device, such as its manufacturer
	 * and product name.
	 */
	@Getter
	@Setter
	private String deviceInformation;

	/*
	 * Timestamp indicating when this device registered with the application.
	 */
	@Getter
	@Setter
	private long timestamp;

	public void setFields(DeviceInfo deviceinfo) {
		setDeviceInformation(deviceinfo.getDeviceInformation());
	}
}
