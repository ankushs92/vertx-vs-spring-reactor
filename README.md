Usually I just use Vert.x for anything related with reactive programming on the JVM. I wanted to see how the new shiny Spring Reactor compares with Vert.x

Both packages are written in Groovy. The idea is to send off `9 hash get` commands to redis. 
This is done via :
	
1. In the vert.x sample, Vertx-Redis-Client is used and 9 hash get requests are sent off to redis. The library uses pipeline as default mode
2. In the spring reactor sample, Lettuce driver is used

The 9 values returned by Redis are then assembled and serialized to JSON. This is done via a `HTTP GET` request to URL ```localhost:8080/fake/value``` .

# Benchmark

The benchmark was done on a Macbook Pro, 2.2 GHz Intel Core i7, 16 GB Ram, with 4 cores 

Vert.x :

```
Running 1m test @ http://localhost:8080/fake/value
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.77ms  780.31us  34.43ms   87.70%
    Req/Sec   572.60     72.37     0.93k    68.58%
  342281 requests in 1.00m, 116.86MB read
Requests/sec:   5697.72
Transfer/sec:      1.95MB
```

Spring Reactor :

```
Running 1m test @ http://localhost:8080/fake/value
  10 threads and 10 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    19.71ms   35.76ms 632.54ms   96.86%
    Req/Sec    66.22     36.50   212.00     71.83%
  39242 requests in 1.00m, 13.73MB read
Requests/sec:    652.96
Transfer/sec:    234.03KB

```

# Winner

Vert.x is a clear winner here, with almost 9x more requests than Spring Reactor.