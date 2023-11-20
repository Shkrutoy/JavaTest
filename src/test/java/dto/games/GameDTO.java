package dto.games;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class GameDTO {
    @JsonProperty("company")
    public String company;
    @JsonProperty("description")
    public String description;
    @JsonProperty("dlcs")
    public ArrayList<DlsDTO> dlcs;
    @JsonProperty("gameId")
    public int gameId;
    @JsonProperty("genre")
    public String genre;
    @JsonProperty("isFree")
    public boolean isFree;
    @JsonProperty("price")
    public int price;
    @JsonProperty("publish_date")
    public String publish_date;
    @JsonProperty("rating")
    public int rating;
    @JsonProperty("requiredAge")
    public boolean requiredAge;
    @JsonProperty("requirements")
    public RequirementsDTO requirements;
    @JsonProperty("tags")
    public ArrayList<String> tags;
    @JsonProperty("title")
    public String title;

    public GameDTO(){}

    public GameDTO(String company, String description, ArrayList<DlsDTO> dlcs, int gameId, String genre, boolean isFree, int price, String publish_date, int rating, boolean requiredAge, RequirementsDTO requirements, ArrayList<String> tags, String title) {
        this.company = company;
        this.description = description;
        this.dlcs = dlcs;
        this.gameId = gameId;
        this.genre = genre;
        this.isFree = isFree;
        this.price = price;
        this.publish_date = publish_date;
        this.rating = rating;
        this.requiredAge = requiredAge;
        this.requirements = requirements;
        this.tags = tags;
        this.title = title;
    }
}

