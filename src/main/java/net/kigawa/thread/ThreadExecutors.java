package net.kigawa.thread;

import net.kigawa.module.Module;
import net.kigawa.log.LogSender;
import net.kigawa.log.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutors implements  LogSender {
    public static ThreadExecutors THREAD_EXECUTORS;
    private ExecutorService cachedPool;
    private Map<String, ExecutorService> executorServiceMap;

    public ThreadExecutors() {
        THREAD_EXECUTORS = this;
    }

    public static void execute(Runnable runnable) {
        THREAD_EXECUTORS.getCachedPool().execute(runnable);
    }

    public void enable() {
        Logger.getInstance().info("enable thread executor");
        this.cachedPool = Executors.newCachedThreadPool();
        executorServiceMap = new LinkedHashMap<>();
    }

    public void disable() {
        info("disable thread executor");
        for (ExecutorService service : executorServiceMap.values()) {
            cachedPool.execute(() -> {
                try {
                    service.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        cachedPool.shutdown();
        cachedPool = null;
    }

    public ExecutorService getCachedPool() {
        return cachedPool;
    }

    public Map<String, ExecutorService> getExecutorServiceMap() {
        return executorServiceMap;
    }
}
