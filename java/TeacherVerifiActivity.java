package asha.md_nayeem.schoolmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class TeacherVerifiActivity extends AppCompatActivity
{
    private EditText number, code;
    private FirebaseAuth mAuth;
    private String codeSent;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_verifi);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        number = findViewById(R.id.phone_number_verify_Id);
        code = findViewById(R.id.code_number_verify_Id);

        findViewById(R.id.verification_Id).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendVerificationCode();
                loadingBar.setTitle("Sending Verification Code");
                loadingBar.setMessage("Dear User Please wait, while we are Sending the verification code which Phone number you are using...!");
                loadingBar.show();
            }
        });


        findViewById(R.id.sign_in_Id).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                verifySignInCode();
                loadingBar.setTitle("Log in");
                loadingBar.setMessage("Dear User Please wait, while we are chalking your Credential...!");
                loadingBar.show();
            }
        });
    }

    private void verifySignInCode()
    {
        String code1 = code.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code1);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {

                            /*Toast.makeText(TeacherVerifiActivity.this, "Log in Successfull", Toast.LENGTH_SHORT).show();*/
                            startActivity(new Intent(TeacherVerifiActivity.this, UserTeacherActivity.class));
                        }
                        else
                        {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            {
                                // The verification code entered was invalid
                                Toast.makeText(TeacherVerifiActivity.this, "Invalided Verification Code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void sendVerificationCode()
    {
        String phoneNumber = number.getText().toString();

        if (phoneNumber.isEmpty())
        {
            number.setError("Phone Number Is Mandatory");
            number.requestFocus();
            return;
        }
        if (phoneNumber.length() < 11 )
        {
            number.setError("Please enter a valid Phone Number...!");
            number.requestFocus();
            return;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber
                (
                        phoneNumber,        // Phone number to verify
                        60,              // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        this,        // Activity (for callback binding)
                        mCallbacks          // OnVerificationStateChangedCallbacks
                );
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
        {
            loadingBar.dismiss();
        }

        @Override
        public void onVerificationFailed(FirebaseException e)
        {
            Toast.makeText(TeacherVerifiActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
        {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
            loadingBar.dismiss();

        }
    };
}
