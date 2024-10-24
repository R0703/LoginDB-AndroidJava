package com.example.persistencetemplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.persistencetemplate.database.DBStudentManager;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText addNameET = findViewById(R.id.addNameET);
        EditText addNimET = findViewById(R.id.addNimET);
        Button addSubmitBtn = findViewById(R.id.addSubmitButton);

        addSubmitBtn.setOnClickListener(v->{
            String name = addNameET.getText().toString();
            String nim = addNimET.getText().toString();

            DBStudentManager dbManager = new DBStudentManager(this);
            dbManager.open();
            dbManager.addStudent(name, nim);
            dbManager.close();

            Intent back = new Intent(this, HomeActivity.class);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //flag activity clear top sama aja kek finish. bole aja pake finish kok (clear stack sblm)
            startActivity(back);
        });
    }
}