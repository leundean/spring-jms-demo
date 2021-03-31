package springjmsdemo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springjmsdemo.sender.TopicSender;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ExampleCtrl {

    @Autowired
    TopicSender topicSender;

    @RequestMapping(value = "/send/{topic}/{msg}", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, String> send(@PathVariable("topic") String topic, @PathVariable("msg") String msg) {
        Map<String, String> resp = new HashMap<>();
        topicSender.send(topic, msg);
        resp.put("topic", topic);
        resp.put("msg", msg);
        return resp;
    }

    @RequestMapping(value = "/add/{topic}", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, String> add(@PathVariable("topic") String topic) {
        Map<String, String> resp = new HashMap<>();
        topicSender.addTopic(topic);
        resp.put("topic-added", topic);
        return resp;
    }

}