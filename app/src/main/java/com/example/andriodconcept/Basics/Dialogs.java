package com.example.andriodconcept.Basics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.andriodconcept.R;

import java.util.Calendar;

public class Dialogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
    }

    public void customdialog(View view) {
        final Dialog d = new Dialog(this);// final because inner class concept,we are using d variable which is declared inside
        // a method in inner class.

        d.setTitle("MESSAGE");
        d.setContentView(R.layout.customdialoglayout);
        Button yes = d.findViewById(R.id.yes);
        Button no = d.findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    // alert dialog is builtin dia;og,which has limitation it has only 3 buttons,positive,negative,neutral.and we cant show vidoes etc.
    //Based on requirement we have to choose in between alert and custom dialog.
    public void alertdialog(View view) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("MESSAGE");
        b.setMessage("ARE YOU SURE WANT TO EXIT?");
        b.setIcon(R.drawable.google_icon);
        b.setCancelable(false);// so if we click outside it will not disappear.
        AlertDialog.OnClickListener listener = new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == Dialog.BUTTON_POSITIVE) {
                    dialog.dismiss();
                    finish();
                } else if (which == Dialog.BUTTON_NEGATIVE) {
                    dialog.dismiss();
                } else if (which == Dialog.BUTTON_NEUTRAL) {
                    Toast.makeText(getApplicationContext(), "ITS A NEUTRAL BUTTON", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        };
  //     you can use this in place of listener for below three line buttons and write logic separetely.
 /*  DialogInterface.OnClickListener listener1=     new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };*/
        b.setPositiveButton("YES", listener);
        b.setNegativeButton("NO", listener);
        b.setNeutralButton("NEUTRAL", listener);
        b.show();
    }

    public void datepick(View view) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), year + "-" + (month + 1) + "-" + dayOfMonth, Toast.LENGTH_LONG).show();
            }
        };
        //adding 1 to month because by default it will show 1 less than actual.
        final Calendar calen = Calendar.getInstance();
        int mYear = calen.get(Calendar.YEAR);
        int mMonth = calen.get(Calendar.MONTH);
        int nDay = calen.get(Calendar.DAY_OF_MONTH);
// using calender class make us to put default date as current date
        DatePickerDialog dp = new DatePickerDialog(this,android.R.style.Theme_Holo_Dialog, listener, mYear, mMonth, nDay);
        dp.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dp.show();

    }

    public void timepick(View view) {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(getApplicationContext(), hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
            }
        };
        final Calendar cd = Calendar.getInstance();
        int nHour = cd.get(Calendar.HOUR_OF_DAY);
        int nMinute = cd.get(Calendar.MINUTE);
// using calender class make us to put default date as current time
        TimePickerDialog tp = new TimePickerDialog(this, listener, nHour, nMinute, false);
        tp.show();

    }

    public void indeterminate(View view) {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("MESSAGE");
        pd.setMessage("PLEASE WAIT.......");
        pd.setIcon(R.drawable.google_icon);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);// ideterminate
        pd.show();
    }

    ProgressDialog pd;
    Handler h;
    Runnable run;
    public void determinate(View view) {
        pd = new ProgressDialog(this);
        pd.setTitle("MESSAGE");
        pd.setMessage("PLEASE WAIT.......");
        pd.setIcon(R.drawable.google_icon);
        pd.setMax(100);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// detereminate
        pd.show();
          h = new Handler();
          run = new Runnable() {
            @Override
            public void run() {
                update();
            }
        };
        h.postDelayed(run,1000); //recursive process

    }

    public void update() {

            try {
                    pd.incrementProgressBy(1);
                    if (pd.getProgress() == pd.getMax()) {
                        pd.dismiss();
                    }
                h.postDelayed(run,1000);

            } catch (Exception e) {
                e.printStackTrace();
            }


    }
}
