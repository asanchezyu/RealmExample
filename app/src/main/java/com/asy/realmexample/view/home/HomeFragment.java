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

package com.asy.realmexample.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.asy.realexample.presenters.HomePresenter;
import com.asy.realexample.ui.model.Car;
import com.asy.realexample.ui.views.HomeView;
import com.asy.realmexample.R;
import com.asy.realmexample.view.home.adapter.CarAdapter;
import com.asy.realmexample.view.home.adapter.OnCarClickedListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class HomeFragment extends Fragment implements HomeView, OnCarClickedListener {

    @Inject
    HomePresenter homePresenter;

    @Bind(R.id.et_car_brand)
    EditText etCarBrand;

    @Bind(R.id.et_car_model)
    EditText etCarModel;

    @Bind(R.id.et_car_plate_number)
    EditText etCarPlateNumber;

    @Bind(R.id.rv_cars)
    RecyclerView rvCars;

    private CarAdapter carAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        ( ( HomeActivity )getActivity() ).getComponent().inject( this );

        homePresenter.setView( this );

        homePresenter.getAllCars();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        rvCars.setLayoutManager( new LinearLayoutManager( getActivity() ) );

        carAdapter = new CarAdapter( this );

        rvCars.setAdapter( carAdapter );

        return view;

    }

    @Override
    public void setCars(List<Car> carList) {

        carAdapter.setCarList( carList );

        carAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        ButterKnife.unbind(this);

    }

    @OnClick(R.id.bt_insert_car)
    public void onClick() {

        homePresenter.insertCar( new Car.Builder()
                .setBrand(etCarBrand.getText().toString() )
                .setModel(etCarModel.getText().toString() )
                .setPlateNumber(etCarPlateNumber.getText().toString() )
                .build()
        );

    }

    @Override
    public void showGetCarsError() {

        Snackbar.make( getView(), R.string.error_msg_get_cars, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showInsertCarError() {

        Snackbar.make( getView(), R.string.error_msg_insert_car, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showRemoveCarError() {

        Snackbar.make( getView(), R.string.error_msg_remove_car, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showFieldsNeededError() {

        Snackbar.make( getView(), R.string.error_msg_fields_needed, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onCarCliked(Car car) {

        homePresenter.removeCar( car );

    }

}
