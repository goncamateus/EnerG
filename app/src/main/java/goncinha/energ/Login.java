package goncinha.energ;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.id;

public class Login extends AppCompatActivity {
    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        final EditText username = (EditText) findViewById(R.id.user);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button loginbutton = (Button) findViewById(R.id.login);
        final Button register = (Button) findViewById(R.id.register);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecionando...",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(loginbutton.getContext(), Main.class);
                    myIntent.putExtra("extra", id);
                    startActivityForResult(myIntent, 0);
                }else{
                    Toast.makeText(getApplicationContext(), "Email ou senha errados",Toast.LENGTH_SHORT).show();
                    counter--;
                    if (counter == 0) {
                        loginbutton.setEnabled(false);
                        Toast.makeText(getApplicationContext(),
                                "Você errou 3 vezes a senha",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Criando conta...",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
