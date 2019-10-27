package up.edu.br.carteiradeclientes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listaDados;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        listaDados = (RecyclerView) findViewById(R.id.listaDados);

       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastrarClienteActivity.class);
                startActivity(it);
            }
        }); */
    }

    public void cadastrar(View view){
        Intent it = new Intent(MainActivity.this, CadastrarClienteActivity.class);
        startActivity(it);
    }

}
