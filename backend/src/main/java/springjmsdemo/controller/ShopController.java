package springjmsdemo.controller;

import springjmsdemo.model.Product;
import springjmsdemo.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping(value = "/listall")
    @ResponseBody
    public List<Product> listAll(@PathVariable("productType") String productType, HttpServletRequest httpServletRequest){
        log.info("/listall");
        shopService.registerVisit(httpServletRequest.getRemoteUser());
        return shopService.listall();
    }

    @GetMapping(value = "/list/{productType}")
    @ResponseBody
    public List<Product> listProducts(@PathVariable("productType") String productType, HttpServletRequest httpServletRequest){
        log.info("/list/" + productType);
        shopService.registerVisit(httpServletRequest.getRemoteUser());
        return shopService.listProducts(productType);
    }

    @GetMapping(value = "/sell/{productId}")
    @ResponseBody
    public Map<String, String> sell(@PathVariable("productId") int productId){
        log.info("/sell/" + productId);
        Map<String, String> resp = new HashMap<>();
        resp.put("productId", Integer.toString(productId));
        if (shopService.sell(productId) != null){
            resp.put("time", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        else {
            resp.put("error", "failed transaction");
        }
        return resp;
    }

}