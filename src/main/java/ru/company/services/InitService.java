package ru.company.services;

import ru.company.dto.games.DlsDTO;
import ru.company.dto.games.GameDTO;
import ru.company.dto.games.RequirementsDTO;
import ru.company.dto.games.SimilarDlsDTO;

import java.util.ArrayList;
import java.util.List;

public class InitService {

    public static GameDTO gameDTOInit() {
        SimilarDlsDTO similarDlsDTO = new SimilarDlsDTO("test", false);
        RequirementsDTO requirementsDTO = new RequirementsDTO(11, "win", 32, "rtx 4090");
        DlsDTO dlsDTO = new DlsDTO("newDls", "cool", true, 0, 10, similarDlsDTO);
        List<DlsDTO> dlsList = new ArrayList<>();
        dlsList.add(dlsDTO);
        List<String> tagList = new ArrayList<>();
        tagList.add("kaif");
        String dateString = "2023-11-20T04:58:06.210Z";
        return new GameDTO("ubisoft", "cool", dlsList, 0, "action",
                false, 5000, dateString, 10, true, requirementsDTO, tagList, "igra");
    }
}
