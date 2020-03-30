package daniel.trabalhotap.com.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;
public class JogoActivity extends AppCompatActivity {

//Declara os componentes da JogoActivity
    private ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
            btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20;
    private Button btnQuit;
    private int score = 0;
    private int conjuntosCertos = 0;

//Atribui um valor inteiro para cada figura pra usar Collections.shuffle
    private int camelo = R.drawable.camelo;
    private int elefante = R.drawable.elefante;
    private int leao = R.drawable.leao;
    private int lobo = R.drawable.lobo;
    private int onca = R.drawable.onca;
    private int raposa = R.drawable.raposa;
    private int rinoceronte = R.drawable.rinoceronte;
    private int tigre = R.drawable.tigre;
    private int urso = R.drawable.urso;
    private int zebra = R.drawable.zebra;
    private TextView txtScore;
    private ImageButton botoes[];
    private int imagens[];
    private boolean umaCartaVirada = false;
    private boolean duasCartasViradas = false;
    private ImageButton firtsBtnVirado, secBtnVirado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        iniComponents();
        iniArrays();
        embaralhar();
        virar();
        atualizaTxtScore();

//Atribui evento ao botão Quit e redireciona o jogador pra MainActivity
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//Atribui eventos aos botões das figuras
        for(int i = 0 ; i < 20; i++){
            clickBotao(botoes[i]);
        }
    }

//Atribui ids aos componentes
    public void iniComponents(){
        btnQuit = findViewById(R.id.btnQuit);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);
        txtScore = findViewById(R.id.txtScore);
    }

//Mostra no TextView o score do jogador
    public void atualizaTxtScore(){
        txtScore.setText("Sua Pontuação Atual: " + String.valueOf(score));
    }

//Declara os vetores com os botões e as imagens
    public void iniArrays() {
        botoes = new ImageButton[] {
                btn1, btn2, btn3, btn4, btn5, btn6, btn7,
                btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15,
                btn16, btn17, btn18, btn19, btn20
        };
        imagens = new int[]{
                camelo, elefante, leao, lobo, onca, raposa, rinoceronte, tigre, urso, zebra,
                camelo, elefante, leao, lobo, onca, raposa, rinoceronte, tigre, urso, zebra
        };
    }

//Embaralha as imagens nos botões
    public void embaralhar() {
        Collections.shuffle(Arrays.asList(imagens));
        Collections.shuffle(Arrays.asList(botoes));
        for(int i = 0 ; i < 20; i++)
            botoes[i].setBackgroundResource(imagens[i]);
    }
//Coloca uma imagem como default nos botões
    public void virar() {
        for(int i = 0 ; i < 20; i++)
            botoes[i].setImageResource(R.drawable.capa);
    }
//Atribui eventos aos botões
    private void clickBotao(final ImageButton botao) {
        final ImageButton btnVirado = botao; //Guarda o botão que foi virado
        btnVirado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//Verifica se tem duas cartas viradas
                if (!duasCartasViradas) {

//Verifica se tem uma carta virada
                    if (!umaCartaVirada) {
                        firtsBtnVirado = btnVirado;//Guarda o primeiro botão virado
                        firtsBtnVirado.setImageResource(0);//Retira a imagem default do botão
                        umaCartaVirada = true;
                        firtsBtnVirado.setClickable(false);

                    } else {
                        duasCartasViradas = true;
                        secBtnVirado = btnVirado;//Guarda o segundo botão virado
                        secBtnVirado.setImageResource(0);
                        secBtnVirado.setClickable(false);

//Compara as imagens do primeiro e segundo botões
                        if (firtsBtnVirado.getBackground().getConstantState()
                                ==
                                secBtnVirado.getBackground().getConstantState())
                        {
                            score++;
                            conjuntosCertos++;
                            limpaBtn();
                            fim();
                        } else {
                            score--;
                            desviraBtn();
                        } atualizaTxtScore();
                        umaCartaVirada = false;
                    }
                }
            }
        });
    }

//Desvira os botões
    public void desviraBtn(){
        Handler handler = new Handler();

//Delay para o jogador visualizar os imagens iguais
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                firtsBtnVirado.setImageResource(R.drawable.capa);
                secBtnVirado.setImageResource(R.drawable.capa);
                firtsBtnVirado.setClickable(true);
                secBtnVirado.setClickable(true);
                duasCartasViradas = false;
            }
        },1000);
    }

//Deixa os botões semalhantes invisíveis quando o jogador acerta
    public void limpaBtn(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                firtsBtnVirado.setVisibility(View.INVISIBLE);
                secBtnVirado.setVisibility(View.INVISIBLE);
                duasCartasViradas = false;
            }
        },1000);
    }

//Redireciona o jogador para a FinalActivity no final da jogada
    public void fim(){
        if( conjuntosCertos == 10){
            Intent intent = new Intent(getApplicationContext(),
                    FinalActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }
}
