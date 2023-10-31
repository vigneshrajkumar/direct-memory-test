package com.rajkumarv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class Main {
    
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final int BOCK_SIZE =
            Integer.parseInt(System.getProperty("BOCK_SIZE", "100"));

    private static final int WAIT_DURATION =
            Integer.parseInt(System.getProperty("WAIT_DURATION", "1"));

    private static final int ONE_MB_IN_BYTES = 1024 * 1024;

    public static void main(String[] args) {
        logger.info("NIO Hogger; allocating " + BOCK_SIZE + "mb with " + WAIT_DURATION + "s waits");

        logger.info("start..");

        long totalAllocation = 0;

        try{
            while (true) {
                ByteBuffer buf = ByteBuffer.allocateDirect(BOCK_SIZE * ONE_MB_IN_BYTES);
                totalAllocation += buf.capacity();
                logger.info("Total Allocation:" + totalAllocation / ONE_MB_IN_BYTES + "mb");
                Thread.sleep(WAIT_DURATION * 1000L);
            }
        } catch (Exception e){
            logger.error("allocation failed", e);
        }

        logger.info("end..");
    }
}
