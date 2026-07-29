# Java Multi-Threading Examples

This project demonstrates two core concurrency and multi-threading models in Java:
1. **Asynchronous Execution with ExecutorService** (Task Submission)
2. **Result Retrieval with Futures** (Callable Submission)

---

## 1. Asynchronous Email Sending with `ExecutorService`

In [EmailService.java](file:///Users/sonaliyadav/Documents/SystemDesign/Threads/src/main/java/org/example/Main.java#L8-L47), we use a fixed thread pool of 10 threads to handle concurrent tasks. 

- **Concept**: `Executors.newFixedThreadPool(10)` creates a pool of reusable worker threads.
- **Task Submission**: `executor.execute(Runnable task)` runs the task asynchronously.
- **Resource Management**: Calling `executor.shutdown()` triggers an orderly shutdown where active tasks finish executing, but no new tasks are accepted.

### Example Run
Running `EmailService.main()` submits 20 email sending tasks. You will see output showing emails being sent across different threads in the pool (`pool-1-thread-1` through `pool-1-thread-10`).

---

## 2. Asynchronous Computations with `Future`

In [FutureExample.java](file:///Users/sonaliyadav/Documents/SystemDesign/Threads/src/main/java/org/example/Main.java#L48-L86), we submit a task that returns a value and retrieve it using a `Future`.

- **Concept**: `Future<T>` represents the pending result of an asynchronous computation.
- **Non-blocking Work**: After submitting the task, the main thread can continue doing other work concurrently.
- **Blocking Retrieval**: `future.get()` is called to retrieve the computation result. If the task is not yet finished, `get()` blocks until it completes.

---

## Running the Examples

Ensure you have Java installed, then run the respective `main` methods:
- Run `org.example.EmailService` to see the `ExecutorService` thread pool simulation.
- Run `org.example.FutureExample` to see the `Future` and task synchronization simulation.
