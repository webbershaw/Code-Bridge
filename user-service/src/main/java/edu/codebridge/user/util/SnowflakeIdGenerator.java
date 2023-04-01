package edu.codebridge.user.util;

public class SnowflakeIdGenerator {

    private final static long START_STMP = 1609430400000L; // 起始时间戳，2021-01-01 00:00:00

    private final static long SEQUENCE_BIT = 12; // 序列号占用的位数

    private final static long WORKER_BIT = 10; // 机器ID占用的位数

    private final static long MAX_WORKER_ID = -1L ^ (-1L << WORKER_BIT); // 最大机器ID

    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT); // 最大序列号

    private static long lastStmp = -1L; // 上次生成ID的时间戳

    private static long sequence = 0L; // 序列号

    private static long workerId = 1L; // 机器ID，可以手动设置

    public static synchronized long nextId() {
        long currStmp = System.currentTimeMillis();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currStmp = getNextMillis(lastStmp);
            }
        } else {
            sequence = 0L;
        }

        lastStmp = currStmp;
        return (currStmp - START_STMP) << (SEQUENCE_BIT + WORKER_BIT) | (workerId << SEQUENCE_BIT) | sequence;
    }

    private static long getNextMillis(long lastStmp) {
        long currStmp = System.currentTimeMillis();
        while (currStmp <= lastStmp) {
            currStmp = System.currentTimeMillis();
        }
        return currStmp;
    }
}
