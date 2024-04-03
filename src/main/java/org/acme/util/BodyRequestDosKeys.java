package org.acme.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BodyRequestDosKeys {
    @JsonProperty("llaveForanea")
    String llaveForanea;

    @JsonProperty("valorLLaveForanea")
    String valorLLaveForanea;

    @JsonProperty("llaveForanea2")
    String llaveForanea2;

    @JsonProperty("valorLLaveForanea2")
    String valorLLaveForanea2;

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

    public String getLlaveForanea2() {
        return llaveForanea2;
    }

    public void setLlaveForanea2(String llaveForanea2) {
        this.llaveForanea2 = llaveForanea2;
    }

    public String getValorLLaveForanea2() {
        return valorLLaveForanea2;
    }

    public void setValorLLaveForanea2(String valorLLaveForanea2) {
        this.valorLLaveForanea2 = valorLLaveForanea2;
    }
}
