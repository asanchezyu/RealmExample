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

package com.asy.realmexample.view.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asy.realexample.ui.model.Car;
import com.asy.realmexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;

    private OnCarClickedListener onCarClickedListener;

    public CarAdapter(OnCarClickedListener onCarClickedListener) {
        this.onCarClickedListener = onCarClickedListener;
        this.carList = new ArrayList<>();
    }

    public CarAdapter setCarList(List<Car> carList) {
        this.carList = carList;
        return this;
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_car, null);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        holder.bind(carList.get(position), onCarClickedListener);
    }


    static class CarViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_car_brand)
        TextView tvCarBrand;

        @Bind(R.id.tv_car_model)
        TextView tvCarModel;

        @Bind(R.id.tv_car_platenumber)
        TextView tvCarPlatenumber;

        @Bind(R.id.tv_car_registration_date)
        TextView tvCarRegistrationDate;

        public CarViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind( this, itemView );

        }

        public void bind(final Car car, final OnCarClickedListener onCarClickedListener) {

            tvCarBrand.setText( car.getBrand() );

            tvCarModel.setText( car.getModel() );

            tvCarPlatenumber.setText( car.getPlateNumber() );

            tvCarRegistrationDate.setText( car.getRegistrationDate() );

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCarClickedListener.onCarCliked( car );
                }
            });

        }
    }

}
