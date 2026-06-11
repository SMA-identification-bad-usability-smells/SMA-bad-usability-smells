package br.com.sma_bad_smells.sma.utils;

import br.com.sma_bad_smells.sma.domain.dto.LogDTO;
import br.com.sma_bad_smells.sma.domain.models.Logs;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;

public class LogParser {
    private final ObjectMapper mapper;

    public LogParser(){
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Logs> parse(String json) throws Exception {
        List<Logs> result = new ArrayList<>();

        JsonNode root = mapper.readTree(json);

        List<JsonNode> lists = root.findValues("logsList");
        for (JsonNode logsList : lists) {
            for (JsonNode node : logsList) {
                LogDTO dto = mapper.treeToValue(node, LogDTO.class);
                result.add(toLogs(dto));
            }
        }

        return result;
    }

    private Logs toLogs(LogDTO dto){
        Logs log = new Logs();
        log.setId(dto.getId());
        log.setCoordinatesX(dto.getCoordinatesX());
        log.setCoordinatesY(dto.getCoordinatesY());
        log.setGestureDirection(dto.getDirection());
        log.setInteractionType(dto.getType());
        log.setTimestamp(dto.getTimestamp());
        log.setTargetElementId(dto.getTargetElementId());
        return log;
    }
}
