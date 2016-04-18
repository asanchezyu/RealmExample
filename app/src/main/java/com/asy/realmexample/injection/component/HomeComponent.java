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

package com.asy.realmexample.injection.component;

import android.app.Activity;

import com.asy.realexample.presenters.HomePresenter;
import com.asy.realexample.ui.model.Car;
import com.asy.realmexample.interactors.car.get.GetAllCarsInteractor;
import com.asy.realmexample.interactors.car.insert.InsertCarInteractor;
import com.asy.realmexample.interactors.car.remove.RemoveCarInteractor;
import com.asy.realmexample.model.CarDomain;
import com.asy.realmexample.repository.CarRepository;
import com.asy.realmexample.repository.datasource.CarDbDatasource;
import com.asy.realmexample.repository.model.CarDb;
import com.asy.realmexample.util.Mapper;
import com.asy.realmexample.view.home.HomeActivity;
import com.asy.realmexample.injection.ActivityModule;
import com.asy.realmexample.injection.HomeModule;
import com.asy.realmexample.injection.annotations.ForActivity;
import com.asy.realmexample.view.home.HomeFragment;

import dagger.Component;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
@ForActivity
@Component(
        dependencies = {
            ApplicationComponent.class
        },
        modules = {
                ActivityModule.class,
                HomeModule.class,
        }

)
public interface HomeComponent {

    Activity activityContext();

    void inject(HomeActivity homeActivity);

    void inject(HomeFragment homeFragment);

    HomePresenter presenter();

    GetAllCarsInteractor getAllCarsInteractor();

    InsertCarInteractor insertCarInteractor();

    RemoveCarInteractor removeCarInteractor();

    CarRepository carRepository();

    CarDbDatasource carDbDatasource();

    Mapper<Car, CarDomain> carUiMapper();

    Mapper<CarDomain, CarDb> carDomainMapper();

}
