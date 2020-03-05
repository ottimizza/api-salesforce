package br.com.ottimizza.salesforceclientapi.domain.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SFObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value="Id") 
    private String Id;

}
