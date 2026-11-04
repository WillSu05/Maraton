package com.example.maraton2.Service;

public interface GeocodingService {
    double[] obtenerCoordenadas(String municipio, String puntoReferencia);
    double calcularDistanciaKm(double lat1, double lon1, double lat2, double lon2);
}
