package up.edu.br.carteiradeclientes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import up.edu.br.carteiradeclientes.dominio.entidades.Cliente;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {

    private List<Cliente> clienteList;


    public ClienteAdapter(List<Cliente> clienteList){
        this.clienteList = clienteList;
    }


    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_cliente, parent, false);

        ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());

        return holderCliente;
    }


    @Override
    public void onBindViewHolder(ClienteAdapter.ViewHolderCliente holder, int position) {

        if (clienteList != null && clienteList.size() > 0){

            Cliente cliente = clienteList.get(position);

            holder.txtNome.setText(cliente.getNome());
            holder.txtTelefone.setText(cliente.getTelefone());

        }
    }


    @Override
    public int getItemCount() {
        return clienteList.size();
    }


    public class ViewHolderCliente extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public TextView txtTelefone;

        public ViewHolderCliente(View itemView, final Context context) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
            txtTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(clienteList.size() > 0){

                        Cliente cliente = clienteList.get(getLayoutPosition());
                        //Toast.makeText(context, "Cliente: " + cliente.getNome(), Toast.LENGTH_SHORT).show();

                        Intent it = new Intent(context, CadastrarClienteActivity.class);
                        it.putExtra("cliente", cliente);
                        ((AppCompatActivity)context).startActivityForResult(it, 0);

                    }
                }
            });
        }
    }

}
