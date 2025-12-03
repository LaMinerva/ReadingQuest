package com.example.thereadingquest.ui.books;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.thereadingquest.R;
import com.example.thereadingquest.model.Book;

import java.util.List;
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> libri;

    public BookAdapter(List<Book> libri){
        this.libri = libri;
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
    }

    @Override
    public int getItemCount(){
        return libri != null ? libri.size() : 0;
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitolo;
        TextView txtAutore;
        TextView txtGenere;

        public BookViewHolder(@NonNull View itemView){
            super(itemView);
            txtTitolo = itemView.findViewById(R.id.txtTitoloLibro);
            txtAutore = itemView.findViewById(R.id.txtAutoreLibro);
            txtGenere = itemView.findViewById(R.id.txtGenereLibro);
        }
    }
}
