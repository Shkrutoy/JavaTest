package ru.company.dto.games;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequirementsDTO {
    @JsonProperty("hardDrive")
    public int hardDrive;
    @JsonProperty("osName")
    public String osName;
    @JsonProperty("ramGb")
    public int ramGb;
    @JsonProperty("videoCard")
    public String videoCard;

    public RequirementsDTO(){}

    public RequirementsDTO(int hardDrive, String osName, int ramGb, String videoCard) {
        this.hardDrive = hardDrive;
        this.osName = osName;
        this.ramGb = ramGb;
        this.videoCard = videoCard;
    }
}
