package storage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import storage.service.DistributionService;
import storage.service.StorageService;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class ManualController {

    @Autowired
    StorageService storageService;

    @GetMapping(value = "/report")
    @ResponseBody
    public Map<String, String> report() {
        log.info("/report");
        Map<String, String> resp = new HashMap<>();
        storageService.reportStatus();
        resp.put("reported", "true");
        return resp;
    }

    @Autowired
    DistributionService distributionService;

    @GetMapping(value = "/dist")
    @ResponseBody
    public Map<String, String> dist() {
        log.info("/dist");
        Map<String, String> resp = new HashMap<>();
        distributionService.distribute();
        resp.put("distributed", "true");
        return resp;
    }

}