package com.vaibhav.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MedicalMyths extends AppCompatActivity {

    ArrayList<String> myths;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_myths);

        myths = new ArrayList<>();
        //add
        myths.add("Egg Yolks Are Bad for You :\n" +
                "Maybe because people only got to know the health benefits of egg yolk only recently, but egg yolk is recommended for everyone unless allergic, even people with heart disease as it is loaded with HDL which is a good cholesterol and actually counteracts the effects of bad cholesterol.");
        myths.add("Cholesterol is Bad:\n" +
                "The overall amount of cholesterol in your blood (AKA ‘total cholesterol’) isn’t nearly as important as how much of each kind you have in your blood. While way too much LDL cholesterol as compared with HDL may be associated with an increased risk of heart disease, it is absolutely not the only thing to consider for heart health.");
        myths.add("Coffee Can Stunt Childhood Development:\n" +
                "it would be relatively insignificant and could be stymied by maintaining regular calcium consumption. That’s not to say kids should start downing the stuff (the caffeine would still have them bouncing off the walls). But it’s not going to stunt their growth.");
        myths.add("Carrots Give You Night Vision:\n" +
                "It would be awesome if this was true, but while carrots are good for your sight, they aren’t that good.The benefits of carrots come in the form of carotene, which the body uses to make vitamin A, Vitamin A enables the eye to convert light into a signal that can be sent to the brain, allowing for overall improved vision in settings with reduced light. The only catch is that Vitamin A is a fat-soluble vitamin, which means it needs to be consumed with fat to allow for absorption and for true health benefits to be felt. Most studies have found ingesting vitamin A supplements have proven to be more effective that just consuming large amounts of carrots.");
        myths.add("Energy Drinks Contain Special Alertness-Boosting Ingredients:\n" +
                "Despite containing a variety of vitamins and extraneous substances, these products actually exert their influence with that self-same ingredient, i.e., caffeine. Our advice: Stick to coffee. Forget the ‘energy drinks.’ They are a waste of money.");
        myths.add("A “Detox” is the Best Way to Jumpstart a Change in Diet:\n" +
                "Most regiments used for a typical detox dehydrate the body and can cause bowel issues like diarrhea so the weight loss you see within a few days is typically just from the loss of water… really the opposite of what you want to do for overall health.");
        myths.add("Eating Before Bed Makes You Overweight:\n" +
                "if you’re feeling hungry before bed, don’t starve yourself—have a small protein-packed snack (like a protein shake) in the evenings, which could potentially increase your metabolism overall.\n" +
                "What you want to avoid is over eating for the day and eating junk food, period—we just happen to eat more junk food in the evenings.");
        myths.add("Starve a Fever, Feed a Cold:\n" +
                "With rare exception, one of the best things to do when you have a fever is to maintain a regular diet as best as you can, \n" +
                "Even though you may not feel like eating, your body actually requires more calories when you’re sick so that it can heal properly and quickly.");
        myths.add("Yogurt is a Health Food:\n" +
                "Sure, some yogurt is packed with healthy bacteria that can create positive health benefits. But plenty of others are packed with far more sugar and high-fructose corn syrup that counters any potential health benefits.");
        myths.add("Gluten is Bad:\n" +
                "With the gluten-free lifestyle becoming mainstream, you might get the impression that gluten is terrible for you, or at least a way to keep your weight down. Not true, it turns out.\n" +
                "The gluten-free diet is only healthier for people with gluten-related disorders, such as celiac or gluten intolerance,Individuals who have celiac disease require a gluten-free diet because gluten causes an adverse reaction in the body which damages the intestines and can lead to serious health problems.\n" +
                "The overall food choices one makes within the diet, whether it’s gluten-free or not, are what is important.");
        recyclerView = findViewById(R.id.myths);

        setAdapter(myths);
    }

    void setAdapter(ArrayList<String> arrayList) {
        AdapterMyths adapterMyths = new AdapterMyths(MedicalMyths.this, R.layout.card_myth_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MedicalMyths.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterMyths);
        } catch (Exception e) {

        }
    }
}