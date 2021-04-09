package hr.unidu.kruno.zadatak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PregledAdapter extends RecyclerView.Adapter<PregledAdapter.MyViewHolder>{
    private final List<Clan> mDataset;
    private Context con;
    private OnRowClickInterface akcija;
    public PregledAdapter(List<Clan> myDataset, Context con, OnRowClickInterface akcija) {
        mDataset = myDataset;
        this.con = con;
        this.akcija = akcija;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.redak_pregled, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.ime.setText(mDataset.get(position).getIme());
        holder.adresa.setText(mDataset.get(position).getAdresa());
        holder.oibTelefon.setText("OIB: "+mDataset.get(position).getOib() + " Tel: " + mDataset.get(position).getTelefon());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();

    }

    // Unutarnja klasa tipa ViewHolder čuva referencu na view
    // U njoj se obrađuje događaj pritiska/klika na stavku
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView ime;
        public TextView adresa;
        public TextView oibTelefon;
        public MyViewHolder(View itemView) {
            super(itemView);
            ime = itemView.findViewById(R.id.ime);
            adresa = itemView.findViewById(R.id.adresa);
            oibTelefon = itemView.findViewById(R.id.oib_i_telefon);
            // referenca na objekt tipa MyViewHolder se sprema kao tag elementa liste
            itemView.setTag(this);
            // registrira se listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            notifyDataSetChanged();
            // Dohvat podataka iz adaptera
            if (pos != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                String pok = mDataset.get(pos).getIme() ;
                // Vraćamo objekt izabranog retka
                akcija.onRowClick(mDataset.get(pos));
            }
        }
     }
}
