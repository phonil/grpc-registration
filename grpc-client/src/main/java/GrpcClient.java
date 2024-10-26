import common.*;
import course.CourseProcessor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import student.StudentProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GrpcClient {

    public static ProcessorFactory processorFactory;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8084)
                .usePlaintext()
                .build();

        StudentProcessor studentProcessor = new StudentProcessor();
        CourseProcessor courseProcessor = new CourseProcessor();
        List<Processor> processorList = new ArrayList<>();
        processorList.add(new LoggingProcessor(studentProcessor));
        processorList.add(new LoggingProcessor(courseProcessor));
        processorFactory = new ProcessorFactory(processorList);

        GrpcClientCaller grpcClientCaller = new GrpcClientCaller(channel);
        System.out.println("Client's ready!");

        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
        try {
            auth:
            while (true) {
                printAuthMenu();
                String sChoice = clientInput.readLine().trim();
                switch (sChoice) {
                    case "1":
                        if (login(grpcClientCaller, clientInput)) {
                            System.out.println("Login Success !");
                            break auth;
                        }
                        else
                            System.out.println("Login Fail !");
                        continue auth;
//                    case "2":
//                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invalid Number !");
                        break;
                }
            }

            process:
            while (true) {
                printProcessMenu();
                String sChoice = clientInput.readLine().trim();
                switch (sChoice) {
                    case "1", "2" :
                        getInfo(grpcClientCaller, sChoice);
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invalid Number !");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        channel.shutdown();
    }

    private static boolean login(GrpcClientCaller grpcClientCaller, BufferedReader clientInput) throws IOException {
        System.out.println("Login ID : ");
        String inputLoginId = clientInput.readLine().trim();
        System.out.println("Password : ");
        String inputPassword = clientInput.readLine().trim();
        return grpcClientCaller.login(inputLoginId, inputPassword);
    }

    private static void getInfo(GrpcClientCaller grpcClientCaller, String sChoice) {
        InfoType infoType = sChoice.equals("1") ? InfoType.STUDENT : InfoType.COURSE;
        Processor processor = processorFactory.findProcessor(infoType);
        AtomicInteger idx = new AtomicInteger();
        processor.getData(grpcClientCaller).stream()
                .map(info -> "[" + infoType.toString() + "_" + idx.incrementAndGet() + "_Information] \n" + info)
                .forEach(System.out::println);
    }

    private static void printAuthMenu() {
        System.out.println("******************** AUTH MENU ********************");
        System.out.println("1. Login");
//        System.out.println("2. Join");
        System.out.println("x. Exit");
    }

    private static void printProcessMenu() {
        System.out.println("\n******************** REGISTRATION MENU ********************");
        System.out.println("1. List Students");
        System.out.println("2. List Courses");
        System.out.println("x. Exit");
    }
}
