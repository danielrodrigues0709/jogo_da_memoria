package daniel.trabalhotap.com.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    //Declara os botões da MainActivity
    private Button btnPlay, btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Instancia os botões da MainActivity
        btnPlay = findViewById(R.id.btnPlay);
        btnQuit = findViewById(R.id.btnQuit);

    //Verifica a intenção de sair do jogo
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }

    //Atribui evento ao botão Play
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), JogoActivity.class );
                startActivity(intent);
            }
        });

    //Atribui evento ao botão Quit
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}