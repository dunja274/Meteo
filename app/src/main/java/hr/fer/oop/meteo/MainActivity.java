package hr.fer.oop.meteo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hr.fer.oop.meteo.util.Clock;

public class MainActivity extends AppCompatActivity {

    // TODO(Dino) : New screen for graph drawing
    // TODO(Dino) : List of cities under the buttons
    // Application main entry point
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First date picker
        final TextView date = findViewById(R.id.chose_date_string);
        final Button choseDate = findViewById(R.id.chose_date);

        // Second date picker
        final TextView date2 = findViewById(R.id.chose_date_string2);
        final Button choseDate2 = findViewById(R.id.chose_date2);

        // Range picker ( ON - Range || OFF - Date )
        final Switch rangeOn = findViewById(R.id.range);

        // Clock for the first date so range can be correct
        final Clock clkDate1 = new Clock();

        // Listener for click on the Date #1
        choseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Clock clk = new Clock();

                // Dialog with calendar for the first date
                final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                date.setText(String.format("%s.%s.%s", dayOfMonth, month, year));
                                choseDate2.setEnabled(true);
                                clkDate1.setDate(year, month, dayOfMonth);  // Remember the ddte #1
                            }
                        }, clk.getYear(), clk.getMonth(), clk.getDay());
                datePickerDialog.getDatePicker().setMaxDate(clk.getDateInMilis());
                datePickerDialog.show();    // Show calendar dialog
            }
        });

        // Same listener for Date #2
        choseDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Clock clk = new Clock();

                final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                date2.setText(String.format("%s.%s.%s", dayOfMonth, month, year));
                            }
                        }, clk.getYear(), clk.getMonth(), clk.getDay());
                // Set Min date so the second date needs to be newer
                datePickerDialog.getDatePicker().setMinDate(clkDate1.getDateInMilis());
                datePickerDialog.getDatePicker().setMaxDate(clk.getDateInMilis());
                datePickerDialog.show();
            }
        });

        // Listener for switch in the corner
        rangeOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Switch (ON - range is picked, OFF - only one date is picked)
                if (isChecked) {
                    date2.setVisibility(View.VISIBLE);
                    choseDate2.setVisibility(View.VISIBLE);
                } else {
                    date2.setVisibility(View.INVISIBLE);
                    choseDate2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
