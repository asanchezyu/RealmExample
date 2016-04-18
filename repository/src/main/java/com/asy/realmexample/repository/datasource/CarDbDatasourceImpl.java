/*
 * Copyright (c) 2016. Alejandro Sánchez.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.asy.realmexample.repository.datasource;

import com.asy.realmexample.repository.exceptions.RepositoryException;
import com.asy.realmexample.repository.model.CarDb;
import com.asy.realmexample.repository.providers.RealmProvider;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class CarDbDatasourceImpl implements CarDbDatasource {

    private RealmProvider realmProvider;

    @Inject
    public CarDbDatasourceImpl(RealmProvider realmProvider) {
        this.realmProvider = realmProvider;
    }

    @Override
    public List<CarDb> findAll() {

        List<CarDb> provinceDbs = getRealm().allObjects(CarDb.class);

        return provinceDbs;

    }

    @Override
    public CarDb insert(CarDb element) throws RepositoryException {

        getRealm().beginTransaction();

        CarDb returnedCarDb = null;

        try {

            returnedCarDb = getRealm().copyToRealm(element);

            getRealm().commitTransaction();

        } catch (Exception e) {

            getRealm().cancelTransaction();

            throw new RepositoryException(e);

        } finally {

            getRealm().close();

        }

        return returnedCarDb;

    }

    @Override
    public List<CarDb> insertAll(List<CarDb> elementList) throws RepositoryException {
        getRealm().beginTransaction();

        List<CarDb> returnedCarsDb = null;

        try {

            returnedCarsDb = getRealm().copyToRealm(elementList);

        } catch (Exception e) {

            throw new RepositoryException(e);

        } finally {

            getRealm().commitTransaction();

            getRealm().close();

        }

        return returnedCarsDb;
    }

    @Override
    public void remove(CarDb element) {

        getRealm().beginTransaction();

        RealmQuery<CarDb> realmQuery = getRealm().where(CarDb.class).equalTo(CarDb.K_CAR_PLATE_NUMBER, element.getPlateNumber());

        RealmResults<CarDb> realmResults = realmQuery.findAll();

        realmResults.clear();

        getRealm().commitTransaction();

        getRealm().close();

    }

    @Override
    public void removeAll() {
        getRealm().beginTransaction();

        getRealm().allObjects(CarDb.class).clear();

        getRealm().commitTransaction();

        getRealm().close();
    }

    private Realm getRealm() {
        return realmProvider.getDatabase();
    }

}
