package academy.belhard.lms.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileServiceImpl {
    public static void writeToFile(String fileAddress, String content) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileAddress))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileContent(String fileAddress) {
        StringBuilder content = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(fileAddress)) {
            byte[] buffer = new byte[1000];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, read));
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
