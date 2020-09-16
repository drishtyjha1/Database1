package com.example.database1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

   private EditText etstdname,etstdemail,etstdcontact,etstdyear,etstdcourse;
    private int studentID;
    private DBHelper dbHelper;

    private boolean isUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etstdname=findViewById(R.id.et_stdName);
        etstdemail=findViewById(R.id.et_stdEmail);
        etstdcontact=findViewById(R.id.et_stdContact);
        etstdyear=findViewById(R.id.et_stdYear);
        etstdcourse=findViewById(R.id.et_stdCourse);

     Button  stdDetail = findViewById(R.id.btn_stdDetail);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            isUpdate = data.getBoolean("is_update");

            StudentDetail studentDetail = (StudentDetail) data.getSerializable("Student");

            etstdname.setText(studentDetail.getStudentName());
            etstdcourse.setText(studentDetail.getStudentCourse());
            etstdyear.setText(studentDetail.getStudentYear());
            etstdemail.setText(studentDetail.getStudentEmail());
            etstdcontact.setText(studentDetail.getStudentContact());

            studentID = studentDetail.getStudentID();



        }


        dbHelper = new DBHelper(MainActivity.this);
    }



    public void onEnterClicked(View view) {
        String studentName = etstdname.getText().toString();
        String studentContact = etstdcontact.getText().toString();
        String studentEmail = etstdemail.getText().toString();
        String studentYear = etstdyear.getText().toString();
        String studentCourse = etstdcourse.getText().toString();

        StudentDetail newStudent = new StudentDetail();

        newStudent.setStudentName(studentName);
        newStudent.setStudentEmail(studentEmail);
        newStudent.setStudentContact(studentContact);
        newStudent.setStudentYear(studentYear);
        newStudent.setStudentCourse(studentCourse);


        etstdname.setText(" ");
        etstdcourse.setText(" ");
        etstdcontact.setText(" ");
        etstdemail.setText(" ");
        etstdyear.setText(" ");

        if (isUpdate) {
            newStudent.setStudentID(studentID);
            dbHelper.updateDataToDatabase(dbHelper.getWritableDatabase(), newStudent);

            setResult(Activity.RESULT_OK);
            finish();
        } else {
            dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(), newStudent);
        }


    }
    public void onViewStudentClicked(View view){
        startActivity(new Intent(MainActivity.this,ViewActivity.class));
    }
}


