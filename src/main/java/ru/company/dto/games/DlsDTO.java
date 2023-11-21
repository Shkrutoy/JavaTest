package ru.company.dto.games;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DlsDTO {
    @JsonProperty("description")
    public String description;
    @JsonProperty("dlcName")
    public String dlcName;
    @JsonProperty("isDlcFree")
    public boolean isDlcFree;
    @JsonProperty("price")
    public int price;
    @JsonProperty("rating")
    public int rating;
    @JsonProperty("similarDlc")
    public SimilarDlsDTO similarDlc;

    public DlsDTO(){}

    public DlsDTO(String description, String dlcName, boolean isDlcFree, int price, int rating, SimilarDlsDTO similarDlc) {
        this.description = description;
        this.dlcName = dlcName;
        this.isDlcFree = isDlcFree;
        this.price = price;
        this.rating = rating;
        this.similarDlc = similarDlc;
    }
}
