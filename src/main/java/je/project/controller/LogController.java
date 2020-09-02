package je.project.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import je.project.domain.Result;
import je.project.service.LogService;
import je.project.utils.LogUtils;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;
    @Value("${log.remainingdays}")
    private Integer remainingDays;

    @RequestMapping("/")
    public void log(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/log/showlogs");
    }

    @RequestMapping("/logtest")
    @ResponseBody
    public Result addLog(@RequestParam("level") Integer level, @RequestParam("content") String content) {
        switch (level) {
        case 0:
            LogUtils.v(this, content);
            break;
        case 1:
            LogUtils.d(this, content);
            break;
        case 2:
            LogUtils.i(this, content);
            break;
        case 3:
            LogUtils.w(this, content);
            break;
        case 4:
            LogUtils.e(this, content);
            break;
        default:
            return new Result(1, "level等级错误！");
        }
        return new Result(0, "操作成功！");
    }

    @RequestMapping(value = { "/showlogs", "/showlogs/", "/showlogs/{level}", "/showlogs/{level}/",
            "/showlogs/{level}/{page}" })
    public String show(Model model, @PathVariable(value = "level", required = false) Integer level,
            @PathVariable(value = "page", required = false) Integer page) {
        if (level == null || level < 0 || level > 4) {
            level = 0;
        }
        if (page == null || page < 1 || page > remainingDays) {
            page = 1;
        }
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(-page + 1);
        model.addAttribute("level", level);
        model.addAttribute("page", page);
        model.addAttribute("days", remainingDays);
        model.addAttribute("logs",
                logService.getLogs(level,
                        new Timestamp(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()),
                        new Timestamp(localDate.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli())));
        return "log/showlog";
    }

    /**
     * 定时清除陈旧日志。
     */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void removeOldLogs() {
        logService.removeOldLogs(remainingDays);
        LogUtils.i(this, "定时清理超过" + remainingDays + "天的日志文件。");
    }
}
