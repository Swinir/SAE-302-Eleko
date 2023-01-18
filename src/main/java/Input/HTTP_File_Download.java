package Input;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class HTTP_File_Download {

    public static void downloadFile(String urlStr, String fileName) {
        try {
            URL url = new URL(urlStr); // URL of the file
            ReadableByteChannel rbc = Channels.newChannel(url.openStream()); // Open the channel
            FileOutputStream fos = new FileOutputStream(fileName); // Open the output stream
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE); // Copy the contents of the channel to the output stream
            fos.close(); // Close the output stream
            rbc.close(); // Close the channel
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
