package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client", url = "https://nsi.gasps-dev.gost-group.com")
public interface ApiClient {

    @GetMapping("/api/v1/mdm/elements/c333d854-7979-480e-b8ea-0e080177c0fc/entries/getEntries")
    String get(Pageable pageable);

}
