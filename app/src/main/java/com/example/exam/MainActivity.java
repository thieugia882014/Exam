package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exam.adapter.EmployeeAdapter;
import com.example.exam.database.AppDatabase;
import com.example.exam.database.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edName,edDes, edSalary;
    Button addBtn,updatebtn,deletebtn;
    AppDatabase db;
    RecyclerView rvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= AppDatabase.getAppDatabase(this);
        List<Employee> list = db.empoyyeeDao().getAllUser();

        EmployeeAdapter adapter = new EmployeeAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvUser = findViewById(R.id.rvUser);
        rvUser.setLayoutManager(layoutManager);
        rvUser.setAdapter(adapter);
        edName = findViewById(R.id.edName);
        edDes = findViewById(R.id.edDes);
        edSalary = findViewById(R.id.edSalary);
        addBtn = findViewById(R.id.addBtn);
        updatebtn = findViewById(R.id.updatebtn);
        deletebtn = findViewById(R.id.deletebtn);
        addBtn.setOnClickListener(this);
        updatebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);




    }

    private boolean validate() {
        String mes = null;
        if (edName.getText().toString().trim().isEmpty()) {
            mes = "chưa nhập name";
        } else if (edDes.getText().toString().trim().isEmpty()) {
            mes = "chưa nhập giới thiệu";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void onRegister(){
        if (!validate()){
            return;
        }
        Employee employee = new Employee();
        employee.name=edName.getText().toString();
        employee.des=edDes.getText().toString();
//        employee.salary=edSalary.getText().insert();
        long id=db.empoyyeeDao().inserUser(employee);
        if (id>0){
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addBtn:onRegister();
                break;
            default:break;
        }

    }
}