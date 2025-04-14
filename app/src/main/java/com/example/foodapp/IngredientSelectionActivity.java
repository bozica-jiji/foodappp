package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class IngredientSelectionActivity extends AppCompatActivity {
    private ArrayList<String> selectedIngredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_selection);

        // Find Recipe button click listener
        Button findRecipeButton = findViewById(R.id.findRecipeButton);
        findRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectSelectedIngredients();
                if (selectedIngredients.isEmpty()) {
                    Toast.makeText(IngredientSelectionActivity.this, "Please select at least one ingredient", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(IngredientSelectionActivity.this, ResultsActivity.class);
                    intent.putStringArrayListExtra("selectedIngredients", selectedIngredients);
                    startActivity(intent);
                }
            }
        });
    }

    // Collect selected ingredients from checkboxes
    private void collectSelectedIngredients() {
        selectedIngredients.clear();
        addIngredientIfChecked(R.id.check_rice, "Rice");
        addIngredientIfChecked(R.id.check_tomato, "Tomato");
        addIngredientIfChecked(R.id.check_egg, "Egg");
        addIngredientIfChecked(R.id.check_cheese, "Cheese");
        addIngredientIfChecked(R.id.check_garlic, "Garlic");
        addIngredientIfChecked(R.id.check_lettuce, "Lettuce");
        addIngredientIfChecked(R.id.check_potato, "Potato");
        addIngredientIfChecked(R.id.check_chicken, "Chicken");
        addIngredientIfChecked(R.id.check_butter, "Butter");
        addIngredientIfChecked(R.id.check_carrot, "Carrot");
    }

    // Helper function to check if a checkbox is selected and add the ingredient
    private void addIngredientIfChecked(int checkBoxId, String ingredient) {
        CheckBox checkBox = findViewById(checkBoxId);
        if (checkBox.isChecked()) {
            selectedIngredients.add(ingredient);
        }
    }
}




