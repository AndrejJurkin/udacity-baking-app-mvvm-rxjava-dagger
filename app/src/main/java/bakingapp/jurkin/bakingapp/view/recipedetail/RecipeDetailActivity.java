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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;

import bakingapp.jurkin.bakingapp.model.Recipe;

/**
 * Created by Andrej Jurkin on 7/15/17.
 */

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String ARG_RECIPE = "arg_recipe";

    public static void startActivity(Context context, Recipe recipe) {
        Intent i = new Intent(context, RecipeDetailActivity.class);
        i.putExtra(ARG_RECIPE, recipe);
        context.startActivity(i);
    }
}
