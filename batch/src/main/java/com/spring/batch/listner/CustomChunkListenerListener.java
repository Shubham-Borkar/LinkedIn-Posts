package com.spring.batch.listner;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;

@SuppressWarnings("deprecation")
public class CustomChunkListenerListener extends ChunkListenerSupport {

    private int itemCount = 0;

    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        // Initialize the count at the start of each chunk
        itemCount = 0;
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        itemCount += context.getStepContext().getStepExecution().getReadCount();
        if (itemCount % 10 == 0) {
            System.out.println("Processed " + itemCount + " items so far.");
        }
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        // Handle chunk errors if needed
    }

    
    
    
    
}