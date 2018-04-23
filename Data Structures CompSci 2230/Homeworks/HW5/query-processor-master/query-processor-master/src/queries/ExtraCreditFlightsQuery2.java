package queries;

import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Reduce;
import iterators.ReduceFunction;
import readers.LineFileReader;
import java.io.IOException;
import java.util.Iterator;

public class ExtraCreditFlightsQuery2 {
    public static void main(String[] args) throws IOException {
        Iterator<String> lines = new LineFileReader("../flights-tiny.csv");
        Iterator<Object[]> recordsGeneric = new Apply<>(new ParseCSVLine(), lines);
        Iterator<FlightRecord> records = new Apply<>(new ConvertToRecord(), recordsGeneric);
        Iterator<Integer> total = new Reduce(new CountCities("Los Angeles CA", "New York NY"), records);
        Iterator<Integer> cancelledOccurences = new Reduce(new CheckCancelled("Los Angeles CA", "New York NY"), records);
        
        while (cancelledOccurences.hasNext()) {
            //float percentage = cancelledOccurences.next() / total.next();
            System.out.println(total.next());
            System.out.println(cancelledOccurences.next());
        }
    }
    
    // object for storing one line in the flights data
    private static class FlightRecord {

        public final int year;
        public final int month;
        public final int dayOfMonth;
        public final String airline;
        public final int flightNumber;
        public final String originCity;
        public final String destCity;
        public final int cancelled; // 0=not cancelled, 1=cancelled
        public final int time;  // flight time in minutes

        public FlightRecord(int year, int month, int dayOfMonth, String airline, int flightNumber, String originCity, String destCity, int cancelled, int time) {
            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;
            this.airline = airline;
            this.flightNumber = flightNumber;
            this.originCity = originCity;
            this.destCity = destCity;
            this.cancelled = cancelled;
            this.time = time;
        }

        @Override
        public String toString() {
            return "FlightRecord{" + "year=" + year + ", month=" + month + ", dayOfMonth=" + dayOfMonth + ", airline=" + airline + ", flightNumber=" + flightNumber + ", originCity=" + originCity + ", destCity=" + destCity + ", cancelled=" + cancelled + ", time=" + time + '}';
        }
    }
    
    // Converts a CSV record from an Object[] to a FlightRecord
    private static class ConvertToRecord implements ApplyFunction<Object[], FlightRecord> {

        @Override
        public FlightRecord apply(Object[] r) {
            try {
                return new FlightRecord((int) r[0],
                                        (int) r[1],
                                        (int) r[2],
                                        (String) r[3],
                                        (int) r[4],
                                        (String) r[5],
                                        (String) r[6],
                                        (int) r[7],
                                        (int) r[8]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                return new FlightRecord((int) r[0], 0, 0, "", 0, "", "", 0, 0);
            }
        }
    }

    // takes a string that is a comma separated list of values and returns an array of Strings and Integers.
    // 
    // Examples
    // "1,2,3,4"
    // [1,2,3,4]
    //
    // "10,cat,dog,22"
    // [10, "cat", "dog", 22]
    private static class ParseCSVLine implements ApplyFunction<String, Object[]> {

        @Override
        public Object[] apply(String x) {
            String[] fields = x.split(",");
            Object[] r = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                // try to convert to integer
                try {
                    r[i] = Integer.parseInt(fields[i]);
                } catch (NumberFormatException ex) {
                    // if it fails, then leave a string
                    r[i] = fields[i];
                }
            }
            return r;
        }
    }
    
    private static class CountCities implements ReduceFunction<FlightRecord, Integer> {
        String input1;
        String input2;
        Integer soFar;
        
        public CountCities(String city1, String city2) {
            this.soFar = initialValue();
            this.input1 = city1;
            this.input2 = city2;
        }
        
        @Override
        public Integer combine(Integer soFar, FlightRecord x) {
            if ((input1.equals(x.originCity) || input1.equals(x.destCity)) && (input2.equals(x.originCity) || input2.equals(x.destCity))) {
                soFar++;
            }
            return soFar;
        }
        
        @Override
        public Integer initialValue() {
            return soFar = 0;
        }
    }
    
    private static class CheckCancelled implements ReduceFunction<FlightRecord, Integer> {
        
        String input1;
        String input2;
        Integer soFar;
        
        public CheckCancelled(String city1, String city2) {
            this.soFar = initialValue();
            this.input1 = city1;
            this.input2 = city2;
        }
        
        @Override
        public Integer combine(Integer soFar, FlightRecord x) {
            if ((input1.equals(x.originCity) || input1.equals(x.destCity)) && (input2.equals(x.originCity) || input2.equals(x.destCity))) {
             //   if (x.cancelled == 1) {
                    soFar ++;
               // }
                
            }
            return soFar;
        }
        
        @Override
        public Integer initialValue() {
            return soFar = 0;
        }
        
    }
}
