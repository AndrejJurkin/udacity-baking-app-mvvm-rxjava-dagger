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

package bakingapp.jurkin.bakingapp.view.recipelist;


import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import bakingapp.jurkin.bakingapp.R;
import bakingapp.jurkin.bakingapp.model.Recipe;
import bakingapp.jurkin.bakingapp.repository.RecipeRepository;
import bakingapp.jurkin.bakingapp.view.ViewModelFactory;
import bakingapp.jurkin.bakingapp.view.recipedetail.RecipeDetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Andrej Jurkin on 7/9/17.
 */

public class RecipeListFragment extends LifecycleFragment implements RecipeCardAdapter.OnRecipeClickListener {
    private static final String TAG = "RecipeListFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private RecipeCardAdapter adapter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        this.adapter = new RecipeCardAdapter();
        this.adapter.setOnRecipeClickListener(this);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false);

        this.recyclerView.setLayoutManager(lm);
        this.recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecipeListViewModel viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(RecipeListViewModel.class);

        viewModel.getRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(recipes -> {
                    Log.d(TAG, "Recipe list updated");
                    this.adapter.setData(recipes);
                }, throwable ->
                        Log.e(TAG, "Failed to update recipe list: " + throwable));
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        RecipeDetailActivity.startActivity(getActivity(), recipe);
    }
}
