package es.polytex.integracionback.auto.service;

import es.polytex.integracionback.auto.mapper.AutoMapper;
import es.polytex.integracionback.core.service.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoService extends Service {
    private final AutoMapper autoMapper;
    public AutoService() {
        this.autoMapper = new AutoMapper();
    }

    public void programarTarea(int dia, int horas, int minutos, String siteID) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        long retrasoInicial = TimeUnit.DAYS.toMillis(dia) + TimeUnit.HOURS.toMillis(horas) + TimeUnit.MINUTES.toMillis(minutos);
        long intervaloEntreEjecuciones = TimeUnit.HOURS.toMillis(horas) + TimeUnit.MINUTES.toMillis(minutos);
        if (retrasoInicial <= 0) {
            retrasoInicial = 10;
        }
        if (intervaloEntreEjecuciones <= 0) {
            intervaloEntreEjecuciones = 10;
        }
        scheduler.scheduleAtFixedRate(() -> {
                autoMapper.executeFiles(siteID);
                autoMapper.executeLimit(siteID);
        }, retrasoInicial, intervaloEntreEjecuciones, TimeUnit.MILLISECONDS);
    }

}
