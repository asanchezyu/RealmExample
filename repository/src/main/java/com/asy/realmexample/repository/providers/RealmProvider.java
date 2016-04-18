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

package com.asy.realmexample.repository.providers;

import android.app.Application;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class RealmProvider implements DbProvider<Realm> {

    @Inject
    public RealmProvider(Application application) {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(application)
                .name("cars.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }


    @Override
    public Realm getDatabase() {

        return Realm.getDefaultInstance();

    }

}
