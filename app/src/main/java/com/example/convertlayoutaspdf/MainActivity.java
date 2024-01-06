package com.example.convertlayoutaspdf;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PdfConverter pdfConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // পরিচয় করিয়ে দেওয়া হয়েছে ।
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        String[][] data = {
                {"1", "John", "123"},
                {"2", "Jane", "456"},
                {"3", "Doe", "789"},
                {"4", "dj", "784"}
        };

        // Add data rows dynamically
        for (String[] row : data) {
            addRowToTable(tableLayout, row);
        }

        // পরিচয় করিয়ে দেওয়া হয়েছে ।
        pdfConverter = new PdfConverter();
        pdfConverter.initializePdfConverter(MainActivity.this);

        // FAB button এর onClick লেখা হয়েছে ।
        findViewById(R.id.btnPrint).setOnClickListener(v -> {
           pdfConverter.createPdf(MainActivity.this,R.id.tableLayout);
        });


    } // onCreate method end here ===================

    private void addRowToTable(TableLayout tableLayout, String[] rowData) {
        // Create a new TableRow
        TableRow tableRow = new TableRow(this);

        // Add TextViews for each column
        for (String columnData : rowData) {
            TextView textView = new TextView(this);
            textView.setText(columnData);
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setPadding(8, 8, 8, 8);

            // Add TextView to TableRow
            tableRow.addView(textView);
        }

        // Add TableRow to TableLayout
        tableLayout.addView(tableRow);
    }

} // public class end here ==========================