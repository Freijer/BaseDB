package freijer.app.sucktest;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity  {

    Button btnAdd, btnRead, btnClear;
    EditText scoreText, lvlText, tryText;
    TextView scoreView, lvlView, tryView;

    String score;
    String lvl;
    String trys;

    DataHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd =  findViewById(R.id.btnAdd);
        btnRead =  findViewById(R.id.btnRead);
        btnClear =  findViewById(R.id.btnClear);

        scoreText =  findViewById(R.id.scoreText);
        lvlText =  findViewById(R.id.lvlText);
        tryText =  findViewById(R.id.tryText);

        scoreView = findViewById(R.id.scoreView);
        lvlView = findViewById(R.id.lvlView);
        tryView = findViewById(R.id.tryView);

        score = scoreText.getText().toString();
        lvl = lvlText.getText().toString();
        trys = tryText.getText().toString();

        dbHelper = new DataHelper(this);

    }

    public void AddDB(View v) {
        String sc = scoreText.getText().toString();
        String lvl = lvlText.getText().toString();
        String tryss = tryText.getText().toString();

        dbHelper.WriteDB(sc, lvl, tryss);
    }

    public void ReadfromDB(View v) {
        dbHelper.ReadDB();
    }

    public void DeleteDB(View v){
        dbHelper.DeleteDB();
    }
}