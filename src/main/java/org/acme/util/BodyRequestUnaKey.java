package org.acme.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BodyRequestUnaKey {
    @JsonProperty("llaveForanea")
    String llaveForanea;

    @JsonProperty("valorLLaveForanea")
    String valorLLaveForanea;

    public String getLlaveForanea() {
        return llaveForanea;
    }

    public void setLlaveForanea(String llaveForanea) {
        this.llaveForanea = llaveForanea;
    }

    public String getValorLLaveForanea() {
        return valorLLaveForanea;
    }

    public void setValorLLaveForanea(String valorLLaveForanea) {
        this.valorLLaveForanea = valorLLaveForanea;
    }
}
