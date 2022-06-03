package si.wolf.planea.controllers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import si.wolf.planea.database.DatabaseHelper;
import si.wolf.planea.HelperUtils.HelperUtilities;
import si.wolf.planea.R;
import si.wolf.planea.models.Client;

public class ExtraPersonAddActivity extends AppCompatActivity {

    private int clientID;
    private EditText inputFirstName;
    private EditText inputLastName;
    private EditText inputEmail;
    private EditText inputCreditCard;
    private EditText inputPhone;
    private EditText inputbirthday;

    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_person_add);

        Button addExtra = (Button) findViewById(R.id.btnAddExtraPerson);


        inputFirstName = (EditText) findViewById(R.id.txtFirstName);
        inputLastName = (EditText) findViewById(R.id.txtLastName);
        inputEmail = (EditText) findViewById(R.id.txtEmail);
        inputPhone = (EditText) findViewById(R.id.txtPhone);

        inputbirthday = (EditText) findViewById(R.id.txtbirthday);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateDateBirth();
            }
        };
        inputbirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ExtraPersonAddActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        addExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isValid = isValidUserInput();

                if (isValid) {
                    Client c = new Client(inputFirstName.getText().toString(), inputLastName.getText().toString(), inputPhone.getText().toString(), "", inputbirthday.getText().toString());

                    Intent data = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("myData1", c);
                    data.putExtras(bundle);

                    // Activity finished ok, return the data
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }

    //Bring date dialog
    private void updateDateBirth(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        inputbirthday.setText(dateFormat.format(myCalendar.getTime()));
    }

    //registers new employee
    /*
    public void registerEmployee() {

        try {


            hospitalDatabaseHelper = new DatabaseHelper(getApplicationContext());
            db = hospitalDatabaseHelper.getWritableDatabase();

            cursor = DatabaseHelper.selectAccount(db, HelperUtilities.filter(inputEmail.getText().toString()));

            isValid = isValidUserInput();


            if (isValid) {

                if (cursor != null && cursor.getCount() > 0) {

                    accountExistsAlert().show();

                } else {

                    DatabaseHelper.insertClient(db,
                            inputFirstName.getText().toString(),
                            inputLastName.getText().toString(),
                            inputPhone.getText().toString(),
                            inputbirthday.getText().toString(),
                            inputCreditCard.getText().toString());

                    cursor = DatabaseHelper.selectClientID(db,
                            inputFirstName.getText().toString(),
                            inputLastName.getText().toString(),
                            inputPhone.getText().toString(),
                            inputCreditCard.getText().toString());

                    if (cursor != null && cursor.getCount() == 1) {
                        cursor.moveToFirst();

                        DatabaseHelper.insertAccount(db,
                                inputEmail.getText().toString(),
                                inputPassword.getText().toString(),
                                cursor.getInt(0));

                        registrationSuccessDialog().show();
                    }

                }

            }


        } catch (SQLiteException ex) {
            Toast.makeText(getApplicationContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
        }

    }


    public Dialog registrationSuccessDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your profile created successfully! ")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        return builder.create();
    }*/

    //validates user input
    public boolean isValidUserInput() {
        if (HelperUtilities.isEmptyOrNull(inputFirstName.getText().toString())) {
            inputFirstName.setError("Vpišite svoje ime");
            return false;
        } else if (!HelperUtilities.isString(inputFirstName.getText().toString())) {
            inputFirstName.setError("Vpišite svoje veljavno ime");
            return false;
        }

        if (HelperUtilities.isEmptyOrNull(inputLastName.getText().toString())) {
            inputLastName.setError("Vpišite svoj priimek");
            return false;
        } else if (!HelperUtilities.isString(inputLastName.getText().toString())) {
            inputLastName.setError("Vpišite svoj veljaven priimek");
            return false;
        }

        if (HelperUtilities.isEmptyOrNull(inputEmail.getText().toString())) {
            inputEmail.setError("Vpišite svoj E-Mail");
            return false;
        } else if (!HelperUtilities.isValidEmail(inputEmail.getText().toString())) {
            inputEmail.setError("Vpišite veljaven E-Mail");
            return false;
        }

        if (HelperUtilities.isEmptyOrNull(inputPhone.getText().toString())) {
            inputPhone.setError("Vpišite Telefon");
            return false;
        } else if (!HelperUtilities.isValidPhone(inputPhone.getText().toString())) {
            inputPhone.setError("Vpišite veljaven telefon");
            return false;
        }

        return true;

    }
}
