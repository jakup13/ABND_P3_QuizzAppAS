package com.example.android.abndp3quizzappas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Jakub
 * @date 18 03 14
 */

public class SecondActivity extends AppCompatActivity {
    // Variables
    public int totalScore = 0;
    int totalFinal = 0;
    int flag = 0;
    EditText charNameField;
    EditText nameField;
    RadioGroup[] radioGroup = new RadioGroup[3];
    CheckBox checkBoxOne, checkBoxTwo, checkBoxThree;
    private View warn1;
    private View warn2;
    private View warn3;
    private View warn4;
    private View warn5;
    private View warn6;
    Button reset;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Initializing the views
        radioGroup[0] = (RadioGroup) findViewById(R.id.radio_group1);
        radioGroup[1] = (RadioGroup) findViewById(R.id.radio_group2);
        radioGroup[2] = (RadioGroup) findViewById(R.id.radio_group3);
        checkBoxOne = (CheckBox) findViewById(R.id.q2_sel1_dealer);
        checkBoxTwo = (CheckBox) findViewById(R.id.q2_sel2_translator);
        checkBoxThree = (CheckBox) findViewById(R.id.q2_sel3_driver);
        charNameField = (EditText) findViewById(R.id.character_name_field);
        nameField = (EditText) findViewById(R.id.name_field);
        warn1 = (TextView) findViewById(R.id.warn1);
        warn2 = (TextView) findViewById(R.id.warn2);
        warn3 = (TextView) findViewById(R.id.warn3);
        warn4 = (TextView) findViewById(R.id.warn4);
        warn5 = (TextView) findViewById(R.id.warn5);
        warn6 = (TextView) findViewById(R.id.warn6);
        reset = (Button) findViewById(R.id.reset_button);
        submit = (Button) findViewById(R.id.submit_button);
    }

    /**
     * Method called when the Reset button is clicked - going back to Main activity
     */
    public void reset(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Disable Back button and display info
        String backNotAllowed = getString(R.string.back_not_allowed);
        Toast.makeText(SecondActivity.this, backNotAllowed, Toast.LENGTH_SHORT).show();
    }

    /**
     * Checking answers in the radio groups by groups Ids
     */
    public void radioQuest(int id) {
        // Is the button now checked?
        // Check which radio button was clicked
        switch (id) {
            case R.id.q1_radio3_sopot:
            case R.id.q3_radio3_fantasy:
            case R.id.q4_radio2_witcher:
                totalScore++;
                break;
            default:
        }
    }

    /**
     * Checking question two answers - checkboxes
     */
    public void checkQuest2() {
        if (checkBoxOne.isChecked() && checkBoxTwo.isChecked() && !checkBoxThree.isChecked()) {
            totalScore++;
        }
    }


    /**
     * Method called when the submit button is clicked
     * display hidden warnings when not all questions have been answered,
     * otherwise it displays a toast with the result
     *
     * @param view displays final score
     */

    public void submitAnswers(View view) {
        EditText characternameEditText = (EditText) findViewById(R.id.character_name_field);
        String characterName = characternameEditText.getText().toString().toLowerCase();
        if (radioGroup[0].getCheckedRadioButtonId() == -1) {
            warn1.setVisibility(View.VISIBLE);
        } else {
            warn1.setVisibility(View.GONE);
        }
        if (radioGroup[1].getCheckedRadioButtonId() == -1) {
            warn3.setVisibility(View.VISIBLE);
        } else {
            warn3.setVisibility(View.GONE);
        }
        if (radioGroup[2].getCheckedRadioButtonId() == -1) {
            warn4.setVisibility(View.VISIBLE);
        } else {
            warn4.setVisibility(View.GONE);
        }
        if (!checkBoxOne.isChecked() && !checkBoxTwo.isChecked() && !checkBoxThree.isChecked()) {
            warn2.setVisibility(View.VISIBLE);
        } else if (checkBoxOne.isChecked() || checkBoxTwo.isChecked() || checkBoxThree.isChecked()) {
            warn2.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(characternameEditText.getText())) {
            warn5.setVisibility(View.VISIBLE);
            return;
        }
        String geralt = getString(R.string.Geralt);
        if (geralt.equals(characterName)) {
            totalScore++;
        }
        if (!TextUtils.isEmpty(characternameEditText.getText())) {
            warn5.setVisibility(View.GONE);
        }
        for (int i = 0; i < 3; i++) {
            radioQuest(radioGroup[i].getCheckedRadioButtonId());
        }
        checkQuest2();
        if (flag == 0) {
            totalFinal = totalScore;
            flag = 1;
        }
        EditText nameEditText = (EditText) findViewById(R.id.name_field);
        String userName = nameEditText.getText().toString();
        if (TextUtils.isEmpty(nameEditText.getText())) {
            warn6.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(nameEditText.getText())) {
            warn6.setVisibility(View.GONE);
        }
        String strScMessage = getString(R.string.scoreMessage);
        String strOfTotalScore = getString(R.string.of_total_score);
        // checkin if everything is checked / marked / filled and if so - displays the result
        @SuppressLint("ResourceType") boolean allRight = ((radioGroup[0].getCheckedRadioButtonId() > -1)
                && (radioGroup[1].getCheckedRadioButtonId() > -1)
                && (radioGroup[2].getCheckedRadioButtonId() > -1)
                && (checkBoxOne.isChecked() || checkBoxTwo.isChecked() || checkBoxThree.isChecked())
                && (!TextUtils.isEmpty(characternameEditText.getText()))
                && (!TextUtils.isEmpty(nameEditText.getText())));
        if (allRight) {
            Toast.makeText(SecondActivity.this, userName + "," + " " + strScMessage + " " + totalFinal + " " + strOfTotalScore, Toast.LENGTH_LONG).show();
        }

    }
}
