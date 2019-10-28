package up.edu.br.carteiradeclientes;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

import up.edu.br.carteiradeclientes.database.DadosOpenHelper;
import up.edu.br.carteiradeclientes.dominio.entidades.Cliente;
import up.edu.br.carteiradeclientes.dominio.repositorios.ClienteRepositorio;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listaDados;
    private FloatingActionButton fab;
    private ConstraintLayout layoutContentMain;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private ClienteRepositorio clienteRepositorio;

    private ClienteAdapter clienteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        listaDados = (RecyclerView) findViewById(R.id.lstDados);

        layoutContentMain = (ConstraintLayout) findViewById(R.id.layoutContentMain);

        criarConexao();

        listaDados.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaDados.setLayoutManager(linearLayoutManager);

        clienteRepositorio = new ClienteRepositorio(conexao);

        List<Cliente> clientes = clienteRepositorio.listarClientes();
        clienteAdapter = new ClienteAdapter(clientes);
        listaDados.setAdapter(clienteAdapter);

    }

    private void criarConexao(){

        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContentMain, getString(R.string.message_conexao_criada_sucesso), Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.action_ok),null).show();
        }
        catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro:");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    public void cadastrar(View view){
        Intent it = new Intent(MainActivity.this, CadastrarClienteActivity.class);
        //startActivity(it);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0){

            List<Cliente> clientes = clienteRepositorio.listarClientes();
            clienteAdapter = new ClienteAdapter(clientes);
            listaDados.setAdapter(clienteAdapter);
        }
    }



}
