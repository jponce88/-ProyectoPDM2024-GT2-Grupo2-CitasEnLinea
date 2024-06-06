package sv.edu.ues.fia.eisi.proyectoinnovador.api;

import retrofit2.Call;
import retrofit2.http.GET;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoResponse;

public interface CryptoApiService {

    @GET("cryptocurrency/listings/latest")
    Call<CryptoResponse> getCryptoCurrencies();
}
