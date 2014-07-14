/*
 */

package com.travelogue.persist;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import com.google.inject.Injector;
import com.googlecode.objectify.ObjectifyFactory;
import com.travelogue.entities.DeviceInfo;
import com.travelogue.entities.MessageData;

/**
 * Our version of ObjectifyFactory which integrates with Guice.  You could and convenience methods here too.
 *
 */
@Singleton
@Slf4j
public class OfyFactory extends ObjectifyFactory
{
	/** */
	@Inject private static Injector injector;

	/** Register our entity types*/
	public OfyFactory() {
		long time = System.currentTimeMillis();
		this.register(MessageData.class);
		this.register(DeviceInfo.class);
		long millis = System.currentTimeMillis() - time;
		log.debug("Registration took " + millis + " millis");
	}

	/** Use guice to make instances instead! */
	@Override
	public <T> T construct(Class<T> type) {
		return injector.getInstance(type);
	}

	@Override
	public Ofy begin() {
		return new Ofy(this);
	}
}