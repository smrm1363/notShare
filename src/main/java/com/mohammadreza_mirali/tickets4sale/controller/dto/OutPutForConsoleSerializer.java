//package com.mohammadreza_mirali.tickets4sale.domain.output;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * Created by mmirali on 30/12/2018.
// */
//public class OutPutForConsoleSerializer extends StdSerializer<List<ResultOutputDto>> {
//    public OutPutForConsoleSerializer() {
//        this(null);
//    }
//    public OutPutForConsoleSerializer(Class<List<ResultOutputDto>> t) {
//        super(t);
//    }
//
//    @Override
//    public void serialize(List<ResultOutputDto> resultOutputDtos, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
////        jsonGenerator.writeStri();
//        jsonGenerator.writeArrayFieldStart("inventory");
//        resultOutputDtos.forEach(resultOutputDto ->
//        {
//
//            try {
//                jsonGenerator.writeStringField("genre",resultOutputDto.getGenre());
//                jsonGenerator.writeArrayFieldStart("shows");
//                resultOutputDto.getShows().forEach(showOutPutDto ->
//                {
//                    try {
//                        jsonGenerator.writeStringField("title",showOutPutDto.getTitle());
//                        jsonGenerator.writeNumberField("ticketsLeft",showOutPutDto.getTicketsLeft());
//                        jsonGenerator.writeNumberField("ticketsAvailable",showOutPutDto.getTicketsAvailable());
//                        jsonGenerator.writeStringField("status", String.valueOf(showOutPutDto.getStatus()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//                jsonGenerator.writeEndArray();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        jsonGenerator.writeEndArray();
//    }
//}
