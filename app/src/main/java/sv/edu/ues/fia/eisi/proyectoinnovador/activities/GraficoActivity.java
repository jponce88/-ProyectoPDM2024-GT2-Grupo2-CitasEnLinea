package sv.edu.ues.fia.eisi.proyectoinnovador.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sv.edu.ues.fia.eisi.proyectoinnovador.api.ApiClient;
import sv.edu.ues.fia.eisi.proyectoinnovador.api.CryptoApiService;
import sv.edu.ues.fia.eisi.proyectoinnovador.api.CryptoService;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoData;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoResponse;
import sv.edu.ues.fia.eisi.proyectoinnovador.R;

public class GraficoActivity extends AppCompatActivity {

    private LineChart chart;
    private CryptoApiService cryptoApiService;

    private TextView nombre, precio;
    private Button button;
    private List<Entry> entries;
    private TextView tiempo;
    private List<Integer> segundos;
    private Handler handler = new Handler();
    private Handler handlerTime = new Handler();
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        Intent intent = getIntent();
        String cryptoName = intent.getStringExtra("cryptoName");

        int maxEntries = 20;

        nombre = findViewById(R.id.textGraphTitle);
        chart = findViewById(R.id.chartCrypto);
        precio = findViewById(R.id.textViewPrice);
        tiempo = findViewById(R.id.textViewAnuncio);

        LottieAnimationView animationView = findViewById(R.id.animationView);
        animationView.setVisibility(View.VISIBLE); // Mostrar la animación
        animationView.playAnimation(); // Iniciar la animación
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
        cryptoApiService = new CryptoService();

        handler.postDelayed(new Runnable() {
            @Override
            public void run(){
                float randomValue = 1000+random.nextFloat() * 100;
                segundos.add(random.nextInt(60));
                if (entries.size() >= maxEntries) {
                    entries.remove(0);
                }

                //entries.add(new Entry(segundos.size(), randomValue));
                try {
                    fetchCryptoData(cryptoName);
                    temporizador();
                    Toasty.info(GraficoActivity.this, "Precio actualizado", Toast.LENGTH_SHORT, true).show();
                }catch (Exception e){
                    e.printStackTrace();
                }

                handler.postDelayed(this, 60000);
            }
        }, 500);

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
                    if (cryptoData != null) {
                        String title = cryptoData.getName()+" - "+cryptoData.getSymbol();
                        nombre.setText(title);
                        Double price = cryptoData.getQuote().getUSD().getPrice();
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        String formattedPrice = decimalFormat.format(price);
                        float actualPrice = Float.parseFloat(formattedPrice);
                        String priceTitle = "Último Precio Actualizado: $" + formattedPrice;
                        precio.setText(priceTitle);
                        String time = cryptoResponse.getStatus().getTimestamp();
                        time = time.substring(14, 16);
                        float timeFloat = Float.parseFloat(time);

                        entries.add(new Entry(timeFloat, actualPrice));
                        LineDataSet dataSet = new LineDataSet(entries, "Precio");
                        LineData lineData = new LineData(dataSet);
                        lineData.setDrawValues(false);
                        int colorLinea = ContextCompat.getColor(GraficoActivity.this, R.color.Bitcoin);
                        dataSet.setColor(colorLinea);
                        chart.setData(lineData);
                        chart.notifyDataSetChanged();
                        chart.invalidate();
                    } else {
                        Toasty.error(GraficoActivity.this, "Criptomoneda no encontrada", Toast.LENGTH_SHORT, true).show();
                    }
                    LottieAnimationView animationView = findViewById(R.id.animationView);
                    animationView.setVisibility(View.GONE); // Ocultar la animación
                    animationView.cancelAnimation(); // Detener la animación
                }
            }

            @Override
            public void onFailure(Call<CryptoResponse> call, Throwable t) {
                Toast.makeText(GraficoActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void temporizador(){
        handlerTime.removeCallbacksAndMessages(null);
        handlerTime.postDelayed(new Runnable() {
            int segundos = 60;
            @Override
            public void run() {
                segundos--;
                tiempo.setText("Precio actualizado dentro de: \n" + segundos + " segundos");
                if (segundos > 0){
                handler.postDelayed(this, 1000);
                }else{
                    handlerTime.removeCallbacksAndMessages(null);
                }
            }
        },0);
    }
}