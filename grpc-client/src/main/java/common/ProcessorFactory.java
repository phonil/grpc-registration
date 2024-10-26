package common;

import common.exception.InformationNotFoundException;

import java.util.List;

public class ProcessorFactory {

    private final List<Processor> processorList;

    public ProcessorFactory(List<Processor> processorList) {
        this.processorList = processorList;
    }

    public Processor findProcessor(final InfoType infoType) {
        return processorList.stream()
                .filter(processor -> processor.support(infoType))
                .findFirst()
                .orElseThrow(() -> new InformationNotFoundException("Processor Not Found"));
    }
}
