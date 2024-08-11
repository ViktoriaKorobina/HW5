package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    static class Philosopher extends Thread {
        private final ReentrantLock leftFork;
        private final ReentrantLock rightFork;
        private final int id;

        public Philosopher(int id, ReentrantLock leftFork, ReentrantLock rightFork) {
            this.id = id;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        private void think() throws InterruptedException {
            System.out.println("Философ " + id + " размышляет.");
            Thread.sleep((int) (Math.random() * 100));
        }

        private void eat() throws InterruptedException {
            System.out.println("Философ " + id + " ест.");
            Thread.sleep((int) (Math.random() * 100));
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    think();
                    leftFork.lock();
                    rightFork.lock();
                    try {
                        eat();
                    } finally {
                        leftFork.unlock();
                        rightFork.unlock();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}

