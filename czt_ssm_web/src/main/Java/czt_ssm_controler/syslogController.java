package czt_ssm_controler;

import czt_ssm_domain.Syslog;
import czt_ssm_service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class syslogController {

    @Autowired
    private ISyslogService iSyslogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Syslog> list = iSyslogService.findAll();
        mv.addObject("sysLogs",list);
        mv.setViewName("syslog-list");

        return mv;
    }

}
