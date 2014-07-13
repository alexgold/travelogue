/*
 */

package com.travelogue.persist;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import com.google.inject.Injector;
import com.googlecode.objectify.ObjectifyFactory;

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
		//long time = System.currentTimeMillis();
		//this.register(IMActiveBaseVersion.class);
		//this.register(DownloadCampaign.class);
		//this.register(IMInstaller.class);
		//long millis = System.currentTimeMillis() - time;
		//log.info("Registration took " + millis + " millis");
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