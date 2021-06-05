package com.albumen.domain.worker;

import com.albumen.worker.Worker;

public interface WorkerService {
    boolean register(String username, String password, Worker worker);
}
