package ngom.web.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hanaijun on 2018/7/4
 */
@Controller
public class Hello {

    @RequestMapping(value={"{html}"}, method=RequestMethod.GET)
    public String helloHtml(@PathVariable("html")String html){
        return html;
    }
}
