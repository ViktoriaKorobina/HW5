package org.example;

import java.util.concurrent.locks.ReentrantLock;


public class Main {
    public static void main(String[] args) {
        ReentrantLock[] forks = new ReentrantLock[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock();
        }

        DiningPhilosophers.Philosopher[] philosophers = new DiningPhilosophers.Philosopher[5];
        for (int i = 0; i < philosophers.length; i++) {
            ReentrantLock leftFork = forks[i];
            ReentrantLock rightFork = forks[(i + 1) % forks.length];
            philosophers[i] = new DiningPhilosophers.Philosopher(i, leftFork, rightFork);
            philosophers[i].start();
        }

        for (DiningPhilosophers.Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
