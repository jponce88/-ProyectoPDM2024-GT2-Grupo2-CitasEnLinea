package sv.edu.ues.fia.eisi.proyectoinnovador.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import sv.edu.ues.fia.eisi.proyectoinnovador.R;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<CardItem> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        cards = new ArrayList<>();
        cards.add(new CardItem("Bitcoin", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/800px-Bitcoin.svg.png"));
        cards.add(new CardItem("Ethereum", "https://download.logo.wine/logo/Ethereum/Ethereum-Logo.wine.png"));
        cards.add(new CardItem("Tether USDt","https://seeklogo.com/images/T/tether-usdt-logo-FA55C7F397-seeklogo.com.png"));
        cards.add(new CardItem("BNB","https://cryptologos.cc/logos/bnb-bnb-logo.png"));
        cards.add(new CardItem("Dogecoin","https://upload.wikimedia.org/wikipedia/en/d/d0/Dogecoin_Logo.png"));
        cards.add(new CardItem("Polygon", "https://cryptologos.cc/logos/polygon-matic-logo.png"));

        cardAdapter = new CardAdapter(this, cards);
        recyclerView.setAdapter(cardAdapter);

        cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CardItem clickedItem = cards.get(position);
                Intent intent = new Intent(MainActivity.this, CryptoInfoActivity.class);
                intent.putExtra("cryptoName", clickedItem.getTitle());
                intent.putExtra("cryptoImage", clickedItem.getImageUrl());
                startActivity(intent);
            }
        });
    }

}