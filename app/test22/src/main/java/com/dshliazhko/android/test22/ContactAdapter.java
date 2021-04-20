package com.dshliazhko.android.test22;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final Contract.Presenter presenter;
    private List<Contact> listContact = new ArrayList<>() ;
    private Intent intent;
    // private List<Contact> listContact = App.getInstance().getDatabase().contactDao().getAll();
    // private List<Contact> listContact;
    private Context context;
    private ContactViewHolder contactViewHolder;
    private int pos;


    public ContactAdapter(Presenter presenter, Context context) {

        this.presenter = presenter;
        this.context = context;
    }


    public int getPosition() {
        return pos;
    }

    public void setPosition(int pos) {
        this.pos = pos;
    }

    public ContactViewHolder getContactViewHolder() {
        return contactViewHolder;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        contactViewHolder = new ContactViewHolder(view);
        // view.setOnCreateContextMenuListener(this);

        return contactViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bind(listContact.get(position));

        holder.itemView.findViewById(R.id.viewButtonCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Log", "нажата кнопка Call");

                presenter.clickCallButton(listContact.get(position).getNumber());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listContact.size();

    }

    public void setList(List<Contact> list) {
        this.listContact = list;
        notifyDataSetChanged();
    }

    public Contact remuve() {
        Contact contact = listContact.get(pos);
        //presenter.remuve(contact);
        //App.getInstance().getDatabase().contactDao().delete(contact);
        //listContact = App.getInstance().getDatabase().contactDao().getAll();
        //notifyDataSetChanged();
        return contact;
    }

    public Contact reNameContact() {
        Contact contact = listContact.get(pos);
        return contact;
    }


    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private final ImageView viewimageView;
        private final TextView viewEditName;
        private final TextView viewEditNumber;
        private final ImageButton imageButton;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            viewEditName = itemView.findViewById(R.id.view_name_list);
            viewEditNumber = itemView.findViewById(R.id.view_number_list);
            viewimageView = itemView.findViewById(R.id.view_imagine_list);
            imageButton = itemView.findViewById(R.id.viewButtonCall);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.add(0, 1, 0, "удалить");
            contextMenu.add(0, 2, 0, "редактировать");
            contextMenu.add(0, 3, 0, "что-то еще");
        }

        public TextView getViewEditName() {
            return viewEditName;
        }


        // для изменения значения у существующих джава объектов -view Holder
        void bind(Contact contact) {
            viewEditName.setText(contact.getName());
            viewEditNumber.setText(contact.getNumber());
            Glide
                    .with(context)
                    .load(contact.getPathImage())
                    // .load("https://i.imgur.com/DvpvklR.png")
                    //.load(R.mipmap.ic_launcher)
                    .placeholder(R.drawable.ic_baseline_cloud_download_24)
                    .fitCenter()
                    .into(viewimageView)
            ;
        }


    }

}
