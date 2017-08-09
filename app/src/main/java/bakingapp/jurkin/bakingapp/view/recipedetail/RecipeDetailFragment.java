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

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import bakingapp.jurkin.bakingapp.R;
import bakingapp.jurkin.bakingapp.model.Ingredient;
import bakingapp.jurkin.bakingapp.model.Recipe;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by andrej on 28/07/17.
 */

public class RecipeDetailFragment extends Fragment {
    private static final String TAG = "RecipeDetailFragment";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.ingredients)
    TextView ingredientTextView;

    @BindView(R.id.steps_recycler_view)
    RecyclerView stepRecyclerView;

    private RecipeDetailViewModel viewModel;

    private RecipeStepAdapter adapter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, v);

        this.adapter = new RecipeStepAdapter();
        this.stepRecyclerView.setAdapter(adapter);
        this.stepRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.viewModel = this.viewModelFactory.create(RecipeDetailViewModel.class);
    }

    public void bindViewModel(int recipeId) {
        this.viewModel.getRecipe(recipeId).subscribe(r -> {
            Log.d(TAG, "Recipe updated");
            this.ingredientTextView.setText(formatIngredients(r.getIngredients()));
            this.adapter.setData(r.getSteps());
        }, throwable -> Log.e(TAG, "Failed to update recipe: " + throwable));
    }

    // Format ingredients to spanned bullet points
    private Spanned formatIngredients(List<Ingredient> ingredients) {
        StringBuilder sb = new StringBuilder();

        for (Ingredient i : ingredients) {
            sb.append(String.format(Locale.getDefault(),
                    "&#8226; <b>%.2f %s</b> %s<br/><br/>",
                    i.getQuantity(), i.getMeasure(), i.getName()));
        }

        String formatted = sb.toString();

        return Html.fromHtml(formatted.substring(0, formatted.length() - 10));
    }
}
