package queries;

import java.util.Iterator;
import readers.LineFileReader;
import iterators.Apply;
import iterators.ApplyFunction;
import binaryTree.BinaryTree;
import iterators.Predicate;
import iterators.ReduceFunction;
import java.io.IOException;
import java.util.List;

public class FamilyRecordQuery {

    public static void main(String[] args) throws IOException {
        Iterator<String> lines = new LineFileReader("familyrecord.csv");
        Iterator<Object[]> recordsGeneric = new Apply<>(new ParseCSVLine(), lines);
        Iterator<FamilyRecord> records = new Apply<>(new ConvertToRecord(), recordsGeneric);

        BinaryTree<FamilyRecord> treeOfRecords = new BinaryTree();
        BinaryTree<String> treeOfNames = new BinaryTree();
        while (records.hasNext()) {
            FamilyRecord record = records.next();
            treeOfNames.insertNode(record.name);
            treeOfRecords.insertNode(record);
        }

        treeOfNames.displayTree();

        //PART 3       
        int generationNumberB = 3;
        String ageGroupB = treeOfNames.reduceAtDepthRecursive(generationNumberB, new ConcatentateNames());
        System.out.println("Generation " + generationNumberB + ": " + ageGroupB);

        int generationNumberA = 4;
        String ageGroupA = treeOfRecords.reduceAtDepth(3, new ConcatenateNamesFromRecord());
        System.out.println("Generation " + generationNumberA + ": " + ageGroupA);

        //END PART 3
        //PART 5
        List<FamilyRecord> robertList = treeOfRecords.selectIterative(new SelectName("Robert"));
        System.out.println(robertList);
        List<FamilyRecord> engineerList = treeOfRecords.selectRecursive(new SelectJob("Engineer"));
        System.out.println(engineerList);
        //END PART 5
        //Part 6
        List<FamilyRecord> under50 = treeOfRecords.selectIterative((new SelectAge(50)));
        // //INSERT CODE HERE
        System.out.println(under50);
    }

    private static class ConcatenateNamesFromRecord implements ReduceFunction<FamilyRecord, String> {

        //PART 3
        @Override
        public String combine(String soFar, FamilyRecord x) {
            if (soFar.equals(" ")) {
                return soFar + x.name;
            } else {
                return soFar + " " + x.name;
            }
        }

        @Override
        public String initialValue() {
            return " ";
        }
    }

    private static class ConcatentateNames implements ReduceFunction<String, String> {

        //PART 3
        @Override
        public String combine(String soFar, String x) {
            if (soFar.equals(" ")) {
                return soFar + x;
            } else {
                return soFar + " " + x;
            }

        }

        @Override
        public String initialValue() {
            return " ";
        }

    }

    //PART 5 START
     private static class SelectName implements Predicate<FamilyRecord>{
         String input;
         public SelectName(String x){
             this.input = x;
             
         }
         @Override
         public boolean check(FamilyRecord data){
             return input.equals(data.name);
         }
     }
      private static class SelectJob implements Predicate<FamilyRecord>{
          String input;
          public SelectJob(String x){
              this.input = x;
          }
          @Override
          public boolean check(FamilyRecord data){
              return data.job.contains(input);
          }
       }
    //PART 5 END
    //PART 6 add new class here\
      private static class SelectAge implements Predicate<FamilyRecord>{
          Integer input;
          public SelectAge(Integer x){
              this.input = x;
          }
          @Override
          public boolean check(FamilyRecord data){
              return (2018 - data.birthYear )< input;
          }
      }
    //////////////// Dont edit after here //////////////////////
    // Converts a CSV record from an Object[] to a FlightRecord
    private static class ConvertToRecord implements ApplyFunction<Object[], FamilyRecord> {

        @Override
        public FamilyRecord apply(Object[] r) {
            return new FamilyRecord((String) r[0],
                    (int) r[1],
                    (String) r[2],
                    (String) r[3],
                    (String) r[4]);
        }
    }

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

    private static class FamilyRecord {

        public final String name;
        public final int birthYear;
        public final String city;
        public final String state;
        public final String job;

        private FamilyRecord(String n, int y, String c, String s, String j) {
            name = n;
            birthYear = y;
            city = c;
            state = s;
            job = j;
        }

        @Override
        public String toString() {
            return "Family record(Name=" + name + ", Birth Year=" + birthYear + ", City=" + city + ", State=" + state + ", Job=" + job + ")";
        }
    }
}
