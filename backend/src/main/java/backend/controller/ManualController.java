package backend.controller;

import backend.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class ManualController {

    @Autowired
    ShopService shopService;

    @GetMapping(value = "/report")
    @ResponseBody
    public Map<String, String> listProducts() {
        log.info("/report");
        Map<String, String> resp = new HashMap<>();
        shopService.reportStatus();
        resp.put("reported", "true");
        return resp;
    }
}