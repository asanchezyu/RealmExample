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

package com.asy.realmexample.model;

import java.util.Date;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class CarDomain {

    private Date registrationDate;

    private String model;

    private String plateNumber;

    private String brand;

    private CarDomain(Builder builder) {
        this.registrationDate = builder.registrationDate;
        this.model = builder.model;
        this.plateNumber = builder.plateNumber;
        this.brand = builder.brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public static class Builder{

        private Date registrationDate;

        private String model;

        private String plateNumber;

        private String brand;


        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
            return this;
        }

        public Builder setRegistrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }


        public CarDomain build(){
            return new CarDomain(this
            );
        }
    }
}
