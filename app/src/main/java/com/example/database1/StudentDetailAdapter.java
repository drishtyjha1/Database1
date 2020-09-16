package com.example.database1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentDetailAdapter extends RecyclerView.Adapter<StudentDetailAdapter.StudentDetailHolder> {
    private ArrayList<StudentDetail> studentDetails;
    private Context context;
    private StudentClickListener listener;

    public StudentDetailAdapter(Context context, ArrayList<StudentDetail> studentDetails) {
        this.context = context;
        this.studentDetails = studentDetails;
    }
    public void setListener(StudentClickListener listener){
        this.listener=listener;
    }
    @NonNull
    @Override
    public StudentDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.cell_student,parent,false);
        return new StudentDetailHolder(view);
    // indrustry level code***#--    return  new StudentDetailHolder(LayoutInflater.from(context).inflate(R.layout.cell_student,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentDetailHolder holder, int position){
        final StudentDetail currentStudentDetail = studentDetails.get(position);

        holder.tvstdName.setText(currentStudentDetail.getStudentName());
        holder.tvstdEmail.setText(currentStudentDetail.getStudentEmail());
        holder.tvstdContact.setText(currentStudentDetail.getStudentContact());
        holder.tvstdYear.setText(currentStudentDetail.getStudentYear());
        holder.tvstdCourse.setText(currentStudentDetail.getStudentCourse());

        holder.mivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)
                {
                    listener.onDeleteClicked(currentStudentDetail);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentDetails.size();
    }

       class StudentDetailHolder extends RecyclerView.ViewHolder{

        private TextView tvstdName;
        private TextView tvstdEmail;
        private TextView tvstdContact;
        private TextView tvstdYear;
        private TextView tvstdCourse;

        private ImageView mivEdit;
        private ImageView mivDelete;

        public StudentDetailHolder(View itemView){
            super(itemView);

            tvstdName =itemView.findViewById(R.id.tv_student_name);
            tvstdEmail=itemView.findViewById(R.id.tv_student_email);
            tvstdContact=itemView.findViewById(R.id.tv_student_contact);
            tvstdYear=itemView.findViewById(R.id.tv_year);
            tvstdCourse=itemView.findViewById(R.id.tv_student_course);

            mivEdit=itemView.findViewById(R.id.iv_edit);
            mivDelete=itemView.findViewById(R.id.iv_delete);

        }
    }
    public interface StudentClickListener{
        void onUpdateClicked(StudentDetail studentDetail);
        void onDeleteClicked(StudentDetail studentDetail);
    }
}
