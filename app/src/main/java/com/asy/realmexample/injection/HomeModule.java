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

import com.asy.realexample.mappers.CarUiMapper;
import com.asy.realexample.presenters.HomePresenter;
import com.asy.realexample.presenters.HomePresenterImpl;
import com.asy.realexample.ui.model.Car;
import com.asy.realmexample.injection.annotations.ForActivity;
import com.asy.realmexample.interactors.car.get.GetAllCarsInteractor;
import com.asy.realmexample.interactors.car.get.GetAllCarsInteractorImpl;
import com.asy.realmexample.interactors.car.insert.InsertCarInteractor;
import com.asy.realmexample.interactors.car.insert.InsertCarInteractorImpl;
import com.asy.realmexample.interactors.car.remove.RemoveCarInteractor;
import com.asy.realmexample.interactors.car.remove.RemoveCarInteractorImpl;
import com.asy.realmexample.model.CarDomain;
import com.asy.realmexample.repository.CarRepository;
import com.asy.realmexample.repository.CarRepositoryImpl;
import com.asy.realmexample.repository.datasource.CarDbDatasource;
import com.asy.realmexample.repository.datasource.CarDbDatasourceImpl;
import com.asy.realmexample.repository.model.CarDb;
import com.asy.realmexample.repository.util.CarDomainMapper;
import com.asy.realmexample.util.Mapper;

import dagger.Module;
import dagger.Provides;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
@Module
public class HomeModule {

    @Provides
    @ForActivity
    public HomePresenter providesPresenter(HomePresenterImpl homePresenter){
        return homePresenter;
    }

    @Provides
    @ForActivity
    public GetAllCarsInteractor providesGetAllCarsInteractor(GetAllCarsInteractorImpl getAllCarsInteractor){
        return  getAllCarsInteractor;
    }

    @Provides
    @ForActivity
    public InsertCarInteractor providesInsertCarInteractor(InsertCarInteractorImpl insertCarInteractor){

        return  insertCarInteractor;

    }

    @Provides
    @ForActivity
    public RemoveCarInteractor providesRemoveCarInteractor(RemoveCarInteractorImpl removeCarInteractor){
        return removeCarInteractor;
    }

    @Provides
    @ForActivity
    public CarRepository providesCarRepository(CarRepositoryImpl carRepository){
        return carRepository;
    }

    @Provides
    @ForActivity
    public CarDbDatasource providesCarDbDatasource(CarDbDatasourceImpl carDbDatasource){
        return carDbDatasource;
    }

    @Provides
    @ForActivity
    public Mapper<Car, CarDomain> providesCarUiMapper(CarUiMapper carUiMapper){
        return carUiMapper;
    }

    @Provides
    @ForActivity
    public Mapper<CarDomain, CarDb> provideCarDomainMapper(CarDomainMapper carDomainMapper){
        return carDomainMapper;
    }

}
