package up.edu.br.carteiradeclientes;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEndereco;
    private EditText editEmail;
    private EditText editTelefone;

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


    }

    private void validaCampos(){

        boolean res = false;

        String nome = editNome.getText().toString();
        String endereco = editEndereco.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = editTelefone.getText().toString();

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
                    //else { }

        if(res){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(getString(R.string.title_aviso));
            dialog.setMessage(getString(R.string.message_campos_invalidos_brancos));
            dialog.setNeutralButton(getString(R.string.lbl_ok), null);
            dialog.show();
        }
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_ok:
                validaCampos();
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
