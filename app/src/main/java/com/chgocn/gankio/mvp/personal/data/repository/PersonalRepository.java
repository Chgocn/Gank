package com.chgocn.gankio.mvp.personal.data.repository;

import javax.inject.Inject;

/**
 * Created by chgocn.
 */
public class PersonalRepository {

    @Inject
    PersonalDataStoreFactory mFactory;

    @Inject
    public PersonalRepository() {
    }

    // TODO: Add methods to connect with mFactory
}