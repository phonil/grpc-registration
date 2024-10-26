package common;

import java.util.List;

public class LoggingProcessor implements Processor {

    private final Processor processor;

    public LoggingProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public boolean support(InfoType infoType) {
        return this.processor.support(infoType);
    }

    @Override
    public List<?> getData(GrpcClientCaller grpcClientCaller) {
        logging();
        return this.processor.getData(grpcClientCaller);
    }

    private void logging() {
        System.out.println("LOGGING CALL !!");

//        String logMessage = String.format("[User ID]: %s, [Command Type]: %s, [Time]: %s", userId, commandType, LocalDateTime.now());
//
//        try (FileWriter logFileWriter = new FileWriter("system_logs.txt", true)) {
//            logFileWriter.write(logMessage + "\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
