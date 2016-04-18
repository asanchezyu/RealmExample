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

package com.asy.realmexample.interactors.car.get;

import com.asy.realmexample.model.CarDomain;
import com.asy.realmexample.repository.CarRepository;
import com.asy.realmexample.threads.InteractorExecutor;
import com.asy.realmexample.threads.MainThread;

import java.util.List;

import javax.inject.Inject;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class GetAllCarsInteractorImpl implements GetAllCarsInteractor {

    private Callback<List<CarDomain>> callback;

    private CarRepository carRepository;

    private MainThread mainThread;

    private InteractorExecutor interactorExecutor;

    @Inject
    public GetAllCarsInteractorImpl(CarRepository carRepository, MainThread mainThread,
                                    InteractorExecutor interactorExecutor) {

        this.carRepository = carRepository;

        this.mainThread = mainThread;

        this.interactorExecutor = interactorExecutor;

    }

    @Override
    public void run(Callback<List<CarDomain>> callback) {

        this.callback = callback;

        interactorExecutor.executeInteractor( this );

    }

    @Override
    public void execute() {

        mainThread.post(new Runnable() {

            @Override
            public void run() {

                callback.onSuccess( carRepository.findAll() );

            }

        });

    }
}
