package com.albumen.domain.worker;

import com.albumen.user.User;
import com.albumen.user.UserMapper;
import com.albumen.worker.Worker;
import com.albumen.worker.WorkerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkerMapper workerMapper;


    @Override
    public boolean register(String username, String password, Worker worker) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleId(1);

        userMapper.insert(user);

        worker.setWorkerId(user.getUserId());
        workerMapper.insert(worker);

        return true;
    }

    @Override
    public List<Worker> search(String category) {
        QueryWrapper<Worker> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Worker::getCategory, category);

        return workerMapper.selectList(queryWrapper);
    }
}
