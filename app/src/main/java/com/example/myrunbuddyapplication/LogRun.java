package com.example.myrunbuddyapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LogRun extends AppCompatActivity {

    EditText runName, runDate, runTime, runDistance, runBPM, runPace;
    Button insert, delete, update, view, back_btn_one;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_run);

        runName = findViewById(R.id.RunName);
        runDate = findViewById(R.id.RunDate);
        runTime = findViewById(R.id.RunTime);
        runDistance = findViewById(R.id.RunDistance);
        runBPM = findViewById(R.id.RunBPM);
        runPace = findViewById(R.id.RunPace);

        insert = findViewById(R.id.insertButton);
        delete = findViewById(R.id.DeleteRunButton);
        update = findViewById(R.id.UpdateRunsButton);
        view = findViewById(R.id.ViewRunsButton);
        back_btn_one = findViewById(R.id.backButton);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nameTXT = runName.getText().toString();
                String dateTXT = runDate.getText().toString();
                String timeTXT = runTime.getText().toString();
                String distanceTXT = runDistance.getText().toString();
                String bpmTXT = runBPM.getText().toString();
                String paceTXT = runPace.getText().toString();

                Boolean checkinsertdata = DB.insertRunData(nameTXT, dateTXT, timeTXT, distanceTXT, bpmTXT, paceTXT);
                if (checkinsertdata == true) {
                    Toast.makeText(LogRun.this, "Run Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogRun.this, "Run Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nameTXT = runName.getText().toString();
                String dateTXT = runDate.getText().toString();
                String timeTXT = runTime.getText().toString();
                String distanceTXT = runDistance.getText().toString();
                String bpmTXT = runBPM.getText().toString();
                String paceTXT = runPace.getText().toString();

                Boolean checkupdatedata = DB.updateRunData(nameTXT, dateTXT, timeTXT, distanceTXT, bpmTXT, paceTXT);
                if (checkupdatedata == true) {
                    Toast.makeText(LogRun.this, "Run Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogRun.this, "Run Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String nameTXT = runName.getText().toString();

                Boolean checkdeletedata = DB.deleteRunData(nameTXT);
                if (checkdeletedata == true) {
                    Toast.makeText(LogRun.this, "Run Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogRun.this, "Run Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getRunData();
                if (res.getCount() == 0) {
                    Toast.makeText(LogRun.this, "No Data Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Run Name :" + res.getString(0) + "\n");
                    buffer.append("Run Date :" + res.getString(1) + "\n");
                    buffer.append("Run Duration :" + res.getString(2) + "\n");
                    buffer.append("Run Distance :" + res.getString(3) + "\n");
                    buffer.append("Run BPM :" + res.getString(4) + "\n");
                    buffer.append("Run Pace :" + res.getString(5) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(LogRun.this);
                builder.setCancelable(true);
                builder.setTitle("Run Details");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

        back_btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}