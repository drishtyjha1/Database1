package com.example.database1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements StudentDetailAdapter.StudentClickListener {
    private DBHelper dbHelper;
    private RecyclerView mRcStudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mRcStudentList =findViewById(R.id.rc_student_data);

        mRcStudentList.setLayoutManager(new LinearLayoutManager(ViewActivity.this,RecyclerView.VERTICAL,false));

       // mRcStudentList.setLayoutManager(new GridLayoutManager(ViewActivity.this,4));
       // mRcStudentList.setLayoutManager(new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL));
      //  StudentDetailAdapter adapter =new StudentDetailAdapter(ViewActivity.this,new ArrayList<StudentDetail>());

        dbHelper = new DBHelper(ViewActivity.this);
     loadDataToDatabase();
    }
    private void loadDataToDatabase()
    {  ArrayList<StudentDetail>details = dbHelper.getDataFromDatabase(dbHelper.getWritableDatabase());

        StudentDetailAdapter adapter =new StudentDetailAdapter(ViewActivity.this,details);
        adapter.setListener(this);
        mRcStudentList.setAdapter(adapter);}
    @Override
    public void onUpdateClicked(StudentDetail studentDetail) {

    }

    @Override
    public void onDeleteClicked(StudentDetail studentDetail) {
    dbHelper.deleteDataFromDatabase(dbHelper.getWritableDatabase(),studentDetail);
    loadDataToDatabase();
    }
}