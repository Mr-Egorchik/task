package com.example.demo.service;

import com.example.demo.ApiClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SpecService {

    private final ApiClient api;

    @PostConstruct
    public List<Long> parse() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String page = api.get(PageRequest.of(0, 20));
        //String page = "{\"k1\":\"v1\",\"1\":[{\"2\": \"a\"}, {\"3\": [{\"31\" : \"v31\"}]}, {\"k2\":\"v2\"}], \"4\":\"v4\", \"5\": {\"6\":\"v6\"}}";
        JsonNode jn = mapper.readTree(page);
        //List<Long> res = new ArrayList<>(getLong(jn));
        List<Long> res = getAll(jn);
        System.out.println(res);
        return res;
    }

    private List<Long> getLong(JsonNode jn) {
        List<Long> res = new ArrayList<>();
        jn.fieldNames().forEachRemaining(str -> {
            try {
                Long num = Long.parseLong(str);
                res.add(num);
            }
            catch (NumberFormatException ignored) {}
        });
        return res;
    }

    private List<Long> getAll(JsonNode jn) {
        List<Long> res = new ArrayList<>(getLong(jn));
        List<Long> innerNodes = new ArrayList<>();
        for (Long n: res) {
            JsonNode subNode = jn.get(n.toString());
            if (subNode.isArray()) {
                for (JsonNode innerSubNode : subNode) {
                    innerNodes.addAll(getAll(innerSubNode));
                }
            }
            else {
                innerNodes.addAll(getAll(subNode));
            }
        }
        res.addAll(innerNodes);
        return res;
    }

}
