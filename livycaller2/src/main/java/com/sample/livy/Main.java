package com.sample.livy;

import java.io.File;
import java.net.URI;
import org.apache.livy.*;
import sample.LivyTest2;
import sample.LivyTest;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello From Caller App!");
        //  clustername.azurehdinsight.net admin:greatPassword08!! C:\Use\\user\Documents\project\src\main\resources\default_artifact.jar

        assert args.length >= 3 : "Wrong Number of argument!!!\n Acceptable Format: ClusterURL user:password jarPath";
        String clusterURL = args[0];
        String userPassword = args[1];
        String jarPath = args [2];
        LivyClient client = new LivyClientBuilder(false)
                .setURI(new URI("https://".concat(userPassword).concat("@").concat(clusterURL).concat("/livy")))
                .build();

        try {
            final long t1 = System.currentTimeMillis();
            client.uploadJar(new File(jarPath)).get();
            final long t2 = System.currentTimeMillis();
            System.out.println("Uploading jar time is execution time: " + (t2 - t1)/1e+3 );

            final long startTime = System.currentTimeMillis();
            Object pi = client.submit(new LivyTest()).get();
            System.out.println("Pi is roughly: " + pi);

            Object v = client.submit(new LivyTest2(36)).get();
            System.out.println("Square of this value is : " + v);
            final long endTime = System.currentTimeMillis();

            System.out.println("Total execution time: " + (endTime - startTime)/1e+3 );
        } finally {
            client.stop(true);
        }

    }
}
