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

package com.asy.realmexample.interactors.car.insert;

import com.asy.realmexample.interactors.base.BaseInteractor;
import com.asy.realmexample.model.CarDomain;
import com.asy.realmexample.repository.CarRepository;
import com.asy.realmexample.repository.exceptions.RepositoryException;
import com.asy.realmexample.threads.InteractorExecutor;
import com.asy.realmexample.threads.MainThread;

import javax.inject.Inject;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class InsertCarInteractorImpl extends BaseInteractor implements InsertCarInteractor {

    private Callback<CarDomain> callback;

    private CarDomain carDomain;

    private CarRepository carRepository;

    @Inject
    public InsertCarInteractorImpl(CarRepository carRepository, InteractorExecutor interactorExecutor, MainThread mainThread) {

        super(interactorExecutor, mainThread);

        this.carRepository = carRepository;

    }

    @Override
    public void run(CarDomain carDomain, Callback<CarDomain> callback){
        this.carDomain = carDomain;

        this.callback = callback;

        getInteractorExecutor().executeInteractor( this );
    }

    @Override
    public void execute() {

        try{
            final CarDomain returnedValue = carRepository.insert( carDomain );

            getMainThread().post(new Runnable() {
                @Override
                public void run() {

                    callback.onSuccess( returnedValue );

                }

            });

        }catch (final RepositoryException exception){

            getMainThread().post(new Runnable() {

                @Override
                public void run() {

                    callback.onError( exception );

                }

            });

        }



    }
}
