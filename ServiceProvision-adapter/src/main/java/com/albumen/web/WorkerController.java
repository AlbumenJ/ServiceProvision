package com.albumen.web;

import com.albumen.domain.worker.WorkerService;
import com.albumen.result.CommonResult;
import com.albumen.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @RequestMapping("/register")
    public CommonResult<Void> register(@RequestParam("username") String username,
                                       @RequestParam("password") String password, Worker worker) {
        workerService.register(username, password, worker);
        return new CommonResult<Void>().success();
    }

    @RequestMapping("/search")
    public CommonResult<List<Worker>> search(@RequestParam("category") String category) {
        return new CommonResult<List<Worker>>().success(workerService.search(category));
    }
}
