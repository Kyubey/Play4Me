package com.example.gabriele.play4me;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.TextView;

public class LoginActivity extends Activity {

    public static final String AMBIENTE_EXTRA = "com.example.gabriele.play4me.factory";
    Button loginButton;
    EditText username;
    EditText password;
    boolean end=false;
    TextView errorText;
    Factory factory;
    Person person=factory.nuovoUtente();
    boolean isResumed = false;
    boolean wrongPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        loginButton = (Button) findViewById(R.id.loginButton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        setContentView(R.layout.activity_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(end){
                    LoginActivity.this.finish();
                }
                if (checkInput()) {
                    if (checkPerson()) {
                        wrongPassword = false;
                        checkInput();
                        // mostro prossima schermata
                        Intent showResult = new Intent(LoginActivity.this, MainActivity.class);
                        showResult.putExtra(AMBIENTE_EXTRA,factory);
                        end=true;
                        checkInput();
                        startActivity(showResult);

                        LoginActivity.this.finish();
                    }
                    else{
                        wrongPassword = true;
                        checkInput();
                    }

                }

            }
        } )
    ;}
    @Override
    public void onResume() {
        super.onResume();
        isResumed = true;
    }
    /**
     * Questo metodo effettua un controllo dei valori inseriti dall'utente
     *
     * @return true se tutti i campi sono stati inseriti correttamente
     * false altrimenti
     */
    private boolean checkInput() {
        int errors = 0;
        username.setError(null);
        password.setError(null);

        if(end){
            loginButton.setText("Uscita!");
        }

        if (username.getText() == null || username.getText().length() == 0) {
            // non e' stato inserito il nome
            username.setError("Inserire il proprio nome utente");
            wrongPassword=false;
            errors++;
        } else {
            // reset dell'errore
            username.setError(null);
        }
        if (password.getText() == null || password.getText().length() == 0) {
            // non e' stato inserito il cognome
            password.setError("Inserire la propria password");
            wrongPassword=false;
            errors++;
        } else {
            // reset dell'errore
            password.setError(null);
        }
        if (wrongPassword){
            username.setError("Inserire nome utente e password corretti");
            password.setError("Inserire nome utente e password corretti");
            errors = 99;
        }



        switch (errors) {
            case 0:
                /*errorText.setVisibility(View.GONE);
                errorSpace.setVisibility(View.GONE);*/
                errorText.setText("");
                break;
            case 1:
                /*errorText.setVisibility(View.VISIBLE);
                errorSpace.setVisibility(View.VISIBLE);*/
                errorText.setText("Si è verificato un errore");
                break;
            case 99:
                /*errorText.setVisibility(View.VISIBLE);
                errorSpace.setVisibility(View.VISIBLE);*/
                errorText.setText("Inseriti dati errati");
                wrongPassword=false;
                break;
            default:
                /*errorText.setVisibility(View.VISIBLE);
                errorSpace.setVisibility(View.VISIBLE);*/
                errorText.setText("Si sono verificati " + errors + " errori");
                break;
        }

        return errors == 0;
    }

    private boolean checkPerson() {
        return this.person.match(this.username.getText().toString(), this.password.getText().toString());
    }
}