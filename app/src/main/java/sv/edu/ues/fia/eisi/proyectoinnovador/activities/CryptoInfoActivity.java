package sv.edu.ues.fia.eisi.proyectoinnovador.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import sv.edu.ues.fia.eisi.proyectoinnovador.R;
import sv.edu.ues.fia.eisi.proyectoinnovador.api.CryptoApiService;
import sv.edu.ues.fia.eisi.proyectoinnovador.api.CryptoService;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoData;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoResponse;

public class CryptoInfoActivity extends AppCompatActivity {
    private CryptoApiService cryptoApiService;


    private TextView textViewName, textViewSymbol, textViewCreationDate, textViewPrice;
    private ImageView imageViewLogo;
    private LottieAnimationView loadingAnimation;
    private Button graphicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_info);

        cryptoApiService = new CryptoService();

        textViewName = findViewById(R.id.textViewName);
        textViewSymbol = findViewById(R.id.textViewSymbol);
        textViewCreationDate = findViewById(R.id.textViewCreationDate);
        textViewPrice = findViewById(R.id.textViewPrice);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        loadingAnimation = findViewById(R.id.loadingAnimation);
        graphicButton = findViewById(R.id.cryptoPrice);

        // Get the intent extras
        String name = getIntent().getStringExtra("cryptoName");
        String logoUrl = getIntent().getStringExtra("cryptoImage");

        if(Objects.equals(name, "Tether USDt")){
            name = "Tether";
        }

        int Color;
        try{
        switch (Objects.requireNonNull(name)){
            case "Bitcoin":
                Color = ContextCompat.getColor(this, R.color.Bitcoin);
                break;
            case "Ethereum":
                Color = ContextCompat.getColor(this, R.color.Ethereum);
                break;
                case "Tether":
                    Color = ContextCompat.getColor(this, R.color.Tether);
                    break;
            case "BNB":
                Color = ContextCompat.getColor(this, R.color.BNB);
                break;
                case "Dogecoin":
                    Color = ContextCompat.getColor(this, R.color.Dogecoin);
                    break;
            case "Polygon":
                Color = ContextCompat.getColor(this, R.color.Polygon);
                break;

            default:
                Color = ContextCompat.getColor(this, R.color.Bitcoin);
        }}catch (Exception e){
            Color = ContextCompat.getColor(this, R.color.Bitcoin);
        }
        graphicButton.setBackgroundColor(Color);

        loadingAnimation.setVisibility(View.VISIBLE);

        // Set the values
        textViewName.setText(name);
        fetchCryptoInfo(name);
        Glide.with(this).load(logoUrl).into(imageViewLogo);


    }

    private void fetchCryptoInfo(String cryptoName) {
        Call<CryptoResponse> call = cryptoApiService.getCryptoCurrencies();

        call.enqueue(new Callback<CryptoResponse>() {
            @Override
            public void onResponse(Call<CryptoResponse> call, retrofit2.Response<CryptoResponse> response) {
                if (response.isSuccessful()) {

                    List<CryptoData> cryptoDataList = response.body().getData();
                    CryptoData cryptoData = filterCryptoData(cryptoDataList, cryptoName);
                    try {
                        textViewSymbol.setText(cryptoData.getSymbol());

                    }catch (Exception e){
                        textViewSymbol.setVisibility(View.GONE);
                    }
                    try{
                        String dateAdded = cryptoData.getDate_added();
                        dateAdded = dateAdded.substring(0, 10);
                        textViewCreationDate.setText("Creado el: "+dateAdded);
                    }catch (Exception e){
                        textViewCreationDate.setVisibility(View.GONE);
                    }
                    try{
                        double price = cryptoData.getQuote().getUSD().getPrice();
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        textViewPrice.setText("Precio Actual: $"+String.valueOf(decimalFormat.format(price)));
                    }catch (Exception e){
                        textViewPrice.setVisibility(View.GONE);
                    }
                    loadingAnimation.setVisibility(View.GONE);
                    textViewName.setVisibility(View.VISIBLE);
                    textViewSymbol.setVisibility(View.VISIBLE);
                    textViewCreationDate.setVisibility(View.VISIBLE);
                    imageViewLogo.setVisibility(View.VISIBLE);
                    Toasty.success(CryptoInfoActivity.this, "Información de "+cryptoName+" obtenida correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    textViewName.setText("Cryptocurrency not found");
                    textViewSymbol.setVisibility(View.GONE);
                    textViewCreationDate.setVisibility(View.GONE);
                    imageViewLogo.setVisibility(View.GONE);
                    loadingAnimation.setVisibility(View.GONE);
                    Toasty.error(CryptoInfoActivity.this, "Error al obtener la información de "+cryptoName, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CryptoResponse> call, Throwable t) {
                Toasty.error(CryptoInfoActivity.this, "Error al obtener la información de "+cryptoName, Toast.LENGTH_SHORT).show();
            }

        });
    }
    private CryptoData filterCryptoData(List<CryptoData> cryptoDataList, String cryptoName) {
        for (CryptoData cryptoData : cryptoDataList) {
            if (cryptoData.getName().equalsIgnoreCase(cryptoName)) {
                return cryptoData;
            }
        }
        return null;
    }

    public void showGraphic(View view) {
        Intent intent = new Intent(this, GraficoActivity.class);
        String nameCrypto;
        if(Objects.equals(textViewName.getText().toString(), "Tether")){
            nameCrypto = "Tether USDt";
        }else{
            nameCrypto = textViewName.getText().toString();
        }
        intent.putExtra("cryptoName", nameCrypto);
        startActivity(intent);
    }
}
