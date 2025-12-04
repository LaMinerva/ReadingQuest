package com.example.thereadingquest.ui.books;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.thereadingquest.R;
import com.example.thereadingquest.data.BookRepository;
import com.example.thereadingquest.data.DatabaseHelper;
import com.example.thereadingquest.model.Book;

import java.util.List;
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> libri;
    private OnBookClickListener listener;

    public interface OnBookClickListener {
        void onBookClick(Book libro);
    }

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.listener = listener;
    }
    public BookAdapter(List<Book> libri, OnBookClickListener listener){
        this.libri = libri;
        this.listener = listener;
    }

    public void setLibri(List<Book> nuoviLibri){
        this.libri = nuoviLibri;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position){
        Book libro = libri.get(position);
        holder.txtTitolo.setText(libro.getTitolo());
        holder.txtAutore.setText(libro.getAutore());
        holder.txtGenere.setText(libro.getGenere());

        DatabaseHelper db = new DatabaseHelper(holder.itemView.getContext());
        double avgSpeed = db.leggiMediaLettura(libro.getUserId());

        BookRepository repo = new BookRepository(holder.itemView.getContext());
        double tempoStimatoMin = repo.calcolaTempoStimato(libro.getPagineTotali(), avgSpeed);

        if(tempoStimatoMin <= 0) {
            holder.txtTempoStimato.setText("Tempo stimato: N/D");
        } else {
            int ore = (int)(tempoStimatoMin / 60);
            int minuti = (int)(tempoStimatoMin % 60);

            if (ore > 0)
                holder.txtTempoStimato.setText("Tempo stimato: " + ore + "h " + minuti + "m");
            else
                holder.txtTempoStimato.setText("Tempo stimato: " + minuti + " min");
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                int pos = holder.getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    listener.onBookClick(libri.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return libri != null ? libri.size() : 0;
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitolo;
        TextView txtAutore;
        TextView txtGenere;
        TextView txtTempoStimato;

        public interface OnBookClickListener{
            void onBookClick(Book libro);
        }
        public BookViewHolder(@NonNull View itemView){
            super(itemView);
            txtTitolo = itemView.findViewById(R.id.txtTitoloLibro);
            txtAutore = itemView.findViewById(R.id.txtAutoreLibro);
            txtGenere = itemView.findViewById(R.id.txtGenereLibro);
            txtTempoStimato = itemView.findViewById(R.id.txtTempoStimatoLibro);

        }
    }
}
