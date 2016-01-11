package kr.co.kj_studio.exbuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import kr.co.kj_studio.exbuddy.R;
import kr.co.kj_studio.exbuddy.dataClass.UserData;
import kr.co.kj_studio.exbuddy.utils.ContextUtil;
import kr.co.kj_studio.exbuddy.utils.ServerUtil;

import org.json.JSONObject;

public class SignUp3Activity extends BaseActivity {

    private Button nextBtn;
    private RadioButton manBtn;
    private RadioButton womanBtn;
    private RadioButton otherBtn;
    private Spinner ageSpinner;
    private EditText specialityEdt;
    private EditText concernEdt;
    private EditText spokenLanguageEdt;
    private EditText belongEdt;
    private EditText profileLineEdt;
    private EditText aboutMeEdt;

    UserData mUserData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);


        mUserData = ContextUtil.getUserData(SignUp3Activity.this);

        setCustomActionbar();
        bindViews();
        setValues(R.string.signup3_title);
        setupEvents();
    }

    @Override
    public void setValues(int title) {
        super.setValues(title);

        if (mUserData.gender.equals("M")) {
            manBtn.setChecked(true);
        }
        else if (mUserData.gender.equals("F")) {

            womanBtn.setChecked(true);
        }
        else if (mUserData.gender.equals("Other")) {

            otherBtn.setChecked(true);
        }

        specialityEdt.setText(mUserData.speciality);
        spokenLanguageEdt.setText(mUserData.spokenLanguage);
        belongEdt.setText(mUserData.belong);
        profileLineEdt.setText(mUserData.profileLine);
        aboutMeEdt.setText(mUserData.aboutMe);




    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.nextBtn = (Button) findViewById(R.id.nextBtn);
        this.aboutMeEdt = (EditText) findViewById(R.id.aboutMeEdt);
        this.profileLineEdt = (EditText) findViewById(R.id.profileLineEdt);
        this.belongEdt = (EditText) findViewById(R.id.belongEdt);
        this.spokenLanguageEdt = (EditText) findViewById(R.id.spokenLanguageEdt);
        this.concernEdt = (EditText) findViewById(R.id.concernEdt);
        this.specialityEdt = (EditText) findViewById(R.id.specialityEdt);
        this.ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        this.otherBtn = (RadioButton) findViewById(R.id.otherBtn);
        this.womanBtn = (RadioButton) findViewById(R.id.womanBtn);
        this.manBtn = (RadioButton) findViewById(R.id.manBtn);

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeUserData();
                ServerUtil.registerUser(SignUp3Activity.this, mUserData, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        ContextUtil.setUserData(SignUp3Activity.this, mUserData);
                        Intent mIntent = new Intent(getApplicationContext(), SignUp4Activity.class);
                        startActivity(mIntent);
                    }
                });
            }
        });
    }

    private void makeUserData() {
        if (manBtn.isChecked()) {
            mUserData.gender = "M";
        }
        else if (womanBtn.isChecked()) {
            mUserData.gender = "F";
        }
        else if (otherBtn.isChecked()) {
            mUserData.gender = "Other";
        }
        else {
            mUserData.gender = "";
        }

        mUserData.age = (ageSpinner.getSelectedItemPosition() +1) * 10;
        mUserData.speciality = specialityEdt.getText().toString();
        mUserData.spokenLanguage = spokenLanguageEdt.getText().toString();
        mUserData.belong = belongEdt.getText().toString();
        mUserData.profileLine = profileLineEdt.getText().toString();
        mUserData.aboutMe = aboutMeEdt.getText().toString();
    }
}
