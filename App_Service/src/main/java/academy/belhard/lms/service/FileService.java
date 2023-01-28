package academy.belhard.lms.service;

public interface FileService {
    void writeToFile(String fileAddress, String content);

    String getFileContent(String fileAddress);
}
