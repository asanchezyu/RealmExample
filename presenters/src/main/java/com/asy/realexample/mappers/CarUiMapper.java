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

package com.asy.realexample.mappers;

import com.asy.realexample.ui.model.Car;
import com.asy.realmexample.model.CarDomain;
import com.asy.realmexample.util.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class CarUiMapper extends Mapper<Car, CarDomain> {

    private SimpleDateFormat sdf;

    @Inject
    public CarUiMapper() {
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public Car map(CarDomain type) {
        return new Car.Builder()
                .setBrand(type.getBrand())
                .setModel(type.getModel())
                .setRegistrationDate(sdf.format(type.getRegistrationDate()))
                .setPlateNumber(type.getPlateNumber())
                .build();
    }

    @Override
    public CarDomain reverseMap(Car type) {

        CarDomain carDomain = new CarDomain.Builder()
                .setBrand(type.getBrand())
                .setModel(type.getModel())
                .setPlateNumber(type.getPlateNumber())
                .build();

        try {

            if( type.getRegistrationDate() != null ) {

                carDomain.setRegistrationDate(sdf.parse( type.getRegistrationDate() ) );

            }else{

                carDomain.setRegistrationDate( new Date() );

            }

        } catch (ParseException e) {

            e.printStackTrace();

        }


        return carDomain;

    }
}
