package com.albumen.domain.worker;

import com.albumen.worker.Worker;

import java.util.List;

public interface WorkerService {
    boolean register(String username, String password, Worker worker);

    List<Worker> search(String category);
}
