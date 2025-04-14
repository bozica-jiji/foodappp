package com.example.foodapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {
    private ImageView dishImage, restaurantImage;
    private TextView dishName, recipeDetails, calorieCount, restaurantName;
    private Button orderLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Initializing UI elements
        dishImage = findViewById(R.id.dishImage);
        restaurantImage = findViewById(R.id.restaurantImage);
        dishName = findViewById(R.id.dishName);
        recipeDetails = findViewById(R.id.recipeDetails);
        calorieCount = findViewById(R.id.calorieCount);
        restaurantName = findViewById(R.id.restaurantName);
        orderLink = findViewById(R.id.orderButton);

        // Get selected ingredients from Intent
        Intent intent = getIntent();
        ArrayList<String> selectedIngredients = intent.getStringArrayListExtra("selectedIngredients");

        // Display the matched recipe
        displayRecipe(selectedIngredients);
    }

    private void displayRecipe(ArrayList<String> ingredients) {
        Map<ArrayList<String>, Recipe> recipeMap = new HashMap<>();

        // Adding predefined recipes with restaurant details
        recipeMap.put(new ArrayList<>(Arrays.asList("Tomato", "Rice")),
                new Recipe("Tomato Rice",
                        "✨ **Create it Yourself** ✨\n\n" +
                                "1️⃣ Sauté Spices – Heat oil, add mustard seeds, cumin, and onions. Sauté until golden.\n" +
                                "2️⃣ Cook Tomatoes – Add ginger-garlic paste, chopped tomatoes, turmeric, chili powder, and salt. Cook until soft.\n" +
                                "3️⃣ Mix Rice – Add cooked rice, mix well, and cook for 2-3 min.\n" +
                                "4️⃣ Garnish & Serve – Sprinkle fresh coriander and serve hot!\n\n",
                        R.drawable.tomato_rice, 250,
                        "Dosa Company", R.drawable.dosa_company,
                        "https://www.zomato.com/ncr/dosa-company-mg-road-new-delhi/order"));

        recipeMap.put(new ArrayList<>(Arrays.asList("Butter", "Chicken")),
                new Recipe("Butter Chicken",
                        "✨ **Create it Yourself** ✨\n\n" +
                                "1️⃣ Marinate Chicken – Mix yogurt, lemon juice, chili powder, salt, and ginger-garlic paste. Marinate for 30 min.\n" +
                                "2️⃣ Cook Chicken – Sauté marinated chicken in oil until golden brown.\n" +
                                "3️⃣ Make Sauce – Melt butter, cook tomato purée, add spices, fresh cream, and mix.\n" +
                                "4️⃣ Combine & Simmer – Add chicken, sprinkle kasuri methi, and cook for 5-10 min.\n" +
                                "5️⃣ Serve Hot – Garnish with cream & coriander. Enjoy with naan or rice!\n\n",
                        R.drawable.butter_chicken, 450,
                        "Gulati Restaurant", R.drawable.gulati_restaurant,
                        "http://gulatirestaurant.in"));

        recipeMap.put(new ArrayList<>(Arrays.asList("Egg", "Rice")),
                new Recipe("Egg Fried Rice",
                        "✨ **Create it Yourself** ✨\n\n" +
                                "1️⃣ Scramble Eggs – Heat oil, add beaten eggs, and scramble. Set aside.\n" +
                                "2️⃣ Sauté Veggies – In the same pan, sauté onions and veggies until soft.\n" +
                                "3️⃣ Mix Rice & Seasoning – Add cooked rice, soy sauce, salt, and pepper. Stir well.\n" +
                                "4️⃣ Add Eggs & Serve – Mix in scrambled eggs, cook for 2 min, and serve hot!\n\n",
                        R.drawable.egg_fried_rice, 350,
                        "The Broken Rice", R.drawable.broken_rice,
                        "https://www.zomato.com/ncr/the-broken-rice-1-palam-new-delhi/order"));

        recipeMap.put(new ArrayList<>(Arrays.asList("Potato", "Tomato", "Carrot", "Garlic")),
                new Recipe("Mix Veg",
                        "✨ **Create it Yourself** ✨\n\n" +
                                "1️⃣ Sauté Garlic & Spices – Heat oil, add cumin seeds and garlic. Sauté for a few seconds.\n" +
                                "2️⃣ Add Veggies – Add potato, carrot, tomato, and stir well.\n" +
                                "3️⃣ Season & Cook – Add turmeric, chili powder, salt, and ½ cup water. Cover and cook until veggies are soft.\n" +
                                "4️⃣ Garnish & Serve – Sprinkle garam masala, mix well, and serve hot!\n\n",
                        R.drawable.mix_veg, 200,
                        "Punjabi Angithi", R.drawable.punjabi_angithi,
                        "https://www.punjabiangithi.in/"));

        // Search for a matching recipe
        for (Map.Entry<ArrayList<String>, Recipe> entry : recipeMap.entrySet()) {
            if (ingredients.containsAll(entry.getKey()) && entry.getKey().containsAll(ingredients)) {
                Recipe recipe = entry.getValue();

                dishName.setText(recipe.name);
                recipeDetails.setText(recipe.recipeText);
                dishImage.setImageResource(recipe.imageResource);
                calorieCount.setText("Calories: " + recipe.calories);
                restaurantName.setText("Best Place to Eat: " + recipe.restaurant);
                restaurantImage.setImageResource(recipe.restaurantImage);

                // Set up the order button
                orderLink.setText("Order Now");
                orderLink.setOnClickListener(v -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.orderUrl));
                    startActivity(browserIntent);
                });
                return;
            }
        }

        // If no exact match found
        dishName.setText("No Exact Match Found");
        recipeDetails.setText("Try selecting different ingredients.");
        dishImage.setImageResource(R.drawable.no_image);
        calorieCount.setText("Calories: N/A");
        restaurantName.setText("");
        orderLink.setText("");
        restaurantImage.setImageResource(R.drawable.no_image);
    }

    // Helper class for recipes
    static class Recipe {
        String name, recipeText, restaurant, orderUrl;
        int imageResource, calories, restaurantImage;

        Recipe(String name, String recipeText, int imageResource, int calories,
               String restaurant, int restaurantImage, String orderUrl) {
            this.name = name;
            this.recipeText = recipeText;
            this.imageResource = imageResource;
            this.calories = calories;
            this.restaurant = restaurant;
            this.restaurantImage = restaurantImage;
            this.orderUrl = orderUrl;
        }
    }
}

