package com.vaibhav.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TypeOfDoctors extends AppCompatActivity {

    ArrayList<Types> types;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_doctors);

        types = new ArrayList<>();
        //add
        types.add(new Types("Cardiologist", "A cardiologist is a doctor that deals with the cardiovascular system. This means he or she treats any abnormality in our blood vessels and heart. This can include heart disease or condition which requires diagnosis and treatment."));
        types.add(new Types("Dentist", "According to American Dental Association, a dentist is a doctor of oral health. Oral health includes teeth, tongue and gums. A dentist is known to diagnose and treat issues of these three areas."));
        types.add(new Types("Audiologist", "As the name suggests, an audiologist treats and evaluates anything and everything to do with audio or hearing abilities of a person. Since hearing is a very important sense, it requires an expert to treat the same."));
        types.add(new Types("ENT specialist", "ENT stands for ear, nose and throat. A specialist who treats and diagnoses the issues and troubles of these three areas. Also known as an otolaryngologist, an ENT specialist is a physician to trained to treat the disorders of ENT."));
        types.add(new Types("Gynaecologist", "A gynaecologist is trained to treat the female reproductive system which includes the vagina, uterus, ovaries and breasts."));
        types.add(new Types("Orthopaedic surgeon", "An orthopaedic surgeon is known to deal with issues relating to the musculoskeletal system. This means muscles and bones. Any fracture, pain or abnormality of these areas need to be consulted about with an orthopaedic surgeon."));
        types.add(new Types("Psychiatrists", "Mental health is a vast field which requires our uttermost attention. Therefore, to treat what goes inside a human brain is difficult, due to the uncertainty of it. A psychiatrist helps treat and diagnose issues of mental health."));
        types.add(new Types("Paediatrician", "Paediatricians are doctors who treat children. Since a child’s body functions in a different manner from ours, due to many factors like age and growing stages, their illness and health issues are different from an adult. A paediatrician helps in mental behaviour issues and physical health problems."));
        types.add(new Types("Neurologist", "As the name suggests, a neurologist is responsible for treating and diagnosing issues of the nervous system. Our nervous system includes our brain, spinal cord, sensory organs, and all the nerves."));
        types.add(new Types("Pulmonologist", "Pulmonary means lungs, hence a doctor who treats lungs. Since the list of abnormalities and issues relating to lungs is long in modern times, a pulmonologist helps diagnose and treat common issues like lung cancer"));
        types.add(new Types("Veterinarian", "After the uniqueness of mental health, comes the issue of our furr buddies: animals. Treatment and diagnosis of issues in animals is done by a veterinarian. This includes mental and physical both as well."));
        types.add(new Types("Radiologist","A radiologist for diagnosing diseases and internal & external injuries with the help of imaging techniques like x-rays, CT scan, MRI and ultrasound etc. They are the first step towards the diagnosis of any sort, which cannot be done without a machine"));
        types.add(new Types("Oncologist", "Oncology involves the study of all types of cancers. This involves the radiation, medical and surgical. Oncologists can specialise in one type of cancer as well as the field is vast."));
        types.add(new Types("Cardiothoracic surgeon", "Thorax means the chest. A cardiothoracic surgeon treats conditions of the heart, lungs, oesophagus and other organs in the chest."));
        types.add(new Types("Endocrinologist", "An endocrinologist is responsible for treating the endocrine system which includes the pituitary gland, pancreas, ovaries, thyroid, hypothalamus etc. they help in treating diabetes, hyperthyroidism etc."));
        recyclerView = findViewById(R.id.typeOfDoctors);

        setAdapter(types);
    }

    void setAdapter(ArrayList<Types> arrayList) {
        AdapterType adapterType = new AdapterType(TypeOfDoctors.this, R.layout.card_type_holder, arrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(TypeOfDoctors.this);
        try {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager1);
            recyclerView.setAdapter(adapterType);
        } catch (Exception e) {

        }
    }
}