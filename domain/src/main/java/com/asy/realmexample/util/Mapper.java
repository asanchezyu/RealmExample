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

package com.asy.realmexample.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Description.
 *
 * @author asanchezyu@gmail.com.
 * @version 1.0.
 * @since 13/4/16.
 */
public abstract class Mapper<K, T> {

    public abstract K map(T type);

    public abstract T reverseMap(K type);

    public List<K> mapList(List<T> typeList){

        List<K> list = new ArrayList<>();

        for( T type : typeList ){

            list.add( map( type ) );

        }

        return list;

    }

    public List<T> reverseMapList(List<K> typeList){

        List<T> list = new ArrayList<>();

        for( K type : typeList ){

            list.add( reverseMap( type ) );

        }

        return list;

    }

}
