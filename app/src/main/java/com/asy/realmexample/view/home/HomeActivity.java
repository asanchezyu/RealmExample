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

import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asy.realexample.presenters.HomePresenter;
import com.asy.realexample.ui.model.Car;
import com.asy.realexample.ui.views.HomeView;
import com.asy.realmexample.R;
import com.asy.realmexample.injection.ActivityModule;
import com.asy.realmexample.injection.component.DaggerHomeComponent;
import com.asy.realmexample.injection.component.HomeComponent;
import com.asy.realmexample.view.RealmApplication;

import java.util.List;

import javax.inject.Inject;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public class HomeActivity extends AppCompatActivity{

    private HomeComponent homeComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

    }

    public HomeComponent getComponent() {
        if (homeComponent == null ){

            homeComponent = DaggerHomeComponent.builder()
                    .applicationComponent( ((RealmApplication)getApplication()).getComponent() )
                    .activityModule( new ActivityModule( this ) )
                    .build();

        }

        return homeComponent;
    }
}
