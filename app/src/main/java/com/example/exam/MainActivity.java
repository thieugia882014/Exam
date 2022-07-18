package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam.adapter.EmployeeAdapter;
import com.example.exam.database.AppDatabase;
import com.example.exam.database.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edName,edDes, edSalary;
    Button addBtn,updatebtn,deletebtn;
    AppDatabase db;
    RecyclerView rv;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= AppDatabase.getAppDatabase(this);
        List<Employee> list = db.empoyyeeDao().getAll();

        EmployeeAdapter adapter = new EmployeeAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv = findViewById(R.id.rvUser);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
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
            mes = "chưa nhập des";
        }
        if (mes != null) {
            Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//    public void getInfoEmployee(int id) {
//        employee = db.empoyyeeDao().findById(id);
//        edName.setText(employee.getName());
//        edDes.setText(employee.getDes());
//        edSalary.setText(employee.getSalary());
//    }
    private void add(){
        if (!validate()){
            return;
        }
        Employee employee = new Employee();
        long id=db.empoyyeeDao().inserUser(employee);
        if (id>0){
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        }
    }

    private void delete() {
        if (employee == null) {
            Toast.makeText(this, "No Employee", Toast.LENGTH_SHORT).show();

        }
        if (db.empoyyeeDao().delete(employee) > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            employee = null;
        }
    }

    private void update() {
        if (employee == null) {
            Toast.makeText(this, "No Employee", Toast.LENGTH_SHORT).show();

        }
        if (db.empoyyeeDao().update(employee) > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            employee = null;
        }
    }

    public void reset(int id) {
        employee = db.empoyyeeDao().findById(id);
        edName.setText("");
        edDes.setText("");
        edSalary.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addBtn:
                add();
                break;
            case R.id.updatebtn:
                update();
                break;
            case R.id.deletebtn:
                delete();
                break;
            default:
                break;
        }
    }
}