package multithread.blockingqueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 找出在目录下的所有文件
 */
public class FileEnumerationTask implements Runnable {
    public static File DUMMY = new File("");

    private BlockingQueue<File> queue;

    private File startingDirectory;

    /**
     * Constructs a FileEnumerationTask.
     *
     * @param queue             the blocking queue to which the enumerated files are added
     * @param startingDirectory the directory in which to start the enumeration
     */
    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories.
     *
     * @param directory the directory in which to start
     */
    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory())
                enumerate(file);
            else
                queue.put(file);
        }
    }
}
