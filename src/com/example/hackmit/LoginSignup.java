package com.example.hackmit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignup extends Activity{
	// Declaration of variables
	Button login;
	Button signup;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_signup);
		
		// Find the EditTexts in login_signup.xml
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		
		// Find the buttons in login_signup
		login = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);
		
		// Login Button Click Listener
		login.setOnClickListener(new OnClickListener(){
		
			public void onClick(View arg0) {
	            // Retrieve the text entered from the EditText
	            usernametxt = username.getText().toString();
	            passwordtxt = password.getText().toString();
	
	            // Send data to Parse.com for verification
	            ParseUser.logInInBackground(usernametxt, passwordtxt,
	                    new LogInCallback() {
	                        public void done(ParseUser user, ParseException e) {
	                            if (user != null) {
	                                // If user exist and authenticated, send user to MainActivity.class
	                                Intent intent = new Intent(
	                                        LoginSignup.this,
	                                        MainActivity.class);
	                                startActivity(intent);
	                                Toast.makeText(getApplicationContext(),
	                                        "Successfully Logged in",
	                                        Toast.LENGTH_LONG).show();
	                                finish();
	                            } else {
	                                Toast.makeText(
	                                        getApplicationContext(),
	                                        "No such user exist, please signup",
	                                        Toast.LENGTH_LONG).show();
	                            }
	                        }
	                    });
			}
		});
		// Sign up Button Click Listener
        signup.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
 
                // Force user to fill up the form
                if (usernametxt.equals("") && passwordtxt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
 
                } else {
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                } 
            }
        });
    }	
}
