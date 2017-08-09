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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bakingapp.jurkin.bakingapp.R;
import bakingapp.jurkin.bakingapp.model.Recipe;
import bakingapp.jurkin.bakingapp.model.Step;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrej on 07/08/17.
 */

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeViewHolder> {

    private List<Step> data;

    @Inject
    public RecipeStepAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.li_recipe_step, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Step step = this.data.get(position);
        holder.stepDescription.setText(step.getDescription());

        holder.divider.setVisibility(position == data.size() - 1 ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Step> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.step_description)
        TextView stepDescription;

        @BindView(R.id.divider)
        View divider;

        RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

