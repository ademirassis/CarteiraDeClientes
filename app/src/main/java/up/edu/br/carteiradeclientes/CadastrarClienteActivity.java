package up.edu.br.carteiradeclientes;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
//import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import up.edu.br.carteiradeclientes.database.DadosOpenHelper;
import up.edu.br.carteiradeclientes.dominio.entidades.Cliente;
import up.edu.br.carteiradeclientes.dominio.repositorios.ClienteRepositorio;

public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEndereco;
    private EditText editEmail;
    private EditText editTelefone;

    private ConstraintLayout layoutContentCadastrarCliente;

    private ClienteRepositorio clienteRepositorio;

    private SQLiteDatabase conexao;

    private DadosOpenHelper dadosOpenHelper;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editNome = (EditText) findViewById(R.id.editNome);
        editEndereco = (EditText) findViewById(R.id.editEndereco);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editTelefone = (EditText) findViewById(R.id.editTelefone);

        layoutContentCadastrarCliente = (ConstraintLayout) findViewById(R.id.layoutContentCadastrarCliente);

        criarConexao();
    }


    private void criarConexao(){

        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContentCadastrarCliente, getString(R.string.message_conexao_criada_sucesso), Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.action_ok),null).show();

            clienteRepositorio = new ClienteRepositorio(conexao);

        }
        catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro:");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }


    private void confirmar(){

        cliente = new Cliente();

        if (validaCampos() == false){

            try {

                clienteRepositorio.inserir(cliente);
                finish();
            }
            catch (SQLException ex){

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Erro:");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }
        }
    }


    private boolean validaCampos(){

        boolean res = false;

        String nome = editNome.getText().toString();
        String endereco = editEndereco.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = editTelefone.getText().toString();

        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        if (isCampoVazio(nome)){
            editNome.requestFocus();
            res = true;
        }
        else
            if (isCampoVazio(endereco)) {
                editEndereco.requestFocus();
                res = true;
            }
            else
                if (!isEmailValido(email)){
                    editEmail.requestFocus();
                    res = true;
                }
                else
                    if (isCampoVazio(telefone)){
                        editTelefone.requestFocus();
                        res = true;
                    }

        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(getString(R.string.title_aviso));
            dlg.setMessage(getString(R.string.message_campos_invalidos_brancos));
            dlg.setNeutralButton(getString(R.string.action_ok), null);
            dlg.show();
        }

        return res;
    }

    private boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        return resultado;
    }

    private boolean isEmailValido(String email){

        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastrar_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_ok:
                confirmar();
                Toast.makeText(this, "Botão OK selecionado!", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_cancelar:
                Toast.makeText(this, "Botão CANCELAR selecionado!", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
