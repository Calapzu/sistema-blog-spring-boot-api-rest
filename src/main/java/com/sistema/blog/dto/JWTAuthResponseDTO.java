package com.sistema.blog.dto;

public class JWTAuthResponseDTO {

    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";

    public JWTAuthResponseDTO(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public JWTAuthResponseDTO(String tokenDeAcceso, String tipoDeToken) {
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }

    public String getTokenDeAcceso() {
        return tokenDeAcceso;
    }

    public void setTokenDeAcceso(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public void setTipoDeToken(String tipoDeToken) {
        this.tipoDeToken = tipoDeToken;
    }
}
