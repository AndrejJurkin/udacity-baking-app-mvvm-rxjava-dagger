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

package bakingapp.jurkin.bakingapp.view.recipedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bakingapp.jurkin.bakingapp.R;
import bakingapp.jurkin.bakingapp.model.Recipe;
import butterknife.ButterKnife;

/**
 * Created by Andrej Jurkin on 7/15/17.
 */

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "arg_recipe_id";

    public static void startActivity(Context context, Recipe recipe) {
        Intent i = new Intent(context, RecipeDetailActivity.class);
        i.putExtra(EXTRA_RECIPE_ID, recipe.getId());
        context.startActivity(i);
    }

    private RecipeDetailFragment recipeDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        this.recipeDetailFragment = (RecipeDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_recipe_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int recipeId = getIntent().getIntExtra(EXTRA_RECIPE_ID, -1);
        this.recipeDetailFragment.bindViewModel(recipeId);
    }
}
