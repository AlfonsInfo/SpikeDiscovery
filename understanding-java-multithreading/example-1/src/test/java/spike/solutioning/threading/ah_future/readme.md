# Concurrency Utilities
Concurrency Utilities memperkenalkan 3 packages baru di Java 5, yaitu
1. java.util.concurrent
2. java.util.concurrent.atomic, dan
3. java.util.concurrent.locks

# Knp Butuh High Level API?
1. Management Thread manual sulit
2. Mudah terjadi kesalahan seperti race condition & deadlock
3. Tidak produktif
4. Harga Object Thread 512 KB - 1mb


# Threadpool
1. Kelas untuk management thread
2. Kelas ini menanmpung thread
3. Pembuatan kelas ini secara manual jarang dilakukan kecuali dalam kasus benar-benar ingin dituning

# Rejected ExecutionHandler
1. Kelas yang ngehandle task yang ditolak
2. By default throw exception

# Executor Service
1. Ini adalah interface dari ThreadPool
2. Executors -> kelas utility yang membantu pembuatan ExecutorService secara lebih mudah

![img.png](img.png)


