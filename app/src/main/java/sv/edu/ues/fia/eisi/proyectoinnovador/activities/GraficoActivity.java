package sv.edu.ues.fia.eisi.proyectoinnovador.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sv.edu.ues.fia.eisi.proyectoinnovador.api.ApiClient;
import sv.edu.ues.fia.eisi.proyectoinnovador.api.CryptoApiService;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoData;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoResponse;
import sv.edu.ues.fia.eisi.proyectoinnovador.R;

public class GraficoActivity extends AppCompatActivity {

    private LineChart chart;
    private CryptoApiService cryptoApiService;
    private TextView textView;
    private Button button;
    private List<Entry> entries;

    private List<Integer> segundos;
    private Handler handler = new Handler();
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        Intent intent = getIntent();
        String cryptoName = intent.getStringExtra("cryptoName");

        int maxEntries = 20;

        textView = findViewById(R.id.tituloGrafico);
        button = findViewById(R.id.buttonUpdate);
        chart = findViewById(R.id.chartCrypto);
        textView.setText(cryptoName);

        entries = new ArrayList<>();

        segundos = new ArrayList<>();
        segundos.add(0);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextColor(Color.WHITE);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setEnabled(false);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setTextColor(Color.WHITE);

        chart.getDescription().setEnabled(false);

        chart.getLegend().setEnabled(false);


        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                float randomValue = 1000+random.nextFloat() * 100;
                segundos.add(random.nextInt(60));
                if (entries.size() >= maxEntries) {
                    entries.remove(0);
                }

                entries.add(new Entry(segundos.size(), randomValue));
                LineDataSet dataSet = new LineDataSet(entries, "Random Data");
                LineData lineData = new LineData(dataSet);
                chart.setData(lineData);

                chart.notifyDataSetChanged();
                chart.invalidate();
                handler.postDelayed(this, 1000);
            }
        }, 2000);






    }

    private void fetchCryptoData(String cryptoName){
        Call<CryptoResponse> call = cryptoApiService.getCryptoCurrencies();
        call.enqueue(new Callback<CryptoResponse>() {
            @Override
            public void onResponse(Call<CryptoResponse> call, Response<CryptoResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    CryptoResponse cryptoResponse = response.body();
                    List<CryptoData> cryptoDataList = cryptoResponse.getData();
                    CryptoData cryptoData = filterCryptoData(cryptoDataList, cryptoName);
                    updateUI(cryptoData);
                }
            }

            @Override
            public void onFailure(Call<CryptoResponse> call, Throwable t) {
                Toast.makeText(GraficoActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
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
    private void updateUI(CryptoData cryptoData) {
        if (cryptoData != null) {
            String cryptoInfo = "Name: " + cryptoData.getName() + "\n" +
                    "Symbol: " + cryptoData.getSymbol() + "\n" +
                    "Price: $" + cryptoData.getQuote().getUSD().getPrice() + "\n" +
                    "Market Cap: $" + cryptoData.getQuote().getUSD().getPrice();

            textView.setText(cryptoInfo);
        } else {
            textView.setText("Cryptocurrency not found.");
        }
    }
}