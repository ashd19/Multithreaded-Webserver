# Single-Threaded Java Web Server

This project demonstrates a basic synchronous server implementation using Java's `ServerSocket` and `Socket` classes.

## How It Works

The server operates on a single thread, meaning it can only handle **one client connection at a time**.

1. **Start Server**: The `ServerSocket` binds to port `8010`.
2. **Wait for Connection**: The server calls `accept()`, which *blocks* (pauses execution) until a client connects.
3. **process Request**: Once a client connects, the server:
    - Reads the input stream from the client.
    - Writes a response to the output stream.
    - Wait for the client to close or finish.
4. **Close Connection**: The server closes the socket and loops back to `accept()` the next client.

### Visual Representation

```text
[Server] Waiting... (accept())
   |
   +--- [Client 1] Connects!
   |      |
   |      +-> Server processes Client 1 (Reading/Writing)
   |      |   (Blocks other clients)
   |      +-> Server finishes Client 1.
   |
   +--- [Client 2] Connects! (Now processed)
```

## Running the Project

### 1. Compile the Java Files

```bash
javac Server.java
javac Client.java
```

### 2. Run the Server

Open a terminal and run:

```bash
java Server
```

*You should see: `Server is listening on port 8010`*

### 3. Run the Client

Open a **new** terminal window (or tab) and run:

```bash
java Client
```

*You should see output similar to:*

```text
Hello from single-threaded server!
```

## Limitations

Because this server is single-threaded, if `Client 1` takes 10 seconds to process a request (e.g., a slow database query or file download), `Client 2` must wait 10 seconds before the server even *accepts* its connection. This is why multi-threaded servers (or non-blocking I/O) are necessary for real-world applications.
