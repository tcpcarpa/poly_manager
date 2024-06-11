package es.polytex.integracionback.core.manager;

import es.polytex.integracionback.user.mapper.UserMapper;
import es.polytex.integracionback.user.model.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Manager {
    public static LocalDateTime currentDateTime = LocalDateTime.now();
    public String mostrarFechaHoraAmigable() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM 'de' yyyy hh:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }
    public boolean isInternet() {
        try {
            URL url = new URL("https://external-api.polytex.cloud");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setConnectTimeout(5000);
            urlConnect.getContent();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public Boolean login(User user) {
        try {
            String token = UserMapper.getToken(user);
            return token != null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String geToken(User user) {
        try {
            return UserMapper.getToken(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
