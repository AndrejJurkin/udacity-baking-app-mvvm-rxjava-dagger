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

package bakingapp.jurkin.bakingapp.repository;

import java.util.List;

import javax.inject.Inject;

import bakingapp.jurkin.bakingapp.data.RecipeService;
import bakingapp.jurkin.bakingapp.model.Recipe;
import rx.Observable;

/**
 * Created by andrej on 12/07/17.
 */

public class RecipeRemoteDataSource {

    private final RecipeService recipeService;

    @Inject
    public RecipeRemoteDataSource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public Observable<List<Recipe>> getRecipes() {
        return null;
    }
}
