package up.edu.br.carteiradeclientes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

        ViewHolderCliente holderCliente = new ViewHolderCliente(view);

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

        public ViewHolderCliente(View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
            txtTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);
        }
    }
}
