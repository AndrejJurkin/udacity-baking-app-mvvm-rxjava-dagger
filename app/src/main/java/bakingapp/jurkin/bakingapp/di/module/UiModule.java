/*
 *
 *  * Copyright 2017 Andrej Jurkin
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package bakingapp.jurkin.bakingapp.di.module;

import com.squareup.haha.perflib.Main;

import bakingapp.jurkin.bakingapp.MainActivity;
import bakingapp.jurkin.bakingapp.view.recipedetail.RecipeDetailFragment;
import bakingapp.jurkin.bakingapp.view.recipelist.RecipeListFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Andrej Jurkin on 7/16/17.
 */

@Module
public abstract class UiModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract RecipeListFragment contributeRecipeListFragment();

    @ContributesAndroidInjector
    abstract RecipeDetailFragment contributeRecipeDetailFragment();
}
