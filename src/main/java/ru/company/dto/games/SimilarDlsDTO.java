package ru.company.dto.games;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimilarDlsDTO {
    @JsonProperty("dlcNameFromAnotherGame")
    public String dlcNameFromAnotherGame;
    @JsonProperty("isFree")
    public boolean isFree;

    public SimilarDlsDTO(){}

    public SimilarDlsDTO(String dlcNameFromAnotherGame, boolean isFree) {
        this.dlcNameFromAnotherGame = dlcNameFromAnotherGame;
        this.isFree = isFree;
    }
}
