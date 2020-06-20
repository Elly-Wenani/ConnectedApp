package com.example.connectedapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    TextView tvTitle;
    TextView tvAuthors;
    TextView tvPublisher;
    TextView tvDate;

    Context context;
    Book book;

    ArrayList<Book> books;

    public BooksAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        TextView tvAuthors;
        TextView tvPublisher;
        TextView tvDate;

        public BookViewHolder(@NonNull View itemView) { //Remove @NonNull
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthors = itemView.findViewById(R.id.tvAuthors);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
            tvDate = itemView.findViewById(R.id.tvPublishedDate);
            itemView.setOnClickListener(this); //Handles on click listener
        }

        public void bind(Book book) {
            tvTitle.setText(book.title);
            //String authors = ""; he changed back to this
            tvAuthors.setText(book.authors);
            tvDate.setText(book.publishedDate);
            tvPublisher.setText(book.publisher);
        }

        //Handles on click listener
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Book selectBook = books.get(position);
            Intent intent = new Intent(view.getContext(), BookDetail.class);
            intent.putExtra("Book", selectBook);
            view.getContext().startActivity(intent);
        }
    }
}
