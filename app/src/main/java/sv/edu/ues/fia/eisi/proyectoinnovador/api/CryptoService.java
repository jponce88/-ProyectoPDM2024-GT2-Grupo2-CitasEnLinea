package sv.edu.ues.fia.eisi.proyectoinnovador.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import sv.edu.ues.fia.eisi.proyectoinnovador.classes.CryptoResponse;

public class CryptoService implements CryptoApiService {
    private final Retrofit retrofit;

    public CryptoService() {
        retrofit = ApiClient.getClient();
    }
    @Override
    public Call<CryptoResponse> getCryptoCurrencies() {
        CryptoApiService service = retrofit.create(CryptoApiService.class);
        return service.getCryptoCurrencies();
    }
}
