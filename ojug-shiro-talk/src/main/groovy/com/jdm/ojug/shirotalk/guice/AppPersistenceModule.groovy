package com.jdm.ojug.shirotalk.guice;

import org.apache.onami.persist.PersistenceModule;

public class AppPersistenceModule extends PersistenceModule {

	@Override
	protected void configurePersistence() {
		bindApplicationManagedPersistenceUnit( "unit" );
	}

}
