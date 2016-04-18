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

package com.asy.realexample.presenters;

import com.asy.realexample.mappers.CarUiMapper;
import com.asy.realexample.ui.model.Car;
import com.asy.realexample.ui.views.HomeView;
import com.asy.realmexample.interactors.base.Interactor;
import com.asy.realmexample.interactors.car.get.GetAllCarsInteractor;
import com.asy.realmexample.interactors.car.insert.InsertCarInteractor;
import com.asy.realmexample.interactors.car.remove.RemoveCarInteractor;
import com.asy.realmexample.model.CarDomain;

import java.util.List;

import javax.inject.Inject;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;

    private GetAllCarsInteractor getAllCarsInteractor;

    private InsertCarInteractor insertCarInteractor;

    private RemoveCarInteractor removeCarInteractor;

    private CarUiMapper carUiMapper;

    @Inject
    public HomePresenterImpl(InsertCarInteractor insertCarInteractor, GetAllCarsInteractor
            getAllCarsInteractor, RemoveCarInteractor removeCarInteractor, CarUiMapper carUiMapper) {

        this.insertCarInteractor = insertCarInteractor;

        this.getAllCarsInteractor = getAllCarsInteractor;

        this.removeCarInteractor = removeCarInteractor;

        this.carUiMapper = carUiMapper;

    }

    @Override
    public void getAllCars() {

        getAllCarsInteractor.run(new Interactor.Callback<List<CarDomain>>() {
            @Override
            public void onSuccess(List<CarDomain> object) {

                homeView.setCars(carUiMapper.mapList(object));

            }

            @Override
            public void onError(Throwable throwable) {

                homeView.showGetCarsError();

            }
        });


    }

    @Override
    public void setView(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void insertCar(Car car) {

        if (car.getPlateNumber().isEmpty() || car.getBrand().isEmpty() || car.getModel().isEmpty() ) {

            homeView.showFieldsNeededError();

        } else {

            insertCarInteractor.run(

                    carUiMapper.reverseMap(car),

                    new Interactor.Callback<CarDomain>() {
                        @Override
                        public void onSuccess(CarDomain object) {

                            getAllCars();

                        }

                        @Override
                        public void onError(Throwable throwable) {

                            homeView.showInsertCarError();

                        }
                    });
        }

    }

    @Override
    public void removeCar(Car car) {

        removeCarInteractor.run(carUiMapper.reverseMap(car), new Interactor.Callback<Boolean>() {

            @Override
            public void onSuccess(Boolean object) {

                getAllCars();

            }

            @Override
            public void onError(Throwable throwable) {

                homeView.showRemoveCarError();

            }
        });

    }
}
