/*
 * Copyright 2022 Joel Kanyi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kanyideveloper.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.kanyideveloper.core.domain.HomeRepository
import com.kanyideveloper.core_database.dao.MealDao
import com.kanyideveloper.core_database.dao.OnlineMealsDao
import com.kanyideveloper.core_network.MealDbApi
import com.kanyideveloper.data.repository.HomeRepositoryImpl
import com.kanyideveloper.data.repository.OnlineMealsRepositoryImpl
import com.kanyideveloper.domain.repository.OnlineMealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        mealDao: MealDao,
        databaseReference: DatabaseReference,
        firebaseAuth: FirebaseAuth
    ): HomeRepository {
        return HomeRepositoryImpl(
            mealDao = mealDao,
            databaseReference = databaseReference,
            firebaseAuth = firebaseAuth
        )
    }

    @Provides
    @Singleton
    fun provideOnlineMealsRepository(mealDbApi: MealDbApi, onlineMealsDao: OnlineMealsDao): OnlineMealsRepository {
        return OnlineMealsRepositoryImpl(mealDbApi = mealDbApi, onlineMealsDao = onlineMealsDao)
    }
}
