package org.example;

import java.util.concurrent.RecursiveTask;

    class FactorialTask extends RecursiveTask<Long> {

    private final int start;
    private final int end;

    public FactorialTask(int start, int end){
        this.start = start;
        this.end = end;
    }

        @Override
        protected Long compute() {
        if (end - start <= 5){
            return sequentialFactorial();
        }

        int mid = (start + end) / 2;

        FactorialTask lowerHalf = new FactorialTask(start, mid);
        FactorialTask upperHalf = new FactorialTask(mid + 1, end);

        lowerHalf.fork();
        Long upperResult = upperHalf.compute();
        Long lowerResult = lowerHalf.join();

        return upperResult * lowerResult;
        }

        private Long sequentialFactorial() {
        long result = 1;
        for (int i = start; i <= end; i++) {
                result *= i;
            }
            return result;
        }
    }
