package java_lab_7;

import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Crawler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final URLPool pool;
    private int maxDepth;
    static final Pattern hrefPattern = Pattern.compile("<a href=[\"|\'](\\w+://[^\"\']+/[^\\\"\\']+)");

    public Crawler(URLPool pool, int maxDepth) {
        this.pool = pool;
        this.maxDepth = maxDepth;
    }


    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                System.out.print("usage: java java_lab_7.Crawler <URL> <depth> <threadsNum>");
                return;
            }

            URLDepthPair firstPair = new URLDepthPair(args[0], 0);
            URLPool pool = new URLPool();
            pool.add(firstPair);

            // Создаем и заполняем массив с потоками
            int threadsNum = Integer.parseInt(args[2]);
            int maxDepth = Integer.parseInt(args[1]);
            Thread[] threads = new Thread[threadsNum];

            for (int i = 0; i < threadsNum; i++) {
                Crawler task = new Crawler(pool, maxDepth);
                threads[i] = new Thread(task);
                threads[i].start();

                System.out.printf("%s started\n", threads[i].getName());
            }

            while (pool.getWaiters() != threadsNum) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            System.out.println("Stop all");
            pool.stop();

            for (URLDepthPair pair : pool.getClosed()) System.out.println(pair.toString());

            for (var thread: threads) thread.interrupt();

        } catch (IOException e) {
            System.out.print("usage: java java_lab_7.Crawler <URL> <depth> <threadsNum>");
        }
    }

    private boolean close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия сокета");
            return false;
        }

        return true;
    }


    private LinkedList<String> getLines(URLDepthPair url) {
        LinkedList<String> list = new LinkedList<>();

        out.print("GET "+ url.url.getPath() +" HTTP/1.1\r\n");
        out.print("Host: "+ url.url.getHost()+"\r\n");
        out.print("Connection: close\r\n");

        out.print("\r\n");
        out.flush();


        String temp;
        try {
            while ((temp = in.readLine()) != null) {
                list.add(temp);
            }

            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println("Ошибка во время считывания");
            e.printStackTrace();
        }

        return list;
    }


    public LinkedList<URLDepthPair> getLinks(URLDepthPair url) {
        if (!url.check()) return null;

        // Достаем из входящего потока данные
        LinkedList<String> listRead = null;
        try {
            socket = new Socket(url.url.getHost(), 80);
            socket.setSoTimeout(1000);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            listRead = getLines(url);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            while (!close()) {}
        }

        LinkedList<URLDepthPair> pairs = new LinkedList<>();
        URLDepthPair newURL;
        Matcher matcher;

        // Проходимся по каждому элементу, понимаем, есть ли там ссылка, и если есть, то добавляем в список
        for (String line : listRead) {
            matcher = hrefPattern.matcher(line);

            while (matcher.find()) {
                try {
                    newURL = new URLDepthPair(matcher.group(1), url.getDepth() + 1);
                } catch (MalformedURLException e) {continue;}

                if (newURL.check()) pairs.add(newURL);
            }
        }

        return pairs;
    }


    @Override
    public void run() {
        URLDepthPair pair;
        LinkedList<URLDepthPair> result;

        while (pool.isWork()) {
            pair = pool.get();

            if (pair == null) return;
            if (pair.getDepth() > maxDepth) continue;

            result = getLinks(pair);
            Iterator<URLDepthPair> iter = result.iterator();
            while (iter.hasNext()) pool.add(iter.next());

            pool.finish(pair);
        }
    }
}