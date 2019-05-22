package edu.miracosta.financialassistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import edu.miracosta.financialassistant.database.DBHelper;
import edu.miracosta.financialassistant.model.Account;

/**
 * The first activity (other than the splash screen) that the user will encounter.
 * Responsible for authenticating users and adding new ones with firebase when possible.
 */
public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "FinancialAssistant";

    private ImageView appLogoImageView;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;
    private Account mAccount;
    private DBHelper db;
    private List<Account> mAccountList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect the components
        appLogoImageView = findViewById(R.id.appLogoImageView);
        emailEditText = findViewById(R.id.emailEditTextSU);
        passwordEditText = findViewById(R.id.passwordEditTextSU);
        loginButton = findViewById(R.id.logInButton);
    }

    //temporary
    public void logIn(View v)
    {
        mAccountList = new ArrayList<>();
        db = new DBHelper(this);
        //db.onUpgrade(db.getReadableDatabase(), 1, 2);

        mAccountList = db.getAllAccounts();

        if(mAccount == null)
        {
            mAccount = new Account(emailEditText.getText().toString(), passwordEditText.getText().toString());
            db.addAccount(mAccount);
            Intent intent= new Intent(this, MonthlyOverview.class);
            intent.putExtra("Account", mAccount);
            startActivity(intent);
        }
        else
        {
            Intent intent= new Intent(this, MonthlyOverview.class);
            intent.putExtra("Account",mAccount);
            startActivity(intent);
        }

    }
}
