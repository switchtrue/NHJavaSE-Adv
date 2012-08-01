package com.webagesolutions.records;

public class AppBatch
{
  public static void main(String[] args)
  {
    Record r1 = new BeanRecord("dude@acme.com", "Ludwig van Beethoven", "dude", "haydn");
    Record r2 = new BeanRecord("coyote@scme.com", "Wilie Coyote", "coyote", "lovebird");
    Record r3 = new BeanRecord("runner@scme.com", "Road Runner", "runner", "meepbeep");
    Model model = new ModelInMemory();    
    model.putRecord(r1);
    model.putRecord(r2);
    model.putRecord(r3);
    
    for (Record br : model.allRecords())
    {
      System.out.println(br.getName());
    }
    
    System.out.println("Got a record: " + model.getRecord("dude@acme.com"));
  }
}
