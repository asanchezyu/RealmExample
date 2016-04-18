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

package com.asy.realmexample.injection;

import android.app.Application;

import com.asy.realmexample.repository.providers.DbProvider;
import com.asy.realmexample.repository.providers.RealmProvider;
import com.asy.realmexample.threads.InteractorExecutor;
import com.asy.realmexample.threads.MainThread;
import com.asy.realmexample.threads.ThreadExecutor;
import com.asy.realmexample.thread.ApplicationMainThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application getApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public MainThread providesMainThread(ApplicationMainThread mainThread) {
        return mainThread;
    }

    @Provides
    @Singleton
    public InteractorExecutor provideThreadExecutor(ThreadExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    public DbProvider<Realm> providesDbProvider(RealmProvider realmProvider){

        return realmProvider;

    }

}
