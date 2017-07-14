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

package bakingapp.jurkin.bakingapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import bakingapp.jurkin.bakingapp.repository.RecipeLocalDataSource;
import bakingapp.jurkin.bakingapp.repository.RecipeRemoteDataSource;
import bakingapp.jurkin.bakingapp.view.recipelist.RecipeListViewModel;

/**
 * Created by andrej on 14/07/17.
 */

public class RecipeViewModelFactory implements ViewModelProvider.Factory {

    private RecipeRemoteDataSource remote;
    private RecipeLocalDataSource local;

    @Inject
    public RecipeViewModelFactory(RecipeRemoteDataSource remote, RecipeLocalDataSource local) {
        this.remote = remote;
        this.local = local;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipeListViewModel.class)) {
            return (T) new RecipeListViewModel(mDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
