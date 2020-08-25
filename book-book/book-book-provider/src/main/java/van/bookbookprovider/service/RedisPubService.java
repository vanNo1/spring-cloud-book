package van.bookbookprovider.service;

public interface RedisPubService {
     void sendMessage(String channel, String message);
}
