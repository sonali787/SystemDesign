# Java Concurrency & Multi-Threading Demos

This directory contains key multi-threading and concurrency concepts in Java, organized by sub-project modules.

## Project Directory Structure

```
threads/
├── basic-concurrency/          # Core Threading & Memory Model
├── executor-future/            # Thread Pools & Async Computation
├── locks-and-sync/             # Explicit Locks & Synchronization
└── read-write-locks/           # Shared vs. Exclusive Locking
```

---

## Demos & Concepts Covered

### 1. [basic-concurrency](./basic-concurrency)
Focuses on thread creation, shared resource access issues, and standard solutions:
* **Race Conditions:** Demonstrates the non-atomicity of compound operations (e.g., `count++`) and how thread preemption causes incorrect data states.
* **Synchronized Blocks & Methods:** Explains mutual exclusion using Java's built-in monitor locks (`synchronized`).
* **Volatile Visibility:** Demonstrates the `volatile` keyword, illustrating how it forces threads to read/write directly to main memory (preventing CPU caching issues) but does *not* provide atomicity.
* **Atomic Variables & CAS:** Shows lock-free thread safety using `AtomicInteger` and a Compare-And-Swap loop.

### 2. [executor-future](./executor-future)
Demonstrates high-level concurrency utilities from `java.util.concurrent`:
* **Thread Pools:** Uses `ExecutorService` with a fixed thread pool to run asynchronous background tasks.
* **Future Blocking:** Demonstrates how `Future.get()` blocks the calling thread (e.g., the main thread) until the result is computed, and highlights the importance of shutting down executor services.

### 3. [locks-and-sync](./locks-and-sync)
Examines explicit locking mechanisms using the `java.util.concurrent.locks` package:
* **Reentrant Locks:** Shows mutual exclusion using `ReentrantLock` for structured thread coordination.
* **Lock Timeouts:** Uses `tryLock(timeout, unit)` to attempt lock acquisition without indefinite blocking.
* **Expiring Locks:** Demonstrates custom lock expiration using a `ScheduledExecutorService` to auto-release locks after a set duration to avoid deadlocks.

### 4. [read-write-locks](./read-write-locks)
Highlights fine-grained concurrency control when reads outnumber writes:
* **ReadWriteLocks:** Utilizes `ReentrantReadWriteLock` to allow multiple concurrent readers (shared locks) to access shared data, while enforcing exclusive access for writing (exclusive lock).
