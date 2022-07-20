package com.example.demo.service;

import com.example.demo.ApiClient;
import com.example.demo.dto.ResponseDto;
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
    public List<Long> getIds() {
        //результат запроса
        ResponseDto page = api.get(PageRequest.of(0, 20));
        //получение всех айдишников
        List<Long> ids = getAllIds(page.getData().getContent()[0].getVersion().getData());
        System.out.println(ids);
        return ids;
    }

    //функция получения айдишников
    private List<Long> getAllIds(Map<String, Object> map) {
        List<Long> res = new ArrayList<>();
        map.keySet().forEach(key -> {
            try {
                Long n = Long.parseLong(key);
                res.add(n);
            }
            catch (NumberFormatException ignored) {}
        });
        map.forEach((key, value) -> {
            if (value instanceof Map<?, ?>) {
                res.addAll(getAllIds((Map<String, Object>) value));
            }
        });
        return res;
    }
}
