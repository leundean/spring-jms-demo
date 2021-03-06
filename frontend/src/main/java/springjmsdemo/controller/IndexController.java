package springjmsdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Configuration
public class IndexController {

    @Value("${BACKEND_HOSTNAME:defbackendhostname}")
    private String backendhostname;

    @Value("${EXT_HOSTNAME:deflocalhostname}")
    private String hostname;

    @RequestMapping({ "/etc","/welcome","/products" })
    public String redirect() {
        return "forward:/";
    }

//    @GetMapping(value = {"/", "/products"}, produces = MediaType.TEXT_HTML_VALUE)
    @GetMapping(value = {"/"}, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String index() {
        System.out.println(hostname + " : " + backendhostname);
        return "<!doctype html>" +
        "<html lang='en'>" +
        "<head>" +
          "<meta charset='utf-8'>" +
          "<title>Shop</title>" +
          "<script type='text/javascript'>" +
                "var apiURL = '" + backendhostname + "';" +
            "</script>" +
          "<base href='" + hostname + "'>" +
          "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
          "<link rel='icon' type='image/x-icon' href='favicon.ico'>" +
          "<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500&amp;display=swap' rel='stylesheet'>" +
          "<link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet'>" +
        "<link rel='stylesheet' href='styles.css'></head>" +
        "<body>" +
          "<app-root></app-root>" +
        "<script src='runtime.js' defer></script><script src='polyfills.js' defer></script><script src='vendor.js' defer></script><script src='main.js' defer></script></body>" +
        "</html>";
    }
}