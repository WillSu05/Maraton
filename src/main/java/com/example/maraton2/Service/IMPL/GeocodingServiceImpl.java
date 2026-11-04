package com.example.maraton2.Service.IMPL;

import com.example.maraton2.Service.GeocodingService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public double[] obtenerCoordenadas(String municipio, String puntoReferencia) {
        try {
            String query = puntoReferencia + ", " + municipio + ", Cundinamarca, Colombia";


            String url = UriComponentsBuilder.fromUriString("https://nominatim.openstreetmap.org/search")
                    .queryParam("q", query)
                    .queryParam("format", "json")
                    .queryParam("limit", "1")
                    .toUriString();

            Map<String, Object>[] response = restTemplate.getForObject(url, Map[].class);
            if (response != null && response.length > 0) {
                double lat = Double.parseDouble(response[0].get("lat").toString());
                double lon = Double.parseDouble(response[0].get("lon").toString());
                return new double[]{lat, lon};
            }
        } catch (Exception e) {
        }
        return new double[]{4.6097, -74.0817};
    }

    @Override
    public double calcularDistanciaKm(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}