package daniel.trabalhotap.com.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class FinalActivity extends AppCompatActivity {

//Declara os componentes da FinalActivity
    private Button btnQuit, btnPlay;
    private TextView txtFinalScore;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        btnQuit = findViewById(R.id.btnQuit);
        btnPlay = findViewById(R.id.btnPlay);
        txtFinalScore = findViewById(R.id.txtFinalScore);
        Intent intent = getIntent();

//Verifica se a Activity tem o argumento "score"
        if(intent.hasExtra("score")) {
            score = intent.getIntExtra("score",0);
            txtFinalScore.setText(String.valueOf(score) + " pontos");
        }

//Atribui evento ao botão Quit
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent); }
        });

//Atribui evento ao botão Play
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), JogoActivity.class );
                startActivity(intent);
                finish();
            }
        });
    }
}