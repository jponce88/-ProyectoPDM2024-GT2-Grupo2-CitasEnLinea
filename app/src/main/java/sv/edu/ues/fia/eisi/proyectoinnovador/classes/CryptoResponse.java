package sv.edu.ues.fia.eisi.proyectoinnovador.classes;

import java.util.List;
public class CryptoResponse {
    private Status status;
    private List<CryptoData> data;

    // Getters and Setters
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<CryptoData> getData() {
        return data;
    }

    public void setData(List<CryptoData> data) {
        this.data = data;
    }
}
