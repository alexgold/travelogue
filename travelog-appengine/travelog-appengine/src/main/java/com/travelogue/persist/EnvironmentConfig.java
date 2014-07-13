package com.travelogue.persist;

import lombok.Getter;
import lombok.Setter;

import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.utils.SystemProperty;

public class EnvironmentConfig{
	private static final String ENVIRONMENT_APPLICATION_DEVELOPMENT = "genieo-installer-download-dev";
	@Getter
	@Setter
	private InstallerEnvironment environment;
	
	private static EnvironmentConfig instance = null;
	@Getter
	@Setter
	private Long version;
	@Getter
	@Setter
	private Boolean isProductionVersion;
	@Getter
	@Setter
	private long defaultGenieoAppId;
	
	public static synchronized EnvironmentConfig getInstance()
    {
    	if (instance == null)
    		instance = new EnvironmentConfig();
    	return instance;
    }
	
	private EnvironmentConfig()
    {
		String applicationAccountName = AppIdentityServiceFactory.getAppIdentityService().getServiceAccountName().split("@")[0];
		if(applicationAccountName.equals(ENVIRONMENT_APPLICATION_DEVELOPMENT))
		{
			setEnvironment(InstallerEnvironment.DEVELOPMENT);
			setDefaultGenieoAppId(3993023);
		}
		else
		{
			setEnvironment(InstallerEnvironment.PRODUCTION);
			setDefaultGenieoAppId(14770001);
		}
		//TODO: after new appengine version update check if is default version
		setIsProductionVersion(true);
		if(SystemProperty.applicationVersion.get()!=null)
			setVersion((long)Float.parseFloat(SystemProperty.applicationVersion.get()));
		
    }

	public enum InstallerEnvironment{
	    PRODUCTION, DEVELOPMENT 
	}

    public Boolean isProduction(){
    	if(getEnvironment()==InstallerEnvironment.PRODUCTION)
    	{
    		return true;
    	}
    	return false;
    }

    public Boolean isDevelopment(){
    	if(getEnvironment()==InstallerEnvironment.DEVELOPMENT)
    	{
    		return true;
    	}
    	return false;
    }
}
