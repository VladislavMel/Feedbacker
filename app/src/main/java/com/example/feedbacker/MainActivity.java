package com.example.feedbacker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.feedbacker.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    TextView id;
    TextView name;
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = binding.IdTextView;
        name = binding.NameTextView;
        address = binding.AddressTextView;

        OrganizationParser op = new OrganizationParser();
        ArrayList<Organization> organizations;
        try {
            organizations = op.getOrganizations();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        id.setText(organizations.get(0).getId());
        address.setText(organizations.get(0).getAddress());
        name.setText(organizations.get(0).getName());
    }
}
