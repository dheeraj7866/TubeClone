package com.example.tubeclone;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubeclone.homeAdapter.Channel;
import com.example.tubeclone.homeAdapter.ChannelAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name;
    Button signOutBtn;
    RecyclerView recyclerView;
    ChannelAdapter adapter;
    private long pressedTime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        ArrayList<Channel> list = new ArrayList<>();
        list.add(new Channel("ZEE NEWS","UCIvaYmXn910QMdemBG3v1pQ",R.drawable.zeenews));
        list.add(new Channel("Aastha","UCRUAdVm9ZOF4JheOd8qIQHA",R.drawable.aastha));
        list.add(new Channel("Sanskar","UC7eJGmLtqQzqCx8Hcz3TQpw",R.drawable.sanskar));
        list.add(new Channel("Tseries","UCq-Fj5jknLsUf-MWSy4_brA",R.drawable.tseries));
        list.add(new Channel("Google Developer","UC_x5XG1OV2P6uZZ5FSM9Ttw",R.drawable.google));
        list.add(new Channel("Hungama","UCzR7770PbrKcG9OYGzBep9w",R.drawable.bollyhungama));
        list.add(new Channel("Filter Copy","UC7IMq6lLHbptAnSucW1pClA",R.drawable.filtercopy));
        list.add(new Channel("BB Ki Wines","UCqwUrj10mAEsqezcItqvwEw",R.drawable.bbkivines));
        list.add(new Channel("Shemaroo","UC7ZivIYRB0fMSGh-THcTYbw",R.drawable.shemaroo));
        list.add(new Channel("T Series Bhakti","UCaayLD9i5x4MmIoVZxXSv_g",R.drawable.tseriesbhakti));
        list.add(new Channel("Shadhna","UC04m8d9t8UeWZ5DuvQVnqiw",R.drawable.shadhnagold));


        name=findViewById(R.id.person_name);

        signOutBtn=findViewById(R.id.sign_out);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();

            name.setText(personName);
        }
        recyclerView=findViewById(R.id.channel_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new ChannelAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        // Call the super class method
        super.onBackPressed();
        // Close the app and remove it from the recent apps list
        finishAffinity();
        //System.exit(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.sign_out){
            signOut();
            startActivity(new Intent(Home.this,SignIn.class));

        }
        return super.onOptionsItemSelected(item);
    }
    private void signOut() {
        gsc.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(Home.this, "Signed Out successfully...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}