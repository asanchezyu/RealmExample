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

package com.asy.realmexample.repository;

import com.asy.realmexample.model.CarDomain;
import com.asy.realmexample.repository.datasource.CarDbDatasource;
import com.asy.realmexample.repository.exceptions.RepositoryException;
import com.asy.realmexample.repository.util.CarDomainMapper;

import java.util.List;

import javax.inject.Inject;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class CarRepositoryImpl implements CarRepository {

    private CarDomainMapper carDomainMapper;

    private CarDbDatasource carDbDatasource;

    @Inject
    public CarRepositoryImpl(CarDomainMapper carDomainMapper, CarDbDatasource carDbDatasource) {

        this.carDomainMapper = carDomainMapper;

        this.carDbDatasource = carDbDatasource;

    }

    @Override
    public List<CarDomain> findAll() {
        return carDomainMapper.mapList(carDbDatasource.findAll());
    }

    @Override
    public CarDomain insert(CarDomain element) throws RepositoryException {
        return carDomainMapper.map(carDbDatasource.insert(carDomainMapper.reverseMap(element)));
    }

    @Override
    public List<CarDomain> insertAll(List<CarDomain> elementList) throws RepositoryException {
        return carDomainMapper.mapList(carDbDatasource.insertAll(carDomainMapper.reverseMapList(elementList)));
    }

    @Override
    public void remove(CarDomain element) {

        carDbDatasource.remove(carDomainMapper.reverseMap(element));

    }

    @Override
    public void removeAll() {

        carDbDatasource.removeAll();

    }
}
